package com.zt.eweb.framework.event.store.impl.mybatis;

import org.apache.ibatis.annotations.*;
import com.zt.eweb.framework.event.store.StoredEvent;

import java.util.List;


/**
 * Created by zhacker.
 * Time 2018/7/28 下午4:53
 */
@Mapper
public interface EventStoreMapper {

  @Select("select * from tbl_stored_event " +
          "where event_id between #{lowStoredEventId} and #{highStoredEventId} " +
          "order by event_id")
  List<StoredEvent> allStoredEventsBetween(@Param("lowStoredEventId") long lowStoredEventId, @Param("highStoredEventId") long highStoredEventId);

  @Select("select * from tbl_stored_event where event_id > #{storedEventId} order by event_id")
  List<StoredEvent> allStoredEventsSince(@Param("storedEventId") long storedEventId);

  @Options(useGeneratedKeys = true, keyProperty = "eventId")
  @Insert("insert into tbl_stored_event" +
          "(type_name, occurred_on, event_body) " +
          "values (#{typeName}, #{occurredOn}, #{eventBody})")
  void create(StoredEvent event);

  @Select("select count(*) from tbl_stored_event")
  long countStoredEvents();
}
