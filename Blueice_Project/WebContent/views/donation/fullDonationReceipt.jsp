<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.blueice.donation.model.vo.*, com.blueice.member.model.vo.Member" %>
<%
	ArrayList<DonationRegular> listRegFull = (ArrayList<DonationRegular>)request.getAttribute("listRegFull"); 
	Donation dFull = (Donation)request.getAttribute("dFull");
	Member m = (Member)request.getAttribute("m");
	Member loginMember = (Member)session.getAttribute("loginMember");
	
%>
<!DOCTYPE html>
<html>
    <head>
    <title>기부금 영수증_모든 내역</title>
    <!-- 
        * 부트스트랩
        웹 사이트를 쉽게 꾸밀 수 있게 도와주는 HTML, CSS, JS 프레임워크 (규칙성이 강한 라이브러리)
    -->
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">

    <!-- Popper JS -->
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
    </head>
    <style>
        .outer {
            font-style:normal;
            font-weight:normal;
            font-family: Mangul Gothic;
            color:#000000;
        }
        
        tbody>tr { height: 30px;}

        .cancle{
            height:35px;
            width:100px;
        }
    </style>
    <body>
        
        <div class="outer">
            <img style="top:0.47in;left:0.69in;width:2.54in;height:0.14in" src="/Blueice/resources/images/donationReceipt/ci_1.png" />
            
            <div style="position:absolute; top:0.94in;left:3.24in;width:2.37in;line-height:0.24in; font-size:16pt;">
                <p> &nbsp;&nbsp;&nbsp;&nbsp;기 부 금 영 수 증 </p>              
            </div>
            
            <img style="position:absolute; top:1.47in;left:7.48in;width:0.30in;height:0.12in" src="/Blueice/resources/images/donationReceipt/ci_11.png" />
            <img style="position:absolute; top:1.47in;left:7.78in;width:0.06in;height:0.12in" src="/Blueice/resources/images/donationReceipt/ci_12.png" />
            
            <div style="position:absolute; top:1.47in;left:7.48in;width:0.43in;line-height:0.12in; font-size:8pt;">
                <span>(</span>
                <span>앞쪽</span>
                <span>)</span>
                <span> </span><br/></SPAN>
            </div>

            <div style="position:absolute; top:1.67in;left:0.69in;line-height:0.15in; font-size:10pt;">
                <span> </span>
                <span>❶</span>
                <span> </span>
                <span>기부자</span>
                <span></span><br/></SPAN>
            </div>
            
            <div style="position:absolute; top:1.91in;left:0.69in;line-height:0.14in; font-size:9pt; margin-top: 5px;">
                <span>성명</span>
                <span>(</span>
                <span>법인명</span>
                <span>)</span>
                <span> </span><br/>&nbsp;&nbsp;
                <% if(m.getMemType().equals("1")) { %>
                    <%= m.getMemName() %> 
                <% } else if(m.getMemType().equals("2")) { %> 
                    <%= m.getGroupName() %> 
                <% } else { %>
                    <%= m.getCompanyName() %> 
                <% } %>
                </SPAN>
            </div>
            
            <div style="position:absolute;top:1.91in;left:3.77in;line-height:0.15in; font-size:9pt; margin-top: 4px;">
                <span>주민등록번호</span>
                <span>&nbsp;&nbsp;
                    <% if(m.getMemType().equals("1")) { %>
                        <%= m.getPersonalNo() %> 
                    <% } %> 
                </span>
                <br/>
                <span>(</span>
                <span>사업자등록번호</span>
                <span>)&nbsp;&nbsp;
                    <% if(m.getMemType().equals("3")) { %>
                        <%= m.getCompanyNo() %> 
                    <% } %>  
                </span>
                <span> </span><br/></SPAN>
            </div>
            
            <div style="position:absolute;top:2.34in;left:0.69in;line-height:0.14in; font-size:9pt; margin-top: 8px;">
                <span>주소</span>
                <span>(</span>
                <span>소재지</span>
                <span>)</span>
                <span> </span><br/>&nbsp;&nbsp;
                    <%= m.getAddress1() + " " + m.getAddress2() %> 
                </SPAN>
            </div>
            
            <div style="position:absolute;top:3.01in;left:0.69in;line-height:0.15in; font-size:10pt;">
                <span> </span>
                <span>❷</span>
                <span> </span>
                <span>기부금</span>
                <span> </span>
                <span>단체</span>
                <span> </span><br/></SPAN>
            </div>
            
            <div style="position:absolute;top:3.25in;left:0.69in;line-height:0.14in; font-size:9pt; margin-top: 5px;">
                <span>단</span>
                <span> </span>
                <span>체</span>
                <span> </span>
                <span>명</span>
                <span>&nbsp;&nbsp;블루아이스</span><br/></SPAN>
            </div>
            
            <div style="position:absolute;top:3.25in;left:4.90in;line-height:0.14in; font-size:9pt; margin-top: 5px;">
                <span>사업자등록번호</span>
                <span>(</span>
                <span>고유번호</span>
                <span>)</span>
                <span>&nbsp;&nbsp;202-07-25123</span><br/></SPAN>
            </div>
            
            <div style="position:absolute;top:3.52in;left:0.69in;line-height:0.14in; font-size:9pt;">
                <span>(</span>
                <span>지점명</span>
                <span>)</span>
                <span> </span><br/></SPAN>
            </div>
            
            <div style="position:absolute;top:3.52in;left:4.90in;line-height:0.14in; font-size:9pt;">
                <span>(</span>
                <span>지점</span>
                <span> </span>
                <span>사업자등록번호</span>
                <span> </span>
                <span>등</span>
                <span>)</span>
                <span> </span><br/></SPAN>
            </div>
            
            <div style="position:absolute;top:3.76in;left:0.69in;line-height:0.14in; font-size:9pt; margin-top: 4px;">
                <span>소</span>
                <span> </span>
                <span>재</span>
                <span> </span>
                <span>지</span>
                <span>&nbsp;&nbsp;서울특별시 영등포구 선유동2로 57 이레빌딩 (구관) 19F</span><br/></SPAN>
            </div>
            
            <div style="position:absolute;top:3.76in;left:4.90in;line-height:0.14in; font-size:9pt; margin-top: 10px;">
                <span>기부금공제대상</span>
                <span></span><br/></SPAN>
            </div>
            
            <div style="position:absolute;top:3.94in;left:4.90in;line-height:0.14in; font-size:9pt; margin-top: 10px;">
                <span></span>
                <span> </span>
                <span>근거법령</span>
                <span></span><br/></SPAN>
            </div>
            
            <div style="position:absolute;top:4.03in;left:0.69in;line-height:0.14in; font-size:9pt;">
                <span>(</span>
                <span>지점</span>
                <span> </span>
                <span>소재지</span>
                <span>)</span>
                <span> </span><br/></SPAN>
            </div>
            
            <div style="position:absolute;top:4.31in;left:0.69in;width:5.99in;line-height:0.14in; font-size:9pt;">
                * 기부금 단체의 지점(분사무소)이 기부 받은 경우, 지점명 등을 추가로 기재할 수 있습니다.
            </div>
            <div style="position:absolute;top:4.54in;left:0.69in;line-height:0.15in; font-size:10pt;">
                <span> ❸ 기부금 모집처(언론기관 등) </span>
                <span></span><br/></SPAN>
                
            </div>
            <div style="position:absolute;top:4.79in;left:0.69in;line-height:0.14in; font-size:9pt">
                <p>단 체 명</p>
            </div>
            <div style="position:absolute;top:4.79in;left:3.77in;line-height:0.14in; font-size:9pt">
                <span>사업자등록번호</span>
                <span> </span><br/></SPAN>
            </div>
            <div style="position:absolute;top:5.17in;left:0.69in;line-height:0.14in; font-size:9pt">
                <p> 소 재 지</p>
            </div>
            <div style="position:absolute;top:5.78in;left:0.69in;line-height:0.15in; font-size:10pt;">
                <span> </span>
                <span>❹</span>
                <span> </span>
                <span>기부내용</span>
                <span> </span><br/></SPAN>
            </div>


            <!-- 표 시작 -->
            
               <div style="position:absolute;top:6.10in;left:0.68in;line-height:0.14in; font-size:9pt;">
                <table border="1" style="text-align:center;">
                    <thead>
                        <tr height="75">
                            <th width="80">유형</th>
                            <th width="80">구분</th>
                            <th width="175">연월일</th>
                            <th width="200">내용</th>
                            <th width="150">후원금액</th>
                        </tr>
                    </thead>
                    
                    <tbody>
                    
                    <% if(listRegFull == null) { %> <%-- 일시 후원 조회라면 --%>
                        <tr>
                            <td>
                            <%-- 후원자 유형 --%>
                                <% if(m.getMemType().equals("1")) { %>
                                    <%= "개인" %> 
                                <% } else if(m.getMemType().equals("2")) { %> 
                                    <%= "단체" %> 
                                <% } else { %>
                                    <%= "기업" %> 
                                <% } %>
                            </td>
                            <td>금전</td>
                            <td><%= dFull.getDoId() %></td>
                            <td></td>   <%-- 내용: 공란 --%>
                            
                            <%-- 후원 금액 --%>
                            <td><%= dFull.getDoBill() %></td>
                        </tr>
                            
                        <% } else { %>  <%-- 정기 후원 조회라면 --%>   
                            <% for(DonationRegular drfull  : listRegFull) { %>
                            <tr>
                                <td>
                                <%-- 후원자 유형 --%>
                                    <% if(m.getMemType().equals("1")) { %>
                                        <%= "개인" %> 
                                    <% } else if(m.getMemType().equals("2")) { %> 
                                        <%= "단체" %> 
                                    <% } else { %>
                                        <%= "기업" %> 
                                    <% } %>
                                </td>
                                
                                <td>금전</td>
                                    <%-- 후원 날짜: 날짜를 문자로 받아서 아이디로 인식하고 아이디값인 doId 에 담겨서 날짜이지만 id로 호출함 --%> 
                                <td><%= drfull.getDoRegId() %></td>
                                <td></td> <%-- 내용: 공란 --%>
                                
                                <%-- 후원 금액 --%>
                                <td><%= drfull.getDoRegBill() %></td>
                            </tr>
                            <% } %>
                        <% } %>    
                        <tr>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                        
                    </tbody>
                </table>
            </div>
            <!-- 표 끝 -->
       

            <br><br><br><br><br>
            <div style="position:absolute;top:8.82in;left:0.76in;width:7.06in;line-height:0.15in; font-size:10pt;">
                <p> 「소득세법」 제 34조, 「조세특례제한법」 제 76조ㆍ제 88조의 4 및 「법인세법」 제 24조에 따른 </p>
            </div>
    
            <br>
    
            <div style="position:absolute;top:9.03in;left:0.76in;width:4.37in;line-height:0.15in; font-size:10pt;">
                <p> 기부금을 위와 같이 기부하였음 증명하여 주시기 바랍니다. </p>
            </div>
            
            <div style="position:absolute;top:9.83in;left:3.60in; line-height:0.15in; font-size:10pt;">
                <span>
                    &nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;신청인
                </span>
                <span>&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <% if(m.getMemType().equals("1")) { %>
                        <%= m.getMemName() %> 
                    <% } else if(m.getMemType().equals("2")) { %> 
                        <%= m.getGroupName() %> 
                    <% } else { %>
                        <%= m.getCompanyName() %> 
                    <% } %>
                </span><br/></SPAN>
            </div>
            
            <div style="position:absolute;top:10.29in;left:0.69in;width:4.01in;line-height:0.15in; font-size:10pt;">
               <p>위와 같이 기부금을 기부받았음을 증명합니다.</p>
            </div>
            
            <div style="position:absolute;top:11.07in;left:3.07in; line-height:0.15in; font-size:10pt;">
                <span>
                    &nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;기부금 수령인
                </span>
                <span>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;블루아이스
                </span><br/></SPAN>
            </div>
            
            <div style="position:absolute;top:11.40in;left:5.04in;width:2.85in;line-height:0.12in; font-size:8pt;">
                <span>210mm×297mm[</span>
                <span>백상지</span>
                <span> 80g/㎡ </span>
                <span>또는</span>
                <span> </span>
                <span>중질지</span>
                <span> 80g/㎡]</span>
                <span> </span><br/></SPAN>
                <br><br><br><br><br><br><br><br><br><br><br><br>
            </div>

            <div id="btns" style="position:absolute;top:11.80in;left:3.2in;line-height:0.12in; font-size:8pt;">
                <% if(loginMember.getMemNo() == 1) { %>
	                <% if(dFull != null) { %>
	                    <a href="temAdminlist.do?currentPage=1" class="btn btn-warning" style="border: 2px solid white;">목록가기</a>
	                <% } else { %>
	                    <a href="regAdminlist.do?currentPage=1" class="btn btn-warning" style="border: 2px solid white;">목록가기</a>
	                <% } %> 
                <% } else { %>
	                <% if(dFull != null) { %>
	                    <a href="temList.do?currentPage=1" class="btn btn-warning" style="border: 2px solid white;">목록가기</a>
	                <% } else { %>
	                    <a href="regList.do?currentPage=1" class="btn btn-warning" style="border: 2px solid white;">목록가기</a>
	                <% } %> 
                <% } %>
                
                <button onclick="window.print();" class="btn btn-secondary" style="border: 2px solid white;">출력하기</button>
            </div>


                
            
            <img style="position:absolute;top:1.88in;left:3.75in;width:0.01in;height:0.44in" src="/Blueice/resources/images/donationReceipt/ci_14.png" />
            <img style="position:absolute;top:4.77in;left:3.75in;width:0.01in;height:0.39in" src="/Blueice/resources/images/donationReceipt/ci_15.png" />
            <img style="position:absolute;top:3.23in;left:0.66in;width:7.20in;height:1.07in" src="/Blueice/resources/images/donationReceipt/ci_16.png" />
            <img style="position:absolute;top:1.59in;left:0.66in;width:7.20in;height:0.01in" src="/Blueice/resources/images/donationReceipt/ci_17.png" />
            <img style="position:absolute;top:1.88in;left:0.66in;width:7.20in;height:0.01in" src="/Blueice/resources/images/donationReceipt/ci_18.png" />
            <img style="position:absolute;top:2.32in;left:0.66in;width:7.20in;height:0.01in" src="/Blueice/resources/images/donationReceipt/ci_19.png" />
            <img style="position:absolute;top:2.79in;left:0.66in;width:7.20in;height:0.01in" src="/Blueice/resources/images/donationReceipt/ci_20.png" />
            <img style="position:absolute;top:2.93in;left:0.66in;width:7.19in;height:0.00in" src="/Blueice/resources/images/donationReceipt/ci_21.png" />
            <img style="position:absolute;top:2.93in;left:0.66in;width:7.20in;height:0.01in" src="/Blueice/resources/images/donationReceipt/ci_22.png" />
            <img style="position:absolute;top:4.47in;left:0.66in;width:7.19in;height:0.01in" src="/Blueice/resources/images/donationReceipt/ci_23.png" />
            <img style="position:absolute;top:4.77in;left:0.66in;width:7.20in;height:0.01in" src="/Blueice/resources/images/donationReceipt/ci_24.png" />
            <img style="position:absolute;top:5.15in;left:0.66in;width:7.20in;height:0.01in" src="/Blueice/resources/images/donationReceipt/ci_25.png" />
            <img style="position:absolute;top:5.53in;left:0.66in;width:7.20in;height:0.01in" src="/Blueice/resources/images/donationReceipt/ci_26.png" />
            <img style="position:absolute;top:5.68in;left:0.66in;width:7.19in;height:0.01in" src="/Blueice/resources/images/donationReceipt/ci_27.png" />
            <img style="position:absolute;top:8.69in;left:0.66in;width:7.19in;height:0.00in" src="/Blueice/resources/images/donationReceipt/ci_28.png" />
            <img style="position:absolute;top:10.15in;left:0.65in;width:7.21in;height:0.01in" src="/Blueice/resources/images/donationReceipt/ci_29.png" />
            <img style="position:absolute;top:11.35in;left:0.64in;width:7.23in;height:0.03in" src="/Blueice/resources/images/donationReceipt/ci_30.png" />
            <img style="position:absolute;top:1.59in;left:0.66in;width:7.20in;height:0.01in" src="/Blueice/resources/images/donationReceipt/ci_31.png" />
        </div>
        
        <br><br><br><br><br><br>

    </body>
</html>