package com.blueice.information.model.service;

import static com.blueice.common.JDBCTemplate.close;
import static com.blueice.common.JDBCTemplate.commit;
import static com.blueice.common.JDBCTemplate.getConnection;
import static com.blueice.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.blueice.common.model.vo.PageInfo;
import com.blueice.information.model.dao.NoticeDao;
import com.blueice.information.model.vo.Notice;

public class NoticeService {
	
	/**
	 * 공지사항 총 게시글 "개수" 조회(SELECT) 서비스  
	 * @param pi
	 * @return listCount
	 */
	public int selectListCount() {
		
		Connection conn = getConnection();
		
		int listCount = new NoticeDao().selectListCount(conn);
		
		close(conn);
		
		return listCount;
	}
	
	/**
	 * 공지사항 총 "게시글" 조회(SELECT) 서비스 
	 * @param pi
	 * @return list
	 */
	public ArrayList<Notice> selectList(PageInfo pi) {
		
		Connection conn = getConnection();
		
		ArrayList<Notice> list = new NoticeDao().selectList(conn, pi);
		
		close(conn);
		
		return list;
	}
	/**
	 * 공지사항 <검색시> 총 게시글 "개수" 조회(SELECT) 서비스  
	 * @param searchText
	 * @return listCount
	 */
	public int selectListSearchCount(String searchText) {
		
		Connection conn = getConnection();
		
		int listCount = new NoticeDao().selectListSearchCount(conn, searchText);
		
		close(conn);
		
		return listCount;
	}

	/**
	 * 공지사항 <검색시> 총 "게시글" 조회(SELECT) 서비스 
	 * @param pi
	 * @param searchText
	 * @return list
	 */
	public ArrayList<Notice> selectSearchList(PageInfo pi, String searchText) {
		
		Connection conn = getConnection();
		
		ArrayList<Notice> list = new NoticeDao().selectSearchList(conn, pi, searchText);
		
		close(conn);
		
		return list;
	}

// --------------------------------------------------------------------------------------------------------------------------------------------

	/**
	 * 공지사항 게시글 등록(INSERT)을 요청하는 서비스 
	 * @param n
	 * @return result 
	 */
	public int insertNotice(Notice n) {
		
		Connection conn = getConnection(); 
		
		int result = new NoticeDao().insertNotice(conn, n);
		
		if (result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		close(conn);
		
		return result;
	}
	
// --------------------------------------------------------------------------------------------------------------------------------------------
	// 공지사항 게시글 클릭시 상세보기 (조회수 증가 + 게시글 조회) 
	
	/**
	 * 공지사항 게시글 클릭시 조회수 증가(UPDATE)를 요청하는 서비스 
	 * @param noticeNo
	 * @return result
	 */
	public int increaseCount(int noticeNo) {
		
		Connection conn = getConnection();
		
		int result = new NoticeDao().increaseCount(conn, noticeNo);
		
		if(result > 0) {
			commit(conn);
		}
		else {
			rollback(conn);
			
		} 
		close(conn);
		
		return result;
	}

	/**
	 * 공지사항 게시글 "상세 조회"(SELECT)를 요청하는 서비스 
	 * @param noticeNo
	 * @return n
	 */
	public Notice selectNotice(int noticeNo) {
		
		Connection conn = getConnection();
		
		Notice n = new NoticeDao().selectNotice(conn, noticeNo);

		close(conn);
		
		return n;
	}

// --------------------------------------------------------------------------------------------------------------------------------------------
	
	/**
	 * 공지사항 게시글 수정(UPDATE)을 요청하는 서비스 
	 * @param n
	 * @return result
	 */
	public int updateNotice(Notice n) {
		
		Connection conn = getConnection();
		
		int result = new NoticeDao().updateNotice(conn, n);
		
		if(result > 0) {
			commit(conn);
		}
		else {
			rollback(conn);
			
		} 
		close(conn);
		
		return result;
	}

// --------------------------------------------------------------------------------------------------------------------------------------------

	/**
	 * 공지사항 게시글 삭제(UPDATE)을 요청하는 서비스 
	 * @param noticeNo
	 * @return result
	 */
	public int deleteNotice(int noticeNo) {
		
		Connection conn = getConnection();
		
		int result = new NoticeDao().deleteNotice(conn, noticeNo);
		
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
	
	








