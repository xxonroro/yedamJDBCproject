package borad.announcementboard;

import borad.announcementboard.general.GeneralAnnouncementBoardDAO;
import borad.announcementboard.manager.ManagerAnnouncementBoardDAO;
import member.Member;

import java.util.List;
import java.util.Scanner;

public class AnnouncementBoardManagement {
    private boolean run = true;
    Scanner scanner = null;
    ManagerAnnouncementBoardDAO managerAnnouncementBoardDAO = null;
    GeneralAnnouncementBoardDAO generalAnnouncementBoardDAO = null;

    public AnnouncementBoardManagement() {
        scanner = new Scanner(System.in);
        generalAnnouncementBoardDAO = GeneralAnnouncementBoardDAO.getInstance();
        managerAnnouncementBoardDAO = ManagerAnnouncementBoardDAO.getInstance();
    }

    public void run(Member member) {
        while (run) {
            if (member.getUserRole().equals("general")) {
                generalAnnouncementBoardActiveMenu();
                int menu = selectMenu();

                switch (menu) {
                    case 1:
                        selectPostAll();
                        break;
                    case 2:
                        end();
                        break;
                    default:
                        showError();
                }
            } else {
                managerAnnouncementBoardActiveMenu();
                int menu = selectMenu();

                switch (menu) {
                    case 1:
                        insertAnnouncementBoard(member);
                        break;
                    case 2:
                        selectPostAll();
                        break;
                    case 3:
                        selectPost(member);
                        break;
                    case 4:
                        updateAnnouncementBoard(member);
                        break;
                    case 5:
                        deleteAnnouncementBoard(member);
                        break;
                    case 6:
                        end();
                        break;
                    default:
                        showError();
                }
            }
        }
    }

    private void generalAnnouncementBoardActiveMenu() {
        System.out.println("================");
        System.out.println("1.전체조회 | 2.이전");
        System.out.println("================");
    }

    private void managerAnnouncementBoardActiveMenu() {
        System.out.println("=======================================================");
        System.out.println("1.등록 | 2.전체조회 | 3.내 공지글 조회 | 4.수정 | 5.삭제 | 6.이전");
        System.out.println("=======================================================");
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

    private void selectPostAll() {
        List<AnnouncementBoard> announcementBoardList = generalAnnouncementBoardDAO.selectAnnouncementBoardAll();

        if (announcementBoardList.isEmpty()) {
            System.out.println("게시글이 없습니다");
        } else {
            for (AnnouncementBoard announcementBoard : announcementBoardList) {
                System.out.println(announcementBoard);
            }
        }
    }

    private void selectPost(Member member) {
        List<AnnouncementBoard> announcementBoardList = managerAnnouncementBoardDAO.selectAnnouncementBoard(member);

        if (announcementBoardList.isEmpty()) {
            System.out.println("게시글이 없습니다.");
        } else {
            for (AnnouncementBoard announcementBoard : announcementBoardList) {
                System.out.println(announcementBoard);
            }
        }
    }

    private void insertAnnouncementBoard(Member member) {
        AnnouncementBoard announcementBoard = new AnnouncementBoard();
        announcementBoard.setUserId(member.getUserId());
        announcementBoard.setWriter(member.getName());

        System.out.print("제목: ");
        announcementBoard.setTitle(scanner.nextLine());

        System.out.print("내용: ");
        announcementBoard.setContent(scanner.nextLine());

        int result = managerAnnouncementBoardDAO.insertAnnouncementBoard(announcementBoard);
        isCompletion(result);
    }

    private void updateAnnouncementBoard(Member member) {
        System.out.print("수정할 게시글 제목: ");
        String title = scanner.nextLine();

        System.out.print("수정할 내용: ");
        String modifyContent = scanner.nextLine();

        int result = managerAnnouncementBoardDAO.updateAnnouncementBoard(member, title, modifyContent);
        isCompletion(result);
    }

    private void deleteAnnouncementBoard(Member member) {
        System.out.print("삭제할 게시글 제목: ");
        String title = scanner.nextLine();

        int result = managerAnnouncementBoardDAO.deleteAnnouncementBoard(member, title);
        isCompletion(result);
    }

    private void isCompletion(int result) {
        if (result > 0) {
            System.out.println("정상적으로 처리되었습니다");
        } else {
            System.out.println("정보를 확인해주세요.");
        }
    }

    private void end() {
        run = false;
    }

    private void showError() {
        System.out.println("메뉴에서 선택해주세요.");
    }
}
