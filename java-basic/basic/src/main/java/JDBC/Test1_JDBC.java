package JDBC;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by Defias on 2017/2/27.

 JDBC基本用法


 Resultset中获取日期时间型的几种方法
 1、从结果集中取得日期部分
 resultSet.getDate();  --2013-01-07

 2、从结果集中取得时间部分
 resultSet.getTime()   --22：08：09

 3、从结果集中同时得到日期和时间
 resultSet.getTimestamp(); --2013-01-07 23：08：09

 */
public class Test1_JDBC {
    public static void main(String[] args) throws SQLException, ClassNotFoundException,UnsupportedEncodingException, InterruptedException {
        test1();
        //test2();
    }

    public static void test1() throws SQLException, ClassNotFoundException {
        //加载驱动程序（驱动程序是一个实现接口java.sql.Driver的具体类， 需要加载对应数据库的JDBC驱动程序jar包）
        Class.forName("com.mysql.jdbc.Driver");
        //Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("Driver loaded");

        //建立连接
        Connection connection = DriverManager.getConnection("jdbc:mysql://140.143.202.185:3306/testdb?characterEncoding=utf-8&useSSL=false",
                "root", "jiefanglu14hao"); //连接字符串、用户名、密码
        System.out.println("Database connected");

        //创建statement
        Statement statement = connection.createStatement();
        System.out.println("111");

        //执行语句
        ResultSet resultSet = statement.executeQuery("select * from user where id in (2)");
        System.out.println("222");
        //处理结果
        while (resultSet.next()) {
            System.out.println("333");
            //获取某列的值
            System.out.println(resultSet.getInt(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getString(3));
        }

        //关闭连接
        connection.close();
    }

    public static void test2() throws SQLException, ClassNotFoundException, UnsupportedEncodingException, InterruptedException {
        Connection con;
        Statement stmt;
        ResultSet rs;
        //加载驱动器，下面的代码为加载MySQL驱动器
        Class.forName("com.mysql.jdbc.Driver");
        //注册MySQL驱动器 --可以不用再注册了
        //DriverManager.registerDriver(new com.mysql.jdbc.Driver());

        //设置输出JDBC日志到控制台
        DriverManager.setLogWriter(new PrintWriter(System.out, true));

        //连接到数据库的URL
        String dbUrl = "jdbc:mysql://140.143.202.185:3306/sampledb?&characterEncoding=utf-8&useSSL=false";
        String dbUser="root";
        String dbPwd="root";
        //建立数据库连接
        con = java.sql.DriverManager.getConnection(dbUrl,dbUser,dbPwd);

        //创建一个Statement对象
        stmt = con.createStatement();

        //增加新记录
        String tname = "name" + (new Random(51263512)).nextInt(1000);
        String tdesc = "decs" +  (new Random(51263512)).nextInt(1000);

        //stmt.executeUpdate("insert into test (tname, tdesc) VALUES ('"+tname+"', '"+tdesc+"')");

        stmt.executeUpdate("insert into t_forum(forum_name, forum_desc) VALUES ('"+tname+"', '"+tdesc+"')");

        TimeUnit.SECONDS.sleep(2);

//        //查询记录
//        rs = stmt.executeQuery("select id,tname,tdesc from test limit ");
//
//        //输出查询结果
//        while (rs.next()){
//            long id = rs.getLong(1);
//            String name = rs.getString(2);
//            int age = rs.getInt(3);
//            String address = rs.getString(4);
//
//            //字符编码转换
//            if(name!=null)name=new String(name.getBytes("ISO-8859-1"),"GB2312");
//            if(address!=null)address=new String(address.getBytes("ISO-8859-1"),"GB2312");
//            //打印所显示的数据
//            System.out.println("id="+id+",name="+name+",age="+age+",address="+address);
//        }
//
//        //删除新增加的记录
//        stmt.executeUpdate("delete from CUSTOMERS where name='"+name1+"'");

        //释放相关资源
        //rs.close();
        stmt.close();
        con.close();
    }
}
