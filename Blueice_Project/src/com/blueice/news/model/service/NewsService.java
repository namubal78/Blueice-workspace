package com.blueice.news.model.service;

import static com.blueice.common.JDBCTemplate.close;
import static com.blueice.common.JDBCTemplate.commit;
import static com.blueice.common.JDBCTemplate.getConnection;
import static com.blueice.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.blueice.news.model.dao.NewsDao;
import com.blueice.news.model.vo.Attachment;
import com.blueice.news.model.vo.News;
import com.blueice.common.model.vo.PageInfo;

public class NewsService {

	/**
	 * 페이징 바에 들어갈 게시판 개수 구하기
	 * @return
	 */
	public int selectListCount() {
		
		Connection conn = getConnection();
		
		int listCount = new NewsDao().selectListCount(conn);
		
		close(conn);
		
		return listCount;
	}
	
	/**
	 * 각 페이지마다 들어갈 게시글 조회
	 * @param pi
	 * @return
	 */
	public ArrayList<News> selectPaging(PageInfo pi){
		
		Connection conn = getConnection();
		
		ArrayList<News> list = new NewsDao().selectPaging(conn, pi);
		
		close(conn);
		
		return list;
	}
	
	/**
	 * 뉴스 상세조회 페이지 조회수 증가용 메소드
	 * @param newsNo
	 * @return
	 */
	public int countNewsPage(int newsNo) {
		
		Connection conn = getConnection();
		
		int result = new NewsDao().countNewsPage(conn, newsNo);
		
		if(result > 0) {
			
			commit(conn);
		}
		else {
			
			rollback(conn);
		}
		
		return result;
	}
	
	/**
	 * 뉴스 상세페이지 조회
	 * @param newsNo
	 * @return
	 */
	public News selectNewsPage(int newsNo) {
		
		Connection conn = getConnection();
		
		News n = new NewsDao().selectNewsPage(conn, newsNo);
		
		close(conn);
		
		return n;
	}
	
	/**
	 * 뉴스 게시글 삽입
	 * @param n
	 * @param at
	 * @return
	 */
	public int insertNews(News n, Attachment at) {
		
		Connection conn = getConnection();
		
		int result1 = new NewsDao().insertNews(conn, n);
		
		int result2 = 1;
		
		if(at != null) {
			
			result2 = new NewsDao().insertNewsAttachment(conn, at);
		}
		
		if(result1 > 0 && result2 > 0) {
			
			commit(conn);
		}
		else {
			
			rollback(conn);
		}
		
		close(conn);
		
		return result1 * result2;
	}
	
	/**
	 * 뉴스 수정 페이지 폼 가져오기(내용)
	 * @param newsNo
	 * @return
	 */
	public News updateFormNews(int newsNo) {
		
		Connection conn = getConnection();
		
		News n = new NewsDao().updateFormNews(conn, newsNo);
		
		close(conn);
		
		return n;		
	}
	
	/**
	 * 뉴스 수정 페이지 폼 가져오기(첨부파일)
	 * @param newsNo
	 * @return
	 */
	public Attachment updateFormAttachment(int newsNo) {
		
		Connection conn = getConnection();
		
		Attachment at = new NewsDao().updateFormAttachment(conn,  newsNo);
		
		close(conn);
		
		return at;
	}
	
	/**
	 * 관리자 뉴스 게시글, 첨부파일 수정
	 * @param n
	 * @param at
	 * @return
	 */
	public int updateNews(News n, Attachment at) {
		
		Connection conn = getConnection();
		
		// 3가지 경우 모두 실행해야 하는 News Update구문 요청
		int result1 = new NewsDao().updateNews(conn, n);
		
		// 두번 째 요청 결과를 받을 변수 세팅
		int result2 = 1;
		
		if(at != null) { // 새롭게 첨부된 파일이 있을 경우 -> Attachment에서 Update 또는 Insert
			
			if(at.getFileNo() != 0) { // 기존 첨부파일이 있는 경우 -> Attachment Update 요청
				
				result2 = new NewsDao().updateAttachment(conn, at);
			}
		}
		
		if(result1 > 0 && result2 > 0) {
			
			commit(conn);
		}
		else {
			
			rollback(conn);
		}
		
		close(conn);
		
		return result1 * result2;
	}
	
	/**
	 * 관리자 뉴스 게시글 삭제
	 * @param newsNo
	 * @param fileNo
	 * @return
	 */
	public int deleteNews(int newsNo) {
		
		Connection conn = getConnection();
		
		int result1 = new NewsDao().deleteNews(conn, newsNo);
		
		int result2 = new NewsDao().deleteAttachment(conn, newsNo);
		
		if(result1 > 0 && result2 > 0) {
			
			commit(conn);
		}
		else {
			
			rollback(conn);
		}
		
		return result1 * result2;
	}
	
	/**
	 * 검색할 키워드로 페이징 바에 들어갈 게시판 개수 구하기
	 * @return
	 */
	public int selectSearchCount(String keyword) {
		
		Connection conn = getConnection();
		
		int listCount = new NewsDao().selectSearchCount(conn, keyword);
		
		close(conn);
		
		return listCount;
	}
	
	/**
	 * 검색창에 키워드를 검색해서 게시판 조회
	 * @param keyword
	 * @return
	 */
	public ArrayList<News> searchNews(String keyword, PageInfo pi){
		
		Connection conn = getConnection();
		
		ArrayList<News> list = new NewsDao().searchNews(conn, keyword, pi);
		
		close(conn);
		
		return list;
	}
	
}






























