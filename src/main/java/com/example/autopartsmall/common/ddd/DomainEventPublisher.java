package com.example.autopartsmall.common.ddd;

import lombok.AllArgsConstructor;

@InfrastructureService
@AllArgsConstructor
public class DomainEventPublisher {

    private EventBus eventBus;

    public void publish(DomainEvent event) {
        eventBus.publish(event);
    }
}
