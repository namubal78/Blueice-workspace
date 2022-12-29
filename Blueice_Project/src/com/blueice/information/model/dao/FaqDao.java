package com.blueice.information.model.dao;

import static com.blueice.common.JDBCTemplate.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.blueice.information.model.vo.Faq;

public class FaqDao {

private Properties prop = new Properties();
    
    public FaqDao() {
        
        String fileName = FaqDao.class.getResource("/sql/information/information-mapper.xml").getPath();
        
        try {
            prop.loadFromXML(new FileInputStream(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // faq 전체조회
    public ArrayList<Faq> selectFaq(Connection conn) {
    	
    	ArrayList<Faq> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectFaq");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				list.add(new Faq(rset.getInt("FAQ_NO")
								 , rset.getString("FAQ_QUESTION")
								 , rset.getString("FAQ_CONTS")
								 , rset.getInt("ADMIN_NO")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			close(rset);
			close(pstmt);
		}
		
		return list;
    	
    }
    
    /**
     * faq 관리자삭제
     * @param conn
     * @param faqNo faq테이블 FAQ_NO 컬럼값
     * @return 1 or 0
     */
    public int deleteFaq(Connection conn, int faqNo) {
    	
    	PreparedStatement pstmt = null;
    	
    	int result = 0;
    	
    	String sql = prop.getProperty("deleteFaq");
    	
    	try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, faqNo);
			
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			close(pstmt);
		}
		
		return result;
    	
    }
    
}
