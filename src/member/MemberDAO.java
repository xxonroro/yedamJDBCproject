package member;

import common.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO extends DAO {

    private static MemberDAO memberDAO = null;

    private MemberDAO() {}

    public static MemberDAO getInstance() {
        if (memberDAO == null) {
            memberDAO = new MemberDAO();
        }
        return memberDAO;
    }

    // 전체 조회
    public List<Member> selectMemberAll() {
        List<Member> memberList = new ArrayList<>();

        try {
            connect();

            String sql = "SELECT * FROM member ORDER BY name";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Member member = new Member();

                member.setName(resultSet.getString("name"));
                member.setUserId(resultSet.getString("user_id"));
                member.setUserPass(resultSet.getString("user_pass"));
                member.setUserRole(resultSet.getString("user_role"));

                memberList.add(member);
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        } finally {
            disconnect();
        }
        return memberList;
    }

    // 단건 조회
    public Member loginMember(Member member) {
        Member resultMember = null;

        try {
            connect();

            String sql = "SELECT * FROM member WHERE user_id = ? AND user_pass = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, member.getUserId());
            preparedStatement.setString(2, member.getUserPass());
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                resultMember = new Member();

                resultMember.setName(resultSet.getString("name"));
                resultMember.setUserId(resultSet.getString("user_id"));
                resultMember.setUserPass(resultSet.getString("user_pass"));
                resultMember.setUserRole(resultSet.getString("user_role"));
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        } finally {
            disconnect();
        }
        return resultMember;
    }

    // 회원 등록
    public int insertMemberInfo(Member member) {
        int result = 0;

        try {
            connect();

            String sql = "INSERT INTO member VALUES (?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, member.getName());
            preparedStatement.setString(2, member.getUserId());
            preparedStatement.setString(3, member.getUserPass());
            preparedStatement.setString(4, member.getUserRole());

            result = preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            System.out.println("이미 등록된 회원이거나 잘못된 역할을 입력하셨습니다.");
        } finally {
            disconnect();
        }
        return result;
    }

    // 회원 삭제
    public int deleteMemberInfo(Member member) {
        int result = 0;

        try {
            connect();

            String sql = "DELETE FROM member WHERE user_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, member.getUserId());
            result = preparedStatement.executeUpdate();

        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        } finally {
            disconnect();
        }
        return result;
    }

    // 아이디 찾기
    public String memberFindId(String name, String userPass) {
        String id = null;

        try {
            connect();

            String sql = "SELECT * FROM member WHERE name = ? AND user_pass = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, userPass);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                id = resultSet.getString("user_id");
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        } finally {
            disconnect();
        }
        return id;
    }

    // 비밀번호 찾기
    public String memberFindPass(String name, String userId) {
        String pass = null;

        try {
            connect();

            String sql = "SELECT * FROM member WHERE name = ? AND user_id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, userId);

            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                pass = resultSet.getString("user_pass");
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        } finally {
            disconnect();
        }
        return pass;
    }
}

