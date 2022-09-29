package jdbc.service;

import static jdbc.common.JDBCTemplate.commit;
import static jdbc.common.JDBCTemplate.rollback;
import static jdbc.common.JDBCTemplate.close;
import static jdbc.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import jdbc.model.dao.UserDAO;
import jdbc.model.vo.User;

public class UserService {

	private UserDAO dao = new UserDAO();
 
	/** 회원 정보 수정 서비스
	 * @param user
	 * @return result
	 * @throws Exception
	 */
	public int updateUser(User user) throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.updateUser(conn, user);
		
		if(result > 0) commit(conn);
		else           rollback(conn);
		
		close(conn);
		
		return result;
	}

	/** 직원 목록 조회 서비스
	 * @return userList
	 * @throws Exception
	 */
	public List<User> userAll() throws Exception{
		
		Connection conn = getConnection();
		
		List<User> userList = dao.userAll(conn);
		
		close(conn);
		
		return userList;
	}

	/** 비밀번호 변경
	 * @param currentPw
	 * @param newPw1
	 * @param userNo
	 * @return result
	 * @throws Exception
	 */
	public int updatePw(String currentPw, String newPw1, int userNo) throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.updatePw(conn, currentPw,  newPw1, userNo);
		
		if(result > 0) commit(conn);
		else           rollback(conn);
		
		close(conn);
		
		return result;
	}

	/** 직원 탈퇴 서비스
	 * @param userPw
	 * @param userNo
	 * @return result
	 * @throws Exception
	 */
	public int userDelete(String userName) throws Exception{
	
		Connection conn = getConnection();
		
		int result = dao.userDelete(conn, userName);
		
		if(result > 0) commit(conn);
		else           rollback(conn);
		
		close(conn);
		
		return result;
	}
			
}
