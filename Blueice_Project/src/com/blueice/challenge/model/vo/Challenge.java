package com.blueice.challenge.model.vo;

public class Challenge {

	// 필드부
	private int chalNo;
	private String chalTitle;
	private String chalStart;
	private String chalEnd;
	private String chalConts;
	private int chalHit;
	private String chalStatus;
	private String titleImg;
	private String challengeOriginName;
	private String challengeChangeName;
	private String challengeFilePath;
	private String memName;
	private int chalComNo;
	
	// 생성자부
	public Challenge() {
		
	}

	public Challenge(int chalNo, String chalTitle, String chalStart, String chalEnd, String chalConts, int chalHit,
			String chalStatus, String titleImg, String challengeOriginName, String challengeChangeName,
			String challengeFilePath, String memName, int chalComNo) {
		super();
		this.chalNo = chalNo;
		this.chalTitle = chalTitle;
		this.chalStart = chalStart;
		this.chalEnd = chalEnd;
		this.chalConts = chalConts;
		this.chalHit = chalHit;
		this.chalStatus = chalStatus;
		this.titleImg = titleImg;
		this.challengeOriginName = challengeOriginName;
		this.challengeChangeName = challengeChangeName;
		this.challengeFilePath = challengeFilePath;
		this.memName = memName;
		this.chalComNo = chalComNo;
	}



	public Challenge(int chalNo, String chalTitle, String chalStart, String chalEnd, int chalHit, String chalStatus) {
		super();
		this.chalNo = chalNo;
		this.chalTitle = chalTitle;
		this.chalStart = chalStart;
		this.chalEnd = chalEnd;
		this.chalHit = chalHit;
		this.chalStatus = chalStatus;
	}

	// 메소드부
	public int getChalNo() {
		return chalNo;
	}

	public void setChalNo(int chalNo) {
		this.chalNo = chalNo;
	}

	public String getChalTitle() {
		return chalTitle;
	}

	public void setChalTitle(String chalTitle) {
		this.chalTitle = chalTitle;
	}

	public String getChalStart() {
		return chalStart;
	}

	public void setChalStart(String chalStart) {
		this.chalStart = chalStart;
	}

	public String getChalEnd() {
		return chalEnd;
	}

	public void setChalEnd(String chalEnd) {
		this.chalEnd = chalEnd;
	}

	public String getChalConts() {
		return chalConts;
	}

	public void setChalConts(String chalConts) {
		this.chalConts = chalConts;
	}

	public int getChalHit() {
		return chalHit;
	}

	public void setChalHit(int chalHit) {
		this.chalHit = chalHit;
	}

	public String getChalStatus() {
		return chalStatus;
	}

	public void setChalStatus(String chalStatus) {
		this.chalStatus = chalStatus;
	}

	public String getTitleImg() {
		return titleImg;
	}

	public void setTitleImg(String titleImg) {
		this.titleImg = titleImg;
	}

	public String getChallengeOriginName() {
		return challengeOriginName;
	}

	public void setChallengeOriginName(String challengeOriginName) {
		this.challengeOriginName = challengeOriginName;
	}

	public String getChallengeChangeName() {
		return challengeChangeName;
	}

	public void setChallengeChangeName(String challengeChangeName) {
		this.challengeChangeName = challengeChangeName;
	}

	public String getChallengeFilePath() {
		return challengeFilePath;
	}

	public void setChallengeFilePath(String challengeFilePath) {
		this.challengeFilePath = challengeFilePath;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public int getChalComNo() {
		return chalComNo;
	}

	public void setChalComNo(int chalComNo) {
		this.chalComNo = chalComNo;
	}

	@Override
	public String toString() {
		return "Challenge [chalNo=" + chalNo + ", chalTitle=" + chalTitle + ", chalStart=" + chalStart + ", chalEnd="
				+ chalEnd + ", chalConts=" + chalConts + ", chalHit=" + chalHit + ", chalStatus=" + chalStatus
				+ ", titleImg=" + titleImg + ", challengeOriginName=" + challengeOriginName + ", challengeChangeName="
				+ challengeChangeName + ", challengeFilePath=" + challengeFilePath + "]";
	}

		
}
