package JDBC.Pool;
import com.sun.istack.internal.Nullable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
/**
 * Created with IntelliJ IDEA.
 * Description: 数据库JDBC连接工具类
 * User: Defias
 * Date: 2018-06
 *
 */
public class DBUtil {
    
    private static final Pattern sLimitPattern = Pattern.compile("\\s*\\d+\\s*(,\\s*\\d+\\s*)?");


    /**
     * 更改数据库实例
     * */
    private static PreparedStatement changDB(Connection connection, String dbnamne) throws SQLException {
        if(connection==null || dbnamne==null) {
            return null;
        }
        String changdb = String.format("use %s", dbnamne);
        PreparedStatement preparedStatement = connection.prepareStatement(changdb);
        return preparedStatement;
    }


     /** 执行数据库插入操作
     *
     * @param valueMap  插入数据表中key为列名和value为列对应的值的Map对象
     * @param tableName 要插入的数据库的表名
     * @return 影响的行数
     * @throws SQLException SQL异常
     */
    public static int insert(String tableName, Map<String, Object> valueMap) throws SQLException {
        //获取数据库插入的Map的键值对的值
        Set<String> keySet = valueMap.keySet();
        Iterator<String> iterator = keySet.iterator();
        //要插入的字段sql，其实就是用key拼起来的
        StringBuilder columnSql = new StringBuilder();
        //要插入的字段值，其实就是？
        StringBuilder unknownMarkSql = new StringBuilder();

        Object[] bindArgs = new Object[valueMap.size()];
        int i = 0;
        while (iterator.hasNext()) {
            String key = iterator.next();
            columnSql.append(i == 0 ? "" : ",");
            columnSql.append(key);

            unknownMarkSql.append(i == 0 ? "" : ",");
            unknownMarkSql.append("?");
            bindArgs[i] = valueMap.get(key);
            i++;
        }
        //开始拼插入的sql语句
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO ");
        sql.append(tableName);
        sql.append(" (");
        sql.append(columnSql);
        sql.append(" )  VALUES (");
        sql.append(unknownMarkSql);
        sql.append(" )");
        return executeUpdate(sql.toString(), bindArgs);
    }


    /**  执行更新操作
     *
     * @param tableName 表名
     * @param valueMap  要更改的值
     * @param whereMap  条件
     * @return 影响的行数
     * @throws SQLException SQL异常
     */
    public static int update(String tableName, Map<String, Object> valueMap, Map<String, Object> whereMap) 
            throws SQLException {
        //获取数据库插入的Map的键值对的值
        Set<String> keySet = valueMap.keySet();
        Iterator<String> iterator = keySet.iterator();
        //开始拼插入的sql语句
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE ");
        sql.append(tableName);
        sql.append(" SET ");

        //要更改的的字段sql，其实就是用key拼起来的
        StringBuilder columnSql = new StringBuilder();
        int i = 0;
        List<Object> objects = new ArrayList<>();
        while (iterator.hasNext()) {
            String key = iterator.next();
            columnSql.append(i == 0 ? "" : ",");
            columnSql.append(key + " = ? ");
            objects.add(valueMap.get(key));
            i++;
        }
        sql.append(columnSql);

        //更新的条件:要更改的的字段sql，其实就是用key拼起来的
        StringBuilder whereSql = new StringBuilder();
        int j = 0;
        if (whereMap != null && whereMap.size() > 0) {
            whereSql.append(" WHERE ");
            iterator = whereMap.keySet().iterator();
            while (iterator.hasNext()) {
                String key = iterator.next();
                whereSql.append(j == 0 ? "" : " AND ");
                whereSql.append(key + " = ? ");
                objects.add(whereMap.get(key));
                j++;
            }
            sql.append(whereSql);
        }
        return executeUpdate(sql.toString(), objects.toArray());
    }

    /**  执行删除操作
     *
     * @param tableName 要删除的表名
     * @param whereMap  删除的条件
     * @return 影响的行数
     * @throws SQLException SQL执行异常
     */
    public static int delete(String tableName, Map<String, Object> whereMap) throws SQLException {
        //准备删除的sql语句
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM ");
        sql.append(tableName);

        //更新的条件:要更改的的字段sql，其实就是用key拼起来的
        StringBuilder whereSql = new StringBuilder();
        Object[] bindArgs = null;
        if (whereMap != null && whereMap.size() > 0) {
            bindArgs = new Object[whereMap.size()];
            whereSql.append(" WHERE ");
            //获取数据库插入的Map的键值对的值
            Set<String> keySet = whereMap.keySet();
            Iterator<String> iterator = keySet.iterator();
            int i = 0;
            while (iterator.hasNext()) {
                String key = iterator.next();
                whereSql.append(i == 0 ? "" : " AND ");
                whereSql.append(key + " = ? ");
                bindArgs[i] = whereMap.get(key);
                i++;
            }
            sql.append(whereSql);
        }
        return executeUpdate(sql.toString(), bindArgs);
    }


    /**  可以执行新增，修改，删除
     *
     * @param sql      sql语句
     * @param bindArgs 绑定参数
     * @return 影响的行数
     * @throws SQLException SQL异常
     */
    public static int executeUpdate(String sql, Object[] bindArgs) throws SQLException {
        //影响的行数
        int affectRowCount = -1;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            //从数据库连接池中获取数据库连接
            connection = DBc3p0Pool.getInstance().getConnection();
            //执行SQL预编译
            preparedStatement = connection.prepareStatement(sql.toString());
            //设置不自动提交，以便于在出现异常的时候数据库回滚
            connection.setAutoCommit(false);
            System.out.println(getExecSQL(sql, bindArgs));
            if (bindArgs != null) {
                //绑定参数设置sql占位符中的值
                for (int i = 0; i < bindArgs.length; i++) {
                    preparedStatement.setObject(i + 1, bindArgs[i]);
                }
            }
            //执行sql
            affectRowCount = preparedStatement.executeUpdate();
            connection.commit();
            String operate;
            if (sql.toUpperCase().indexOf("DELETE FROM") != -1) {
                operate = "删除";
            } else if (sql.toUpperCase().indexOf("INSERT INTO") != -1) {
                operate = "新增";
            } else {
                operate = "修改";
            }
            System.out.println("成功" + operate + "了" + affectRowCount + "行");
            System.out.println();
        } catch (Exception e) {
            if (connection != null) {
                connection.rollback();
            }
            e.printStackTrace();
            throw e;
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return affectRowCount;
    }


    /**  执行数据库插入操作-多行
     *
     * @param datas     插入数据表中key为列名和value为列对应的值的Map对象的List集合
     * @param tableName 要插入的数据库的表名
     * @return 影响的行数
     * @throws SQLException SQL异常
     */
    public static int insertAll(String tableName, List<Map<String, Object>> datas) throws SQLException {
        //影响的行数
        int affectRowCount = -1;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            //从数据库连接池中获取数据库连接
            connection = DBc3p0Pool.getInstance().getConnection();


            Map<String, Object> valueMap = datas.get(0);
            //获取数据库插入的Map的键值对的值
            Set<String> keySet = valueMap.keySet();
            Iterator<String> iterator = keySet.iterator();
            //要插入的字段sql，其实就是用key拼起来的
            StringBuilder columnSql = new StringBuilder();
            //要插入的字段值，其实就是？
            StringBuilder unknownMarkSql = new StringBuilder();

            Object[] keys = new Object[valueMap.size()];
            int i = 0;
            while (iterator.hasNext()) {
                String key = iterator.next();
                keys[i] = key;
                columnSql.append(i == 0 ? "" : ",");
                columnSql.append(key);

                unknownMarkSql.append(i == 0 ? "" : ",");
                unknownMarkSql.append("?");
                i++;
            }
            //开始拼插入的sql语句
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO ");
            sql.append(tableName);
            sql.append(" (");
            sql.append(columnSql);
            sql.append(" )  VALUES (");
            sql.append(unknownMarkSql);
            sql.append(" )");

            //执行SQL预编译
            preparedStatement = connection.prepareStatement(sql.toString());
            //设置不自动提交，以便于在出现异常的时候数据库回滚
            connection.setAutoCommit(false);
            System.out.println(sql.toString());

            for (int j = 0; j < datas.size(); j++) {
                for (int k = 0; k < keys.length; k++) {
                    preparedStatement.setObject(k + 1, datas.get(j).get(keys[k]));
                }
                preparedStatement.addBatch();
            }
            int[] arr = preparedStatement.executeBatch();
            connection.commit();
            affectRowCount = arr.length;
            System.out.println("成功了插入了" + affectRowCount + "行");
            System.out.println();
        } catch (Exception e) {
            if (connection != null) {
                connection.rollback();
            }
            e.printStackTrace();
            throw e;
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return affectRowCount;
    }



    /**  通过sql查询数据,
     * 慎用，会有sql注入问题
     *
     * @param sql
     * @return 查询的数据集合
     * @throws SQLException
     */
    public static List<Map<String, Object>> querySql(String sql) throws SQLException {
        return executeQuery(sql, null);
    }


    /**  执行sql通过 Map<String, Object>限定查询条件查询
     *
     * @param tableName 表名
     * @param whereMap  where条件
     * @return List<Map<String, Object>>
     * @throws SQLException
     */
    public static List<Map<String, Object>> query(String tableName,
                                                  Map<String, Object> whereMap) throws Exception {
        String whereClause = "";
        Object[] whereArgs = null;
        if (whereMap != null && whereMap.size() > 0) {
            Iterator<String> iterator = whereMap.keySet().iterator();
            whereArgs = new Object[whereMap.size()];
            int i = 0;
            while (iterator.hasNext()) {
                String key = iterator.next();
                whereClause += (i == 0 ? "" : " AND ");
                whereClause += (key + " = ? ");
                whereArgs[i] = whereMap.get(key);
                i++;
            }
        }
        return query(tableName, false, null, whereClause, whereArgs, null, null, null, null);
    }


    /**  执行sql条件参数绑定形式的查询
     *
     * @param tableName   表名
     * @param whereClause where条件的sql
     * @param whereArgs   where条件中占位符中的值
     * @return List<Map<String, Object>>
     * @throws SQLException
     */
    public static List<Map<String, Object>> query(String tableName,
                                                  String whereClause,
                                                  String[] whereArgs) throws SQLException {
        return query(tableName, false, null, whereClause, whereArgs, null, null, null, null);
    }


    /**  执行全部结构的sql查询
     *
     * @param tableName     表名
     * @param distinct      去重
     * @param columns       要查询的列名
     * @param selection     where条件
     * @param selectionArgs where条件中占位符中的值
     * @param groupBy       分组
     * @param having        筛选
     * @param orderBy       排序
     * @param limit         分页
     * @return List<Map<String, Object>>
     * @throws SQLException
     */
    public static List<Map<String, Object>> query(String tableName,
                                                  boolean distinct,
                                                  String[] columns,
                                                  String selection,
                                                  Object[] selectionArgs,
                                                  String groupBy,
                                                  String having,
                                                  String orderBy,
                                                  String limit) throws SQLException {
        String sql = buildQueryString(distinct, tableName, columns, selection, groupBy, having, orderBy, limit);
        return executeQuery(sql, selectionArgs);

    }


    /**  执行查询
     *
     * @param sql      要执行的sql语句
     * @param bindArgs 绑定的参数
     * @return List<Map<String, Object>>结果集对象
     * @throws SQLException SQL执行异常
     */
    public static List<Map<String, Object>> executeQuery(String sql, Object[] bindArgs) throws SQLException {
        List<Map<String, Object>> datas = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            //获取数据库连接池中的连接
            connection = DBc3p0Pool.getInstance().getConnection();
            //changDB(connection, "dingwanying_waimai_commodity");
            preparedStatement = connection.prepareStatement(sql);
            if (bindArgs != null) {
                //设置sql占位符中的值
                for (int i = 0; i < bindArgs.length; i++) {
                    preparedStatement.setObject(i + 1, bindArgs[i]);
                }
            }
            System.out.println(getExecSQL(sql, bindArgs));
            //执行sql语句，获取结果集
            resultSet = preparedStatement.executeQuery();
            datas = getDatas(resultSet);
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return datas;
    }




    /**  将结果集对象封装成List<Map<String, Object>> 对象
     *
     * @param resultSet 结果多想
     * @return 结果的封装
     * @throws SQLException
     */
    private static List<Map<String, Object>> getDatas(ResultSet resultSet) throws SQLException {
        List<Map<String, Object>> datas = new ArrayList<>();
        //获取结果集的数据结构对象
        ResultSetMetaData metaData = resultSet.getMetaData();
        while (resultSet.next()) {
            Map<String, Object> rowMap = new HashMap<>();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                rowMap.put(metaData.getColumnName(i), resultSet.getObject(i));
            }
            datas.add(rowMap);
        }
        System.out.println("成功查询到了" + datas.size() + "行数据");
        for (int i = 0; i < datas.size(); i++) {
            Map<String, Object> map = datas.get(i);
            System.out.println("第" + (i + 1) + "行：" + map);
        }
        return datas;
    }



    private static String buildQueryString(
            boolean distinct, String tables, String[] columns, String where,
            String groupBy, String having, String orderBy, String limit) {
        if (isEmpty(groupBy) && !isEmpty(having)) {
            throw new IllegalArgumentException(
                    "HAVING clauses are only permitted when using a groupBy clause");
        }
        if (!isEmpty(limit) && !sLimitPattern.matcher(limit).matches()) {
            throw new IllegalArgumentException("invalid LIMIT clauses:" + limit);
        }

        StringBuilder query = new StringBuilder(120);

        query.append("SELECT ");
        if (distinct) {
            query.append("DISTINCT ");
        }
        if (columns != null && columns.length != 0) {
            appendColumns(query, columns);
        } else {
            query.append(" * ");
        }
        query.append("FROM ");
        query.append(tables);
        appendClause(query, " WHERE ", where);
        appendClause(query, " GROUP BY ", groupBy);
        appendClause(query, " HAVING ", having);
        appendClause(query, " ORDER BY ", orderBy);
        appendClause(query, " LIMIT ", limit);
        return query.toString();
    }


    private static void appendColumns(StringBuilder s, String[] columns) {
        int n = columns.length;

        for (int i = 0; i < n; i++) {
            String column = columns[i];

            if (column != null) {
                if (i > 0) {
                    s.append(", ");
                }
                s.append(column);
            }
        }
        s.append(' ');
    }


    private static void appendClause(StringBuilder s, String name, String clause) {
        if (!isEmpty(clause)) {
            s.append(name);
            s.append(clause);
        }
    }


    private static boolean isEmpty(@Nullable CharSequence str) {
        if (str == null || str.length() == 0)
            return true;
        else
            return false;
    }


    private static String getExecSQL(String sql, Object[] bindArgs) {
        StringBuilder sb = new StringBuilder(sql);
        if (bindArgs != null && bindArgs.length > 0) {
            int index = 0;
            for (int i = 0; i < bindArgs.length; i++) {
                index = sb.indexOf("?", index);
                sb.replace(index, index + 1, String.valueOf(bindArgs[i]));
            }
        }
        return sb.toString();
    }
}
