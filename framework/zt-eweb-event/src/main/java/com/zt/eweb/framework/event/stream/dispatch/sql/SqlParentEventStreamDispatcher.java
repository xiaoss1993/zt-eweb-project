

package com.zt.eweb.framework.event.stream.dispatch.sql;

import org.springframework.beans.factory.annotation.Autowired;
import com.zt.eweb.framework.event.publish.DomainEventPublisher;
import com.zt.eweb.framework.event.stream.DispatchableDomainEvent;
import com.zt.eweb.framework.event.stream.EventNotifiable;
import com.zt.eweb.framework.event.stream.dispatch.EventStreamDispatcher;
import com.zt.eweb.framework.event.stream.store.EventStreamStore;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


//@Component("parentEventStreamDispatcher")
public class SqlParentEventStreamDispatcher implements EventStreamDispatcher, EventNotifiable {

    private DataSource dataSource;
    private long lastDispatchedEventId;
    private List<EventStreamDispatcher> registeredDispatchers;
    private EventStreamStore eventStreamStore;

    @Autowired
    public SqlParentEventStreamDispatcher(DataSource aDataSource, EventStreamStore eventStreamStore) {
        super();

        this.setDataSource(aDataSource);
        this.setRegisteredDispatchers(new ArrayList<EventStreamDispatcher>());

        this.eventStreamStore = eventStreamStore;
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

        // this could be multi-threaded from here,
        // but is not for simplicity

        // child EventDispatchers should use only
        // ConnectionProvider.connection() and
        // not commit. i will commit and close the
        // connection here

        Connection connection =
                ConnectionProvider
                        .connection(this.collaborationDataSource());

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

                this.saveLastDispatchedEventId(connection, lastDispatchedEventId);
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

    private void close(Statement aStatement, ResultSet aResultSet) {
        this.closeStatement(aStatement);

        this.closeResultSet(aResultSet);

        ConnectionProvider.closeConnection();
    }

    private void closeResultSet(ResultSet aResultSet) {
        if (aResultSet != null) {
            try {
                aResultSet.close();
            } catch (Exception e) {
                // ignore
            }
        }
    }

    private void closeStatement(Statement aStatement) {
        if (aStatement != null) {
            try {
                aStatement.close();
            } catch (Exception e) {
                // ignore
            }
        }
    }

    private DataSource collaborationDataSource() {
        return this.dataSource;
    }

    private void setDataSource(DataSource aDataSource) {
        this.dataSource = aDataSource;
    }

    private Connection connection() {
        Connection connection = null;

        try {
            connection =
                    ConnectionProvider
                            .connection(this.collaborationDataSource());
        } catch (Throwable t) {
            throw new IllegalStateException(
                    "Cannot acquire database connection because: "
                            + t.getMessage(),
                    t);
        }

        return connection;
    }

    private long lastDispatchedEventId() {
        return this.lastDispatchedEventId;
    }

    private void setLastDispatchedEventId(long aLastDispatchedEventId) {
        this.lastDispatchedEventId = aLastDispatchedEventId;
    }

    private long queryLastDispatchedEventId() {

        long lastHandledEventId = 0;

        Connection connection = this.connection();
        ResultSet result = null;
        PreparedStatement statement = null;

        try {
            statement =
                    connection.prepareStatement(
                            "select max(event_id) from tbl_dispatcher_last_event");

            result = statement.executeQuery();

            if (result.next()) {
                lastHandledEventId = result.getLong(1);
            } else {
                this.saveLastDispatchedEventId(connection, 0);
            }

//            connection.commit();

        } catch (Exception e) {
            throw new IllegalStateException(
                    "Cannot query last dispatched event because: "
                            + e.getMessage(),
                    e);
        } finally {
            this.close(statement, result);
        }

        return lastHandledEventId;
    }

    private void saveLastDispatchedEventId(
            Connection aConnection,
            long aLastDispatchedEventId)
            throws Exception {

        int updated = 0;

        PreparedStatement statement = null;

        try {
            statement = aConnection.prepareStatement(
                    "update tbl_dispatcher_last_event set event_id=?");
            statement.setLong(1, aLastDispatchedEventId);
            updated = statement.executeUpdate();

        } catch (Exception e) {
            throw new IllegalStateException("Cannot update dispatcher last event.");
        } finally {
            this.closeStatement(statement);
        }

        if (updated == 0) {

            try {
                statement = aConnection.prepareStatement(
                        "insert into tbl_dispatcher_last_event values(?)");
                statement.setLong(1, aLastDispatchedEventId);
                statement.executeUpdate();

            } catch (Exception e) {
                throw new IllegalStateException("Cannot insert dispatcher last event.");
            } finally {
                this.closeStatement(statement);
            }
        }
    }

    private List<EventStreamDispatcher> registeredDispatchers() {
        return this.registeredDispatchers;
    }

    private void setRegisteredDispatchers(List<EventStreamDispatcher> aDispatchers) {
        this.registeredDispatchers = aDispatchers;
    }
}
