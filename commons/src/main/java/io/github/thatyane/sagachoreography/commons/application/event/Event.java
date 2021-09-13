package io.github.thatyane.sagachoreography.commons.application.event;

import java.time.LocalDateTime;
import java.util.UUID;

public interface Event {

    UUID getEventId();
    LocalDateTime getEventDate();
}
