package com.lifefitness.EmployeeManagement.bean;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="department")
public class Department {
	
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long departmentId;
	private String name;
	
	@JsonIgnore
	//@OneToOne(mappedBy = "department",cascade = CascadeType.ALL)
	//@OneToMany(fetch = FetchType.LAZY)
	@OneToMany
    @JoinColumn(name = "departmentId", referencedColumnName = "departmentId")
	private List<Employee> employee;
	public Department() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Department(Long departmentId, String name) {
		super();
		this.departmentId = departmentId;
		this.name = name;
	}
	public Department(Long departmentId, String name, List<Employee> employee) {
		super();
		this.departmentId = departmentId;
		this.name = name;
		this.employee = employee;
	}
	public Long getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Department [departmentId=" + departmentId + ", name=" + name + "]";
	}
	public List<Employee> getEmployee() {
		return employee;
	}
	public void setEmployee(List<Employee> employee) {
		this.employee = employee;
	}
	
	
}
