package borad;

import borad.announcementboard.AnnouncementBoardManagement;
import borad.anonymousboard.AnonyMousBoardManagement;
import borad.freeboard.FreeBoardManagement;
import borad.freeboard.general.GeneralFreeBoardDAO;
import borad.freeboard.manager.ManagerFreeBoardDAO;
import member.Member;
import member.MemberDAO;

import java.util.Scanner;

public class BoardManagement {
    private boolean run = true;
    private Scanner scanner = null;
    private GeneralFreeBoardDAO generalFreeBoardDAO = null;
    private ManagerFreeBoardDAO managerFreeBoardDAO = null;

    public BoardManagement() {
        scanner = new Scanner(System.in);
        generalFreeBoardDAO = GeneralFreeBoardDAO.getInstance();
        managerFreeBoardDAO = ManagerFreeBoardDAO.getInstance();
    }

    public void run(Member member) {
        while (run) {
            System.out.println(member.getName() + "님 환영합니다.");
            menuPrint();
            int menu = selectMenu();

            switch (menu) {
                case 1:
                    new FreeBoardManagement().run(member);
                    break;
                case 2:
                    new AnonyMousBoardManagement().run(member);
                    break;
                case 3:
                    new AnnouncementBoardManagement().run(member);
                    break;
                case 4:
                    end(member);
                    break;
                case 5:
                    deleteMember(member);
                    break;
                default:
                    showError();
            }
        }
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

    private void menuPrint() {
        System.out.println("=======================================================");
        System.out.println("1.자유게시판 | 2.익명게시판 | 3.공지게시판 | 4.로그아웃 | 5.회원탈퇴 ");
        System.out.println("=======================================================");
    }

    private void end(Member member) {
        run = false;
        System.out.println(member.getName() + "님 안녕히 가세요.😀");
    }

    private void deleteMember(Member member) {
        MemberDAO memberDAO = MemberDAO.getInstance();

        System.out.print("정말로 회원을 탈퇴하겠습니까 🤔 (Y/N): ");
        String question = scanner.nextLine();

        if (question.equalsIgnoreCase("Y")) {
            memberDAO.deleteMemberInfo(member);
            run = false;
        }
    }

    private void showError() {
        System.out.println("메뉴에서 선택해주세요.");
    }

}
