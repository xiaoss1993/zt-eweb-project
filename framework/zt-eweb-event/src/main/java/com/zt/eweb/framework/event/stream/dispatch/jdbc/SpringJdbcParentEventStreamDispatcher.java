

package com.zt.eweb.framework.event.stream.dispatch.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import com.zt.eweb.framework.event.publish.DomainEventPublisher;
import com.zt.eweb.framework.event.stream.DispatchableDomainEvent;
import com.zt.eweb.framework.event.stream.EventNotifiable;
import com.zt.eweb.framework.event.stream.dispatch.EventStreamDispatcher;
import com.zt.eweb.framework.event.stream.dispatch.sql.ConnectionProvider;
import com.zt.eweb.framework.event.stream.store.EventStreamStore;

import java.util.ArrayList;
import java.util.List;


//@Component("parentEventStreamDispatcher")
public class SpringJdbcParentEventStreamDispatcher implements EventStreamDispatcher, EventNotifiable {

    private JdbcTemplate jdbcTemplate;
    private long lastDispatchedEventId;
    private List<EventStreamDispatcher> registeredDispatchers;
    private EventStreamStore eventStreamStore;

    @Autowired
    public SpringJdbcParentEventStreamDispatcher(JdbcTemplate jdbcTemplate, EventStreamStore eventStreamStore) {
        super();

        this.jdbcTemplate = jdbcTemplate;
        this.setRegisteredDispatchers(new ArrayList<EventStreamDispatcher>());

        this.eventStreamStore = eventStreamStore;
        eventStreamStore.registerEventNotifiable(this);

        this.setLastDispatchedEventId(this.queryLastDispatchedEventId());

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

        try {
            List<DispatchableDomainEvent> undispatchedEvents =
                    eventStreamStore
                            .eventsSince(this.lastDispatchedEventId());

            if (!undispatchedEvents.isEmpty()) {

                for (DispatchableDomainEvent event : undispatchedEvents) {
                    this.dispatch(event);
                }

                DispatchableDomainEvent withLastEventId =
                        undispatchedEvents.get(undispatchedEvents.size() - 1);

                long lastDispatchedEventId = withLastEventId.eventId();

                this.setLastDispatchedEventId(lastDispatchedEventId);

                this.saveLastDispatchedEventId(lastDispatchedEventId);
            }

//            connection.commit();

        } catch (Throwable t) {
            throw new IllegalStateException("Cannot dispatch events because: " + t.getMessage(), t);
        } finally {
            ConnectionProvider.closeConnection();
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

        long lastHandledEventId = 0;

        Long maxEventId = jdbcTemplate.queryForObject("select max(event_id) from tbl_dispatcher_last_event", Long.class);
        if (maxEventId != null) {
            lastHandledEventId = maxEventId;
        } else {
            this.saveLastDispatchedEventId(0);
        }
        return lastHandledEventId;
    }

    private void saveLastDispatchedEventId(long aLastDispatchedEventId) {

        int updated = jdbcTemplate.update("update tbl_dispatcher_last_event set event_id=?", aLastDispatchedEventId);

        if (updated == 0) {
            jdbcTemplate.update("insert into tbl_dispatcher_last_event values(?)", aLastDispatchedEventId);
        }
    }

    private List<EventStreamDispatcher> registeredDispatchers() {
        return this.registeredDispatchers;
    }

    private void setRegisteredDispatchers(List<EventStreamDispatcher> aDispatchers) {
        this.registeredDispatchers = aDispatchers;
    }
}
