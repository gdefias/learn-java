package JDBC;
import java.sql.*;
/**
 * Created by Defias on 2017/2/27.
 *
 * PreparedStatement接口  预备语句
 * 继承自Statement接口，用于执行含有或不含有参数的预编译的SQL语句（重复执行时效率更高）
 */


public class Test1_PreparedStatement {
    private static PreparedStatement preparedStatement;

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            // Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("Driver loaded");

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "haixia.zhang02", "123456");
            // ("jdbc:oracle:thin:@liang.armstrong.edu:1521:orcl","scott", "tiger");
            System.out.println("Database connected");

            String queryString = "select * from test where id in (?, ?, ?)";

            //创建PreparedStatement对象
            preparedStatement = connection.prepareStatement(queryString);

            String id1 = "3";
            String id2 = "5";
            String id3 = "7";

            //给指定参数设置值  setString(int parameterIndex, String value)  parameterIndex是语句中参数的下标，从1开始
            preparedStatement.setString(1, id1);
            preparedStatement.setString(2, id2);
            preparedStatement.setString(3, id3);

            ResultSet rset = preparedStatement.executeQuery();  //执行查询
            if (rset.next()) {  //读第一行
                String id = rset.getString(1);
                String num = rset.getString(2);
                String wid = rset.getString(3);

                // Display result
                System.out.println(id + " " + num + " " + wid);
            } else {
                System.out.println("Not found");
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
