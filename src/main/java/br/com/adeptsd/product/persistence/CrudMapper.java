package br.com.adeptsd.product.persistence;

import java.util.HashMap;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface CrudMapper {

//	@Insert("insert into product (name, description) values (#{name}, #{description})")
//	public void insert(Product product);

	@Select("select * from ${service} where id=${id}")
	public HashMap<String, Object> getObjectById(@Param("service") String service,@Param("id") Integer id);
	
	@Insert("insert into ${service} (${columns}) values (${values})")
	public Integer createObject(@Param("service") String service, @Param("columns")  String columns ,@Param("values")  String values);
 
}
