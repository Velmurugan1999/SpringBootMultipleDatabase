package com.multidb.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multidb.addressdao.AddressDao;
import com.multidb.employeedao.EmployeeDao;
import com.multidb.model.address.Address;
import com.multidb.model.employee.Employee;

@Service
public class EmployeeService {
	@Autowired
	private EmployeeDao edao;
	@Autowired
	private AddressDao adao;

	public List<Employee> getAllEmployee() {
		return edao.findAll();
	}

	public EmployeeDto getEmployeeById(int eid) {
		try {
			Employee e = edao.findById(eid).get();
			Address a = adao.findAddressByEid(eid).get(0);
			System.out.println(e.toString() + a.toString());
			EmployeeDto dto = new EmployeeDto(e.getEid(), e.getEname(), e.getAge(), e.getDepartment(), e.getSalary(),
					e.getCompanyname(), a);
			return dto;
		} catch (Exception e) {
			return null;
		}
	}

	public EmployeeDto insertEmployee(EmployeeDto dto) {

		Employee e = edao.save(
				new Employee(dto.getEname(), dto.getAge(), dto.getDepartment(), dto.getSalary(), dto.getCompanyname()));
		Address add = dto.getAddress();
		add.setEid(e.getEid());
		Address a = adao.save(dto.getAddress());
		EmployeeDto empdto = new EmployeeDto(e.getEid(), e.getEname(), e.getAge(), e.getDepartment(), e.getSalary(),
				e.getCompanyname(), a);
		return empdto;
	}

	public Employee updateEmployee(Employee emp) {
		if (edao.findById(emp.getEid()) != null) {
			return edao.save(emp);
		} else {
			System.out.println("No Employee to update");
			return null;
		}
	}

	public Employee delete(int eid) {
		try {
			Employee emp = edao.findById(eid).get();

			if (edao.findById(eid) != null) {
				edao.delete(emp);
				Address add = adao.findAddressByEid(eid).get(0);
				adao.delete(add);
				return emp;
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}
}
