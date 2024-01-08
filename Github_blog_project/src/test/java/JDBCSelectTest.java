import com.blog.nari_moon.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCSelectTest {
  public static void main(String[] args) throws SQLException {
    // JDBC 연결 정보
    String url = "jdbc:mysql://localhost:3306/blog_database?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeNehavior=convertToNull";

    // JDBC 드라이버 로드
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
    } catch (ClassNotFoundException e) {
      System.err.println("JDBC 드라이버를 찾을 수 없습니다.");
      e.printStackTrace();
      return;
    }

    // 데이터베이스 연결
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    List<User> users = new ArrayList<>();
    try {
      connection = DriverManager.getConnection(url, "Libong", "kyj1028");
      System.out.println("데이터베이스에 성공적으로 연결되었습니다.");

      // 여기에서 데이터베이스 관련 작업을 수행하면 됩니다.

      // SELECT 쿼리 작성
      String selectQuery = "SELECT * FROM `user` WHERE email = ?";
      preparedStatement = connection.prepareStatement(selectQuery);

      preparedStatement.setString(1, "code11298@naver.com");

      resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        // 결과 처리
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        String position = resultSet.getString("position");
        String username = resultSet.getString("username");
        String nickname = resultSet.getString("nickname");

        User user = new User(email, password, position, username, nickname);
        users.add(user);
      }

      System.out.println("결과 : " + users);
    } catch (SQLException e) {
      System.err.println("데이터베이스에 연결하는 중 오류가 발생했습니다.");
      e.printStackTrace();
    } finally {
      // PreparedStatement 닫기
      if (preparedStatement != null && !preparedStatement.isClosed()) {
        try {
          preparedStatement.close();
          System.out.println("PreparedStatement를 닫았습니다.");
        } catch (SQLException e) {
          System.err.println("PreparedStatement를 닫는 중 오류가 발생했습니다.");
          e.printStackTrace();
        }
      }
      // 연결 종료
      if (connection != null && !connection.isClosed()) {
        try {
          connection.close();
          System.out.println("데이터베이스 연결이 성공적으로 종료되었습니다.");
        } catch (SQLException e) {
          System.err.println("데이터베이스 연결을 종료하는 중 오류가 발생했습니다.");
          e.printStackTrace();
        }
      }
    }
  }
}
