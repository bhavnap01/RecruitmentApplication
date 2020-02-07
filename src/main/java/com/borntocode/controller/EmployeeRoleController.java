package com.borntocode.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.borntocode.pojo.EmployeeRole;
import com.borntocode.service.EmployeeRoleService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/roles")
public class EmployeeRoleController {

	@Autowired
	private EmployeeRoleService employeeRoleService;
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "/role", method = RequestMethod.POST)
	public int addNewEmployeeRole(@RequestBody EmployeeRole employeeRole) {
		LOGGER.info("addNewEmployeeRole");
		LOGGER.info(employeeRole.toString());
		return employeeRoleService.insertEmployeeRole(employeeRole);
	}

	@RequestMapping(value = "/{roleId}", method = RequestMethod.PUT)
	public EmployeeRole updateEmployeeRole(@PathVariable("roleId") int roleId, @RequestBody EmployeeRole employeeRole) {
		LOGGER.info("updateEmployeeRole");
		LOGGER.info("RoleId :: " + roleId);
		LOGGER.info(employeeRole.toString());
		return employeeRoleService.updateEmployeeRole(roleId, employeeRole);
	}

	@RequestMapping(value = "/{roleId}", method = RequestMethod.DELETE)
	public boolean deleteEmployeeRole(@PathVariable("roleId") int roleId) {
		LOGGER.info("deleteEmployeeRole");
		LOGGER.info("RoleId :: " + roleId);
		return employeeRoleService.deleteEmployeeRole(roleId);
	}

	@RequestMapping(value = "/getAllRoles", method = RequestMethod.GET)
	public List<EmployeeRole> getAllEmployeeRoles() {
		LOGGER.info("getAllEmployeeRoles");
		return employeeRoleService.getAllEmployeeRoles();
	}
	
	@RequestMapping(value = "/{roleId}", method = RequestMethod.GET)
	public EmployeeRole getEmployeeRoleById(@PathVariable("roleId") int roleId)
	{
		LOGGER.info("getEmployeeRoleById");
		return employeeRoleService.getEmployeeRoleByRoleId(roleId);
	}
	
}
