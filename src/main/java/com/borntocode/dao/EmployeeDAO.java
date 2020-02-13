package com.borntocode.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.borntocode.dao.interfaces.EmployeeDAOInterface;
import com.borntocode.dao.interfaces.EmployeeRoleDAOInterface;
import com.borntocode.pojo.Address;
import com.borntocode.pojo.Employee;
import com.borntocode.pojo.EmployeeRole;
import com.borntocode.pojo.ProjectDetails;

@Repository
public class EmployeeDAO implements EmployeeDAOInterface {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private AddressDAO addressDAO;
	@Autowired
	private EmployeeRoleDAO employeeRoleDAO;
	@Autowired
	private ProjectDetailsDAO projectDetailsDAO;

	private final Logger LOGGER = LoggerFactory.getLogger(EmployeeRoleDAOInterface.class);
	private static String INSERT_EMPLOYEE_SQL = "insert into employee_master (first_name,last_name,address_id,project_id,role_id,email,password,active,salary) values(?,?,?,?,?,?,?,?,?)";
	private static String SELECT_ALL_EMPLOYEE_SQL = "select * from employee_master";
	private static String UPDATE_EMPLOYEE_SQL = "update employee_master set first_name=?,last_name=?,email=?,password=?,active=?,salary=?  where employee_id=?";
	private static String DELETE_EMPLOYEE_SQL = "delete from employee_master where employee_id=?";
	private static String SELECT_EMPLOYEE = "select * from employee_master where employee_id=?";

	private int count;

	@Override
	public int insertEmployee(Employee employee) {
		LOGGER.info("DAO :: InsertEmployeeRole");
		LOGGER.info(employee.toString());
		KeyHolder keyHolder = new GeneratedKeyHolder();

		int addressId = addressDAO.insertAddress(employee.getAddress());
		LOGGER.info("AddressId :: " + addressId);
		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMPLOYEE_SQL,
						new String[] { "employee_id" });
				preparedStatement.setString(1, employee.getFirstName());
				preparedStatement.setString(2, employee.getLastName());
				preparedStatement.setInt(3, addressId);
				preparedStatement.setInt(4, employee.getProjectDetails().getProjectId());
				preparedStatement.setInt(5, employee.getEmployeeRole().getRoleId());
				preparedStatement.setString(6, employee.getEmail());
				preparedStatement.setString(7, employee.getPassword());
				if (employee.isActive())
					preparedStatement.setBoolean(8, true);
				else
					preparedStatement.setBoolean(8, false);
				preparedStatement.setDouble(9, employee.getSalary());
				return preparedStatement;
			}
		}, keyHolder);
		LOGGER.info("Inserted EmployeeRole :: RoleId :: " + keyHolder.getKey().intValue());
		return keyHolder.getKey().intValue();
	}

	@Override
	public Employee updateEmployee(int employeeId, Employee Employee) {
		return null;
	}

	@Override
	public boolean deleteEmployee(int employeeId) {
		return false;
	}

	@Override
	public Employee getEmployeeByEmployeeId(int employeeId) {
		return null;
	}

	@Override
	public List<Employee> getAllEmployees() {
		LOGGER.info("DAO :: getAllEmployees");
		List<Employee> employeeList = new ArrayList<Employee>();

		List<Map<String, Object>> rows = jdbcTemplate.queryForList(SELECT_ALL_EMPLOYEE_SQL);

		for (Map<String, Object> map : rows) {
			Employee employee = new Employee();

			employee.setEmployeeId(Integer.valueOf(map.get("employee_id").toString()));
			employee.setFirstName(map.get("first_name").toString());
			employee.setLastName(map.get("last_name").toString());

			Address address = addressDAO.getAddressByAddressId(Integer.valueOf(map.get("address_id").toString()));
			LOGGER.info(address.toString());
			employee.setAddress(address);

			ProjectDetails projectDetails = projectDetailsDAO
					.getProjectDetailsById(Integer.valueOf(map.get("project_id").toString()));
			LOGGER.info(projectDetails.toString());
			employee.setProjectDetails(projectDetails);

			EmployeeRole employeeRole = employeeRoleDAO
					.getEmployeeRoleByRoleId(Integer.valueOf(map.get("role_id").toString()));
			LOGGER.info(employeeRole.toString());
			employee.setEmployeeRole(employeeRole);

			employee.setEmail(map.get("email").toString());
			employee.setPassword(map.get("password").toString());
			if (map.get("active").toString().equals("0"))
				employee.setActive(false);
			else
				employee.setActive(true);
			employee.setSalary(Double.valueOf(map.get("salary").toString()));

			employeeList.add(employee);
		}
		return employeeList;
	}

}
