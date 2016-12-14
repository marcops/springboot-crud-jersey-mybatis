package br.com.adeptsd.product.persistence;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import br.com.adeptsd.product.entity.User;

public interface AuthenticationMapper {
	// ESTUDAR o Mapeamento em XML para classes grandes ,
	// http://stackoverflow.com/questions/5080000/mybatis-columns-mapping
	@Results({
			@Result(property = "name", column = "name"),
			@Result(property = "categories", javaType = List.class
			, column = "id", many = @Many(select = "getCategories")) })
	@Select("select * from user where name = #{username} and password = #{password}")
	public User autentication(@Param("username") String username,
			@Param("password") String password);

	@Select("SELECT description FROM usercategory WHERE iduser = #{iduser}")
	List<String> getCategories(Integer iduser);

}
