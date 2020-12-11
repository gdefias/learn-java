package JDBC.Pool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.beanutils.BeanUtils;
/**
 * Created with IntelliJ IDEA.
 * Description: DataSource 数据源
 * User: Defias
 * Date: 2018-06
 */


public class DataSource_c3p0 {
    private static DataSource dataSource;
    private static Connection connection;
    private static PreparedStatement ps;
    private static ResultSet rs;

    public static void main(String[] args) throws Exception {
        test1();
//        test2();

    }

    //c3p0数据源：com.mchange.v2.c3p0.ComboPooledDataSource
    public static void test1() throws Exception {
        ComboPooledDataSource ds = new ComboPooledDataSource();
        ds.setDriverClass("com.mysql.jdbc.Driver");
        ds.setJdbcUrl("jdbc:mysql://localhost:3306/test");
        ds.setUser("haixia.zhang02");
        ds.setPassword("123456");
        ds.setInitialPoolSize(50);
        ds.setMaxPoolSize(100);
        ds.setMaxIdleTime(10000);

        //通过DataSource来得到Connection
        connection = ds.getConnection();
        ps = connection.prepareStatement("select * from test where id in (3,5)");
        rs = ps.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString(3));
        }
    }

    //properties配置文件+c3p0数据源
    public static void test2() throws Exception {
        Properties properties = new Properties();
        //从指定的配置文件加载数据到properties中
        properties.load(DataSource_c3p0.class.getClassLoader().getResourceAsStream("TestDataSource.properties"));


        //将properties其中每条记录前面的jdbc.去掉再重新装入一个Properties中
        Properties dbProperties = new Properties();
        for (Object key : properties.keySet()) {
            String temp = (String) key;

            if (temp.startsWith("jdbc.")) {
                String name = temp.substring(5);
                dbProperties.put(name, properties.getProperty(temp));
            }
        }

        //列出dbProperties中的所有属性
        dbProperties.list(System.out);
        System.out.println("==============================================");

        //从厂商提供的DataSource实现中加载其对象
        dataSource = (DataSource)Class.forName(properties.getProperty("jdbc.datasource")).newInstance();

        //把dbProperties中的key-value作为属性加到dataSource这个bean对象中
        BeanUtils.populate(dataSource, dbProperties);

        //通过DataSource来得到Connection
        connection = dataSource.getConnection();
        ps = connection.prepareStatement("select * from test where id in (3,5)");
        rs = ps.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getString(3));
        }
    }

    //JNDI数据源
    public static void test3() throws NamingException,SQLException {
        Context jndiContext = new InitialContext();
        DataSource source = (DataSource) jndiContext.lookup("java:comp/env/jdbc/corejava");
        Connection conn = source.getConnection();

    }
}
