package com.blueice.news.model.vo;

public class News {
	
	// 필드부
	private int newsNo;
	private String newsTitle;
	private String newsConts;
	private String newsDate;
	private int newsHit;
	private String newsStatus;
	private int adminNo;
	
	private String titleImg;
	
	public News() {
		
	}

	public News(int newsNo, String newsTitle, String newsConts, String newsDate, int newsHit, String newsStatus,
			int adminNo) {
		super();
		this.newsNo = newsNo;
		this.newsTitle = newsTitle;
		this.newsConts = newsConts;
		this.newsDate = newsDate;
		this.newsHit = newsHit;
		this.newsStatus = newsStatus;
		this.adminNo = adminNo;
	}
	
	public News(int newsNo, String newsTitle, String newsDate, int newsHit, String titleImg) {
		super();
		this.newsNo = newsNo;
		this.newsTitle = newsTitle;
		this.newsDate = newsDate;
		this.newsHit = newsHit;
		this.titleImg = titleImg;
	}
	
	public News(int newsNo, String newsTitle, String newsConts, String newsDate, int newsHit) {
		super();
		this.newsNo = newsNo;
		this.newsTitle = newsTitle;
		this.newsConts = newsConts;
		this.newsDate = newsDate;
		this.newsHit = newsHit;
	}
	
	public News(int newsNo, String newsTitle, String newsDate, int newsHit) {
		super();
		this.newsNo = newsNo;
		this.newsTitle = newsTitle;
		this.newsDate = newsDate;
		this.newsHit = newsHit;
	}

	public News(int newsNo, String newsTitle, String newsConts, String newsDate) {
		super();
		this.newsNo = newsNo;
		this.newsTitle = newsTitle;
		this.newsConts = newsConts;
		this.newsDate = newsDate;
	}

	public News(int newsNo, String newsTitle, String newsDate) {
		super();
		this.newsNo = newsNo;
		this.newsTitle = newsTitle;
		this.newsDate = newsDate;
	}

	public News(int newsNo, String newsTitle, String newsConts, String newsDate, int newsHit, String titleImg) {
		super();
		this.newsNo = newsNo;
		this.newsTitle = newsTitle;
		this.newsConts = newsConts;
		this.newsDate = newsDate;
		this.newsHit = newsHit;
		this.titleImg = titleImg;
	}

	public News(int newsNo, String newsTitle, String newsDate, int newsHit, int adminNo, String titleImg) {
		super();
		this.newsNo = newsNo;
		this.newsTitle = newsTitle;
		this.newsDate = newsDate;
		this.newsHit = newsHit;
		this.adminNo = adminNo;
		this.titleImg = titleImg;
	}

	public News(int newsNo, String newsTitle, String newsConts, String newsDate, int newsHit, int adminNo,
			String titleImg) {
		super();
		this.newsNo = newsNo;
		this.newsTitle = newsTitle;
		this.newsConts = newsConts;
		this.newsDate = newsDate;
		this.newsHit = newsHit;
		this.adminNo = adminNo;
		this.titleImg = titleImg;
	}

	public int getNewsNo() {
		return newsNo;
	}

	public void setNewsNo(int newsNo) {
		this.newsNo = newsNo;
	}

	public String getNewsTitle() {
		return newsTitle;
	}

	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}

	public String getNewsConts() {
		return newsConts;
	}

	public void setNewsConts(String newsConts) {
		this.newsConts = newsConts;
	}

	public String getNewsDate() {
		return newsDate;
	}

	public void setNewsDate(String newsDate) {
		this.newsDate = newsDate;
	}

	public int getNewsHit() {
		return newsHit;
	}

	public void setNewsHit(int newsHit) {
		this.newsHit = newsHit;
	}

	public String getNewsStatus() {
		return newsStatus;
	}

	public void setNewsStatus(String newsStatus) {
		this.newsStatus = newsStatus;
	}

	public int getAdminNo() {
		return adminNo;
	}

	public void setAdminNo(int adminNo) {
		this.adminNo = adminNo;
	}

	public String getTitleImg() {
		return titleImg;
	}

	public void setTitleImg(String titleImg) {
		this.titleImg = titleImg;
	}

	@Override
	public String toString() {
		return "News [newsNo=" + newsNo + ", newsTitle=" + newsTitle + ", newsConts=" + newsConts + ", newsDate="
				+ newsDate + ", newsHit=" + newsHit + ", newsStatus=" + newsStatus + ", adminNo=" + adminNo + "]";
	}
	
}
