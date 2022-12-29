package com.blueice.challenge.model.vo;

import java.sql.Date;

public class Reward {
	
	// 필드부
	private int chalRewardNo; // CHAL_REWARD_NO NUMBER CONSTRAINT CHAL_REW_PK PRIMARY KEY,
	private String chalRewardTitle; // CHAL_REWARD_TITLE VARCHAR2(100) NOT NULL,
	private Date chalRewardDate; // CHAL_REWARD_DATE DATE DEFAULT SYSDATE NOT NULL,
	private int chalRewardHit; // CHAL_REWARD_HIT NUMBER DEFAULT 0 NOT NULL,
	private String chalRewardConts; // CHAL_REWARD_CONTS LONG NOT NULL,
	private String chalRewardFile; // CHAL_REWARD_FILE VARCHAR2(200),
	private String chalRewardStatus; // CHAL_REWARD_STATUS CHAR(1) DEFAULT 'Y' NOT NULL CONSTRAINT CHAL_REWARD_STATUS_CK CHECK(CHAL_REWARD_STATUS IN ('Y','N')),
	private int chalRewardWriter; 
	private String titleImg; // 이미지 상세보기로 가져오기
	
	
	// 생성자부
	public Reward() { }

	public Reward(int chalRewardNo, String chalRewardTitle, Date chalRewardDate, int chalRewardHit,
			String chalRewardConts, String chalRewardFile, String chalRewardStatus, int chalRewardWriter) {
		super();
		this.chalRewardNo = chalRewardNo;
		this.chalRewardTitle = chalRewardTitle;
		this.chalRewardDate = chalRewardDate;
		this.chalRewardHit = chalRewardHit;
		this.chalRewardConts = chalRewardConts;
		this.chalRewardFile = chalRewardFile;
		this.chalRewardStatus = chalRewardStatus;
		this.chalRewardWriter = chalRewardWriter;
	}

	// 리워드 전체 조회용 생성자
	public Reward(int chalRewardNo, String chalRewardTitle, int chalRewardHit, Date chalRewardDate) {
		super();
		this.chalRewardNo = chalRewardNo;
		this.chalRewardTitle = chalRewardTitle;
		this.chalRewardHit = chalRewardHit;
		this.chalRewardDate = chalRewardDate;
	}
	
	// 리워드 게시글 상세조회용 생성자
	public Reward(int chalRewardNo, String chalRewardTitle, Date chalRewardDate, int chalRewardHit,
			String chalRewardConts) {
		super();
		this.chalRewardNo = chalRewardNo;
		this.chalRewardTitle = chalRewardTitle;
		this.chalRewardDate = chalRewardDate;
		this.chalRewardHit = chalRewardHit;
		this.chalRewardConts = chalRewardConts;
	}
	
	// 리워드 게시글 이미지 상세보기
	public Reward(int chalRewardNo, String chalRewardTitle, Date chalRewardDate, int chalRewardHit,
			String chalRewardConts, String titleImg) {
		super();
		this.chalRewardNo = chalRewardNo;
		this.chalRewardTitle = chalRewardTitle;
		this.chalRewardDate = chalRewardDate;
		this.chalRewardHit = chalRewardHit;
		this.chalRewardConts = chalRewardConts;
		this.titleImg = titleImg;
	}


	// 메소드부
	public int getChalRewardNo() {
		return chalRewardNo;
	}

	public void setChalRewardNo(int chalRewardNo) {
		this.chalRewardNo = chalRewardNo;
	}

	public String getChalRewardTitle() {
		return chalRewardTitle;
	}

	public void setChalRewardTitle(String chalRewardTitle) {
		this.chalRewardTitle = chalRewardTitle;
	}

	public Date getChalRewardDate() {
		return chalRewardDate;
	}

	public void setChalRewardDate(Date chalRewardDate) {
		this.chalRewardDate = chalRewardDate;
	}

	public int getChalRewardHit() {
		return chalRewardHit;
	}

	public void setChalRewardHit(int chalRewardHit) {
		this.chalRewardHit = chalRewardHit;
	}

	public String getChalRewardConts() {
		return chalRewardConts;
	}

	public void setChalRewardConts(String chalRewardConts) {
		this.chalRewardConts = chalRewardConts;
	}

	public String getChalRewardFile() {
		return chalRewardFile;
	}

	public void setChalRewardFile(String chalRewardFile) {
		this.chalRewardFile = chalRewardFile;
	}

	public String getChalRewardStatus() {
		return chalRewardStatus;
	}

	public void setChalRewardStatus(String chalRewardStatus) {
		this.chalRewardStatus = chalRewardStatus;
	}

	public int getChalRewardWriter() {
		return chalRewardWriter;
	}

	public void setChalRewardWriter(int chalRewardWriter) {
		this.chalRewardWriter = chalRewardWriter;
	}
	
	public String getTitleImg() {
		return titleImg;
	}

	public void setTitleImg(String titleImg) {
		this.titleImg = titleImg;
	}

	
	@Override
	public String toString() {
		return "Reward [chalRewardNo=" + chalRewardNo + ", chalRewardTitle=" + chalRewardTitle + ", chalRewardDate="
				+ chalRewardDate + ", chalRewardHit=" + chalRewardHit + ", chalRewardConts=" + chalRewardConts
				+ ", chalRewardFile=" + chalRewardFile + ", chalRewardStatus=" + chalRewardStatus
				+ ", chalRewardWriter=" + chalRewardWriter + "]";
	}
}
