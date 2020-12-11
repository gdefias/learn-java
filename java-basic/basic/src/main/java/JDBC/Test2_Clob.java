package JDBC;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.*;

/**
 * Created by Defias on 2020/08.
 * Description: 读写大对象 -- CLOB

 CLOB: 字符型文本大对象 ---ORACLE数据库中数据类型，MYSQL数据库中没有该类型
 处理方式与BLOB相似


 */
public class Test2_Clob {
    Connection con;

    public Test2_Clob(Connection con) {
        this.con = con;
    }

    public static void main(String args[])throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Driver loaded");

        //建立连接
        Connection con = DriverManager.getConnection("jdbc:mysql://140.143.202.185:3306/sampledb?&characterEncoding=utf-8&useSSL=false",
                "root", "root"); //连接字符串、用户名、密码
        System.out.println("Database connected");

        Test2_Clob tester = new Test2_Clob(con);
        tester.createTableWithClob();
        //tester.saveClobToDatabase();
        //tester.getClobFromDatabase();
        con.close();
    }

    public void createTableWithClob()throws Exception{
        Statement stmt=con.createStatement();
        stmt.execute("drop table if exists ACLOB");
        stmt.execute("create table ACLOB(ID bigint auto_increment primary key,FILE clob)");
        stmt.close();
    }

    //向数据库中保存Clob数据
    public void saveClobToDatabase()throws Exception{
        PreparedStatement stmt = con.prepareStatement("insert into ACLOB(ID,FILE) values(?,?) ");
        stmt.setLong(1,1);
        FileInputStream fin = new FileInputStream("test.txt");
        InputStreamReader reader = new InputStreamReader(fin);
        stmt.setCharacterStream(2,reader,fin.available());
        stmt.executeUpdate();
        reader.close();
        stmt.close();
    }

    //从数据库中读取Clob数据
    public void getClobFromDatabase()throws Exception{
        Statement stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery("select FILE from ACLOB where ID=1");
        rs.next();
        Clob clob = rs.getClob(1);

        //把数据库中的Clob数据拷贝到test_bak.txt文件中
        Reader reader = clob.getCharacterStream();
        FileWriter writer = new FileWriter("test_bak.txt");
        int c=-1;
        while((c=reader.read())!=-1)
            writer.write(c);
        writer.close();
        reader.close();
        rs.close();
        stmt.close();
    }
}
