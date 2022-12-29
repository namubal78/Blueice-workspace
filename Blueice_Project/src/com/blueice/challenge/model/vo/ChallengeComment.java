package com.blueice.challenge.model.vo;

public class ChallengeComment {

	private int chalComNo;
	private String chalComConts;
	private String chalComDate;
	private String chalComStatus;
	private int chalNo;
	private String memNo;
	private String titleImg;
	private String chalComNo1;
	private String chalComNo2;
	private String chalComNo3;
	
	public ChallengeComment() {
		
	}

	public ChallengeComment(int chalComNo, String chalComConts, String chalComDate, String chalComStatus, int chalNo,
			String memNo, String titleImg, String chalComNo1, String chalComNo2, String chalComNo3) {
		super();
		this.chalComNo = chalComNo;
		this.chalComConts = chalComConts;
		this.chalComDate = chalComDate;
		this.chalComStatus = chalComStatus;
		this.chalNo = chalNo;
		this.memNo = memNo;
		this.titleImg = titleImg;
		this.chalComNo1 = chalComNo1;
		this.chalComNo2 = chalComNo2;
		this.chalComNo3 = chalComNo3;
	}



	public int getChalComNo() {
		return chalComNo;
	}

	public void setChalComNo(int chalComNo) {
		this.chalComNo = chalComNo;
	}

	public String getChalComConts() {
		return chalComConts;
	}

	public void setChalComConts(String chalComConts) {
		this.chalComConts = chalComConts;
	}

	public String getChalComDate() {
		return chalComDate;
	}

	public void setChalComDate(String chalComDate) {
		this.chalComDate = chalComDate;
	}

	public String getChalComStatus() {
		return chalComStatus;
	}

	public void setChalComStatus(String chalComStatus) {
		this.chalComStatus = chalComStatus;
	}

	public int getChalNo() {
		return chalNo;
	}

	public void setChalNo(int chalNo) {
		this.chalNo = chalNo;
	}

	public String getMemNo() {
		return memNo;
	}

	public void setMemNo(String memNo) {
		this.memNo = memNo;
	}

	public String getTitleImg() {
		return titleImg;
	}

	public void setTitleImg(String titleImg) {
		this.titleImg = titleImg;
	}

	public String getChalComNo1() {
		return chalComNo1;
	}

	public void setChalComNo1(String chalComNo1) {
		this.chalComNo1 = chalComNo1;
	}

	public String getChalComNo2() {
		return chalComNo2;
	}

	public void setChalComNo2(String chalComNo2) {
		this.chalComNo2 = chalComNo2;
	}

	public String getChalComNo3() {
		return chalComNo3;
	}

	public void setChalComNo3(String chalComNo3) {
		this.chalComNo3 = chalComNo3;
	}

	@Override
	public String toString() {
		return "ChallengeComment [chalComNo=" + chalComNo + ", chalComConts=" + chalComConts + ", chalComDate="
				+ chalComDate + ", chalComStatus=" + chalComStatus + ", chalNo=" + chalNo + ", memNo=" + memNo
				+ ", titleImg=" + titleImg + "]";
	}
	
}
