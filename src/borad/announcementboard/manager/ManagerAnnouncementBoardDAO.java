package borad.announcementboard.manager;

import borad.announcementboard.AnnouncementBoard;
import common.DAO;
import member.Member;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManagerAnnouncementBoardDAO extends DAO {
    private static ManagerAnnouncementBoardDAO managerAnnouncementBoardDAO = null;
    private List<AnnouncementBoard> announcementBoardList = null;

    private ManagerAnnouncementBoardDAO() {}

    public static ManagerAnnouncementBoardDAO getInstance() {
        if (managerAnnouncementBoardDAO == null) {
            managerAnnouncementBoardDAO = new ManagerAnnouncementBoardDAO();
        }
        return managerAnnouncementBoardDAO;
    }

    // 등록
    public int insertAnnouncementBoard(AnnouncementBoard announcementBoard) {
        int result = 0;

        try {
            connect();

            String sql = "INSERT INTO announcement_board VALUES (?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, announcementBoard.getUserId());
            preparedStatement.setString(2, announcementBoard.getWriter());
            preparedStatement.setString(3, announcementBoard.getTitle());
            preparedStatement.setString(4, announcementBoard.getContent());

            result = preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        } finally {
            disconnect();
        }
        return result;
    }

    // 전체조회
    public List<AnnouncementBoard> selectAnnouncementBoardAll() {
        announcementBoardList = new ArrayList<>();

        try {
            connect();

            String sql = "SELECT * FROM announcement_board";
            preparedStatement = connection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                AnnouncementBoard announcementBoard = new AnnouncementBoard();

                announcementBoard.setUserId(resultSet.getString("user_id"));
                announcementBoard.setWriter(resultSet.getString("writer"));
                announcementBoard.setTitle(resultSet.getString("title"));
                announcementBoard.setContent(resultSet.getString("content"));

                announcementBoardList.add(announcementBoard);
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        } finally {
            disconnect();
        }
        return announcementBoardList;
    }

    // 단권 조회
    public List<AnnouncementBoard> selectAnnouncementBoard(Member member) {
        announcementBoardList = new ArrayList<>();

        try {
            connect();

            String sql = "SELECT * FROM announcement_board WHERE user_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, member.getUserId());

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                AnnouncementBoard announcementBoard = new AnnouncementBoard();

                announcementBoard.setUserId(resultSet.getString("user_id"));
                announcementBoard.setWriter(resultSet.getString("writer"));
                announcementBoard.setTitle(resultSet.getString("title"));
                announcementBoard.setContent(resultSet.getString("content"));

                announcementBoardList.add(announcementBoard);
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        } finally {
            disconnect();
        }
        return announcementBoardList;
    }

    // 수정
    public int updateAnnouncementBoard(Member member, String title, String modifyContent) {
        int result = 0;

        try {
            connect();

            String sql = "UPDATE announcement_board SET content = ? WHERE user_id = ? AND title = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, modifyContent);
            preparedStatement.setString(2, member.getUserId());
            preparedStatement.setString(3, title);

            result = preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        } finally {
            disconnect();
        }
        return result;
    }

    // 삭제
    public int deleteAnnouncementBoard(Member member, String title) {
        int result = 0;

        try {
            connect();

            String sql = "DELETE FROM announcement_board WHERE user_id = ? AND title = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, member.getUserId());
            preparedStatement.setString(2, title);

            result = preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        } finally {
            disconnect();
        }
        return result;
    }
}
