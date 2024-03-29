package com.ssafy.product.model.service;

import java.util.List;

import com.ssafy.product.model.dto.ProductDto;

public interface ProductService {

	public void registProduct(ProductDto product) throws Exception; //상품등록
	public List<ProductDto> listProduct() throws Exception; //전체조회
	public ProductDto getProduct(String productCode) throws Exception; //상세조회
	public void modifyProduct(ProductDto product) throws Exception; //상품수정
	public void deleteProduct(String productCode) throws Exception; //상품삭제

	
}
