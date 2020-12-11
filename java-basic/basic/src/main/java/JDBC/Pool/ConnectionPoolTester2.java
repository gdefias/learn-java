package JDBC.Pool;
import java.sql.*;
/**
 * Created by Defias on 2020/08.
 * Description: 使用已实现的连接池2
 */

public class ConnectionPoolTester2 implements Runnable{
    ConnectionPool pool = new ConnectionPoolImpl2();

    public static void main(String args[]) throws Exception{
        ConnectionPoolTester2 tester = new ConnectionPoolTester2();
        Thread[] threads = new Thread[30];
        for(int i=0; i<threads.length; i++){
            threads[i] = new Thread(tester);
            threads[i].start();
            Thread.sleep(300);
        }

        for(int i=0; i<threads.length; i++){
            threads[i].join();
        }
        tester.close(); //关闭连接池
    }

    public void close(){
        pool.close();
    }

    public void run(){
        try{
            Connection con = pool.getConnection();
            System.out.println(Thread.currentThread().getName()+": 从连接池取出一个连接"+con);
            Statement stmt = con.createStatement();
            stmt.executeUpdate("insert into CUSTOMERS (NAME,AGE,ADDRESS) "
                    +"VALUES ('小王',20,'上海')");

            //释放相关资源
            stmt.close();
            con.close();  //被代理拦截，实际调用的是pool.releaseConnection(con);
            System.out.println(Thread.currentThread().getName()+": 释放连接"+con);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}