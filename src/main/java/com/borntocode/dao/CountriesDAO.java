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

import com.borntocode.dao.interfaces.AddressDAOInterface;
import com.borntocode.dao.interfaces.CountriesDAOInterface;
import com.borntocode.pojo.Countries;

@Repository
public class CountriesDAO implements CountriesDAOInterface {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private final Logger LOGGER = LoggerFactory.getLogger(AddressDAOInterface.class);
	private static String INSERT_COUNTRY_SQL = "insert into countries_master (shortname,name,phonecode) values(?,?,?)";
	private static String SELECT_ALL_COUNTRY_SQL = "select * from countries_master";
	private static String UPDATE_COUNTRY_SQL = "update countries_master set shortname=? , name = ? , phonecode = ?  where country_id=?";
	private static String DELETE_COUNTRY_SQL = "delete from countries_master where country_id=?";
	private static String SELECT_COUNTRY = "select * from countries_master where country_id=?";

	private int count;

	public CountriesDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int insertCountry(Countries country) {
		LOGGER.info("DAO :: insertCountry");
		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_COUNTRY_SQL,
						new String[] { "country_id" });
				preparedStatement.setString(1, country.getSortName());
				preparedStatement.setString(2, country.getName());
				preparedStatement.setInt(3, country.getPhoneCode());
				return preparedStatement;
			}
		}, keyHolder);
		LOGGER.info("Inserted Country :: CountryID :: " + keyHolder.getKey().intValue());
		return keyHolder.getKey().intValue();
	}

	@Override
	public Countries updateCountry(int countryId, Countries country) {
		LOGGER.info("DAO :: updateCountry ");
		LOGGER.info("countryId :: " + countryId);
		LOGGER.info(country.toString());

		Object[] args = { country.getSortName(), country.getName(), country.getPhoneCode(), countryId };

		jdbcTemplate.update(UPDATE_COUNTRY_SQL, args);

		return getCountryByCountryId(countryId);
	}

	@Override
	public boolean deleteCountry(int countryId) {
		LOGGER.info("DAO :: deleteCountry");
		LOGGER.info("countryId :: " + countryId);
		Object[] args = { countryId };
		count = jdbcTemplate.update(DELETE_COUNTRY_SQL, args);
		if (count > 0)
			return true;
		else
			return false;
	}

	@Override
	public Countries getCountryByCountryId(int countryId) {
		LOGGER.info("DAO :: getCountryByCountryId");
		try {
			Countries country = jdbcTemplate.queryForObject(SELECT_COUNTRY, new Object[] { countryId },
					new CountryRowMapper());
			if (country != null) {
				LOGGER.info("Values From DB");
				LOGGER.info(country.toString());
				return country;
			}
		} catch (Exception e) {
			LOGGER.info("EXCEPTION");
			LOGGER.info(e.getMessage());
		}
		return new Countries();
	}

	@Override
	public List<Countries> getAllCountries() {

		LOGGER.info("DAO :: getAllCountries");
		List<Countries> countryList = new ArrayList<Countries>();

		List<Map<String, Object>> rows = jdbcTemplate.queryForList(SELECT_ALL_COUNTRY_SQL);

		for (Map<String, Object> map : rows) {
			Countries country = new Countries();
			country.setCountryId(Integer.valueOf(map.get("country_id").toString()));
			country.setSortName(map.get("shorname").toString());
			country.setName(map.get("name").toString());
			country.setPhoneCode(Integer.valueOf(map.get("phonecode").toString()));
			LOGGER.info(country.toString());
			countryList.add(country);
		}
		return countryList;
	}

	class CountryRowMapper implements RowMapper<Countries> {
		@Override
		public Countries mapRow(ResultSet rs, int rowNum) throws SQLException {
			Countries country = new Countries();
			country.setCountryId(rs.getInt("country_id"));
			country.setSortName(rs.getString("sortname"));
			country.setName(rs.getString("name"));
			country.setPhoneCode(rs.getInt("phonecode"));
			return country;
		}

	}

}
