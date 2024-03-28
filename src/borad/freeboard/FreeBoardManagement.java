package borad.freeboard;

import borad.freeboard.general.GeneralFreeBoardDAO;
import borad.freeboard.manager.ManagerFreeBoardDAO;
import member.Member;
import member.MemberDAO;

import java.util.List;
import java.util.Scanner;

public class FreeBoardManagement {
    private boolean run = true;
    private Scanner scanner = null;
    private GeneralFreeBoardDAO generalFreeBoardDAO = null;
    private ManagerFreeBoardDAO managerFreeBoardDAO = null;

    public FreeBoardManagement() {
        scanner = new Scanner(System.in);
        generalFreeBoardDAO = GeneralFreeBoardDAO.getInstance();
        managerFreeBoardDAO = ManagerFreeBoardDAO.getInstance();
    }

    public void run(Member member) {
        while (run) {
            if (member.getUserRole().equals("general")) {
                generalFreeBoardActiveMenu();
                int menu = selectMenu();

                switch (menu) {
                    case 1:
                        insertPost(member);
                        break;
                    case 2:
                        selectPostAll();
                        break;
                    case 3:
                        selectPost(member);
                        break;
                    case 4:
                        updatePost(member);
                        break;
                    case 5:
                        deletePost(member);
                        break;
                    case 6:
                        run = false;
                        break;
                    default:
                        showError();
                }
            } else {
                managerFreeBoardActiveMenu();
                int menu = selectMenu();

                switch (menu) {
                    case 1:
                        selectPostAll();
                        break;
                    case 2:
                        mangerSelectPost();
                        break;
                    case 3:
                        managerDeletePost();
                        break;
                    case 4:
                        run = false;
                        break;
                    default:
                        showError();
                }
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

    private void generalFreeBoardActiveMenu() {
        System.out.println("=======================================================");
        System.out.println("1.등록 | 2.전체조회 | 3.내 개시글 조회 | 4.수정 | 5.삭제 | 6.이전");
        System.out.println("=======================================================");
    }

    private void managerFreeBoardActiveMenu() {
        System.out.println("==================================================");
        System.out.println("1.전체조회 | 2.특정 게시글 조회 | 3.특정 게시글 삭제 | 4.이전");
        System.out.println("==================================================");
    }

    private void insertPost(Member member) {
        FreeBoard freeBoard = new FreeBoard();
        freeBoard.setUserId(member.getUserId());
        freeBoard.setWriter(member.getName());

        System.out.print("제목: ");
        freeBoard.setTitle(scanner.nextLine());

        System.out.print("내용: ");
        freeBoard.setContent(scanner.nextLine());

        int result = generalFreeBoardDAO.insertFreeBoard(freeBoard);
        isCompletion(result);
    }

    private void isCompletion(int result) {
        if (result > 0) {
            System.out.println("정상적으로 처리되었습니다");
        } else {
            System.out.println("정보를 확인해주세요.");
        }
    }

    private void selectPostAll() {
        List<FreeBoard> freeBoardList = generalFreeBoardDAO.selectFreeBoardAll();

        if (freeBoardList.isEmpty()) {
            System.out.println("게시글이 없습니다.");
        } else {
            for (FreeBoard freeBoard : freeBoardList) {
                System.out.println(freeBoard);
            }
        }
    }

    private void selectPost(Member member) {
        List<FreeBoard> freeBoardList = generalFreeBoardDAO.selectFreeBoardOne(member);

        if (freeBoardList.isEmpty()) {
            System.out.println("게시글이 없습니다.");
        } else {
            for (FreeBoard freeBoard : generalFreeBoardDAO.selectFreeBoardOne(member)) {
                System.out.println(freeBoard);
            }
        }
    }

    private void mangerSelectPost() {
        List<FreeBoard> freeBoardList = null;

        System.out.print("조회할 글쓴이: ");
        String writer = scanner.nextLine();

        freeBoardList = managerFreeBoardDAO.selectFreeBoard(writer);

        for (FreeBoard freeBoard : freeBoardList) {
            System.out.println(freeBoard);
        }
    }

    private void updatePost(Member member) {
        System.out.print("수정할 게시글 제목: ");
        String title = scanner.nextLine();

        System.out.print("수정할 내용: ");
        String modifyContent = scanner.nextLine();

        int result = generalFreeBoardDAO.updateFreeBoard(member, title, modifyContent);
        isCompletion(result);
    }

    private void deletePost(Member member) {
        System.out.print("삭제할 게시글 제목: ");
        String title = scanner.nextLine();

        int result = generalFreeBoardDAO.deleteFreeBoard(member, title);
        isCompletion(result);
    }

    private void managerDeletePost() {
        System.out.print("작성자: ");
        String writer = scanner.nextLine();

        System.out.print("삭제할 제목: ");
        String title = scanner.nextLine();

        int result = managerFreeBoardDAO.managerDeleteFreeBoard(writer, title );
        isCompletion(result);
    }

    private void end() {
        run = false;
    }

    private void showError() {
        System.out.println("메뉴에서 선택해주세요.");
    }
}
