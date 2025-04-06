

package com.zt.eweb.framework.event.stream;

public class EventStreamStoreVersionException extends EventStreamStoreException {

    private static final long serialVersionUID = 1L;

    public EventStreamStoreVersionException(String aMessage) {
        super(aMessage);
    }

    public EventStreamStoreVersionException(String aMessage, Throwable aCause) {
        super(aMessage, aCause);
    }
}
