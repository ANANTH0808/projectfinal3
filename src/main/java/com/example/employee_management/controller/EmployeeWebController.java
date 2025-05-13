package com.example.employee_management.controller;

import com.example.employee_management.model.Employee;
import com.example.employee_management.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employees")
public class EmployeeWebController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeWebController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping
    public String listEmployees(Model model) {
        model.addAttribute("employees", employeeService.getAllEmployees());
        return "employees"; // Maps to employees.html
    }
    @GetMapping("/search")
    public String searchByDepartment(@RequestParam String department, Model model) {
        model.addAttribute("employees", employeeService.findByDepartment(department));
        return "employees"; // same view as the employee list
    }

    @GetMapping("/new")
    public String newEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "add-employee"; // Maps to add-employee.html
    }

    @PostMapping
    public String saveEmployee(@Valid @ModelAttribute Employee employee, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-employee";
        }
        employeeService.saveEmployee(employee);
        return "redirect:/employees";
    }

    @GetMapping("/edit/{id}")
    public String editEmployeeForm(@PathVariable Long id, Model model) {
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "edit-employee"; // Maps to edit-employee.html
    }


    @PostMapping("/edit/{id}")
    public String updateEmployee(@PathVariable Long id, @Valid @ModelAttribute Employee employee, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "edit-employee";
        }
        employeeService.updateEmployee(id, employee);
        return "redirect:/employees";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return "redirect:/employees";
    }
}