package jdbc.service;

import static jdbc.common.JDBCTemplate.*;
import java.sql.Connection;
import java.util.List;

import jdbc.model.dao.OrdersDAO;
import jdbc.model.vo.MenuSelection;
import jdbc.model.vo.Orders;

public class OrdersService {

	private OrdersDAO dao = new OrdersDAO();
	
	
	public int orders(List<MenuSelection> menuList) throws Exception{
		
		// 커넥션 생성
		Connection conn = getConnection();
		
		// 주문번호 생성 DAO 호출
		int ordersNo = dao.nextOrdersNo(conn);
		
		// 주문 삽입
		int result = dao.insertOrders(conn, ordersNo); 
		
		// 주문별 메뉴 테이블 삽입(for문으로 DAO(insert) 호출)
		if (result > 0) {
			result = 0; // result 재사용
			
			for(MenuSelection m : menuList) {
				m.setOrdersNo(ordersNo); // 얻어왔던 orderNo 세팅
				result += dao.insertOrderMenu(conn, m);
			}
			
			if(result == menuList.size()) {
				commit(conn);
				result = ordersNo;
			}else {
				rollback(conn);
				result = 0;
			}
		
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}


		public void burger1() {
			
			Connection conn = getConnection();
			
			
		}
}
