package JDBC;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
/**
 * Created with IntelliJ IDEA.
 * Description: 事务控制
 * User: Defias
 * Date: 2018-06

 数据库事务控制
 事务是指一个工作单元，它包含了一组添加，删除，修改等数据操作命令，这组命令作为一个整体向系统提交执行，要么都执行成功，要么全部恢复

 事务的ACID属性
 原子性 -- Atomicity means either all successful or none.
 一致性 -- Consistency ensures bringing the database from one consistent state to another consistent state.
 隔离性 -- Isolation ensures that transaction is isolated from other transaction.
 持久性 -- Durability means once a transaction has been committed, it will remain so, even in the event of errors, power loss etc.
 数据库系统的客户程序只要向数据库系统声明了一个事务，数据库系统就会自动保证事务的ACID特性

 数据库系统支持两种事务模式：
 1、自动提交模式，每个SQL语句都是一个独立事务，当数据库系统执行完一个SQL语句后，会自动提交事务
 2、手工提交事务：必须由数据库的客户出现显示指定事务开始边界和结束边界


 数据库的并发
 不同的事务对同一部分数据执行操作。如: 事务T1和T2(或多个事务)对同一部分数据进行改，删，查操作：T1进行修改而T2进行查询
 数据库并发可能导致的问题

 i.读脏数据：事务T1修改某一数据，并将其写回数据库，事务T2读取同一数据后，T1由于某种原因被撤消(rollback而非commit)，这时T1已修改过
 的数据恢复原值，T2读到的数据就与数据库中的数据不一致，T2读到的数据就为"脏"数据，即不正确的数据

 ii.不可重复读：事务T1读取数据后，事务T2执行更新操作，使T1无法再现前一次读取结果

 iii.幻读：事务T1按一定条件从数据库中读取某些数据记录后，事务T2插入了一些记录，当T1再次按相同条件读取数据时，发现多了一些记录


 并发控制
 通过connection设置事务隔离级别来对数据库并发进行控制
 事务隔离级别(Connection接口中的常量)
 TRANSACTION_NONE：此级别不支持事务
 TRANSACTION_READ_UNCOMMITTED：此级别允许某一事务读其他事务还没有更改完的数据。允许发生脏读 、不可重复读和幻读
 TRANSACTION_READ_COMMITTED：此级别要求某一事务只能等别的事务全部更改完才能读。可以防止发生脏读，但不可重复读和幻读有可能发生
 TRANSACTION_REPEATABLE_READ：此级别要求某一事务只能等别的事务全部更改完才能读而且禁止不可重复读。也就是可以防止脏读和不可重复读，
                              但幻读有可能发生
 TRANSACTION_SERIALIZABLE：此级别防止发生脏读、不可重复读和幻读，事务只能一个接着一个地执行，而不能并发执行

 MYSQL后台中声明事务：
 1）select @@autocommit
    set autocommit=0   //0:手工提交事务，1：默认值 自动提交事务
 2) begin ... commit ... rollback

 JDBC API声明事务
 void	setAutoCommit(boolean autoCommit)
 将此连接的自动提交模式设置为给定状态
 autoCommit - true启用自动提交模式; false禁用它

 void	commit()
 使自上次提交/回滚以来所做的所有更改都将永久性，并释放此Connection对象当前持有的任何数据库锁

 void	rollback()
 撤消在当前事务中所做的所有更改，并释放此Connection对象当前持有的任何数据库锁


 API

 conn.setAutoCommit(0);//修改系统非自动提交

 conn.commit();//事务提交

 conn.rollback();//事务回滚

 SavePoint sp=con.setSavePoint();//设置保存点

 conn.rollback(sp);//返回保存点

 conn.setTransactionIsolation();//设置隔离级别

 conn.getTransactionIsolation();//获取隔离级别
 */
public class Test4_Transaction {
    public String driver = "com.mysql.jdbc.Driver";
    private String host = "localhost:3306/test";
    private String user = "haixia.zhang02";
    private String pwd = "123456";
    private Connection conn = null;
    private Statement stmt = null;

    public static void main(String[] args) throws SQLException  {
        Test4_Transaction TestDBO = new Test4_Transaction();
        TestDBO.connect();
        //TestDBO.test_transaction();
        TestDBO.test_transactionSavepoint();
        TestDBO.close();
    }

    //事务
    public void test_transaction() {
        try {
            conn.setAutoCommit(false);  //默认情下JDBC的每个数据操作SQL语句都是自动提交的，关闭默认的自动提交模式

            stmt.executeUpdate("insert into test(id,num,wid,test_num) values(97,233,523,'AAA')");
            stmt.executeUpdate("insert into test1(id,num,str) values(97,767,'AAA')");
            conn.commit();  //提交

            stmt.executeUpdate("insert into user(user_id,user_account,user_name) values(1, 'sadf', 232)");
            conn.commit();  //提交

            System.out.println("success!");
        } catch(SQLException e) {
            try {
                System.out.println("false!");
                conn.rollback();  //回滚 --撤销自上次提交以来的所有语句
            } catch (Exception ee) {
                ee.printStackTrace();
            }
        }
    }

    //保存点
    public void test_transactionSavepoint() throws SQLException {
        conn.setAutoCommit(false);  //关闭默认的自动提交模式

        stmt.executeUpdate("insert into test(id,num,wid,test_num) values(98,233,523,'AAA')");
        stmt.executeUpdate("insert into test1(id,num,str) values(98,767,'AAA')");

        Savepoint svpt = conn.setSavepoint();  //设置保存点
        try {
            stmt.executeUpdate("insert into user(user_id,user_account,user_name) values(1, 'sadf', 232)");
            System.out.println("success!");
        } catch(SQLException e) {
            try {
                System.out.println("false!");
                conn.rollback(svpt);  //回滚到保存点
                conn.commit();  //提交
                conn.releaseSavepoint(svpt);  //释放保存点
            } catch (Exception ee) {
                ee.printStackTrace();
            }
        }
    }

    //并发处理
    public void test_transactionConcurrency() {
        try {
            conn.setAutoCommit(false);
            conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            stmt.executeUpdate("insert into test(id,num,wid,test_num) values(97,233,523,'AAA')");
            stmt.executeUpdate("insert into test1(id,num,str) values(97,767,'AAA')");
            conn.commit();

            stmt.executeUpdate("insert into user(user_id,user_account,user_name) values(1, 'sadf', 232)");
            conn.commit();
            System.out.println("success!");
        } catch(SQLException e) {
            try {
                System.out.println("false!");
                conn.rollback();
            } catch (Exception ee) {
                ee.printStackTrace();
            }
        }

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
