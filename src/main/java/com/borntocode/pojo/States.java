package com.borntocode.pojo;

public class States {
	private int stateId;
	private String name;
	private Countries country;

	public States() {
		// TODO Auto-generated constructor stub
	}

	public States(int stateId, String name, Countries country) {
		super();
		this.stateId = stateId;
		this.name = name;
		this.country = country;
	}

	public int getStateId() {
		return stateId;
	}

	public void setStateId(int stateId) {
		this.stateId = stateId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Countries getCountry() {
		return country;
	}

	public void setCountry(Countries country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "States [stateId=" + stateId + ", name=" + name + ", country=" + country + "]";
	}

}
