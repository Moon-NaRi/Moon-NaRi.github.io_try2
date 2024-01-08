import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBCUpdateTest {
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

      // UPDATE 쿼리 작성
      String updateQuery = "UPDATE `user` SET nickname = ? WHERE email = ?";
      preparedStatement = connection.prepareStatement(updateQuery);
      // PreparedStatement를 사용하여 쿼리 실행
      preparedStatement.setString(1, "yuza");
      preparedStatement.setString(2, "code11298@naver.com");

      // 쿼리 실행
      int affectedRows = preparedStatement.executeUpdate();

      if (affectedRows > 0) {
        System.out.println("데이터가 성공적으로 업데이트되었습니다.");
      } else {
        System.out.println("업데이트에 실패했습니다. 조건에 맞는 데이터가 없을 수 있습니다.");
      }
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
