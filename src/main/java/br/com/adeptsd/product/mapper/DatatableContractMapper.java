package br.com.adeptsd.product.mapper;

import java.util.HashMap;
import java.util.List;

import br.com.adeptsd.product.contract.DatatableContract;

public class DatatableContractMapper {
	public DatatableContract getDatatable(List<HashMap<String, Object>> data, Integer recordsTotal, Integer recordsPartial,Integer draw){
		DatatableContract datatableContract = new DatatableContract();
		datatableContract.setDraw(draw);
		datatableContract.setRecordsFiltered(recordsPartial);
		datatableContract.setRecordsTotal(recordsTotal);
		datatableContract.setData(data);
		return datatableContract;
	}
}
