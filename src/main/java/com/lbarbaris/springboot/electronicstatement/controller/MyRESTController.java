//package com.lbarbaris.springboot.electronicstatement.controller;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RequestMapping("/")
//@RestController
//public class MyRESTController {
//
//    @GetMapping("/employees")
//    public List<Employee> getAllEmployees() {
//        List<Employee> allEmployees = employeeService.getAllEmployees();
//
//
//        return allEmployees;
//    }
//
//    @GetMapping("/employees/{id}")
//    public Employee getEmployee(@PathVariable int id) {
//        if (employeeService.getEmp(id) == null) {
//            throw new IncorrectDataException("no employee for id: " + id + " or incorrect data");
//
//        }
//        return employeeService.getEmp(id);
//
//    }
//
//    @PostMapping("/employees")
//    public Employee addNewEmployee(@RequestBody Employee employee){
//        employeeService.save(employee);
//        return employee;
//    }
//
//    @PutMapping("/employees")
//    public Employee updateEmployee(@RequestBody Employee employee){
//        employeeService.save(employee);
//        return employee;
//    }
//
//    @DeleteMapping("/employees/{id}")
//    public String deleteEmployee(@PathVariable int id){
//
//        if (employeeService.getEmp(id) == null){
//            throw new IncorrectDataException("no employee with id: " + id);
//        }
//        employeeService.delete(id);
//        return "Employee " + id + " was deleted";
//    }
//}
//
