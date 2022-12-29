package com.blueice.information.model.service;

import static com.blueice.common.JDBCTemplate.close;
import static com.blueice.common.JDBCTemplate.commit;
import static com.blueice.common.JDBCTemplate.getConnection;
import static com.blueice.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.blueice.common.model.vo.PageInfo;
import com.blueice.information.model.dao.InquiryDao;
import com.blueice.information.model.vo.Inquiry;

/**
 * 1:1 문의하기 Service
 * @author user1
 */
public class InquiryService {

    public int insertInquiry(Inquiry i) {
        
        Connection conn = getConnection();
        
        int result = new InquiryDao().insertInquiry(conn, i);
        
        // 1:1 문의하기 작성 성공
        if(result > 0) {
            
            commit(conn);
            
        }
        
        // 실패
        else {
        
            rollback(conn);
        }
        
        // 자원반납
        close(conn);
        
        return result;
        
    }
    
    /**
     * 1:1 문의하기 리스트 조회 페이징 처리 서비스
     * @return
     */
    public int selectListCount() {
        
        Connection conn = getConnection();
        
        int listCount = new InquiryDao().selectListCount(conn);
        
        close(conn);
        
        return listCount;
    }
    
    /**
     * 마이페이지 1:1 문의내역 리스트 조회 서비스
     * @param pi
     * @return
     */
    public ArrayList<Inquiry> selectListInquiry(PageInfo pi) {
        
        Connection conn = getConnection();
        
        ArrayList<Inquiry> list = new InquiryDao().selectListInquiry(conn, pi);
        
        close(conn);
        
        return list;
        
    }
    
    /**
     * 1:1 문의내역 상세조회 서비스(마이페이지 + 관리자 둘다 사용 가능)
     * @param iNo
     * @return
     */
    public Inquiry selectInquiry(int iNo) {
        
        Connection conn = getConnection();
        
        Inquiry i = new InquiryDao().selectInquiry(conn, iNo);
        
        close(conn);
        
        return i;
        
    }
    
    /**
     * 마이페이지 1:1 문의내역 삭제 서비스 + 관리자 1:1 문의내역 삭제 서비스 
     * @param iNo
     * @return
     */
    public int deleteInquiry(int iNo) {
        
        Connection conn = getConnection();
        
        int result = new InquiryDao().deleteInquiry(conn, iNo);
        
        if(result < 0 ) {
            commit(conn);
        }
        
        else {
            rollback(conn);
        }
        
        close(conn);
        
       
       return result; 
        
    }
    
    /**
     * 관리자 1:1 문의내역 리스트 조회 서비스
     * @param pi
     * @return
     */
    public ArrayList<Inquiry> adminSelectList(PageInfo pi) {
        
        Connection conn = getConnection();
        
        ArrayList<Inquiry> list = new InquiryDao().adminSelectList(conn, pi);
        
        close(conn);
        
        return list;
        
    }
    
    /**
     * 관리자 1:1 문의내역 답변 작성 및 수정 서비스 
     * @return
     */
    public int adminInsertInquiry(String answerConts, int iNo) {
        
        Connection conn = getConnection();
        
        int result = new InquiryDao().adminInsertInquiry(conn, answerConts, iNo);
        
        if(result > 0) {
            commit(conn);
        }
        else {
            rollback(conn);
        }
        
        return result;
    
        
    }
    
    /**
     * 관리자 1:1문의 아이디로 검색 서비스
     * @param pi
     * @param searchId
     * @return
     */
    public ArrayList<Inquiry> adminIdSearchList(PageInfo pi, String searchId) {
        
        Connection conn = getConnection();
        
        ArrayList<Inquiry> list = new InquiryDao().adminIdSearchList(conn, pi, searchId);
        
        close(conn);
        
        return list;
        
    }
    
    /**
     * 관리자 1:1문의 답변완료 검색 서비스
     * @param pi
     * @return
     */
    public ArrayList<Inquiry> adminInquirySearChAnswerOList(PageInfo pi) {
        
        Connection conn = getConnection();
        
        ArrayList<Inquiry> list = new InquiryDao().adminInquirySearChAnswerOList(conn, pi);
        
        close(conn);
        
        return list;
        
    }
    
    /**
     * 관리자 1:1문의 답변대기 검색 서비스
     * @param pi
     * @return
     */
    public ArrayList<Inquiry> adminInquirySearChAnswerXList(PageInfo pi) {
        
        Connection conn = getConnection();
        
        ArrayList<Inquiry> list = new InquiryDao().adminInquirySearChAnswerXList(conn, pi);
        
        close(conn);
        
        return list;
        
    }
      
}
