package JDBC;

import java.sql.*;

/**
 * Created by Defias on 2020/08.
 * Description: SQL异常 - SQLException


 方法：
 getMesasge  数据库系统提供的描述错误的字符串
 getSQLState   数据库系统提供的错误状态
 getErrorCode  数据库系统提供的错误编号

 */
public class Test1_SQLException {
    public static void main(String args[])  {
        try{
            //建立连接
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test",
                    "haixia.zhang02", "123456"); //连接字符串、用户名、密码
            System.out.println("Database connected");

            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select FIRSTNAME from CUSTOMERS");

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch(SQLException e) {
            System.out.println("ErrorCode:"+e.getErrorCode());
            System.out.println("SQLState:"+e.getSQLState());
            System.out.println("Reason:"+e.getMessage());
        }
    }
}
