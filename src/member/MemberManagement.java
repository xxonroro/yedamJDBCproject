package member;

import borad.BoardManagement;
import borad.freeboard.FreeBoardManagement;

import java.util.Scanner;

public class MemberManagement {
    private boolean run = true;
    private Scanner scanner = null;
    private MemberDAO memberDAO = null;

    public MemberManagement() {
        scanner = new Scanner(System.in);
        memberDAO = MemberDAO.getInstance();
    }

    public void run() {
        while (run) {
            loginMenuPrint();
            int menu = selectMenu();

            switch (menu) {
                case 1:
                    insertMember();
                    break;
                case 2:
                    loginMember();
                    break;
                case 3:
                    memberFindPass();
                    break;
                case 4:
                    end();
                    break;
                default:
                    showError();
            }
        }
    }

    private void loginMenuPrint() {
        System.out.println("========================================");
        System.out.println("1.회원가입 | 2.로그인 | 3.비밀번호 찾기 | 4.종료");
        System.out.println("========================================");
    }

    private int selectMenu() {
        System.out.print("메뉴: ");
        return inputNumber();
    }

    private int inputNumber() {
        int menu = 0;

        try {
            menu = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException numberFormatException) {
            System.out.println("숫자로 입력해주세요.");
        }
        return menu;
    }

    private void insertMember() {
        Member member = inputMemberAll();
        int result = memberDAO.insertMemberInfo(member);

        if (result > 0) {
            System.out.println("회원가입 완료 😁");
        } else {
            System.out.println("회원가입 실패 😭");
        }
    }

    private Member inputMemberAll() {
        Member member = new Member();

        System.out.print("이름: ");
        member.setName(scanner.nextLine());

        System.out.print("아이디: ");
        member.setUserId(scanner.nextLine());

        System.out.print("비번: ");
        member.setUserPass(scanner.nextLine());

        System.out.print("역할(general/manager): ");
        member.setUserRole(scanner.nextLine());

        return member;
    }

    private void loginMember() {
        Member member = new Member();

        System.out.print("아이디: ");
        member.setUserId(scanner.nextLine());

        System.out.print("비번: ");
        member.setUserPass(scanner.nextLine());

        Member loginMember = memberDAO.loginMember(member);

        if (loginMember == null) {
            System.out.println("등록된 회원이 아닙니다.");
        } else {
            new BoardManagement().run(loginMember);
        }
    }

    // 아이디 찾기
    private void memberFindId() {
        System.out.print("이름: ");
        String name = scanner.nextLine();

        System.out.print("비번: ");
        String userPass = scanner.nextLine();

        System.out.println(name + userPass);

        String id = memberDAO.memberFindPass(name, userPass);
        System.out.println(id);
        findCheck(id);
    }

    // 비밀번호 찾기
    private void memberFindPass() {
        System.out.print("이름: ");
        String name = scanner.nextLine();

        System.out.print("아이디: ");
        String userId = scanner.nextLine();

        String pass = memberDAO.memberFindPass(name, userId);
        findCheck(pass);
    }

    private void findCheck(String memberInfo) {
        if (memberInfo == null) {
            System.out.println("등록된 회원이 아닙니다.");
        } else {
            System.out.println(memberInfo + "입니다.");
        }
    }

    private void end() {
        run = false;
        scanner.close();
        System.out.println("프로그램을 종료합니다.");
    }

    private void showError() {
        System.out.println("메뉴에서 선택해주세요.");
    }
}
