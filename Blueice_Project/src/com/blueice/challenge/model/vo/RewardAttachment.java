package com.blueice.challenge.model.vo;

import java.sql.Date;

public class RewardAttachment {
	
	// 필드부
	private int reFileNo; // RE_FILE_NO NUMBER CONSTRAINT RE_ATTACH_PK PRIMARY KEY,
	private int reRefCno; // RE_REF_CNO NUMBER NOT NULL,
	private String reOriginName; // RE_ORIGIN_NAME VARCHAR2(255) NOT NULL,
	private String reChangeName; // RE_CHANGE_NAME VARCHAR2(255) NOT NULL,
	private String reFilePath; // RE_FILE_PATH VARCHAR2(1000),
	private Date reUploadDate; // RE_UPLOAD_DATE DATE DEFAULT SYSDATE NOT NULL,
	private int reFileLevel; // RE_FILE_LEVEL NUMBER,
	private String reAtStatus; // RE_AT_STATUS VARCHAR2(1) DEFAULT 'Y' CONSTRAINT RE_A_STATUS_CK CHECK(RE_AT_STATUS IN('Y', 'N')),

	
	// 생성자부
	public RewardAttachment() { }

	public RewardAttachment(int reFileNo, int reRefCno, String reOriginName, String reChangeName, String reFilePath,
			Date reUploadDate, int reFileLevel, String reAtStatus) {
		super();
		this.reFileNo = reFileNo;
		this.reRefCno = reRefCno;
		this.reOriginName = reOriginName;
		this.reChangeName = reChangeName;
		this.reFilePath = reFilePath;
		this.reUploadDate = reUploadDate;
		this.reFileLevel = reFileLevel;
		this.reAtStatus = reAtStatus;
	}
	
	// 리워드 첨부파일 상세조회용 생성자
	public RewardAttachment(int reFileNo, String reOriginName, String reChangeName, String reFilePath) {
		super();
		this.reFileNo = reFileNo;
		this.reOriginName = reOriginName;
		this.reChangeName = reChangeName;
		this.reFilePath = reFilePath;
	}
	

	// 메소드부
	public int getReFileNo() {
		return reFileNo;
	}

	public void setReFileNo(int reFileNo) {
		this.reFileNo = reFileNo;
	}

	public int getReRefCno() {
		return reRefCno;
	}

	public void setReRefCno(int reRefCno) {
		this.reRefCno = reRefCno;
	}

	public String getReOriginName() {
		return reOriginName;
	}

	public void setReOriginName(String reOriginName) {
		this.reOriginName = reOriginName;
	}

	public String getReChangeName() {
		return reChangeName;
	}

	public void setReChangeName(String reChangeName) {
		this.reChangeName = reChangeName;
	}

	public String getReFilePath() {
		return reFilePath;
	}

	public void setReFilePath(String reFilePath) {
		this.reFilePath = reFilePath;
	}

	public Date getReUploadDate() {
		return reUploadDate;
	}

	public void setReUploadDate(Date reUploadDate) {
		this.reUploadDate = reUploadDate;
	}

	public int getReFileLevel() {
		return reFileLevel;
	}

	public void setReFileLevel(int reFileLevel) {
		this.reFileLevel = reFileLevel;
	}

	public String getReAtStatus() {
		return reAtStatus;
	}

	public void setReAtStatus(String reAtStatus) {
		this.reAtStatus = reAtStatus;
	}

	
	@Override
	public String toString() {
		return "RewardAttachment [reFileNo=" + reFileNo + ", reRefCno=" + reRefCno + ", reOriginName=" + reOriginName
				+ ", reChangeName=" + reChangeName + ", reFilePath=" + reFilePath + ", reUploadDate=" + reUploadDate
				+ ", reFileLevel=" + reFileLevel + ", reAtStatus=" + reAtStatus + "]";
	}
}
