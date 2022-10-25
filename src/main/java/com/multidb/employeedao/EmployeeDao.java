package com.multidb.employeedao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.multidb.model.employee.Employee;

@Repository
public interface EmployeeDao extends JpaRepository<Employee, Integer>{
 
}
