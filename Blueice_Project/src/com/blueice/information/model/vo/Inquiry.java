package com.blueice.information.model.vo;

import java.sql.Date;

/**
 * 
 * @author user1
 * 1:1 문의하기 클래스
 */
public class Inquiry {

    // 필드부
    private int inquiryNo; // 글번호 => 시퀀스 채번
    private String inquiryTitle; // 1:1 문의글 제목 
    private String inquiryConts; // 1:1 문의글 내용
    private Date inquiryDate; // 1:1 문의글 작성날짜
    private String answerConts; // 1:1 문의글 답변내용
    private Date answerDate; // 1:1 문의글 답변날짜
    private String inquiryStatus; // 1:1 문의글 삭제여부
    private String memNo; // 1:1 문의글 작성자 회원번호(MEMBER 테이블과 JOIN 후 memId 가져와야 하므로 String 타입으로 설정)
    
    // 생성자부
    
    public Inquiry() {
        
    }

    // 상세조회용 생성자
    public Inquiry(int inquiryNo, String inquiryTitle, String inquiryConts, Date inquiryDate, String answerConts,
            Date answerDate, String inquiryStatus, String memNo) {
        super();
        this.inquiryNo = inquiryNo;
        this.inquiryTitle = inquiryTitle;
        this.inquiryConts = inquiryConts;
        this.inquiryDate = inquiryDate;
        this.answerConts = answerConts;
        this.answerDate = answerDate;
        this.inquiryStatus = inquiryStatus;
        this.memNo = memNo;
    }
    
    // 리스트 조회용 생성자
    public Inquiry(int inquiryNo, String inquiryTitle, Date inquiryDate, String answerConts, String memNo) {
        super();
        this.inquiryNo = inquiryNo;
        this.inquiryTitle = inquiryTitle;
        this.inquiryDate = inquiryDate;
        this.answerConts = answerConts;
        this.memNo = memNo;
    }
    
    // 메소드부

    public int getInquiryNo() {
        return inquiryNo;
    }

    public void setInquiryNo(int inquiryNo) {
        this.inquiryNo = inquiryNo;
    }

    public String getInquiryTitle() {
        return inquiryTitle;
    }

    public void setInquiryTitle(String inquiryTitle) {
        this.inquiryTitle = inquiryTitle;
    }

    public String getInquiryConts() {
        return inquiryConts;
    }

    public void setInquiryConts(String inquiryConts) {
        this.inquiryConts = inquiryConts;
    }

    public Date getInquiryDate() {
        return inquiryDate;
    }

    public void setInquiryDate(Date inquiryDate) {
        this.inquiryDate = inquiryDate;
    }

    public String getAnswerConts() {
        return answerConts;
    }

    public void setAnswerConts(String answerConts) {
        this.answerConts = answerConts;
    }

    public Date getAnswerDate() {
        return answerDate;
    }

    public void setAnswerDate(Date answerDate) {
        this.answerDate = answerDate;
    }

    public String getInquiryStatus() {
        return inquiryStatus;
    }

    public void setInquiryStatus(String inquiryStatus) {
        this.inquiryStatus = inquiryStatus;
    }

    public String getMemNo() {
        return memNo;
    }

    public void setMemNo(String memNo) {
        this.memNo = memNo;
    }

    @Override
    public String toString() {
        return "Inquiry [inquiryNo=" + inquiryNo + ", inquiryTitle=" + inquiryTitle + ", inquiryConts=" + inquiryConts
                + ", inquiryDate=" + inquiryDate + ", answerConts=" + answerConts + ", answerDate=" + answerDate
                + ", inquiryStatus=" + inquiryStatus + ", memNo=" + memNo + "]";
    }
 
}
