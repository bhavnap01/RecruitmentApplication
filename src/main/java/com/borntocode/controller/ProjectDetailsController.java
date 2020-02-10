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

import com.borntocode.pojo.ProjectDetails;
import com.borntocode.service.ProjectDetailsService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/projects")
public class ProjectDetailsController {
	@Autowired
	private ProjectDetailsService projectDetailsService;
	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "/project", method = RequestMethod.POST)
	public int addNewProjectDetails(@RequestBody ProjectDetails ProjectDetails) {
		LOGGER.info("addNewProjectDetails");
		LOGGER.info(ProjectDetails.toString());
		return projectDetailsService.insertProjectDetails(ProjectDetails);
	}

	@RequestMapping(value = "/{projectId}", method = RequestMethod.PUT)
	public ProjectDetails updateProjectDetails(@PathVariable("projectId") int projectId,
			@RequestBody ProjectDetails ProjectDetails) {
		LOGGER.info("updateProjectDetails");
		LOGGER.info("projectId :: " + projectId);
		LOGGER.info(ProjectDetails.toString());
		return projectDetailsService.updateProjectDetails(projectId, ProjectDetails);
	}

	@RequestMapping(value = "/{projectId}", method = RequestMethod.DELETE)
	public boolean deleteProjectDetails(@PathVariable("projectId") int projectId) {
		LOGGER.info("deleteProjectDetails");
		LOGGER.info("projectId :: " + projectId);
		return projectDetailsService.deleteProjectDetails(projectId);
	}

	@RequestMapping(value = "/getAllProjects", method = RequestMethod.GET)
	public List<ProjectDetails> getAllProjectDetailss() {
		LOGGER.info("getAllProjectDetailss");
		return projectDetailsService.getAllProjectDetails();
	}

	@RequestMapping(value = "/{projectId}", method = RequestMethod.GET)
	public ProjectDetails getProjectDetailsById(@PathVariable("projectId") int projectId) {
		LOGGER.info("getProjectDetailsById");
		return projectDetailsService.getProjectDetailsById(projectId);
	}
}
