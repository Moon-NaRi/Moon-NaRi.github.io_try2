import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCInsertTest {
  public static void main(String[] args) throws SQLException {
    // JDBC 연결 정보
    String url = "jdbc:mysql://localhost:3306/blog_database?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeNehavior=convertToNull";
    String user = "Libong";
    String password = "kyj1028";

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
    String sql;
    try {
      connection = DriverManager.getConnection(url, user, password);
      System.out.println("데이터베이스에 성공적으로 연결되었습니다.");

      // 여기에서 데이터베이스 관련 작업을 수행하면 됩니다.

      String insertQuery = "INSERT INTO `user` (email, `password`, `position`, username, nickname, regDate, updDate) VALUES (?, ?, 'bronze', ?, ?, NOW(), NOW())";
      preparedStatement = connection.prepareStatement(insertQuery);

      try {
        // 값 설정
        preparedStatement.setString(1, "user1");
        preparedStatement.setString(2, "user1000");
        preparedStatement.setString(3, "name one");
        preparedStatement.setString(4, "nickname one");

        // 쿼리 실행
        int affectedRows = preparedStatement.executeUpdate();

        if (affectedRows > 0) {
          System.out.println("데이터가 성공적으로 삽입되었습니다.");
        } else {
          System.out.println("데이터 삽입에 실패했습니다.");
        }
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }

      System.out.println(insertQuery);
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
