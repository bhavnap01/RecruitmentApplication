package com.borntocode.service.interfaces;

import java.util.List;

import com.borntocode.pojo.Countries;

public interface CountriesServiceInterface {
	public int insertCountry(Countries country);

	public Countries updateCountry(int countryId, Countries country);

	public boolean deleteCountry(int countryId);

	public Countries getCountryByCountryId(int countryId);

	public List<Countries> getAllCountries();

}
