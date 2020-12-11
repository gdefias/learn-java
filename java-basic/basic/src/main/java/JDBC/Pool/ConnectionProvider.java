package JDBC.Pool;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by Defias on 2020/08.
 * Description: 连接提供器
 */

public class ConnectionProvider {
    static private Properties ps;
    private  String JDBC_DRIVER;
    private  String DB_URL;
    private  String DB_USER;
    private  String DB_PASSWORD;

    static {
        ps = new Properties();
        try {
            InputStream in = ConnectionProvider.class.getResourceAsStream("base/src/db.conf");
            ps.load(in);
            in.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public ConnectionProvider() {
        JDBC_DRIVER = (String)ps.get("JDBC_DRIVER");
        DB_URL = (String)ps.get("DB_URL");
        DB_USER = (String)ps.get("DB_USER");
        DB_PASSWORD = (String)ps.get("DB_PASSWORD");
        try {
            Class jdbcDriver = Class.forName(JDBC_DRIVER);
            java.sql.DriverManager.registerDriver((Driver)jdbcDriver.newInstance());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection()throws SQLException{
        Connection con = java.sql.DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
        return con;
    }
}
