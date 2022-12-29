package com.blueice.member.model.dao;

import static com.blueice.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.blueice.member.model.vo.BillGraph;
import com.blueice.member.model.vo.Member;
import com.blueice.common.model.vo.PageInfo;

public class MemberDao {
    
    private Properties prop = new Properties();
    
    public MemberDao() {
        
        String fileName = MemberDao.class.getResource("/sql/member/member-mapper.xml").getPath();
        
        try {
            prop.loadFromXML(new FileInputStream(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 회원 로그인 DAO
     * @param conn
     * @param m
     * @return
     */
    public Member loginMember(Connection conn, Member m) {
        
        // SELECT문
        
        Member loginMember = null;
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        
        String sql = prop.getProperty("loginMember");
        
        try {
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, m.getMemId());
            pstmt.setString(2, m.getMemPwd());
            
            rset = pstmt.executeQuery();
            
            if(rset.next()) {
                         
                // 만일 MEM_TYPE = 1 개인회원 이라면 개인회원 로그인 정보 가져옴
                if(rset.getString("MEM_TYPE").equals("1")) {
                    loginMember = new Member(rset.getInt("MEM_NO")
                                           , rset.getString("MEM_TYPE")
                                           , rset.getString("MEM_ID")
                                           , rset.getString("MEM_PWD")
                                           , rset.getString("MEM_NAME")
                                           , rset.getString("ADDRESS_1")
                                           , rset.getString("ADDRESS_2")
                                           , rset.getString("ZIP")
                                           , rset.getString("EMAIL")
                                           , rset.getString("PHONE")
                                           , rset.getDate("ENROLL_DATE")
                                           , rset.getString("PERSONAL_NO")
                                           , rset.getString("MEM_STATUS"));
                }
                
                // 만일 MEM_TYPE = 2 단체회원 이라면 단체회원 로그인 정보 가져옴
                else if(rset.getString("MEM_TYPE").equals("2")) {
                    loginMember = new Member(rset.getInt("MEM_NO")
                                           , rset.getString("MEM_TYPE")
                                           , rset.getString("MEM_ID")
                                           , rset.getString("MEM_PWD")
                                           , rset.getString("MEM_NAME")
                                           , rset.getString("ADDRESS_1")
                                           , rset.getString("ADDRESS_2")
                                           , rset.getString("ZIP")
                                           , rset.getString("EMAIL")
                                           , rset.getString("PHONE")
                                           , rset.getString("GROUP_NAME")
                                           , rset.getDate("ENROLL_DATE")
                                           , rset.getString("MEM_STATUS"));
                }
                
                // 만일 MEM_tYPE = 3 기업회원 이라면 기업회원 로그인 정보 가져옴
                else if(rset.getString("MEM_TYPE").equals("3")) {
                    loginMember = new Member(rset.getInt("MEM_NO")
                                           , rset.getString("MEM_TYPE")
                                           , rset.getString("MEM_ID")
                                           , rset.getString("MEM_PWD")
                                           , rset.getString("MEM_NAME")
                                           , rset.getString("ADDRESS_1")
                                           , rset.getString("ADDRESS_2")
                                           , rset.getString("ZIP")
                                           , rset.getString("EMAIL")
                                           , rset.getString("PHONE")
                                           , rset.getDate("ENROLL_DATE")
                                           , rset.getString("COMPANY_NO")
                                           , rset.getString("COMPANY_NAME")
                                           , rset.getString("MEM_STATUS"));
                }
                
            }
            
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rset);
            close(pstmt);
        }
        return loginMember;
    }
    
    /**
     * 개인회원 가입 DAO
     * @param conn
     * @param m
     * @return
     */
    public int insertMember(Connection conn, Member m) {
        
        // INSERT문
        int result = 0;
        PreparedStatement pstmt = null;
        
        String sql = prop.getProperty("insertMember");
        
        try {
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, m.getMemId());
            pstmt.setString(2, m.getMemPwd());
            pstmt.setString(3, m.getMemName());
            pstmt.setString(4, m.getAddress1());
            pstmt.setString(5, m.getAddress2());
            pstmt.setString(6, m.getZip());
            pstmt.setString(7, m.getEmail());
            pstmt.setString(8, m.getPhone());
            pstmt.setString(9, m.getPersonalNo());
            
            result = pstmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
        }
        
        return result;
        
    }
    
    /**
     * 기업회원 가입 DAO
     * @param conn
     * @param m
     * @return
     */
    public int companyInsertMember(Connection conn, Member m) {
        
        // INSERT문
        int result = 0;
        PreparedStatement pstmt = null;
        
        String sql = prop.getProperty("companyInsertMember");
        
        System.out.println(m);
        
        try {
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, m.getMemId());
            pstmt.setString(2, m.getMemPwd());
            pstmt.setString(3, m.getMemName());
            pstmt.setString(4, m.getAddress1());
            pstmt.setString(5, m.getAddress2());
            pstmt.setString(6, m.getZip());
            pstmt.setString(7, m.getEmail());
            pstmt.setString(8, m.getPhone());
            pstmt.setString(9, m.getCompanyNo());
            pstmt.setString(10, m.getCompanyName());
            
            result = pstmt.executeUpdate();
            
            
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
        }
        
        return result;
    }
    
    /**
     * 단체회원 가입 DAO
     * @param conn
     * @param m
     * @return
     */
    public int groupInsertMember(Connection conn, Member m) {
        
        // INSERT문
        int result = 0;
        PreparedStatement pstmt = null;
        
        String sql = prop.getProperty("groupInsertMember");
        
        try {
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, m.getMemId());
            pstmt.setString(2, m.getMemPwd());
            pstmt.setString(3, m.getMemName());
            pstmt.setString(4, m.getAddress1());
            pstmt.setString(5, m.getAddress2());
            pstmt.setString(6, m.getZip());
            pstmt.setString(7, m.getEmail());
            pstmt.setString(8, m.getPhone());
            pstmt.setString(9, m.getGroupName());
            
            result = pstmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally { 
            close(pstmt);
        }
        
        return result; 
    }
    
    /**
     * 아이디 중복체크 DAO
     * @param conn
     * @param checkId
     * @return
     */
    public int idCheck(Connection conn, String checkId) {
        
        // SELECT 문 => ResultSet 객체 (숫자 하나)
        
        int count = 0;
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        
        String sql = prop.getProperty("idCheck");
        
        try {
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, checkId);
            
            rset = pstmt.executeQuery();
            
            if(rset.next()) {
                count = rset.getInt("COUNT(*)");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            
            close(rset);
            close(pstmt);
        }
        
        return count; // 중복된 아이디가 있다면 1, 없다면 0
    }
    
    
    /**
     * 회원 아이디 찾기 DAO
     * @param conn
     * @param m
     * @return
     */
    public Member findIdMember(Connection conn, Member m) {
        
        Member findIdMember = null;
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        
        String sql = prop.getProperty("findIdMember");
        
        try {
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, m.getMemName());
            pstmt.setString(2, m.getEmail());
            
            rset = pstmt.executeQuery();
            
            if(rset.next()) {
                
                findIdMember = new Member();
                findIdMember.setMemName(rset.getString("MEM_NAME"));
                findIdMember.setMemId(rset.getString("MEM_ID"));
            }
                        
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rset);
            close(pstmt);
        }
           
        return findIdMember;
    }
    
    /**
     * 회원 비밀번호 찾기 DAO
     * @param conn
     * @param m
     * @return
     */
    public Member findPasswordMember(Connection conn, Member m) {
        
        Member findPasswordMember = null;
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        
        String sql = prop.getProperty("findPasswordMember");
        
        try {
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, m.getMemId());
            pstmt.setString(2, m.getMemName());
            pstmt.setString(3, m.getEmail());
            
            rset = pstmt.executeQuery();
            
            if(rset.next()) {
                
                findPasswordMember = new Member();
                findPasswordMember.setMemNo(rset.getInt("MEM_NO"));
                findPasswordMember.setMemId(rset.getString("MEM_ID"));
                findPasswordMember.setMemPwd(rset.getString("MEM_PWD"));
            }
                        
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(rset);
            close(pstmt);
        }
           
        return findPasswordMember;
    }
    
    /**
     * 회원정보 변경 DAO
     * @param conn
     * @param m
     * @return
     */
    public int updateMember(Connection conn, Member m) {
        
        // UPDATE 문
        int result = 0;
        PreparedStatement pstmt = null;
        String sql = prop.getProperty("updateMember");
        
        try {
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, m.getMemName());
            pstmt.setString(2, m.getPhone());
            pstmt.setString(3, m.getEmail());
            pstmt.setString(4, m.getAddress1());
            pstmt.setString(5, m.getAddress2());
            pstmt.setString(6, m.getZip());
            pstmt.setString(7, m.getMemId());
            
            result = pstmt.executeUpdate();
            
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            
            close(pstmt);
        }
        
        return result;
    }
    
    /**
     * 전체회원 조회 DAO
     * @param conn
     * @param memId
     * @return
     */
    public Member selectMember(Connection conn, String memId) {
        
        // SELECT 문 => ResultSet 객체
        Member m = null;
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        
        String sql = prop.getProperty("selectMember");
        
        try {
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, memId);
            
            rset = pstmt.executeQuery();
            
            if(rset.next()) {
                
                // 만일 MEM_TYPE = 1 개인회원 이라면 개인회원 로그인 정보 가져옴
                if(rset.getString("MEM_TYPE").equals("1")) {
                    m = new Member(rset.getInt("MEM_NO")
                                 , rset.getString("MEM_TYPE")
                                 , rset.getString("MEM_ID")
                                 , rset.getString("MEM_PWD")
                                 , rset.getString("MEM_NAME")
                                 , rset.getString("ADDRESS_1")
                                 , rset.getString("ADDRESS_2")
                                 , rset.getString("ZIP")
                                 , rset.getString("EMAIL")
                                 , rset.getString("PHONE")
                                 , rset.getDate("ENROLL_DATE")
                                 , rset.getString("PERSONAL_NO")
                                 , rset.getString("MEM_STATUS"));
                }
                
                // 만일 MEM_TYPE = 2 단체회원 이라면 단체회원 로그인 정보 가져옴
                else if(rset.getString("MEM_TYPE").equals("2")) {
                    m = new Member(rset.getInt("MEM_NO")
                                 , rset.getString("MEM_TYPE")
                                 , rset.getString("MEM_ID")
                                 , rset.getString("MEM_PWD")
                                 , rset.getString("MEM_NAME")
                                 , rset.getString("ADDRESS_1")
                                 , rset.getString("ADDRESS_2")
                                 , rset.getString("ZIP")
                                 , rset.getString("EMAIL")
                                 , rset.getString("PHONE")
                                 , rset.getString("GROUP_NAME")
                                 , rset.getDate("ENROLL_DATE")
                                 , rset.getString("MEM_STATUS"));
                }
                
                // 만일 MEM_tYPE = 3 기업회원 이라면 기업회원 로그인 정보 가져옴
                else if(rset.getString("MEM_TYPE").equals("3")) {
                    m = new Member(rset.getInt("MEM_NO")
                                 , rset.getString("MEM_TYPE")
                                 , rset.getString("MEM_ID")
                                 , rset.getString("MEM_PWD")
                                 , rset.getString("MEM_NAME")
                                 , rset.getString("ADDRESS_1")
                                 , rset.getString("ADDRESS_2")
                                 , rset.getString("ZIP")
                                 , rset.getString("EMAIL")
                                 , rset.getString("PHONE")
                                 , rset.getDate("ENROLL_DATE")
                                 , rset.getString("COMPANY_NO")
                                 , rset.getString("COMPANY_NAME")
                                 , rset.getString("MEM_STATUS"));
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            
            close(rset);
            close(pstmt);
        }
        
        return m;
    }
    
    /**
     * 회원 비밀번호 변경 DAO
     * @param conn
     * @param m
     * @param updatePwd
     * @return
     */
    public int updatePwdMember(Connection conn, Member m, String updatePwd) {
        
        // UPDATE 문
        
        int result = 0;
        PreparedStatement pstmt = null;
        
        String sql = prop.getProperty("updatePwdMember");
        
        try {
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, updatePwd);
            pstmt.setString(2, m.getMemId());
            pstmt.setString(3, m.getMemPwd());
            
            result = pstmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            
            close(pstmt);
        }
        return result;
    }
    
    /**
     * 회원 탈퇴 DAO
     * @param conn
     * @param m
     * @return
     */
    public int deleteMember(Connection conn, Member m) {
        
        // UPDATE 문
        
        int result = 0;
        PreparedStatement pstmt = null;
        
        String sql = prop.getProperty("deleteMember");
        
        try {
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, m.getMemId());
            pstmt.setString(2, m.getMemPwd());
            
            result = pstmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            
            close(pstmt);
        }
        
        return result;
    }
    
    // 그룹회원 전체 페이징
    public int selectListCountGroupAll(Connection conn) {
		
		// SELECT 문 => ResultSet 객체 (그룹함수를 써서 한 행 조회)
		
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectListCountGroupAll");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				listCount = rset.getInt("COUNT");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			close(rset);
			close(pstmt);
		}
		
		return listCount;
	}
    
    // 그룹회원 번호조회 페이징
    public int selectListCountGroupMemNo(Connection conn, int memNo) {
		
		// SELECT 문 => ResultSet 객체 (그룹함수를 써서 한 행 조회)
		
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectListCountGroupMemNo");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				listCount = rset.getInt("COUNT");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			close(rset);
			close(pstmt);
		}
		
		return listCount;
	}
    
    // 그룹회원 이름조회 페이징
    public int selectListCountGroupMemName(Connection conn, String memName) {
		
		// SELECT 문 => ResultSet 객체 (그룹함수를 써서 한 행 조회)
		
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectListCountGroupMemName");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memName);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				listCount = rset.getInt("COUNT");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			close(rset);
			close(pstmt);
		}
		
		return listCount;
	}
    
    // 그룹회원 가입일조회 페이징
    public int selectListCountGroupDate(Connection conn, String enrollDate) {
		
		// SELECT 문 => ResultSet 객체 (그룹함수를 써서 한 행 조회)
		
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectListCountGroupDate");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, enrollDate);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				listCount = rset.getInt("COUNT");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			close(rset);
			close(pstmt);
		}
		
		return listCount;
	}
    
    // 개인회원 전체 페이징
    public int selectListCountAll(Connection conn) {
		
		// SELECT 문 => ResultSet 객체 (그룹함수를 써서 한 행 조회)
		
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectListCountAll");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				listCount = rset.getInt("COUNT");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			close(rset);
			close(pstmt);
		}
		
		return listCount;
	}
    
    // 개인회원 번호조회 페이징
    public int selectListCountMemNo(Connection conn, int memNo) {
		
		// SELECT 문 => ResultSet 객체 (그룹함수를 써서 한 행 조회)
		
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectListCountMemNo");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, memNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				listCount = rset.getInt("COUNT");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			close(rset);
			close(pstmt);
		}
		
		return listCount;
	}
    
    // 개인회원 이름조회 페이징
    public int selectListCountMemName(Connection conn, String memName) {
		
		// SELECT 문 => ResultSet 객체 (그룹함수를 써서 한 행 조회)
		
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectListCountMemName");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memName);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				listCount = rset.getInt("COUNT");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			close(rset);
			close(pstmt);
		}
		
		return listCount;
	}
    
    // 개인회원 가입일조회 페이징
    public int selectListCountDate(Connection conn, String enrollDate) {
		
		// SELECT 문 => ResultSet 객체 (그룹함수를 써서 한 행 조회)
		
		int listCount = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectListCountDate");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, enrollDate);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				listCount = rset.getInt("COUNT");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			close(rset);
			close(pstmt);
		}
		
		return listCount;
	}
    
    /**
     * 기업/단체회원 전체조회용 DAO
     * @param conn
     * @param pi
     * @return
     */
    public ArrayList<Member> selectGroupMemberListAll(Connection conn, PageInfo pi) {
        
        // SELECT 문 => ResultSet 객체 (여러행 조회)
        
        ArrayList<Member> list = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        
        String sql = prop.getProperty("selectGroupMemberListAll");
        
        try {
            pstmt = conn.prepareStatement(sql);
            
            int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
            int endRow = startRow + pi.getBoardLimit() - 1;
            
            pstmt.setInt(1, startRow);
            pstmt.setInt(2, endRow);
            
            rset = pstmt.executeQuery();
            
            while(rset.next()) {
                
                list.add(new Member(rset.getInt("MEM_NO")
                                  , rset.getString("MEM_NAME")
                                  , rset.getString("PHONE")
                                  , rset.getString("MEM_ID")
                                  , rset.getDate("ENROLL_DATE")));
                
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            
            close(rset);
            close(pstmt);
        }
        
        return list;
    }
    
    /**
     * 단체/기업 회원 회원번호로 조회 DAO
     * @param conn
     * @param memNo
     * @param pi
     * @return
     */
    public ArrayList<Member> selectGroupMemberListMemNo(Connection conn, int memNo, PageInfo pi) {
        
        // SELECT 문 => ResultSet 객체 (여러행 조회)
        
        ArrayList<Member> list = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        
        String sql = prop.getProperty("selectGroupMemberListMemNo");
        
        try {
            pstmt = conn.prepareStatement(sql);
            
            int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
            int endRow = startRow + pi.getBoardLimit() - 1;
            
            pstmt.setInt(1, memNo);
            pstmt.setInt(2, startRow);
            pstmt.setInt(3, endRow);
            
            rset = pstmt.executeQuery();
            
            while(rset.next()) {
                
                list.add(new Member(rset.getInt("MEM_NO")
                                  , rset.getString("MEM_NAME")
                                  , rset.getString("PHONE")
                                  , rset.getString("MEM_ID")
                                  , rset.getDate("ENROLL_DATE")));
                
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            
            close(rset);
            close(pstmt);
        }
        
        return list;
    }
    
    /**
     * 기업/단체회원명으로 찾기 DAO
     * @param conn
     * @param memName
     * @param pi
     * @return
     */
    public ArrayList<Member> selectGroupMemberListMemName(Connection conn, String memName, PageInfo pi) {
        
        // SELECT 문 => ResultSet 객체 (여러행 조회)
        
        ArrayList<Member> list = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        
        String sql = prop.getProperty("selectGroupMemberListMemName");
        
        try {
            pstmt = conn.prepareStatement(sql);
            
            int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
            int endRow = startRow + pi.getBoardLimit() - 1;
            
            pstmt.setString(1, memName);
            pstmt.setInt(2, startRow);
            pstmt.setInt(3, endRow);
            
            rset = pstmt.executeQuery();
            
            while(rset.next()) {
                
                list.add(new Member(rset.getInt("MEM_NO")
                                  , rset.getString("MEM_NAME")
                                  , rset.getString("PHONE")
                                  , rset.getString("MEM_ID")
                                  , rset.getDate("ENROLL_DATE")));
                
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            
            close(rset);
            close(pstmt);
        }
        
        return list;
    }
    
    /**
     * 기업/단체회원 가입일로 찾기 DAO
     * @param conn
     * @param enrollDate
     * @param pi
     * @return
     */
    public ArrayList<Member> selectGroupMemberListDate(Connection conn, String enrollDate, PageInfo pi) {
        
        // SELECT 문 => ResultSet 객체 (여러행 조회)
        
        ArrayList<Member> list = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        
        String sql = prop.getProperty("selectGroupMemberListDate");
        
        try {
            pstmt = conn.prepareStatement(sql);
            
            int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
            int endRow = startRow + pi.getBoardLimit() - 1;
            
            pstmt.setString(1, enrollDate);
            pstmt.setInt(2, startRow);
            pstmt.setInt(3, endRow);
            
            rset = pstmt.executeQuery();
            
            while(rset.next()) {
                
                list.add(new Member(rset.getInt("MEM_NO")
                                  , rset.getString("MEM_NAME")
                                  , rset.getString("PHONE")
                                  , rset.getString("MEM_ID")
                                  , rset.getDate("ENROLL_DATE")));
                
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            
            close(rset);
            close(pstmt);
        }
        
        return list;
    }
    
    /**
     * 관리자 개인회원 전체리스트 조회 DAO
     * @param conn
     * @param pi
     * @return
     */
    public ArrayList<Member> selectMemberListAll(Connection conn, PageInfo pi) {
        
        // SELECT 문 => ResultSet 객체 (여러행 조회)
        
        ArrayList<Member> list = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        
        String sql = prop.getProperty("selectMemberListAll");
        
        try {
            pstmt = conn.prepareStatement(sql);
            
            int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
            int endRow = startRow + pi.getBoardLimit() - 1;
            
            pstmt.setInt(1, startRow);
            pstmt.setInt(2, endRow);
            
            rset = pstmt.executeQuery();
            
            while(rset.next()) {
                
                list.add(new Member(rset.getInt("MEM_NO")
                                  , rset.getString("MEM_NAME")
                                  , rset.getString("PHONE")
                                  , rset.getString("MEM_ID")
                                  , rset.getDate("ENROLL_DATE")));
                                  
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            
            close(rset);
            close(pstmt);
        }
        
        return list;
    }
    
    /**
     * 개인회원 회원번호로 찾기 DAO
     * @param conn
     * @param memNo
     * @param pi
     * @return
     */
    public ArrayList<Member> selectMemberListMemNo(Connection conn, int memNo, PageInfo pi) {
        
        // SELECT 문 => ResultSet 객체 (여러행 조회)
        
        ArrayList<Member> list = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        
        String sql = prop.getProperty("selectMemberListMemNo");
        
        try {
            pstmt = conn.prepareStatement(sql);
            
            int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
            int endRow = startRow + pi.getBoardLimit() - 1;
            
            pstmt.setInt(1, memNo);
            pstmt.setInt(2, startRow);
            pstmt.setInt(3, endRow);
            
            rset = pstmt.executeQuery();
            
            while(rset.next()) {
                
                list.add(new Member(rset.getInt("MEM_NO")
                                  , rset.getString("MEM_NAME")
                                  , rset.getString("PHONE")
                                  , rset.getString("MEM_ID")
                                  , rset.getDate("ENROLL_DATE")));
                
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            
            close(rset);
            close(pstmt);
        }
        
        return list;
    }
    
    /**
     * 개인회원 회원명으로 찾기 DAO
     * @param conn
     * @param memName
     * @param pi
     * @return
     */
    public ArrayList<Member> selectMemberListMemName(Connection conn, String memName, PageInfo pi) {
        
        // SELECT 문 => ResultSet 객체 (여러행 조회)
        
        ArrayList<Member> list = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        
        String sql = prop.getProperty("selectMemberListMemName");
        
        try {
            pstmt = conn.prepareStatement(sql);
            
            int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
            int endRow = startRow + pi.getBoardLimit() - 1;
            
            pstmt.setString(1, memName);
            pstmt.setInt(2, startRow);
            pstmt.setInt(3, endRow);
            
            rset = pstmt.executeQuery();
            
            while(rset.next()) {
                
                list.add(new Member(rset.getInt("MEM_NO")
                                  , rset.getString("MEM_NAME")
                                  , rset.getString("PHONE")
                                  , rset.getString("MEM_ID")
                                  , rset.getDate("ENROLL_DATE")));
                
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            
            close(rset);
            close(pstmt);
        }
        
        return list;
    }
    
    /**
     * 개인회원 가입일로 찾기 DAO
     * @param conn
     * @param enrollDate
     * @param pi
     * @return
     */
    public ArrayList<Member> selectMemberListDate(Connection conn, String enrollDate, PageInfo pi) {
        
        // SELECT 문 => ResultSet 객체 (여러행 조회)
        
        ArrayList<Member> list = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        
        String sql = prop.getProperty("selectMemberListDate");
        
        try {
            pstmt = conn.prepareStatement(sql);
            
            int startRow = (pi.getCurrentPage() - 1) * pi.getBoardLimit() + 1;
            int endRow = startRow + pi.getBoardLimit() - 1;
            
            pstmt.setString(1, enrollDate);
            pstmt.setInt(2, startRow);
            pstmt.setInt(3, endRow);
            
            rset = pstmt.executeQuery();
            
            while(rset.next()) {
                
                list.add(new Member(rset.getInt("MEM_NO")
                                  , rset.getString("MEM_NAME")
                                  , rset.getString("PHONE")
                                  , rset.getString("MEM_ID")
                                  , rset.getDate("ENROLL_DATE")));
                
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            
            close(rset);
            close(pstmt);
        }
        
        return list;
    }
    
    /**
     * 관리자 회원상세조회 DAO 
     * @param conn
     * @param memNo
     * @return
     */
    public Member detailMember(Connection conn, int memNo) {
        
        // SELECT 문 => ResultSet 객체 (여러행 조회)
        
        Member list = new Member();
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        
        String sql = prop.getProperty("detailMember");
        
        try {
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setInt(1, memNo);
            
            rset = pstmt.executeQuery();
            
            if(rset.next()) {
                
                list = new Member();
                list.setMemName(rset.getString("MEM_NAME"));
                list.setMemId(rset.getString("MEM_ID"));
                list.setPhone(rset.getString("PHONE"));
                list.setEmail(rset.getString("EMAIL"));
                list.setZip(rset.getString("ZIP"));
                list.setAddress1(rset.getString("ADDRESS_1"));
                list.setAddress2(rset.getString("ADDRESS_2"));
                
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            
            close(rset);
            close(pstmt);
        }
        
        return list;
    }
    
    /**
     * 관리자 회원탈퇴 DAO
     * @param conn
     * @param memNo
     * @return
     */
    public int deleteAdminMember(Connection conn, int memNo) {
        
        // UPDATE 문
        
        int result = 0;
        PreparedStatement pstmt = null;
        
        String sql = prop.getProperty("deleteAdminMember");
        
        try {
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setInt(1, memNo);
            
            result = pstmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            
            close(pstmt);
        }
        
        return result;
    }
    
    /**
     * 회원번호(PK)를 이용한 회원 조회용 Dao
     * @param conn
     * @param memNo
     * @return
     */
    public Member selectMemberNo(Connection conn, int memNo) {
        
        // SELECT 문 => ResultSet 객체
        Member m = null;
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        
        String sql = prop.getProperty("selectMemberNo");
        
        try {
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setInt(1, memNo);
            
            rset = pstmt.executeQuery();
            
            if(rset.next()) {
                
                // 만일 MEM_TYPE = 1 개인회원 이라면 개인회원 로그인 정보 가져옴
                if(rset.getString("MEM_TYPE").equals("1")) {
                    m = new Member(rset.getInt("MEM_NO")
                                 , rset.getString("MEM_TYPE")
                                 , rset.getString("MEM_ID")
                                 , rset.getString("MEM_PWD")
                                 , rset.getString("MEM_NAME")
                                 , rset.getString("ADDRESS_1")
                                 , rset.getString("ADDRESS_2")
                                 , rset.getString("ZIP")
                                 , rset.getString("EMAIL")
                                 , rset.getString("PHONE")
                                 , rset.getDate("ENROLL_DATE")
                                 , rset.getString("PERSONAL_NO")
                                 , rset.getString("MEM_STATUS"));
                }
                
                // 만일 MEM_TYPE = 2 단체회원 이라면 단체회원 로그인 정보 가져옴
                else if(rset.getString("MEM_TYPE").equals("2")) {
                    m = new Member(rset.getInt("MEM_NO")
                                 , rset.getString("MEM_TYPE")
                                 , rset.getString("MEM_ID")
                                 , rset.getString("MEM_PWD")
                                 , rset.getString("MEM_NAME")
                                 , rset.getString("ADDRESS_1")
                                 , rset.getString("ADDRESS_2")
                                 , rset.getString("ZIP")
                                 , rset.getString("EMAIL")
                                 , rset.getString("PHONE")
                                 , rset.getString("GROUP_NAME")
                                 , rset.getDate("ENROLL_DATE")
                                 , rset.getString("MEM_STATUS"));
                }
                
                // 만일 MEM_tYPE = 3 기업회원 이라면 기업회원 로그인 정보 가져옴
                else if(rset.getString("MEM_TYPE").equals("3")) {
                    m = new Member(rset.getInt("MEM_NO")
                                 , rset.getString("MEM_TYPE")
                                 , rset.getString("MEM_ID")
                                 , rset.getString("MEM_PWD")
                                 , rset.getString("MEM_NAME")
                                 , rset.getString("ADDRESS_1")
                                 , rset.getString("ADDRESS_2")
                                 , rset.getString("ZIP")
                                 , rset.getString("EMAIL")
                                 , rset.getString("PHONE")
                                 , rset.getDate("ENROLL_DATE")
                                 , rset.getString("COMPANY_NO")
                                 , rset.getString("COMPANY_NAME")
                                 , rset.getString("MEM_STATUS"));
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            
            close(rset);
            close(pstmt);
        }
        
        return m;
    }
    
    
    /**
     * 멤버 현황 도넛 그래프
     * @param conn
     * @return
     */
    public ArrayList countMemberList(Connection conn){
    	
    	ArrayList list = new ArrayList();
    	PreparedStatement pstmt = null;
    	ResultSet rset = null;
    	
    	String sql = prop.getProperty("countMemberList");
    	
    	try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				list.add(rset.getInt("MEMNUM"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			close(rset);
			close(pstmt);
		}
    	
    	return list;
    }
    
    /**
     * 최근 6개월 동안의 후원금 조회 그래프
     * @param conn
     * @return
     */
    public ArrayList<BillGraph> countDonationBill(Connection conn) {
    	
    	ArrayList<BillGraph> list2 = new ArrayList<>();
    	PreparedStatement pstmt = null;
    	ResultSet rset = null;
    	
    	String sql = prop.getProperty("countDonationBill");
    	
    	try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				BillGraph b = new BillGraph();
				
				b.setMonthDate(rset.getInt("MONTH"));
				b.setSumBill(rset.getInt("SUM(BILL)"));
				
				list2.add(b);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			close(rset);
			close(pstmt);
		}
    	
    	return list2;
    }
    /**
     * 1:1 문의 답변 현황 수치
     * @param conn
     * @return
     */
    public double countInquiry(Connection conn) {
    	
    	double result = 0;
    	PreparedStatement pstmt = null;
    	ResultSet rset = null;
    	
    	String sql = prop.getProperty("countInquiry");
    	
    	try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				
				result = rset.getDouble("COUNT");
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			close(rset);
			close(pstmt);
		}
    	
    	return result;
    }
    
    /**
     * 총 후원금액 현황
     * @param conn
     * @return
     */
    public int totalDonation(Connection conn) {
    	
    	int result2 = 0;
    	PreparedStatement pstmt = null;
    	ResultSet rset = null;
    	
    	String sql = prop.getProperty("totalDonation");
    	
    	try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				
				result2 = rset.getInt("SUM(A)");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			close(rset);
			close(pstmt);
		}
    	
    	return result2;
    }
    
    /**
     * 현재 진행중인 챌린지 댓글 수치
     * @param conn
     * @return
     */
    public int totalChallengeCmt(Connection conn) {
    	
    	int result3 = 0;
    	PreparedStatement pstmt = null;
    	ResultSet rset = null;
    	
    	String sql = prop.getProperty("totalChallengeCmt");
    	
    	try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				
				result3 = rset.getInt("CMT");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			close(rset);
			close(pstmt);
		}
    	
    	return result3;
    }
    
    
    
       
}
