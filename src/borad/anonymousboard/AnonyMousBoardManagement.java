package borad.anonymousboard;

import borad.anonymousboard.general.GeneralAnonyMousBoardDAO;
import borad.anonymousboard.manager.ManagerAnonyMousBoardDAO;
import member.Member;

import java.util.List;
import java.util.Scanner;

public class AnonyMousBoardManagement {
    private boolean run = true;
    private Scanner scanner = null;
    private GeneralAnonyMousBoardDAO generalAnonyMousBoardDAO = null;
    private ManagerAnonyMousBoardDAO managerAnonyMousBoardDAO = null;

    public AnonyMousBoardManagement() {
        scanner = new Scanner(System.in);
        generalAnonyMousBoardDAO = GeneralAnonyMousBoardDAO.getInstance();
        managerAnonyMousBoardDAO = ManagerAnonyMousBoardDAO.getInstance();
    }

    public void run(Member member) {
        while (run) {
            if (member.getUserRole().equals("general")) {
                generalAnonymousBoardMenu();
                int menu = selectMenu();

                switch (menu) {
                    case 1:
                        insertAnonymousBoard(member);
                        break;
                    case 2:
                        selectAnnoymousBoardAll();
                        break;
                    case 3:
                        selectAnnoymousBoard(member);
                        break;
                    case 4:
                        updateAnnoymousBoard(member);
                        break;
                    case 5:
                        deleteAnnoymousBoard(member);
                        break;
                    case 6:
                        end();
                        break;
                    default:
                        showError();
                }
            } else {
                managerAnonymousBoardMenu();
                int menu = selectMenu();

                switch (menu) {
                    case 1:
                        selectAnnoymousBoardAll();
                        break;
                    case 2:
                        managerSelectAnnoymousBorad();
                        break;
                    case 3:
                        managerDeleteAnnoymousBoard();
                        break;
                    case 4:
                        end();
                        break;
                    default:
                        showError();
                }
            }
        }
    }

    private void generalAnonymousBoardMenu() {
        System.out.println("=======================================================");
        System.out.println("1.등록 | 2.전체조회 | 3.내 게시물 조회 | 4.수정 | 5.삭제 | 6.이전");
        System.out.println("=======================================================");
    }

    private void managerAnonymousBoardMenu() {
        System.out.println("===============================================");
        System.out.println("1.전체조회 | 2.제목으로 조회 | 3.특정 게시물 삭제 | 4.이전");
        System.out.println("===============================================");

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

    private void insertAnonymousBoard(Member member) {
        System.out.print("제목: ");
        String title = scanner.nextLine();

        System.out.print("내용: ");
        String content = scanner.nextLine();

        int result = generalAnonyMousBoardDAO.insertAnonyMousBoard(member, title, content);
        isCompletion(result);
    }

    private void selectAnnoymousBoardAll() {
        List<AnonyMousBoard> anonyMousBoardList = generalAnonyMousBoardDAO.selectAnonymousBoardAll();

        if (anonyMousBoardList.isEmpty()) {
            System.out.println("게시글이 없습니다.");
        } else {
            for (AnonyMousBoard anonyMousBoard : anonyMousBoardList) {
                System.out.println(anonyMousBoard);
            }
        }
    }

    private void selectAnnoymousBoard(Member member) {
        List<AnonyMousBoard> anonyMousBoardList = generalAnonyMousBoardDAO.selectAnonymousBoard(member);

        if (anonyMousBoardList.isEmpty()) {
            System.out.println("게시글이 없습니다.");
        } else {
            for (AnonyMousBoard anonyMousBoard : anonyMousBoardList) {
                System.out.println(anonyMousBoard);
            }
        }
    }

    private void managerSelectAnnoymousBorad() {
        System.out.print("조회할 게시글의 제목: ");
        String title = scanner.nextLine();

        System.out.print("조회할 게시글 내용의 첫 글자: ");
        String contentFirst = scanner.nextLine();

        System.out.print("조회할 게시글 내용의 마지막 글자: ");
        String contentLast = scanner.nextLine();

        List<AnonyMousBoard> anonyMousBoardList = managerAnonyMousBoardDAO.selectAnonymousBoard(title, contentFirst, contentLast);

        System.out.println();

        if (anonyMousBoardList.isEmpty()) {
            System.out.println("게시글이 없습니다.");
        } else {
            for (AnonyMousBoard anonyMousBoard : anonyMousBoardList) {
                System.out.println(anonyMousBoard);
            }
        }
    }

    private void updateAnnoymousBoard(Member member) {
        System.out.print("수정할 게시글 제목: ");
        String title = scanner.nextLine();

        System.out.print("수정할 내용: ");
        String modifyContent = scanner.nextLine();

        int result = generalAnonyMousBoardDAO.updateAnonymousBoard(member, title, modifyContent);
        isCompletion(result);
    }

    private void deleteAnnoymousBoard(Member member) {
        System.out.print("삭제할 게시글 제목: ");
        String title = scanner.nextLine();

        int result = generalAnonyMousBoardDAO.deleteAnonymousBoard(member, title);
        isCompletion(result);
    }

    private void managerDeleteAnnoymousBoard() {
        System.out.print("삭제할 게시글의 제목: ");
        String title = scanner.nextLine();

        System.out.print("삭제할 게시글 내용의 첫 글자: ");
        String contentFirst = scanner.nextLine();

        System.out.print("삭제할 게시글 내용의 마지막 글자: ");
        String contentLast = scanner.nextLine();

        int result = managerAnonyMousBoardDAO.deleteAnonymousBoard(title, contentFirst, contentLast);
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
        System.out.println("올바른 메뉴를 입력하세요.");
    }
}