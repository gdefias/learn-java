package JDBC.Pool;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;
/**
 * Created with IntelliJ IDEA.
 * Description: 数据库连接工具类 -- DBCP
 * User: Defias
 * Date: 2018-07

 很多WEB服务器(Weblogic, WebSphere, Tomcat)都提供了DataSoruce的实现，即连接池的实现。通常我们把DataSource的实现，按其英文含义
 称之为数据源，数据源中都包含了数据库连接池的实现。在使用了数据库连接池之后，在项目的实际开发中就不需要编写连接数据库的代码了，直接
 从数据源获得数据库的连接

 DBCP数据库连接池
 Apache软件基金组织下的开源连接池实现，Tomcat的连接池正是采用该连接池来实现的。该数据库连接池既可以与应用服务器整合使用，也可由应用
 程序独立使用

 dbcp与c3p0区别：
     dbcp没有自动回收空闲连接的功能
     c3p0有自动回收空闲连接功能
 */


public class JdbcUtils_DBCP {
    private static DataSource ds = null;
    //在静态代码块中创建数据库连接池
    static {
        try {
            //加载dbcpconfig.properties配置文件
            InputStream in = JdbcUtils_DBCP.class.getClassLoader().getResourceAsStream("dbcpconfig.properties");
            Properties prop = new Properties();
            prop.load(in);
            //创建数据源
            ds = BasicDataSourceFactory.createDataSource(prop);
        } catch (Exception e) {
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
        //从数据源中获取数据库连接
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
