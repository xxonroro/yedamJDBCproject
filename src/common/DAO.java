package common;

import java.sql.*;

// 데이터베이스 연결을 관리하는 클래스
public class DAO {

    protected final String JDBC_DRIVER_URL = "com.mysql.cj.jdbc.Driver";
    protected final String MYSQL_URL = "jdbc:mysql://localhost:3306/yedam";
    protected final String MYSQL_USERNAME = "root";
    protected final String MYSQL_USERPASS = "smjsmk8841!";

    protected Connection connection = null;
    protected PreparedStatement preparedStatement = null;
    protected Statement statement = null;
    protected ResultSet resultSet = null;

    protected DAO() {}

    protected void connect() {
        try {
            Class.forName(JDBC_DRIVER_URL);
            connection = DriverManager.getConnection(MYSQL_URL, MYSQL_USERNAME, MYSQL_USERPASS);

        } catch (ClassNotFoundException classNotFoundException) {
            System.out.println("JDBC Driver Loading Fail");
        } catch (SQLException sqlException) {
            System.out.println("DB Connect Fail");
            System.out.println(sqlException.getMessage());
        }
    }

    protected void disconnect() {
        try {
            // connection 참조변수가 (유효한) Connection 객체를 참조하고 있고, 데이터베이스 연결이 아직 활성화 상태라면, 데이터베이스와의 연결을 종료한다.
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException sqlException) {
            sqlException.getMessage();
        }
    }


}
