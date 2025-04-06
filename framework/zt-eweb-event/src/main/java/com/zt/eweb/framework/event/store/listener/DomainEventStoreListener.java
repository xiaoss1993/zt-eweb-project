package com.zt.eweb.framework.event.store.listener;

import com.zt.eweb.framework.common.registry.DomainRegistry;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import com.zt.eweb.framework.event.store.EventStore;
import com.zt.eweb.framework.common.utils.json.Json;

import com.zt.eweb.framework.common.base.model.DomainEvent;


/**
 * Created by zhacker.
 * Time 2018/6/24 上午10:11
 */
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
