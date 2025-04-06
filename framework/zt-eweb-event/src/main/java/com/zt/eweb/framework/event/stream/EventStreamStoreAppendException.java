

package com.zt.eweb.framework.event.stream;

public class EventStreamStoreAppendException extends EventStreamStoreException {

    private static final long serialVersionUID = 1;

    public EventStreamStoreAppendException(String aMessage, Throwable aCause) {
        super(aMessage, aCause);
    }

    public EventStreamStoreAppendException(String aMessage) {
        super(aMessage);
    }
}
