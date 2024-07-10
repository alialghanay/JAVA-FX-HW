package hw.q4;

import java.sql.Date;
import java.time.LocalDate;

/**
 * Employee class representing an employee record.
 * 
 * @author alialghanay
 */
public class Employee {
    private int eid;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String phoneNum;
    private boolean sex;
    private Date dob;

    // Constructors
    public Employee() {}

    public Employee(String firstName, String lastName, String address, String city, String phoneNum, boolean sex, Date dob) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.phoneNum = phoneNum;
        this.sex = sex;
        this.dob = dob;
    }

    public Employee(int eid, String firstName, String lastName, String address, String city, String phoneNum, boolean sex, Date dob) {
        this.eid = eid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.phoneNum = phoneNum;
        this.sex = sex;
        this.dob = dob;
    }

    // Getters and Setters
    public int getEid() {
        return eid;
    }

    public void setEid(int eid) {
        this.eid = eid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }
    
    public void setDob(LocalDate dob) {
        Date date = Date.valueOf(dob);
        this.dob = date;
    }
}
