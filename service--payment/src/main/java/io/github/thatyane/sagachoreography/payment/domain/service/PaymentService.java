package io.github.thatyane.sagachoreography.payment.domain.service;

import io.github.thatyane.sagachoreography.commons.application.dto.OrderRequest;
import io.github.thatyane.sagachoreography.commons.application.dto.PaymentRequest;
import io.github.thatyane.sagachoreography.commons.application.dto.enums.PaymentStatus;
import io.github.thatyane.sagachoreography.commons.application.event.OrderEvent;
import io.github.thatyane.sagachoreography.commons.application.event.PaymentEvent;
import io.github.thatyane.sagachoreography.payment.domain.entity.UserBalance;
import io.github.thatyane.sagachoreography.payment.domain.entity.UserTransaction;
import io.github.thatyane.sagachoreography.payment.infrastructure.repository.UserBalanceRepository;
import io.github.thatyane.sagachoreography.payment.infrastructure.repository.UserTransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final UserBalanceRepository userBalanceRepository;
    private final UserTransactionRepository userTransactionRepository;

    @PostConstruct
    public void initUserBalanceInDB() {
        userBalanceRepository.saveAll(Stream.of(new UserBalance(101L, 500),
                new UserBalance(102L, 3000),
                new UserBalance(103L, 4200),
                new UserBalance(104L, 20000),
                new UserBalance(105L, 999)).collect(Collectors.toList()));
    }

    @Transactional
    public PaymentEvent newOrderEvent(OrderEvent orderEvent) {
        OrderRequest orderRequest = orderEvent.getOrderRequest();

        PaymentRequest paymentRequestDto = PaymentRequest.builder()
                .orderId(orderRequest.getOrderId())
                .userId(orderRequest.getUserId())
                .amount(orderRequest.getAmount())
                .build();

        return userBalanceRepository.findById(orderRequest.getUserId())
                .filter(ub -> ub.getAmount() > orderRequest.getAmount())
                .map(ub -> {
                    ub.setAmount(ub.getAmount() - orderRequest.getAmount());
                    userTransactionRepository.save(UserTransaction.builder()
                            .orderId(orderRequest.getOrderId())
                            .userId(orderRequest.getUserId())
                            .amount(orderRequest.getAmount())
                            .build());
                    return new PaymentEvent(paymentRequestDto, PaymentStatus.PAYMENT_COMPLETED);

                }).orElse(new PaymentEvent(paymentRequestDto, PaymentStatus.PAYMENT_FAILED));
    }

    @Transactional
    public void cancelOrderEvent(OrderEvent orderEvent) {
        userTransactionRepository.findById(orderEvent.getOrderRequest().getOrderId())
                .ifPresent(ut -> {
                    userTransactionRepository.delete(ut);
                    userTransactionRepository.findById(ut.getUserId())
                            .ifPresent(ub -> ub.setAmount(ub.getAmount() + ut.getAmount()));
                });
    }
}
