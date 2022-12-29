package com.blueice.challenge.model.service;

import static com.blueice.common.JDBCTemplate.close;
import static com.blueice.common.JDBCTemplate.commit;
import static com.blueice.common.JDBCTemplate.getConnection;
import static com.blueice.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.blueice.challenge.model.dao.ChallengeDao;
import com.blueice.challenge.model.vo.Challenge;
import com.blueice.challenge.model.vo.ChallengeAttachment;
import com.blueice.challenge.model.vo.ChallengeComment;
import com.blueice.common.model.vo.PageInfo;

public class ChallengeService {

	/**
	 * 챌린지 게시판 리스트 조회
	 * @return
	 */
	public ArrayList<Challenge> selectChallengeList(){
		
		Connection conn = getConnection();
		
		ArrayList<Challenge> list = new ChallengeDao().selectChallengeList(conn);
		
		close(conn);
		
		return list;
	}
	
	/**
	 * 챌린지 댓글 게시판 챌린지 내용 가져오기
	 * @param chalNo
	 * @return
	 */
	public Challenge selectChallengeComment(int chalNo) {
		
		Connection conn = getConnection();
		
		Challenge ch = new ChallengeDao().selectChallengeComment(conn, chalNo);
		
		close(conn);
		
		return ch;
	}
	
	/**
	 * 페이징 처리 때 필요한 게시글 총 개수 구하기
	 * @return
	 */
	public int selectListCount(int chalNo) {
		
		Connection conn = getConnection();
		
		int listCount = new ChallengeDao().selectListCount(conn, chalNo);
		
		close(conn);
		
		return listCount;
	}
	
	/**
	 * 게시글 조회 시 조회수 증가
	 * @param chalNo
	 * @return
	 */
	public int countListChallenge(int chalNo) {
		
		Connection conn = getConnection();
		
		int result = new ChallengeDao().countListChallenge(conn, chalNo);
		
		if(result > 0) {
			
			commit(conn);
		}
		else {
			
			rollback(conn);
		}
		
		return result;
	}
	
	/**
	 * 챌린지 댓글 리스트 조회
	 * @param pi
	 * @return
	 */
	public ArrayList<ChallengeComment> selectChallengeCommentList(PageInfo pi, int chalNo){
		
		Connection conn = getConnection();
		
		ArrayList<ChallengeComment> chList = new ChallengeDao().selectChallengeCommentList(conn, pi, chalNo);
		
		close(conn);
		
		return chList;
	}
	
	/**
	 * 챌린지 게시글, 첨부파일 삽입
	 * @param c
	 * @param chat
	 * @return
	 */
	public int insertChallenge(Challenge c) {
		
		Connection conn = getConnection();
		
		int result = new ChallengeDao().insertChallenge(conn, c);
		
		if(result > 0) {
			
			commit(conn);
		}
		else {
			
			rollback(conn);
		}
		
		return result;
	}
	
	/**
	 * 챌린지 게시글 수정폼 가져오기
	 * @param chalNo
	 * @return
	 */
	public Challenge updateFormChallenge(int chalNo) {
		
		Connection conn = getConnection();
		
		Challenge ch = new ChallengeDao().updateFormChallenge(conn, chalNo);
		
		close(conn);
		
		return ch;
	}
	
	/**
	 * 챌린지 게시글 삭제
	 * @param chalNo
	 * @return
	 */
	public int deleteChallenge(int chalNo) {
		
		Connection conn = getConnection();
		
		int result = new ChallengeDao().deleteChallenge(conn, chalNo);
		
		if(result > 0) {
			
			commit(conn);
		}
		else {
			
			rollback(conn);
		}
		
		return result;
	}
	
	/**
	 * 챌린지 게시글 내용, 첨부파일 수정
	 * @param c
	 * @return
	 */
	public int updateChallenge(Challenge c) {
		
		Connection conn = getConnection();
		
		int result = new ChallengeDao().updateChallenge(conn, c);
		
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
	 * 챌린지 댓글 작성 후 삽입
	 * @param chco
	 * @param chat
	 * @return
	 */
	public int insertChallengeComment(ChallengeComment chco, ChallengeAttachment chat) {
		
		Connection conn = getConnection();
		
		int result1 = new ChallengeDao().insertChallengeComment(conn, chco);
		
		int result2 = 0;
		
		if(result1 > 0) {
			
			result2 = new ChallengeDao().insertChallengeCommentAttachment(conn, chat);
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
	 * 챌린지 댓글 내용, 첨부파일 삭제
	 * @param ccno
	 * @return
	 */
	public int deleteChallengeComment(int ccno) {
		
		Connection conn = getConnection();
		
		int result1 = new ChallengeDao().deleteChallengeComment(conn, ccno);
		
		int result2 = 1;
		
		if(result1 > 0) {
			
			result2 = new ChallengeDao().deleteChallengeCommentAttachment(conn, ccno);
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
	
	public ArrayList<Challenge> ChallengeRewardCommentNo() {
		
		Connection conn = getConnection();
		
		ArrayList<Challenge> chList = new ChallengeDao().ChallengeRewardCommentNo(conn);
		
		close(conn);
		
		return chList;
	}
	
	public ArrayList<ChallengeComment> ChallengeRewardName(ChallengeComment cno) {
		
		Connection conn = getConnection();
		
		ArrayList<ChallengeComment> rewardName = new ChallengeDao().ChallengeRewardName(conn, cno);
		
		close(conn);
		
		return rewardName;
	}
}


























