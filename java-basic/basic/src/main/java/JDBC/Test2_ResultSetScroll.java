package JDBC;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * Created with IntelliJ IDEA.
 * Description: 可滚动和可更新的结果集
 * User: Defias
 * Date: 2018-06

 默认的ResultSet对象不可更新，仅有一个向前移动的光标。因此，只能迭代它一次，并且只能按从第一行到最后一行的顺序进行
 可以生成可滚动和/或可更新的ResultSet对象，需要得到一个不同的Statement或PreparedStatement对象

 并非所有的数据库驱动程序都支持可滚动和可更新的结果集，可以通过DatabaseMetaData接口中的方法获知到是否支持
 并非所有的查询都会返回可更新的结果集，如果结果集涉及到多个表的连接操作，那么它所产生的结果集将是不可更新的。如果查询只涉及到一个表，
 或者在查询时是使用主键连接多个表的，那么它所产生的结果集将是可更新的


 Statement stmt = conn.createStatement(TYPE, CONCURRENCY);
 PreparedStatement preparedStatement = connection.prepareStatement(command, TYPE, CONCURRENCY);

 TYPE:
    TYPE_FORWARD_ONLY：默认类型，指示光标只能向前从上往下移动
    TYPE_SCROLL_INSENSITIVE：光标可以上下移动，指示可滚动并且对结果集的内容作了修改时不敏感
    TYPE_SCROLL_SENSITIVE：光标可以上下移动，指示可滚动并且对结果集的内容作了修改时敏感，如当删除了结果集中一条记录时光标位置随之改变

 CONCURRENCY
    CONCUR_READ_ONLY：结果集不能用于更新数据库（默认值）。即只读
    CONCUR_UPDATABLE：结果集可以用于更新数据库
 */

public class Test2_ResultSetScroll {
    public String driver = "com.mysql.jdbc.Driver";
    private String host = "localhost:3306/test";
    private String user = "haixia.zhang02";
    private String pwd = "123456";
    private Connection conn = null;
    private Statement stmt = null;

    public static void main(String[] args) throws SQLException {
        Test2_ResultSetScroll TestDBO = new Test2_ResultSetScroll();
        TestDBO.connect();
        //TestDBO.TestScroll();
        TestDBO.TestUpdata();
        TestDBO.close();

    }

    //可滚动的结果集
    public void TestScroll() throws SQLException {
        //设置结果集的状态
        stmt = (Statement)conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stmt.executeQuery("SELECT * from test where id in(3,5,8,10,12)");
        System.out.println(rs.getType());  //是否支持可滚动
        assert rs.getType()==ResultSet.TYPE_SCROLL_INSENSITIVE;
        System.out.println(rs.getConcurrency());   //是否支持可更新
        assert rs.getType()==ResultSet.CONCUR_READ_ONLY;

        rs.next(); //将光标前移一行
        System.out.println(rs.getInt(1));

        rs.last(); //光标移到最后一行
        System.out.println(rs.getString(1));
        System.out.println(rs.isLast());
        System.out.println(rs.isAfterLast());
        System.out.println(rs.getRow());

        rs.previous(); //将光标移到该resultset对象的上一行
        System.out.println(rs.getString(3));

        rs.absolute(2); //将光标移动到该编号行
        System.out.println(rs.getString(3));

        rs.relative(2); //光标移向前移动2行
        System.out.println(rs.getInt(1));
        rs.relative(-2); //光标移向后移动2行
        System.out.println(rs.getInt(1));

        rs.first();
        System.out.println(rs.getInt(1));
        rs.beforeFirst();
        System.out.println(rs.isBeforeFirst());
        rs.afterLast();
        System.out.println(rs.isAfterLast());

        rs.close();
    }

    //可更新的结果集
    public void TestUpdata() throws SQLException {
        //设置结果集的状态
        Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

        ResultSet rs=stmt.executeQuery("SELECT * from test where id in(17,19,21)");

        //更新行
        rs.next();
        rs.updateString("test_num","PPPP");  //更新结果中当前行上的某个字段值
        //rs.cancelRowUpdates();   //撤销对当前行的更新
        rs.updateRow();  //将更新信息发送到数据库


        //插入新行
        rs.moveToInsertRow();  //将光标移动到特定的位置（插入行）
        System.out.println(rs.getRow());
        //无法控制在结果集或数据库中添加新数据的位置
        rs.updateInt(1, 93);
        rs.updateInt("num",2);
        rs.updateInt("wid", 3);
        rs.updateString("test_num", "BBBB");
        rs.insertRow(); //将插入行的内容插入到此resultset对象和数据库中

        //将光标移动到调用moveToInsertRow方法前的位置
        rs.moveToCurrentRow();
        System.out.println(rs.getRow());

        //从结果集和数据库中删除行（光标不位于插入行上时不能调用此方法）
        //rs.deleteRow();
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
