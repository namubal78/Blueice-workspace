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

import com.blueice.common.model.vo.PageInfo;
import com.blueice.information.model.vo.Notice;

public class NoticeDao {
	
	private Properties prop = new Properties();
	
	public NoticeDao() {
		
		String fileName = NoticeDao.class.getResource("/sql/information/information-mapper.xml").getPath();
		// -> class 파일(컴파일된 배포 파일) 폴더 기준으로 경로 설정  
		
		try {
			prop.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
// --------------------------------------------------------------------------------------------------------------------------------------------	

	// 페이징 처리 
	
		/* 검색창 NULL */
		/**
		 * 공지사항 게시글 개수를 조회하는 (SELECT) 서비스 
		 * @return listCount
		 */
		public int selectListCount(Connection conn) {
			
			// SELECT 문 -> ResultSet 객체 (그룹함수를 써서 한 행 조회) 
			
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
		 * 공지사항 게시글 전체 조회 (SELECT) 서비스 
		 * @param conn
		 * @param pi
		 * @return list
		 */
		public ArrayList<Notice> selectList(Connection conn, PageInfo pi) {
			
			// SELECT 문 -> ResultSet 객체 (여러행 조회)
			
			ArrayList<Notice> list = new ArrayList<>();
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			
			String sql = prop.getProperty("selectList");
			
			try {
				pstmt = conn.prepareStatement(sql);
				
				int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
				int endRow = startRow + pi.getBoardLimit() - 1;
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, endRow);
				
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					list.add(new Notice(rset.getInt("N_NO"),
										rset.getString("N_TITLE"),
										rset.getInt("N_HIT"),
										rset.getDate("N_DATE")));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rset);
				close(pstmt);
			}
			return list;
		}
		
		
		/* 검색시 */
		public int selectListSearchCount(Connection conn, String searchText) {
			
			// SELECT 문 -> ResultSet 객체 (그룹함수를 써서 한 행 조회) 
			
			int listCount = 0; 
			PreparedStatement pstmt = null; 
			ResultSet rset = null;
			
			String sql = prop.getProperty("selectListSearchCount");
			
				try {
					
					pstmt = conn.prepareStatement(sql);
					
					pstmt.setString(1, searchText);
					
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

		public ArrayList<Notice> selectSearchList(Connection conn, PageInfo pi, String searchText) {
			
			// SELECT 문 -> ResultSet 객체 (여러행 조회)

			ArrayList<Notice> list = new ArrayList<>();
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			
			String sql = prop.getProperty("selectSearchList");
			
			try {
				pstmt = conn.prepareStatement(sql);
				
				int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
				int endRow = startRow + pi.getBoardLimit() - 1;
				
				pstmt.setString(1, searchText);
				pstmt.setInt(2, startRow);
				pstmt.setInt(3, endRow);
				
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					list.add(new Notice(rset.getInt("N_NO"),
										rset.getString("N_TITLE"),
										rset.getInt("N_HIT"),
										rset.getDate("N_DATE")));
				}
				// System.out.println("Dao while문 다음" + list);
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(rset);
				close(pstmt);
			}
			// System.out.println("Dao return 전" + list);
			return list;
		}
		
// --------------------------------------------------------------------------------------------------------------------------------------------	

	/**
	 * 공지사항 게시글 등록(INSERT)을 요청하는 서비스 
	 * @param conn
	 * @param n
	 * @return result
	 */
	public int insertNotice(Connection conn, Notice n) {
		
		// INSERT 문 -> int (처리된 행의 개수)
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertNotice");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, n.getNoticeTitle());
			pstmt.setString(2, n.getNoticeContent());
			
			result = pstmt.executeUpdate();
			
			System.out.println(result);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

// --------------------------------------------------------------------------------------------------------------------------------------------
	// 공지사항 게시글 클릭시 상세보기 (조회수 증가 + 게시글 조회) 
	
	/**
	 * 공지사항 게시글 클릭시 조회수 증가(UPDATE)를 요청하는 서비스 
	 * @param conn
	 * @param noticeNo
	 * @return
	 */
	public int increaseCount(Connection conn, int noticeNo) {
		
		// UPDATE 문 -> int (처리된 행의 개수) 
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("increaseCount");
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, noticeNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	/**
	 * 공지사항 게시글 상세 조회(SELECT)를 요청하는 서비스
	 * @param conn
	 * @param noticeNo
	 * @return n
	 */
	public Notice selectNotice(Connection conn, int noticeNo) {
		
		// SELECT 문 -> ResultSet 객체 (Primary key 기준으로 조회하기 때문에 1건 이하) -> Notice 객체 
		Notice n = null;
		PreparedStatement pstmt = null; 
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectNotice");
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, noticeNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
			     
				n = new Notice(rset.getInt("N_NO"),
						rset.getString("N_TITLE"),
						rset.getDate("N_DATE"),
						rset.getInt("N_HIT"),
						rset.getString("N_CONTS"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return n;
	}

// --------------------------------------------------------------------------------------------------------------------------------------------

	public int updateNotice(Connection conn, Notice n) {
		
		// UPDATE 문 -> int (처리된 행의 개수)
		
		int result = 0; 
		PreparedStatement pstmt = null; 
		
		String sql = prop.getProperty("updateNotice");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, n.getNoticeTitle());
			pstmt.setString(2, n.getNoticeContent());
			pstmt.setInt(3, n.getNoticeNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

// --------------------------------------------------------------------------------------------------------------------------------------------

	/**
	 * 공지사항 게시글 삭제(UPDATE)을 요청하는 서비스 
	 * @param conn
	 * @param noticeNo
	 * @return result
	 */
	public int deleteNotice(Connection conn, int noticeNo) {
		
		// UPDATE 문 -> int (처리된 행의 개수)
		
		int result = 0; 
		PreparedStatement pstmt = null; 
		
		String sql = prop.getProperty("deleteNotice");
				
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, noticeNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}
}

















