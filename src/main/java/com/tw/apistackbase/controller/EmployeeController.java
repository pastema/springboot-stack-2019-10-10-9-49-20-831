package com.tw.apistackbase.controller;

import com.tw.apistackbase.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/Employee")
public class EmployeeController {

    private List<Employee> employeeList = new ArrayList<>();

    @GetMapping(value = "get-all-employees")
    public List<Employee> getAllEmployees() {
        return this.employeeList;
    }

    @GetMapping(value = "get-employee-by-id/{id}")
    public Employee getEmployeeById(@PathVariable Integer id) {
        return employeeList.stream().filter(employee1 -> employee1.getId() == id).findFirst().orElse(null);
    }

    @PostMapping(value = "add-employee")
    public List<Employee> addEmployee(@RequestBody Employee employee) {
        this.employeeList.add(employee);
        return this.employeeList;
    }

    @PostMapping(value = "add-all-employees")
    public List<Employee> addAllEmployee(@RequestBody List<Employee> employee) {
        this.employeeList.addAll(employee);
        return this.employeeList;
    }

    @PutMapping(value = "edit-employee/{id}")
    public List<Employee> editEmployee(@PathVariable Integer id, @RequestBody Employee employeeNewValues) {
        Employee employeeOldValue = employeeList.stream().filter(employee1 -> employee1.getId() == id).findFirst().orElse(null);
        if(employeeOldValue != null) {
            employeeOldValue.setName(employeeNewValues.getName());
            employeeOldValue.setAge(employeeNewValues.getAge());
            employeeOldValue.setGender(employeeNewValues.getGender());
        }
        return this.employeeList;
    }

    @DeleteMapping(value = "delete-employee/{id}")
    public List<Employee> deleteEmployee(@PathVariable Integer id) {
        Employee employeeDelete = employeeList.stream().filter(employee1 -> employee1.getId() == id).findFirst().orElse(null);
        this.employeeList.remove(employeeDelete);
        return this.employeeList;
    }
}