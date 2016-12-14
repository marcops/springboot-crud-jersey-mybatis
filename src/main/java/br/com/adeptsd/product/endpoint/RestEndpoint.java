package br.com.adeptsd.product.endpoint;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import br.com.adeptsd.product.entity.Product;
import br.com.adeptsd.product.service.ProductService;

@Path("/product")
public class RestEndpoint {

	@Autowired ProductService productService;

	@POST
	public Response add(Product product) {
		productService.save(product);
		return Response.status(Status.CREATED).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
	public List<Product> getAll() {
		return productService.getAll();
	}
	
	@GET
	@Path("/getAllAuthenticated")
	@Produces(MediaType.APPLICATION_JSON)
	@RolesAllowed("authenticated")
	public List<Product> getAllAuthenticated() {
		return productService.getAll();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Product getOne(@PathParam("id") Long id) {
		return productService.getOne(id);
	}

	@DELETE
	@Path("/{id}")
	public void remove(@PathParam("id") Long id) {
		productService.delete(id);
	}

	@PUT
	@Path("/{id}")
	public void update(@PathParam("id") Long id, Product product) {
		product.setId(id);
		productService.update(product);
	}
}
