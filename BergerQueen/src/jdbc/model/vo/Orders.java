package jdbc.model.vo;

import java.util.List;

// 주문 번호를 저장하는 VO
public class Orders {

	private int orderNo;
	private String orderDate;
	private List<MenuSelection> MenuSelectionLIst;
	
	private Orders() {	 }

	public Orders(int orderNo, String orderDate, List<MenuSelection> menuSelectionLIst) {
		super();
		this.orderNo = orderNo;
		this.orderDate = orderDate;
		MenuSelectionLIst = menuSelectionLIst;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public List<MenuSelection> getMenuSelectionLIst() {
		return MenuSelectionLIst;
	}

	public void setMenuSelectionLIst(List<MenuSelection> menuSelectionLIst) {
		MenuSelectionLIst = menuSelectionLIst;
	}


	
	
	
}
