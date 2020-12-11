package ssh.jcraft;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Defias
 * Date: 2018-06
 *
 * java利用jcraft实现和远程服务器交互   通过ssh隧道连接数据库
 */

public class TestJSch {
    static int lport = 3307;//本地端口
    static String rhost = "192.168.3.7";//远程MySQL服务器
    static int rport = 3306;//远程MySQL服务端口

    public static void go() {
        String user = "root";//SSH连接用户名
        String password = "yzh@148091348";//SSH连接密码
        String host = "192.168.3.7";//SSH服务器
        int port = 22; //SSH访问端口
        try {
            JSch jsch = new JSch();
            Session session = jsch.getSession(user, host, port);
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            System.out.println("111");
            session.connect();
            System.out.println("222");
            System.out.println(session.getServerVersion()); //这里打印SSH服务器版本信息
            int assinged_port = session.setPortForwardingL(lport, rhost, rport);
            System.out.println("localhost:" + assinged_port + " -> " + rhost + ":" + rport);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sql() throws SQLException {
        Connection conn = null;
        ResultSet rs = null;
        Statement st = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/ssm", "root", "root");
            st = conn.createStatement();
            String sql = "SELECT COUNT(1) FROM user";
            rs = st.executeQuery(sql);
            while (rs.next())
                System.out.println(rs.getString(1));

            //Statement statement = conn.createStatement();
//            st.execute("use test_sql");
//            sql = "SELECT COUNT(1) FROM recurrence_job";
//            rs = st.executeQuery(sql);
//            while (rs.next())
//                System.out.println(rs.getString(1));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rs.close();
            st.close();
            conn.close();
        }
    }

    public static void main(String[] args) throws SQLException {
        go();
        sql();
    }
}
