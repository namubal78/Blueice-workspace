package com.blueice.donation.model.dao;

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
import com.blueice.donation.model.vo.Donation;
import com.blueice.donation.model.vo.DonationRegular;

public class DonationDao {
	
	private Properties prop = new Properties();
	
	public DonationDao() {
		
		String fileName = DonationDao.class.getResource("/sql/donation/donation-mapper.xml").getPath();

		try {
			prop.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 일시 결제 가정보 INSERT용 Dao
	 * @param conn
	 * @param d
	 * @return result
	 */
	public int insertDonation(Connection conn, Donation d) {
		
		// INSERT문 => int (처리된 행의 개수)
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertDonation");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, d.getDoBill());
			pstmt.setInt(2, Integer.parseInt(d.getDonator()));
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
		
	}
	
	/**
	 * 정기결제 가정보 INSERT용 Dao
	 * @param conn
	 * @param dr
	 * @return result
	 */
	public int insertRegular(Connection conn, DonationRegular dr) {
		
		// INSERT문 => int (처리된 행의 개수)
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertRegular");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, dr.getDoRegBill());
			pstmt.setInt(2, Integer.parseInt(dr.getRegDonator()));
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
		
	}
	
	// ------------------------------------------------------------------
	
	/**
	 * 일시결제 테이블로부터 가정보 PK SELECT용 Dao
	 * @param conn
	 * @param d
	 * @return d
	 */
	public Donation SelectBeforeConfirm(Connection conn, Donation d) {
		
		// SELECT문 => ResultSet 객체 (한 행 조회)
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("SelectBeforeConfirm");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, Integer.parseInt(d.getDonator()));
			pstmt.setInt(2, d.getDoBill());
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				d.setDoId(rset.getString("DO_ID"));
				d.setDoBill(rset.getInt("DO_BILL"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return d;
		
	}
	
	
	/**
	 * 정기결제 테이블로부터 가정보 PK SELECT용 Dao
	 * @param conn
	 * @param doRegBill
	 * @param doRegDonator
	 * @return dr
	 */
	public DonationRegular SelectRegBeforeConfirm(Connection conn, int doRegBill, int doRegDonator) {
		
		// SELECT문 => ResultSet 객체 (한 행 조회)
		
		DonationRegular dr = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("SelectRegBeforeConfirm");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, doRegDonator);
			pstmt.setInt(2, doRegBill);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				dr = new DonationRegular();
				dr.setDoRegId(rset.getString("DO_REG_ID"));
				dr.setDoRegBill(rset.getInt("DO_REG_BILL"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return dr;
		
	}
	
	
	// -----------------------------------------------------------
	
	/**
	 * 일시결제 테이블에 실결제 정보 UPDATE용 Dao
	 * @param conn
	 * @param d
	 * @return
	 */
	public int UpdateDonation(Connection conn, Donation d) {
		
		// INSERT문 => int (처리된 행의 개수)
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("UpdateDonation");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, d.getReceiptId());
			pstmt.setString(2, d.getReceiptURL());
			pstmt.setString(3, d.getDoId());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
		
	}
	
	
	/**
	 * 정기결제 테이블에 실결제 정보 UPDATE용 Dao
	 * @param conn
	 * @param dr
	 * @return
	 */
	public int UpdateRegDonation(Connection conn, DonationRegular dr) {
		
		// INSERT문 => int (처리된 행의 개수)
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("UpdateRegDonation");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dr.getReceiptRegId());
			pstmt.setString(2, dr.getBillingkey());
			pstmt.setString(3, dr.getReserveId());
			pstmt.setString(4, dr.getDoRegId());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
		
	}
	
	/**
	 * 정기결제 예약 시 예약ID와 결제영수증 URL을 UPDATE용 Dao
	 * @param conn
	 * @param dr2
	 * @return result
	 */
	public int UpdateSubscribe(Connection conn, DonationRegular dr2) {
		
		// UPDATE문 => int (처리된 행의 개수)
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("UpdateSubscribe");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dr2.getReserveId());
			pstmt.setString(2, dr2.getReceiptRegURL());
			pstmt.setString(3, dr2.getDoRegId());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	// ----------------------------------------------------
	
	/**
	 * 일시결제 한 건을 조회하는 Dao
	 * @param conn
	 * @param doId
	 * @return d
	 */
	public Donation SelectDonation(Connection conn, String doId) {
		
		// SELECT문 => ResultSet 객체 (한 행 조회)
		
		Donation d = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("SelectDonation");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, doId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				d = new Donation(rset.getString("DO_ID"),
								 rset.getString("RECEIPT_ID"),
								 rset.getString("RECEIPT_URL"),
								 rset.getInt("DO_BILL"),
								 rset.getDate("DO_DATE"),
								 rset.getString("DONATOR_NO"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return d;
		
	}
	
	/**
	 * 정기결제 한 건을 조회하는 Dao
	 * @param conn
	 * @param doRegId
	 * @return dr
	 */
	public DonationRegular SelectRegDonation(Connection conn, String doRegId) {
		
		// SELECT문 => ResultSet 객체 (한 행 조회)
		
		DonationRegular dr = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("SelectRegDonation");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, doRegId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				dr = new DonationRegular(rset.getString("DO_REG_ID"),
										 rset.getString("RECEIPT_REG_ID"),
										 rset.getString("RECEIPT_REG_URL"),
										 rset.getDate("DO_REG_DATE"),
										 rset.getInt("DO_REG_BILL"),
										 rset.getString("BILLINGKEY"),
										 rset.getString("RESERVE_ID"),
										 rset.getInt("DO_REG_COUNT"),
										 rset.getString("REG_DONATOR_NO"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return dr;
		
	}
	
	/**
	 * 정기결제 한 건을 조회할 때 총 금액을 가져오는 Dao
	 * @param conn
	 * @param doRegId
	 * @return
	 */
	public DonationRegular SelectRegDonationSumBill(Connection conn, String doRegId) {
		
		// SELECT문 => ResultSet 객체 (한 행 조회)
		
		DonationRegular dr = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("SelectRegDonationSumBill");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, doRegId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				dr = new DonationRegular(rset.getString("DO_REG_ID"),
										 rset.getString("RECEIPT_REG_ID"),
										 rset.getString("RECEIPT_REG_URL"),
										 rset.getDate("DO_REG_DATE"),
										 rset.getInt("SUMBILL"),
										 rset.getString("BILLINGKEY"),
										 rset.getString("RESERVE_ID"),
										 rset.getInt("DO_REG_COUNT"),
										 rset.getString("REG_DONATOR_NO"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return dr;
		
	}
	
	
	
	
	/**
	 * 정기결제 한 건을 빌링키를 통해 조회하는 Dao
	 * @param conn
	 * @param billingkey
	 * @return
	 */
	public DonationRegular SelectRegDonaWithBilling(Connection conn, String billingkey) {
		
		// SELECT문 => ResultSet 객체 (한 행 조회)
		
		DonationRegular dr = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("SelectRegDonaWithBilling");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, billingkey);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				dr = new DonationRegular(rset.getString("DO_REG_ID"),
										 rset.getString("RECEIPT_REG_ID"),
										 rset.getString("RECEIPT_REG_URL"),
										 rset.getDate("DO_REG_DATE"),
										 rset.getInt("DO_REG_BILL"),
										 rset.getString("BILLINGKEY"),
										 rset.getString("RESERVE_ID"),
										 rset.getInt("DO_REG_COUNT"),
										 rset.getString("REG_DONATOR_NO"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return dr;
	}
	
	// ---------------------------------------------------------------
	
	/**
	 * 일시결제 요청 실패 시 DELETE하는 Dao
	 * @param conn
	 * @param doId
	 * @return result
	 */
	public int DeleteError(Connection conn, String doId) {
		
		// DELETE문 => int (처리된 행의 개수)
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("DeleteError");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, doId);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
		
	}
	
	
	/**
	 * 정기결제 요청 실패 시 DELETE하는 Dao
	 * @param conn
	 * @param doRegId
	 * @return
	 */
	public int DeleteErrorReg(Connection conn, String doRegId) {
		
		// DELETE문 => int (처리된 행의 개수)
		
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("DeleteErrorReg");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, doRegId);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
		
	}
	
	// ---------------------------------------------------------------
	
	/**
	 * 일시결제 취소(UPDATE)용 Dao
	 * @param conn
	 * @param cancel
	 * @param reserveId
	 * @return result
	 */
	public int CancelDonation(Connection conn, String receiptId) {
		
		// INSERT문 => int (처리된 행의 개수)
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("CancelDonation");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, receiptId);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
		
	}
	
	/**
	 * 정기결제 취소(UPDATE)용 Dao
	 * @param conn
	 * @param cancel
	 * @param reserveId
	 * @return result
	 */
	public int CancelRegDonation(Connection conn, int cancel, String reserveId) {
		
		// INSERT문 => int (처리된 행의 개수)
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = "";
		
		if(cancel > 1) { // cancel이 2면 에러로 취소된 것
			sql = prop.getProperty("CancelRegDonationError");
		} else {
			sql = prop.getProperty("CancelRegDonation");
		}
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, reserveId);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
		
	}
	
	// ----------------------------------------------------------------------------
	
	/**
	 * 검색 조건이 없을 때 listCount(총 후원 개수)를 알아내는 Dao
	 * @param conn
	 * @param type
	 * @return listCount
	 */
	public int selectListCount(Connection conn, int type) {
		
		// SELECT문 => ResultSet 객체 => COUNT 함수라 한 행 조회, int 타입 반환
		
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = "";
		
		if(type == 1) {// 정기결제면
			sql = prop.getProperty("selectRegListCount");
		}
		else { // 일시결제면
			sql = prop.getProperty("selectListCount");
		}
		
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
	 * 검색어로 이름 검색 시 listCount를 알아내는 Dao
	 * @param conn
	 * @param type
	 * @return listCount
	 */
	public int selectListSearchNameCount(Connection conn, int type, String searchText) {
		
		// SELECT문 => ResultSet 객체 => COUNT 함수라 한 행 조회, int 타입 반환
		
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = "";
		
		if(type == 1) { // 정기결제면
			sql = prop.getProperty("selectRegListSearchNameCount");
		}
		else { // 일시결제면
			sql = prop.getProperty("selectListSearchNameCount");
		}
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, searchText);
			pstmt.setString(2, searchText);
			pstmt.setString(3, searchText);
			
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
	 * 검색어로 ID 검색 시 listCount를 알아내는 Dao
	 * @param conn
	 * @param type
	 * @param searchText
	 * @return listCount
	 */
	public int selectListSearchIdCount(Connection conn, int type, String searchText) {
		
		// SELECT문 => ResultSet 객체 => COUNT 함수라 한 행 조회, int 타입 반환
		
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = "";
		
		if(type == 1) { // 정기결제면
			sql = prop.getProperty("selectRegListSearchIdCount");
		}
		else { // 일시결제면
			sql = prop.getProperty("selectListSearchIdCount");
		}
		
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
	
	
	/**
	 * 회원 유형 검색 시 listCount를 알아내는 Dao
	 * @param conn
	 * @param type
	 * @param memType
	 * @return listCount
	 */
	public int selectListSearchTypeCount(Connection conn, int type, String memType) {
		
		// SELECT문 => ResultSet 객체 => COUNT 함수라 한 행 조회, int 타입 반환
		
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = "";
		
		if(type == 1) { // 정기결제면
			sql = prop.getProperty("selectRegListSearchTypeCount");
		}
		else { // 일시결제면
			sql = prop.getProperty("selectListSearchTypeCount");
		}
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			switch(memType) {
				case "1": pstmt.setString(1, memType);
				case "2": pstmt.setString(1, memType);
				case "3": pstmt.setString(1, memType);
			}
			
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
	
	// ------------------------------------------------------------------
	
	/**
	 * 검색 조건이 없을 때 일시결제 리스트 조회용 Dao
	 * @param conn
	 * @param pi
	 * @return list
	 */
	public ArrayList<Donation> selectDonaList(Connection conn, PageInfo pi) {
		
		// SELECT문 => ResultSet 객체 (한 행 조회)
		
		ArrayList<Donation> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectDonaList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Donation d = new Donation();
                d.setDoId(rset.getString("DO_ID"));
                d.setDoDate(rset.getDate("DO_DATE"));
                d.setDoBill(rset.getInt("DO_BILL"));
				d.setDonator(String.valueOf(rset.getInt("DONATOR_NO")));
				d.setReceiptId(rset.getString("RECEIPT_ID"));
				
				list.add(d);
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
	 * 이름 검색 시 일시결제 리스트 조회용 Dao
	 * @param conn
	 * @param pi
	 * @param searchText
	 * @return list
	 */
	public ArrayList<Donation> selectTemSearchNameList(Connection conn, PageInfo pi, String searchText) {
		
		// SELECT문 => ResultSet 객체 (한 행 조회)
		
		ArrayList<Donation> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectTemSearchNameList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			pstmt.setString(1, searchText);
			pstmt.setString(2, searchText);
			pstmt.setString(3, searchText);
			pstmt.setInt(4, startRow);
			pstmt.setInt(5, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Donation d = new Donation();
                d.setDoId(rset.getString("DO_ID"));
                d.setDoDate(rset.getDate("DO_DATE"));
                d.setDoBill(rset.getInt("DO_BILL"));
				d.setDonator(String.valueOf(rset.getInt("DONATOR_NO")));
				d.setReceiptId(rset.getString("RECEIPT_ID"));
				
				list.add(d);
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
	 * 아이디 검색 시 일시결제 리스트 조회용 Dao
	 * @param conn
	 * @param pi
	 * @param searchText
	 * @return list
	 */
	public ArrayList<Donation> selectTemSearchIdList(Connection conn, PageInfo pi, String searchText) {
		
		// SELECT문 => ResultSet 객체 (한 행 조회)
		
		ArrayList<Donation> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectTemSearchIdList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			pstmt.setString(1, searchText);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Donation d = new Donation();
                d.setDoId(rset.getString("DO_ID"));
                d.setDoDate(rset.getDate("DO_DATE"));
                d.setDoBill(rset.getInt("DO_BILL"));
				d.setDonator(String.valueOf(rset.getInt("DONATOR_NO")));
				d.setReceiptId(rset.getString("RECEIPT_ID"));
				
				list.add(d);
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
	 * 회원 유형 검색 시 일시결제 리스트 조회용 Dao
	 * @param conn
	 * @param pi
	 * @param memType
	 * @return list
	 */
	public ArrayList<Donation> selectTemSearchTypeList(Connection conn, PageInfo pi, String memType) {
		
		// SELECT문 => ResultSet 객체 (한 행 조회)
		
		ArrayList<Donation> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectTemSearchTypeList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			pstmt.setString(1, memType);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Donation d = new Donation();
                d.setDoId(rset.getString("DO_ID"));
                d.setDoDate(rset.getDate("DO_DATE"));
                d.setDoBill(rset.getInt("DO_BILL"));
				d.setDonator(String.valueOf(rset.getInt("DONATOR_NO")));
				d.setReceiptId(rset.getString("RECEIPT_ID"));
				
				list.add(d);
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
	 * 검색 조건이 없을 때 정기결제 리스트 조회용 Dao
	 * @param conn
	 * @param pi
	 * @return list
	 */
	public ArrayList<DonationRegular> selectRegDonaList(Connection conn, PageInfo pi) {
		
		// SELECT문 => ResultSet 객체 (한 행 조회)
		
		ArrayList<DonationRegular> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectRegDonaList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				DonationRegular dr = new DonationRegular();
                dr.setDoRegId(rset.getString("DO_REG_ID"));
                dr.setDoRegDate(rset.getDate("DO_REG_DATE"));
                dr.setDoRegCount(rset.getInt("DO_REG_COUNT"));
                dr.setDoRegBill(rset.getInt("SUMBILL"));
				dr.setRegDonator(String.valueOf(rset.getInt("REG_DONATOR_NO")));
				dr.setReserveId(rset.getString("RESERVE_ID"));
				dr.setBillingkey(rset.getString("BILLINGKEY"));
				
				list.add(dr);
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
	 * 이름 검색 시 정기결제 리스트 조회용 Dao
	 * @param conn
	 * @param pi
	 * @param type
	 * @param searchText
	 * @return list
	 */
	public ArrayList<DonationRegular> selectRegSearchNameList(Connection conn, PageInfo pi, String searchText) {
		
		// SELECT문 => ResultSet 객체 (한 행 조회)
		
		ArrayList<DonationRegular> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectRegSearchNameList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			pstmt.setString(1, searchText);
			pstmt.setString(2, searchText);
			pstmt.setString(3, searchText);
			pstmt.setInt(4, startRow);
			pstmt.setInt(5, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				DonationRegular dr = new DonationRegular();
                dr.setDoRegId(rset.getString("DO_REG_ID"));
                dr.setDoRegDate(rset.getDate("DO_REG_DATE"));
                dr.setDoRegCount(rset.getInt("DO_REG_COUNT"));
                dr.setDoRegBill(rset.getInt("SUMBILL"));
				dr.setRegDonator(String.valueOf(rset.getInt("REG_DONATOR_NO")));
				dr.setReserveId(rset.getString("RESERVE_ID"));
				dr.setBillingkey(rset.getString("BILLINGKEY"));
				
				list.add(dr);
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
	 * 아이디 검색 시 정기결제 리스트 조회용 Dao
	 * @param conn
	 * @param pi
	 * @param searchText
	 * @return list
	 */
	public ArrayList<DonationRegular> selectRegSearchIdList(Connection conn, PageInfo pi, String searchText) {
		
		// SELECT문 => ResultSet 객체 (한 행 조회)
		
		ArrayList<DonationRegular> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectRegSearchIdList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			pstmt.setString(1, searchText);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				DonationRegular dr = new DonationRegular();
                dr.setDoRegId(rset.getString("DO_REG_ID"));
                dr.setDoRegDate(rset.getDate("DO_REG_DATE"));
                dr.setDoRegCount(rset.getInt("DO_REG_COUNT"));
                dr.setDoRegBill(rset.getInt("SUMBILL"));
				dr.setRegDonator(String.valueOf(rset.getInt("REG_DONATOR_NO")));
				dr.setReserveId(rset.getString("RESERVE_ID"));
				dr.setBillingkey(rset.getString("BILLINGKEY"));
				
				list.add(dr);
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
	 * 회원 유형 검색 시 정기결제 리스트 조회용 Dao
	 * @param conn
	 * @param pi
	 * @param memType
	 * @return list
	 */
	public ArrayList<DonationRegular> selectRegSearchTypeList(Connection conn, PageInfo pi, String memType) {
		
		// SELECT문 => ResultSet 객체 (한 행 조회)
		
		ArrayList<DonationRegular> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectRegSearchTypeList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			
			pstmt.setString(1, memType);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				DonationRegular dr = new DonationRegular();
                dr.setDoRegId(rset.getString("DO_REG_ID"));
                dr.setDoRegDate(rset.getDate("DO_REG_DATE"));
                dr.setDoRegCount(rset.getInt("DO_REG_COUNT"));
                dr.setDoRegBill(rset.getInt("SUMBILL"));
				dr.setRegDonator(String.valueOf(rset.getInt("REG_DONATOR_NO")));
				dr.setReserveId(rset.getString("RESERVE_ID"));
				dr.setBillingkey(rset.getString("BILLINGKEY"));
				
				list.add(dr);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
		
	}
	
	// -----------------------------------------------------------------------------
	
	/**
	 * 사용자 결제 리스트 listCount(총 후원 개수)를 알아내는 Dao
	 * @param conn
	 * @param type
	 * @return listCount
	 */
	public int selectMyListCount(Connection conn, int type, int memNo) {
		
		// SELECT문 => ResultSet 객체 => COUNT 함수라 한 행 조회, int 타입 반환
		
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = "";
		
		if(type == 1) {// 정기결제면
			sql = prop.getProperty("selectMyRegListCount");
		}
		else { // 일시결제면
			sql = prop.getProperty("selectMyListCount");
		}
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memNo);
			
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
	 * 사용자 일시 결제 리스트 조회용 Dao
	 * @param conn
	 * @param pi
	 * @param memNo
	 * @return
	 */
	public ArrayList<Donation> selectMyDonaList(Connection conn, PageInfo pi, int memNo) {
		
		// SELECT문 => ResultSet 객체 (한 행 조회)
		
		ArrayList<Donation> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectMyDonaList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			pstmt.setInt(1, memNo);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Donation d = new Donation();
                d.setDoId(rset.getString("DO_ID"));
                d.setDoDate(rset.getDate("DO_DATE"));
                d.setDoBill(rset.getInt("DO_BILL"));
                d.setReceiptURL(rset.getString("RECEIPT_URL"));
				d.setDonator(String.valueOf(rset.getInt("DONATOR_NO")));
				d.setReceiptId(rset.getString("RECEIPT_ID"));
				
				list.add(d);
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
	 * 사용자 정기결제 리스트 조회용 Dao
	 * @param conn
	 * @param pi
	 * @param memNo
	 * @return list
	 */
	public ArrayList<DonationRegular> selectMyRegDonaList(Connection conn, PageInfo pi, int memNo) {
		
		// SELECT문 => ResultSet 객체 (한 행 조회)
		
		ArrayList<DonationRegular> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectMyRegDonaList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
			int endRow = startRow + pi.getBoardLimit() - 1;
			pstmt.setInt(1, memNo);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				DonationRegular dr = new DonationRegular();
                dr.setDoRegId(rset.getString("DO_REG_ID"));
                dr.setDoRegDate(rset.getDate("DO_REG_DATE"));
                dr.setDoRegCount(rset.getInt("DO_REG_COUNT"));
                dr.setDoRegBill(rset.getInt("SUMBILL"));
                dr.setReceiptRegURL(rset.getString("RECEIPT_REG_URL"));
				dr.setRegDonator(String.valueOf(rset.getInt("REG_DONATOR_NO")));
				dr.setReserveId(rset.getString("RESERVE_ID"));
				
				list.add(dr);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return list;
		
	}
	
	// --------------------------------------------------------------------------------------------------------------------
	
	/**
	 * 기부금 영수증 - 금년 후원 금액 조회 (정기) 
	 * @param regDonatorNo
	 * @return dYear
	 */
	public int selectRegDonationYear(Connection conn, String regDonatorNo) {
		
		int dYear = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectRegDonationYear");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, regDonatorNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				
				dYear = rset.getInt("YEARTOTAL_REG");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return dYear;
	}

	
	/**
	 * 기부금 영수증 - 금년 후원 금액 조회 (일시) 
	 * @param regDonatorNo
	 * @return dYear
	 */
	public int selectDonationYear(Connection conn, String donatorNo) {
		
		int dYear = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectDonationYear");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, donatorNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				
				dYear = rset.getInt("YEARTOTAL");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return dYear;
	}

	// --------------------------------------------------------------------------------------------------------------------
	
	/**
	 * 기부금 영수증 - 총 내역 (정기) 
	 * @param regDonatorNo
	 * @return dFull
	 */
	public ArrayList<DonationRegular> selectRegDonationFull(Connection conn, String regDonatorNo) {
		
		ArrayList<DonationRegular> listRegFull = new ArrayList<>();
		
		DonationRegular dFull = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectRegDonationFull");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, Integer.parseInt(regDonatorNo));
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				dFull = new DonationRegular(rset.getString("DO_REG_DATE"),
						rset.getInt("TOTAL_REG"));
				
				listRegFull.add(dFull);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return listRegFull;
	}

	
	/**
	 * 기부금 영수증 - 총 내역 (일시) 
	 * @param regDonatorNo
	 * @return dFull
	 */
	public Donation selectDonationFull(Connection conn, String donatorNo) {
		
		Donation dFull = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectDonationFull");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, Integer.parseInt(donatorNo));
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				
				dFull = new Donation(rset.getString("MIN(DO_DATE)"),
						rset.getInt("TOTAL"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return dFull;
	}
	
}
