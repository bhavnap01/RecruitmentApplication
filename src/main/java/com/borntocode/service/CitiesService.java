package com.borntocode.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.borntocode.dao.interfaces.CitiesDAOInterface;
import com.borntocode.pojo.Cities;
import com.borntocode.service.interfaces.CitiesServiceInterface;

@Service
public class CitiesService implements CitiesServiceInterface {
	
	@Autowired
	private CitiesDAOInterface citiesDAO;

	@Override
	public int insertCities(Cities city) {
		return citiesDAO.insertCities(city);
	}

	@Override
	public Cities updateCities(int citiesId, Cities city) {
		return citiesDAO.updateCities(citiesId, city);
	}

	@Override
	public boolean deleteCities(int citiesId) {
		return citiesDAO.deleteCities(citiesId);
	}

	@Override
	public Cities getCitiesByCitiesId(int citiesId) {
		return citiesDAO.getCitiesByCitiesId(citiesId);
	}

	@Override
	public List<Cities> getAllCities() {
		return citiesDAO.getAllCities();
	}

}
