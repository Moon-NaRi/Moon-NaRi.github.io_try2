import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnectionTest {
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
    // 데이터베이스 연결
    Connection connection = null;
    try {
      connection = DriverManager.getConnection(url, user, password);
      System.out.println("데이터베이스에 성공적으로 연결되었습니다.");

      // 여기에서 데이터베이스 관련 작업을 수행하면 됩니다.
    } catch (SQLException e) {
      System.err.println("데이터베이스에 연결하는 중 오류가 발생했습니다.");
      e.printStackTrace();
    } finally {
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
