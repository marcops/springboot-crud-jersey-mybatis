package br.com.adeptsd.product.endpoint.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import br.com.adeptsd.product.endpoint.AuthEndpoint;
import br.com.adeptsd.product.endpoint.CrudEndpoint;
import br.com.adeptsd.product.endpoint.DatatableEndpoint;
import br.com.adeptsd.product.endpoint.RestEndpoint;
import br.com.adeptsd.product.filter.AuthorizationFilter;

@Component
public class JerseyConfig extends ResourceConfig {
	public JerseyConfig() {
		register(RestEndpoint.class);
		register(AuthEndpoint.class);
		register(AuthorizationFilter.class);
		register(DatatableEndpoint.class);
		register(CrudEndpoint.class);
	}
}
