

package com.zt.eweb.framework.event.stream.dispatch.mybatis;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import com.zt.eweb.framework.event.publish.DomainEventPublisher;
import com.zt.eweb.framework.event.stream.DispatchableDomainEvent;
import com.zt.eweb.framework.event.stream.EventNotifiable;
import com.zt.eweb.framework.event.stream.dispatch.EventStreamDispatcher;
import com.zt.eweb.framework.event.stream.store.EventStreamStore;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


//@Component("parentEventStreamDispatcher")
public class MybatisParentEventStreamDispatcher implements EventStreamDispatcher, EventNotifiable {

    private long lastDispatchedEventId;
    private List<EventStreamDispatcher> registeredDispatchers = new ArrayList<>();

    @Setter
    @Autowired
    private EventStreamStore eventStreamStore;

    @Autowired
    @Setter
    private DispatcherLastEventMapper dispatcherLastEventMapper;

    @PostConstruct
    public void init() {

        eventStreamStore.registerEventNotifiable(this);

        this.setLastDispatchedEventId(this.queryLastDispatchedEventId());

//        this.notifyDispatchableEvents();
    }

    @Override
    public void dispatch(DispatchableDomainEvent aDispatchableDomainEvent) {
        DomainEventPublisher.publish(aDispatchableDomainEvent.domainEvent());

        for (EventStreamDispatcher eventDispatcher : this.registeredDispatchers()) {
            eventDispatcher.dispatch(aDispatchableDomainEvent);
        }
    }

    @Override
    public void notifyDispatchableEvents() {

        List<DispatchableDomainEvent> undispatchedEvents = eventStreamStore.eventsSince(this.lastDispatchedEventId());

        if (!undispatchedEvents.isEmpty()) {

            for (DispatchableDomainEvent event : undispatchedEvents) {
                this.dispatch(event);
            }

            DispatchableDomainEvent withLastEventId = undispatchedEvents.get(undispatchedEvents.size() - 1);

            long lastDispatchedEventId = withLastEventId.eventId();

            this.setLastDispatchedEventId(lastDispatchedEventId);

            this.saveLastDispatchedEventId(lastDispatchedEventId);
        }
    }

    @Override
    public void registerEventDispatcher(EventStreamDispatcher anEventDispatcher) {
        this.registeredDispatchers().add(anEventDispatcher);
    }

    @Override
    public boolean understands(DispatchableDomainEvent aDispatchableDomainEvent) {
        return true;
    }

    private long lastDispatchedEventId() {
        return this.lastDispatchedEventId;
    }

    private void setLastDispatchedEventId(long aLastDispatchedEventId) {
        this.lastDispatchedEventId = aLastDispatchedEventId;
    }

    private long queryLastDispatchedEventId() {
        return Optional.ofNullable(dispatcherLastEventMapper.findLastEventId()).orElse(0L);
    }

    private void saveLastDispatchedEventId(long aLastDispatchedEventId) {

        int updated = dispatcherLastEventMapper.update(new DispatcherLastEvent().setEventId(aLastDispatchedEventId));
        if (updated == 0) {
            dispatcherLastEventMapper.insert(new DispatcherLastEvent().setEventId(aLastDispatchedEventId));
        }
    }

    private List<EventStreamDispatcher> registeredDispatchers() {
        return this.registeredDispatchers;
    }

    private void setRegisteredDispatchers(List<EventStreamDispatcher> aDispatchers) {
        this.registeredDispatchers = aDispatchers;
    }
}
