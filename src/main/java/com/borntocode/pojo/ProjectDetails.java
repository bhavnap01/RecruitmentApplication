package com.borntocode.pojo;

import org.springframework.stereotype.Component;

@Component
public class ProjectDetails {
	private int projectId;
	private String name;
	private double totalBudget;

	public ProjectDetails() {
		// TODO Auto-generated constructor stub
	}

	public ProjectDetails(int projectId, String name, double totalBudget) {
		super();
		this.projectId = projectId;
		this.name = name;
		this.totalBudget = totalBudget;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getTotalBudget() {
		return totalBudget;
	}

	public void setTotalBudget(double totalBudget) {
		this.totalBudget = totalBudget;
	}

	@Override
	public String toString() {
		return "ProjectDetails [projectId=" + projectId + ", name=" + name + ", totalBudget=" + totalBudget + "]";
	}

}
