package jsf;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import client.ClientApp;

import dao.backend.Employee;

@ManagedBean(name="bean1")
@SessionScoped
public class Bean1 {

	private int employeeId;
	private String name;
	private String designation;
	private double salary;
	
	public int getEmployeeId() {
		return employeeId;
	}
	
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDesignation() {
		return designation;
	}
	
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	
	public double getSalary() {
		return salary;
	}
	
	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	public Employee assignValues(Employee e)
	{
		e.setEmployeeId(this.getEmployeeId());
		e.setName(this.getName());
		e.setSalary(this.getSalary());
		e.setDesignation(this.getDesignation());
		return e;
	}
	public void clearValues()
	{
		this.setEmployeeId(0);
		this.setName(null);
		this.setSalary(0);
		this.setDesignation(null);
	}
	public void updateValues(Employee e)
	{
		this.setEmployeeId(e.getEmployeeId());
		this.setName(e.getName());
		this.setSalary(e.getSalary());
		this.setDesignation(e.getDesignation());
	}
	public String updateEmployee()
	{
		Employee e = new Employee();
		e=assignValues(e);
		ClientApp client =new ClientApp();
		e=client.put(e);
		FacesMessage doneMessage = null;
		@SuppressWarnings("unused")
		String outcome = null;
		if (e==null) {
			doneMessage = new FacesMessage("Failed to Update user. Please check Id/Server");
			clearValues();
			outcome = "put";
			} 
		else {
			doneMessage = new FacesMessage("Successfully Updated user");
			this.updateValues(e);
			outcome = "put1";
			}
		FacesContext.getCurrentInstance().addMessage(null, doneMessage);
		return outcome;
	}
	
	public String getEmployee()
	{
		String str = String.valueOf(this.employeeId);
		Employee e = new Employee();
		ClientApp client =new ClientApp();
		e=client.get((str));
		FacesMessage doneMessage = null;
		@SuppressWarnings("unused")
		String outcome = null;
		if (e==null) {
			doneMessage = new FacesMessage("Failed to Retrieve user. Please check Id/Server");
			clearValues();
			outcome = "get";
			} 
		else {
			doneMessage = new FacesMessage("Retrieve user Successfully");
			this.updateValues(e);
			outcome = "get1";
			}
		FacesContext.getCurrentInstance().addMessage(null, doneMessage);
		return outcome;
	}
	
	public String deleteEmployee()
	{
		String str = String.valueOf(this.employeeId);
		ClientApp client =new ClientApp();
		Employee e=client.delete(str);
		FacesMessage doneMessage = null;
		@SuppressWarnings("unused")
		String outcome = null;
		if (e==null) {
			doneMessage = new FacesMessage("Failed to Delete user. Please check Id/Server");
			clearValues();
			outcome = "delete";
			} 
		else {
			doneMessage = new FacesMessage("Successfully Deleted user with Id:" +str);
			this.updateValues(e);
			outcome = "delete1";;
			}
		FacesContext.getCurrentInstance().addMessage(null, doneMessage);
		return outcome;
	}
	public String addEmployee()
	{
		Employee e = new Employee();
		e=assignValues(e);
		ClientApp client =new ClientApp();
		e=client.post(e);
		FacesMessage doneMessage = null;
		@SuppressWarnings("unused")
		String outcome = null;
		if (e==null) {
			doneMessage = new FacesMessage("Failed to add new user");
			clearValues();
			outcome = "post";
			} 
		else {
			doneMessage = new FacesMessage("Successfully added new user");
			this.updateValues(e);
			outcome = "post1";
			}
		FacesContext.getCurrentInstance().addMessage(null, doneMessage);
		return outcome;
	}
	
}