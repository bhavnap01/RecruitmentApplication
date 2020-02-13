package com.borntocode.pojo;

import org.springframework.stereotype.Component;

@Component
public class Cities {
	private int cityId;
	private String name;
	private States state;

	public Cities() {
		// TODO Auto-generated constructor stub
	}

	public Cities(int cityId, String name, States state) {
		super();
		this.cityId = cityId;
		this.name = name;
		this.state = state;
	}

	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public States getState() {
		return state;
	}

	public void setState(States state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "Cities [cityId=" + cityId + ", name=" + name + ", state=" + state + "]";
	}

}
