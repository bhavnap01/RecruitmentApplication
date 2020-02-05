package com.borntocode.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.borntocode.dao.interfaces.EmployeeRoleDAOInterface;
import com.borntocode.pojo.EmployeeRole;
import com.borntocode.service.interfaces.EmployeeRoleServiceInterface;

@Service
public class EmployeeRoleService implements EmployeeRoleServiceInterface {

	@Autowired
	private EmployeeRoleDAOInterface employeeRoleDAO;
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	public EmployeeRoleService() {
		LOGGER.info("Object Creaated !!");
	}

	@Override
	public int insertEmployeeRole(EmployeeRole employeeRole) {
		return employeeRoleDAO.insertEmployeeRole(employeeRole);
	}

	@Override
	public EmployeeRole updateEmployeeRole(int roleId, EmployeeRole employeeRole) {
		return employeeRoleDAO.updateEmployeeRole(roleId, employeeRole);
	}

	@Override
	public boolean deleteEmployeeRole(int roleId) {
		return employeeRoleDAO.deleteEmployeeRole(roleId);
	}

	@Override
	public EmployeeRole getEmployeeRoleByRoleId(int roleId) {
		return employeeRoleDAO.getEmployeeRoleByRoleId(roleId);
	}

	@Override
	public List<EmployeeRole> getAllEmployeeRoles() {
		return employeeRoleDAO.getAllEmployeeRoles();
	}

}
