package com.blueice.donation.model.service;

import static com.blueice.common.JDBCTemplate.close;
import static com.blueice.common.JDBCTemplate.commit;
import static com.blueice.common.JDBCTemplate.getConnection;
import static com.blueice.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.ArrayList;

import com.blueice.common.model.vo.PageInfo;
import com.blueice.donation.model.dao.DonationDao;
import com.blueice.donation.model.vo.Donation;
import com.blueice.donation.model.vo.DonationRegular;

public class DonationService {
	
	
	/**
	 * 일시결제 테이블에 가결제 정보 INSERT 요청용 서비스
	 * @param d
	 * @return result
	 */
	public int insertDonation(Donation d) {
		
		Connection conn = getConnection();
		
		int result = new DonationDao().insertDonation(conn, d);
		
		if(result > 0) {
			commit(conn);
		}
		else {
			rollback(conn);
		}
		
		return result;
	}
	
	/**
	 * 정기결제 테이블에 가결제 정보 INSERT 요청용 서비스
	 * @param dr
	 * @return result
	 */
	public int insertRegular(DonationRegular dr) {
		
		Connection conn = getConnection();
		
		int result = new DonationDao().insertRegular(conn, dr);
		
		if(result > 0) {
			commit(conn);
		}
		else {
			rollback(conn);
		}
		
		return result;
	}
	
	// -------------------------------------------------------------
	
	/**
	 * 일시결제 테이블의 가정보 PK SELECT를 요청하는 서비스
	 * @param doBill
	 * @param doDonator
	 * @return d
	 */
	public Donation SelectBeforeConfirm(Donation d) {
		
		Connection conn = getConnection();
		
		d = new DonationDao().SelectBeforeConfirm(conn, d);
		
		close(conn);
		
		return d;
	}
	
	
	/**
	 * 정기결제 테이블의 가정보 PK SELECT를 요청하는 서비스
	 * @param doRegBill
	 * @param doRegDonator
	 * @return
	 */
	public DonationRegular SelectRegBeforeConfirm(int doRegBill, int doRegDonator) {
		
		Connection conn = getConnection();
		
		DonationRegular dr = new DonationDao().SelectRegBeforeConfirm(conn, doRegBill, doRegDonator);
		
		close(conn);
		
		return dr;
	}
	
	// --------------------------------------------------------------------
	
	/**
	 * 일시 결제 확정 시 가정보 UPDATE 요청용 서비스
	 * @param d
	 * @return
	 */
	public int UpdateDonation(Donation d) {
		
		Connection conn = getConnection();
		
		int result = new DonationDao().UpdateDonation(conn, d);
		
		if(result > 0) {
			commit(conn);
		}
		else {
			close(conn);
		}
		
		return result;
	}
	
	
	/**
	 * 정기 결제 확정 시 가정보 UPDATE 요청용 서비스
	 * @param dr
	 * @return
	 */
	public int UpdateRegDonation(DonationRegular dr) {
		
		Connection conn = getConnection();
		
		int result = new DonationDao().UpdateRegDonation(conn, dr);
		
		if(result > 0) {
			commit(conn);
		}
		else {
			close(conn);
		}
		
		return result;
	}
	
	/**
	 * 정기 결제 횟수와 예약ID UPDATE를 요청하는 서비스
	 * @param reserveId
	 * @return result
	 */
	public int UpdateSubscribe(DonationRegular dr2) {
		
		Connection conn = getConnection();
		
		int result = new DonationDao().UpdateSubscribe(conn, dr2);
		
		if(result > 0) {
			commit(conn);
		}
		else {
			close(conn);
		}
		
		return result;
	}
	
	// -----------------------------------------------------------
	
	/**
	 * 일시결제 한 건을 조회 요청하는 서비스
	 * @param doId
	 * @return d
	 */
	public Donation SelectDonation(String doId) {
		
		Connection conn = getConnection();
		
		Donation d = new DonationDao().SelectDonation(conn, doId);
		
		close(conn);
		
		return d;
	}
	
	/**
	 * 정기결제 한 건을 조회 요청하는 서비스
	 * @param doRegId
	 * @return dr
	 */
	public DonationRegular SelectRegDonation(String doRegId) {
		
		Connection conn = getConnection();
		
		DonationRegular dr = new DonationDao().SelectRegDonation(conn, doRegId);
		
		close(conn);
		
		return dr;
	}
	
	/**
	 * 정기결제 한 건에서 금액을 결제한 총 금액으로 바꿧 조회 요청하는 서비스
	 * @param doRegId
	 * @return dr
	 */
	public DonationRegular SelectRegDonationSumBill(String doRegId) {
		
		Connection conn = getConnection();
		
		DonationRegular dr = new DonationDao().SelectRegDonationSumBill(conn, doRegId);
		
		close(conn);
		
		return dr;
		
	}
	
	/**
	 * 정기 결제 한 행을 빌링키를 통해 조회 요청하는 서비스
	 * @param billingkey
	 * @return
	 */
	public DonationRegular SelectRegDonaWithBilling(String billingkey) {
		
		Connection conn = getConnection();
		
		DonationRegular dr = new DonationDao().SelectRegDonaWithBilling(conn, billingkey);
		
		close(conn);
		
		return dr;
	}
	
	// -----------------------------------------------------------------------
	
	/**
	 * 일시결제 도중 오류 시 행을 삭제(DELETE) 요청하는 서비스
	 * @param doId
	 * @return result
	 */
	public int DeleteError(String doId) {
		
		Connection conn = getConnection();
		
		int result = new DonationDao().DeleteError(conn, doId);
		
		if(result > 0) {
			commit(conn);
		}
		else {
			close(conn);
		}
		
		return result;
	}
	
	
	/**
	 * 정기결제 도중 오류 시 행을 삭제(DELETE) 요청하는 서비스
	 * @param doRegId
	 * @return
	 */
	public int DeleteErrorReg(String doRegId) {
		
		Connection conn = getConnection();
		
		int result = new DonationDao().DeleteErrorReg(conn, doRegId);
		
		if(result > 0) {
			commit(conn);
		}
		else {
			close(conn);
		}
		
		return result;
	}
	
	// -------------------------------------------------------
	
	/**
	 * 일시결제 취소를 요청하는 서비스
	 * @param cancel
	 * @param reserveId
	 * @return result
	 */
	public int CancelDonation(String receiptId) {
		
		Connection conn = getConnection();
		
		int result = new DonationDao().CancelDonation(conn, receiptId);
		
		if(result > 0) {
			commit(conn);
		}
		else {
			close(conn);
		}
		
		return result;
	}
	
	/**
	 * 정기결제를 취소(UPDATE) 요청하는 서비스
	 * @param cancel
	 * @return result
	 */
	public int CancelRegDonation(int cancel, String reserveId) {
		
		Connection conn = getConnection();
		
		int result = new DonationDao().CancelRegDonation(conn, cancel, reserveId);
		
		if(result > 0) {
			commit(conn);
		}
		else {
			close(conn);
		}
		
		return result;
	}
	
	// ------------------------------------------------------------------
	
	/**
	 * 전체 조회 시 결제 listCount를 요청하는 서비스
	 * @return listCount
	 */
	public int selectListCount(int type) {
		
		Connection conn = getConnection();
		
		int listCount = new DonationDao().selectListCount(conn, type);
		
		close(conn);
		
		return listCount;
	}
	
	/**
	 * 이름 검색 시 결제 listCount를 요청하는 서비스
	 * @param selection
	 * @param searchText
	 * @return listCount
	 */
	public int selectListSearchNameCount(int type, String searchText) {
		
		Connection conn = getConnection();
		
		int listCount = new DonationDao().selectListSearchNameCount(conn, type, searchText);
		
		close(conn);
		
		return listCount;
	}
	
	/**
	 * 아이디 검색 시 결제 listCount를 요청하는 서비스
	 * @param type
	 * @param searchText
	 * @return listCount
	 */
	public int selectListSearchIdCount(int type, String searchText) {
		
		Connection conn = getConnection();
		
		int listCount = new DonationDao().selectListSearchIdCount(conn, type, searchText);
		
		close(conn);
		
		return listCount;
	}
	
	/**
	 * 회원 유형 검색 시 결제 listCount를 요청하는 서비스
	 * @param type
	 * @param searchText
	 * @return listCount
	 */
	public int selectListSearchTypeCount(int type, String memType) {
		
		Connection conn = getConnection();
		
		int listCount = new DonationDao().selectListSearchTypeCount(conn, type, memType);
		
		close(conn);
		
		return listCount;
	}
	
	/**
	 * 일시결제 검색없이 전체 조회를 요청하는 서비스
	 * @param pi
	 * @return list
	 */
	public ArrayList<Donation> selectDonaList(PageInfo pi) {
		
		Connection conn = getConnection();
		
		ArrayList<Donation> list = new DonationDao().selectDonaList(conn, pi);
		
		close(conn);
		
		return list;
	}
	
	/**
	 * 일시결제 이름 검색 시 결과 조회를 요청하는 서비스
	 * @param pi
	 * @param searchText
	 * @return list
	 */
	public ArrayList<Donation> selectTemSearchNameList(PageInfo pi, String searchText) {
		
		Connection conn = getConnection();
		
		ArrayList<Donation> list = new DonationDao().selectTemSearchNameList(conn, pi, searchText);
		
		close(conn);
		
		return list;
	}
	
	/**
	 * 일시결제 아이디 검색 시 결과 조회를 요청하는 서비스
	 * @param pi
	 * @param searchText
	 * @return list
	 */
	public ArrayList<Donation> selectTemSearchIdList(PageInfo pi, String searchText) {
		
		Connection conn = getConnection();
		
		ArrayList<Donation> list = new DonationDao().selectTemSearchIdList(conn, pi, searchText);
		
		close(conn);
		
		return list;
	}
	
	/**
	 * 일시결제 회원 유형 검색 시 결과 조회를 요청하는 서비스
	 * @param pi
	 * @param memType
	 * @return list
	 */
	public ArrayList<Donation> selectTemSearchTypeList(PageInfo pi, String memType) {
		
		Connection conn = getConnection();
		
		ArrayList<Donation> list = new DonationDao().selectTemSearchTypeList(conn, pi, memType);
		
		close(conn);
		
		return list;
	}
	
	/**
	 * 정기결제 검색없이 전체 조회를 요청하는 서비스
	 * @param pi
	 * @return list
	 */
	public ArrayList<DonationRegular> selectRegDonaList(PageInfo pi) {
		
		Connection conn = getConnection();
		
		ArrayList<DonationRegular> list = new DonationDao().selectRegDonaList(conn, pi);
		
		close(conn);
		
		return list;
	}
	
	/**
	 * 정기결제 이름 검색 시 결과 조회를 요청하는 서비스
	 * @param pi
	 * @param type
	 * @param searchText
	 * @return list
	 */
	public ArrayList<DonationRegular> selectRegSearchNameList(PageInfo pi, String searchText) {
		
		Connection conn = getConnection();
		
		ArrayList<DonationRegular> list = new DonationDao().selectRegSearchNameList(conn, pi, searchText);
		
		close(conn);
		
		return list;
	}
	
	/**
	 * 정기결제 아이디 검색 시 결과 조회를 요청하는 서비스
	 * @param pi
	 * @param searchText
	 * @return
	 */
	public ArrayList<DonationRegular> selectRegSearchIdList(PageInfo pi, String searchText) {
		
		Connection conn = getConnection();
		
		ArrayList<DonationRegular> list = new DonationDao().selectRegSearchIdList(conn, pi, searchText);
		
		close(conn);
		
		return list;
	}
	
	/**
	 * 정기결제 회원 유형 검색 시 결과 조회를 요청하는 서비스
	 * @param pi
	 * @param searchText
	 * @return list
	 */
	public ArrayList<DonationRegular> selectRegSearchTypeList(PageInfo pi, String memType) {
		
		Connection conn = getConnection();
		
		ArrayList<DonationRegular> list = new DonationDao().selectRegSearchTypeList(conn, pi, memType);
		
		close(conn);
		
		return list;
	}
	
	
	// ---------------------------------------------------------------------------
	
	/**
	 * 사용자 결제리스트 조회 시 결제 listCount를 요청하는 서비스
	 * @param type
	 * @param memNo
	 * @return listCount
	 */
	public int selectMyListCount(int type, int memNo) {
		
		Connection conn = getConnection();
		
		int listCount = new DonationDao().selectMyListCount(conn, type, memNo);
		
		close(conn);
		
		return listCount;
	}
	
	/**
	 * 사용자 일시결제 리스트 조회 요청 서비스
	 * @param pi
	 * @param memNo
	 * @return list
	 */
	public ArrayList<Donation> selectMyDonaList(PageInfo pi, int memNo) {
		
		Connection conn = getConnection();
		
		ArrayList<Donation> list = new DonationDao().selectMyDonaList(conn, pi, memNo);
		
		close(conn);
		
		return list;
	}
	
	/**
	 * 사용자 정기결제 리스트 조회 요청 서비스
	 * @param pi
	 * @param memNo
	 * @return list
	 */
	public ArrayList<DonationRegular> selectMyRegDonaList(PageInfo pi, int memNo) {
		
		Connection conn = getConnection();
		
		ArrayList<DonationRegular> list = new DonationDao().selectMyRegDonaList(conn, pi, memNo);
		
		close(conn);
		
		return list;
	}
	
	
	// --------------------------------------------------------------------------------------------------------------------
	
	/**
	 * 기부금 영수증 - 금년 후원 금액 조회 (정기) 
	 * @param regDonatorNo
	 * @return dYear
	 */
	public int selectRegDonationYear(String regDonatorNo) {
		
		Connection conn = getConnection();
		
		int dYear = new DonationDao().selectRegDonationYear(conn, regDonatorNo);
		
		close(conn);
		
		return dYear;
	}
	
	
	/**
	 * 기부금 영수증 - 금년 후원 금액 조회 (일시) 
	 * @param regDonatorNo
	 * @return dYear
	 */
	public int selectDonationYear(String donatorNo) {
		
		Connection conn = getConnection();
		
		int dYear = new DonationDao().selectDonationYear(conn, donatorNo);
		
		close(conn);
		
		return dYear;
	}
	
	// --------------------------------------------------------------------------------------------------------------------
	
	/**
	 * 기부금 영수증 - 총 내역 (정기) 
	 * @param regDonatorNo
	 * @return drFull
	 */
	public ArrayList<DonationRegular> selectRegDonationFull(String regDonatorNo) {
		
		Connection conn = getConnection();
		
		ArrayList<DonationRegular> listRegFull = new DonationDao().selectRegDonationFull(conn, regDonatorNo);
		
		close(conn);
		
		return listRegFull;
	}
	
	
	/**
	 * 기부금 영수증 - 총 내역 (일시) 
	 * @param regDonatorNo
	 * @return dFull
	 */
	public Donation selectDonationFull(String donatorNo) {
		
		Connection conn = getConnection();
		
		Donation dFull = new DonationDao().selectDonationFull(conn, donatorNo);
		
		close(conn);
		
		return dFull;
	}
	
	
}
