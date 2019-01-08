package com.hexaware.ftp92.persistence;

import com.hexaware.ftp92.model.Employee;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.util.List;

/**
 * The DAO class for employee.
 */
public interface EmployeeDAO  {
  /**
   * return all the details of all the employees.
   * @return the employee array
   */
  @SqlQuery("SELECT * FROM EMPLOYEE")
  @Mapper(EmployeeMapper.class)
  List<Employee> list();

  /**
   * return all the details of the selected employee.
   * @param empID the id of the employee
   * @return the employee object
   */
  @SqlQuery("SELECT * FROM EMPLOYEE WHERE EMP_ID = :empID")
  @Mapper(EmployeeMapper.class)
  Employee find(@Bind("empID") int empID);
  /**
    * @param remainingleaves Manager Response data.
    * @param  empId Manager Status data.
    */
  @SqlUpdate("UPDATE EMPLOYEE SET EMP_LEAVE_BALANCE = :remainingleaves WHERE "
             + "EMP_ID= :empId")
  void lbalances(@Bind("remainingleaves") int remainingleaves,
      @Bind("empId") int empId);
  /**
   * @param remaining Manager Response data.
   * @param empId     Manager Status data.
   */
  @SqlUpdate("UPDATE EMPLOYEE SET EMP_SSL_LEAVE_BALANCE = :remainingleaves WHERE " + "EMP_ID= :empId")
  void slbalance(@Bind("remainingleaves") int remaining, @Bind("empId") int empId);
  /**gju
     * return Manager the details of the Leave Id.
     * @param leaveID the id of the employee
     * @return the ManagerID value
     */

  @SqlQuery("SELECT E1.EMP_ID FROM EMPLOYEE E1 "
      + " JOIN EMPLOYEE E2 ON E1.Emp_ID = E2.EMP_MANAGER_ID WHERE E2.EMP_ID =(SELECT EMP_ID FROM LEAVE_DETAILS "
      + " WHERE LEAVE_ID=:leaveID)")
  int getManager(@Bind("leaveID") int leaveID);
  /**
  *
  * @param empId of the manager
  */

  @SqlUpdate("UPDATE LEAVE_DETAILS SET SSL_APPLY_STATUS = 'YES' WHERE EMP_ID = :empId")
   void updateSSL(@Bind("empId") int empId);

  /**
  * close with no args is used to close the connection.
  */
  void close();
}
