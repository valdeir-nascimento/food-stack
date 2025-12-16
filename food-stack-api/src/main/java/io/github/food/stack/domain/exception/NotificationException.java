package io.github.food.stack.domain.exception;

import io.github.food.stack.domain.validation.handler.Notification;

public class NotificationException extends DomainException {

    public NotificationException(final String message, final Notification notification) {
        super(message, notification.getErrors());
    }

}
