package com.borntocode.service.interfaces;

import java.util.List;

import com.borntocode.pojo.ProjectDetails;

public interface ProjectDetailsServiceInterface {
	public int insertProjectDetails(ProjectDetails projectDetails);

	public ProjectDetails updateProjectDetails(int projectId, ProjectDetails projectDetails);

	public boolean deleteProjectDetails(int projectId);

	public ProjectDetails getProjectDetailsById(int projectId);

	public List<ProjectDetails> getAllProjectDetails();

}
