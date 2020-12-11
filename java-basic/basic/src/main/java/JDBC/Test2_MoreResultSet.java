package JDBC;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Defias
 * Date: 2018-06
 *
 * 多结果集
 * 在执行存储过程，或者在使用允许在单个查询中提交多个select语句的数据库时，一个查询有可能会返回多个结果集
 *
 * 获得自动生成的键 -getGeneratedKeys方法
 */

public class Test2_MoreResultSet {
    public String driver = "com.mysql.jdbc.Driver";
    private String host = "localhost:3306/test";
    private String user = "haixia.zhang02";
    private String pwd = "123456";
    private Connection conn = null;
    private Statement stmt = null;

    public static void main(String[] args) throws Exception {
        Test2_MoreResultSet O = new Test2_MoreResultSet();
        O.connect();
        O.test1();
        O.close();
    }

    private void connect() {
        try {
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection("jdbc:mysql://"
                    + host + "?useUnicode=true&characterEncoding=UTF8", user, pwd);
            stmt = conn.createStatement();
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

    //多结果集
    public void test1() throws Exception {
        //如果由多个结果集构成的链中的下一项是结果集，execute和getMoreResults将返回true，而如果链中的下一项不是更新计数，getUpdateCount返回-1
        boolean isResultSet = stmt.execute("SELECT * from test where id in(3,5,8,10)");  //单结果集当多结果集来处理
        ResultSet rs = null;
        int count = 0;

        while(true) {
            if(isResultSet) {
                //如果Statement.getUpdateCount()==-1&&getMoreResults()==true的话表明当前statement对象正指向一个真正的结果集
                System.out.println("stmt.getUpdateCount(): " + stmt.getUpdateCount());
                rs = stmt.getResultSet();  //可以把Statement理解成为指向ResultSet的指针
                while(rs.next()) {
                    System.out.println(rs.getString(3));
                }
                rs.close();
            } else {
                if(stmt.getUpdateCount() == -1) {
                    break;
                }
                System.out.printf("Result {} is just a count: {}", count, stmt.getUpdateCount());
            }
            count ++;
            isResultSet = stmt.getMoreResults();
            //Statement提供了一个getMoreResults()的方法，该方法能将当前Statement "指针" 移动到下一个结果集，如果下一项不是结果集将返回false
        }

        System.out.println("count: " + count);
    }

    //获得数据库数据插入时自动生的成的键
    public void test2() throws Exception {
        String sql = "insert into user(name) values('Belin')";
        stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = stmt.getGeneratedKeys();

        //或
        //PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        //pstmt.executeUpdate();
        ////获取主键值，是一个ResultSet
        //ResultSet rs = pstmt.getGeneratedKeys();

        if (rs.next()) {
            System.out.println(rs.getInt(1));  //第一列就是自动生成键
        }
    }
}
