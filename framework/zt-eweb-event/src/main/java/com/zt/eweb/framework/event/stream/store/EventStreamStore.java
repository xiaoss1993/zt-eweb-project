

package com.zt.eweb.framework.event.stream.store;

import com.zt.eweb.framework.event.stream.DispatchableDomainEvent;
import com.zt.eweb.framework.event.stream.EventNotifiable;
import com.zt.eweb.framework.event.stream.EventStream;
import com.zt.eweb.framework.event.stream.EventStreamId;
import com.zt.eweb.framework.common.base.model.DomainEvent;

import java.util.List;


public interface EventStreamStore {

    public void appendWith(EventStreamId aStartingIdentity, List<DomainEvent> anEvents);

    public void close();

    public List<DispatchableDomainEvent> eventsSince(long aLastReceivedEvent);

    public EventStream eventStreamSince(EventStreamId anIdentity);

    public EventStream fullEventStreamFor(EventStreamId anIdentity);

    public void purge(); // mainly used for testing

    public void registerEventNotifiable(EventNotifiable anEventNotifiable);
}
