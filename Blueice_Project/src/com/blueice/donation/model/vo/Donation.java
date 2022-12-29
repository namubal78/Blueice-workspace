package com.blueice.donation.model.vo;

import java.sql.Date;

public class Donation {
	
	// 필드부
	// DO_ID VARCHAR2(50) DEFAULT 'blueice_donation_' CONSTRAINT DONATION_PK PRIMARY KEY
    private String doId; 
    private String receiptId; // RECEIPT_ID VARCHAR2(50) NULL,
    private String receiptURL; // RECEIPT_URL VARCHAR2(300) NULL
	private int doBill; // DO_BILL NUMBER NOT NULL,
	private Date doDate; // DO_DATE	DATE DEFAULT SYSDATE NOT NULL,
    private String doStatus; // DO_STATUS CHAR(1) DEFAULT 'N' NOT NULL CONSTRAINT DO_STATUS_CK CHECK(DO_STATUS IN ('Y', 'N', 'C')),
	private String donator; // DONATOR_NO NUMBER NOT NULL,
    // CONSTRAINT DONATOR_NO_FK FOREIGN KEY (DONATOR_NO) REFERENCES MEMBER(MEM_NO)
	
	
	// 생성자부
	public Donation() { }


	public Donation(String doId, String receiptId, String receiptURL, int doBill, Date doDate, String doStatus,
			String donator) {
		super();
		this.doId = doId;
		this.receiptId = receiptId;
		this.receiptURL = receiptURL;
		this.doBill = doBill;
		this.doDate = doDate;
		this.doStatus = doStatus;
		this.donator = donator;
	}
	
	// 결제 시도용 생성자부
	public Donation(String doId, int doBill) {
		super();
		this.doId = doId;
		this.doBill = doBill;
	}


	// 한 행 조회용 생성자
	public Donation(String doId, String receiptId, String receiptURL, int doBill, Date doDate, String donator) {
		super();
		this.doId = doId;
		this.receiptId = receiptId;
		this.receiptURL = receiptURL;
		this.doBill = doBill;
		this.doDate = doDate;
		this.donator = donator;
	}


	public String getDoId() {
		return doId;
	}


	public void setDoId(String doId) {
		this.doId = doId;
	}


	public String getReceiptId() {
		return receiptId;
	}


	public void setReceiptId(String receiptId) {
		this.receiptId = receiptId;
	}


	public String getReceiptURL() {
		return receiptURL;
	}


	public void setReceiptURL(String receiptURL) {
		this.receiptURL = receiptURL;
	}


	public int getDoBill() {
		return doBill;
	}


	public void setDoBill(int doBill) {
		this.doBill = doBill;
	}


	public Date getDoDate() {
		return doDate;
	}


	public void setDoDate(Date doDate) {
		this.doDate = doDate;
	}


	public String getDoStatus() {
		return doStatus;
	}


	public void setDoStatus(String doStatus) {
		this.doStatus = doStatus;
	}


	public String getDonator() {
		return donator;
	}


	public void setDonator(String donator) {
		this.donator = donator;
	}


	@Override
	public String toString() {
		return "Donation [doId=" + doId + ", receiptId=" + receiptId + ", receiptURL=" + receiptURL + ", doBill="
				+ doBill + ", doDate=" + doDate + ", doStatus=" + doStatus + ", donator=" + donator + "]";
	}
	
}
