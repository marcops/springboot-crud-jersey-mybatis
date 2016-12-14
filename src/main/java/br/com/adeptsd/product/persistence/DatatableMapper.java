package br.com.adeptsd.product.persistence;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import br.com.adeptsd.product.mapper.DatatableInput;

public interface DatatableMapper {

	@Select("select count(*) from ${service} ")
	public Integer count(DatatableInput datatableInput);

	@Select("select * from ${service} "
		    + " ${fullsearch} "
		    + " ORDER BY ${order} ${orderdirection} " //#{order} #{orderdirection}
		    + " LIMIT #{start},#{length} " )
	public  List<HashMap<String, Object>> getDatatable(DatatableInput datatableInput);
	
	@Select("<script>"
			+ "select * from ${service} "
			+ " <if test=\"search != null and search != ''\"> "
		    + " WHERE ${fullsearch} "
		    +" </if> "
		    + " ORDER BY ${order} ${orderdirection} " //#{order} #{orderdirection}
		    + " LIMIT #{start},#{length} "
		    + " </script>")
	public  List<HashMap<String, Object>> getDatatable2(DatatableInput datatableInput, @Param("fullsearch") String fullsearch);

	@Select("<script>"
			+ "select count(*) from ${service} "
			+ " <if test=\"search != null and search != ''\"> "
		    + " WHERE description like '%${search}%' or name like '%${search}%' "
		    +" </if> "
		    + " </script>")
	public Integer getPartialSize(DatatableInput datatableInput);

}
