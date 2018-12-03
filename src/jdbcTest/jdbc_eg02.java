package jdbcTest;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class jdbc_eg02 {
	public static void main(String[] args) {
		
		Connection conn = JdbcUtil.getConnection();
		Statement sta = null;
		
		try {
			sta = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String sql = "select empno, ename, job from emp";
		ResultSet set = null;
		
		try {
			set = sta.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println("empno    ename    job");
			while(set.next()) {
				System.out.print(set.getInt("empno")+"    ");
				System.out.print(set.getString("ename")+"    ");
				System.out.println(set.getString("job"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			set.close();
			sta.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
