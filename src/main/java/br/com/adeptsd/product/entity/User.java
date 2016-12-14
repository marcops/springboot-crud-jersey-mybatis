package br.com.adeptsd.product.entity;

import java.util.List;

import br.com.adeptsd.product.SerializableBase64;

public class User extends SerializableBase64<User> {
	private static final long serialVersionUID = 1L;
	private String name;
	private List<String> categories;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}
}
