package br.com.adeptsd.product.endpoint;

import java.util.HashMap;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.adeptsd.product.contract.DatatableContract;
import br.com.adeptsd.product.mapper.DatatableInput;
import br.com.adeptsd.product.mapper.DatatableContractMapper;
import br.com.adeptsd.product.service.DatatableService;

@Path("/datatable")
public class DatatableEndpoint {
	//TODO POR NO FILTER
	//private final Set<String> allowedServices = new HashSet<String>(Arrays.asList("product", "someclass"));
	//check
			//stupid security
			/*if(!allowedServices.contains(datatableInput.getSearch())){
				return null;
			}*/
	@Autowired
	DatatableService datatableService;

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/list")
	@PermitAll
	public DatatableContract getList(DatatableInput datatableInput) {
		DatatableContractMapper datatableMapper = new DatatableContractMapper();
		List<HashMap<String, Object>> lst = datatableService.getFromDatatable(datatableInput);
		return datatableMapper.getDatatable(lst, datatableService.getSize(datatableInput)
				, datatableService.getPartialSize(datatableInput)
				, datatableInput.getDraw());
	}
}
