package com.blueice.donation.model.vo;

import java.sql.Date;

public class DonationRegular {
	
	// 필드부
    // DO_REG_ID VARCHAR2(50) DEFAULT 'blueice_reg_donation_' PRIMARY KEY,
    private String doRegId;
	// RECEIPT_REG_ID VARCHAR2(50) NULL - 부트페이의 결제번호
    private String receiptRegId;
    private String receiptRegURL; // RECEIPT_REG_URL VARCHAR2(300) NULL
    private Date doRegDate; // DO_REG_DATE DATE NOT NULL
    private int doRegBill; // DO_REG_BILL NUMBER NOT NULL,
    private String doRegStatus; // DO_REG_STATUS CHAR(1) DEFAULT 'N' NOT NULL CHECK(DO_REG_STATUS IN ('Y', 'N', 'C'))
    private String billingkey;
    private String reserveId;
    private int doRegCount;
    private String regDonator;// REG_DONATOR_NO NUMBER NOT NULL,
    // CONSTRAINT REG_DONATOR_NO_FK FOREIGN KEY (REG_DONATOR_NO) REFERENCES MEMBER(MEM_NO)
	
	// 생성자부
    public DonationRegular() { }
    
	public DonationRegular(String doRegId, String receiptRegId, String receiptRegURL, Date doRegDate, int doRegBill,
			String doRegStatus, String billingkey, String reserveId, int doRegCount, String regDonator) {
		super();
		this.doRegId = doRegId;
		this.receiptRegId = receiptRegId;
		this.receiptRegURL = receiptRegURL;
		this.doRegDate = doRegDate;
		this.doRegBill = doRegBill;
		this.doRegStatus = doRegStatus;
		this.billingkey = billingkey;
		this.reserveId = reserveId;
		this.doRegCount = doRegCount;
		this.regDonator = regDonator;
	}

	// 결제 시도용 생성자부
	public DonationRegular(String doRegId, int doRegBill) {
		super();
		this.doRegId = doRegId;
		this.doRegBill = doRegBill;
	}
	
	// 한 행 조회용 메소드부
	public DonationRegular(String doRegId, String receiptRegId, String receiptRegURL, Date doRegDate, int doRegBill,
			String billingkey, String reserveId, int doRegCount, String regDonator) {
		super();
		this.doRegId = doRegId;
		this.receiptRegId = receiptRegId;
		this.receiptRegURL = receiptRegURL;
		this.doRegDate = doRegDate;
		this.doRegBill = doRegBill;
		this.billingkey = billingkey;
		this.reserveId = reserveId;
		this.doRegCount = doRegCount;
		this.regDonator = regDonator;
	}

	public String getDoRegId() {
		return doRegId;
	}

	public void setDoRegId(String doRegId) {
		this.doRegId = doRegId;
	}

	public String getReceiptRegId() {
		return receiptRegId;
	}

	public void setReceiptRegId(String receiptRegId) {
		this.receiptRegId = receiptRegId;
	}

	public String getReceiptRegURL() {
		return receiptRegURL;
	}

	public void setReceiptRegURL(String receiptRegURL) {
		this.receiptRegURL = receiptRegURL;
	}

	public Date getDoRegDate() {
		return doRegDate;
	}

	public void setDoRegDate(Date doRegDate) {
		this.doRegDate = doRegDate;
	}

	public int getDoRegBill() {
		return doRegBill;
	}

	public void setDoRegBill(int doRegBill) {
		this.doRegBill = doRegBill;
	}

	public String getDoRegStatus() {
		return doRegStatus;
	}

	public void setDoRegStatus(String doRegStatus) {
		this.doRegStatus = doRegStatus;
	}

	public String getBillingkey() {
		return billingkey;
	}

	public void setBillingkey(String billingkey) {
		this.billingkey = billingkey;
	}

	public String getReserveId() {
		return reserveId;
	}

	public void setReserveId(String reserveId) {
		this.reserveId = reserveId;
	}

	public int getDoRegCount() {
		return doRegCount;
	}

	public void setDoRegCount(int doRegCount) {
		this.doRegCount = doRegCount;
	}

	public String getRegDonator() {
		return regDonator;
	}

	public void setRegDonator(String regDonator) {
		this.regDonator = regDonator;
	}

	@Override
	public String toString() {
		return "DonationRegular [doRegId=" + doRegId + ", receiptRegId=" + receiptRegId + ", receiptRegURL="
				+ receiptRegURL + ", doRegDate=" + doRegDate + ", doRegBill=" + doRegBill + ", doRegStatus="
				+ doRegStatus + ", billingkey=" + billingkey + ", reserveId=" + reserveId + ", doRegCount=" + doRegCount
				+ ", regDonator=" + regDonator + "]";
	}
	
	
}
