package jdbc.service;

import static jdbc.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import jdbc.model.dao.StoreManagementDAO;
import jdbc.model.vo.StoreManagement;


public class StoreManagementService {

	private StoreManagementDAO dao = new StoreManagementDAO();
	
	/** 판매 내역 서비스 
	 * @return sellList
	 * @throws Exception
	 */
	public List<StoreManagement> selectSell() throws Exception{
		
		Connection conn = getConnection();

		
		List<StoreManagement> sellList = dao.selectSell(conn);
		
		close(conn);
		
		return sellList;
	}


	/** 월별 매출 조회 서비스 
	 * @param sellDate
	 * @return months
	 * @throws Exception
	 */
//	public List<StoreManagement> monthsaleSelect(String sellDate) throws Exception{
//		Connection conn = getConnection();
//		
////		List<StoreManagement> months = dao.monthsaleSelect(conn);
//		
//		close(conn);
//		return months;
//		
//	}







}
