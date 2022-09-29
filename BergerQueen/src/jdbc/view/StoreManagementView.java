package jdbc.view;

import static jdbc.common.JDBCTemplate.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import jdbc.model.vo.StoreManagement;
import jdbc.model.vo.User;
import jdbc.service.StoreManagementService;


// 매출 관리 메뉴 
public class StoreManagementView {

	private Scanner sc = new Scanner(System.in);
	private StoreManagementService service = new StoreManagementService(); 
	private int input = -1;
	
	public void sellMenu() {

		do {

			try {
				System.out.println("\n =========[매장 관리 메뉴]=========");
				System.out.println("  1. 주문 내역");
				System.out.println("  2. 일별 매출 내역");
				System.out.println("  3. 월별 매출 내역");
				System.out.println("  4. 직원 공지사항");
				System.out.println("  0. 메인 메뉴로 이동");
				System.out.println("\n ==================================");
				System.out.print("\n메뉴 선택 : ");
				input = sc.nextInt();
				sc.nextLine();
				
				switch (input) {
				case 1:  sellList(); break;
				case 2:  dailysaleSelect(); break;
				case 3:  monthsaleSelect(); break;
				case 4:  /* 직원 공지 사항 */ break;
				case 0:  System.out.println("[메인 메뉴로 이동합니다.]"); break;
				default: System.out.println("[ 메뉴에 있는 번호를 선택해주세요 ]"); break;
				}
				
				

			} catch (Exception e) {
				e.printStackTrace();
			}

		} while (input != 0);

	}




	/**
	 * 판매 내역 
	 */
	private void sellList() {

		System.out.println("\n[판매 내역]");

		try {
			List<StoreManagement> sellList = service.selectSell();

			if (sellList.isEmpty()) {
				System.out.println("판매 내역이 없습니다.");
			} else {
				System.out.println("|   주문 번호   |        주문 일자        |   총 금액   |                          주문 내역                                |");
				System.out.println("==============================================================================================================================");
				for (StoreManagement storeMG : sellList) {
					System.out.printf("        %d           %s        %d         %s \n", storeMG.getSellNo(), storeMG.getSellDate(),
							storeMG.getSellPrice() ,storeMG.getSellList() );
				}
			}

		} catch (Exception e) {
			System.out.println("판매 내역 조회 중 예외 발생");
			e.printStackTrace();
		}

	}
	
	/**
	 * 일별 매출 조회
	 */
	private void dailysaleSelect() {
		System.out.println("\n[일별 매출 조회]");
		
		try {
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	/**
	 * 월별 매출 조회 
	 * @param sellDate 
	 */
	private void monthsaleSelect() {
		System.out.println("\n[월별 매출 조회]");

			try {
				System.out.println("찾으시는 월을 입력해주세요 \n (*숫자만 입력해주세요)");
				int input= sc.nextInt();
				sc.nextLine();

				if (input > 0 && input <= 12) {
				
//					StoreManagement storeMG = service.monthsaleSelect();
//					List<StoreManagement> months = service.monthsaleSelect();
					
				} else {
					System.out.println("[1~12 중에서 입력해주세요!]");
				}

			} catch (Exception e) {
				System.out.println("[월별 매출 조회 중 예외 발생]");
				e.printStackTrace();
			}
	}

}
