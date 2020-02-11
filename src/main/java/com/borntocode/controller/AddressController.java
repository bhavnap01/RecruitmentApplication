package com.borntocode.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.borntocode.pojo.Address;
import com.borntocode.service.AddressService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/addresses")
public class AddressController {
	@Autowired
	private AddressService addressService;
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "/address", method = RequestMethod.POST)
	public int addNewAddress(@RequestBody Address address) {
		LOGGER.info("addNewAddress");
		LOGGER.info(address.toString());
		return addressService.insertAddress(address);
	}

	@RequestMapping(value = "/{addressId}", method = RequestMethod.PUT)
	public Address updateAddress(@PathVariable("addressId") int addressId, @RequestBody Address address) {
		LOGGER.info("updateAddress");
		LOGGER.info("addressId :: " + addressId);
		LOGGER.info(address.toString());
		return addressService.updateAddress(addressId, address);
	}

	@RequestMapping(value = "/{addressId}", method = RequestMethod.DELETE)
	public boolean deleteAddress(@PathVariable("addressId") int addressId) {
		LOGGER.info("deleteAddress");
		LOGGER.info("addressId :: " + addressId);
		return addressService.deleteAddress(addressId);
	}

	@RequestMapping(value = "/getAllAddresss", method = RequestMethod.GET)
	public List<Address> getAllAddresss() {
		LOGGER.info("getAllAddresss");
		return addressService.getAllAddresss();
	}

	@RequestMapping(value = "/{addressId}", method = RequestMethod.GET)
	public Address getAddressById(@PathVariable("addressId") int addressId) {
		LOGGER.info("getAddressById");
		return addressService.getAddressByAddressId(addressId);
	}
}
