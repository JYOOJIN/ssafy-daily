package com.ssafy.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.model.dto.Product;

public interface ProductService {
	void registProduct(Product product) throws Exception;
	List<Product> listProduct(Map<String, String> map) throws Exception;
	Product getProduct(String code) throws Exception;
	void updateProduct(Product product) throws Exception;
	void deleteProduct(String code) throws Exception;
}
