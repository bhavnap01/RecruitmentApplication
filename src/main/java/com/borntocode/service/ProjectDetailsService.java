package com.borntocode.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.borntocode.dao.interfaces.ProjectDetailsDAOInterface;
import com.borntocode.pojo.ProjectDetails;
import com.borntocode.service.interfaces.ProjectDetailsServiceInterface;

@Service
public class ProjectDetailsService implements ProjectDetailsServiceInterface {

	@Autowired
	private ProjectDetailsDAOInterface projectDetailsDAO;

	@Override
	public int insertProjectDetails(ProjectDetails projectDetails) {
		return projectDetailsDAO.insertProjectDetails(projectDetails);
	}

	@Override
	public boolean deleteProjectDetails(int projectId) {
		return projectDetailsDAO.deleteProjectDetails(projectId);
	}

	@Override
	public ProjectDetails getProjectDetailsById(int projectId) {
		return projectDetailsDAO.getProjectDetailsById(projectId);
	}

	@Override
	public List<ProjectDetails> getAllProjectDetails() {
		return projectDetailsDAO.getAllProjectDetails();
	}

	@Override
	public ProjectDetails updateProjectDetails(int projectId, ProjectDetails projectDetails) {
		return projectDetailsDAO.updateProjectDetails(projectId, projectDetails);
	}

}
