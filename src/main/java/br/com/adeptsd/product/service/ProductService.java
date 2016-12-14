package br.com.adeptsd.product.service;

import java.util.List;

import br.com.adeptsd.product.entity.Product;
import br.com.adeptsd.product.mapper.DatatableInput;
import br.com.adeptsd.product.persistence.ProductMapper;

public class ProductService {

	private ProductMapper productMapper;

	public ProductService(ProductMapper productMapper) {
		this.productMapper = productMapper;
	}

	public void save(Product product) {
		this.productMapper.insert(product);
	}

	public List<Product> getRange(Integer start, Integer length) {
		return productMapper.selectRange(start, length);
	}
	public Integer getSize() {
		return productMapper.count();
	}
	
	public List<Product> getAll() {
		return productMapper.selectAll();
	}

	public Product getOne(Long id) {
		return productMapper.selectById(id);
	}

	public void delete(Long id) {
		productMapper.deleteById(id);
	}

	public void update(Product product) {
		productMapper.update(product);
	}

	public List<Product> getFromDatatable(DatatableInput datatableInput) {
		//datatableService
		return productMapper.getDatatable(datatableInput);
	}

	public Integer getPartialSize(DatatableInput datatableInput) {
		return productMapper.getPartialSize(datatableInput);
	}

}
