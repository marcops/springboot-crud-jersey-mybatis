package br.com.adeptsd.product.contract;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthenticationContract {
	@JsonProperty("grant_type")
	@NotEmpty
	private String grantType;
	@NotEmpty
	private String username;
	@NotEmpty
	private String password;

	public String getGrantType() {
		return grantType;
	}

	public void setGrantType(String grantType) {
		this.grantType = grantType;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public AuthenticationContract() {
	}

	@Override
	public String toString() {
		return "AuthRequest{" + "grantType=" + grantType + ", username="
				+ username + ", password=" + password + '}';
	}
}
