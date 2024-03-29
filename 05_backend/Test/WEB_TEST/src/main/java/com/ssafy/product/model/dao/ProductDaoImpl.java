package com.ssafy.product.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.xdevapi.PreparableStatement;
import com.ssafy.product.model.dto.ProductDto;
import com.ssafy.util.DBUtil;

public class ProductDaoImpl implements ProductDao{
	
	//singleton pattern
	private static ProductDao instance=new ProductDaoImpl();
	private ProductDaoImpl() {}
	public static ProductDao getInstance() {
		return instance;
	}

	@Override
	public void registProduct(ProductDto product) throws SQLException {
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		try {
			//1.DB 연결
			conn=DBUtil.getConnection(); 
			
			//2.불완전 SQL문 생성
			StringBuilder sql=new StringBuilder();
			sql.append("insert into product (code,model,price) values (?,?,?)"); 
			
			//3.통로 생성
			pstmt=conn.prepareStatement(sql.toString()); 
			//4.SQL 채우기
			pstmt.setString(1, product.getCode()); 
			pstmt.setString(2, product.getModel());
			pstmt.setInt(3, product.getPrice());
			
			//5.SQL 실행
			pstmt.executeUpdate();
			
			//여기서는 catch 없다. 오류는 controller가 관리해주기 때문.
			
			
		}finally {
			
			//6.닫아주기
			DBUtil.close(pstmt,conn);
			
			//static 아니라면
			//DBUtil.getInstance().close(rs,pstmt,conn);
		}
		
	}

	@Override
	public List<ProductDto> listProduct() throws SQLException {
		
		List<ProductDto> list=new ArrayList<>();
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null; 
		
		try {
			conn=DBUtil.getConnection();
			StringBuilder sql=new StringBuilder();
			sql.append("select * from product order by code"); //코드 순 오름차순
			
			pstmt=conn.prepareStatement(sql.toString());
			rs=pstmt.executeQuery(); 
			
			while(rs.next()) {
				ProductDto product=new ProductDto();
				//column 명
				product.setCode(rs.getString("code"));
				product.setModel(rs.getString("model"));
				product.setPrice(rs.getInt("price"));
				
				list.add(product);
			}
			
			
		}finally {
			DBUtil.close(rs,conn,pstmt);
		}
		
		return list;
		
		
	}

	@Override
	public ProductDto getProduct(String productCode) throws SQLException {

		ProductDto product=null;
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		
		try {
			conn=DBUtil.getConnection();
			StringBuilder sql=new StringBuilder();
			sql.append("select * from product where code=?");
			
			pstmt=conn.prepareStatement(sql.toString());
			pstmt.setString(1, productCode);
			
			rs=pstmt.executeQuery();
			 
			if(rs.next()) { //해당 물품이 존재한다면
				product=new ProductDto();
				product.setCode(rs.getString("code"));
				product.setModel(rs.getString("model"));
				product.setPrice(rs.getInt("price"));
			}

		}finally {
			DBUtil.close(conn,pstmt);
		}
		
		return product;
		
	}

	@Override
	public void modifyProduct(ProductDto product) throws SQLException {
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		try {
			conn=DBUtil.getConnection();
			StringBuilder sql=new StringBuilder();
			sql.append("update product set model=?,price=? whrere code=?");
			
			pstmt=conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, product.getModel());
			pstmt.setInt(2, product.getPrice());
			pstmt.setString(3, product.getCode());
			
			pstmt.executeUpdate();
			
		}finally {
			DBUtil.close(conn,pstmt);
		}
		
	}

	@Override
	public void deleteProduct(String productCode) throws SQLException {
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		
		try {
			conn=DBUtil.getConnection();
			StringBuilder sql=new StringBuilder();
			sql.append("delete from product where code=?");
			pstmt=conn.prepareStatement(sql.toString());
			
			pstmt.setString(1, productCode);
			
			pstmt.executeUpdate();
			
		}finally {
			DBUtil.close(conn,pstmt);
		}
		
	}


}
