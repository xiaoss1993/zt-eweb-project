

package com.zt.eweb.framework.event.process;


import java.util.UUID;


public final class ProcessId {

    private static final long serialVersionUID = 1L;

    String id;

    public static ProcessId existingProcessId(String anId) {
        ProcessId processId = new ProcessId(anId);

        return processId;
    }

    public static ProcessId newProcessId() {
        ProcessId processId =
                new ProcessId(UUID.randomUUID().toString().toLowerCase());

        return processId;
    }

    protected ProcessId(String anId) {
        this.id = anId;
    }

    protected ProcessId() {
        super();
    }

    public String id() {
        return this.id;
    }

//    @Override
//    protected int hashOddValue() {
//        return 3773;
//    }
//
//    @Override
//    protected int hashPrimeValue() {
//        return 43;
//    }
}
