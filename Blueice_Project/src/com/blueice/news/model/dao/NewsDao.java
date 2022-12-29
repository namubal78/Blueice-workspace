package com.blueice.news.model.dao;

import static com.blueice.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.blueice.news.model.vo.Attachment;
import com.blueice.news.model.vo.News;
import com.blueice.common.model.vo.PageInfo;

public class NewsDao {
	
	private Properties prop = new Properties();
	
	public NewsDao() {
		
		String fileName = NewsDao.class.getResource("/sql/news/news-mapper.xml").getPath();
		
		try {
			prop.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 페이징 바에 들어갈 게시판 개수 구하기
	 * #사용됨
	 * @param conn
	 * @return
	 */
	public int selectListCount(Connection conn) {
		
		// SELECT문 -> ResultSet객체 (그룹함수를 써서 한 행 조회)
		
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
	 * 각 페이지마다 들어갈 게시글 조회, 이미지 포함 
	 * #사용됨
	 * @param pi
	 * @return
	 */
	public ArrayList<News> selectPaging(Connection conn, PageInfo pi){
		
		// SELECT문 -> 여러 행 조회
		
		ArrayList<News> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectPaging");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()){
				
				News n = new News();
				n.setNewsNo(rset.getInt("NEWS_NO"));
				n.setNewsTitle(rset.getString("NEWS_TITLE"));
				n.setNewsHit(rset.getInt("NEWS_HIT"));
				n.setNewsDate(rset.getString("NEWS_DATE"));
				n.setTitleImg(rset.getString("TITLEIMG"));
				n.setAdminNo(rset.getInt("ADMIN_NO"));
								
				list.add(n);				
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
	 * 뉴스 상세조회 페이지 조회수 조회
	 */
	public int countNewsPage(Connection conn, int newsNo) {
		
		// UPDATE문 -> int(처리된 행의 개수)
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("countNewsPage");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, newsNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			close(pstmt);
		}
		
		return result;		
	}
	
	/**
	 * 뉴스 상세조회 페이지 조회
	 * #사용됨
	 */
	public News selectNewsPage(Connection conn, int newsNo) {
		
		// SELECT문 조회 -> 게시글 번호로 조회 하기 때문에 한 행만 조회
		
		News n = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectNewsPage");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, newsNo);
						
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				
				n = new News();				
				n.setNewsNo(rset.getInt("NEWS_NO"));
				n.setNewsTitle(rset.getString("NEWS_TITLE"));
				n.setNewsConts(rset.getString("NEWS_CONTS"));
				n.setNewsDate(rset.getString("NEWS_DATE"));
				n.setNewsHit(rset.getInt("NEWS_HIT"));
				n.setTitleImg(rset.getString("TITLEIMG"));
				n.setAdminNo(rset.getInt("ADMIN_NO"));
			}
									
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			close(rset);
			close(pstmt);
		}
		
		return n;
	}
	
	/**
	 * 뉴스 관리자 게시글 삽입
	 * @param conn
	 * @param n
	 * @return
	 */
	public int insertNews(Connection conn, News n) {
		
		// INSERT문 -> 삽입된 행이 조회
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String sql = prop.getProperty("insertNews");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, n.getNewsTitle());
			pstmt.setString(2, n.getNewsConts());
			
			result = pstmt.executeUpdate();
						
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			close(pstmt);
		}
		
		return result;		
	}
	
	/**
	 * 뉴스 게시판 첨부파일 삽입
	 * @param conn
	 * @param at
	 * @return
	 */
	public int insertNewsAttachment(Connection conn, Attachment at) {
		
		// INSERT문 실행 -> 1개의 행 삽입
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertNewsAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, at.getOriginName());
			pstmt.setString(2, at.getChangeName());
			pstmt.setString(3, at.getFilePath());
			
			result = pstmt.executeUpdate();
						
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			close(pstmt);
		}
		
		return result;
	}
	
	/**
	 * 뉴스 수정 페이지 수정 폼 가져오기(내용)
	 * @param conn
	 * @param newsNo
	 * @return
	 */
	public News updateFormNews(Connection conn, int newsNo) {
		
		// SELECT문 -> 글번호로 1행만 조회
		News n = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("updateFormNews");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, newsNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				
				n = new News(rset.getInt("NEWS_No")
						   , rset.getString("NEWS_TITLE")
						   , rset.getString("NEWS_CONTS")
						   , rset.getString("NEWS_DATE"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			close(rset);
			close(pstmt);
		}
		
		return n;
	}
	
	/**
	 * 뉴스 수정 페이지 수정 폼 가져오기(첨부파일)
	 * @param conn
	 * @param at
	 * @return
	 */
	public Attachment updateFormAttachment(Connection conn, int newsNo) {
		
		// SELECT문 -> 번호로 조회해서 한 행만 조회
		
		Attachment at = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("updateFormAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, newsNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				
				at = new Attachment(rset.getInt("FILE_NO")
								  , rset.getString("ORIGIN_NAME")
								  , rset.getString("CHANGE_NAME")
								  , rset.getString("FILE_PATH"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			close(rset);
			close(pstmt);
		}
		
		return at;		
	}
	
	/**
	 * 뉴스 게시판 내용 수정
	 * @param conn
	 * @param n
	 * @return
	 */
	public int updateNews(Connection conn, News n) {
		
		// UPDATE문 실행 -> 1행만 조회
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updateNews");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, n.getNewsTitle());
			pstmt.setString(2, n.getNewsConts());
			pstmt.setInt(3, n.getNewsNo());
			
			result = pstmt.executeUpdate();
						
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			close(pstmt);
		}
		
		return result;
	}
	
	/**
	 * 뉴스 게시판 첨부파일 수정
	 * @param conn
	 * @param at
	 * @return
	 */
	public int updateAttachment(Connection conn, Attachment at) {
		
		// UPDATE구문 -> 한행만 조회
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updateAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, at.getOriginName());
			pstmt.setString(2, at.getChangeName());
			pstmt.setString(3, at.getFilePath());
			pstmt.setInt(4, at.getFileNo());
			
			result = pstmt.executeUpdate();
						
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			close(pstmt);
		}
		
		return result;
	}
	
	/**
	 * 뉴스 게시글 삭제
	 * @param conn
	 * @param newsNo
	 * @return
	 */
	public int deleteNews(Connection conn, int newsNo) {
		
		// UPDATE문 -> 한 행만 조회
		
		int result = 0; 
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteNews");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, newsNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			close(pstmt);
		}
		
		return result;
	}
	
	/**
	 * 뉴스 게시글 삭제와 동시에 첨부파일 삭제
	 * @param conn
	 * @param newsNo
	 * @return
	 */
	public int deleteAttachment(Connection conn, int newsNo) {
		
		// UPDATE문 실행 -> 한 행만 조회
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, newsNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			close(pstmt);
		}
		
		return result;		
	}
	
	/**
	 * 검색할 키워드로 페이징 바에 들어갈 게시판 개수 구하기
	 * #사용됨
	 * @param conn
	 * @return
	 */
	public int selectSearchCount(Connection conn, String keyword) {
		
		// SELECT문 -> ResultSet객체 (그룹함수를 써서 한 행 조회)
		
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectSearchCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, keyword);
			
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
	 * 검색창에 키워드를 검색해서 게시판 조회
	 * @param keyword
	 * @return
	 */
	public ArrayList<News> searchNews(Connection conn, String keyword, PageInfo pi){
		
		// SELECT문 -> 키워드 포함으로 검색 여러 행 조회
		
		ArrayList<News> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("searchNews");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
						
			pstmt.setString(1, keyword);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				News n = new News();
				n.setNewsNo(rset.getInt("NEWS_NO"));
				n.setNewsTitle(rset.getString("NEWS_TITLE"));
				n.setNewsHit(rset.getInt("NEWS_HIT"));
				n.setNewsDate(rset.getString("NEWS_DATE"));
				n.setTitleImg(rset.getString("TITLEIMG"));
				n.setAdminNo(rset.getInt("ADMIN_NO"));
				
				list.add(n);
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






























