package borad.anonymousboard.manager;

import borad.anonymousboard.AnonyMousBoard;
import common.DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManagerAnonyMousBoardDAO extends DAO {
    private static ManagerAnonyMousBoardDAO managerAnonyMousBoardDAO = null;
    private List<AnonyMousBoard> anonyMousBoardList = null;

    private ManagerAnonyMousBoardDAO() {}

    public static ManagerAnonyMousBoardDAO getInstance() {
        if (managerAnonyMousBoardDAO == null) {
            managerAnonyMousBoardDAO = new ManagerAnonyMousBoardDAO();
        }
        return managerAnonyMousBoardDAO;
    }

    // 전체 조회
    public List<AnonyMousBoard> selectAnonymousBoardAll() {
        anonyMousBoardList = new ArrayList<>();

        try {
            connect();

            String sql = "SELECT * FROM anonymous_board";
            preparedStatement = connection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                AnonyMousBoard anonyMousBoard = new AnonyMousBoard();

                anonyMousBoard.setUserId(resultSet.getString("user_id"));
                anonyMousBoard.setTitle(resultSet.getString("title"));
                anonyMousBoard.setContent(resultSet.getString("content"));

                anonyMousBoardList.add(anonyMousBoard);
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        } finally {
            disconnect();
        }
        return anonyMousBoardList;
    }

    // 단건조회(제목)
    public List<AnonyMousBoard> selectAnonymousBoard(String title, String contentOne, String contentTwo) {
        anonyMousBoardList = new ArrayList<>();

        try {
            connect();

            String sql = "SELECT * FROM anonymous_board WHERE title = ? AND content LIKE ? AND content LIKE ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, contentOne + "%");
            preparedStatement.setString(3, "%" + contentTwo);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                AnonyMousBoard anonyMousBoard = new AnonyMousBoard();

                anonyMousBoard.setUserId(resultSet.getString("user_id"));
                anonyMousBoard.setTitle(resultSet.getString("title"));
                anonyMousBoard.setContent(resultSet.getString("content"));

                anonyMousBoardList.add(anonyMousBoard);
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        } finally {
            disconnect();
        }
        return anonyMousBoardList;
    }

    //삭제
    public int deleteAnonymousBoard(String title, String contentOne, String contentTwo) {
        int result = 0;

        try {
            connect();

            String sql = "DELETE FROM anonymous_board WHERE title = ? AND content LIKE ? AND content LIKE ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, contentOne + "%");
            preparedStatement.setString(3, "%" + contentTwo);

            result = preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        } finally {
            disconnect();
        }
        return result;
    }


}
