package com.lifefitness.EmployeeManagement.service;

import java.util.List;
import java.util.Optional;

import com.lifefitness.EmployeeManagement.bean.Employee;

public interface EmployeeService {

	Optional<Employee> getEmployeeById(Long empId);
	
	List<Employee> getEmployeeByDepartmentId(Long departmentId);
	
	List<Employee> getEmployeeByDepartmentIdWithMinimumSalary(Long departmentId);
	
	List<Employee> getEmployeeByGender(String gender);
	
	List<Employee> listAllEmployees();
	
	Employee saveEmployee(Employee emp);
	
	Optional<Employee> updateEmployee(Employee emp);
	
	boolean deleteEmployeeById(Long id);
	
	Object getTaxByEmployeeId(Long id);
}
