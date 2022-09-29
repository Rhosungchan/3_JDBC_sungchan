package jdbc.model.dao;

import static jdbc.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import jdbc.model.vo.User;

public class UserDAO {

	private Statement stmt; 
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Properties prop;
	
	public UserDAO() {	
		
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("user-query.xml"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/** 회원 정보 수정 DAO
	 * @param conn
	 * @param user
	 * @return result
	 * @throws Exception
	 */
	public int updateUser(Connection conn, User user) throws Exception{

		int result = 0;
		
		try {
			String sql = prop.getProperty("updateUser");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,  user.getUserName());
			pstmt.setString(2,  user.getUserGender());
			pstmt.setString(3,  user.getUserNickname());
			pstmt.setInt(4,     user.getUserPay());
			pstmt.setString(5,  user.getUserJobname());
			pstmt.setString(6,  user.getPhone());
			pstmt.setInt(7,  	user.getUserNo());
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	/** 직원 목록 조회 DAO
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	public List<User> userAll(Connection conn) throws Exception{
		
		List<User> userList = new ArrayList<>();
		
		try {
			String sql = prop.getProperty("userAll");
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				int userNo          = rs.getInt("USER_NO");
				String userId       = rs.getString("USER_ID");
				String userName     = rs.getString("USER_NAME");
				String userGender   = rs.getString("USER_GENDER");
				String userNickname = rs.getString("USER_NICKNAME");
				String userSsn      = rs.getString("USER_SSN");
				int userPay         = rs.getInt("USER_PAY");
				String userJobname  = rs.getString("USER_JOBNAME");
				String userPhone    = rs.getString("PHONE");
				
				User user = new User();
				user.setUserNo(userNo);
				user.setUserId(userId);
				user.setUserName(userName);
				user.setUserGender(userGender);
				user.setUserNickname(userNickname);
				user.setUserSsn(userSsn);
				user.setUserPay(userPay);
				user.setUserJobname(userJobname);
				user.setPhone(userPhone);
				
				userList.add(user);
			}
			
			
		} finally {
			close(rs);
			close(stmt);
		}
		
		return userList;
	}

	/** 비밀번호 변경 DAO
	 * @param conn
	 * @param currentPw
	 * @param newPw1
	 * @param userNo
	 * @return result
	 * @throws Exception
	 */
	public int updatePw(Connection conn, String currentPw, String newPw1, int userNo) throws Exception{
		
		int result = 0;
		
		try {
			
			String sql = prop.getProperty("updatePw");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,  newPw1);
			pstmt.setInt(2,  userNo);
			pstmt.setString(3,  currentPw);
			
			result = pstmt.executeUpdate();
			
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	/** 회원탈퇴 DAO
	 * @param conn
	 * @param userPw
	 * @param userNo
	 * @return result
	 * @throws Exception
	 */
	public int userDelete(Connection conn, String userName) throws Exception{
		
		int result = 0; 
		
		try {
			
			String sql = prop.getProperty("userDelete");
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, userName);

			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}
}
