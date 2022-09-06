package edu.kh.jdbc1;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.kh.jdbc1.model.vo.Emp;
import edu.kh.jdbc1.model.vo.Employee;
import oracle.jdbc.connector.OracleManagedConnection;

public class JDBCExample4 {

	public static void main(String[] args) {
	
		// 직급명, 급여를 입력받아
		// 해당 직급에서 입력 받은 급여보다 많이 받는 사원의 
		// 이름, 직급명, 급여, 연봉을 조회하여 출력
		
		// 단, 조회 결과가 없으면, "조회결과업음" 출력
		// 조회 결과가 있으면
		// 선동일 / 대표 / 8000000 / 96000000
		// 송종기 / 부장 / 6000000 / 72000000
		// ....
		
		Scanner sc = new Scanner(System.in);

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			System.out.print("직급명 : ");
			String input1 = sc.nextLine();
			System.out.print("급여 : ");
			int input2 = sc.nextInt();

			Class.forName("oracle.jdbc.driver.OracleDriver");
			String type = "jdbc:oracle:thin:@";
			String ip = "localhost";
			String port = ":1521";
			String sid = ":XE";
			String user = "kh_rsc";
			String pw = "kh1234";

			conn = DriverManager.getConnection(type + ip + port + sid, user, pw);
//			String url = "jdbc:oracle:thin:@localhost:1521:XE";
			
			String sql = " SELECT EMP_NAME , JOB_NAME, SALARY , SALARY*12 " + " FROM EMPLOYEE A "
					+ " JOIN JOB B ON(A.JOB_CODE = B.JOB_CODE)" + " WHERE JOB_NAME = '" + input1 + "' AND SALARY > '"
					+ input2 + "'";
//			'" + input+ "'";
			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);

			List<Employee> list = new ArrayList<>();

			while (rs.next()) {

				// 현재 행에 존재하는 컬럼 값 얻어오기
				String empName = rs.getString("EMP_NAME");
				String jobName = rs.getString("JOB_NAME");
				int salary = rs.getInt("SALARY");
				int annualIncome = rs.getInt("SALARY*12");

				Employee employee = new Employee(empName, jobName, salary, annualIncome);
				list.add(employee);
				// == list.add(new Employee(empName, jobName, salary, annualIncome));
			}

			if (list.isEmpty()) {

				System.err.println("조회 결과가 없습니다.");

			} else {

				for (Employee employee : list) {
					System.out.println(employee);
				}

			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();

			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}

}
