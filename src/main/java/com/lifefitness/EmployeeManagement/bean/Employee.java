package com.lifefitness.EmployeeManagement.bean;

import java.math.BigInteger;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="employee")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long employeeId;
	private String firstName;
	private String lastName;
	private String email;
	private String gender;
	
	

	
	
	@ManyToOne(cascade = CascadeType.ALL)
	Department department;
	 

	@OneToOne(mappedBy = "employee",cascade = CascadeType.ALL)
	private Salary salary;
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Employee(Long employeeId, String firstName, String lastName, String email, String gender,
			 Department department, Salary salary) {
		super();
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.gender = gender;
		this.department = department;
		this.salary = salary;
	}
	public Long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public Salary getSalary() {
		return salary;
	}
	public void setSalary(Salary salary) {
		this.salary = salary;
	}
	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", firstName=" + firstName + ", lastName=" + lastName + ", email="
				+ email + ", gender=" + gender + ", department=" + department
				+ ", salary=" + salary + "]";
	}
	
	
	
	
	
	
	
}
