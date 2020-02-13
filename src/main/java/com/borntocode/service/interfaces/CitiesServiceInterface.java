package com.borntocode.service.interfaces;

import java.util.List;

import com.borntocode.pojo.Cities;

public interface CitiesServiceInterface {
	public int insertCities(Cities city);

	public Cities updateCities(int citiesId, Cities city);

	public boolean deleteCities(int citiesId);

	public Cities getCitiesByCitiesId(int citiesId);

	public List<Cities> getAllCities();
}
