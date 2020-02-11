package com.borntocode.pojo;

import org.springframework.stereotype.Component;

@Component
public class Employee {
	private int employeeId;
	private String firstName;
	private String lastName;
	private Address address;
	private ProjectDetails projectDetails;
	private EmployeeRole employeeRole;
	private String email;
	private String password;
	private boolean active;
	private double salary;

	public Employee() {
		// TODO Auto-generated constructor stub
	}

	public Employee(int employeeId, String firstName, String lastName, Address address, ProjectDetails projectDetails,
			EmployeeRole employeeRole, String email, String password, boolean active, double salary) {
		super();
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.projectDetails = projectDetails;
		this.employeeRole = employeeRole;
		this.email = email;
		this.password = password;
		this.active = active;
		this.salary = salary;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public ProjectDetails getProjectDetails() {
		return projectDetails;
	}

	public void setProjectDetails(ProjectDetails projectDetails) {
		this.projectDetails = projectDetails;
	}

	public EmployeeRole getEmployeeRole() {
		return employeeRole;
	}

	public void setEmployeeRole(EmployeeRole employeeRole) {
		this.employeeRole = employeeRole;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", address=" + address + ", projectDetails=" + projectDetails + ", employeeRole=" + employeeRole
				+ ", email=" + email + ", password=" + password + ", active=" + active + ", salary=" + salary + "]";
	}

}
