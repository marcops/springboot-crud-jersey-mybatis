package br.com.adeptsd.product.service;

import java.util.HashMap;

import br.com.adeptsd.product.persistence.CrudMapper;

public class CrudService {

	private CrudMapper crudMapper;
	private final String separator = ",";
	public CrudService(CrudMapper crudMapper) {
		this.crudMapper = crudMapper;
	}

	public HashMap<String, Object> getObjectById(String service, Integer id) {
		return crudMapper.getObjectById(service, id);
	}

	private String formatToDatabase(String value) {
		return "'"+value+"'"+separator;
	}
	private String removeLastSeparator(StringBuilder value) {
		return value.substring(0, value.length()-separator.length());
	}
	public Integer createObject(String service,HashMap<String, String> contractValues) {
		StringBuilder columns = new StringBuilder();
		StringBuilder values = new StringBuilder();
		contractValues.forEach((k, v) -> {columns.append(k+separator); values.append(formatToDatabase(v));});
		return crudMapper.createObject(service,removeLastSeparator(columns),removeLastSeparator(values));
	}
}
