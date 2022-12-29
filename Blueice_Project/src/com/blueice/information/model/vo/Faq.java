package com.blueice.information.model.vo;

public class Faq {

	// 필드부
	private int faqNo; // FAQ_NO NUMBER CONSTRAINT FAQ_PK PRIMARY KEY,
    private String faqQuestion; // FAQ_QUESTION VARCHAR2(200) NOT NULL,
    private String faqConts; // FAQ_CONTS LONG NOT NULL,
    private int adminNo; // ADMIN_NO NUMBER NOT NULL,
	
	
	// 생성자부
	
    // 기본 생성자
    public Faq() {
		super();
	}
	
    public Faq(int faqNo, String faqQuestion, String faqConts, int adminNo) {
		super();
		this.faqNo = faqNo;
		this.faqQuestion = faqQuestion;
		this.faqConts = faqConts;
		this.adminNo = adminNo;
	}
    
	// 메소드부
	public int getFaqNo() {
		return faqNo;
	}


	public void setFaqNo(int faqNo) {
		this.faqNo = faqNo;
	}


	public String getFaqQuestion() {
		return faqQuestion;
	}


	public void setFaqQuestion(String faqQuestion) {
		this.faqQuestion = faqQuestion;
	}


	public String getFaqConts() {
		return faqConts;
	}


	public void setFaqConts(String faqConts) {
		this.faqConts = faqConts;
	}


	public int getAdminNo() {
		return adminNo;
	}


	public void setAdminNo(int adminNo) {
		this.adminNo = adminNo;
	}

	@Override
	public String toString() {
		return "FAQ [faqNo=" + faqNo + ", faqQuestion=" + faqQuestion + ", faqConts=" + faqConts + ", adminNo="
				+ adminNo + "]";
	}

}
