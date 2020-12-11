package JDBC;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.*;

/**
 * Created with IntelliJ IDEA.
 * Description: 读写大对象 -- BLOB
 * User: Defias
 * Date: 2018-06

 BLOB: 二进制大对象

 MySQL的Blob数据分为4种类型：
    TINYBLOB  容量为256B
    BLOB      容量为64KB
    MEDIUMBLOB   容量为16MB
    LONGBLOB     容量为4GB


 ResultSet上调用getBlob或getClob方法获得BLOB或CLOB类型的对象
 BLOB类型对象提供转换成流的方法

 */
public class Test2_Blob {
    Connection con;

    public Test2_Blob(Connection con) {
        this.con=con;
    }

    public static void main(String args[]) throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Driver loaded");

        //建立连接
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test",
                "haixia.zhang02", "123456"); //连接字符串、用户名、密码
        System.out.println("Database connected");

        Test2_Blob tester = new Test2_Blob(con);
        tester.createTableWithBlob();
        tester.saveBlobToDatabase();
        tester.getBlobFromDatabase();
        con.close();
    }

    public void createTableWithBlob() throws Exception {
        Statement stmt=con.createStatement();
        stmt.execute("drop table if exists ABLOB");
        stmt.execute("create table ABLOB(ID bigint auto_increment primary key,FILE mediumblob)");
        stmt.close();
    }

    //向数据库中保存Blob数据
    public void saveBlobToDatabase()throws Exception{
        PreparedStatement stmt=con.prepareStatement("insert into ABLOB(ID,FILE) values(?,?) ");
        stmt.setLong(1,1);
        FileInputStream fin=new FileInputStream("base/src/test.gif");
        stmt.setBinaryStream(2,fin,fin.available());
        stmt.executeUpdate();
        fin.close();
        stmt.close();
    }

    //从数据库中读取Blob数据
    public void getBlobFromDatabase()throws Exception{
        Statement stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery("select FILE from ABLOB where ID=1");
        rs.next();
        Blob blob=rs.getBlob(1);

        //把数据库中的Blob数据拷贝到test_bak.gif文件中
        InputStream in=blob.getBinaryStream();
        FileOutputStream fout = new FileOutputStream("base/src/test_bak.gif");
        int b=-1;
        while((b=in.read())!=-1)
            fout.write(b);
        fout.close();
        in.close();
        rs.close();
        stmt.close();
    }


}
