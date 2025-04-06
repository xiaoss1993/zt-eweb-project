

package com.zt.eweb.framework.event.stream.dispatch;

import com.zt.eweb.framework.event.stream.DispatchableDomainEvent;


public interface EventStreamDispatcher {

    public void dispatch(DispatchableDomainEvent aDispatchableDomainEvent);

    public void registerEventDispatcher(EventStreamDispatcher anEventDispatcher);

    public boolean understands(DispatchableDomainEvent aDispatchableDomainEvent);
}
