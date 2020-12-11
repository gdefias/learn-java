package JDBC;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
//依赖jar包:mysql-connector-java.jar
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
/**
 * Created by Defias on 2017/3/16.

 执行SQL - execute

 每个Connection连接对象可以创建一个或多个Statement对象（可以通过DatabaseMetaData的getMaxStatements()方法获取）
 同一个Statement对象可以用于多个不相关的命令和查询
 同一个Statement对象最多只能有一个打开的结果集

 三个查询方法：
    boolean execute(String sql)   可执行任何SQL语句，返回一个布尔值，表示是否返回ResultSet
    ResultSet executeQuery(String sql)  执行SQL查询，并返回ResultSet 对象
    int executeUpdate(String sql)  可执行增，删，改，返回执行受到影响的行数

 execute
    用于执行返回多个结果集、多个更新计数或二者组合的语句，execute方法应该仅在语句能返回多个ResultSet对象、多个更新计数或ResultSet对
    象与更新计数的组合时使用

 executeQuery
    用于产生单个结果集的语句，例如 SELECT 语句

 executeUpdate
    用于执行 NSERT、UPDATE 或 DELETE 语句以及 SQL DDL（数据定义语言）语句
    例如 CREATE TABLE 和 DROP TABLE。INSERT、UPDATE 或DELETE
    语句的效果是修改表中零行或多行中的一列或多列，返回值是一个整数，指示受影响的行数（即更新计数）
    对于 CREATE TABLE 或 DROP TABLE 等不操作行的语句，返回值总为零
 */
public class Test1_Query {
    public String driver = "com.mysql.jdbc.Driver";
    private String host;
    private String user;
    private String pwd;
    private Connection conn = null;
    private Statement stmt = null;

    public static void main(String[] args) throws SQLException {
        Test1_Query testConnect = new Test1_Query("localhost:3306/test", "haixia.zhang02", "123456");

        testConnect.connect();

        List<HashMap<String, String>> rs = testConnect.query("SELECT * from test where id = 3");
        System.out.println(rs.get(0).get("wid"));

        List<HashMap<String, String>> rs2 = testConnect.query("SELECT * from test where id = 5");
        System.out.println(rs2.get(0).get("wid"));

        testConnect.close();
    }


    //构造方法
    public Test1_Query(String host, String user, String pwd) {
        this.host = host;
        this.user = user;
        this.pwd = pwd;
    }

    //连接
    private void connect() {
        try {
            Class.forName(driver).newInstance();
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://"
                            + host + "?useUnicode=true&characterEncoding=UTF8", user, pwd);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //关闭连接
    public synchronized void close() {
        try {
            if (stmt != null) {
                stmt.close();
                stmt = null;
            }
            if (conn != null) {
                conn.close();
                conn = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void statement() {
        try {
            if (conn == null) {
                throw new SQLException("conn为null");
            }
            stmt = (Statement) conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private ResultSet resultSet(String sql) {
        ResultSet rs = null;
        if (stmt == null) {
            statement();
        }
        try {
            rs = stmt.executeQuery(sql);  //executeQuery执行查询
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    private List<HashMap<String, String>> query(String sql) {
        ResultSet rs = resultSet(sql);
        List<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
        try {
            ResultSetMetaData md = rs.getMetaData();  //ResultSetMetaData：结果集元数据接口
            int cc = md.getColumnCount();
            while (rs.next()) {   //遍历查询结果ResultSet，逐行遍历
                HashMap<String, String> columnMap = new HashMap<String, String>();
                for (int i = 1; i <= cc; i++) {
                    columnMap.put(md.getColumnName(i), rs.getString(i));  //rs.getString(i): 放回当前行中第i列的值，从1开始，当i为字符串时获取的是对应的列
                }
                result.add(columnMap);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


}
