package com.lifefitness.EmployeeManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lifefitness.EmployeeManagement.bean.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long>{

	@Query("select e from Employee e where e.department.departmentId = ?1")
	List<Employee> findByDepartmentId(Long departmentId);
	
	@Query("select e from Employee e where e.gender = ?1")
	List<Employee> findByGender(String gender);
	
	@Query("select e from Employee e where e.department.departmentId = ?1 and e.salary.basicSalary = (select min(s.basicSalary) from Salary s)")
	List<Employee> findByDepartmentIdWithMinSalary(Long departmentId);
}
