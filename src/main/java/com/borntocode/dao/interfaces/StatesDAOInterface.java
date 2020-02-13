package com.borntocode.dao.interfaces;

import java.util.List;

import com.borntocode.pojo.States;

public interface StatesDAOInterface {
	public int insertStates(States states);

	public States updateStates(int statesId, States states);

	public boolean deleteStates(int statesId);

	public States getStatesByStatesId(int statesId);

	public List<States> getAllStates();
}
