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

import com.borntocode.dao.interfaces.AddressDAOInterface;
import com.borntocode.dao.interfaces.CitiesDAOInterface;
import com.borntocode.pojo.Cities;
import com.borntocode.pojo.States;

public class CitiesDAO implements CitiesDAOInterface {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private CountriesDAO countriesDAO;

	@Autowired
	private StatesDAO statesDAO;

	private final Logger LOGGER = LoggerFactory.getLogger(AddressDAOInterface.class);
	private static String INSERT_CITIES_SQL = "insert into cities_master (name,state_id) values(?,?)";
	private static String SELECT_ALL_CITIES_SQL = "select * from cities_master";
	private static String UPDATE_CITIES_SQL = "update cities_master set name=? , state_id = ? where city_id=?";
	private static String DELETE_CITIES_SQL = "delete from cities_master where city_id=?";
	private static String SELECT_CITIES = "select * from cities_master where city_id=?";

	private int count;

	@Override
	public int insertCities(Cities city) {
		LOGGER.info("DAO :: insertStates");
		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CITIES_SQL,
						new String[] { "city_id" });

				preparedStatement.setString(1, city.getName());
				preparedStatement.setInt(2, city.getState().getStateId());
				return preparedStatement;
			}
		}, keyHolder);
		LOGGER.info("Inserted City :: cityID :: " + keyHolder.getKey().intValue());
		return keyHolder.getKey().intValue();
	}

	@Override
	public Cities updateCities(int citiesId, Cities city) {
		LOGGER.info("DAO :: updateCities ");
		LOGGER.info("citiesId :: " + citiesId);
		LOGGER.info(city.toString());

		Object[] args = { city.getName(), city.getState().getStateId() };

		jdbcTemplate.update(UPDATE_CITIES_SQL, args);

		return getCitiesByCitiesId(citiesId);
	}

	@Override
	public boolean deleteCities(int citiesId) {
		LOGGER.info("DAO :: deleteCities");
		LOGGER.info("citiesId :: " + citiesId);
		Object[] args = { citiesId };
		count = jdbcTemplate.update(DELETE_CITIES_SQL, args);
		if (count > 0)
			return true;
		else
			return false;
	}

	@Override
	public Cities getCitiesByCitiesId(int citiesId) {
		LOGGER.info("DAO :: getCitiesByCitiesId");
		try {
			Cities cities = jdbcTemplate.queryForObject(SELECT_CITIES, new Object[] { citiesId },
					new CitiesRowMapper());
			if (cities != null) {
				LOGGER.info("Values From DB");
				LOGGER.info(cities.toString());
				return cities;
			}
		} catch (Exception e) {
			LOGGER.info("EXCEPTION");
			LOGGER.info(e.getMessage());
		}
		return new Cities();
	}

	@Override
	public List<Cities> getAllCities() {
		LOGGER.info("DAO :: getAllCities");
		List<Cities> cityesList = new ArrayList<Cities>();

		List<Map<String, Object>> rows = jdbcTemplate.queryForList(SELECT_ALL_CITIES_SQL);

		for (Map<String, Object> map : rows) {
			Cities cities = new Cities();
			cities.setCityId(Integer.valueOf(map.get("city_id").toString()));
			cities.setName(map.get("name").toString());
			States states = statesDAO.getStatesByStatesId(Integer.valueOf(map.get("state_id").toString()));
			cities.setState(states);
			LOGGER.info(states.toString());
			cityesList.add(cities);
		}
		return cityesList;
	}

	class CitiesRowMapper implements RowMapper<Cities> {

		@Override
		public Cities mapRow(ResultSet rs, int rowNum) throws SQLException {
			Cities cities = new Cities();
			cities.setCityId(rs.getInt("city_id"));
			cities.setName(rs.getString("name"));
			States states = statesDAO.getStatesByStatesId(rs.getInt("state_id"));
			cities.setState(states);
			return cities;
		}

	}
}
