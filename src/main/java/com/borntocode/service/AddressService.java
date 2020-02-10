package com.borntocode.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.borntocode.dao.interfaces.AddressDAOInterface;
import com.borntocode.pojo.Address;
import com.borntocode.service.interfaces.AddressServiceInterface;

@Service
public class AddressService implements AddressServiceInterface {
	
	@Autowired
	private AddressDAOInterface addressDAO;

	@Override
	public int insertAddress(Address address) {
		return addressDAO.insertAddress(address);
	}

	@Override
	public Address updateAddress(int addressId, Address address) {
		return addressDAO.updateAddress(addressId, address);
	}

	@Override
	public boolean deleteAddress(int addressId) {
		return addressDAO.deleteAddress(addressId);
	}

	@Override
	public Address getAddressByAddressId(int addressId) {
		return addressDAO.getAddressByAddressId(addressId);
	}

	@Override
	public List<Address> getAllAddresss() {
		return addressDAO.getAllAddresss();
	}

}
