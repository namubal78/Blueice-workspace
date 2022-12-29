package com.blueice.member.model.vo;

import java.sql.Date;

public class Member {

    // 필드부
    private int memNo;
    private String memType;
    private String memId;
    private String memPwd;
    private String memName;
    private String address1;
    private String address2;
    private String zip;
    private String email;
    private String phone;
    private Date enrollDate;
    private String personalNo;
    private String companyNo;
    private String companyName;
    private String groupName;
    private String memStatus;
    
    // 기본 생성자
    public Member() {
        
    }

    // 개인회원용 생성자
    public Member(int memNo, String memType, String memId, String memPwd, String memName, String address1,
            String address2, String zip, String email, String phone, Date enrollDate, String personalNo,
            String memStatus) {
        super();
        this.memNo = memNo;
        this.memType = memType;
        this.memId = memId;
        this.memPwd = memPwd;
        this.memName = memName;
        this.address1 = address1;
        this.address2 = address2;
        this.zip = zip;
        this.email = email;
        this.phone = phone;
        this.enrollDate = enrollDate;
        this.personalNo = personalNo;
        this.memStatus = memStatus;
    }

    // 기업회원용 생성자
    public Member(int memNo, String memType, String memId, String memPwd, String memName, String address1,
            String address2, String zip, String email, String phone, Date enrollDate, String companyNo,
            String companyName, String memStatus) {
        super();
        this.memNo = memNo;
        this.memType = memType;
        this.memId = memId;
        this.memPwd = memPwd;
        this.memName = memName;
        this.address1 = address1;
        this.address2 = address2;
        this.zip = zip;
        this.email = email;
        this.phone = phone;
        this.enrollDate = enrollDate;
        this.companyNo = companyNo;
        this.companyName = companyName;
        this.memStatus = memStatus;
    }

    // 단체회원용 생성자(개인회원용 생성자와 오버로딩때문에 매개변수 위치 groupName <-> enrollDate 변경)
    public Member(int memNo, String memType, String memId, String memPwd, String memName, String address1,
            String address2, String zip, String email, String phone, String groupName, Date enrollDate, // 주의!! 생성자 오버로딩때문에 groupName enrollDate 위치가 바꼈으니 주의하세요!!
            String memStatus) {
        super();
        this.memNo = memNo;
        this.memType = memType;
        this.memId = memId;
        this.memPwd = memPwd;
        this.memName = memName;
        this.address1 = address1;
        this.address2 = address2;
        this.zip = zip;
        this.email = email;
        this.phone = phone;
        this.enrollDate = enrollDate;
        this.groupName = groupName;
        this.memStatus = memStatus;
    }
    
    // 개인회원 가입용 생성자
    public Member(String memId, String memPwd, String memName, String address1, String address2, String zip,
            String email, String phone, String personalNo) {
        super();
        this.memId = memId;
        this.memPwd = memPwd;
        this.memName = memName;
        this.address1 = address1;
        this.address2 = address2;
        this.zip = zip;
        this.email = email;
        this.phone = phone;
        this.personalNo = personalNo;
    }
    
    // 기업회원 가입용 생성자
    public Member(String memId, String memPwd, String memName, String address1, String address2, String zip,
            String email, String phone, String companyNo, String companyName) {
        super();
        this.memId = memId;
        this.memPwd = memPwd;
        this.memName = memName;
        this.address1 = address1;
        this.address2 = address2;
        this.zip = zip;
        this.email = email;
        this.phone = phone;
        this.companyNo = companyNo;
        this.companyName = companyName;
    }
    
    // 단체회원 가입용 생성자(생성자 오버로딩에 걸려서 groupName만 Setter로 넣을거임)
    public Member(String memId, String memPwd, String memName, String address1, String address2, String zip,
            String email, String phone) {
        super();
        this.memId = memId;
        this.memPwd = memPwd;
        this.memName = memName;
        this.address1 = address1;
        this.address2 = address2;
        this.zip = zip;
        this.email = email;
        this.phone = phone;
    }
    
    // 아이디찾기용 생성자
    public Member(String memName, String memId) {
		super();
		this.memName = memName;
		this.memId = memId;
	}
    
    // 비밀번호찾기용 생성자
    public Member(int memNo, String memId, String memPwd) {
		super();
		this.memNo = memNo;
		this.memId = memId;
		this.memPwd = memPwd;
	}
    
    // 회원정보 조회 및 수정용 생성자
    public Member(String memId, String memName, String address1, String address2, String zip, String email, String phone) {
		super();
		this.memId = memId;
		this.memName = memName;
		this.address1 = address1;
		this.address2 = address2;
		this.zip = zip;
		this.email = email;
		this.phone = phone;
	}
    
    // 관리자 회원관리용 생성자
	public Member(int memNo, String memId, String memName, String phone, Date enrollDate) {
		super();
		this.memNo = memNo;
		this.memId = memId;
		this.memName = memName;
		this.phone = phone;
		this.enrollDate = enrollDate;
	}

	// 메소드부
    public int getMemNo() {
        return memNo;
    }

	public void setMemNo(int memNo) {
        this.memNo = memNo;
    }

    public String getMemType() {
        return memType;
    }

    public void setMemType(String memType) {
        this.memType = memType;
    }

    public String getMemId() {
        return memId;
    }

    public void setMemId(String memId) {
        this.memId = memId;
    }

    public String getMemPwd() {
        return memPwd;
    }

    public void setMemPwd(String memPwd) {
        this.memPwd = memPwd;
    }

    public String getMemName() {
        return memName;
    }

    public void setMemName(String memName) {
        this.memName = memName;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getEnrollDate() {
        return enrollDate;
    }

    public void setEnrollDate(Date enrollDate) {
        this.enrollDate = enrollDate;
    }

    public String getPersonalNo() {
        return personalNo;
    }

    public void setPersonalNo(String personalNo) {
        this.personalNo = personalNo;
    }

    public String getCompanyNo() {
        return companyNo;
    }

    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getMemStatus() {
        return memStatus;
    }

    public void setMemStatus(String memStatus) {
        this.memStatus = memStatus;
    }

    @Override
    public String toString() {
        return "Member [memNo=" + memNo + ", memType=" + memType + ", memId=" + memId + ", memPwd=" + memPwd
                + ", memName=" + memName + ", address1=" + address1 + ", address2=" + address2 + ", zip=" + zip
                + ", email=" + email + ", phone=" + phone + ", enrollDate=" + enrollDate + ", personalNo=" + personalNo
                + ", companyNo=" + companyNo + ", companyName=" + companyName + ", groupName=" + groupName
                + ", memStatus=" + memStatus + "]";
    }
    
}
