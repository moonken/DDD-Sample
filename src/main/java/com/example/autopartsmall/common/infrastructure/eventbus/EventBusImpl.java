package com.example.autopartsmall.common.infrastructure.eventbus;

import com.example.autopartsmall.common.ddd.DomainEvent;
import com.example.autopartsmall.common.ddd.EventBus;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EventBusImpl implements EventBus {

    private ApplicationEventPublisher publisher;

    private EventListenerRegister eventListenerRegister;

    @Async
    @Override
    public void onEvent(DomainEvent event) {
        eventListenerRegister.findEventListenerMetadata(event).forEach(eventListener -> {
                    eventListener.invoke(event);
                });
    }

    @Override
    public void publish(DomainEvent event) {
        publisher.publishEvent(event);
    };
}
