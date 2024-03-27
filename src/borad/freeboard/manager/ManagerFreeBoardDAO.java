package borad.freeboard.manager;

import common.DAO;
import borad.freeboard.FreeBoard;
import member.Member;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManagerFreeBoardDAO extends DAO {
    private static ManagerFreeBoardDAO generalFreeBoardDAO = null;
    private List<FreeBoard> freeBoardList = null;

    private ManagerFreeBoardDAO() {}

    public static ManagerFreeBoardDAO getInstance() {
        if (generalFreeBoardDAO == null) {
            generalFreeBoardDAO = new ManagerFreeBoardDAO();
        }
        return generalFreeBoardDAO;
    }

    public int insertFreeBoard(FreeBoard freeBoard) {
        int result = 0;

        try {
            connect();

            String sql = "INSERT INTO free_board VALUES (?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, freeBoard.getUserId());
            preparedStatement.setString(2, freeBoard.getWriter());
            preparedStatement.setString(3, freeBoard.getTitle());
            preparedStatement.setString(4, freeBoard.getContent());

            result = preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        } finally {
            disconnect();
        }
        return result;
    }

    // 자유 게시판의 게시글 전체 조회
    public List<FreeBoard> selectFreeBoardAll() {
        List<FreeBoard> freeBoardList = new ArrayList<>();

        try {
            connect();

            String sql = "SELECT * FROM free_board ORDER BY writer ASC";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                FreeBoard freeBoard = new FreeBoard();

                freeBoard.setUserId(resultSet.getString("user_id"));
                freeBoard.setWriter(resultSet.getString("writer"));
                freeBoard.setTitle(resultSet.getString("title"));
                freeBoard.setContent(resultSet.getString("content"));

                freeBoardList.add(freeBoard);
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        } finally {
            disconnect();
        }
        return freeBoardList;
    }

    // 자유 게시판의 게시글 조회
    public List<FreeBoard> selectFreeBoard(String writer) {
        freeBoardList = new ArrayList<>();

        try {
            connect();

            String sql = "SELECT * FROM free_board WHERE writer = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, writer);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                FreeBoard freeBoard = new FreeBoard();

                freeBoard.setUserId(resultSet.getString("user_id"));
                freeBoard.setWriter(resultSet.getString("writer"));
                freeBoard.setTitle(resultSet.getString("title"));
                freeBoard.setContent(resultSet.getString("content"));

                freeBoardList.add(freeBoard);
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        } finally {
            disconnect();
        }
        return freeBoardList;
    }

    // 게시글 수정
    public int updateFreeBoard(Member member, String title, String modifyContent) {
        int result = 0;

        try {
            connect();

            String sql = "UPDATE free_board SET content = ? WHERE user_id = ? AND title = ?";
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

    // 게시글 삭제
    public int deleteFreeBoard(Member member, String title) {
        int result = 0;

        try {
            connect();

            String sql = "DELETE FROM free_board WHERE user_id = ? AND title = ?";
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

    // 자유 게시판의 게시글 삭제
    public int managerDeleteFreeBoard(Member member, String title) {
        int result = 0;

        try {
            connect();

            String sql = "DELETE FROM free_board WHERE user_id = ? AND title = ?";
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
