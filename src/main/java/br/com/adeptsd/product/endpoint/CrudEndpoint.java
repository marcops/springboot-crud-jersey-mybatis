package br.com.adeptsd.product.endpoint;

import java.util.HashMap;

import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.adeptsd.product.service.CrudService;

@Path("/crud")
public class CrudEndpoint {
	@Autowired CrudService crudService;
	//@Autowired CrudMapper<?> crudMapper;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/read/{service}/{id}")
	@PermitAll
	public HashMap<String, Object> getObjectById(@PathParam("service") String service, @PathParam("id") Integer id) {
		return crudService.getObjectById(service, id);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/create/{service}")
	@PermitAll
	public Integer createObject(@PathParam("service") String service, HashMap<String,String> values) {
		return crudService.createObject(service,values);
	}
	
	
}
