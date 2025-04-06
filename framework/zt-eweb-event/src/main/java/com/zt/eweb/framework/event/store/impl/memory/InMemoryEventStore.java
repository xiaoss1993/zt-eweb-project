

package com.zt.eweb.framework.event.store.impl.memory;


import com.zt.eweb.framework.event.store.EventStore;
import com.zt.eweb.framework.event.store.StoredEvent;
import com.zt.eweb.framework.common.utils.json.Json;
import com.zt.eweb.framework.common.base.model.DomainEvent;

import java.util.ArrayList;
import java.util.List;


public class InMemoryEventStore implements EventStore {

    private final Json json;

    private List<StoredEvent> storedEvents;

    public InMemoryEventStore(Json json) {
        super();
        this.json = json;

        this.storedEvents = new ArrayList<StoredEvent>();
    }

    @Override
    public List<StoredEvent> allStoredEventsBetween(
            long aLowStoredEventId,
            long aHighStoredEventId) {
        List<StoredEvent> events = new ArrayList<StoredEvent>();

        for (StoredEvent storedEvent : this.storedEvents) {
            if (storedEvent.eventId() >= aLowStoredEventId && storedEvent.eventId() <= aHighStoredEventId) {
                events.add(storedEvent);
            }
        }

        return events;
    }

    @Override
    public List<StoredEvent> allStoredEventsSince(long aStoredEventId) {
        List<StoredEvent> events = new ArrayList<StoredEvent>();

        for (StoredEvent storedEvent : this.storedEvents) {
            if (storedEvent.eventId() > aStoredEventId) {
                events.add(storedEvent);
            }
        }

        return events;
    }

    @Override
    public synchronized StoredEvent append(DomainEvent aDomainEvent) {
        String eventSerialization =
                json.toJson(aDomainEvent);

        StoredEvent storedEvent =
                new StoredEvent(
                        aDomainEvent.getClass().getName(),
                        aDomainEvent.getOccurredOn(),
                        eventSerialization,
                        this.storedEvents.size() + 1);

        this.storedEvents.add(storedEvent);

        return storedEvent;
    }

    @Override
    public void close() {
        this.clean();
    }

    @Override
    public long countStoredEvents() {
        return this.storedEvents.size();
    }

    public void clean() {
        this.storedEvents = new ArrayList<StoredEvent>();
    }
}
