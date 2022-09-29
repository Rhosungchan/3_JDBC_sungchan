package jdbc.model.vo;

// 주문 내역을 저장하는 VO
public class MenuSelection {

	private int ordersNo;    // 주문 번호 
	private int menuNo; // 주문 내역 
	private int amount;      // 주문 수량 
	
	public MenuSelection() {	}

	public int getOrdersNo() {
		return ordersNo;
	}

	public void setOrdersNo(int ordersNo) {
		this.ordersNo = ordersNo;
	}

	public int getMenuNo() {
		return menuNo;
	}

	public void setMenuNo(int menuNo) {
		this.menuNo = menuNo;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	
	
	
}
