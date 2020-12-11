package Annotation;
import java.lang.annotation.*;
import java.lang.reflect.*;
import java.util.*;
/**
 * Created by Defias on 2017/3/16.

 运行时注解框架
 在虚拟机运行程序时使用反射技术搭建的框架

 用运行时注解来搭建框架相对容易而且适用性也比较广，搭建的框架使用起来也比较简单
 对象关系映射（Object Relational Mapping，简称ORM，或O/RM，或O/R mapping）框架通常使用运行时注解来搭建
 */

//运行时注解处理器 - 模拟ORM框架
public class TestAnnotationORM {
    public static void main(String[] args) {
        System.out.println(createTable(Bean.class));
    }

    //返回建表的sql语句
    public static String createTable(Class<?> bean) {
        String tableName = getTableName(bean);
        List<NameAndType> columns = getColumns(bean);
        if (tableName != null && !tableName.equals("") && !columns.isEmpty()) {
            StringBuilder createTableSql = new StringBuilder("create table ");
            //加表名
            createTableSql.append(tableName);
            createTableSql.append("(\n");

            //加表中字段
            for (int i=0; i<columns.size(); i++) {
                NameAndType column = columns.get(i);
                createTableSql.append(column.name);
                createTableSql.append(" ");
                createTableSql.append(column.type);

                //追加下一个字段定义前需要添加逗号
                if (i != columns.size()-1) {
                    createTableSql.append(",\n");
                }
            }
            createTableSql.append("\n);");
            return createTableSql.toString();
        }
        return null;
    }


    //获取表名
    private static String getTableName(Class<?> bean) {
        String name = null;
        if (bean.isAnnotationPresent(Table.class)) {
            //获取Table注解对象
            Table table = bean.getAnnotation(Table.class);
            name = table.name();
        }
        return name;
    }

    //获取字段名与类型
    private static List<NameAndType> getColumns(Class<?> bean) {
        List<NameAndType> columns = new ArrayList<NameAndType>();
        Field[] fields = bean.getDeclaredFields();
        if (fields != null) {
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                if (field.isAnnotationPresent(Column.class)) {
                    //生成sql字段的名
                    Column column = field.getAnnotation(Column.class);
                    String name = column.name();

                    //生成sql字段的类型
                    String type = null;

                    //isAssignableFrom:
                    //判定此Class对象所表示的类或接口与指定的Class参数所表示的类或接口是否相同，或是否是其超类或超接口
                    if (int.class.isAssignableFrom(field.getType())) {
                        type = "integer";
                    } else if (String.class.isAssignableFrom(field.getType())) {
                        type = "text";
                    } else {
                        throw new RuntimeException("unspported type=" + field.getType().getSimpleName());
                    }
                    columns.add(new NameAndType(type, name));
                }

            }
        }
        return columns;
    }
}


//定义运行时注解
@Retention(RetentionPolicy.RUNTIME)
@interface Table {
    String name(); //name用来设置表名
}

@Retention(RetentionPolicy.RUNTIME)
@interface Column {
    String name(); //name用来设置字段名
}


@Table(name="BeanTable")
class Bean {
    @Column(name="field")
    int field;

    @Column(name="description")
    String description;
}


class NameAndType {
    String type;
    String name;

    public NameAndType(String type, String name) {
        this.type = type;
        this.name = name;
    }
}
