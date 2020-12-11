package JDBC;

/**
 * Created by Defias on 2017/2/27.
 *
 * 结果集元数据  --ResultSetMetaData接口
 *
 */

import java.sql.*;

public class Test2_ResultSetMetaData {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Driver loaded");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "haixia.zhang02", "123456");
        System.out.println("Database connected");

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from test where id in (3,5,8,10,12)");


        //获得一个ResultSetMetaData对象
        ResultSetMetaData rsMetaData = resultSet.getMetaData();
        for (int i = 1; i <= rsMetaData.getColumnCount(); i++)  //循环输出所有列名
            System.out.printf("%-12s\t", rsMetaData.getColumnName(i));
        System.out.println();

        while (resultSet.next()) {  //调用next逐行遍历
            for (int i = 1; i <= rsMetaData.getColumnCount(); i++)  //循环输出所有列
                System.out.printf("%-12s\t", resultSet.getObject(i));
            System.out.println();
        }

        // Close the connection
        connection.close();
    }
}
