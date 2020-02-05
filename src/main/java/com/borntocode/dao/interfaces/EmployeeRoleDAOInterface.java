package com.borntocode.dao.interfaces;

import java.util.List;

import com.borntocode.pojo.EmployeeRole;

public interface EmployeeRoleDAOInterface {

	public int insertEmployeeRole(EmployeeRole employeeRole);

	public EmployeeRole updateEmployeeRole(int roleId, EmployeeRole employeeRole);

	public boolean deleteEmployeeRole(int roleId);

	public EmployeeRole getEmployeeRoleByRoleId(int roleId);

	public List<EmployeeRole> getAllEmployeeRoles();

}
