package com.blueice.member.model.service;

import static com.blueice.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import com.blueice.member.model.dao.MemberDao;
import com.blueice.member.model.vo.BillGraph;
import com.blueice.member.model.vo.Member;
import com.blueice.common.model.vo.PageInfo;

public class MemberService {

    /**
     * 회원 로그인 서비스
     * @param m
     * @return
     */
    public Member loginMember(Member m) {
        
        Connection conn = getConnection();
        
        Member loginMember = new MemberDao().loginMember(conn, m);
        
        close(conn);
        
        return loginMember;
        
    }
    
    /**
     * 개인회원 가입 서비스
     * @param m
     * @return
     */
    public int insertMember(Member m) {
        
        Connection conn = getConnection();
        
        int result = new MemberDao().insertMember(conn, m);
        
        if(result > 0) {
            commit(conn);
        } else {
            rollback(conn);
        }
        
        return result;
        
    }
    
    /**
     * 기업회원 가입 서비스
     * @param m
     * @return
     */
    public int companyInsertMember(Member m) {
        
        Connection conn = getConnection();
        
        int result = new MemberDao().companyInsertMember(conn, m);
        
        if(result > 0) {
            commit(conn);
        } else {
            rollback(conn);
        }
        
        return result;
        
    }
    
    /**
     * 단체회원 가입 서비스
     * @param m
     * @return
     */
    public int groupInsertMember(Member m) {
        
        Connection conn = getConnection();
        
        int result = new MemberDao().groupInsertMember(conn, m);
        
        if(result > 0) {
            commit(conn);
        } else {
            rollback(conn);
        }
        
        return result;
        
        
      
    }
    
    /**
     * 아이디 중복체크 서비스
     * @param checkId
     * @return
     */
    public int idCheck(String checkId) {
        
        Connection conn = getConnection();
        
        int count = new MemberDao().idCheck(conn, checkId);
        
        close(conn);
        
        return count;
    }
    
    /**
     * 아이디 찾기 서비스
     * @param m
     * @return
     */
    public Member findIdMember(Member m) {
        
        Connection conn = getConnection();
        
        Member findIdMember = new MemberDao().findIdMember(conn, m);
        
        close(conn);
        
        return findIdMember;
    }
    
    /**
     * 패스워드 찾기 서비스
     * @param m
     * @return
     */
    public Member findPasswordMember(Member m) {
        
        Connection conn = getConnection();
        
        Member findPasswordMember = new MemberDao().findPasswordMember(conn, m);
        
        close(conn);
        
        return findPasswordMember;
    }
    
    /**
     * 회원 정보수정 서비스
     * @param m
     * @return
     */
    public Member updateMember(Member m) {
        
        Connection conn = getConnection();
        
        int result = new MemberDao().updateMember(conn, m);
        
        Member updateMem = null;
        
        if(result > 0) { // 회원정보 수정 성공 => commit
            commit(conn);
            
            // 갱신된 회원 객체를 다시 조회
            updateMem = new MemberDao().selectMember(conn, m.getMemId());
        }
        else { // 회원정보 수정 실패 => rollback
            rollback(conn);
        }
        
        close(conn);
        
            return updateMem; // 성공 : 갱신된 회원 정보, 실패 : null
    }
    
    /**
     * 비밀번호 변경 서비스
     * @param m
     * @param updatePwd
     * @return
     */
    public Member updatePwdMember(Member m, String updatePwd) {
        
        Connection conn = getConnection();
        
        int result = new MemberDao().updatePwdMember(conn, m, updatePwd);
        
        Member updateMem = null;
        
        if(result > 0) { // 성공 => commit
            commit(conn);
            
            // 갱신된 회원의 정보 다시 조회하기
            updateMem = new MemberDao().selectMember(conn, m.getMemId());
        }
        else { // 실패 => rollback
            rollback(conn);
        }
        
            close(conn);
        
        return updateMem;
    }
    
    /**
     * 회원 탈퇴 서비스
     * @param m
     * @return
     */
    public int deleteMember(Member m) {
        
        Connection conn = getConnection();
        
        int result = new MemberDao().deleteMember(conn, m);
        
        if(result > 0) {
            commit(conn);
        }
        else {
            rollback(conn);
        }
        
            close(conn);
        
        return result;
    }
    
    // 그룹회원 전체 페이징용 리스트카운트
	public int selectListCountGroupAll() {
		
		Connection conn = getConnection();
		
		int listCount = new MemberDao().selectListCountGroupAll(conn);
		
		close(conn);
		
		return listCount;
	}
	
	// 그룹회원 번호조회 페이징용 리스트카운트
	public int selectListCountGroupMemNo(int memNo) {
		
		Connection conn = getConnection();
		
		int listCount = new MemberDao().selectListCountGroupMemNo(conn, memNo);
		
		close(conn);
		
		return listCount;
	}
	
	// 그룹회원 이름조회 페이징용 리스트카운트
	public int selectListCountGroupMemName(String memName) {
		
		Connection conn = getConnection();
		
		int listCount = new MemberDao().selectListCountGroupMemName(conn, memName);
		
		close(conn);
		
		return listCount;
	}
	
	// 그룹회원 가입일조회 페이징용 리스트카운트
	public int selectListCountGroupDate(String enrollDate) {
		
		Connection conn = getConnection();
		
		int listCount = new MemberDao().selectListCountGroupDate(conn, enrollDate);
		
		close(conn);
		
		return listCount;
	}
	
	// 개인회원 전체 페이징용 리스트카운트
	public int selectListCountAll() {
			
		Connection conn = getConnection();
		
		int listCount = new MemberDao().selectListCountAll(conn);
		
		close(conn);
		
		return listCount;
	}
	
	// 개인회원 번호조회 페이징용 리스트카운트
	public int selectListCountMemNo(int memNo) {
		
		Connection conn = getConnection();
		
		int listCount = new MemberDao().selectListCountMemNo(conn, memNo);
		
		close(conn);
		
		return listCount;
	}
	
	// 개인회원 이름조회 페이징용 리스트카운트
	public int selectListCountMemName(String memName) {
		
		Connection conn = getConnection();
		
		int listCount = new MemberDao().selectListCountMemName(conn, memName);
		
		close(conn);
		
		return listCount;
	}
	
	// 개인회원 가입일조회 페이징용 리스트카운트
	public int selectListCountDate(String enrollDate) {
		
		Connection conn = getConnection();
		
		int listCount = new MemberDao().selectListCountDate(conn, enrollDate);
		
		close(conn);
		
		return listCount;
	}
    
    /**
     * 관리자 단체/기업회원 전체 조회 서비스
     * @param pi
     * @return
     */
    public ArrayList<Member> selectGroupMemberListAll(PageInfo pi) {
        
        Connection conn = getConnection();
        
        ArrayList<Member> list = new MemberDao().selectGroupMemberListAll(conn, pi);
        
        close(conn);
        
        return list;
    }
    
    /**
     * 관리자 단체/기업회원 회원번호로 찾기 서비스
     * @param memNo
     * @param pi
     * @return
     */
    public ArrayList<Member> selectGroupMemberListMemNo(int memNo, PageInfo pi) {
        
        Connection conn = getConnection();
        
        ArrayList<Member> list = new MemberDao().selectGroupMemberListMemNo(conn, memNo, pi);
        
        close(conn);
        
        return list;
    }

    /**
     * 관리자 단체/기업회원 회원이름으로 찾기 서비스
     * @param memName
     * @param pi
     * @return
     */
    public ArrayList<Member> selectGroupMemberListMemName(String memName, PageInfo pi) {
        
        Connection conn = getConnection();
        
        ArrayList<Member> list = new MemberDao().selectGroupMemberListMemName(conn, memName, pi);
        
        close(conn);
        
        return list;
    }
    
    /**
     * 관리자 단체/기업회원 가입일로 찾기 서비스
     * @param enrollDate
     * @param pi
     * @return
     */
    public ArrayList<Member> selectGroupMemberListDate(String enrollDate, PageInfo pi) {
        
        Connection conn = getConnection();
        
        ArrayList<Member> list = new MemberDao().selectGroupMemberListDate(conn, enrollDate, pi);
        
        close(conn);
        
        return list;
    }
    
    /**
     * 관리자 개인회원 전체 리스트 조회
     * @param pi
     * @return
     */
    public ArrayList<Member> selectMemberListAll(PageInfo pi) {
        
        Connection conn = getConnection();
        
        ArrayList<Member> list = new MemberDao().selectMemberListAll(conn, pi);
        
        close(conn);
        
        return list;
    }
    
    /**
     * 관리자 회원번호로 찾기 서비스
     * @param memNo
     * @param pi
     * @return
     */
    public ArrayList<Member> selectMemberListMemNo(int memNo, PageInfo pi) {
        
        Connection conn = getConnection();
        
        ArrayList<Member> list = new MemberDao().selectMemberListMemNo(conn, memNo, pi);
        
        close(conn);
        
        return list;
    }

    /**
     * 관리자 회원이름으로 찾기 서비스
     * @param memName
     * @param pi
     * @return
     */
    public ArrayList<Member> selectMemberListMemName(String memName, PageInfo pi) {
        
        Connection conn = getConnection();
        
        ArrayList<Member> list = new MemberDao().selectMemberListMemName(conn, memName, pi);
        
        close(conn);
        
        return list;
    }
    
    /**
     * 관리자 가입일로 찾기 서비스
     * @param enrollDate
     * @param pi
     * @return
     */
    public ArrayList<Member> selectMemberListDate(String enrollDate, PageInfo pi) {
        
        Connection conn = getConnection();
        
        ArrayList<Member> list = new MemberDao().selectMemberListDate(conn, enrollDate, pi);
        
        close(conn);
        
        return list;
    }
    
    /**
     * 관리자 회원관리 상세조회 서비스
     * @param memNo
     * @return
     */
    public Member detailMember(int memNo) {
        
        Connection conn = getConnection();
        
        Member list = new MemberDao().detailMember(conn, memNo);
        
        close(conn);
        
        return list;
    }
    
    /** 
     * 관리자 회원탈퇴 서비스
     * @param memNo
     * @return
     */
    public int deleteAdminMember(int memNo) {
        
        Connection conn = getConnection();
        
        int result = new MemberDao().deleteAdminMember(conn, memNo);

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
     * 회원 번호(PK)를 이용한 회원 정보 조회 요청 서비스
     * @param memNo
     * @return m
     */
    public Member selectMemberNo(int memNo) {
        
        Connection conn = getConnection();
        
        Member m = new MemberDao().selectMemberNo(conn, memNo);
        
        close(conn);
        
        return m;
    }

    
    /**
     * 멤버 현황 도넛 그래프
     * @return
     */
    public ArrayList countMemberList(){
    	
    	Connection conn = getConnection();
    	
    	ArrayList list = new MemberDao().countMemberList(conn);
    	
    	close(conn);
    	
    	return list;
    }
    
    /**
     * 최근 6개월 동안의 후원금 조회 그래프
     * @return
     */
    public ArrayList<BillGraph> countDonationBill() {
    	
    	Connection conn = getConnection();
    	
    	ArrayList<BillGraph> list2 = new MemberDao().countDonationBill(conn);
    	
    	close(conn);
    	
    	return list2;
    }
    
    /**
     * 1:1 문의 답변 현황 수치
     * @return
     */
    public double countInquiry() {
    	
    	Connection conn = getConnection();
    	
    	double result = new MemberDao().countInquiry(conn);
    	
    	close(conn);
    	
    	return result;
    }
    
    /**
     * 총 후원금액 현황
     * @return
     */
    public int totalDonation() {
    	
    	Connection conn = getConnection();
    	
    	int result2 = new MemberDao().totalDonation(conn);
    	
    	close(conn);
    	
    	return result2;
    }
    
    /**
     * 현재 진행중인 챌린지 댓글 수치
     * @return
     */
    public int totalChallengeCmt() {
    	
    	Connection conn = getConnection();
    	
    	int result3 = new MemberDao().totalChallengeCmt(conn);
    	
    	close(conn);
    	
    	return result3;
    }
    
}
