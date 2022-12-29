package com.blueice.information.model.service;

import static com.blueice.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import com.blueice.information.model.dao.FaqDao;
import com.blueice.information.model.vo.Faq;

public class FaqService {

	// faq 전체조회
	public ArrayList<Faq> selectFaq() {
		
		Connection conn = getConnection();
		
		ArrayList<Faq> list = new FaqDao().selectFaq(conn);
		
		close(conn);
		
		return list;
		
	}
	
	/**
     * faq 관리자삭제
     * @param faqNo faq테이블 FAQ_NO 컬럼값
     * @return 1 or 0
     */
	public int deleteFaq(int faqNo) {
		
		Connection conn = getConnection();
		
		int result = new FaqDao().deleteFaq(conn, faqNo);
		
		if(result > 0) {
			commit(conn);
		}
		else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
		
		
	}
}
