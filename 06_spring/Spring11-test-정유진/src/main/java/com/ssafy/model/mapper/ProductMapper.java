package com.ssafy.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.model.dto.Product;

@Mapper
public interface ProductMapper {
	void registProduct(Product product) throws SQLException;
	List<Product> listProduct(Map<String, String> map) throws SQLException;
	Product getProduct(String code) throws SQLException;
	void updateProduct(Product product) throws SQLException;
	void deleteProduct(String code) throws SQLException;
}
