package com.blueice.challenge.model.dao;

import static com.blueice.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.blueice.challenge.model.vo.Challenge;
import com.blueice.challenge.model.vo.ChallengeAttachment;
import com.blueice.challenge.model.vo.ChallengeComment;
import com.blueice.common.model.vo.PageInfo;

public class ChallengeDao {

	Properties prop = new Properties();
	
	public ChallengeDao() {
			
		String fileName = ChallengeDao.class.getResource("/sql/challenge/challenge-mapper.xml").getPath();
		
		try {
			prop.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 챌린지 게시판 리스트 조회
	 * @return
	 */
	public ArrayList<Challenge> selectChallengeList(Connection conn){
		
		// SELECT문 실행 -> 여러 행 조회
		ArrayList<Challenge> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectChallengeList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				Challenge ch = new Challenge(rset.getInt("CHAL_NO"),
											 rset.getString("CHAL_TITLE"),
											 rset.getString("CHAL_START"),
											 rset.getString("CHAL_END"),
											 rset.getInt("CHAL_HIT"),
											 rset.getString("CHAL_STATUS"));
				
				list.add(ch);
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
	 * 챌린지 댓글 게시판에서 챌린지 내용 조회
	 * @param conn
	 * @param chalNo
	 * @return
	 */
	public Challenge selectChallengeComment(Connection conn, int chalNo) {
		
		// SELECT문 조회 -> 한 행만 조회
		
		Challenge ch = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectChallengeComment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, chalNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				
				ch = new Challenge();
				ch.setChalNo(rset.getInt("CHAL_NO"));
				ch.setChalTitle(rset.getString("CHAL_TITLE"));
				ch.setChalConts(rset.getString("CHAL_CONTS"));
				ch.setChalStart(rset.getString("CHAL_START"));
				ch.setChalHit(rset.getInt("CHAL_HIT"));
				ch.setTitleImg(rset.getString("TITLEIMG"));
				ch.setChalStatus(rset.getString("CHAL_STATUS"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			close(rset);
			close(pstmt);
		}
		
		return ch;
	}
	
	/**
	 * 챌린지 게시글 조회 시 조회수 증가
	 * @param conn
	 * @param chalNo
	 * @return
	 */
	public int countListChallenge(Connection conn, int chalNo) {
		
		// UPDATE구문 -> 조회수 증가
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("countListChallenge");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, chalNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			close(pstmt);
		}
		
		return result;		
	}
	
	/**
	 * 페이징 처리 시 필요한 총 게시글 수 구하기
	 * @param conn
	 * @return
	 */
	public int selectListCount(Connection conn, int chalNo) {
		
		// SELECT문 -> 여러행 조회 int에 개수 담기
		
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectListCount");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, chalNo);
			
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
	 * 챌린지 댓글 리스트 조회
	 * @param conn
	 * @param pi
	 * @return
	 */
	public ArrayList<ChallengeComment> selectChallengeCommentList(Connection conn, PageInfo pi, int chalNo){
		
		// SELECT문 -> 여러 행 조회 
		ArrayList<ChallengeComment> chList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectChallengeCommentList");		
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
						
			pstmt.setInt(1, chalNo);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				ChallengeComment ch = new ChallengeComment();
				ch.setChalComNo(rset.getInt("CHAL_COM_NO"));
				ch.setChalComConts(rset.getString("CHAL_COM_CONTS"));
				ch.setChalComDate(rset.getString("CHAL_COM_DATE"));
				ch.setTitleImg(rset.getString("TITLEIMG"));
				ch.setMemNo(rset.getString("MEM_NAME"));
				
				chList.add(ch);
			}
									
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			close(rset);
			close(pstmt);
		}
		
		return chList;	
	}
	
	/**
	 * 챌린지 게시글 내용, 첨부파일 삽입
	 * @param conn
	 * @param c
	 * @return
	 */
	public int insertChallenge(Connection conn, Challenge c) {
		
		// INSERT문 사용 -> 성공 실패 반환
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertChallenge");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, c.getChalTitle());
			pstmt.setString(2, c.getChalConts());
			pstmt.setString(3, c.getChallengeOriginName());
			pstmt.setString(4, c.getChallengeChangeName());
			pstmt.setString(5, c.getChallengeFilePath());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			
			close(pstmt);
		}
		
		return result;
	}
	
	/**
	 * 챌린지 게시글 수정 폼 가져오기
	 * @param conn
	 * @param chalNo
	 * @return
	 */
	public Challenge updateFormChallenge(Connection conn, int chalNo) {
		
		// SELECT문 -> 한 행만 조회
		
		Challenge ch = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("updateFormChallenge");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, chalNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				
				ch = new Challenge();
				ch.setChalNo(rset.getInt("CHAL_NO"));
				ch.setChalTitle(rset.getString("CHAL_TITLE"));
				ch.setChalConts(rset.getString("CHAL_CONTS"));
				ch.setChallengeOriginName(rset.getString("CHALLENGE_ORIGIN_NAME"));
			}
						
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			close(rset);
			close(pstmt);
		}
		
		return ch;
	}
	
	/**
	 * 챌린지 게시글 삭제
	 * @param conn
	 * @param chalNo
	 * @return
	 */
	public int deleteChallenge(Connection conn, int chalNo) {
		
		// UPDATE문 실행 -> Y에서 N으로 변경
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteChallenge");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, chalNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			close(pstmt);
		}
		
		return result;		
	}
	
	/**
	 * 챌린지 게시글 내용, 첨부파일 수정
	 * @param conn
	 * @param c
	 * @return
	 */
	public int updateChallenge(Connection conn, Challenge c) {
		
		// UPDATE문 실행 -> 성공 시 1반환
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updateChallenge");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, c.getChalTitle());
			pstmt.setString(2, c.getChalConts());
			pstmt.setString(3, c.getChallengeOriginName());
			pstmt.setString(4, c.getChallengeChangeName());
			pstmt.setString(5, c.getChallengeFilePath());
			pstmt.setInt(6, c.getChalNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			close(pstmt);
		}
		
		return result;
	}
	
	/**
	 * 챌린지 댓글만 삽입
	 * @param conn
	 * @param chco
	 * @return
	 */
	public int insertChallengeComment(Connection conn, ChallengeComment chco) {
		
		// INSERT문 작성 -> 성공 시 1반환
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertChallengeComment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, chco.getChalComConts());
			pstmt.setInt(2, chco.getChalNo());
			pstmt.setInt(3, Integer.parseInt(chco.getMemNo()));
						
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			close(pstmt);
		}
		
		return result;
	}
	
	/**
	 * 챌린지 댓글 첨부파일만 삽입
	 * @param conn
	 * @param chat
	 * @return
	 */
	public int insertChallengeCommentAttachment(Connection conn, ChallengeAttachment chat) {
		
		// INSERT문 실행 -> 성공 시 1을 반환
		 
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertChallengeCommentAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, chat.getChalOriginName());
			pstmt.setString(2, chat.getChalChangeName());
			pstmt.setString(3, chat.getChalFilePath());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			close(pstmt);
		}
		
		return result;
	}
	
	/**
	 * 챌린지 댓글 삭제
	 * @param conn
	 * @param ccno
	 * @return
	 */
	public int deleteChallengeComment(Connection conn, int ccno) {
		
		// UPDATE문 사용 삭제 -> 성공 시 1반환
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String sql = prop.getProperty("deleteChallengeComment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, ccno);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			close(pstmt);
		}
		
		return result;		
	}
	
	/**
	 * 챌린지 댓글 첨부파일 삭제
	 * @param conn
	 * @param ccno
	 * @return
	 */
	public int deleteChallengeCommentAttachment(Connection conn, int ccno) {
		
		// UPDATE문 사용 삭제 -> 성공 시 1반환
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String sql = prop.getProperty("deleteChallengeCommentAttachment");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, ccno);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			close(pstmt);
		}
		
		return result;
	}
	
	public ArrayList<Challenge> ChallengeRewardCommentNo(Connection conn) {
		
		// SELECT문 조회 -> 한 행만 조회
		ArrayList<Challenge> chList = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("ChallengeRewardCommentNo");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				Challenge ch = new Challenge();
				
				ch.setChalNo(rset.getInt("CHAL_NO"));
				ch.setChalTitle(rset.getString("CHAL_TITLE"));
				ch.setChalComNo(rset.getInt("CHAL_COM_NO"));
				
				chList.add(ch);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			close(rset);
			close(pstmt);
		}
		
		return chList;
	}
	
	public ArrayList<ChallengeComment> ChallengeRewardName(Connection conn, ChallengeComment cno){
		
		// SELECT문 실행  -> 여러행 조회
		ArrayList<ChallengeComment> rewardName = new ArrayList<>();
		ChallengeComment cc = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("ChallengeRewardName");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, Integer.parseInt(cno.getChalComNo1()));
			pstmt.setInt(2, Integer.parseInt(cno.getChalComNo2()));
			pstmt.setInt(3, Integer.parseInt(cno.getChalComNo3()));
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				cc = new ChallengeComment();
				
				cc.setMemNo(rset.getString("MEM_NAME"));
				
				rewardName.add(cc);
			}
						
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			
			close(rset);
			close(pstmt);
		}
		
		return rewardName;
	}
	
}



























