package com.example.employee_management.service;

import com.example.employee_management.exception.EmployeeNotFoundException;
import com.example.employee_management.model.Employee;
import com.example.employee_management.repository.EmployeeRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;


import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + id));
    }
   @Override
   public List<Employee> findByDepartment(String department) {
        return employeeRepository.findByDepartmentIgnoreCase(department);
    }

    @Override
    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        Employee existing = getEmployeeById(id);
        existing.setName(updatedEmployee.getName());
        existing.setEmail(updatedEmployee.getEmail());
        existing.setDepartment(updatedEmployee.getDepartment());
        existing.setAge(updatedEmployee.getAge());
        return employeeRepository.save(existing);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);

    }
    @Override
    public Page<Employee> getAllPagedEmployees(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

}
