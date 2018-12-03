package jdbcTest;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtil {
	public static String url = null;
	public static String user = null;
	public static String password = null;
	public static Connection conn = null;
	
	static 
	{
		InputStream in = 
				JdbcUtil.class.getClassLoader().getResourceAsStream("jdbc01.properties");
		
		Properties properties = new Properties();
		try {
			properties.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		url = properties.getProperty("url");
		user = properties.getProperty("user");
		password = properties.getProperty("pass");
		
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection()
	{
		return conn;
	}
	
	public static void CloseConnection()
	{
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
