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

import com.borntocode.pojo.States;
import com.borntocode.service.interfaces.StatesServiceIntreface;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/states")
public class StatesController {

	@Autowired
	private StatesServiceIntreface stateService;
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "/state", method = RequestMethod.POST)
	public int addNewState(@RequestBody States states) {
		LOGGER.info("addNewState");
		LOGGER.info(states.toString());
		return stateService.insertStates(states);
	}

	@RequestMapping(value = "/{stateId}", method = RequestMethod.PUT)
	public States updateStates(@PathVariable("stateId") int stateId, @RequestBody States states) {
		LOGGER.info("updateStates");
		LOGGER.info("stateId :: " + stateId);
		LOGGER.info(states.toString());
		return stateService.updateStates(stateId, states);
	}

	@RequestMapping(value = "/{stateId}", method = RequestMethod.DELETE)
	public boolean deleteStates(@PathVariable("stateId") int statesId) {
		LOGGER.info("deleteStates");
		LOGGER.info("statesId :: " + statesId);
		return stateService.deleteStates(statesId);
	}

	@RequestMapping(value = "/getAllStates", method = RequestMethod.GET)
	public List<States> getAllStates() {
		LOGGER.info("getAllStates");
		return stateService.getAllStates();
	}

	@RequestMapping(value = "/{stateId}", method = RequestMethod.GET)
	public States getStatesById(@PathVariable("stateId") int statesId) {
		LOGGER.info("getStatesById");
		return stateService.getStatesByStatesId(statesId);
	}

}
