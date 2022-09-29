package jdbc.view;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import jdbc.model.vo.MenuSelection;
import jdbc.model.vo.Orders;
import jdbc.service.OrdersService;

public class OrdersView {

	private Scanner sc = new Scanner(System.in);
	private OrdersService os = new OrdersService();

	private List<MenuSelection> menuList = new ArrayList<>();
	
	public void ordersMenu() {

		int input = -1;

		do {

			try {
				System.out.println("\n======[ 주문하기 ]======\n");
				System.out.println("1. 햄버거 메뉴 보기");
				System.out.println("2. 사이드 메뉴 보기");
				System.out.println("3. 음료 메뉴 보기");
				System.out.println("4. 주문내역 보기");
				System.out.println("0. 로그인 메뉴로 이동");
				System.out.println("=======================");
				System.out.print("\n메뉴 선택 : ");
				input = sc.nextInt();
				sc.nextLine();

				switch (input) {
				case 1: burgerMenu(); break;
				case 2: sideMenu(); break;
				case 3: beverageMenu(); break;
				case 4: selectOrders(); break;
				case 0: System.out.println("[메인 메뉴로 이동합니다.]"); break;
				default: System.out.println("[ 메뉴에 있는 번호를 선택해주세요 ]"); break;
				}

			} catch (InputMismatchException e) {
				System.out.println("\n<<입력 형식이 올바르지 않습니다.>>");
				sc.nextLine();
			}
		} while (input != 0);

	}

	/**
	 * 1. 햄버거 메뉴 보기
	 */
	private void burgerMenu() {
		int input = -1;
	
		do {
			
			try {
				System.out.println("\n=======[ 햄버거 메뉴 ]========");
				System.out.println("==============================");
				System.out.println("1. 불고기 버거 ----- (6000원)");
				System.out.println("2. 쉬림프 버거 ----- (7000원)");
				System.out.println("3. 어린이 버거 ----- (4000원)");
				System.out.println("4. 존맛탱 버거 ----- (8000원)");
				System.out.println("5. 더블 치즈 버거 -- (9000원)");
				System.out.println("6. 내장파괴 버거 --- (12000원)");
				System.out.println("0. 다른 메뉴 선택");
				System.out.println("==============================");
				System.out.print("\n메뉴 선택 : ");
				input = sc.nextInt();
				sc.nextLine();
				
				if(input == 0) {
					System.out.println("[다른 메뉴 선택]"); 
					
				} else if(input >=1 && input <= 6 ) {
					System.out.print("수량 : ");
					int amount = sc.nextInt();
					sc.nextLine();
					
					MenuSelection ms = new MenuSelection();
					ms.setMenuNo(input);
					ms.setAmount(amount);
					
					menuList.add(ms); // 메뉴 리스트에 추가
					
				} else {
					 System.out.println("[ 메뉴에 있는 번호를 선택해주세요 ]"); 
				}
				
				
			} catch (Exception e) {
				System.out.println("\n[ 햄버거 메뉴 실행 중 예외 발생 ]\n");
				e.printStackTrace();
			}
		
		} while(input !=0 );
	}
	/**
	 * 2. 사이드 메뉴 보기
	 */
	private void sideMenu() {
		int input = -1;
		do {
		try {
			System.out.println("\n========[ 사이드 메뉴 ]=========");
			System.out.println("================================");
			System.out.println("7. 치즈 스틱(2EA) ---- (3000원)");
			System.out.println("8. 어니언 링(3EA) ---- (2000원)");
			System.out.println("9. 모짜볼(3EA) ------- (4000원)");
			System.out.println("10. 치킨 텐더(3EA) --- (5000원)");
			System.out.println("11. 코운슬로 --------- (2000원)");
			System.out.println("12. 감자튀김 --------- (2000원)");
			System.out.println("13. 아이스크림 ------- (1500원)");
			System.out.println("0. 다른 메뉴 선택");
			System.out.println("================================");
			System.out.print("\n메뉴 선택 : ");
			input = sc.nextInt();
			sc.nextLine();
			
			if(input == 0) {
				System.out.println("[다른 메뉴 선택]"); 
				
			} else if(input >=7 && input <= 13 ) {
				System.out.print("수량 : ");
				int amount = sc.nextInt();
				sc.nextLine();
				
				MenuSelection ms = new MenuSelection();
				ms.setMenuNo(input);
				ms.setAmount(amount);
				
				menuList.add(ms); // 메뉴 리스트에 추가
				
			} else {
				 System.out.println("[ 메뉴에 있는 번호를 선택해주세요 ]");
			}
			
		} catch (Exception e) {
			System.out.println("\n[사이드 메뉴 실행 중 예외 발생]\n");
			e.printStackTrace();
		}
		} while(input !=0 );
	}
	/**
	 * 3. 음료 메뉴 보기
	 */
	private void beverageMenu() {
		int input = -1;
		do {
		try {
			System.out.println("\n========[ 음료 메뉴 ]=========");
			System.out.println("==============================");
			System.out.println("14. 콜라(제로) ----- (2000원)");
			System.out.println("15. 콜라 ----------- (1500원)");
			System.out.println("16. 사이다(제로) --- (2000원)");
			System.out.println("17. 사이다 --------- (1500원)");
			System.out.println("18. 레몬 에이드 ---- (3000원)");
			System.out.println("19. 마운틴 듀 ------ (1500원)");
			System.out.println("0. 다른 메뉴 선택");
			System.out.println("==============================");
			System.out.print("\n메뉴 선택 : ");
			input = sc.nextInt();
			sc.nextLine();
			
			if(input == 0) {
				System.out.println("[다른 메뉴 선택]"); 
				
			} else if(input >=14 && input <= 19 ) {
				System.out.print("수량 : ");
				int amount = sc.nextInt();
				sc.nextLine();
				
				MenuSelection ms = new MenuSelection();
				ms.setMenuNo(input);
				ms.setAmount(amount);
				
				menuList.add(ms); // 메뉴 리스트에 추가
				
			} else {
				 System.out.println("[ 메뉴에 있는 번호를 선택해주세요 ]");
			}
			
		} catch (Exception e) {
			System.out.println("\n [ 음료 메뉴 실행 중 예외 발생 ] \n");
			e.printStackTrace();
		}
		} while(input !=0 );
	}
	
	/**
	 * 4. 주문내역 보기
	 */
	private void selectOrders() {
		for(MenuSelection m : menuList) {
			System.out.printf("%d번 %d개\n", m.getMenuNo(), m.getAmount());
		}
		
		try {
			int result = os.orders(menuList);
			
			if(result > 0) {
				System.out.println("["+result + "번 주문 완료 ]");
			}else {
				System.out.println("[ 주문 실패 ]");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
