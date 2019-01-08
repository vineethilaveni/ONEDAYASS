package com.hexaware.ftp92.persistence;

//import com.hexaware.ftp92.model.Employee;
import com.hexaware.ftp92.model.LeaveDetails;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.util.List;
/**
 * The DAO class for LeaveDetails.
 */
public interface LeaveDetailsDAO  {

 /**
   * return all the details of the selected employee.
   * @param empID the id of the employee
   * @return the employee object
   */
  @SqlQuery(" select * from LEAVE_DETAILS where "
        + " EMP_ID in "
        + " (SELECT E2.EMP_ID FROM "
        + " EMPLOYEE E1 INNER JOIN "
        + " EMPLOYEE E2 ON E1.EMP_ID = E2.EMP_MANAGER_ID "
        + " where e2.Emp_manager_id = :empID) and "
        + " leave_status ='pending'")
  @Mapper(LeaveDetailsMapper.class)
  List<LeaveDetails> pending(@Bind("empID") int empID);

 /**
   * return all the details of the selected employee.
   * @param empID the id of the employee
   * @return the employee object
   */
  @SqlQuery("SELECT * FROM LEAVE_DETAILS WHERE EMP_ID = :empID")
  @Mapper(LeaveDetailsMapper.class)
  List<LeaveDetails> empHistory(@Bind("empID") int empID);

   /**
   /**
     * insert all the details of the employee leave.
     * @param empId       the employee id of the employee.
     * @param sDate       the start date of the employee.
     * @param eDate       the end date of the employee.
     * @param days        the number of days of the employee.
     * @param ltype       the leaveType details of employee.
     * @param reason      the reason for applying.
     * @param appliedDate the applied date of the employee.
     */
  @SqlUpdate("insert into LEAVE_DETAILS  " + "  (EMP_ID, "
            + "  LEAVE_NO_OF_DAYS, " + "   LEAVE_START_DATE, " + " LEAVE_END_DATE, "
            + "  LEAVE_TYPE, " + "LEAVE_REASON, "
            + "  LEAVE_APPLIED_ON) " + "values ( " + " :empId, "
            + "  :nodays, " + " :startDate, " + " :endDate, "
            + "  :leavetype, " + ":leavereason, "
            + "  :appliedOn)")

  // void insert(@Bind("empId") int empId, @Bind("nodays") int days,
  //           @Bind("startDate") String sDate, @Bind("endDate") String eDate, @Bind("leavetype") String ltype,
  //           @Bind("leavereason") String reason, @Bind("appliedOn") String appliedDate);
  void insert(@Bind("empId") int empId, @Bind("nodays") int days,
            @Bind("startDate") String sDate, @Bind("endDate") String eDate, @Bind("leavetype") String ltype,
            @Bind("leavereason") String reason, @Bind("appliedOn") String appliedDate);



   /**
  /**
     * insert all the details of the employee leave.
     * @param empId       the employee id of the employee.
     */
  @SqlUpdate("UPDATE LEAVE_DETAILS SET LEAVE_STATUS = 'APPROVED' WHERE EMP_ID = :empId")
  void updateCEO(@Bind("empId") int empId);

   /**
   * return all the details of the selected employee.
   * @param empID     the id of the employee.
   * @param startDate leaveStartDate of employee.
   * @param endDate   leaveEndDate of employee.
   * @return the total record count.
   */
  @SqlQuery("SELECT COUNT(*) FROM LEAVE_DETAILS " + " WHERE EMP_ID = :empID"
      + " AND (LEAVE_START_DATE BETWEEN :startDate AND :endDate "
      + " OR LEAVE_END_DATE BETWEEN :startDate AND :endDate )")
  int count(@Bind("empID") int empID, @Bind("startDate") String startDate, @Bind("endDate") String endDate);
  /**
   * close with no args is used to close the connection.
   * @param managercomments Updated Manager Response data.
   * @param leavestatus     Updated Manager Status data.
   * @param leaveId         the id of the LeaveDetails.
   */
  @SqlUpdate("UPDATE LEAVE_DETAILS SET LEAVE_MANAGER_COMMENT = :managercomments, LEAVE_STATUS = :leavestatus WHERE "
             + "LEAVE_ID= :leaveId")
  void approved(@Bind("managercomments") String managercomments, @Bind("leavestatus") String leavestatus,
      @Bind("leaveId") int leaveId);
  /**
    * @param managercomments Updated Manager Response data.
    * @param leavestatus Updated Manager Status data.
    * @param leaveId the id of the LeaveDetails.
    */
  @SqlUpdate("UPDATE LEAVE_DETAILS SET LEAVE_MANAGER_COMMENT = :managercomments, LEAVE_STATUS = :leavestatus WHERE "
             + "LEAVE_ID= :leaveId")
  void denied(@Bind("managercomments") String managercomments, @Bind("leavestatus") String leavestatus,
      @Bind("leaveId") int leaveId);
  /**
    * @param managercomments Updated Manager Response data.
    * @param leavestatus Updated Manager Status data.
    * @param leaveId the id of the LeaveDetails.
    */
  /**
    * return all the details of the selected employee.
    * @param leaveId the id of the employee
    * @return the leavedetails object
    */
  @SqlQuery("SELECT * FROM LEAVE_DETAILS WHERE LEAVE_ID = :leaveId")
  @Mapper(LeaveDetailsMapper.class)
  LeaveDetails findLeave(@Bind("leaveId") int leaveId);
  /**
   * return all the details of the selected employee.
   * @param empID the id of the employee
   * @return the leavedetails object
   */
  @SqlQuery("SELECT * FROM LEAVE_DETAILS WHERE EMP_ID = :empID and leave_status ='pending'")
  @Mapper(LeaveDetailsMapper.class)
  List<LeaveDetails> allrecordsofid(@Bind("empID") int empID);
  /**
   * @param leaveReason jhgjg
   * @param leaveId mhgvbjhvjh
   * @param leaveType mjhju
   * @param endDate mjgh
   */
  @SqlUpdate("UPDATE LEAVE_DETAILS SET LEAVE_TYPE = :leaveType, LEAVE_REASON = :leaveReason WHERE LEAVE_ID = :leaveId")
    void updater(@Bind("endDate") String endDate, @Bind("leaveType") String leaveType,
      @Bind("leaveReason") String leaveReason, @Bind("leaveId") int leaveId);
  /**
   * @param endD mjgh
   * @param startDat ukh
   * @param leaveId mjg
   */
  @SqlUpdate("UPDATE LEAVE_DETAILS SET LEAVE_START_DATE = :startDat, LEAVE_END_DATE = :endD WHERE LEAVE_ID = :leaveId")
    void updatea(@Bind("endD") String endD, @Bind("startDat") String startDat, @Bind("leaveId") int leaveId);
  /**
   * gju return Manager the details of the Leave Id.
   * @param leaveID the id of the employee.
   * @return the ManagerID value.
   */
  @SqlQuery("SELECT E1.EMP_ID FROM EMPLOYEE E1 "
      + " JOIN EMPLOYEE E2 ON E1.Emp_ID = E2.EMP_MANAGER_ID WHERE E2.EMP_ID =(SELECT EMP_ID FROM LEAVE_DETAILS "
      + " WHERE LEAVE_ID=:leaveID)")
  int getManager(@Bind("leaveID") int leaveID);

  /**
   * @param empId   employee Id.
   * @param leaveId leaveId.
   */
  @SqlUpdate("DELETE FROM LEAVE_DETAILS WHERE LEAVE_ID = :leaveId")
  void deleteLeave(@Bind("empId") int empId, @Bind("leaveId") int leaveId);
  /**
   * close with no args is used to close the connection.
   */
  void close();
}
