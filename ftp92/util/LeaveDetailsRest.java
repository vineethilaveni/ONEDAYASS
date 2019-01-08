package com.hexaware.ftp92.util;

import javax.ws.rs.GET;
//import javax.ws.rs.NotFoundException;
// import javax.ws.rs.GET;
//import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;

import com.hexaware.ftp92.model.LeaveDetails;
//import com.hexaware.ftp92.model.LeaveType;
/**
 * This class provides a REST interface for the employee entity.
 */
// @Path("/leavedetails")
// public class LeaveDetailsRest {
// // /**
//    * Returns a specific employee's details.
//    * @param empId the id of the employee.
//    * @param ldv the object of the LeaveDetails
//    * @throws ParseException for Parsing errors.
//    * @return the employee details
//    */
  // @POST
  // @Produces(MediaType.APPLICATION_JSON)
  // @Consumes(MediaType.APPLICATION_JSON)
  // @Path("/{empId}/insertData")
  // public final String  insertData(@PathParam("empId") final int empId, final LeaveDetails ldv) throws
  //  ParseException {
  //   SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
  //   String  ltype = ldv.getLeaveType().toString();
  //   String s = LeaveDetails.applyLeave(empId, df.format(ldv.getLeaveStartDate()), df.format(ldv.getLeaveEndDate()),
  //                            ltype, ldv.getLeaveReason(), 0);
  //   return s;
  // }
/**
 * This class provides a REST interface for the employee entity.
 */
@Path("/leavedetails")
public class LeaveDetailsRest {
    /**
     * This class provides a REST interface for the LeaveDetails entity.
     * @param empId to give leaveDetails obj.
     * @return leavegives the leavedetails.
     */
  @GET
  @Path("/{empId}/leaveHistory")
  @Produces(MediaType.APPLICATION_JSON)
  public final LeaveDetails[] empLeaveHistory(@PathParam("empId") final int empId) {
    LeaveDetails[] leave = LeaveDetails.leaveHis(empId);
    return leave;
  }
  /**
    * This class provides a REST interface for the LeaveDetails entity.
    * @param ld to give leaveDetails obj.
    * @return leavegives the leavedetails.
    * @throws ParseException mjhj.
    */
  @POST
  @Path("/editleave")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public final String editLeaveRest(final LeaveDetails ld) throws ParseException {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      //String sDate = ld.getLeaveStartDate().toString();
      //String eDate = ld.getLeaveEndDate().toString();
    String leaveType = ld.getLeaveType().toString();
    int leaveId = ld.getLeaveId();
      // LeaveDetails lvd = new LeaveDetails();
    String s = LeaveDetails.applyLeave(ld.getEmpId(), sdf.format(ld.getLeaveStartDate()),
                                  sdf.format(ld.getLeaveEndDate()), leaveType, ld.getLeaveReason(), leaveId);
    return s;
  }
 /**
   * Returns a list of all the employees.
   * @param empManagerId jhjhgjh
   * @param leaveStatus  mbjm
   * @param l            nhfghf
   * @param leaveId mnbjmn
   * @throws ParseException for Parsing errors.
   * @return a list of all the employees
   */
  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  @Path("/{empManagerId}/{leaveStatus}/{leaveId}/manageraction")
  public final String managerAction(@PathParam("empManagerId") final int empManagerId,
      @PathParam("leaveId") final int leaveId,
      @PathParam("leaveStatus") final String leaveStatus, final LeaveDetails l) throws ParseException {
    LeaveDetails ld = new LeaveDetails();
    String a = ld.approveDeny(empManagerId, l.getEmpId(), leaveId, leaveStatus, l.getLeaveManagerComment());
    return a;
  }
 /**
   * Returns a specific employee's leave  details.
   * @param empId the id of the Employee
   * @param ld the object of the LeaveDetails
   * @throws ParseException for Parsing errors.
   * @return the Leave details
   */

  @POST
  @Path("/applyleave/{empId}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public final String employeeApplyLeave(@PathParam("empId") final int empId,
                                         final LeaveDetails ld) throws ParseException {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String leType = ld.getLeaveType().toString();
    String result = LeaveDetails.applyLeave(empId, sdf.format(ld.getLeaveStartDate()), sdf.format(ld.getLeaveEndDate()),
                          leType, ld.getLeaveReason(), 0);
    return result;
  }
 /**
   * Returns a specific employee's details.
   * @param mgrid the id of all the employee
   * @return the pending leave details of all the employee
   */

  @GET
  @Path("{mgrid}")
  @Produces(MediaType.APPLICATION_JSON)
  public final LeaveDetails[] leavePendingList(@PathParam("mgrid") final int mgrid) {
    LeaveDetails[] l = LeaveDetails.pendingLeave(mgrid);
    return l;
  }
}
