package com.multidb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.multidb.model.address.Address;
import com.multidb.model.employee.Employee;
import com.multidb.service.EmployeeDto;
import com.multidb.service.EmployeeService;

@RestController
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	@GetMapping("/")
	public List<Employee> getAllEmployee(){
		return employeeService.getAllEmployee();
	}
	
	@GetMapping("/{eid}")
	public EmployeeDto getEmployeeById(@PathVariable int eid)
	{
		return employeeService.getEmployeeById(eid);
	}
	
	@PostMapping(path="/add")
	public EmployeeDto insertEmployee(@RequestBody EmployeeDto dto)
	{
		return employeeService.insertEmployee(dto);
	}
	
	@DeleteMapping("/{eid}")
	public Employee deleteEmployee(@PathVariable int  eid)
	{
		return employeeService.delete(eid);
	}
}
