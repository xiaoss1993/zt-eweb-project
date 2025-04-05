package com.zt.eweb.framework.event.store.listener;

import com.zt.eweb.framework.common.base.model.DomainEvent;
import com.zt.eweb.framework.common.registry.DomainRegistry;
import com.zt.eweb.framework.common.utils.json.Json;
import com.zt.eweb.framework.event.store.EventStore;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;

@Slf4j
public class DomainEventStoreListener {

    @Autowired
    @Setter
    private EventStore eventStore;

    @EventListener
    public void storeEvents(DomainEvent domainEvent) {
        log.info("store event: {} = {}", domainEvent.getClass().getSimpleName(), DomainRegistry.service(Json.class).toJson(domainEvent));
        eventStore.append(domainEvent);
    }
}
