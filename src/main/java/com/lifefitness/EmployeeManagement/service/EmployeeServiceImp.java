package com.lifefitness.EmployeeManagement.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.lifefitness.EmployeeManagement.bean.Department;
import com.lifefitness.EmployeeManagement.bean.Employee;
import com.lifefitness.EmployeeManagement.repository.DepartmentRepository;
import com.lifefitness.EmployeeManagement.repository.EmployeeRepository;

@Service
public class EmployeeServiceImp implements EmployeeService{

	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	DepartmentRepository departmentRepository;
	
	@Override
	public List<Employee> listAllEmployees() {
		// TODO Auto-generated method stub
		/*
		 * List<Employee> emp= (List<Employee>) employeeRepository.findAll();
		 * 
		 * for(Employee e:emp) { System.out.println(e); Department d=
		 * departmentRepository.findById(e.getDepartment().getDepartmentId()).get();
		 * e.setDepartment(d); } return emp;
		 */
		
		return (List<Employee>) employeeRepository.findAll();
	}

	@Override
	public Employee saveEmployee(Employee emp) {
		// TODO Auto-generated method stub
		
		Department d = new Department(emp.getDepartment().getDepartmentId(), emp.getDepartment().getName());
		Department existDep = departmentRepository.save(d);
		
		System.out.println("existDep"+existDep);
		Employee e = new Employee(emp.getEmployeeId(), emp.getFirstName(), emp.getLastName(), emp.getEmail(), emp.getGender().toUpperCase(), existDep, emp.getSalary());
		if(!checkEmployeeExists(e)) {
			return employeeRepository.save(e);
		}
		return null;
	}

	@Override
	public Optional<Employee> updateEmployee(Employee emp) {
		// TODO Auto-generated method stub
		System.out.println("Employee for update "+emp.toString());
		Optional<Employee> employee = employeeRepository.findById(emp.getEmployeeId());
		System.out.println("Retrieved emp "+employee);
		if(employee.isPresent()) {
			System.out.println("updating to db");
			saveEmployee(emp);
		}
		
		return employee;
	}

	@Override
	public Optional<Employee> getEmployeeById(Long empId) {
		// TODO Auto-generated method stub
		return employeeRepository.findById(empId);
	}

	@Override
	public boolean deleteEmployeeById(Long id) {
		// TODO Auto-generated method stub
		boolean isEmpPresent = getEmployeeById(id).isPresent();
		if(isEmpPresent)
			employeeRepository.deleteById(id);
		return isEmpPresent;
		
	}

	@Override
	public List<Employee> getEmployeeByDepartmentId(Long departmentId) {
		// TODO Auto-generated method stub
		
		List<Employee> emp = employeeRepository.findByDepartmentId(departmentId);
		return emp;
	}

	@Override
	public List<Employee> getEmployeeByGender(String gender) {
		// TODO Auto-generated method stub
		List<Employee> emp = employeeRepository.findByGender(gender.toUpperCase());
		return emp;
	}

	@Override
	public List<Employee> getEmployeeByDepartmentIdWithMinimumSalary(Long departmentId) {
		// TODO Auto-generated method stub
		List<Employee> emp = employeeRepository.findByDepartmentIdWithMinSalary(departmentId);
		return emp;
	}

	
	private double calculateTax(double basicSalary, double allowance) {
		// TODO Auto-generated method stub
			double totalSalary = basicSalary+allowance;
			double tax = 0;
			if(totalSalary >= 15000)
				tax=0;
			if(totalSalary >= 30000)
				tax = tax+200;
			if(totalSalary >= 75000) {
				tax = tax+ (45000*(4.75/100));
			}
			if(totalSalary > 75000) {
				double exceedingAmount = totalSalary - 75000;
				double exceetax =exceedingAmount*0.07;
				tax = tax + exceetax;
			}
			System.out.println("tax amount = "+tax);
			return tax;
		
		
	}

	@Override
	public Object getTaxByEmployeeId(Long id) {
		// TODO Auto-generated method stub
		Optional<Employee> emp = getEmployeeById(id);
		System.out.println("");
		if(emp.isPresent()) {
			double tax=calculateTax(emp.get().getSalary().getBasicSalary(), emp.get().getSalary().getAllowance());
			Map<String,Object> hm = new HashMap<String,Object>();
			hm.put("employeeId",id );
			hm.put("totalSalary",emp.get().getSalary().getBasicSalary()+ emp.get().getSalary().getAllowance() );
			hm.put("taxAmount","$"+tax );
			return hm;
		}
		return null;
		
	}
	
	private boolean checkEmployeeExists(Employee e) {
		List<Employee> employees = listAllEmployees();
		for(Employee emp: employees) {
			if(emp.getEmail().equals(e.getEmail())) {
				return true;
			}
		}
		return false;
	}
	
	
}
