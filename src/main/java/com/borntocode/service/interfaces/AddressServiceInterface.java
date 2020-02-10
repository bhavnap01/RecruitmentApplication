package com.borntocode.service.interfaces;

import java.util.List;

import com.borntocode.pojo.Address;

public interface AddressServiceInterface {

	public int insertAddress(Address Address);

	public Address updateAddress(int addressId, Address address);

	public boolean deleteAddress(int addressId);

	public Address getAddressByAddressId(int addressId);

	public List<Address> getAllAddresss();
}
