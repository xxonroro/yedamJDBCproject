package borad.anonymousboard.general;

import borad.anonymousboard.AnonyMousBoard;
import common.DAO;
import member.Member;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GeneralAnonyMousBoardDAO extends DAO {
    private static GeneralAnonyMousBoardDAO generalAnonyMousBoardDAO = null;
    private List<AnonyMousBoard> anonyMousBoardList = null;

    private GeneralAnonyMousBoardDAO() {}

    public static GeneralAnonyMousBoardDAO getInstance() {
        if (generalAnonyMousBoardDAO == null) {
            generalAnonyMousBoardDAO = new GeneralAnonyMousBoardDAO();
        }
        return generalAnonyMousBoardDAO;
    }

    // 등록
    public int insertAnonyMousBoard(Member member, String title, String content) {
        int result = 0;

        try {
            connect();

            String sql = "INSERT INTO anonymous_board VALUES (?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, member.getUserId());
            preparedStatement.setString(2, title);
            preparedStatement.setString(3, content);

            result = preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        } finally {
            disconnect();
        }
        return result;
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

    // 단건 조회
    public List<AnonyMousBoard> selectAnonymousBoard(Member member) {
        anonyMousBoardList = new ArrayList<>();

        try {
            connect();

            String sql = "SELECT * FROM anonymous_board WHERE user_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, member.getUserId());

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

    // 수정
    public int updateAnonymousBoard(Member member, String title, String modifyContent) {
        int result = 0;

        try {
            connect();

            String sql = "UPDATE anonymous_board SET content = ? WHERE user_id = ? AND title = ?";
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
    public int deleteAnonymousBoard(Member member, String title) {
        int result = 0;

        try {
            connect();

            String sql = "DELETE FROM anonymous_board WHERE user_id = ? AND title = ?";
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
