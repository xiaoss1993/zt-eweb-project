

package com.zt.eweb.framework.event.stream;

public class EventStreamStoreException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public EventStreamStoreException(String aMessage) {
        super(aMessage);
    }

    public EventStreamStoreException(String aMessage, Throwable aCause) {
        super(aMessage, aCause);
    }
}
