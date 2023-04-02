package com.lifefitness.EmployeeManagement.repository;

import java.math.BigInteger;

import org.springframework.data.repository.CrudRepository;

import com.lifefitness.EmployeeManagement.bean.Department;

public interface DepartmentRepository extends CrudRepository<Department, BigInteger> {

}
