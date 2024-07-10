/*
* Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
* Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
*/
package hw.q4;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
/**
*
* @author alialghanay
*/
public class EmployeeManager {

private static DBConnector dbconnector = new DBConnector();
private static Connection conn = dbconnector.getConnection();  

public EmployeeManager() {}

public void insertEmployee(Employee employee) throws SQLException {
    String query = "INSERT INTO employee (eid, first_name, last_name, address, city, phone_nam, sex, dob) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    PreparedStatement pstmt = conn.prepareStatement(query);
    pstmt.setInt(1, employee.getEid());
    pstmt.setString(2, employee.getFirstName());
    pstmt.setString(3, employee.getLastName());
    pstmt.setString(4, employee.getAddress());
    pstmt.setString(5, employee.getCity());
    pstmt.setString(6, employee.getPhoneNum());
    pstmt.setBoolean(7, employee.isSex());
    pstmt.setDate(8, employee.getDob());
    pstmt.executeUpdate();
    System.out.println("Employee inserted successfully.");
}

public Employee getEmployee(int eid) throws SQLException {
    String query = "SELECT * FROM employee WHERE eid = ?";
    Employee employee = null;
    
    PreparedStatement pstmt = conn.prepareStatement(query);
    pstmt.setInt(1, eid);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            employee = new Employee(
                    rs.getInt("eid"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("address"),
                    rs.getString("city"),
                    rs.getString("phone_nam"),
                    rs.getBoolean("sex"),
                    rs.getDate("dob")
            );
        }

    return employee;
}

public void updateEmployee(Employee employee) throws SQLException {
    String query = "UPDATE employee SET first_name = ?, last_name = ?, address = ?, city = ?, phone_nam = ?, sex = ?, dob = ? WHERE eid = ?";
    PreparedStatement pstmt = conn.prepareStatement(query);
     pstmt.setString(1, employee.getFirstName());
        pstmt.setString(2, employee.getLastName());
        pstmt.setString(3, employee.getAddress());
        pstmt.setString(4, employee.getCity());
        pstmt.setString(5, employee.getPhoneNum());
        pstmt.setBoolean(6, employee.isSex());
        pstmt.setDate(7, employee.getDob());
        pstmt.setInt(8, employee.getEid());
        pstmt.executeUpdate();
        System.out.println("Employee updated successfully.");
    
}

 public void deleteEmployee(int eid) throws SQLException {
    String query = "DELETE FROM employee WHERE eid = ?";
    PreparedStatement pstmt = conn.prepareStatement(query);
    pstmt.setInt(1, eid);
    pstmt.executeUpdate();
    System.out.println("Employee deleted successfully.");
   
}

 public List<Employee> listEmployees() throws SQLException{
    String query = "SELECT * FROM employee";
    List<Employee> employees = new ArrayList<>();
    Statement stmt = conn.createStatement();
     ResultSet rs = stmt.executeQuery(query);
      while (rs.next()) {
            Employee employee = new Employee(
                    rs.getInt("eid"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("address"),
                    rs.getString("city"),
                    rs.getString("phone_nam"),
                    rs.getBoolean("sex"),
                    rs.getDate("dob")
            );
            employees.add(employee);
        }

    return employees;
}
}
