package com.borntocode.dao.interfaces;

import java.util.List;

import com.borntocode.pojo.Countries;

public interface CountriesDAOInterface {
	public int insertCountry(Countries country);

	public Countries updateCountry(int countryId, Countries country);

	public boolean deleteCountry(int countryId);

	public Countries getCountryByCountryId(int countryId);

	public List<Countries> getAllCountries();
}
