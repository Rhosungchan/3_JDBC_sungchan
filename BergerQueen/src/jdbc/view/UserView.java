package jdbc.view;

import java.util.List;
import java.util.Scanner;

import jdbc.main.view.MainView;
import jdbc.model.vo.User;
import jdbc.service.UserService;


// 직원 관리 메뉴 
public class UserView {
	
	private Scanner sc = new Scanner(System.in);
	private UserService service = new UserService();
	private User loginUser = null;
	private int input = -1;
	
	public void userMenu(User loginUser) {
		
		this.loginUser = loginUser;
		do {

			try {
				System.out.println("\n =========[직원 관리 메뉴]=========");
				System.out.println("  1. 내 정보 조회");
				System.out.println("  2. 내 정보 수정");
				System.out.println("  3. 비밀번호 변경");
				System.out.println("  4. 직원 목록 조회");
				System.out.println("  5. 직원 탈퇴");
				System.out.println("  0. 메인 메뉴로 이동");
				System.out.println("\n ==================================");
				System.out.print("\n메뉴선택 : ");
				input = sc.nextInt();
				sc.nextLine();
				
				switch (input) {
				case 1:  selectMyInfo();  break;
				case 2:  updateUser();    break;
				case 3:  updatePw();      break;
				case 4:  userAll();       break;
				case 5:  userDelete();    break;
				case 0: System.out.println("[메인 메뉴로 이동합니다.]"); break;
				default: System.out.println("[메뉴에 작성된 번호만 입력해주세요.]");
					break;
				}
				
			} catch (Exception e) {
				System.out.println("\n[입력 형식이 올바르지 않습니다.]");
				sc.nextLine();
				e.printStackTrace();
			}
		} while (input != 0);
	}




	/**
	 *  1. 내 정보 조회
	 */
	private void selectMyInfo() {
		System.out.println("\n[내 정보 조회]\n");
		
		System.out.println("회원 번호 : " + loginUser.getUserNo());
		System.out.println("아이디    : "    + loginUser.getUserId());
		System.out.println("이름      : "      + loginUser.getUserName());
		
		System.out.print("성별      : " );
		if(loginUser.getUserGender().equals("M")) {
			System.out.println("남");
		} else {
			System.out.println("여");
		}
		System.out.println("닉네임    : " + loginUser.getUserNickname());
		System.out.println("생년월일  : " + loginUser.getUserSsn());
		System.out.println("가입일    : " + loginUser.getEnrollDate());
		System.out.println("시급      : " + loginUser.getUserPay());
		System.out.println("직급      : " + loginUser.getUserJobname());
		System.out.println("전화번호  : " + loginUser.getPhone());
	}
	
	/**
	 * 2. 내 정보 수정 
	 */
	private void updateUser() {
		
		try {
			System.out.println("\n[회원 정보 수정]\n");
			
			System.out.print("변경할 이름 : ");
			String userName = sc.next();
			
			String userGender = null;
			while(true) {
				System.out.print("변경할 성별(M/F) : ");

				userGender = sc.next().toUpperCase();
				
				if(userGender.equals("M") || userGender.equals("F")) {
					break;
				}else {
					System.out.println("M 또는 F만 입력해주세요.");
				}
			}
			System.out.print("변경할 닉네임 : ");
			String userNickname = sc.next();
			System.out.print("변경할 시급 : ");
			int userPay = sc.nextInt();
			System.out.print("변경할 직급 : ");
			String userJobname = sc.next();
			System.out.print("변경할 전화번호 : ");
			String Phone = sc.next();
			
			User user = new User();
			user.setUserNo(loginUser.getUserNo());
			user.setUserName(loginUser.getUserName());
			user.setUserGender(loginUser.getUserGender());
			user.setUserNickname(loginUser.getUserNickname());
			user.setUserPay(loginUser.getUserPay());
			user.setUserJobname(loginUser.getUserJobname());
			user.setPhone(loginUser.getPhone());
			
			int result = service.updateUser(user);
			
			if(result > 0) {
				loginUser.setUserName(userName);
				loginUser.setUserGender(userGender);
				loginUser.setUserNickname(userNickname);
				loginUser.setUserPay(userPay);
				loginUser.setUserJobname(userJobname);
				loginUser.setPhone(Phone);
				System.out.println("\n[회원 정보가 수정되었습니다.]\n");
				
			} else {
				System.out.println("\n[수정 실패]\n");
			}
			
			
		} catch (Exception e) {
			System.out.println("\n[회원 정보 수정 중 예외 발생]\n");
			e.printStackTrace();
		}
	}


	/**
	 * 3. 비밀번호 변경
	 */
	private void updatePw() {
		System.out.println("\n[비밀 번호 변경]\n");
		
		try {
			
			System.out.print("현재 비밀번호 : ");
			String currentPw = sc.next();
			
			String newPw1 = null;
			String newPw2 = null;
			
			while(true) {
				System.out.print("새 비밀번호 : ");
				newPw1 = sc.next();
				
				System.out.print(" 새 비밀번호 확인 : ");
				newPw2 = sc.next();
				
				if(newPw1.equals(newPw2)) {
					break;
				} else {
					System.out.println("\n새 비밀번호가 일치하지 않습니다. 다시 입력해주세요.\n");
				}
			} 
			
			int result = service.updatePw(currentPw, newPw1, loginUser.getUserNo());

			if(result > 0 ) {
				System.out.println("\n[비밀번호가 변경되었습니다.]\n");
			} else {
				System.out.println("\n[현재 비밀번호가 일치하지 않습니다.]\n");
			}
			
		} catch (Exception e) {
			System.out.println("\n[비밀번호 변경 중 예외 발생]\n");
			e.printStackTrace();
		}
		
	}
		
	
	/**
	 * 4. 회원 목록 조회
	 */
	private void userAll() {
		
		System.out.println("\n[직원 목록 조회]\n");
		
		try {
			List<User> userList = service.userAll();
			
			if(userList.isEmpty()) {
				System.out.println("\n[조회 결과가 없습니다.]\n");
			} else {
				System.out.println("|   아이디   |   이름   |   성별   |   닉네임   |   시급   |   직급   |     전화번호    | ");
				System.out.println("==========================================================================================");
				for( User user : userList) {
					System.out.printf("%10s    %5s       %s     %10s %10d %10s %15s \n",
									  user.getUserId(),
							          user.getUserName(),
							          user.getUserGender(),
							          user.getUserNickname(),
							          user.getUserPay(),
							          user.getUserJobname(),
							          user.getPhone());
				}
			}
			
//			userId / userName / userGender / userNickname 
//			/ userPay / userJobname /Phone
			
		} catch (Exception e) {
			System.out.println("\n[직원 목록 조회 중 예외 발생]\n");
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 5. 직원 탈퇴 
	 */
	private void userDelete() {

		System.out.println("\n[직원 탈퇴 삭제]\n");
	
		try {
			
			System.out.print("비밀번호 입력: ");
			String userPw = sc.next();
			
			while(true) {
				System.out.print("정말 떠나갈거니? (Y/N) : ");
				char ch = sc.next().toUpperCase().charAt(0);
				
				if(ch == 'Y') {
					// 서비스 호출 후 결과 반환 받기
					int result = service.userDelete(loginUser.getUserName());
					if(result > 0) {
						System.out.println("\n[탈퇴 되었습니다...]\n");
						
						input = 0; // 메인 메뉴로 이동 
						MainView.loginUser = null; // 로그아웃
						
					} else {
						System.out.println("\n[비밀번호가 일치하지 않습니다.]\n");
					}
					
					break; // while문 종료
					
				} else if(ch == 'N' ) {
					
					System.out.println("\n[취소되었습니다.]\n");
					break;
					
				} else {
					System.out.println("\n[Y 또는 N만 입력해주세요.]\n");
				}
				
			}
			
			
		} catch (Exception e) {
			System.out.println("\n[회원 탈퇴 중 예외 발생]\n");
			e.printStackTrace();
		}
	
	}
		
		
		
		
	
	
	
	
	
	
	





	
	
	
}
