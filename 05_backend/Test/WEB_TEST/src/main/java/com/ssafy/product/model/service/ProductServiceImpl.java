package com.ssafy.product.model.service;

import java.util.List;

import com.ssafy.product.model.dao.ProductDao;
import com.ssafy.product.model.dao.ProductDaoImpl;
import com.ssafy.product.model.dto.ProductDto;

public class ProductServiceImpl  implements ProductService{

	//singleton Pattern
	private static ProductService instance = new ProductServiceImpl();
	private ProductServiceImpl() {}
	public static ProductService getInstance() {
		return instance;
	}
	
	//서비스에서 Dao(singleton pattern) 사용하기 위한 객체 생성: productDao
	private ProductDao productDao=ProductDaoImpl.getInstance();
	
	@Override
	public void registProduct(ProductDto product) throws Exception {
		productDao.registProduct(product);
	}

	@Override
	public List<ProductDto> listProduct() throws Exception {
		return productDao.listProduct();
	}

	@Override
	public ProductDto getProduct(String productCode) throws Exception {
		return productDao.getProduct(productCode);
	}

	@Override
	public void modifyProduct(ProductDto product) throws Exception {
		productDao.modifyProduct(product);
	}

	@Override
	public void deleteProduct(String productCode) throws Exception {
		productDao.deleteProduct(productCode);
	}



}
