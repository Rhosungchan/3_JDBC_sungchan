package jdbc.model.dao;

import static jdbc.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import jdbc.model.vo.MenuSelection;
import jdbc.model.vo.Orders;

public class OrdersDAO {

	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs; 
	private Properties prop;
	
	public OrdersDAO() {
		
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("orders-query.xml"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/** 주문번호 생성 DAO
	 * @param conn
	 * @return orderNo
	 * @throws Exception
	 */
	public int nextOrdersNo(Connection conn) throws Exception{
		int orderNo = 0; 
		
		try {
			
			String sql = prop.getProperty("nextOrdersNo");
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				orderNo = rs.getInt(1);
			}
			
		} finally {
			close(rs);
			close(pstmt);
		}
		return orderNo;
	}

	/** 주문 삽입 DAO
	 * @param conn
	 * @param ordersNo
	 * @return result
	 * @throws Exception
	 */
	public int insertOrders(Connection conn, int ordersNo) throws Exception{
		int result = 0;
		
		try {
			
			String sql = prop.getProperty("insertOrders");
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1,  ordersNo);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	/** 주문별 메뉴 테이블 삽입
	 * @param conn
	 * @param m
	 * @return
	 * @throws Exception
	 */
	public int insertOrderMenu(Connection conn, MenuSelection m) throws Exception{
		int result = 0;
		
		try {
			
			String sql = prop.getProperty("insertOrderMenu");
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1,  m.getOrdersNo());
			pstmt.setInt(2,  m.getMenuNo());
			pstmt.setInt(3,  m.getAmount());
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	
	
}
