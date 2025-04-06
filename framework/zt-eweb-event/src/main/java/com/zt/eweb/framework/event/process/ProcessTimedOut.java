

package com.zt.eweb.framework.event.process;


import com.zt.eweb.framework.common.base.model.DomainEvent;

import java.util.Date;


public class ProcessTimedOut implements DomainEvent {

    private int eventVersion;
    private Date occurredOn;
    private ProcessId processId;
    private int retryCount;
    private String tenantId;
    private int totalRetriesPermitted;

    public ProcessTimedOut(
            String aTenantId,
            ProcessId aProcessId,
            int aTotalRetriesPermitted,
            int aRetryCount) {
        super();

        this.eventVersion = 1;
        this.occurredOn = new Date();
        this.processId = aProcessId;
        this.retryCount = aRetryCount;
        this.tenantId = aTenantId;
        this.totalRetriesPermitted = aTotalRetriesPermitted;
    }

    public boolean allowsRetries() {
        return this.totalRetriesPermitted() > 0;
    }

    @Override
    public int getEventVersion() {
        return this.eventVersion;
    }

    public boolean hasFullyTimedOut() {
        return !this.allowsRetries() || this.totalRetriesReached();
    }

    @Override
    public Date getOccurredOn() {
        return this.occurredOn;
    }

    public ProcessId processId() {
        return processId;
    }

    public int retryCount() {
        return retryCount;
    }

    public String tenantId() {
        return tenantId;
    }

    public int totalRetriesPermitted() {
        return totalRetriesPermitted;
    }

    public boolean totalRetriesReached() {
        return this.retryCount() >= this.totalRetriesPermitted();
    }
}
