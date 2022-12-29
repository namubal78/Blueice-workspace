package com.blueice.information.model.dao;

import static com.blueice.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.text.html.HTMLDocument.HTMLReader.PreAction;

import com.blueice.common.model.vo.PageInfo;
import com.blueice.information.model.vo.Inquiry;
import com.blueice.member.model.dao.MemberDao;

public class InquiryDao {
    
    private Properties prop = new Properties();
    
    public InquiryDao() {
        
        String fileName = MemberDao.class.getResource("/sql/information/information-mapper.xml").getPath();
        
        try {
            prop.loadFromXML(new FileInputStream(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 1:1 문의하기 작성 DAO
     * @param conn
     * @param i
     * @return
     */
    public int insertInquiry(Connection conn, Inquiry i) {
        
        // Insert문
        
        int result = 0;
        PreparedStatement pstmt = null;
        
        String sql = prop.getProperty("insertInquiry");
        
        try {
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, i.getInquiryTitle());
            pstmt.setString(2, i.getInquiryConts());
            pstmt.setInt(3, Integer.parseInt(i.getMemNo()));
            
            result = pstmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            
            close(pstmt);
            
        }
        
        return result;
        
    }
    
    /**
     * 1:1 문의하기 리스트 조회 페이징 처리 DAO
     * @param conn
     * @return
     */
    public int selectListCount(Connection conn) {
        
        // SELECT문 => ResultSet 객체 (그룹함수를 써 서 한 행 조회)
        
        int listCount = 0;
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        
        String sql = prop.getProperty("selectListCount");
        
        try {
            
            pstmt = conn.prepareStatement(sql);
            
            rset = pstmt.executeQuery();
            
            if(rset.next()) {
                listCount = rset.getInt("COUNT");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            
            close(rset);
            close(pstmt);
            
        }
        
        return listCount;
        
    }
    /**
     * 마이페이지 1:1 리스트 조회 DAO
     * @param conn
     * @param pi
     * @return
     */
    public ArrayList<Inquiry> selectListInquiry(Connection conn, PageInfo pi) {
        
        // SELECT 문 => ResultSet 객체 (여러행 조회)
        
        ArrayList<Inquiry> list = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        
        String sql = prop.getProperty("selectListInquiry");
        
        
        try {
            pstmt = conn.prepareStatement(sql);
            
            int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
            int endRow = startRow + pi.getBoardLimit() - 1;
            
            pstmt.setInt(1, startRow);
            pstmt.setInt(2, endRow);
            
            rset = pstmt.executeQuery();
            
            while(rset.next()) {
                
                list.add(new Inquiry(rset.getInt("INQUIRY_NO")
                                   , rset.getString("INQUIRY_TITLE")
                                   , rset.getDate("INQUIRY_DATE")
                                   , rset.getString("ANSWER_CONTS")
                                   , rset.getString("MEM_NO")));
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
     * 1:1 문의내역 상세조회 서비스(마이페이지 + 관리자 둘다 사용 가능)
     * @param conn
     * @param iNo
     * @return
     */
    public Inquiry selectInquiry(Connection conn, int iNo) {
        
        // SELECT문
        
        Inquiry i = null;
        ResultSet rset = null;
        PreparedStatement pstmt = null;
        
        String sql = prop.getProperty("selectInquiry");
        
        
        try {
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setInt(1, iNo);
            
            rset = pstmt.executeQuery();
            
            if(rset.next()) {
                
                i = new Inquiry(rset.getInt("INQUIRY_NO")
                          , rset.getString("INQUIRY_TITLE")
                          , rset.getString("INQUIRY_CONTS")
                          , rset.getDate("INQUIRY_DATE")
                          , rset.getString("ANSWER_CONTS")
                          , rset.getDate("ANSWER_DATE")
                          , rset.getString("INQUIRY_STATUS")
                          , rset.getString("MEM_ID"));

            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rset);
            close(pstmt);
        }
        
        return i;
    }
    
    /**
     * 마이페이지 1:1 문의내역 삭제 DAO + 관리자 1:1 문의내역 삭제 DAO
     * @param conn
     * @param iNo
     * @return
     */
    public int deleteInquiry(Connection conn, int iNo) {
        
        // UPDATE문
        int result = 0;
        PreparedStatement pstmt = null;
        
        String sql = prop.getProperty("deleteInquiry");
        
        try {
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setInt(1, iNo);
            
            result = pstmt.executeUpdate();
            
        } catch (SQLException e) {
            
            e.printStackTrace();
        } finally {
            close(pstmt);
        }
          
        return result;
    }
    
    /**
     * 관리자 1:1 문의내역 리스트 조회 DAO
     * @param conn
     * @param pi
     * @return
     */
    public ArrayList<Inquiry> adminSelectList(Connection conn, PageInfo pi) {
        
        // SELECT 문 => ResultSet 객체 (여러행 조회)
        
        ArrayList<Inquiry> list = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        
        String sql = prop.getProperty("adminSelectList");
              
        try {
            pstmt = conn.prepareStatement(sql);
            
            int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
            int endRow = startRow + pi.getBoardLimit() - 1;
            
            pstmt.setInt(1, startRow);
            pstmt.setInt(2, endRow);
            
            rset = pstmt.executeQuery();
            
            while(rset.next()) {
                
                list.add(new Inquiry(rset.getInt("INQUIRY_NO")
                                   , rset.getString("INQUIRY_TITLE")
                                   , rset.getDate("INQUIRY_DATE")
                                   , rset.getString("ANSWER_CONTS")
                                   , rset.getString("MEM_ID")));
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
     * 관리자 1:1 문의내역 답변 작성 및 수정 DAO
     * @return
     */
    public int adminInsertInquiry(Connection conn, String answerConts, int iNo) {
        
        // UPDATE문
        
        int result = 0;
        PreparedStatement pstmt = null;
        
        String sql = prop.getProperty("adminInsertInquiry");
        
        try {
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, answerConts);
            pstmt.setInt(2, iNo);
            
            result = pstmt.executeUpdate();
            
            
        } catch (SQLException e) {
            
            e.printStackTrace();
        } finally {
            
            close(pstmt);
        }
        
        return result;
    }
    
    /**
     * 관리자 1:1문의 아이디로 검색 DAO
     * @param conn
     * @param pi
     * @return
     */
    public ArrayList<Inquiry> adminIdSearchList(Connection conn, PageInfo pi, String searchId) {
        
        // SELECT 문 => ResultSet 객체 (여러행 조회)
        
        ArrayList<Inquiry> list = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        
        String sql = prop.getProperty("adminIdSearchList");
              
        try {
            pstmt = conn.prepareStatement(sql);
            
            int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
            int endRow = startRow + pi.getBoardLimit() - 1;
            
            pstmt.setString(1,  "%" + searchId + "%");
            pstmt.setInt(2, startRow);
            pstmt.setInt(3, endRow);
            
            rset = pstmt.executeQuery();
            
            while(rset.next()) {
                
                list.add(new Inquiry(rset.getInt("INQUIRY_NO")
                                   , rset.getString("INQUIRY_TITLE")
                                   , rset.getDate("INQUIRY_DATE")
                                   , rset.getString("ANSWER_CONTS")
                                   , rset.getString("MEM_ID")));
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
     * 관리자 1:1문의 답변완료 검색 서비스
     * @param conn
     * @param pi
     * @param searchId
     * @return
     */
    public ArrayList<Inquiry> adminInquirySearChAnswerOList(Connection conn, PageInfo pi) {
        
        // SELECT 문 => ResultSet 객체 (여러행 조회)
        
        ArrayList<Inquiry> list = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        
        String sql = prop.getProperty("adminInquirySearChAnswerOList");
              
        try {
            pstmt = conn.prepareStatement(sql);
            
            int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
            int endRow = startRow + pi.getBoardLimit() - 1;
            
            pstmt.setInt(1, startRow);
            pstmt.setInt(2, endRow);
            
            rset = pstmt.executeQuery();
            
            while(rset.next()) {
                
                list.add(new Inquiry(rset.getInt("INQUIRY_NO")
                                   , rset.getString("INQUIRY_TITLE")
                                   , rset.getDate("INQUIRY_DATE")
                                   , rset.getString("ANSWER_CONTS")
                                   , rset.getString("MEM_ID")));
            }
            
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rset);
            close(pstmt);
        }
        
        return list;
    }
    
    public ArrayList<Inquiry> adminInquirySearChAnswerXList(Connection conn, PageInfo pi) {
        
        // SELECT 문 => ResultSet 객체 (여러행 조회)
        
        ArrayList<Inquiry> list = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        
        String sql = prop.getProperty("adminInquirySearChAnswerXList");
              
        try {
            pstmt = conn.prepareStatement(sql);
            
            int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
            int endRow = startRow + pi.getBoardLimit() - 1;
            
            pstmt.setInt(1, startRow);
            pstmt.setInt(2, endRow);
            
            rset = pstmt.executeQuery();
            
            while(rset.next()) {
                
                list.add(new Inquiry(rset.getInt("INQUIRY_NO")
                                   , rset.getString("INQUIRY_TITLE")
                                   , rset.getDate("INQUIRY_DATE")
                                   , rset.getString("ANSWER_CONTS")
                                   , rset.getString("MEM_ID")));
            }
            
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rset);
            close(pstmt);
        }
        
        return list;
    }
    
    
    
}
