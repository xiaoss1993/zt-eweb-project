

package com.zt.eweb.framework.event.stream;

import com.zt.eweb.framework.common.base.model.DomainEvent;

import java.util.List;


public interface EventStream {

    public List<DomainEvent> events();

    public int version();
}
