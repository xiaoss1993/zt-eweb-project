

package com.zt.eweb.framework.common.base.model;

import java.util.Date;

public interface DomainEvent {

    default int getEventVersion() {
        return eventVersion();
    }

    default Date getOccurredOn() {
        return occurredOn();
    }

    default int eventVersion() {
        return getEventVersion();
    }

    default Date occurredOn() {
        return getOccurredOn();
    }
}
