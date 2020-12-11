package Assertj;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Defias
 * Date: 2018-05
 *
 * https://github.com/joel-costigliola/assertj-examples
 */

import org.junit.Test;
import java.util.*;
import java.lang.annotation.*;

import static org.junit.Assert.assertEquals;
import static org.assertj.core.api.Assertions.assertThat;  // main one
import static org.assertj.core.api.Assertions.atIndex; // for List assertions
import static org.assertj.core.api.Assertions.entry;  // for Map assertions
import static org.assertj.core.api.Assertions.tuple; // when extracting several properties at once
import static org.assertj.core.api.Assertions.fail; // use when writing exception tests
import static org.assertj.core.api.Assertions.failBecauseExceptionWasNotThrown; // idem
import static org.assertj.core.api.Assertions.filter; // for Iterable/Array assertions
import static org.assertj.core.api.Assertions.offset; // for floating number assertions
import static org.assertj.core.api.Assertions.anyOf; // use with Condition
import static org.assertj.core.api.Assertions.contentOf; // use with File assertions
import org.assertj.core.internal.Dates;


public class testAssertjTest {
    //junit的断言
    @Test
    public void testadd() throws Exception {
        assertEquals(5, testAssertj.add(2,3));
    }

    //对字符串断言
    @Test
    public void testString() throws Exception  {
        System.out.println("sssssssss");
        String str = null;
        //断言null或为空字符串
        assertThat(str).isNullOrEmpty();

        //断言空字符串
        assertThat("").isEmpty();

        //断言字符串相等 忽略大小写
        assertThat("Frodo").isEqualTo("Frodo").isEqualToIgnoringCase("frodo");

        //断言开始字符串 结束字符串 字符串长度
        assertThat("Frodo").startsWith("Fro").endsWith("do").hasSize(5);

        //断言包含字符串 不包含字符串
        assertThat("Frodo").contains("rod").doesNotContain("fro");

        //断言字符串只出现过一次
        assertThat("Frodo").containsOnlyOnce("do");


        //判断正则匹配
        assertThat("Frodo").matches("..o.o").doesNotMatch(".*d");
    }

    //对数字断言
    @Test
    public void testNumber() {
        Integer num = null;

        //断言空
        assertThat(num).isNull();

        //断言相等
        assertThat(42).isEqualTo(42);

        //断言大于 大于等于
        assertThat(42).isGreaterThan(38).isGreaterThanOrEqualTo(38);

        //断言小于 小于等于
        assertThat(42).isLessThan(58).isLessThanOrEqualTo(58);

        //断言0
        assertThat(0).isZero();

        //断言正数 非负数
        assertThat(1).isPositive().isNotNegative();

        //断言负数 非正数
        assertThat(-1).isNegative().isNotPositive();
    }


    //对List断言
    @Test
    public void testList() {
        ArrayList<Integer> arraylist = new ArrayList<Integer>();
        arraylist.add(1);
        arraylist.add(2);
        arraylist.add(3);

        //断言列表是非空的
        assertThat(arraylist).isNotEmpty();

        //断言列表size大小
        assertThat(arraylist).hasSize(2);

        //断言列表的开始 结束元素
        assertThat(arraylist).startsWith(1).endsWith(3);

        //断言列表包含元素 并且是排序的
        assertThat(arraylist).contains(1, atIndex(0)).contains(2, atIndex(1)).contains(3).isSorted();

        //断言被包含与给定列表
        ArrayList<Integer> arraylist2 = new ArrayList<Integer>();
        arraylist2.add(1);
        arraylist2.add(2);
        arraylist2.add(3);
        arraylist2.add(4);
        assertThat(arraylist).isSubsetOf(arraylist2);

        //断言存在唯一元素
        assertThat(arraylist).containsOnlyOnce(1);
    }

    //对Map断言
    @Test
    public void testMap() {
        Map<String, Object> foo = new HashMap<String, Object>();
        foo.put("A", 1);
        foo.put("B", 2);
        foo.put("C", 3);

        //断言map不为空size
        assertThat(foo).isNotEmpty().hasSize(3);
        //断言map包含元素
        assertThat(foo).contains(entry("A", 1), entry("B", 2));
        //断言map包含key
        assertThat(foo).containsKeys("A", "B", "C");
        //断言map包含value
        assertThat(foo).containsValue(3);
    }

    //对Class断言
    @Test
    public void testClass() {
        //断言是注解
        assertThat(Magical.class).isAnnotation();
        //断言不是注解
        assertThat(Ring.class).isNotAnnotation();
        //断言存在注解
        assertThat(Ring.class).hasAnnotation(Magical.class);
        //断言不是接口
        assertThat(Ring.class).isNotInterface();
        //断言是否为指定Class实例
        assertThat("string").isInstanceOf(String.class);
        //断言类是给定类的父类
        assertThat(Person.class).isAssignableFrom(Employee.class);
    }

    @Magical
    public enum Ring {
        oneRing, vilya, nenya, narya, dwarfRing, manRing;
    }

    @Target(ElementType.TYPE)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Magical {}
    public class Person {}
    public class Employee extends Person {}

    //使用fail方法
    @Test
    public void testFail() {
        try {
            fail("在不检查任何条件的情况下使断言失败。显示一则消息");
        } catch (AssertionError ae) {
            System.out.println("可以通过catch捕获该Error");
        }
        try {
            failBecauseExceptionWasNotThrown(Exception.class);
        } catch (AssertionError ae) {
            System.out.println("可以通过catch捕获该Error");
        }
    }


    @Test
    public void testAssertJ() {
        A a1 = new A();
        A a2 = new A();

        a1.str = "hello";
        a2.str = "hello";

        a1.list.add("world");
        a2.list.add("world");

        B b1 = new B();
        B b2 = new B();

        b1.test = "b1";
        b2.test = "b1";

        b1.aB.list = a1.list;
        b1.aB.str = a1.str;

        b2.aB.list = a2.list;
        b2.aB.str = a2.str;


        assertThat(a1).isEqualToComparingFieldByField(a2);

        //对象类型 只比较引用
        // assertThat(b1).isEqualToComparingFieldByField(b2);

        //含有嵌式比较，含有对象类型的字段，再比较对象内的数据
        assertThat(b1).isEqualToComparingFieldByFieldRecursively(b2);

    }
}
