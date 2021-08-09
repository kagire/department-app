package com.kagire.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;

class EmployeeTest {
    
    @Test
    public void getEmployeeSetters() {
        Employee employee = new Employee("Two", new Date(), 2, 2);

        Assertions.assertNotNull(employee.getDateOfBirth());
        Assertions.assertEquals("Two", employee.getName());
        Assertions.assertEquals(2, employee.getSalary());
        Assertions.assertEquals(2, employee.getDepartmentId());

        employee.setId(1);
        employee.setName("One");
        employee.setDateOfBirth(new Date(1));
        employee.setSalary(9);
        employee.setDepartmentId(1);

        Assertions.assertEquals(1, employee.getId());
        Assertions.assertEquals("One", employee.getName());
        Assertions.assertEquals(new Date(1), employee.getDateOfBirth());
        Assertions.assertEquals(9, employee.getSalary());
        Assertions.assertEquals(1, employee.getDepartmentId());
    }

    @Test
    public void equalsTest(){
        Employee employee = new Employee("", new Date(1), 1,1);
        assert employee.equals(employee);
        Assertions.assertFalse(employee.equals(new Object()));
        Employee employee1 = new Employee("", new Date(1), 1,1);
        Assertions.assertTrue(employee.equals(employee1));

    }

    @Test
    public void toStringTest(){
        Assertions.assertEquals("Employee{" +
                "id=" + 0 +
                ", name='"  + '\'' +
                ", dateOfBirth='" + new Date(1) + '\'' +
                ", salary=" + 1 +
                ", departmentId=" + 1 +
                '}', new Employee("", new Date(1), 1,1).toString());
    }

    @Test
    public void cloneDataTest(){
        Employee employee = new Employee("", new Date(), 1,1);
        Employee employee1 = new Employee("2", new Date(1), 2,2);
        employee.cloneData(employee1);
        Assertions.assertEquals(employee, employee1);

        Assertions.assertEquals(employee.hashCode(), employee1.hashCode());
    }
}