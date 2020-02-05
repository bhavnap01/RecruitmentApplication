package com.borntocode.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.borntocode.dao.interfaces.EmployeeRoleDAOInterface;
import com.borntocode.pojo.EmployeeRole;

@Repository
public class EmployeeRoleDAO implements EmployeeRoleDAOInterface {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private final Logger LOGGER = LoggerFactory.getLogger(EmployeeRoleDAOInterface.class);
	private static String INSERT_EMPLOYEE_ROLE_SQL = "insert into employee_role_master (designation) values(?)";
	private static String SELECT_ALL_EMPLOYEE_ROLES_SQL = "select * from employee_role_master";
	private static String UPDATE_EMPLOYEE_ROLE_SQL = "update employee_role set designation=? where role_id=?";
	private static String DELETE_EMPLOYEE_ROLE_SQL = "delete from employee_role where role_id=?";

	private int count;

	public EmployeeRoleDAO() {
		LOGGER.info("Object Created !!");
	}

	@Override
	public int insertEmployeeRole(EmployeeRole employeeRole) {
		LOGGER.info("DAO :: InsertEmployeeRole");
		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMPLOYEE_ROLE_SQL,
						new String[] { "role_id" });
				preparedStatement.setString(1, employeeRole.getDesignation());
				return preparedStatement;
			}
		}, keyHolder);
		LOGGER.info("Inserted EmployeeRole :: RoleId :: " + keyHolder.getKey().intValue());
		return keyHolder.getKey().intValue();
	}

	@Override
	public EmployeeRole updateEmployeeRole(int roleId, EmployeeRole employeeRole) {
		LOGGER.info("DAO :: updateEmployeeRole");
		LOGGER.info("roleId :: " + roleId);
		LOGGER.info(employeeRole.toString());

		Object[] args = { employeeRole.getDesignation() };
		jdbcTemplate.update(UPDATE_EMPLOYEE_ROLE_SQL, args);
		return getEmployeeRoleByRoleId(roleId);
	}

	@Override
	public boolean deleteEmployeeRole(int roleId) {
		LOGGER.info("DAO :: deleteEmployeeRole");
		LOGGER.info("roleId :: " + roleId);
		Object[] args = { roleId };
		count = jdbcTemplate.update(DELETE_EMPLOYEE_ROLE_SQL, args);
		if (count > 0)
			return true;
		else
			return false;
	}

	@Override
	public EmployeeRole getEmployeeRoleByRoleId(int roleId) {
		LOGGER.info("DAO :: getEmployeeRoleByRoleId");
		try {
			EmployeeRole employeeRole = jdbcTemplate.queryForObject(UPDATE_EMPLOYEE_ROLE_SQL, new Object[] { roleId },
					new EmployeeRoleRowMapper());
			if (employeeRole != null) {
				LOGGER.info("Values From DB");
				LOGGER.info(employeeRole.toString());
				return employeeRole;
			}
		} catch (Exception e) {
			LOGGER.info("EXCEPTION");
			LOGGER.info(e.getMessage());
		}
		return new EmployeeRole();

	}

	@Override
	public List<EmployeeRole> getAllEmployeeRoles() {
		LOGGER.info("DAO :: getAllEmployeeRoles");
		List<EmployeeRole> employeeRoleList = new ArrayList<EmployeeRole>();

		List<Map<String, Object>> rows = jdbcTemplate.queryForList(SELECT_ALL_EMPLOYEE_ROLES_SQL);

		for (Map<String, Object> map : rows) {
			EmployeeRole employeeRole = new EmployeeRole();
			employeeRole.setRoleId(Integer.parseInt(map.get("role_id").toString()));
			employeeRole.setDesignation(map.get("designation").toString());
			LOGGER.info(employeeRole.toString());
			employeeRoleList.add(employeeRole);
		}
		return employeeRoleList;
	}

	class EmployeeRoleRowMapper implements RowMapper<EmployeeRole> {
		@Override
		public EmployeeRole mapRow(ResultSet rs, int rowNum) throws SQLException {
			EmployeeRole employeeRole = new EmployeeRole();
			employeeRole.setRoleId(rs.getInt("role_id"));
			employeeRole.setDesignation(rs.getString("designation"));
			return employeeRole;
		}

	}

}
