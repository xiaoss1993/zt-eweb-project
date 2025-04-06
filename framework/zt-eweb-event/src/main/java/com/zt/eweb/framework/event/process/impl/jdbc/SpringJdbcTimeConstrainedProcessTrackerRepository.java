


package com.zt.eweb.framework.event.process.impl.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import com.zt.eweb.framework.event.process.ProcessId;
import com.zt.eweb.framework.event.process.TimeConstrainedProcessTracker;
import com.zt.eweb.framework.event.process.TimeConstrainedProcessTrackerRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;


@Component
public class SpringJdbcTimeConstrainedProcessTrackerRepository
        implements TimeConstrainedProcessTrackerRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void add(TimeConstrainedProcessTracker aTimeConstrainedProcessTracker) {
        this.save(aTimeConstrainedProcessTracker);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Collection<TimeConstrainedProcessTracker> allTimedOut() {

        return jdbcTemplate.query("select * " +
                "from tbl_time_constrained_process_tracker " +
                "where completed=false" +
                "and process_informed_of_timeout = false" +
                "and timeout_occurs_on <= now()", (rs, num) -> parseResultSet(rs));
    }

    private TimeConstrainedProcessTracker parseResultSet(ResultSet rs) throws SQLException {

        TimeConstrainedProcessTracker tracker = new TimeConstrainedProcessTracker(
                rs.getString("tenantId"),
                ProcessId.existingProcessId(rs.getString("process_id_id")),
                rs.getString("description"),
                rs.getDate("timeout_occurs_on"),
                rs.getLong("allowable_duration"),
                rs.getInt("total_retries_permitted"),
                rs.getString("process_timed_out_event_type")
        );
        return tracker;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Collection<TimeConstrainedProcessTracker> allTimedOutOf(String aTenantId) {
        return jdbcTemplate.query(
                "select * from tbl_time_constrained_process_tracker as tcpt "
                        + "where tcpt.tenant_id = ?"
                        + " tcpt.completed = false and"
                        + " tcpt.process_informed_of_timeout = false and"
                        + " tcpt.timeout_occurs_on <= now()",
                (rs, num) -> parseResultSet(rs),
                aTenantId
        );
    }

    @Override
    @SuppressWarnings("unchecked")
    public Collection<TimeConstrainedProcessTracker> allTrackers(String aTenantId) {
        return jdbcTemplate.query(
                "select * from tbl_time_constrained_process_tracker where tenant_id= ?",
                (rs, rum) -> parseResultSet(rs),
                aTenantId);
    }

    @Override
    public void save(TimeConstrainedProcessTracker tracker) {
        if (tracker.timeConstrainedProcessTrackerId() == -1) {
            jdbcTemplate.update(
                    "INSERT INTO `tbl_time_constrained_process_tracker` " +
                            "(`allowable_duration`, `completed`, `description`, `process_id_id`, `process_informed_of_timeout`, `process_timed_out_event_type`, `retry_count`, `tenant_id`, `timeout_occurs_on`, `total_retries_permitted`, `concurrency_version`)\n" +
                            "VALUES\n" +
                            "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);\n",
                    tracker.allowableDuration(),
                    tracker.isCompleted(),
                    tracker.description(),
                    tracker.processId().id(),
                    tracker.isProcessInformedOfTimeout(),
                    tracker.processTimedOutEventType(),
                    tracker.retryCount(),
                    tracker.tenantId(),
                    tracker.timeoutOccursOn(),
                    tracker.totalRetriesPermitted(),
                    0
            );
        } else {
            jdbcTemplate.update(
                    "update tbl_time_constrained_process_tracker set " +
                            "allowable_duration = ?, " +
                            "completed = ?, " +
                            "description = ?, " +
                            "process_informed_of_timeout=?, " +
                            "retry_count=?, " +
                            "timeout_occurs_on = ?" +
                            "where time_constrained_process_tracker_id=?",
                    tracker.allowableDuration(),
                    tracker.isCompleted(),
                    tracker.description(),
                    tracker.isProcessInformedOfTimeout(),
                    tracker.retryCount(),
                    tracker.timeoutOccursOn(),
                    tracker.timeConstrainedProcessTrackerId()
            );
        }
    }

    @Override
    public TimeConstrainedProcessTracker trackerOfProcessId(String aTenantId, ProcessId aProcessId) {
        return jdbcTemplate.queryForObject(
                "select * from tbl_time_constrained_process_tracker where tenant_id=? and process_id=?",
                ((rs, rowNum) -> parseResultSet(rs)),
                aTenantId, aProcessId
        );
    }
}
