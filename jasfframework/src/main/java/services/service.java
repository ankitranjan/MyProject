package services;

import javax.ws.rs.Consumes;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import dao.backend.Employee;
import dao.backend.EmployeeDao;

@Path("/json/test")
public class service {

	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response get(@QueryParam("id") int id) {

		Employee emp = EmployeeDao.search(id);
//		track.a();

		return Response.status(201).entity(emp).build();
//		return emp;

	}

	@POST
	@Path("/post")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(Employee emp) {

		//String result = "Track saved : " + track;
	Employee em=	EmployeeDao.add(emp);
		return Response.status(201).entity(em).build();
		
	}
	
	@PUT
	@Path("/put")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response update1(Employee emp) {

		//String result = "Track saved : " + track;
	Employee em=	EmployeeDao.update(emp);
		return Response.status(201).entity(em).build();
		
	}
	
	@DELETE
	@Path("/delete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(@QueryParam("id") int id) {

		//String result = "Track saved : " + track;
	Employee em=	EmployeeDao.delete(id);
		return Response.status(201).entity(em).build();
		
	}
	
}