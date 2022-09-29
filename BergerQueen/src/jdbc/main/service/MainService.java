package jdbc.main.service;

import static jdbc.common.JDBCTemplate.*;

import java.sql.Connection;

import jdbc.main.model.dao.MainDAO;
import jdbc.model.vo.User;


public class MainService {

	private MainDAO dao = new MainDAO();
	
	/** 로그인 서비스 
	 * @param userId
	 * @param userPw
	 * @return loginUser
	 * @throws Exception
	 */
	public User login(String userId, String userPw) throws Exception{

		Connection conn = getConnection();
		
		User loginUser = dao.login(conn, userId, userPw);
		
		close(conn);
		
		return loginUser;
	}

	/** 아이디 중복 검사 서비스
	 * @param userId
	 * @return result
	 * @throws Exception
	 */
	public int idCheck(String userId) throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.idCheck(conn, userId);
		
		close(conn);
		
		return result;
	}

	/** 회원가입 서비스 
	 * @param user
	 * @return result
	 * @throws Exception
	 */
	public int signUp(User user) throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.signUp(conn,user);
		
		if(result > 0) commit(conn);
		else           rollback(conn);
		
		close(conn);
		
		return result;
	}


}
