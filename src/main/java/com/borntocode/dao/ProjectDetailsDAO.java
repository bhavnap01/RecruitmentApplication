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
import com.borntocode.dao.interfaces.ProjectDetailsDAOInterface;
import com.borntocode.pojo.ProjectDetails;

@Repository
public class ProjectDetailsDAO implements ProjectDetailsDAOInterface {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private final Logger LOGGER = LoggerFactory.getLogger(EmployeeRoleDAOInterface.class);
	private static String INSERT_PROJECT_DETAILS_SQL = "insert into project_master (name,total_budget) values(?,?)";
	private static String SELECT_ALL_PROJECT_DETAILS_SQL = "select * from project_master";
	private static String UPDATE_PROJECT_DETAILS_SQL = "update project_master set name=? , total_budget = ?  where project_id=?";
	private static String DELETE_PROJECT_DETAILS_SQL = "delete from project_master where project_id=?";
	private static String SELECT_PROJECT_DETAILS = "select * from project_master where project_id=?";

	private int count;

	@Override
	public int insertProjectDetails(ProjectDetails projectDetails) {
		LOGGER.info("DAO :: insertProjectDetails");
		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PROJECT_DETAILS_SQL,
						new String[] { "role_id" });
				preparedStatement.setString(1, projectDetails.getName());
				preparedStatement.setDouble(2, projectDetails.getTotalBudget());
				return preparedStatement;
			}
		}, keyHolder);
		LOGGER.info("Inserted ProjectDetails :: ProjectId :: " + keyHolder.getKey().intValue());
		return keyHolder.getKey().intValue();
	}

	@Override
	public ProjectDetails updateProjectDetails(int projectId, ProjectDetails projectDetails) {
		LOGGER.info("DAO :: updateProjectDetails");
		LOGGER.info("projectId :: " + projectId);
		LOGGER.info(projectDetails.toString());

		Object[] args = { projectDetails.getName(), projectDetails.getTotalBudget(), projectId };
		jdbcTemplate.update(UPDATE_PROJECT_DETAILS_SQL, args);

		return getProjectDetailsById(projectId);
	}

	@Override
	public boolean deleteProjectDetails(int projectId) {
		LOGGER.info("DAO :: deleteProjectDetails");
		LOGGER.info("projectId :: " + projectId);
		Object[] args = { projectId };
		count = jdbcTemplate.update(DELETE_PROJECT_DETAILS_SQL, args);
		if (count > 0)
			return true;
		else
			return false;
	}

	@Override
	public ProjectDetails getProjectDetailsById(int projectId) {
		LOGGER.info("DAO :: getProjectDetailsById");
		try {
			ProjectDetails projectDetails = jdbcTemplate.queryForObject(SELECT_PROJECT_DETAILS,
					new Object[] { projectId }, new ProjectDetailsRowMapper());
			if (projectDetails != null) {
				LOGGER.info("Values From DB");
				LOGGER.info(projectDetails.toString());
				return projectDetails;
			}
		} catch (Exception e) {
			LOGGER.info("EXCEPTION");
			LOGGER.info(e.getMessage());
		}
		return new ProjectDetails();
	}

	@Override
	public List<ProjectDetails> getAllProjectDetails() {
		LOGGER.info("DAO :: getAllProjectDetails");
		List<ProjectDetails> projectDetailsList = new ArrayList<ProjectDetails>();

		List<Map<String, Object>> rows = jdbcTemplate.queryForList(SELECT_ALL_PROJECT_DETAILS_SQL);

		for (Map<String, Object> map : rows) {
			ProjectDetails projectDetails = new ProjectDetails();
			projectDetails.setProjectId(Integer.valueOf(map.get("project_id").toString()));
			projectDetails.setName(map.get("name").toString());
			projectDetails.setTotalBudget(Double.valueOf(map.get("total_budget").toString()));
			LOGGER.info(projectDetails.toString());
			projectDetailsList.add(projectDetails);
		}
		return projectDetailsList;
	}

	class ProjectDetailsRowMapper implements RowMapper<ProjectDetails> {
		@Override
		public ProjectDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
			ProjectDetails projectDetails = new ProjectDetails();
			projectDetails.setProjectId(rs.getInt("project_id"));
			projectDetails.setName(rs.getString("name"));
			projectDetails.setTotalBudget(rs.getDouble("total_budget"));
			return projectDetails;
		}

	}
}
