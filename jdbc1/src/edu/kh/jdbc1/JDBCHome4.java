package edu.kh.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.kh.jdbc1.model.vo.Employee;

public class JDBCHome4 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {

			System.out.print("직급명 : ");
			String inputJobName = sc.next();

			System.out.print("급여 입력 : ");
			int inputsalary = sc.nextInt();

			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:XE";
			String user = "kh_rsc";
			String pw = "kh1234";

			conn = DriverManager.getConnection(url, user, pw);

			String sql = " SELECT EMP_NAME , JOB_NAME, SALARY , SALARY*12 " + " FROM EMPLOYEE A "
					+ " JOIN JOB B ON(A.JOB_CODE = B.JOB_CODE)" + " WHERE JOB_NAME = '" + inputJobName
					+ "' AND SALARY > '" + inputsalary + "'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			List<Employee> list = new ArrayList<>();

			while (rs.next()) {

				String empName = rs.getString("EMP_NAME");
				String jobName = rs.getString("JOB_NAME");
				int salary = rs.getInt("SALARY");
				int annualIncome = rs.getInt("SALARY*12");

				list.add(new Employee(empName, jobName, salary, annualIncome));
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
