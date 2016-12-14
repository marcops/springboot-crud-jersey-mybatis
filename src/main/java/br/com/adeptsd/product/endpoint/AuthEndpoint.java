package br.com.adeptsd.product.endpoint;


import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.adeptsd.product.contract.AuthenticationContract;
import br.com.adeptsd.product.service.AuthenticationService;

@Path("/authentication")
public class AuthEndpoint {

	@Autowired AuthenticationService autenticacaoService;

    @POST
    @Path("/oauth/token")
    @Consumes(MediaType.APPLICATION_JSON)
    @PermitAll
    public String autenticate(@NotNull @Valid AuthenticationContract credentials) {
        return autenticacaoService.autenticar(credentials.getUsername(), credentials.getPassword());
    }
    
    @GET
    @Path("/info")
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed("Authorization")
    public String getInformation(@NotNull @Valid @HeaderParam("token") String token) {
        return autenticacaoService.getInformation(token);
    }
}
    
