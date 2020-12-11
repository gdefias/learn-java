package JDBC;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created with IntelliJ IDEA.
 * Description:  批量处理操作
 * User: Defias
 * Date: 2018-06
 *
 * 一个语句序列作为一批操作同时被收集和提交
 *
 * 同一批中的语句可以是INSERT、UPDATE、DELETE等操作，也可以是数据库定义语言CREATE TABLE、DROP TABLE，但批量处理中添加SELECT语句
 * 会抛出异常
 *
 */
public class Test2_Batch {
    public String driver = "com.mysql.jdbc.Driver";
    private String host = "localhost:3306/test";
    private String user = "haixia.zhang02";
    private String pwd = "123456";
    private Connection conn = null;
    private Statement stmt = null;

    public static void main(String[] args) throws Exception {
        Test2_Batch TestDBO = new Test2_Batch();
        TestDBO.connect();
        //TestDBO.test_batch();
        TestDBO.test_batchUsePreparedStatement();
        TestDBO.close();

    }

    public void test_batch() throws SQLException {
        conn.setAutoCommit(false);
        stmt.addBatch("insert into user values(20, 'abhi','abhi')");
        stmt.addBatch("insert into user values(21, 'umesh','umesh')");

        int[] res = stmt.executeBatch();  //执行当前批量更新中的所有语句，返回一个记录数的数组
        //conn.commit();

        for(int i: res) {
            System.out.println(i);
        }
        int affectRowCount = res.length;
        System.out.println("成功了插入了" + affectRowCount + "行");
    }

    public void test_batchUsePreparedStatement() throws Exception {
        conn.setAutoCommit(false);
        PreparedStatement ps = conn.prepareStatement("insert into user values(?,?,?)");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            System.out.println("enter user_id");
            String s1 = br.readLine();
            int user_id = Integer.parseInt(s1);

            System.out.println("enter user_account");
            String user_account = br.readLine();

            System.out.println("enter user_name");
            String user_name = br.readLine();

            ps.setInt(1, user_id);
            ps.setString(2, user_account);
            ps.setString(3, user_name);

            ps.addBatch();
            System.out.println("Want to add more records y/n");
            String ans = br.readLine();
            if(ans.equals("n")){
                break;
            }
        }
        int[] res = ps.executeBatch();
        System.out.println("record successfully saved");
        //conn.commit();
        ps.close();

        int affectRowCount = res.length;
        System.out.println("成功了插入了" + affectRowCount + "行");
    }

    private void connect() {
        try {
            Class.forName(driver).newInstance();
            conn = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://"
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
}
