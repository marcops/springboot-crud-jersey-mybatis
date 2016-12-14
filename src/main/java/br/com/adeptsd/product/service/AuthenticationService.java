package br.com.adeptsd.product.service;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.adeptsd.product.Authorization;
import br.com.adeptsd.product.entity.User;
import br.com.adeptsd.product.persistence.AuthenticationMapper;

public class AuthenticationService {

	private AuthenticationMapper autenticationMapper;
	@Autowired Authorization authorization;
	
	public AuthenticationService(AuthenticationMapper autenticationMapper) {
		this.autenticationMapper = autenticationMapper;
	}

	public String autenticar(String username, String password) {
		User user = autenticationMapper.autentication(username, password);
		return authorization.buildToken(user);
	}

	public String getInformation(String token) {
		return authorization.authorize(token);
	}

}
