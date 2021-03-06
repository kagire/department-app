package com.kagire.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @Column(name = "date_of_birth")
    private Date dateOfBirth;
    private int salary;
    @Column(name = "department_id")
    private long departmentId;

    public Employee() {}

    public Employee(String name, Date dateOfBirth, int salary, long departmentId) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.salary = salary;
        this.departmentId = departmentId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(long departmentId) {
        this.departmentId = departmentId;
    }

    public void cloneData(Employee employee){
        this.name = employee.getName();
        this.dateOfBirth = employee.getDateOfBirth();
        this.departmentId = employee.getDepartmentId();
        this.salary = employee.getSalary();
    }

    public String stringDate(){
        return this.getDateOfBirth().toString().substring(0,10);
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == this) return true;

        if (obj == null || obj.getClass() != this.getClass()) return false;

        Employee employee2 = (Employee) obj;
        return this.name.equals(employee2.getName()) &&
                this.dateOfBirth.equals(employee2.getDateOfBirth()) &&
                this.salary == (employee2.getSalary()) &&
                this.departmentId == employee2.getDepartmentId();
    }

    public String toString() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        String strDate = dateFormat.format(this.dateOfBirth);
        return "{" +
                "\"id\":" + this.id +
                ", \"name\":\"" + this.name + "\"" +
                ", \"dateOfBirth\":\"" + strDate + "\"" +
                ", \"salary\":" + this.salary +
                ", \"departmentId\":" + this.departmentId +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.dateOfBirth, this.salary, this.departmentId);
    }
}
