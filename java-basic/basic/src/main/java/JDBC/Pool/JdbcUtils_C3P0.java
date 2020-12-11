package JDBC.Pool;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.mchange.v2.c3p0.ComboPooledDataSource;
/**
 * Created with IntelliJ IDEA.
 * Description: 数据库连接工具类 -- C3P0
 * User: Defias
 * Date: 2018-07
 *
 * C3P0数据库连接池
 * 一个开源的JDBC连接池，它实现了数据源和JNDI绑定，支持JDBC3规范和JDBC2的标准扩展。目前使用它的开源项目有Hibernate，Spring等
 *
 */


public class JdbcUtils_C3P0 {
    private static ComboPooledDataSource ds = null;
    //在静态代码块中创建数据库连接池
    static {
        try {
            //通过代码创建C3P0数据库连接池
            /*
            ds = new ComboPooledDataSource();
            ds.setDriverClass("com.mysql.jdbc.Driver");
            ds.setJdbcUrl("jdbc:mysql://localhost:3306/jdbcstudy");
            ds.setUser("root");
            ds.setPassword("XDP");
            ds.setInitialPoolSize(10);
            ds.setMinPoolSize(5);
            ds.setMaxPoolSize(20);
            */

            //通过读取C3P0的xml配置文件创建数据源，C3P0的xml配置文件c3p0-config.xml必须放在src目录下
            //ds = new ComboPooledDataSource();//使用C3P0的默认配置来创建数据源
            ds = new ComboPooledDataSource("MySQL");//使用C3P0的命名配置来创建数据源

        }catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }


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

    //从数据源中获取数据库连接
    public static Connection getConnection() throws SQLException{
        return ds.getConnection();
    }

    //释放的资源 --包括Connection数据库连接对象，负责执行SQL命令的Statement对象，存储查询结果的ResultSet对象
    public static void release(Connection conn,Statement st,ResultSet rs){
        if(rs!=null){
            try{
                //关闭存储查询结果的ResultSet对象
                rs.close();
            }catch (Exception e) {
                e.printStackTrace();
            }
            rs = null;
        }
        if(st!=null){
            try{
                //关闭负责执行SQL命令的Statement对象
                st.close();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }

        if(conn!=null){
            try{
                //将Connection连接对象还给数据库连接池
                conn.close();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
