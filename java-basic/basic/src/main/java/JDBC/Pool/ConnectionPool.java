package JDBC.Pool;
import java.sql.*;

/**
 * Created by Defias on 2020/08.
 * Description: 数据库连接池
 */
public interface ConnectionPool{
    //从连接池中取出连接
    public Connection getConnection()throws SQLException;

    //把连接返回连接池
    public void releaseConnection(Connection con)throws SQLException;

    //关闭连接池
    public void close();
}