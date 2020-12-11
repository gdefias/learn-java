package JDBC;

/**
 * Created by Defias on 2017/2/27.
 *
 * 数据库元数据  DatabaseMetaData接口
 * 描述数据库或其组成部分的数据


 */

import java.sql.*;

public class Test2_MetaData {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        test0();
        test1();
    }

    public static void test0() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Driver loaded");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test",
                "haixia.zhang02", "123456");
        System.out.println("Database connected");

        //得到一个DatabaseMetaData对象
        DatabaseMetaData dbMetaData = connection.getMetaData();
        System.out.println("database URL: " + dbMetaData.getURL());
        System.out.println("database username: " + dbMetaData.getUserName());
        System.out.println("database product name: " + dbMetaData.getDatabaseProductName());
        System.out.println("database product version: " + dbMetaData.getDatabaseProductVersion());
        System.out.println("JDBC driver name: " + dbMetaData.getDriverName());
        System.out.println("JDBC driver version: " + dbMetaData.getDriverVersion());
        System.out.println("JDBC driver major version: " + dbMetaData.getDriverMajorVersion());
        System.out.println("JDBC driver minor version: " + dbMetaData.getDriverMinorVersion());
        System.out.println("Max number of connections: " + dbMetaData.getMaxConnections());
        System.out.println("MaxTableNameLength: " + dbMetaData.getMaxTableNameLength());
        System.out.println("MaxColumnsInTable: " + dbMetaData.getMaxColumnsInTable());

        System.out.println("supportsResultSetType: " + dbMetaData.supportsResultSetType(
                ResultSet.TYPE_SCROLL_INSENSITIVE));
        System.out.println("supportsResultSetConcurrency: " + dbMetaData.supportsResultSetConcurrency(
                ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY));

        // Close the connection
        connection.close();
    }

    //获取数据库表
    public static void test1() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Driver loaded");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test",
                "haixia.zhang02", "123456");
        System.out.println("Database connected");

        //得到一个DatabaseMetaData对象
        DatabaseMetaData dbMetaData = connection.getMetaData();

        System.out.println("允许的最大连接数为:"+dbMetaData.getMaxConnections());
        System.out.println("一个连接允许同时打开的Statement对象的数目为:"+dbMetaData.getMaxStatements());

        //获得一个包含所有数据库表信息的结果集，每一行都表示都包含了数据库中一张表的详细信息
        ResultSet rsTables = dbMetaData.getTables(null, null, null,
                new String[] {"TABLE"});

        System.out.print("User tables: ");
        while (rsTables.next())
            System.out.print(rsTables.getString("TABLE_NAME") + " ");

        // Close the connection
        connection.close();
    }
}
