package com.borntocode.pojo;

import org.springframework.stereotype.Component;

@Component
public class Countries {
	private int countryId;
	private String sortName;
	private String name;
	private int phoneCode;

	public Countries() {
		// TODO Auto-generated constructor stub
	}

	public Countries(int countryId, String sortName, String name, int phoneCode) {
		super();
		this.countryId = countryId;
		this.sortName = sortName;
		this.name = name;
		this.phoneCode = phoneCode;
	}

	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	public String getSortName() {
		return sortName;
	}

	public void setSortName(String sortName) {
		this.sortName = sortName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPhoneCode() {
		return phoneCode;
	}

	public void setPhoneCode(int phoneCode) {
		this.phoneCode = phoneCode;
	}

	@Override
	public String toString() {
		return "Countries [countryId=" + countryId + ", sortName=" + sortName + ", name=" + name + ", phoneCode="
				+ phoneCode + "]";
	}

}
