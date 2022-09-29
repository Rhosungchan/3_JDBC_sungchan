package jdbc.model.vo;

public class User {
	
	private int userNo;
	private String userId;
	private String userPw;
	private String userName;
	private String userGender;
	private String userNickname;
	private String userSsn;
	private String enrollDate;
	private int userPay;
	private String userJobname;
	private String Phone;

	public User() {	}


	public User(String userId, String userPw, String userName, String userGender, String userNickname,
			String userSsn, int userPay, String userJobname, String phone) {
		super();
		this.userId = userId;
		this.userPw = userPw;
		this.userName = userName;
		this.userGender = userGender;
		this.userNickname = userNickname;
		this.userSsn = userSsn;
		this.userPay = userPay;
		this.userJobname = userJobname;
		this.Phone = phone; 
	}







	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserGender() {
		return userGender;
	}

	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}

	public String getUserNickname() {
		return userNickname;
	}

	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}

	public String getUserSsn() {
		return userSsn;
	}

	public void setUserSsn(String userSsn) {
		this.userSsn = userSsn;
	}

	public String getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(String enrollDate) {
		this.enrollDate = enrollDate;
	}

	public int getUserPay() {
		return userPay;
	}

	public void setUserPay(int userPay) {
		this.userPay = userPay;
	}

	public String getUserJobname() {
		return userJobname;
	}

	public void setUserJobname(String userJobname) {
		this.userJobname = userJobname;
	}

	public String getPhone() {
		return Phone;
	}

	public void setPhone(String phone) {
		this.Phone = phone;
	}
	
}
