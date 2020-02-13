package com.borntocode.service;

import java.util.List;

import com.borntocode.dao.interfaces.CountriesDAOInterface;
import com.borntocode.pojo.Address;
import com.borntocode.pojo.Countries;
import com.borntocode.service.interfaces.CountriesServiceInterface;

public class CountriesService implements CountriesServiceInterface {

	private CountriesDAOInterface countriesDAO;

	@Override
	public int insertCountry(Countries country) {
		return countriesDAO.insertCountry(country);
	}

	@Override
	public Countries updateCountry(int countryId, Countries country) {
		return countriesDAO.updateCountry(countryId, country);
	}

	@Override
	public boolean deleteCountry(int countryId) {
		return countriesDAO.deleteCountry(countryId);
	}

	@Override
	public Countries getCountryByCountryId(int countryId) {
		return countriesDAO.getCountryByCountryId(countryId);
	}

	@Override
	public List<Countries> getAllCountries() {
		return countriesDAO.getAllCountries();
	}

}
