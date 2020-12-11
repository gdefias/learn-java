package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by Defias on 2020/08.
 * Description:  批量抓取

 设置ResultSet每次向数据库抓取的行数
     如果设置太小的话，需要频繁访问数据库，效率低
     如果设置太大的话，当数据量很大时会消耗非常大的内存

 void	setFetchSize(int rows)
 给JDBC驱动程序一个提示，当这个ResultSet对象需要更多的行时，应该从数据库中获取的行数
 ResultSet.setFetchSize()
 Statement.setFetchSize()
 PreparedStatement.setFetchSize()

 void	setFetchDirection(int direction)
 给出这个ResultSet对象中的行将被处理的方向的提示
 ResultSet.FETCH_FORWARD  单向
 ResultSet.FETCH_REVERSE  双向
 ResultSet.FETCH_UNKNOWN  未知

 */
public class Test2_FetchSize {
    public static void main(String[] args) throws Exception {
        test();
    }

    public static void test() throws Exception {
        //建立连接
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test",
                "haixia.zhang02", "123456"); //连接字符串、用户名、密码
        System.out.println("Database connected");

        Statement stmt = connection.createStatement();
        ResultSet rsset = stmt.executeQuery("SELECT * FROM user LIMIT 30");
        rsset.setFetchSize(10);
        while (rsset.next()) {
            //这里还是30条记录
        }
    }
}
