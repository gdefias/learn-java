package JDBC.Pool;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
/**
 * Created with IntelliJ IDEA.
 * Description:  数据库连接工具类 -- JNDI of Tomcat
 * User: Defias
 * Date: 2018-07


 JNDI(Java Naming and Directory Interface)  ---Java命名和目录接口
 它对应于J2SE中的javax.naming包，这套API的主要作用在于：它可以把Java对象放在一个容器中（JNDI容器），并为容器中的java对象取一个
 名称，以后程序想获得Java对象，只需通过名称检索即可。其核心API为Context，它代表JNDI容器，其lookup方法为检索容器中对应名称的对象


 JTA(Java Transaction API)
 提供了跨数据库连接（或其他JTA资源）的事务管理能力。JTA事务管理则由JTA容器实现

 Tomcat服务器创建的数据源是以JNDI资源的形式发布的
 Tomcat服务器的数据源配置：
 <Context>
      <Resource name="jdbc/datasource" auth="Container"
      type="javax.sql.DataSource" username="haixia.zhang02" password="123456"
      driverClassName="com.mysql.jdbc.Driver"
      url="jdbc:mysql://localhost:3306/test"
      maxActive="8" maxIdle="4"/>
 </Context>
 */
public class JdbcUtils_JNDI {
    private static DataSource ds = null;
    //在静态代码块中创建数据库连接池
    static {
        try {
            //初始化JNDI
            Context initCtx = new InitialContext();
            //得到JNDI容器
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            //从JNDI容器中检索name为jdbc/datasource的数据源
            ds = (DataSource)envCtx.lookup("jdbc/datasource");
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
        //从数据源中获取数据库连接
        return ds.getConnection();
    }


    //释放资源 --包括Connection数据库连接对象，负责执行SQL命令的Statement对象，存储查询结果的ResultSet对象
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
