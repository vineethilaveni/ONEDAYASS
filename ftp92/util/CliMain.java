package com.hexaware.ftp92.util;

import java.text.ParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.hexaware.ftp92.model.LeaveDetails;
// import java.text.ParseException;
import com.hexaware.ftp92.model.Employee;
import com.hexaware.ftp92.model.LeaveStatus;

/**
 * Class CliMain provides the command line interface to the leavemanagement
 * application.
 */
public class CliMain {
  private Scanner option = new Scanner(System.in, "UTF-8");

  private void mainMenu() {
    System.out.println("Leave Management System");
    System.out.println("-----------------------");
    System.out.println("1. List All Employees Info");
    System.out.println("2. Display Employee Info");
    System.out.println("3. Apply for Leave");
    System.out.println("4. view Leave History");
    System.out.println("5. pending Leaves");
    System.out.println("6. Manager's Action");
    System.out.println("7. Edit or Delete Leave");
    System.out.println("8. Exit");
    System.out.println("Enter your choice:");
    int menuOption = option.nextInt();
    mainMenuDetails(menuOption);
  }

  private void mainMenuDetails(final int selectedOption) {
    switch (selectedOption) {
      case 1:
        listEmployeesDetails();
        break;
      case 2:
        listEmployeeDetail();
        break;
      case 3:
        applyLeave();
        break;
      case 4:
        leaveHistory();
        break;
      case 5:
        pendingLeaves();
        break;
      case 6:
        managerAction();
        break;
      case 7:
        editOrDelete();
        break;
      case 8:
        // halt since normal exit throws a stacktrace due to jdbc threads not responding
        Runtime.getRuntime().halt(0);
      default:
        System.out.println("Choose any option between 1 - 7");
    }
    mainMenu();
  }
  private void editOrDelete() {
    Scanner sc = new Scanner(System.in);
    System.out.println("1. Edit leave");
    System.out.println("2. Delete Leave");
    System.out.println("3. Exit");
    int i = sc.nextInt();
    switch (i) {
      case 1:
        editApplyedLeave();
        break;
      case 2:
        deleteLeave();
        break;
      case 3:
        // halt since normal exit throws a stacktrace due to jdbc threads not responding
        //Runtime.getRuntime().halt(0);
        mainMenu();
      default:
        System.out.println("Choose any option between 1 - 3");
        break;
    }
  }
  private void editApplyedLeave() {
    System.out.println("**********Welcome to edit the Applyed leave portal************");
    Scanner r = new Scanner(System.in);
    System.out.println("**********PLEASE ENTER YOUR ID************");
    int id = 0;
    while (true) {
      try {
        System.out.println("Enter employee Id.");
        String s = r.next();
        id = Integer.parseInt(s);
        Employee e = Employee.listById(id);
        int newempId = e.getEmpId();
        if (id == newempId) {
          break;
        }
      } catch (NumberFormatException e) {
        System.out.println("===================================================================");
        System.out.println("Use the correct format please.");
      } catch (InputMismatchException e) {
        System.out.println("===================================================================");
        System.out.println("Please Enter in Numbers only. Don't use Strings.");
      } catch (NullPointerException e) {
        System.out.println("===================================================================");
        System.out.println("No Employ Id as such found.");
      }
    }
    LeaveDetails[] employee = LeaveDetails.listAll(id);
    for (LeaveDetails e : employee) {
      System.out.println("===================================================");
      System.out.println(e.getLeaveId() + "  " + e.getEmpId() + "  " + e.getLeaveNoOfDays() + "  "
          + e.getLeaveStartDate() + "  " + e.getLeaveEndDate() + "  " + e.getLeaveType() + "  " + e.getLeaveStatus()
          + "  " + e.getLeaveReason() + "  " + e.getLeaveAppliedOn() + "  " + e.getLeaveManagerComment());
    }
    int i = 0;
    while (true) {
      try {
        System.out.println("*********Enter your leave id which you want to edit***********");
        String w = r.next();
        i = Integer.parseInt(w);
        LeaveDetails e = LeaveDetails.listById(i);
        int newempId = e.getLeaveId();
        LeaveStatus rak = e.getLeaveStatus();
        String q = rak.toString();
        if (i == newempId) {
          if (q.equals("PENDING")) {
            System.out.println("Sucessfully in pending state");
            try {
              System.out.println("Enter StartDate (yyyy-MM-dd) ");
              String startDate = option.next();
              System.out.println("Enter endDate (yyyy-MM-dd) ");
              String endDate = option.next();
              System.out.println(" **The No Of Days You Are Applying is : "
                                  + LeaveDetails.leaveNoOfDays(startDate, endDate)
                                  + "    ********* From : " + startDate + " To : " + endDate
                                  + "  **********please check the date you entered and apply");
              String lT;
              while (true) {
                try {
                  System.out.println("Enter LeaveType (EL)");
                  lT = option.next();
                  String type = lT.toUpperCase();
                  lT = type;
                  String a = lT.valueOf("EL").toString();
                  if (lT.equals(a)) {
                    break;
                  }
                } catch (Exception b) {
                  System.out.println(" ******* Enter the correct format of EL *******");
                }
              }
              System.out.println("Enter LeaveReason ");
              String leaveReason = option.next();
              // System.out.println("Enter LeaveAppliedOn(YYYY-MM-DD) ");
              // String leaveAppliedOn = option.next();
              String status = null;
              int up = newempId;
              try {
                status = LeaveDetails.applyLeave(id, startDate, endDate, lT, leaveReason, up);
                System.out.println(status);
              } catch (Exception a) {
                System.out.println("******** please enter correct format of date *********");
              }
            } catch (ParseException m) {
              System.out.println("**************please enter valid date **************");
            }
          } else {
            System.out.println("**********YOUR LEAVE IS NOT IN PENDING STATE**************");
          }
          break;
        }
      } catch (Exception e) {
        System.out.println("===================================================================");
        System.out.println("Use the correct format please.");
      }
    }
  }
  private void listEmployeeDetail() {
    System.out.println("Enter an Employee Id");
    int empId = option.nextInt();
    Employee e = Employee.listById(empId);
    if (e == null) {
      System.out.println("******Sorry, There is no such employee********");
    } else {
      System.out.println("===================================================");
      System.out.println(e.getEmpId() + " " + e.getEmpName() + " " + e.getEmpEmail() + " " + e.getEmpMobileNo() + " "
          + e.getEmpDoj() + " " + e.getEmpDept() + " " + e.getEmpLeaveBalance() + " " + e.getEmpManagerId());
      System.out.println("===================================================");
    }
  }

  private void listEmployeesDetails() {
    Employee[] employee = Employee.listAll();
    for (Employee e : employee) {
      System.out.println("===================================================");
      System.out.println(e.getEmpId() + " " + e.getEmpName() + " " + e.getEmpEmail() + " " + e.getEmpMobileNo() + " "
          + e.getEmpDoj() + " " + e.getEmpDept() + " " + e.getEmpLeaveBalance() + " " + e.getEmpManagerId());
    }
  }

  private void applyLeave() {
    System.out.println("Enter Employee Id  ");
    try {
      int empId = Integer.parseInt(option.next());
      Employee emp = Employee.listById(empId);
      if (emp == null) {
        System.out.println("**********************************");
        System.out.println("******** please enter an employee Id of this Organization *********");
        System.out.println("**********************************");
      } else {
        System.out.println("********************************");
        System.out.println("welcome..!!" + emp.getEmpName() + " your leave balance is :  " + emp.getEmpLeaveBalance());
        System.out.println("********************************");
        try {
          System.out.println("Enter StartDate (yyyy-MM-dd) ");
          String startDate = option.next();
          System.out.println("Enter endDate (yyyy-MM-dd) ");
          String endDate = option.next();
          // System.out.println("Enter noOfdays ");
          // int noOfdays = Integer.parseInt(option.next());
          // System.out.println("Enter LeaveType (EL)");
          // String leaveType = option.next();
          System.out.println(" **The No Of Days You Are Applying is : " + LeaveDetails.leaveNoOfDays(startDate, endDate)
                              + "    ********* From : " + startDate + " To : " + endDate
                              + "  **********please check the date you entered and apply");
          String leaveType;
          while (true) {
            try {
              System.out.println("Enter LeaveType (EL/SSL)");
              leaveType = option.next();
              String type = leaveType.toUpperCase();
              leaveType = type;
              String a = leaveType.valueOf("EL").toString();
              String b = leaveType.valueOf("SSL").toString();
              if (leaveType.equals(a) || leaveType.equals(b)) {
                break;
              }
            } catch (Exception e) {
              System.out.println(" ******* Enter the correct format of EL *******");
            }
          }
          System.out.println("Enter LeaveReason ");
          String leaveReason = option.next();
          // System.out.println("Enter LeaveAppliedOn(YYYY-MM-DD) ");
          // String leaveAppliedOn = option.next();
          int a = 0;
          String status = null;
          try {
            status = LeaveDetails.applyLeave(empId, startDate, endDate, leaveType, leaveReason, a);
            System.out.println(status);
          } catch (ParseException e) {
            System.out.println("******** please enter correct format of date *********");
          }
        } catch (ParseException e) {
          System.out.println("**************please enter valid date **************");
        }
      }
    } catch (Exception e) {
      System.out.println("********* please enter a valid id. employee id cannot be in characters***********");
    }
  }

  private void leaveHistory() {
    int empId = 0;
    while (true) {
      try {
        System.out.println("*********************");
        System.out.println("Enter an Employee Id");
        System.out.println("*********************");
        String id = option.next();
        empId = Integer.parseInt(id);
        Employee e = Employee.listById(empId);
        int newempId = e.getEmpId();
        if (empId == newempId) {
          break;
        }
      } catch (NumberFormatException e) {
        System.out.println("*********Please Enter the valid EMPID*********");
      } catch (InputMismatchException e) {
        System.out.println("********Enter EMPID only Number Format*******");
      } catch (NullPointerException e) {
        System.out.println("******Don't enter NULL.....Enter valid EMPID******");
      }
    }
    LeaveDetails[] leaves = LeaveDetails.leaveHis(empId);
    int size = leaves.length;
    if (size == 0) {
      System.out.println("*******No Records*******");
    } else {
      for (LeaveDetails e : leaves) {
        System.out.println("L.id/Empid/LNo.Days/L.startDate/L.EndDate/L.Type/LStatus/L.Reason/L.AppliedOn/MgrComm");
        System.out.println("***************************************************************************");
        System.out.println(e.getLeaveId() + " " + e.getEmpId() + " " + e.getLeaveNoOfDays() + " "
            + e.getLeaveStartDate() + " " + e.getLeaveEndDate() + " " + e.getLeaveType() + " " + e.getLeaveStatus()
            + " " + e.getLeaveReason() + " " + e.getLeaveAppliedOn() + " " + e.getLeaveManagerComment());
      }
      System.out.println("===================================================");
    }
  }

  private void pendingLeaves() {
    int empId = 0;
    while (true) {
      try {
        System.out.println("Enter Manager Id.");
        String id = option.next();
        empId = Integer.parseInt(id);
        Employee e = Employee.listById(empId);
        int newempId = e.getEmpId();
        if (empId == newempId) {
          break;
        }
      } catch (NumberFormatException e) {
        System.out.println("===================================================================");
        System.out.println("Use the correct format please.");
      } catch (InputMismatchException e) {
        System.out.println("===================================================================");
        System.out.println("Please Enter in Numbers only. Don't use Strings.");
      } catch (NullPointerException e) {
        System.out.println("===================================================================");
        System.out.println("No Employ Id as such found.");
      }
    }
    if (empId == 1000 || empId == 2000 || empId == 2001) {
      LeaveDetails[] employee = LeaveDetails.pendingLeave(empId);
      int size = employee.length;
      if (size == 0) {
        System.out.println("===================================================================");
        System.out.println("No records found for manager Id  " + empId);
      } else {
        System.out.println("=============================================");
        System.out.println("LEAVEID EMPID LEAVE_NO_OF_DAYS LEAVE_START_DATE"
                    + "LEAVE_END_DATE lEAVE_TYPE LEAVE_STATUS LEAVE_REASON"
                    + "LEAVE_APPLIED_ON MANAGER_COMMENT");
        for (LeaveDetails e : employee) {
          System.out.println("===================================================================");
          System.out.println(e.getLeaveId() + " " + e.getEmpId() + " "
                            + " " + e.getLeaveNoOfDays() + " " + e.getLeaveStartDate() + " "
                            + " " + e.getLeaveEndDate() + " " + e.getLeaveType() + " " + e.getLeaveStatus()
                            + " " + e.getLeaveReason() + " " + e.getLeaveAppliedOn()
                            + " " + e.getLeaveManagerComment());
        }
        System.out.println("===================================================================");
        System.out.println("Pending leaves of managerid  " + empId);
      }
    } else {
      System.out.println("===================================================================");
      System.out.println("enter manager id only");
    }
  }

  /**
   * For Approval and Denial.
   */
  public static void managerAction() {
    Scanner sc = new Scanner(System.in);
    int eid = 0;
    while (true) {
      try {
        System.out.println("!!===========================================!!");
        System.out.println("ENTER THE EMPLOYEE ID WHO APPLIED FOR LEAVE");
        System.out.println("!!===========================================!!");
        String id = sc.next();
        eid = Integer.parseInt(id);
        Employee e = Employee.listById(eid);
        int newempId = e.getEmpId();
        if (eid == newempId) {
          break;
        }
      } catch (NumberFormatException e) {
        System.out.println("PLEASE ENTER THE VALID EMPLOYEEID:");
      } catch (InputMismatchException e) {
        System.out.println("PLEASE ENTER DATA IN NUMBER FORMAT ONLY!!");
      } catch (NullPointerException e) {
        System.out.println("PLEASE ENTER VALID EMPLOYEEID:");
      }
    }
    if (eid == 1000) {
      System.out.println("!!========================================!!");
      System.out.println("CANNOT PERFORM ANY OPERATIONS ON CEO EMPID!!");
      System.out.println("!!========================================!!");
    } else {
      System.out.println("!!================!!");
      System.out.println("Enter a leave ID:");
      System.out.println("!!================!!");
      int leaveId = sc.nextInt();
      LeaveDetails ldetails = LeaveDetails.listById(leaveId);
      // while (true) {
      //   try {
      //     System.out.println("jkhguifi" + ldetails);
      //     if (ldetails == null) {
      //       break;
      //     }
      //   } catch (NullPointerException e) {
      //     System.out.println("enter valid leave id");
      //   }
      // }
      int a = ldetails.getEmpId();
      LeaveStatus lt;
      lt = ldetails.getLeaveStatus();
      String z = lt.toString();
      //String p = "APPROVED";
      String res = "";
      //System.out.println("*****" + z);
      if (eid == a) {
        if (ldetails == null) {
          System.out.println("!!========================!!");
          System.out.println("enter the valid leaveId");
          System.out.println("!!========================!!");
        } else {
          System.out.println("!!==========================================!!");
          System.out.println("PLEASE ENTER YOUR EMPLOYEEID(MANAGERID:):");
          System.out.println("!!==========================================!!");
          int mgrId = sc.nextInt();
          //Employee emp = Employee.listById(mgrId);
          System.out.println("!!===========================================!!");
          System.out.println("DO YOU WANT TO APPROVE OR DENY THE LEAVE ?:");
          System.out.println("!!===========================================!!");
          String ch = sc.next();
          System.out.println("!!======================!!");
          System.out.println("MANAGER COMMENTS:");
          System.out.println("!!======================!!");
          String mgrCom = sc.next();
          // String type = ch.toUpperCase();
          if (ch.equalsIgnoreCase(z)) {
            // if (type.equals(z)) {
            System.out.println("!!=========================================!!");
            System.out.println("IT IS ALREADY IN APPROVED OR DENIED STATE!!");
            System.out.println("!!==========================================!!");
            //res = LeaveDetails.approveDeny(eid, leaveId, ch, mgrCom);
            // }
          } else {
            res = LeaveDetails.approveDeny(mgrId, eid, leaveId, ch, mgrCom);

          }
          System.out.println(res);
        }
      } else {
        System.out.println("!!================================!!");
        System.out.println("EMPLOYEE ID AND LEAVEID MISSMATCH");
        System.out.println("!!================================!!");
      }
    }
  }

  /**
   * For deleting leave.
   */
  public final void deleteLeave() {
    String status = "null";
    System.out.println("please enter employee id");
    try {
      int empId = Integer.parseInt(option.next());
      Employee emp = Employee.listById(empId);
      if (emp == null) {
        System.out.println("*********** please enter id of this organization**************");
      } else {
        System.out.println("enter leave id");
        try {
          int leaveId = Integer.parseInt(option.next());
          LeaveDetails ld = LeaveDetails.listById(leaveId);
          int empid = ld.getEmpId();
          if (empid != empId) {
            System.out.println("************there is no such leave applied by you************");
          } else {
            String stat = ld.getLeaveStatus().toString();
            if (stat == "PENDING") {
              status = LeaveDetails.deleteLeave(empId, leaveId);
              System.out.println(status);
            } else {
              System.out.println("you cannot delete this leave, It is not in pnding state");
            }
          }
        } catch (NumberFormatException e) {
          System.out.println("*************please enter correct format of leaveid ***********");
        }
      }
    } catch (Exception e) {
      System.out.println("please enter a valid id");
    }
  }

  /**
   * The main entry point.
   * @param ar the list of arguments
   */
  public static void main(final String[] ar) {
    final CliMain mainObj = new CliMain();
    mainObj.mainMenu();
  }
}
