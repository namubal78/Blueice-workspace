package com.blueice.challenge.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import static com.blueice.common.JDBCTemplate.*;
import com.blueice.challenge.model.vo.Reward;
import com.blueice.challenge.model.vo.RewardAttachment;
import com.sun.xml.internal.ws.api.message.Attachment;

public class RewardDao {
	
private Properties prop = new Properties();
    
    public RewardDao() {
        
        String fileName = RewardDao.class.getResource("/sql/challenge/challenge-mapper.xml").getPath();
        
        try {
            prop.loadFromXML(new FileInputStream(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 게시글 전체 조회
     * @param conn
     * @return
     */
    public ArrayList<Reward> selectRewardList(Connection conn){
    	
    	// SELECT 문
    	ArrayList<Reward> list = new ArrayList<>();
    	PreparedStatement pstmt = null;
    	ResultSet rset = null;
    	
    	String sql = prop.getProperty("selectRewardList");
    	
    	try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				list.add(new Reward(rset.getInt("CHAL_REWARD_NO"),
									rset.getString("CHAL_REWARD_TITLE"),
									rset.getInt("CHAL_REWARD_HIT"),
									rset.getDate("CHAL_REWARD_DATE")));
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
     * 게시글 내용 삽입
     * @param conn
     * @param r
     * @return
     */
    public int insertReward(Connection conn, Reward r) {
    	
    	// INSERT 문
    	int result = 0;
    	PreparedStatement pstmt = null;
    	
    	String sql =  prop.getProperty("insertReward");
    	
    	try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, r.getChalRewardTitle());
			pstmt.setString(2, r.getChalRewardConts());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			close(pstmt);
		}
    	
    	return result;
    }
    
    /**
     * 게시글 첨부파일 삽입
     * @param conn
     * @param ra
     * @return
     */
    public int insertRewardAttachment(Connection conn, RewardAttachment ra) {
    	
    	int result = 0;
    	PreparedStatement pstmt = null;
    	
    	String sql = prop.getProperty("insertRewardAttachment");
    	
    	try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, ra.getReOriginName());
			pstmt.setString(2, ra.getReChangeName());
			pstmt.setString(3, ra.getReFilePath());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			close(pstmt);
		}
    	
    	return result;
    }
    
    /**
     * 조회수 증가
     * @param conn
     * @param rewardNo
     * @return
     */
    public int increaseCount(Connection conn, int rewardNo) {
    	
    	// UPDATE 문
    	int result = 0;
    	PreparedStatement pstmt = null;
    	
    	String sql = prop.getProperty("increaseCount");
    	
    	try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, rewardNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			close(pstmt);
		}
    	
    	return result;
    }
    
    /**
     * 게시글 상세조회
     * @param conn
     * @param reward
     * @return
     */
    public Reward selectReward(Connection conn, int reward) {
    	
    	Reward r = null;
    	PreparedStatement pstmt = null;
    	ResultSet rset = null;
    	
    	String sql = prop.getProperty("selectReward");
    	
    	try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, reward);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				
				r = new Reward();
				r.setChalRewardNo(rset.getInt("CHAL_REWARD_NO")); 
				r.setChalRewardTitle(rset.getString("CHAL_REWARD_TITLE"));
				r.setChalRewardDate(rset.getDate("CHAL_REWARD_DATE"));
				r.setChalRewardHit(rset.getInt("CHAL_REWARD_HIT"));
				r.setChalRewardConts(rset.getString("CHAL_REWARD_CONTS"));
				r.setTitleImg(rset.getString("TITLEIMG"));
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			close(rset);
			close(pstmt);
		}
    	
    	return r;
    }
    
    /**
     * 첨부파일 상세 조회
     * @param conn
     * @param chalRewardNo
     * @return
     */
    public RewardAttachment selectRewardAttachment(Connection conn, int chalRewardNo){
    	
    	RewardAttachment ra = null;
    	PreparedStatement pstmt = null;
    	ResultSet rset = null;
    	
    	String sql = prop.getProperty("selectRewardAttachment");
    	
    	try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, chalRewardNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				
				ra = new RewardAttachment();
				ra.setReFileNo(rset.getInt("RE_FILE_NO"));
				ra.setReOriginName(rset.getString("RE_ORIGIN_NAME"));
				ra.setReChangeName(rset.getString("RE_CHANGE_NAME"));
				ra.setReFilePath(rset.getString("RE_FILE_PATH"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			close(rset);
			close(pstmt);
		}
    	
    	return ra;
    }
    
    /**
     * 리워드 수정 페이지 폼 가져오기 (내용)
     * @param conn
     * @param chalRewardNo
     * @return
     */
    public Reward updateFormReward(Connection conn, int chalRewardNo) {
    	
    	Reward r = null;
    	PreparedStatement pstmt = null;
    	ResultSet rset = null;
    	
    	String sql = prop.getProperty("updateFormReward");
    	
    	try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, chalRewardNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				
				r = new Reward(rset.getInt("CHAL_REWARD_NO")
							 , rset.getString("CHAL_REWARD_TITLE")
							 , rset.getDate("CHAL_REWARD_DATE")
							 , rset.getInt("CHAL_REWARD_HIT")
							 , rset.getString("CHAL_REWARD_CONTS")); 
			} 

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			close(rset);
			close(pstmt);
		}
    	
    	return r;
    }
    
    /**
     * 리워드 수정 페이지 폼 가져오기 (첨부파일)
     * @param conn
     * @param chalRewardNo
     * @return
     */
    public RewardAttachment updateFormRewardAttachment(Connection conn, int chalRewardNo) {
    	
    	RewardAttachment ra = null;
    	PreparedStatement pstmt = null;
    	ResultSet rset = null;
    	
    	String sql = prop.getProperty("updateFormRewardAttachment");
    	
    	try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, chalRewardNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				
				ra = new RewardAttachment(rset.getInt("RE_FILE_NO")
										, rset.getString("RE_ORIGIN_NAME")
										, rset.getString("RE_CHANGE_NAME")
										, rset.getString("RE_FILE_PATH"));	
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
			close(rset);
			close(pstmt);
		}
    	
    	return ra;
    }
    
    /**
     * 게시글 수정 결과 (내용)
     * @param conn
     * @param r
     * @return
     */
    public int updateReward(Connection conn, Reward r) {
    	
    	int result = 0;
    	PreparedStatement pstmt = null;
    	
    	String sql = prop.getProperty("updateReward");
    	
    	try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, r.getChalRewardTitle());
			pstmt.setString(2, r.getChalRewardConts());
			pstmt.setInt(3, r.getChalRewardNo());
			
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
    	
    	return result;
    }
    
    /**
     * 게시글 수정 결과 (첨부파일)
     * @param conn
     * @param ra
     * @return
     */
    public int updateRewardAttachment(Connection conn, RewardAttachment ra) {
    	
    	int result = 0;
    	PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updateRewardAttachment");
    	
    	try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, ra.getReOriginName());
			pstmt.setString(2, ra.getReChangeName());
			pstmt.setString(3, ra.getReFilePath());
			pstmt.setInt(4, ra.getReFileNo());
						
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
    	
    	return result;
    }
    
    /**
     * 게시글 내용 삭제
     * @param conn
     * @param chalRewardNo
     * @return
     */
    public int deleteReward(Connection conn, int chalRewardNo) {
    	
    	int result = 0;
    	PreparedStatement pstmt = null;
    	
    	String sql = prop.getProperty("deleteReward");
    	
    	try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, chalRewardNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
    	
    	return result;
    }
    
    /**
     * 첨부파일 삭제
     * @param conn
     * @param chalRewardNo
     * @return
     */
    public int deleteRewardAttachment(Connection conn, int reFileNo) {
    	
    	int result = 0;
    	PreparedStatement pstmt = null;
    	
    	String sql = prop.getProperty("deleteRewardAttachment");
    	
    	try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, reFileNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
    	
    	return result;
    }
}
