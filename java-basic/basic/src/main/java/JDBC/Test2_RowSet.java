package JDBC;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.RowSetMetaData;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;
import javax.sql.rowset.RowSetFactory;
import com.sun.rowset.CachedRowSetImpl;

/**
 * Created with IntelliJ IDEA.
 * Description:  行集 --RowSet
 * User: Defias
 * Date: 2018-06

 背景
    1.基于结果集ResultSet的缺点：在与用户的整个交互过程中，必须始终与数据库保持连接
        后果：当用户长时间离开时，数据库连接长时间被占用，而这属于稀缺资源
        解决：使用行集RowSet，RowSet继承了ResultSet接口，却无需始终保持与数据库的连接
    2.结果集不便于移动，因为数据结构复杂，且依赖于连接
        解决：使用行集RowSet，RowSet适用于将查询结果移动到复杂应用的其他层，或者其他设备当中
    3.因为RowSet继承了ResultSet接口，所以ResultSet中的方法RowSet都能用，RowSet是对ResultSet离线化、移动化的增强

 RowSet对象表示的行集总是可滚动的，滚动的方式与滚动结果集的方式相同

 如果一个行集包含的是复杂查询的查询结果，就无法把修改写到数据库中;如果行集上的数据都来自同一张表，那就保证可以更新到数据库中


 javax.sql.rowset包提供的接口，这些接口扩展了行集RowSet接口：
    1.CachedRowSet接口：允许在断开连接的状态下执行相关的操作
    2.WebRowSet接口：代表了一个被缓存的行集，且该行集可以保存为XML文件，该XML文件可以移动到Web应用的其他层中，只要在该层中使用
      WebRowSet对象重新打开该文件即可
    3.FilteredRowSet和JoinRowSet接口：支持对行集的轻量级操作，即等同于SQL中的SELECT和JOIN操作，操作的对象是存储在行集中的数据，
      因此也无需建立数据库连接
    4.JdbcRowSet接口：ResultSet接口的一个瘦包装器，他从RowSet接口中继承了都有用的get和set方法，从而将一个结果集转换成一个Bean

 RowSet的同步问题
    在填充了行集之后，如果数据库中的数据发生了改变，将造成数据不一致；此时，可以检查行集中的原始值（修改前的值）与数据库中的当前值是
    否一致，一致时才同意修改（比如让修改后的值覆盖数据库中的值）；不一致时，抛出SynProviderException异常，且不向数据库中写数据


 CachedRowSet接口
 public interface CachedRowSet extends RowSet, Joinable
 CachedRowSet的所有标准CachedRowSet必须实现的接口
 会先把查询结果保存到内存中，因此无需一直保持与数据库的连接，不适合用来处理数据量比较大的结果，因为会效果过多的内存

 void	acceptChanges()
 对此CachedRowSet对象的离线更改后，将更新，插入和删除真正更新到数据库中

 void	acceptChanges(Connection con)
 对此CachedRowSet对象的离线更改后，将更新，插入和删除真正更新到指定连接的数据库中

 并不是所有行集都可以被更新，可更新的行集必须满足查询语句只查询一张表，并且查询的字段包括表的所有主键
 调用acceptChanges时可能抛出SyncProviderException异常，因为离线修改到真正更新期间，别人也可能已经修改了相同的记录

 */

public class Test2_RowSet {
    public String driver = "com.mysql.jdbc.Driver";
    private String host = "localhost:3306/test";
    private String user = "haixia.zhang02";
    private String pwd = "123456";
    private Connection conn = null;
    private Statement stmt = null;

    public static void main(String[] args) throws SQLException {
        Test2_RowSet TestDBO = new Test2_RowSet();
        TestDBO.connect();
        TestDBO.TestCachedRowSet();
        //TestDBO.TestCachedRowSet2();
        //TestDBO.close();

    }

    public void Test1() throws SQLException {
        //JavaSE7获取行集的标准方式
        RowSetFactory factory = RowSetProvider.newFactory();
        CachedRowSet crs = factory.createCachedRowSet();
        //CachedRowSet crs1 = new CachedRowSetImpl();  //如果有具体实现类，也可使用下面的方式
    }

    //使用结果集来填充行集
    public void TestCachedRowSet() throws SQLException {
        stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery("SELECT * from test where id in(3,5,8,10,12)");

        //创建CachedRowSet对象
        CachedRowSet crs = new CachedRowSetImpl();

        //将ResultSet放入CachedRowSet中
        crs.populate(result);

        //此时就可以关闭数据库连接了
        conn.close();
        stmt.close();

        //行集操作
        //如果使用结果集来填充行集，那么行集是不知道原始表的名字的，这时行集需要调用setTable来设置表名称
        System.out.println(crs.getTableName());
        crs.setTableName("test");
        System.out.println(crs.getTableName());

        //从CachedRowSet对象获取数据
        while (crs.next()) {
            int id = crs.getInt(1);
            int num = crs.getInt(2);
            int wid = crs.getInt("wid");
            String test_num = crs.getString("test_num");
            System.out.println(id + "  " + num + "  " + wid + "  " + test_num);
        }

        //RowSetMetaData
        RowSetMetaData rsmd = (RowSetMetaData)crs.getMetaData();
        int count = rsmd.getColumnCount();  //列数
        int type = rsmd.getColumnType(4);  //存储在第4列的JDBC类型的值
        System.out.println("count: " + count);
        System.out.println("type: " + type);


        //更新CachedRowSet对象
        crs.absolute(4);
        crs.updateString("test_num", "OOOO");
        crs.updateRow();

        //将修改写回数据库 --如果不带连接参数则只有行集中设置了数据库连接相关参数了才会生效
        //crs.acceptChanges();
        connect();  //重新连接下
        conn.setAutoCommit(false);  //将自动提交改成手动提交
        crs.acceptChanges(conn);

        //插入数据
        crs.moveToInsertRow();
        crs.updateInt(1, 12);
        crs.updateInt("num", 147);
        crs.updateInt("wid", 518);
        crs.updateString("test_num", "JJJJ");
        crs.insertRow();
        crs.moveToCurrentRow();
        crs.acceptChanges(conn);

    }

    //自力更生: 让CachedRowSet对象自动创建一个数据库连接
    public void TestCachedRowSet2() throws SQLException {
        CachedRowSet crs = new CachedRowSetImpl();

        //设置此RowSet对象在使用DriverManager创建连接时将使用的URL
        crs.setUrl("jdbc:mysql://localhost:3306/test");
        //设置用户名和密码
        crs.setUsername("haixia.zhang02");
        crs.setPassword("123456");

        //设置查询语句并填充参数
        crs.setCommand("SELECT * from test where id in(?, ?, ?, ?)");
        String id1 = "1";
        String id2 = "2";
        String id3 = "17";
        String id4 = "19";
        crs.setString(1, id1);
        crs.setString(2, id2);
        crs.setString(3, id3);
        crs.setString(4, id4);

        //设置每批次获取2行 --如果查询结果很大指定每一页的尺寸
        crs.setPageSize(2);

        //将查询结果填充到行集
        //execute方法调用将会:1.建立数据库连接;2.执行查询操作；3.填充行集；4.最后自动断开连接！
        crs.execute();

        //行集操作
        //从CachedRowSet对象获取数据
        while (crs.next()) {
            int id = crs.getInt(1);
            int num = crs.getInt(2);
            int wid = crs.getInt("wid");
            String test_num = crs.getString("test_num");
            System.out.println(id + "  " + num + "  " + wid + "  " + test_num);
        }

        //循环获取下一批次
        while(crs.nextPage()) {
            while (crs.next()) {
                int id = crs.getInt(1);
                int num = crs.getInt(2);
                int wid = crs.getInt("wid");
                String test_num = crs.getString("test_num");
                System.out.println(id + "  " + num + "  " + wid + "  " + test_num);
            }
        }
    }

    private void connect() {
        try {
            Class.forName(driver).newInstance();
            conn = (com.mysql.jdbc.Connection) DriverManager.getConnection("jdbc:mysql://"
                    + host + "?useUnicode=true&characterEncoding=UTF8", user, pwd);
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
}
