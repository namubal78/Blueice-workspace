package com.blueice.challenge.model.service;

import static com.blueice.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import com.blueice.challenge.model.dao.RewardDao;
import com.blueice.challenge.model.vo.Reward;
import com.blueice.challenge.model.vo.RewardAttachment;
import com.sun.xml.internal.ws.api.message.Attachment;

public class RewardService {
	
	/**
	 * 공지사항 전체 조회용 서비스
	 * @return
	 */
	 public ArrayList<Reward> selectRewardList(){
		 
		 Connection conn = getConnection();
		 
		 ArrayList<Reward> list = new RewardDao().selectRewardList(conn);
		 
		 close(conn);
		 
		 return list; 
	 }
	 
	 /**
	  * 리워드 게시글 삽입
	  * @param r
	  * @param ra
	  * @return
	  */
	 public int insertReward(Reward r, RewardAttachment ra) {
		 
		 Connection conn = getConnection();
		 
		 // Reward 테이블에 INSERT 요청
		 int result1 = new RewardDao().insertReward(conn, r);
		 
		 // 첨부파일이 있다면 Reward 테이블에 INSERT 요청 보내기
		 
		 // 두번째 요청에 대한 결과값을 담을 변수 셋팅
		 int result2 = 1;
		 
		 if(ra != null) { // 첨부파일이 있다면
			 
			 result2 = new RewardDao().insertRewardAttachment(conn, ra);
		 }
		 
		 // 트랜잭션 처리
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
	  * 조회수 증가용 서비스
	  * @param rewardNo
	  * @return
	  */
	 public int increaseCount(int rewardNo) {
		 
		 Connection conn = getConnection();
		 
		 int result = new RewardDao().increaseCount(conn, rewardNo);
		 
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
	  * 상세페이지 조회
	  * @param Reward
	  * @return
	  */
	 public Reward selectReward(int reward) {
		 
		 Connection conn = getConnection();
		 
		 Reward r = new RewardDao().selectReward(conn, reward);
		 
		 close(conn);
		 
		 return r;
	 }
	 
	 /**
	  * 첨부파일 리스트 조회
	  * @param chalRewardNo
	  * @return
	  */
	 public RewardAttachment selectRewardAttachment(int chalRewardNo){
		 
		 Connection conn = getConnection();
		 
		 RewardAttachment ra = new RewardDao().selectRewardAttachment(conn, chalRewardNo);
		 
		 close(conn);
		 
		 return ra;
	 }
	
	 /**
	  * 리워드 수정 페이지 폼 가져오기 (내용)
	  * @param rewardNo
	  * @return
	  */
	 public Reward updateFormReward(int chalRewardNo) {
		 
		 Connection conn = getConnection();
		 
		 Reward r = new RewardDao().updateFormReward(conn, chalRewardNo);
		 
		 close(conn);
		 
		 return r;
	 }
	 
	 /**
	  * 리워드 수정 페이지 폼 가져오기 (첨부파일)
	  * @param chalRewardNo
	  * @return
	  */
	 public RewardAttachment updateFormRewardAttachment(int chalRewardNo) {
		 
		 Connection conn = getConnection();
		 
		 RewardAttachment ra = new RewardDao().updateFormRewardAttachment(conn, chalRewardNo);
		 
		 close(conn);
		 
		 return ra;
	 }
	 
	 /**
	  * 게시글 수정 결과 받기
	  * @param r
	  * @param ra
	  * @return
	  */
	 public int updateReward(Reward r, RewardAttachment ra) {
		 
		 Connection conn = getConnection();
		 
		 int result1 = new RewardDao().updateReward(conn, r);
		 
		 int result2 = 1;
		 
		 if(ra != null) {
			 
			 if(ra.getReFileNo() != 0) {
				 
				 result2 = new RewardDao().updateRewardAttachment(conn, ra);
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
	  * 게시글 삭제
	  * @param chalRewardNo
	  * @return
	  */
	 public int deleteReward(int chalRewardNo, int reFileNo) {
		 
		 Connection conn = getConnection();
		 
		 int result1 = 1;
		 if(reFileNo != 0) {
			 result1 = new RewardDao().deleteRewardAttachment(conn, reFileNo);
		 }
		 
		 int result2 = new RewardDao().deleteReward(conn, chalRewardNo);
		 
		 if(result1 > 0 && result2 > 0) {
			 commit(conn);
		 }
		 else {
			 rollback(conn);
		 }
		 
		 return result1 * result2;
	 }
}
