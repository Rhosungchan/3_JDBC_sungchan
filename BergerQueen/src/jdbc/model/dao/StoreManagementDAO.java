package jdbc.model.dao;

import static jdbc.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.nio.file.ClosedDirectoryStreamException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Properties;

import jdbc.model.vo.StoreManagement;
import jdbc.model.vo.User;

public class StoreManagementDAO {

	private Statement stmt; 
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Properties prop;

	public StoreManagementDAO() { 
		
		try {
			prop = new Properties();
			prop.loadFromXML(new FileInputStream("storeManagement-query.xml"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	/** 판매 내역 DAO
	 * @param conn
	 * @return selectsellList
	 * @throws Exception
	 */
	public List<StoreManagement> selectSell(Connection conn) throws Exception{

		List<StoreManagement> selectsellList = new ArrayList<>();

		try {
			String sql = prop.getProperty("selectSellList");
			
			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				int sellNo = rs.getInt("ORDERS_NO");
				String sellDate = rs.getString("ORDERS_DATE");
				String sellList = rs.getString("MENU");
				int sellPrice = rs.getInt("TOTAL");

				StoreManagement storeMG = new StoreManagement();
			
				storeMG.setSellNo(sellNo);
				storeMG.setSellDate(sellDate);
				storeMG.setSellList(sellList);
				storeMG.setSellPrice(sellPrice);
				
				selectsellList.add(storeMG);
			}

		} finally {
			close(rs);
			close(stmt);
		}

		return selectsellList;
	}


//	public List<StoreManagement> monthsaleSelect(Connection conn) throws Exception{
//
//		try {
//			
//			String sql = prop.getProperty("");
//			
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(0, sql);
//			
//			
//			rs = pstmt.executeQuery();
//			
//			while(rs.next()) {
//				
//				String sellDate = rs.getString("ORDERS_DATE");
//				String sellList = rs.getString("MENU");
//				int sellPrice = rs.getInt("TOTAL");
//
//				StoreManagement storeMG = new StoreManagement();
//			
//				storeMG.setSellDate(sellDate);
//				storeMG.setSellList(sellList);
//				storeMG.setSellPrice(sellPrice);
//				
////				monthS.add(storeMG);
//			}
//			
//		} finally {
//			
//			close(rs);
//			close(pstmt);
//			
//		}
		
		
			
//		return monthS;
//	}

	
}
