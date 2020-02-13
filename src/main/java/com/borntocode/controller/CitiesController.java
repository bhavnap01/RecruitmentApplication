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

import com.borntocode.pojo.Cities;
import com.borntocode.pojo.States;
import com.borntocode.service.interfaces.CitiesServiceInterface;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/cities")
public class CitiesController {
	@Autowired
	private CitiesServiceInterface citiesService;
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "/city", method = RequestMethod.POST)
	public int addNewCity(@RequestBody Cities cities) {
		LOGGER.info("addNewState");
		LOGGER.info(cities.toString());
		return citiesService.insertCities(cities);
	}
	
	@RequestMapping(value = "/{cityId}", method = RequestMethod.PUT)
	public Cities updateCities(@PathVariable("cityId") int cityId, @RequestBody Cities cities) {
		LOGGER.info("updateCities");
		LOGGER.info("cityId :: " + cityId);
		LOGGER.info(cities.toString());
		return citiesService.updateCities(cityId, cities);
	}
	
	@RequestMapping(value = "/{cityId}", method = RequestMethod.DELETE)
	public boolean deleteCityes(@PathVariable("cityId") int cityId) {
		LOGGER.info("deleteCityes");
		LOGGER.info("cityId :: " + cityId);
		return citiesService.deleteCities(cityId);
	}
	
	@RequestMapping(value = "/getAllCities", method = RequestMethod.GET)
	public List<Cities> getAllCities() {
		LOGGER.info("getAllCities");
		return citiesService.getAllCities();
	}
	
	@RequestMapping(value = "/{cityId}", method = RequestMethod.GET)
	public Cities getCitisById(@PathVariable("cityId") int cityId) {
		LOGGER.info("getCitisById");
		return citiesService.getCitiesByCitiesId(cityId);
	}
	
}
