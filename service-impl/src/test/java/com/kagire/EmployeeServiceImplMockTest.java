package com.kagire;

import com.kagire.config.MockTestConfig;
import com.kagire.entity.Department;
import com.kagire.entity.Employee;
import com.kagire.exceptions.DepartmentNotFoundException;
import com.kagire.exceptions.EmployeeNotFoundException;
import com.kagire.repository.DepartmentRepository;
import com.kagire.repository.EmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {EmployeeServiceImpl.class, DepartmentServiceImpl.class})
@ContextConfiguration(classes = MockTestConfig.class)
@ActiveProfiles("test")
class EmployeeServiceImplMockTest {

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    EmployeeService employeeService;

    @Test
    void findAllShouldReturnEmptyList() {
        Assertions.assertNotNull(employeeService.findAll());
    }

    @Test
    void findByIdShouldThrowEmployeeNotFoundException() {
        Assertions.assertThrows(EmployeeNotFoundException.class,() -> employeeService.findById((long)1));
    }

    @Test
    void createShouldProcessButEntityListEmpty() {
        Assertions.assertThrows(DepartmentNotFoundException.class,() -> employeeService.create(new Employee("", new Date(), 1, 1)));
        Assertions.assertThrows(EmployeeNotFoundException.class,() -> employeeService.findById((long)0));

        when(departmentRepository.findById(any())).thenReturn(Optional.of(new Department("")));
        when(employeeRepository.save(any())).thenReturn(new Employee("", new Date(), 1, 1));
        Assertions.assertDoesNotThrow(() -> employeeService.create(new Employee("", new Date(), 1, 1)));
    }

    @Test
    void updateShouldThrowEmployeeNotFoundExceptionButNotWhenMocked() {
        Assertions.assertThrows(EmployeeNotFoundException.class, () -> employeeService.update(new Employee("", new Date(), 1, 1)));

        when(employeeRepository.save(any())).thenReturn(new Employee("", new Date(), 1, 1));
        when(employeeRepository.findById(any())).thenReturn(Optional.of(new Employee("", new Date(), 1, 1)));
        Assertions.assertNotNull(employeeService.update(new Employee("", new Date(), 1, 1)));
    }

    @Test
    void deleteShouldThrowEmployeeNotFoundExceptionButNotWhenMocked() {
        Assertions.assertThrows(EmployeeNotFoundException.class, () -> employeeService.delete(Mockito.anyLong()));

        when(employeeRepository.findById(any())).thenReturn(Optional.of(new Employee("", new Date(), 1, 1)));
        when(employeeRepository.findById(any())).thenReturn(Optional.of(new Employee("", new Date(), 1, 1)));
        Assertions.assertDoesNotThrow(() -> employeeService.delete((long)1));
    }

    @Test
    void countShouldReturnNonNullValue() {
        Assertions.assertNotNull(employeeService.count());
    }

    @Test
    void findByDepartmentIdShouldReturnInstance(){
        when(employeeRepository.findByDepartmentId(anyInt())).thenReturn(new ArrayList<>());
        Assertions.assertTrue(employeeService.findByDepartmentId(1).isEmpty());

    }
}