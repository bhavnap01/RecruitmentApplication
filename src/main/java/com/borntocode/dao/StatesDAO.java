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

import com.borntocode.dao.CountriesDAO.CountryRowMapper;
import com.borntocode.dao.interfaces.AddressDAOInterface;
import com.borntocode.dao.interfaces.StatesDAOInterface;
import com.borntocode.pojo.Countries;
import com.borntocode.pojo.States;

@Repository
public class StatesDAO implements StatesDAOInterface {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private CountriesDAO countriesDAO;

	private final Logger LOGGER = LoggerFactory.getLogger(AddressDAOInterface.class);
	private static String INSERT_STATES_SQL = "insert into states_master (name,country_id) values(?,?)";
	private static String SELECT_ALL_STATES_SQL = "select * from states_master";
	private static String UPDATE_STATES_SQL = "update states_master set name=? , country_id = ? where state_id=?";
	private static String DELETE_STATES_SQL = "delete from states_master where state_id=?";
	private static String SELECT_STATES = "select * from states_master where state_id=?";

	private int count;

	@Override
	public int insertStates(States states) {
		LOGGER.info("DAO :: insertStates");
		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STATES_SQL,
						new String[] { "state_id" });

				preparedStatement.setString(1, states.getName());
				preparedStatement.setInt(2, states.getCountry().getCountryId());
				return preparedStatement;
			}
		}, keyHolder);
		LOGGER.info("Inserted State :: StateID :: " + keyHolder.getKey().intValue());
		return keyHolder.getKey().intValue();
	}

	@Override
	public States updateStates(int statesId, States states) {
		LOGGER.info("DAO :: updateStates ");
		LOGGER.info("stateId :: " + statesId);
		LOGGER.info(states.toString());

		Object[] args = { states.getName(), states.getCountry().getCountryId() };

		jdbcTemplate.update(UPDATE_STATES_SQL, args);

		return getStatesByStatesId(statesId);
	}

	@Override
	public boolean deleteStates(int statesId) {
		LOGGER.info("DAO :: deleteStates");
		LOGGER.info("countryId :: " + statesId);
		Object[] args = { statesId };
		count = jdbcTemplate.update(DELETE_STATES_SQL, args);
		if (count > 0)
			return true;
		else
			return false;
	}

	@Override
	public States getStatesByStatesId(int statesId) {
		LOGGER.info("DAO :: getCountryByStatesId");
		try {
			States states = jdbcTemplate.queryForObject(SELECT_STATES, new Object[] { statesId }, new StateRowMapper());
			if (states != null) {
				LOGGER.info("Values From DB");
				LOGGER.info(states.toString());
				return states;
			}
		} catch (Exception e) {
			LOGGER.info("EXCEPTION");
			LOGGER.info(e.getMessage());
		}
		return new States();
	}

	@Override
	public List<States> getAllStates() {
		LOGGER.info("DAO :: getAllStates");
		List<States> statesList = new ArrayList<States>();

		List<Map<String, Object>> rows = jdbcTemplate.queryForList(SELECT_ALL_STATES_SQL);

		for (Map<String, Object> map : rows) {
			States states = new States();
			states.setStateId(Integer.valueOf(map.get("state_id").toString()));
			states.setName(map.get("name").toString());
			Countries countries = countriesDAO.getCountryByCountryId(Integer.valueOf(map.get("country_id").toString()));
			states.setCountry(countries);
			LOGGER.info(states.toString());
			statesList.add(states);
		}
		return statesList;
	}

	class StateRowMapper implements RowMapper<States> {

		@Override
		public States mapRow(ResultSet rs, int rowNum) throws SQLException {
			States state = new States();
			state.setStateId(rs.getInt("state_id"));
			state.setName(rs.getString("name"));
			Countries countries = countriesDAO.getCountryByCountryId(rs.getInt("country_id"));
			state.setCountry(countries);
			return state;
		}

	}

}
