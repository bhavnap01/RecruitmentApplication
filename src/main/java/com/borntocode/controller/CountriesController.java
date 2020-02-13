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

import com.borntocode.pojo.Countries;
import com.borntocode.service.interfaces.CountriesServiceInterface;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/contries")
public class CountriesController {

	@Autowired
	private CountriesServiceInterface countriesService;
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "/Countries", method = RequestMethod.POST)
	public int addNewCountries(@RequestBody Countries countries) {
		LOGGER.info("addNewCountries");
		LOGGER.info(countries.toString());
		return countriesService.insertCountry(countries);
	}

	@RequestMapping(value = "/{countriesId}", method = RequestMethod.PUT)
	public Countries updateCountries(@PathVariable("CountriesId") int countriesId, @RequestBody Countries countries) {
		LOGGER.info("updateCountries");
		LOGGER.info("CountriesId :: " + countriesId);
		LOGGER.info(countries.toString());
		return countriesService.updateCountry(countriesId, countries);
	}

	@RequestMapping(value = "/{countriesId}", method = RequestMethod.DELETE)
	public boolean deleteCountries(@PathVariable("countriesId") int countriesId) {
		LOGGER.info("deleteCountries");
		LOGGER.info("countriesId :: " + countriesId);
		return countriesService.deleteCountry(countriesId);
	}

	@RequestMapping(value = "/getAllCountriess", method = RequestMethod.GET)
	public List<Countries> getAllCountriess() {
		LOGGER.info("getAllCountriess");
		return countriesService.getAllCountries();
	}

	@RequestMapping(value = "/{CountriesId}", method = RequestMethod.GET)
	public Countries getCountriesById(@PathVariable("CountriesId") int countriesId) {
		LOGGER.info("getCountriesById");
		return countriesService.getCountryByCountryId(countriesId);
	}
}