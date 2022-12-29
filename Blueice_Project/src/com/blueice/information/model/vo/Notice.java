package com.blueice.information.model.vo;

import java.sql.Date;

public class Notice {

	// 필드부 
	private int noticeNo; // N_NO NUMBER CONSTRAINT N_PK PRIMARY KEY,
	private String noticeTitle; // N_TITLE	VARCHAR2(100) NOT NULL,
	private Date createDate; // N_DATE DATE DEFAULT SYSDATE NOT NULL,
	private int hit; // N_HIT NUMBER DEFAULT 0 NOT NULL (수
	private String noticeContent; // N_CONTS	LONG NOT NULL,
	private String status; // N_STATUS CHAR(1) DEFAULT 'Y' NOT NULL CONSTRAINT N_STATUS_CK CHECK(N_STATUS IN ('Y', 'N')),
	private String admin; // ADMIN_NO NUMBER NOT NULL, CONSTRAINT N_ADMIN_NO_FK FOREIGN KEY (ADMIN_NO) REFERENCES MEMBER(MEM_NO)
							// -> 조회시 작성자 아이디값 "blueice" / 작성하기시 로그인한회원번호 "1"
	
	// 생성자부 
	public Notice() {
		super();
	}

	public Notice(int noticeNo, String noticeTitle, Date createDate, int hit, String noticeContent, String status,
			String admin) {
		super();
		this.noticeNo = noticeNo;
		this.noticeTitle = noticeTitle;
		this.createDate = createDate;
		this.hit = hit;
		this.noticeContent = noticeContent;
		this.status = status;
		this.admin = admin;
	}
	
	// 공지사항 리스트 전체조회용 생성자 
	public Notice(int noticeNo, String noticeTitle, int hit, Date createDate) {
		super();
		this.noticeNo = noticeNo;
		this.noticeTitle = noticeTitle;
		this.createDate = createDate;
		this.hit = hit;
	}
	
	// 공지사항 게시글 상세 조회용 생성자 
	public Notice(int noticeNo, String noticeTitle, Date createDate, int hit, String noticeContent) {
		super();
		this.noticeNo = noticeNo;
		this.noticeTitle = noticeTitle;
		this.createDate = createDate;
		this.hit = hit;
		this.noticeContent = noticeContent;
	}

	// 메소드부 
	public int getNoticeNo() {
		return noticeNo;
	}


	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}

	public String getNoticeTitle() {
		return noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public String getNoticeContent() {
		return noticeContent;
	}

	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

	@Override
	public String toString() {
		return "Notice [noticeNo=" + noticeNo + ", noticeTitle=" + noticeTitle + ", createDate=" + createDate + ", hit="
				+ hit + ", noticeContent=" + noticeContent + ", status=" + status + ", admin=" + admin + "]";
	}
}
