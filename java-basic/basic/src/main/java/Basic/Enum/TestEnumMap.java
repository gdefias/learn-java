package Basic.Enum;

import java.util.EnumMap;
import java.util.Map;

/**
 * Created by Defias on 2020/07.

 枚举映射 - EnumMap

 java.util.EnumMap
 Map接口的实现，其key-value映射中的key是Enum类型，而value则可以是任意类型
 EnumMap内部由数组实现

 public class EnumMap<K extends Enum<K>, V> extends AbstractMap<K, V> implements java.io.Serializable, Cloneable

 API
 构造方法
 EnumMap(Class<K> keyType)  创建一个具有指定键类型的空枚举映射
 EnumMap(EnumMap<K,? extends V> m) 创建一个其键类型与指定枚举映射相同的枚举映射，最初包含相同的映射关系（如果有的话）
 EnumMap(Map<K,? extends V> m) 创建一个枚举映射，从指定映射对其初始化

 */
public class TestEnumMap {

    public static void main(String[] args) {
        //test0();
        test1();
    }


    public static void test0() {
        //映射表主键是日期枚举类型，值是颜色枚举类型
        Map<TestEnumSet.WeekDayEnum, Colorr> schema =
                new EnumMap<TestEnumSet.WeekDayEnum, Colorr>(TestEnumSet.WeekDayEnum.class);

        //将一周的每一天与彩虹的某一种色彩映射起来
        for (int i = 0; i< TestEnumSet.WeekDayEnum.values().length; i++) {
            schema.put(TestEnumSet.WeekDayEnum.values()[i], Colorr.values()[i]);
        }

        System.out.println("What is the lucky color today?");
        System.out.println("It's " + schema.get(TestEnumSet.WeekDayEnum.Mon));
    }

    public static void test1() {
        EnumMap<AlarmPoints,Command> em = new EnumMap<AlarmPoints,Command>(AlarmPoints.class);
        em.put(AlarmPoints.KITCHEN, new Command() {
            public void action() {
                System.out.println("Kitchen fire!");
            }
        });
        em.put(AlarmPoints.BATHROOM, new Command() {
            public void action() {
                System.out.println("Bathroom alert!");
            }
        });

        for(Map.Entry<AlarmPoints,Command> e : em.entrySet()) {
            System.out.print(e.getKey() + ": ");
            e.getValue().action();
        }

        try { // If there's no value for a particular key:
            em.get(AlarmPoints.UTILITY).action();
        } catch(Exception e) {
            System.out.println(e);
        }
    }
}


//命令模式
interface Command {
    void action();
}