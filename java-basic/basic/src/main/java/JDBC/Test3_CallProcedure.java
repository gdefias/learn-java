package JDBC;
import java.sql.*;
/**
 * Created by Defias on 2017/2/27.

 执行SQL存储过程   --CallableStatement接口
 可能会有IN、OUT、INOUT参数
 */


public class Test3_CallProcedure {
    public static void main(String[] args) throws Exception {
        // Class.forName("oracle.jdbc.driver.OracleDriver");
        Class.forName("com.mysql.jdbc.Driver");

        // Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@liang.armstrong.edu:1521:orcl","scott", "tiger");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_sql",
                "haixia.zhang02", "123456");

        //创建CallableStatement对象  studentFound为存储过程
        CallableStatement callableStatement = connection.prepareCall("{? = call studentFound(?, ?)}");


        java.util.Scanner input = new java.util.Scanner(System.in);
        System.out.print("Enter student's first name: ");
        String firstName = input.nextLine();
        System.out.print("Enter student's last name: ");
        String lastName = input.nextLine();

        callableStatement.setString(2, firstName);  //将值传给IN和IN OUT参数
        callableStatement.setString(3, lastName);

        //使用registerOutParameter来注册OUT和IN OUT参数
        callableStatement.registerOutParameter(1, Types.INTEGER);
        callableStatement.execute();  //execute()或executeUpdate()按照SQL语句的类型执行过程

        if (callableStatement.getInt(1) >= 1)  //通过getter方法获取来自OUT参数的值
            System.out.println(firstName + " " + lastName + " is in the database");
        else
            System.out.println(firstName + " " + lastName + " is not in the database");
    }
}
