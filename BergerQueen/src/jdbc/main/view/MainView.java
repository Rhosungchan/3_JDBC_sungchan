package jdbc.main.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import jdbc.main.service.MainService;
import jdbc.model.vo.User;
import jdbc.service.UserService;
import jdbc.view.OrdersView;
import jdbc.view.StoreManagementView;
import jdbc.view.UserView;

public class MainView {

	private Scanner sc = new Scanner(System.in);
	private MainService service = new MainService();
	public static User loginUser = null;

	private UserView userView = new UserView();
	private OrdersView ordersView = new OrdersView(); 
	private StoreManagementView sellView = new StoreManagementView();
	
	/**
	 * 메인 메뉴 화면
	 */
	public void mainMenu() {

		int input = -1;

		do {
			try {

				if (loginUser == null) { // 로그인 X

					System.out.println("\n♥♥♥[ BurgerQueen Management System ]♥♥♥");
					System.out.println("===========================================");
					System.out.println("  1. 로그인 ");
					System.out.println("  2. 회원가입 ");
					System.out.println("  0. 프로그램 종료");
					System.out.println("===========================================");
					System.out.print("\n 메뉴 선택 : \n");
					input = sc.nextInt();
					sc.nextLine();

					switch (input) {
					case 1:  login();  break;
					case 2: signUp();  break;
					case 0: System.out.println("[프로그램 종료]"); break;
					default: System.out.println("[메뉴에 작성된 번호를 선택해주세요.]");
					}

				} else { // 로그인 O
					System.out.println("\n=======[로그인 메뉴]=======");
					System.out.println("  1. 주문 시스템 실행");
					System.out.println("  2. 직원관리 ");
					System.out.println("  3. 매출관리");
					System.out.println("  0. 로그아웃");
					System.out.println("  99. 프로그램 종료");
					System.out.println("=============================");
					System.out.print("\n 메뉴를 선택해주세요 : \n");
					input = sc.nextInt();
		
					switch (input) {
					case 1: ordersView.ordersMenu(); break;
					case 2: userView.userMenu(loginUser); break;
					case 3: sellView.sellMenu(); break;
					case 0: loginUser = null; 
 					        System.out.println("로그아웃 되었습니다.");
 					        input = -1;
 					        break;
					case 99: System.out.println("[프로그램 종료]"); 
					         input = 0; break;
					default: System.out.println("[메뉴에 작성된 번호를 선택해주세요.]");
					}

				}
			} catch (InputMismatchException e) {
				e.printStackTrace();
				System.out.println("\n[입력형식이 올바르지 않습니다.]");
				sc.nextLine();

			}

		} while (input != 0);

	}


	/**
	 *  회원 가입 화면
	 */
	private void signUp() {
		System.out.println("[회원 가입]");

		String userId = null;
		String userPw1 = null;
		String userPw2 = null;
		String userName = null;
		String userNickname = null;
		String userSsn = null;
		int userPay = -1;
		String userJobname;
		String Phone;
		String userGender = null;

		try {

			// 아이디 입력 
			while (true) {

				System.out.print("아이디 입력 : ");
				userId = sc.next();

				int result = service.idCheck(userId);
				System.out.println();
				
				if(result == 0 ) {
					System.out.println("\n[사용 가능한 아이디입니다.]");
					break;
				} else {
					System.out.println("\n[이미 사용중인 아이디입니다.]");
				}
				
				System.out.println();
				
			}
			
			// 비밀번호 입력 
			while(true) {
				
				System.out.print("비밀번호 : ");
				userPw1 = sc.next();
				
				System.out.print("비밀번호 확인 : ");
				userPw2 = sc.next();
				
				System.out.println();
				if(userPw1.equals(userPw2)) { // 일치하는 경우
					System.out.println("[일치합니다.]");
					break;
				} else { // 일치하지 않는 경우
					System.out.println("[입력받은 비밀번호가 일치하지 않습니다. 다시 입력해주세요.]");
				}
				System.out.println();
				
			}
			
			// 이름 입력
			System.out.print("이름 입력 : ");
			userName = sc.next();
			
			// 성별 
			// M 또는 F가 입력 될 때까지 무한반복
			
			while(true) {
				System.out.print("성별 입력(M/F) : ");
				userGender = sc.next().toUpperCase(); 
				
				if(userGender.equals("M") || userGender.equals("F")) {  
					break;
				} else {
					System.out.println("[M 또는 F만 입력해주세요]");
				}
				System.out.println();
			}
			
			// 별칭 입력 
			System.out.print("별칭 입력 : ");
			userNickname = sc.next();
			
			// 생년월일 입력
			System.out.print("생년월일 입력 : ");
			userSsn = sc.next();
			
			// 시급 입력
			System.out.print("시급 입력 : ");
			userPay = sc.nextInt();
			
			// 직급 입력
			System.out.print("직급 입력 : ");
			userJobname = sc.next();
			
			// 전화번호 입력
			System.out.print("전화번호 입력 : ");
			Phone = sc.next();
			
			User user = new User(userId, userPw2, userName, userGender, userNickname, 
		             			userSsn, userPay, userJobname, Phone);

			int result = service.signUp(user);

			// 서비스 처리 결과에 따른 출력 화면 제어 
			System.out.println();
			if(result > 0) {
				System.out.println("*****회원가입 성공*****");
			} else {
				System.out.println("*****회원가입 실패*****");
			}
			System.out.println();
			
			
			
		} catch (Exception e) {
				e.printStackTrace();
				System.out.println("\n[회원 가입 중 예외 발생]");
		}
	}

	// 로그인 
	private void login() {

		System.out.println("[로그인]");

		System.out.println("아이디 : ");
		String userId = sc.next();

		System.out.println("비밀번호 : ");
		String userPw = sc.next();

		try {

			loginUser = service.login(userId, userPw);

			if (loginUser != null) {
				System.out.println("[사장님 환영합니다.]");
			} else {
				System.out.println("[아이디 또는 비밀번호가 일치하지 않습니다.]");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("[로그인 중 예외 발생]");
		}

	}

	

}
