package com.ssafy.product.model.dao;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.product.model.dto.ProductDto;

public interface ProductDao {
	
	public void registProduct(ProductDto product) throws SQLException; //상품등록
	public List<ProductDto> listProduct() throws SQLException; //전체조회
	public ProductDto getProduct(String productCode) throws SQLException; //상세조회
	public void modifyProduct(ProductDto product) throws SQLException; //상품수정
	public void deleteProduct(String productCode) throws SQLException; //상품삭제
	
}
