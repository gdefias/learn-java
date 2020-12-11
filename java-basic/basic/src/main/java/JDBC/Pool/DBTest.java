package JDBC.Pool;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Created with IntelliJ IDEA.
 * Description: 使用数据库JDBC连接工具
 * User: Defias
 * Date: 2018-06
 *
 */

public class DBTest {
    public static void main(String[] args) {
//        System.out.println("数据库的原数据");
//        testQuery3();
//        testInsert();
//        System.out.println("执行插入后的数据");
//        testQuery3();
//        testUpdate();
//        System.out.println("执行修改后的数据");
//        testQuery3();
//        testDelete();
//        System.out.println("执行删除后的数据");
//        testQuery3();
//        System.out.println("带条件的查询2");
//        testQuery2();
        System.out.println("带条件的查询1");
        testQuery1();
    }


    /**
     * 查询方式一
     */
    public static void testQuery1() {
        Map<String, Object> whereMap = new HashMap<>();
        whereMap.put("activity_id", 10000029);
        try {
            List<Map<String, Object>> result = DBUtil.query("newretail_mkt_activity", whereMap);
            Map<String, Object> res1 = result.get(0);
            Integer status = (Integer)res1.get("status");
            System.out.println(status);

            String activity_name = (String)res1.get("activity_name");
            System.out.println(activity_name);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询方式二
     */
    public static void testQuery2() {
        String where = "job = ?  AND salary = ? ";
        String[] whereArgs = new String[]{"clerk", "3000"};

        try {
            List<Map<String, Object>> list = DBUtil.query("emp_test", where, whereArgs);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询方式三
     */
    public static void testQuery3() {
        try {
            List<Map<String, Object>> list = DBUtil.query("emp_test", false, null,
                    null, null, null, null, null, null);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * SQL注入问题
     */
    public static void query4() {
        String name = "'1' OR '1'='1'";
        String password = "'1' OR '1'='1'";

        String sql = "SELECT * FROM emp_test WHERE name = " + name + " and password = " + password;
        String where = "name = ?  AND password = ? ";
        String[] whereArgs = new String[]{name, password};

        try {
            DBUtil.querySql(sql);
            DBUtil.query("emp_test", false, null, where, whereArgs, null,
                    null, null, null);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void testAll1() {

        long start = System.currentTimeMillis();
        try {
            for (int i = 0; i < 10000; i++) {
                Map<String, Object> map = new HashMap<>();
                map.put("emp_id", 1013);
                map.put("name", "JDBCUtil测试");
                map.put("job", "developer");
                map.put("salary", 10000);
                map.put("hire_date", new java.sql.Date(System.currentTimeMillis()));
                DBUtil.insert("emp_test3", map);
            }
            System.out.println("共耗时" + (System.currentTimeMillis() - start));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //共耗时44110
    }

    public static void testAll2() {
        List<Map<String, Object>> datas = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("emp_id", 1013);
            map.put("name", "JDBCUtil测试");
            map.put("job", "developer");
            map.put("salary", 10000);
            map.put("hire_date", new java.sql.Date(System.currentTimeMillis()));
            datas.add(map);
        }
        try {
            long start = System.currentTimeMillis();
            DBUtil.insertAll("emp_test3", datas);
            System.out.println("共耗时" + (System.currentTimeMillis() - start));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //共耗时649
    }

    /**
     * 测试插入
     */
    private static void testInsert() {
        Map<String, Object> map = new HashMap<>();
        map.put("emp_id", 1013);
        map.put("name", "JDBCUtil测试");
        map.put("job", "developer");
        map.put("salary", 10000);
        map.put("hire_date", new java.sql.Date(System.currentTimeMillis()));
        try {
            int count = DBUtil.insert("emp_test", map);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * 测试更新
     */
    private static void testUpdate() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "测试更新");

        Map<String, Object> whereMap = new HashMap<>();
        whereMap.put("emp_id", "1013");
        try {
            int count = DBUtil.update("emp_test", map, whereMap);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * 测试删除
     */
    private static void testDelete() {
        Map<String, Object> whereMap = new HashMap<>();
        whereMap.put("emp_id", 1013);
        whereMap.put("job", "developer");
        try {
            int count = DBUtil.delete("emp_test", whereMap);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
