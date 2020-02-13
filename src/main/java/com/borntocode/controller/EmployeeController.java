package com.borntocode.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.borntocode.pojo.Employee;
import com.borntocode.service.EmployeeService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "/employee", method = RequestMethod.POST)
	public int addNewEmployee(@RequestBody Employee employee) {
		LOGGER.info("addNewEmployee");
		LOGGER.info(employee.toString());
		return employeeService.insertEmployee(employee);
	}

	@RequestMapping(value = "/getAllEmployees", method = RequestMethod.GET)
	public List<Employee> getAllEmployees() {
		LOGGER.info("getAllEmployees");
		return employeeService.getAllEmployees();
	}

}
