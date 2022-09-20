package edu.kh.jdbc.main.view;

import java.util.InputMismatchException;
import java.util.Scanner;

import edu.kh.jdbc.main.model.service.MainService;
import edu.kh.jdbc.member.view.MemberView;
import edu.kh.jdbc.model.vo.Member;

// 메인화면 
/**
 * @author rbh
 *
 */
public class MainView {

	private Scanner sc = new Scanner(System.in);
	
	private MainService service = new MainService();
	
	// 로그인된 회원정보를 저장한 객체를 참조하는 참조변수
//	private Member loginMember = null;
	
	public static Member loginMember = null; // 다른곳에서 로그아웃으로 활용하기 위해 public으로 변경
	//-> 로그인 X == null
	//   로그인 O != null
	
	// 회원 기능 메뉴 객체 생성 
	private MemberView memberView = new MemberView();
	
	
	
	//-----------------------------------------------------------------------------
	/**
	 *  메인 메뉴 출력 메서드 
	 */
	public void mainMenu() {

		int input = -1;

		do {
				
			try {
				
				// Tip ctrl + shift + p = 시작, 종료 괄호 이동
				//     ctrl + shift + F = 들여쓰기 , 문장 정렬
				
				// 로그인 X 화면 =======================================================
				if(loginMember == null) {
				System.out.println("\n***[회원제 게시판 프로그램]***\n");
				System.out.println("1. 로그인");
				System.out.println("2. 회원가입");
				System.out.println("0. 프로그램 종료");

				System.out.print("\n메뉴 선택 : ");

				input = sc.nextInt();
				sc.nextLine(); // 입력 버퍼 개행문자 제거
				System.out.println();
				
					switch(input) {
						case 1 : login(); break;     // 로그인
						case 2 : signUp(); break;    // 회원 가입
						case 0 : System.out.println("프로그램 종료"); break;
						default : System.out.println("메뉴에 작성된 번호만 입력해주세요.");
					}
				} else {  // 로그인 O
					System.out.println("*****[로그인 메뉴]*****");
					System.out.println("1. 회원 기능");
					System.out.println("2. 게시판 기능");
					System.out.println("0. 로그아웃");
					System.out.println("99. 프로그램 종료");
					
					System.out.print("\n메뉴선택 : ");
					input = sc.nextInt();
					
					switch (input) {
						case 1: memberView.memberMenu(loginMember); break;
						case 2: break;
						case 0:  // 로그아웃 == loginMember가 참조하는 객체 없음( == null)
							     // 로그인 == loginMember가 참조하는 객체 존재  
							loginMember = null;
							System.out.println("\n[로그아웃 되었습니다.]\n");
							input = -1; // do-while문이 종료되지 않도록 0이 아닌 값으로 변경 
							break;
						case 99: 
							System.out.println("[프로그램 종료]"); 
							input = 0; // -> do-while 조건식을 false로 만듦
//							System.exit(0); // JVM을 종료, 매개변수 
							break;
						default: System.out.println("메뉴에 작성된 번호만 입력해주세요."); break;
					}
							
					
					
				}
				// =======================================================================
				
			} catch (InputMismatchException e) {
				e.printStackTrace();
				System.out.println("\n[입력 형식이 올바르지 않습니다.]");
				sc.nextLine(); // 입력 버퍼에 남아있는 잘못된 문자열 제거

			}

		} while (input != 0);
	}



	//-----------------------------------------------------------------------------

	/**
	 *  회원 가입 화면
	 */
	private void signUp() {
		System.out.println("[회원 가입]");

		String memberId = null;

		String memberPw1 = null;
		String memberPw2 = null;

		String memberName = null;
		String memberGender = null;

		// ID를 입력받아 중복이 아닐 때까지 반복

		try {

			while (true) {

				System.out.print("아이디 입력 : ");
				memberId = sc.next();

				// 입력받은 아이디를 매개변수로 전달하여
				// 중복 여부를 검사하는 서비스 호충 후 결과(1/0) 반환받기

				int result = service.idDupCheck(memberId);

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
			// 비밀번호 / 비밀번호 확인이 일치 할 때 까지 무한반복
			while(true) {
				
				System.out.print("비밀번호 : ");
				memberPw1 =sc.next();
				
				System.out.print("비밀번호 확인 : ");
				memberPw2 =sc.next();
				
				System.out.println();
				if(memberPw1.equals(memberPw2)) { // 일치하는 경우
					System.out.println("[일치합니다.]");
					break;
				} else { // 일치하지 않는 경우
					System.out.println("[입력받은 비밀번호가 일치하지 않습니다. 다시 입력해주세요.]");
				}
				System.out.println();
				
			}
			
			// 이름 입력
			System.out.print("이름 입력 : ");
			memberName = sc.next();
			
			// 성별 
			// M 또는 F가 입력 될 때까지 무한반복

			while(true) {
				System.out.print("성별 입력(M/F) : ");
				memberGender = sc.next().toUpperCase(); // 입력 받자마자 대문자로 변경
				
				if(memberGender.equals("M") || memberGender.equals("F")) {  
					break;
				} else {
					System.out.println("[M 또는 F만 입력해주세요]");
				}
				System.out.println();
			}
			
			// ---- 아이디, 비밀번호, 이름, 성별, 입력 완료 ----
			// -> 하나의 VO에 담아서 서비스 호출 후 결과 반환 받기
			Member member = new Member(memberId, memberPw2, memberName, memberGender);
			
			int result = service.signUp(member);

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
	//-----------------------------------------------------------------------------
	// 로그인 화면 ================================================================
	
	/**  
	 * 로그인 화면 
	 */
	private void login() {
		System.out.println("[로그인]");

		System.out.println("아이디 : ");
		String memberId = sc.next();

		System.out.println("비밀번호 : ");
		String memberPw = sc.next();

		try {

			// 로그인 서비스 호출 후 조회 결과를 loginMember에 저장
			loginMember = service.login(memberId, memberPw);

			System.out.println();
			if (loginMember != null) { // 로그인 성공 시
				System.out.println(loginMember.getMemberName() + "님 환영합니다.");
			} else { // 로그인 실패 시
				System.out.println("[아이디 또는 비밀번호가 일치하지 않습니다.]");
			}
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("\n[로그인 중 예외 발생]\n");
		}
	}
	
	
	
}