package com.borntocode.service.interfaces;

import java.util.List;

import com.borntocode.pojo.Employee;

public interface EmployeeServiceInterface {
	public int insertEmployee(Employee employee);

	public Employee updateEmployee(int employeeId, Employee Employee);

	public boolean deleteEmployee(int employeeId);

	public Employee getEmployeeByEmployeeId(int employeeId);

	public List<Employee> getAllEmployees();
}
