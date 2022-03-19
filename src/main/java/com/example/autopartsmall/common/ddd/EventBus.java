package com.example.autopartsmall.common.ddd;

import org.springframework.transaction.event.TransactionalEventListener;

public interface EventBus {
    @TransactionalEventListener
    void onEvent(DomainEvent event);
    void publish(DomainEvent event);
}
