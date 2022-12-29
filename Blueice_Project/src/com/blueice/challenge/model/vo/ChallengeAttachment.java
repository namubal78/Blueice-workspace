package com.blueice.challenge.model.vo;

import java.sql.Date;

public class ChallengeAttachment {

	private int chalFileNo;
	private int chalRefCno;
	private String chalOriginName;
	private String chalChangeName;
	private String chalFilePath;
	private Date chalUploadDate;
	private int chalFileLevel;
	private String chalAtStatus;
	
	public ChallengeAttachment() {
		
	}

	public ChallengeAttachment(int chalFileNo, int chalRefCno, String chalOriginName, String chalChangeName,
			String chalFilePath, Date chalUploadDate, int chalFileLevel, String chalAtStatus) {
		super();
		this.chalFileNo = chalFileNo;
		this.chalRefCno = chalRefCno;
		this.chalOriginName = chalOriginName;
		this.chalChangeName = chalChangeName;
		this.chalFilePath = chalFilePath;
		this.chalUploadDate = chalUploadDate;
		this.chalFileLevel = chalFileLevel;
		this.chalAtStatus = chalAtStatus;
	}

	public int getChalFileNo() {
		return chalFileNo;
	}

	public void setChalFileNo(int chalFileNo) {
		this.chalFileNo = chalFileNo;
	}

	public int getChalRefCno() {
		return chalRefCno;
	}

	public void setChalRefCno(int chalRefCno) {
		this.chalRefCno = chalRefCno;
	}

	public String getChalOriginName() {
		return chalOriginName;
	}

	public void setChalOriginName(String chalOriginName) {
		this.chalOriginName = chalOriginName;
	}

	public String getChalChangeName() {
		return chalChangeName;
	}

	public void setChalChangeName(String chalChangeName) {
		this.chalChangeName = chalChangeName;
	}

	public String getChalFilePath() {
		return chalFilePath;
	}

	public void setChalFilePath(String chalFilePath) {
		this.chalFilePath = chalFilePath;
	}

	public Date getChalUploadDate() {
		return chalUploadDate;
	}

	public void setChalUploadDate(Date chalUploadDate) {
		this.chalUploadDate = chalUploadDate;
	}

	public int getChalFileLevel() {
		return chalFileLevel;
	}

	public void setChalFileLevel(int chalFileLevel) {
		this.chalFileLevel = chalFileLevel;
	}

	public String getChalAtStatus() {
		return chalAtStatus;
	}

	public void setChalAtStatus(String chalAtStatus) {
		this.chalAtStatus = chalAtStatus;
	}

	@Override
	public String toString() {
		return "ChallengeAttachment [chalFileNo=" + chalFileNo + ", chalRefCno=" + chalRefCno + ", chalOriginName="
				+ chalOriginName + ", chalChangeName=" + chalChangeName + ", chalFilePath=" + chalFilePath
				+ ", chalUploadDate=" + chalUploadDate + ", chalFileLevel=" + chalFileLevel + ", chalAtStatus="
				+ chalAtStatus + "]";
	}
	
}
