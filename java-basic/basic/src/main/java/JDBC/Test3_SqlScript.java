package JDBC;
import java.sql.*;
import java.io.*;
/**
 * Created by Defias on 2020/08.
 * Description:   执行SQL脚本文件
 *
 */

public class Test3_SqlScript {
    public static void main(String[] args) throws Exception {
        test();
    }

    public static void test() throws Exception {
        //建立连接
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test",
                "haixia.zhang02", "123456"); //连接字符串、用户名、密码
        System.out.println("Database connected");

        //SQL脚本文件
        String sqlfile="base/src/testsql.sql";

        Statement stmt = connection.createStatement();
        BufferedReader reader = new BufferedReader(new FileReader(new File(sqlfile)));
        try{
            String data = null;
            String sql = "";
            while((data = reader.readLine())!=null){
                data = data.trim(); //删除开头与结尾的空格
                if(data.length()==0)
                    continue; //忽略空行
                sql = sql+data;
                if(sql.substring(sql.length()-1).equals(";")) {
                    System.out.println(sql);
                    boolean hasResult = stmt.execute(sql);
                    if(hasResult)
                        showResultSet(stmt.getResultSet());
                    sql="";
                }
            }
        } finally {
            connection.close();
        }
    }

    public static void showResultSet(ResultSet rs) throws SQLException{
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        for(int i=1; i<=columnCount; i++){
            if(i>1)
                System.out.print(",");
            System.out.print(metaData.getColumnLabel(i));
        }
        System.out.println();

        while(rs.next()){
            for(int i=1; i<=columnCount; i++){
                if(i>1)
                    System.out.print(",");
                int type = metaData.getColumnType(i);
                switch(type){
                    case Types.BIGINT:
                        System.out.print(rs.getLong(i));
                        break;
                    case Types.FLOAT:
                        System.out.print(rs.getFloat(i));
                        break;
                    case Types.VARCHAR:
                    default:
                        System.out.print(rs.getString(i));
                }
            }
            System.out.println();
        }
        rs.close();
    }
}
