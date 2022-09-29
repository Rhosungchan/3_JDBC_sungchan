package jdbc.model.vo;

public class StoreManagement {

	private int sellNo;      // 주문 번호 
	private String sellDate; // 주문 일자 
	private String sellList; // 주문 내역 
	private int sellPrice;   // 토탈 금액 
	
	public StoreManagement() {	}

	
	
	public StoreManagement(String sellDate, String sellList, int sellPrice) {
		super();
		this.sellDate = sellDate;
		this.sellList = sellList;
		this.sellPrice = sellPrice;
	}



	public StoreManagement(int sellNo, String sellDate, String sellList, int sellPrice) {
		super();
		this.sellNo = sellNo;
		this.sellDate = sellDate;
		this.sellList = sellList;
		this.sellPrice = sellPrice;
	}

	public int getSellNo() {
		return sellNo;
	}

	public void setSellNo(int sellNo) {
		this.sellNo = sellNo;
	}

	public String getSellDate() {
		return sellDate;
	}

	public void setSellDate(String sellDate) {
		this.sellDate = sellDate;
	}

	public String getSellList() {
		return sellList;
	}

	public void setSellList(String sellList) {
		this.sellList = sellList;
	}

	public int getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(int sellPrice) {
		this.sellPrice = sellPrice;
	}
	
	
	
	
	
}
