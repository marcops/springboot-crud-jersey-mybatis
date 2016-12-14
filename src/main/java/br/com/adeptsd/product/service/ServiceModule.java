package br.com.adeptsd.product.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.adeptsd.product.Authorization;
import br.com.adeptsd.product.persistence.AuthenticationMapper;
import br.com.adeptsd.product.persistence.CrudMapper;
import br.com.adeptsd.product.persistence.DatatableMapper;
import br.com.adeptsd.product.persistence.ProductMapper;

@Configuration
public class ServiceModule {

	@Bean
	public CrudService crudService(CrudMapper crudMapper) {
		return new CrudService(crudMapper);
	}

	@Bean
	public DatatableService datatableService(DatatableMapper datatableMapper) {
		return new DatatableService(datatableMapper);
	}

	@Bean
	public ProductService productService(ProductMapper productMapper) {
		return new ProductService(productMapper);
	}

	@Bean
	public Authorization authorization(
			@Value("${authorization.key:empty}") String key,
			@Value("${authorization.expiresInSeconds:604800}") Integer expiresInSeconds) {
		return new Authorization(key, expiresInSeconds);
	}

	@Bean
	public AuthenticationService autenticacaoService(
			AuthenticationMapper autenticationMapper) {
		return new AuthenticationService(autenticationMapper);
	}

}
