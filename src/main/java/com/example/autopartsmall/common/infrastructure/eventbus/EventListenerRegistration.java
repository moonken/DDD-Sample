package com.example.autopartsmall.common.infrastructure.eventbus;

import com.example.autopartsmall.common.ddd.DomainEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class EventListenerRegistration {

    private Object bean;

    private Method method;

    private Class type;

    public void invoke(DomainEvent event) {
        try {
            method.invoke(bean, event);
        } catch (InvocationTargetException ex) {
            log.error("Command execute error when invoke bean:{} method:{}",
                    bean.toString(), method.getName(), ex);
        }
        catch (Exception exception) {
            log.error("Command execute error when invoke bean:{} method:{} event:{}",
                    bean.toString(), method.getName(), exception);
        }
    }
}
