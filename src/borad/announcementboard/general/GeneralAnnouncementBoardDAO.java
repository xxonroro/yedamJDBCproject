package borad.announcementboard.general;

import borad.announcementboard.AnnouncementBoard;
import common.DAO;
import member.Member;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GeneralAnnouncementBoardDAO extends DAO {
    private static GeneralAnnouncementBoardDAO generalAnnouncementBoardDAO = null;
    private List<AnnouncementBoard> announcementBoardList = null;

    private GeneralAnnouncementBoardDAO() {}

    public static GeneralAnnouncementBoardDAO getInstance() {
        if (generalAnnouncementBoardDAO == null) {
            generalAnnouncementBoardDAO = new GeneralAnnouncementBoardDAO();
        }
        return generalAnnouncementBoardDAO;
    }

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

    public List<AnnouncementBoard> selectAnnouncementBoard(String writer) {
        announcementBoardList = new ArrayList<>();

        try {
            connect();

            String sql = "SELECT * FROM announcement_board WHERE writer = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, writer);

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

}
