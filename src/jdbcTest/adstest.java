package jdbcTest;

import java.sql.*;
import java.util.Properties;

public class adstest {

    public static void main(String[] args) {

        Connection connection = null;
        PreparedStatement statement = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //adb_url是AnalyticDB for MySQL集群的连接地址URL，可以在控制台的集群信息页面获取连接URL，3306是端口号。
            //db_name是AnalyticDB for MySQL集群中的数据库名称。
            String url = "jdbc:mysql://am-2ze85jk72mn38c261131910.ads.aliyuncs.com:3306/real_time_datawarehouse?useUnicode=true&characterEncoding=UTF-8";
            Properties connectionProps = new Properties();
            //account_name是AnalyticDB for MySQL集群中的用户账号：高权限账号或者普通账号。
            connectionProps.put("user", "ads_test_root");
            //account_password是AnalyticDB for MySQL集群中用户账号对应的密码。
            connectionProps.put("password", "ads_test_root@vhall2020");
            connection = DriverManager.getConnection(url, connectionProps);
            statement = connection.prepareStatement("INSERT INTO test(id) VALUES(?)");

            statement.setInt(1, 8);
            statement.addBatch();
            statement.setInt(1, 9);
            statement.addBatch();
            statement.setInt(1, 10);
            statement.addBatch();
            statement.setInt(1, 11);
            statement.addBatch();
            statement.setInt(1, 12);
            statement.addBatch();
            statement.setInt(1, 13);
            statement.addBatch();

            statement.executeBatch();
            statement.clearBatch();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }



    }

}
