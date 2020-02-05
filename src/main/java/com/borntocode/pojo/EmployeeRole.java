package com.borntocode.pojo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class EmployeeRole {
	private int roleId;
	private String designation;

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	public EmployeeRole() {
		LOGGER.info("Default Construcotr Called !!");
	}

	public EmployeeRole(int roleId, String designation) {
		super();
		this.roleId = roleId;
		this.designation = designation;
		LOGGER.info("Param. Construcotr Called !!");
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	@Override
	public String toString() {
		return "EmployeeRole [roleId=" + roleId + ", designation=" + designation + "]";
	}

}
