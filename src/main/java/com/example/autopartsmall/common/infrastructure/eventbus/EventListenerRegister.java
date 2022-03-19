package com.example.autopartsmall.common.infrastructure.eventbus;

import com.example.autopartsmall.common.ddd.DomainEvent;
import com.example.autopartsmall.common.ddd.DomainEventListener;
import com.example.autopartsmall.common.ddd.OnDomainEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.support.AopUtils;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.MethodIntrospector;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;

@Slf4j
@Component
public class EventListenerRegister implements ApplicationListener<ContextRefreshedEvent> {

    private LinkedMultiValueMap<Class, EventListenerRegistration> eventListeners = new LinkedMultiValueMap();

    private void validateMethodDefine(Class<?> targetType, Map.Entry<Method, OnDomainEvent> method) {
        if (method.getKey().getParameters().length != 1) {
            log.error("{} - {} has wrong parameters size for event listen.", targetType, method.getKey().getName());
            System.exit(1);
        }

        if (!DomainEvent.class.isAssignableFrom(method.getKey().getParameterTypes()[0])) {
            log.error("{} - {} has wrong parameters type for event listen.", targetType, method.getKey().getName());
            System.exit(1);
        }
    }

    private void registerEventHandler(EventListenerRegistration metadata) {
        eventListeners.add(metadata.getType(), metadata);
    }

    public Collection<EventListenerRegistration> findEventListenerMetadata(DomainEvent event) {
        return eventListeners.get(event.getClass());
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        // 根容器为Spring容器
        if (event.getApplicationContext().getParent() == null) {
            Map<String, Object> beans = event.getApplicationContext().getBeansWithAnnotation(DomainEventListener.class);
            for (Object bean : beans.values()) {
                Map<Method, OnDomainEvent> annotatedMethods = null;
                Class<?> targetType = bean.getClass();
                try {
                    annotatedMethods = MethodIntrospector.selectMethods(targetType,
                            (MethodIntrospector.MetadataLookup<OnDomainEvent>) method ->
                                    AnnotatedElementUtils.findMergedAnnotation(method, OnDomainEvent.class));
                } catch (Throwable ex) {
                    // An unresolvable type in a method signature, probably from a lazy bean - let's ignore it.
                    if (log.isDebugEnabled()) {
                        log.debug("Could not resolve methods for bean with name '" + bean.getClass().getName() + "'", ex);
                    }
                }

                if (CollectionUtils.isEmpty(annotatedMethods)) {
                    if (log.isTraceEnabled()) {
                        log.trace("No @OnDomainEvent annotations found on bean class: " + targetType.getName());
                    }
                } else {
                    // Non-empty set of methods
                    for (Map.Entry<Method, OnDomainEvent> method : annotatedMethods.entrySet()) {
                        validateMethodDefine(targetType, method);
                        Method methodToUse = AopUtils.selectInvocableMethod(method.getKey(), targetType);
                        registerEventHandler(new EventListenerRegistration(bean, methodToUse, methodToUse.getParameterTypes()[0]));
                    }
                }
            }
        }
    }
}
