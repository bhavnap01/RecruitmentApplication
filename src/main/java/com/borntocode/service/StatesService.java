package com.borntocode.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.borntocode.dao.interfaces.StatesDAOInterface;
import com.borntocode.pojo.States;
import com.borntocode.service.interfaces.StatesServiceIntreface;

@Service
public class StatesService implements StatesServiceIntreface {

	@Autowired
	private StatesDAOInterface stateDAO;

	@Override
	public int insertStates(States states) {
		return stateDAO.insertStates(states);
	}

	@Override
	public States updateStates(int statesId, States states) {
		return stateDAO.updateStates(statesId, states);
	}

	@Override
	public boolean deleteStates(int statesId) {
		return stateDAO.deleteStates(statesId);
	}

	@Override
	public States getStatesByStatesId(int statesId) {
		return stateDAO.getStatesByStatesId(statesId);
	}

	@Override
	public List<States> getAllStates() {
		return stateDAO.getAllStates();
	}

}
