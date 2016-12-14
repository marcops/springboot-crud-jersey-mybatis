package br.com.adeptsd.product.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import br.com.adeptsd.product.entity.Product;
import br.com.adeptsd.product.mapper.DatatableInput;

public interface ProductMapper {

	@Insert("insert into product (name, description) values (#{name}, #{description})")
	public void insert(Product product);

	@Results({ 
		@Result(property = "name", column = "name")
		, @Result(property = "description", column = "description")
		, @Result(property = "id", column = "id") })
	@Select("select * from product")
	public List<Product> selectAll();
	
	@Results({ 
		@Result(property = "name", column = "name")
		, @Result(property = "description", column = "description")
		, @Result(property = "id", column = "id") })
	@Select("select * from product LIMIT #{start},#{length}")
	public List<Product> selectRange(@Param("start") Integer start, @Param("length") Integer length);
	
	@Select("select count(*) from product")
	public Integer count();

	@Results({ 
		@Result(property = "name", column = "name")
		, @Result(property = "description", column = "description")
		, @Result(property = "id", column = "id") })
	@Select("select * from product where id = #{id}")
	public Product selectById(Long id);

	@Delete("delete from product where id = #{id}")
	public void deleteById(Long id);

	@Update("update product set name = #{name}, description = #{description} where id = #{id}")
	public void update(Product book);

	@Results({ 
		@Result(property = "name", column = "name")
		, @Result(property = "description", column = "description")
		, @Result(property = "id", column = "id") })
	@Select("<script>"
			+ "select * from product "
			+ " <if test=\"search != null and search != ''\"> "
		    + " WHERE description like '%${search}%' or name like '%${search}%' "
		    +" </if> "
		    + " ORDER BY ${order} ${orderdirection} " //#{order} #{orderdirection}
		    + " LIMIT #{start},#{length} "
		    + " </script>")
	public List<Product> getDatatable(DatatableInput datatableInput);

	@Select("<script>"
			+ "select count(*) from product "
			+ " <if test=\"search != null and search != ''\"> "
		    + " WHERE description like '%${search}%' or name like '%${search}%' "
		    +" </if> "
		    + " </script>")
	public Integer getPartialSize(DatatableInput datatableInput);

}
