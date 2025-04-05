package com.zt.eweb.framework.event.store;

import com.zt.eweb.framework.common.base.model.DomainEvent;

import java.util.List;

public interface EventStore {

    public List<StoredEvent> allStoredEventsBetween(long lowStoredEventId, long highStoredEventId);

    public List<StoredEvent> allStoredEventsSince(long storedEventId);

    public StoredEvent append(DomainEvent domainEvent);

    public void close();

    public long countStoredEvents();
}
