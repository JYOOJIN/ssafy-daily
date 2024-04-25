package com.ssafy.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.model.dto.Product;
import com.ssafy.model.mapper.ProductMapper;

@Service
public class ProductServiceImpl implements ProductService {
	final ProductMapper productMapper;

	public ProductServiceImpl(ProductMapper productMapper) {
		super();
		this.productMapper = productMapper;
	}

	@Override
	public void registProduct(Product product) throws Exception {
		productMapper.registProduct(product);
	}

	@Override
	public Product getProduct(String code) throws Exception {
		return productMapper.getProduct(code);
	}

	@Override
	public void updateProduct(Product product) throws Exception {
		productMapper.updateProduct(product);
	}

	@Override
	public void deleteProduct(String code) throws Exception {
		productMapper.deleteProduct(code);
	}

	

	@Override
	public List<Product> searchProduct(String year, String month, String day) throws Exception {
		return productMapper.searchProduct(year,month,day);
	}

	@Override
	public List<Product> listProduct() throws Exception {
		return productMapper.listProduct();
	}

}
