package com.example.employee_management.service;

import com.example.employee_management.model.Employee;
import com.example.employee_management.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


public interface EmployeeService  {

    Employee saveEmployee(Employee employee);

    List<Employee> getAllEmployees();

    Employee getEmployeeById(Long id);

    List<Employee> findByDepartment(String department);

    Employee updateEmployee(Long id, Employee employee);

    void deleteEmployee(Long id);

    Page<Employee> getAllPagedEmployees(Pageable pageable);
}
