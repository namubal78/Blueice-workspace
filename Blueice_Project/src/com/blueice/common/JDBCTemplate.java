package com.blueice.common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {
	
	
	/**
	 * Connection 객체를 생성 후 (나를 호출한 메소드에게) 반환하는 메소드
	 * @return Connection
	 */
	public static Connection getConnection() {
		
		Connection conn = null;
		Properties prop = new Properties();
		
		try {

			String fileName = JDBCTemplate.class.getResource("/sql/driver/driver.properties").getPath();
			prop.load(new FileInputStream(fileName));
			
			// 1) JDBC Driver 등록(DriverManager)
			Class.forName(prop.getProperty("driver"));
			
			// 2) Connection 객체 생성
			conn = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("username"), prop.getProperty("password"));
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Connection 객체 return
		return conn;
	}
	
	/**
	 * 전달받은 Connection 객체를 가지고 commit 해주는 메소드
	 * @param conn
	 */
	public static void commit(Connection conn) {
		
		try {
			if(conn != null && !conn.isClosed()) {
				conn.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 전달받은 Connection 객체를 가지고 rollback 해주는 메소드
	 * @param conn
	 */
	public static void rollback(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) {
				conn.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 전달받은 Connection 객체를 반납시켜주는 메소드
	 * @param conn
	 */
	public static void close(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 전달받은 Statement 객체를 반납시켜주는 메소드
	 * @param stmt
	 */
	public static void close(Statement stmt) {
		// 다형성에 의해 PreparedStatement도 반납 가능
		try {
			if(stmt != null && !stmt.isClosed()) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 전달받은 ResultSet 객체를 반납시켜주는 메소드
	 * @param rset
	 */
	public static void close(ResultSet rset) {
		try {
			if(rset != null && !rset.isClosed()) {
				rset.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
