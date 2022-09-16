package edu.kh.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCHome3 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		
		Connection conn = null; 
		Statement stmt = null;
		ResultSet rs = null; 
		
		try {
			
			System.out.print("부서명 입력 : ");
			String input = sc.nextLine();
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:XE";
			String user = "kh_rsc";
			String pw = "kh1234";
			
			conn = DriverManager.getConnection(url , user, pw); 
			String sql = "SELECT EMP_NAME," 
					+ "	NVL(DEPT_TITLE, '부서없음') AS DEPT_TITLE , SALARY" 
					+ " FROM EMPLOYEE"
					+ " LEFT JOIN DEPARTMENT ON (DEPT_CODE = DEPT_ID)" 
					+ " WHERE NVL(DEPT_TITLE, '부서없음') = '" + input
					+ "'";
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
		
	}
}
