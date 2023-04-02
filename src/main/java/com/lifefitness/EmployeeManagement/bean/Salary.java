package com.lifefitness.EmployeeManagement.bean;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="salary")
public class Salary {
	@Id
	//@Column(name="employee_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long employeeId;
	private double basicSalary;
	private double allowance;
	@JsonIgnore
	@OneToOne(cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private Employee employee;
	public Salary() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Salary(Long employeeId, double basicSalary, double allowance) {
		super();
		this.employeeId = employeeId;
		this.basicSalary = basicSalary;
		this.allowance = allowance;
	}
	public Salary(Long employeeId, double basicSalary, double allowance, Employee employee) {
		super();
		this.employeeId = employeeId;
		this.basicSalary = basicSalary;
		this.allowance = allowance;
		this.employee = employee;
	}
	public Long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}
	public double getBasicSalary() {
		return basicSalary;
	}
	public void setBasicSalary(double basicSalary) {
		this.basicSalary = basicSalary;
	}
	public double getAllowance() {
		return allowance;
	}
	public void setAllowance(double allowance) {
		this.allowance = allowance;
	}
	@Override
	public String toString() {
		return "Salary [employeeId=" + employeeId + ", basicSalary=" + basicSalary + ", allowance=" + allowance + "]";
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	
}
