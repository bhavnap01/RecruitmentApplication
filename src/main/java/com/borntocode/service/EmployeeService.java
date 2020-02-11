package com.borntocode.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.borntocode.dao.interfaces.EmployeeDAOInterface;
import com.borntocode.pojo.Employee;
import com.borntocode.service.interfaces.EmployeeServiceInterface;

@Service
public class EmployeeService implements EmployeeServiceInterface {

	@Autowired
	private EmployeeDAOInterface employeeDAO;

	@Override
	public int insertEmployee(Employee employee) {
		return employeeDAO.insertEmployee(employee);
	}

	@Override
	public Employee updateEmployee(int employeeId, Employee Employee) {
		return employeeDAO.updateEmployee(employeeId, Employee);
	}

	@Override
	public boolean deleteEmployee(int employeeId) {
		return employeeDAO.deleteEmployee(employeeId);
	}

	@Override
	public Employee getEmployeeByEmployeeId(int employeeId) {
		return employeeDAO.getEmployeeByEmployeeId(employeeId);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeDAO.getAllEmployees();
	}

}
