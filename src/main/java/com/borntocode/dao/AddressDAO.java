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
import com.borntocode.pojo.Address;

@Repository
public class AddressDAO implements AddressDAOInterface {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private final Logger LOGGER = LoggerFactory.getLogger(AddressDAOInterface.class);
	private static String INSERT_ADDRESS_SQL = "insert into address_master (address_line_1,address_line_2,city,state,country) values(?,?,?,?,?)";
	private static String SELECT_ALL_ADDRESS_SQL = "select * from address_master";
	private static String UPDATE_ADDRESS_SQL = "update address_master set address_line_1=? , address_line_2 = ? , city = ? , state = ? , country = ? where address_id=?";
	private static String DELETE_ADDRESS_SQL = "delete from address_master where address_id=?";
	private static String SELECT_ADDRESS = "select * from address_master where address_id=?";

	private int count;

	public AddressDAO() {
		LOGGER.info("Object Created !!");
	}

	@Override
	public int insertAddress(Address address) {
		LOGGER.info("DAO :: InsertAddress");
		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ADDRESS_SQL,
						new String[] { "address_id" });
				preparedStatement.setString(1, address.getAddressLineOne());
				preparedStatement.setString(2, address.getAddressLineTwo());
				preparedStatement.setString(3, address.getCity());
				preparedStatement.setString(4, address.getState());
				preparedStatement.setString(5, address.getCountry());
				return preparedStatement;
			}
		}, keyHolder);
		LOGGER.info("Inserted Address :: AddressId :: " + keyHolder.getKey().intValue());
		return keyHolder.getKey().intValue();
	}

	@Override
	public Address updateAddress(int addressId, Address address) {
		LOGGER.info("DAO :: updateAddress");
		LOGGER.info("addressId :: " + addressId);
		LOGGER.info(address.toString());

		Object[] args = { address.getAddressLineOne(), address.getAddressLineTwo(), address.getCity(),
				address.getState(), address.getCountry(), address.getAddressId() };
		jdbcTemplate.update(UPDATE_ADDRESS_SQL, args);

		return getAddressByAddressId(addressId);
	}

	@Override
	public boolean deleteAddress(int addressId) {
		LOGGER.info("DAO :: deleteAddress");
		LOGGER.info("addressId :: " + addressId);
		Object[] args = { addressId };
		count = jdbcTemplate.update(DELETE_ADDRESS_SQL, args);
		if (count > 0)
			return true;
		else
			return false;
	}

	@Override
	public Address getAddressByAddressId(int addressId) {
		LOGGER.info("DAO :: getAddressByRoleId");
		try {
			Address Address = jdbcTemplate.queryForObject(SELECT_ADDRESS, new Object[] { addressId },
					new AddressRowMapper());
			if (Address != null) {
				LOGGER.info("Values From DB");
				LOGGER.info(Address.toString());
				return Address;
			}
		} catch (Exception e) {
			LOGGER.info("EXCEPTION");
			LOGGER.info(e.getMessage());
		}
		return new Address();
	}

	@Override
	public List<Address> getAllAddresss() {
		LOGGER.info("DAO :: getAllAddresss");
		List<Address> AddressList = new ArrayList<Address>();

		List<Map<String, Object>> rows = jdbcTemplate.queryForList(SELECT_ALL_ADDRESS_SQL);

		for (Map<String, Object> map : rows) {
			Address address = new Address();
			address.setAddressId(Integer.valueOf(map.get("address_id").toString()));
			address.setAddressLineOne(map.get("address_line_1").toString());
			address.setAddressLineTwo(map.get("address_line_2").toString());
			address.setCity(map.get("city").toString());
			address.setState(map.get("state").toString());
			address.setCountry(map.get("country").toString());
			LOGGER.info(address.toString());
			AddressList.add(address);
		}
		return AddressList;
	}

	class AddressRowMapper implements RowMapper<Address> {
		@Override
		public Address mapRow(ResultSet rs, int rowNum) throws SQLException {
			Address address = new Address();
			address.setAddressId(rs.getInt("address_id"));
			address.setAddressLineOne(rs.getString("address_line_1"));
			address.setAddressLineTwo(rs.getString("address_line_2"));
			address.setCity(rs.getString("city"));
			address.setState(rs.getString("state"));
			address.setCountry(rs.getString("country"));
			return address;
		}

	}

}
