package com.hexaware.ftp92.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.tweak.ResultSetMapper;
import org.skife.jdbi.v2.StatementContext;

import com.hexaware.ftp92.model.LeaveDetails;
import com.hexaware.ftp92.model.LeaveStatus;
import com.hexaware.ftp92.model.LeaveType;
import com.hexaware.ftp92.model.SslAppliedStatus;

/**
 * Mapper class to map from result set to employee object.
 */
public class LeaveDetailsMapper implements ResultSetMapper<LeaveDetails> {
  /**
   * @param idx the index
   * @param rs the resultset
   * @param ctx the context
   * @return the mapped employee object
   * @throws SQLException in case there is an error in fetching data from the resultset
   */
  public final LeaveDetails map(final int idx, final ResultSet rs, final     StatementContext ctx) throws SQLException {
    String leaveType = rs.getString("LEAVE_TYPE");
    LeaveType leavet = LeaveType.valueOf(leaveType);

    String leaveStatus = rs.getString("LEAVE_STATUS");
    LeaveStatus leaves = LeaveStatus.valueOf(leaveStatus);

    String sslststus = rs.getString("SSL_APPLY_STATUS");
    SslAppliedStatus sslstat = SslAppliedStatus.valueOf(sslststus);

    /**
     * @return LeaveDetails
     */
    return new LeaveDetails(rs.getInt("LEAVE_ID"),
      rs.getInt("EMP_ID"),
      rs.getInt("LEAVE_NO_OF_DAYS"),
      rs.getDate("LEAVE_START_DATE"),
      rs.getDate("LEAVE_END_DATE"),
      leavet, leaves,
      rs.getString("LEAVE_REASON"),
      rs.getDate("LEAVE_APPLIED_ON"),
      rs.getString("LEAVE_MANAGER_COMMENT"),
      sslstat);
  }
}
