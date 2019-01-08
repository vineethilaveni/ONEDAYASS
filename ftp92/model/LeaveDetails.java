package com.hexaware.ftp92.model;

import com.hexaware.ftp92.persistence.DbConnection;
import com.hexaware.ftp92.persistence.EmployeeDAO;
import com.hexaware.ftp92.persistence.LeaveDetailsDAO;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * LeaveDetails class to store employe e leave details.
 * @author hexware
 */
public class LeaveDetails {

  /**
   * leaveId to store leaveId. empId to store employee's id. leaveNoOfDays to
   * store leave no of days. leaveStartDate to store leave start date.
   * leaveEndDate to store leave end date. leaveType to store type of leave.
   * leaveStatus to store leave status. leaveReason to store leave Reason.
   * leaveAppliedOn to store applied date. leaveManagerComment to store manager
   * comment's.
   */
  private int leaveId;
  private int empId;
  private int leaveNoOfDays;
  private Date leaveStartDate;
  private Date leaveEndDate;
  private LeaveType leaveType;
  private LeaveStatus leaveStatus;
  private String leaveReason;
  private Date leaveAppliedOn;
  private String leaveManagerComment;
  private SslAppliedStatus sslAppliedStatus;

  /**
   * @param argEmpId               to initialize employee id.
   * @param argLeaveId             to initialize leave id.
   * @param argLeaveNoOfDays       to initialize leave no of days.
   * @param argLeaveStartDate      to initialize leave start date.
   * @param argLeaveEndDate        to initialize leave end date.
   * @param argLeaveStatus         to initialize leave status.
   * @param argLeaveType           to initialize leave type.
   * @param argLeaveReason         to initialize leave reason.
   * @param argLeaveAppliedOn      to initialize leave applied date.
   * @param argLeaveManagerComment to initialize leave manager comment.
   * @param argSslAppliedStatus    to intilazie.
   */
  public LeaveDetails(final int argLeaveId, final int argEmpId, final int argLeaveNoOfDays,
      final Date argLeaveStartDate, final Date argLeaveEndDate, final LeaveType argLeaveType,
      final LeaveStatus argLeaveStatus, final String argLeaveReason, final Date argLeaveAppliedOn,
      final String argLeaveManagerComment, final SslAppliedStatus argSslAppliedStatus) {
    this.empId = argEmpId;
    this.leaveId = argLeaveId;
    this.sslAppliedStatus = argSslAppliedStatus;
    this.leaveNoOfDays = argLeaveNoOfDays;
    this.leaveStartDate = argLeaveStartDate;
    this.leaveEndDate = argLeaveEndDate;
    this.leaveType = argLeaveType;
    this.leaveStatus = argLeaveStatus;
    this.leaveReason = argLeaveReason;
    this.leaveAppliedOn = argLeaveAppliedOn;
    this.leaveManagerComment = argLeaveManagerComment;
  }

  /**
   */
  public LeaveDetails() {
  }

  @Override
  public final boolean equals(final Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    LeaveDetails ld = (LeaveDetails) obj;
    if (Objects.equals(empId, ld.empId) && Objects.equals(leaveId, ld.leaveId)
        && Objects.equals(leaveNoOfDays, ld.leaveNoOfDays) && Objects.equals(leaveStartDate, ld.leaveNoOfDays)
        && Objects.equals(leaveStartDate, ld.leaveStartDate) && Objects.equals(leaveEndDate, ld.leaveEndDate)
        && Objects.equals(leaveType, ld.leaveType) && Objects.equals(leaveStatus, ld.leaveStatus)
        && Objects.equals(leaveReason, ld.leaveReason) && Objects.equals(leaveAppliedOn, ld.leaveAppliedOn)
        && Objects.equals(leaveManagerComment, ld.leaveManagerComment)) {
      return true;
    }
    return false;
  }

  @Override
  public final int hashCode() {
    return Objects.hash(leaveId, empId, leaveNoOfDays, leaveStartDate, leaveEndDate, leaveType, leaveStatus,
        leaveReason, leaveAppliedOn, leaveManagerComment);
  }

  /**
   * Gets the LeaveId.
   * @return this Leave ID.
   */
  public final int getLeaveId() {
    return leaveId;
  }
  /**
   * Gets the LeaveId.
   * @return this Leave ID.
   */
  public final SslAppliedStatus getSslAppliedStatus() {
    return sslAppliedStatus;
  }
  /**
   * @param argSslAppliedStatus to set leave id.
   */
  public final void setSslAppliedStatus(final SslAppliedStatus argSslAppliedStatus) {
    this.sslAppliedStatus = argSslAppliedStatus;
  }
  /**
   * @param argLeaveId to set leave id.
   */
  public final void setLeaveId(final int argLeaveId) {
    this.leaveId = argLeaveId;
  }

  /**
   * Gets the EmployeeId.
   * @return this Employee ID.
   */
  public final int getEmpId() {
    return empId;
  }

  /**
   * @param argEmpId to set employee id.
   */
  public final void setEmpId(final int argEmpId) {
    this.empId = argEmpId;
  }

  /**
   * Gets the LeaveNoOfDays.
   * @return this Leave No Of Days.
   */
  public final int getLeaveNoOfDays() {
    return leaveNoOfDays;
  }

  /**
   * @param argLeaveNoOfDays to set leave no of days.
   */
  public final void setLeaveNoOfDays(final int argLeaveNoOfDays) {
    this.leaveNoOfDays = argLeaveNoOfDays;
  }

  /**
   * Gets the LeaveStartDate.
   * @return this Leave Start Date.
   */
  public final Date getLeaveStartDate() {
    return leaveStartDate;
  }

  /**
   * @param argLeaveStartDate to set leave start date.
   */
  public final void setLeaveStartDate(final Date argLeaveStartDate) {
    this.leaveStartDate = argLeaveStartDate;
  }

  /**
   * Gets the LeaveEndDate.
   * @return this Leave End Date.
   */
  public final Date getLeaveEndDate() {
    return leaveEndDate;
  }

  /**
   * @param argLeaveEndDate to set leave end date.
   */
  public final void setLeaveEndDate(final Date argLeaveEndDate) {
    this.leaveEndDate = argLeaveEndDate;
  }

  /**
   * Gets the LeaveType.
   *
   * @return this Leave type.
   */
  public final LeaveType getLeaveType() {
    return leaveType;
  }

  /**
   * @param argLeaveType to set leave type.
   */
  public final void setLeaveType(final LeaveType argLeaveType) {
    this.leaveType = argLeaveType;
  }

  /**
   * Gets the LeaveStatus.
   * @return this Leave Status.
   */
  public final LeaveStatus getLeaveStatus() {
    return leaveStatus;
  }

  /**
   * @param argLeaveStatus to set leave status.
   */
  public final void setLeaveStatus(final LeaveStatus argLeaveStatus) {
    this.leaveStatus = argLeaveStatus;
  }

  /**
   * Gets the LeaveReason.
   * @return this Leave Reason.
   */
  public final String getLeaveReason() {
    return leaveReason;
  }

  /**
   * @param argLeaveReason to set leave reason.
   */
  public final void setLeaveReason(final String argLeaveReason) {
    this.leaveReason = argLeaveReason;
  }

  /**
   * Gets the LeaveAppliedOn.
   * @return this Leave Applied On.
   */
  public final Date getLeaveAppliedOn() {
    return leaveAppliedOn;
  }

  /**
   * @param argLeaveAppliedOn to set leave applied on.
   */
  public final void setLeaveAppliedOn(final Date argLeaveAppliedOn) {
    this.leaveAppliedOn = argLeaveAppliedOn;
  }

  /**
   * Gets the LeaveManagerComment.
   * @return this Leave Manager Comment.
   */
  public final String getLeaveManagerComment() {
    return leaveManagerComment;
  }

  /**
   * @param argLeaveManagerComment to set leave manager comment.
   */
  public final void setLeaveManagerComment(final String argLeaveManagerComment) {
    this.leaveManagerComment = argLeaveManagerComment;
  }

  /**
   * list all employee leave details.
   * @param mgrID id to get employee leave Details.
   * @return all employee leave details
   */
  public static LeaveDetails[] pendingLeave(final int mgrID) {

    List<LeaveDetails> e = dao().pending(mgrID);
    return e.toArray(new LeaveDetails[e.size()]);
  }

  /**
   * list employee details by id.
   * @param empID id to get employee details.
   * @return Employee
   */
  public static LeaveDetails[] leaveHis(final int empID) {
    List<LeaveDetails> es = dao().empHistory(empID);
    return es.toArray(new LeaveDetails[es.size()]);
  }


  @Override
  public final String toString() {
    return "leave id is:" + leaveId + "employee id is " + empId + "leave no of days is: " + leaveNoOfDays
        + "leave start date is: " + leaveStartDate + "leave end date is :" + leaveEndDate + "leave type is: "
        + leaveType + "leave status is: " + leaveStatus + "leave reason is:" + leaveReason
        + "leave applied on: " + leaveAppliedOn + "leave manager comments are: " + leaveManagerComment;
  }
  /**
   * The dao for employee.
   */
  private static EmployeeDAO edao() {
    DbConnection db = new DbConnection();
    return db.getConnect().onDemand(EmployeeDAO.class);
  }
    /**
   * list leave details by id.
   * @param leaveId id to get employee details.
   * @return LeaveDetails.
   */
  public static LeaveDetails listById(final int leaveId) {
    return dao().findLeave(leaveId);
  }
  /**
   * For Approval and Denial.
   * @param mgrId for manager info.
   * @param eid for employee info
   * @param leaveId for Leave related info.
   * @param leavestatus for knowing leave status.
   * @param mgrcomments for knowing if denied or approved.
   * @return details.
   */
  public static String approveDeny(final int mgrId, final int eid, final int leaveId, final String leavestatus,
                                   final String mgrcomments) {
    LeaveDetails ld = dao().findLeave(leaveId);
    int noOfDays = ld.getLeaveNoOfDays();
    int empId = ld.getEmpId();
    String sslStatus = ld.getLeaveType().toString();
    LeaveStatus lvStatus = ld.getLeaveStatus();
    String status = lvStatus.toString();
    Employee employee = edao().find(empId);
    int getMgrId = dao().getManager(leaveId);
    int balanceleaves = employee.getEmpLeaveBalance();
    System.out.println("balance leaves******" + balanceleaves);
    int remaingleaves = balanceleaves - noOfDays;
    int increment = balanceleaves + noOfDays;
    int leaveNoOfDay = ld.getLeaveNoOfDays();
    int elbal = 0;
    String res = null;
    String dbStatus = null;
    System.out.println(leavestatus);
    if (mgrId == getMgrId) {
      if (sslStatus.equals("SSL")) {
        if (leavestatus.equalsIgnoreCase("approved")) {
          dbStatus = "APPROVED";
          res = "LEAVE APPROVED SUCCESSFULLY";
          if (leaveNoOfDay > balanceleaves) {
            elbal = leaveNoOfDay - employee.getEmpLeaveBalance();
            dao().approved(mgrcomments, dbStatus, leaveId);
            edao().updateSSL(empId);
            res = "LEAVE APPROVED SUCCESSFULLY";
            System.out.println("******reaming balance is********" + elbal);
            int bal1 = employee.getEmpLeaveBalance() - leaveNoOfDay;
            System.out.println("employee balance after detecting" + bal1);
            if (bal1 < 0) {
              edao().lbalances(0, empId);
            } else {
              edao().slbalance(bal1, empId);
            }
            int bal2 = employee.getEmpSslBalance() - elbal;
            edao().slbalance(bal2, empId);
          } else {
            int bal = employee.getEmpLeaveBalance() - leaveNoOfDay;
            edao().lbalances(bal, empId);
          }
        } else {
          dbStatus = "DENIED";
          res = "SORRY YOUR LEAVE HAS BEEN REJECTED";
          dao().denied(mgrcomments, dbStatus, leaveId);
        }
      } else {
        if (leavestatus.equalsIgnoreCase("approved")) {
          dbStatus = "APPROVED";
          dao().approved(mgrcomments, dbStatus, leaveId);
          edao().lbalances(remaingleaves, empId);
          res = "LEAVE APPROVED SUCCESSFULLY";
          System.out.println("!!============" + res + "==============!!");
        } else {
          dbStatus = "DENIED";
          if (status == "APPROVED") {
            edao().lbalances(increment, empId);
          } else {
            System.out.println("sorry");
          }
          res = "SORRY YOUR LEAVE HAS BEEN REJECTED";
          edao().lbalances(increment, empId);
          dao().denied(mgrcomments, dbStatus, leaveId);
        }
      }
    } else {
      System.out.println("!!======================================================================!!");
      System.out.println("SORRRY YOU DON'T HAVE ACCESS PERMISSIONS TO THIS EMPLOYEEID PLEASE CHECK!!");
      System.out.println("!!======================================================================!!");
    }
    return res;
  }
  /**
   * The dao for employee.
   */
  private static LeaveDetailsDAO dao() {
    DbConnection db = new DbConnection();
    return db.getConnect().onDemand(LeaveDetailsDAO.class);
  }
   /**
   * @param empId id to get employee details.
   * @param startDate id to get startdate.
   * @param endDate id to get enddate.
   * @return count of no of employee.
   * @throws ParseException.
   */
  public static int count(final int empId, final String startDate, final String endDate) {
    int count = dao().count(empId, startDate, endDate);
    return count;
  }
  /**
   * list leave details by id.
   * @param sDate to initialize start date
   * @param eDate to initilize end Date
   * @return leaveNoOfDay.
   * @throws ParseException to throw parse exception.
   */
  public static int leaveNoOfDays(final String sDate, final String eDate) throws ParseException {
    SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
    Date startDate = sd.parse(sDate);
    Date endDate = sd.parse(eDate);
    long diffOfDays = endDate.getTime() - startDate.getTime();
    long days = 1 + diffOfDays / (1000 * 60 * 60 * 24);
    int leaveNoOfDay = (int) days;
    return leaveNoOfDay;
  }

  /**
   * @param empId         to initialize employee id.
   * @param startDate     to initialize employee startdate.
   * @param endDate       to initialize employee enddate.
   * @param leaveType     to initialize employee leave type.
   * @param leaveReason   to initialize employee leavereason.
   * @param i to know whether to insert or update.
   * @throws ParseException  to give parse exception
   * @return String message.
   */
  public static String applyLeave(final int empId, final String startDate,
      final String endDate, final String leaveType, final String leaveReason, final int i) throws ParseException {
    String s = "";
    int elbal = 0;
    Employee emp1 = Employee.listById(empId);
    String sslStat = null;
    LeaveDetails[] ld = LeaveDetails.leaveHis(empId);
    for (LeaveDetails var : ld) {
      if (var.getSslAppliedStatus().toString() == "YES") {
        sslStat = "YES";
      }
    }
    int mgrId = emp1.getEmpManagerId();
    Date today = new Date();
    Date doj = emp1.getEmpDoj();
    SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
    String leaveAppliedOn = sd.format(today);
    Date sDate = sd.parse(startDate);
    int countOfDays = countDays(startDate, endDate);
    Date eDate = sd.parse(endDate);
    long diffOfDays = eDate.getTime() - sDate.getTime();
    long expCheck = sDate.getTime() - doj.getTime();
    long check = 1 + expCheck / (1000 * 60 * 60 * 24);
    int exCheck = (int) check;
    System.out.println("experience check is *************" + exCheck);
    System.out.println("leave Type Is **************" + leaveType);
    System.out.println("emp ssl balance is*********" + emp1.getEmpSslBalance());
    long days = 1 + diffOfDays / (1000 * 60 * 60 * 24);
    int leaveNoOfDay = (int) days;
    int availableLvBl = emp1.getEmpLeaveBalance() - leaveNoOfDay;
    int overlap = 0;
    if (i == 0) {
      overlap = count(empId, startDate, endDate);
    }
    if (sDate.compareTo(today) == -1) {
      s = "*************** please enter valid date ,you cannot apply leave to past ****************";
    } else if (overlap == 1) {
      s = "**********  you have already applied for leave and it is overlapped **********";
    } else if (days <= 0) {
      s = "**************** End date is greater than start date please enter a valid date ************";
    } else if (sslStat == "YES") {
      s = "*******************you have already applied this leave*****************";
    } else if (leaveType.equals("EL")) {
      if (availableLvBl < 0) {
        s = "******* insufficient leave balance, you cannot apply more than your leave balance ******";
        System.out.println("******** your available balance is : " + emp1.getEmpLeaveBalance());
      }
    } else if (i > 0) {
      System.out.println("Entered into update" + i);
      // dao().insert(empId, leaveNoOfDay, startDate, endDate, leaveType, leaveReason,
      // leaveAppliedOn);
      // dao().leaveu(leaveNoOfDay, startDate, endDate, leaveType, leaveReason,
      // leaveAppliedOn, i);
      dao().updater(endDate, leaveType, leaveReason, i);
      dao().updatea(startDate, endDate, i);
      s = "**************************SUCESSFULLY UPDATED*****************";
    } else if (leaveType.equals("SSL") && exCheck <= 180) {
      s = "********************you cannot apply this leave  you should have atleast 6 months experience*******";
      System.out.println("experience works********************");
    } else {
      System.out.println("applyingh working *****************");
      dao().insert(empId, countOfDays, startDate, endDate, leaveType, leaveReason, leaveAppliedOn);
      System.out.println("**********  The number of days you applied is : " + countOfDays + "   ***********");
      s = "*****************  Sucessfully Applied *****************";
      if (mgrId == 0) {
        if (leaveType.equals("EL")) {
          dao().updateCEO(empId);
          int balance = emp1.getEmpLeaveBalance() - leaveNoOfDay;
          edao().lbalances(balance, empId);
        } else {
          dao().updateCEO(empId);
          edao().updateSSL(empId);
          if (leaveNoOfDay > emp1.getEmpLeaveBalance()) {
            elbal = leaveNoOfDay - emp1.getEmpLeaveBalance();
            System.out.println("empbalance is****" + emp1.getEmpLeaveBalance());
            System.out.println("******reaming balance is********" + elbal);
            int bal1 = emp1.getEmpLeaveBalance() - leaveNoOfDay;
            System.out.println("emp balance after dedecting" + bal1);
            if (bal1 < 0) {
              edao().lbalances(0, empId);
            } else {
              edao().slbalance(bal1, empId);
            }
            int bal2 = emp1.getEmpSslBalance() - elbal;
            edao().slbalance(bal2, empId);
          } else {
            int bal = emp1.getEmpLeaveBalance() - leaveNoOfDay;
            edao().lbalances(bal, empId);
          }
        }
      }
    }
    // System.out.println("employee exist");
   // dao().insert(empId, leaveNoOfDays, startDate, endDate, leaveType, leaveReason, leaveAppliedOn);
    return s;
  }
     /**
   * @param startDate     to initialize employee startdate.
   * @param endDate       to initialize employee enddate.
   * @throws ParseException  to give parse exception
   * @return String message.
   */
  public static int countDays(final String startDate, final String endDate) throws ParseException {
    int workingDays = 0;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Calendar startdate = Calendar.getInstance();
    startdate.setTime(sdf.parse(startDate));
    Calendar enddate = Calendar.getInstance();
    enddate.setTime(sdf.parse(endDate));
    while (!startdate.after(enddate)) {
      int day = startdate.get(Calendar.DAY_OF_WEEK);
      if (day != Calendar.SATURDAY && day != Calendar.SUNDAY) {
        workingDays++;
      }
      startdate.add(Calendar.DATE, 1);
    }
    return workingDays;
  }
  /**
   * list all employee details.
   * @return all employees' details
   * @param empID emp  id
   */
  public static LeaveDetails[] listAll(final int empID) {
    List<LeaveDetails> es = dao().allrecordsofid(empID);
    return es.toArray(new LeaveDetails[es.size()]);
  }

  /**
   * @param empId   to initialize employee id.
   * @param leaveId to give leaveId.
   * @return String message.
   */
  public static String deleteLeave(final int empId, final int leaveId) {
    String result = "null";
    dao().deleteLeave(empId, leaveId);
    result = "Your leave sucessfully deleted";
    return result;
  }
}
