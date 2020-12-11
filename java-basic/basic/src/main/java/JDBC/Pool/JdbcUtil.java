package JDBC.Pool;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Defias
 * Date: 2018-07
 *
 * 数据库连接池
 */

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class JdbcUtil {
    private static JdbcPool pool = new JdbcPool();


    public static void main(String[] args) throws SQLException {
        Connection conn = getConnection();

        Statement stat = conn.createStatement();
        ResultSet resultSet = stat.executeQuery("select * from test where id in (3,5)");
        while (resultSet.next())
            System.out.println(resultSet.getString(1) + "\t" +
                    resultSet.getString(2) + "\t" +
                    resultSet.getString(3));  //getString获取某列的值

        release(conn, stat, resultSet);
    }

    //从数据库连接池中获取数据库连接对象
    public static Connection getConnection() throws SQLException{
        return pool.getConnection();
    }


    //释放资源 --包括Connection数据库连接对象，负责执行SQL命令的Statement对象，存储查询结果的ResultSet对象
    public static void release(Connection conn,Statement st,ResultSet rs){
        if(rs!=null) {
            try {
                //关闭存储查询结果的ResultSet对象
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            rs = null;
        }
        if(st!=null) {
            try{
                //关闭负责执行SQL命令的Statement对象
                st.close();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }

        if(conn!=null){
            try{
                //关闭Connection数据库连接对象
                conn.close();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
