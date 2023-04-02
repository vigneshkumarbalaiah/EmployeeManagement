package com.lifefitness.EmployeeManagement.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lifefitness.EmployeeManagement.bean.Employee;
import com.lifefitness.EmployeeManagement.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@GetMapping("/employees")
	public ResponseEntity<Object> listEmployees(){
		try {
			List<Employee> emp = employeeService.listAllEmployees();
			for(Employee e: emp) {
				System.out.println(e.toString());
			}
			if(emp.size() > 0)
				return ResponseEntity.status(HttpStatus.OK).body(emp);
			else
				return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No employees found in database");
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getStackTrace());
		}
	}

	@PostMapping("/employees")
	public ResponseEntity<Object> saveEmployee(@Valid @RequestBody Employee employee){
		try {
			Employee emp = employeeService.saveEmployee(employee);
			if(emp!=null)
				return ResponseEntity.status(HttpStatus.CREATED).body(emp);
			String errMsg = "Employee already exists with same email. Please udpate existing employee";
			return ResponseEntity.status(HttpStatus.FOUND).body(errMsg);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getStackTrace());
		}
	}

	@GetMapping("/employees/{id}")
	public ResponseEntity<Object> getEmployeeById(@PathVariable Long id) {
		try {
			Optional<Employee> emp = employeeService.getEmployeeById(id);
			if(emp.isPresent())
				return ResponseEntity.status(HttpStatus.OK).body(emp);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getStackTrace());
		}
	}

	@GetMapping("/employees/department/{id}")
	public ResponseEntity<Object> getEmployeeBydepartmentId(@PathVariable Long id) {
		try {
			List<Employee> emp = employeeService.getEmployeeByDepartmentId(id);
			if(emp == null || emp.size() == 0)
				return ResponseEntity.status(HttpStatus.OK).body("No Employees found for department Id "+id);
			return ResponseEntity.status(HttpStatus.OK).body(emp);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getStackTrace());
		}

	}

	@GetMapping("/employees/department/{id}/minsalary")
	public ResponseEntity<Object> getEmployeeBydepartmentIdWithMinSalary(@PathVariable Long id) {
		try {
			List<Employee> emp = employeeService.getEmployeeByDepartmentIdWithMinimumSalary(id);
			if(emp == null || emp.size() == 0)
				return ResponseEntity.status(HttpStatus.OK).body("No Employees found for department Id "+id);
			return ResponseEntity.status(HttpStatus.OK).body(emp);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getStackTrace());
		}
	}

	@GetMapping("/employees/gender/{gender}")
	public ResponseEntity<Object> getEmployeeByGender(@PathVariable String gender) {
		try {
			List<Employee> emp = employeeService.getEmployeeByGender(gender);
			if(emp == null || emp.size() == 0)
				return ResponseEntity.status(HttpStatus.OK).body("No "+gender+" Employees found");
			return ResponseEntity.status(HttpStatus.OK).body(emp);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getStackTrace());
		}
	}

	@PutMapping("/employees/update/{id}")
	public ResponseEntity<Object> updateEmployee(@Valid @RequestBody Employee employee,@PathVariable Long id){
		try {
			employee.setEmployeeId(id);
			employee.getSalary().setEmployeeId(id);
			Optional<Employee> emp = employeeService.updateEmployee(employee);
			if(emp.isPresent())
				return ResponseEntity.status(HttpStatus.OK).body(emp);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found for update");
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getStackTrace());
		}

	}

	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Object> deleteEmployeeById(@PathVariable Long id) {
		try {
			if(employeeService.deleteEmployeeById(id))
				return ResponseEntity.status(HttpStatus.OK).body("Employee deleted successfully");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found for deletion");
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getStackTrace());
		}
	}
	
	@GetMapping("/employees/tax/{id}")
	public ResponseEntity<Object> getTaxByEmployeeId(@PathVariable Long id){
		try {
			Object tax = employeeService.getTaxByEmployeeId(id);
			if(tax!=null)
				return ResponseEntity.status(HttpStatus.OK).body(tax);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Employee not found");
			
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getStackTrace());
		}
	}

}
