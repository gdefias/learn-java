/**
 * Created by Defias on 2017/3/8.
 *
 * StringTokenizer - 基本已经被抛弃，使用Scanner更灵活
 *
 * 分解字符串
 * StringTokenizer类实现了Enumeration接口
 *
 构造函数：
 StringTokenizer(String str)
 构造一个用来解析str的StringTokenizer对象。java默认的分隔符是“空格”、“制表符（‘\t’）”、“换行符(‘\n’）”、“回车符（‘\r’）”

 StringTokenizer（String str，String delim）
 构造一个用来解析str的StringTokenizer对象，并提供一个指定的分隔符

 StringTokenizer（String str，String delim，boolean returnDelims）
 构造一个用来解析str的StringTokenizer对象，并提供一个指定的分隔符，同时指定是否返回分隔符


 一些常用方法：
 int countTokens（）： 返回nextToken方法被调用的次数
 boolean hasMoreTokens（）： 返回是否还有分隔符
 boolean hasMoreElements（）： 返回是否还有分隔符
 String nextToken（）： 返回从当前位置到下一个分隔符的字符串
 Object nextElement（）： 返回从当前位置到下一个分隔符的字符串
 String nextToken（String delim）： 与4类似，以指定的分隔符返回结果
 *
 */
package Basic.String;


import java.util.*;

public class TestStringTokenizer {
    public static void main(String[] args) {

        test1();
    }

    public static void test1() {
        String s = new String("The Java platform is the ideal platform for network computing");
        StringTokenizer st = new StringTokenizer(s);
        System.out.println("Token Total: " + st.countTokens());
        while (st.hasMoreElements()) {
            System.out.println(st.nextToken());
        }
        System.out.println("----------------------");

        String s1 = new String("The=Java=platform=is=the=ideal=platform=for=network=computing");
        StringTokenizer st1 = new StringTokenizer(s,"=",true);
        System.out.println("Token Total: "+ st1.countTokens());
        while (st1.hasMoreElements()) {
            System.out.println(st1.nextToken());
        }
        System.out.println("----------------------");

        String clientid = "     123567154     ";
        clientid = clientid.trim();
        System.out.println(clientid);

        String str=null;
        str=String.format("Hi,%s", "王力");
        System.out.println(str);
        str=String.format("Hi,%s:%s.%s", "王南","王力","王张");
        System.out.println(str);
        System.out.printf("字母a的大写是：%c %n", 'A');
        System.out.printf("3>7的结果是：%b %n", 3>7);
        System.out.printf("100的一半是：%d %n", 100/2);
        System.out.printf("100的16进制数是：%x %n", 100);
        System.out.printf("100的8进制数是：%o %n", 100);
        System.out.printf("50元的书打8.5折扣是：%f 元%n", 50*0.85);
        System.out.printf("上面价格的16进制数是：%a %n", 50*0.85);
        System.out.printf("上面价格的指数表示：%e %n", 50*0.85);
        System.out.printf("上面价格的指数和浮点数结果的长度较短的是：%g %n", 50*0.85);
        System.out.printf("上面的折扣是%d%% %n", 85);
        System.out.printf("字母A的散列码是：%h %n", 'A');

        str = String.format("{\"t\":\"1\",\"d\":\"%s\",\"sn\":\"%s\",\"src\":\"2\",\"iccid\":\"%s\"}","uid","sn","iccid");
        System.out.println(str);
        byte[] strbytes = str.getBytes();
        System.out.println(strbytes);
        System.out.println("----------------------");

        System.out.println(s.intern());
        System.out.println("======================");

        char[] A = new char[1];
        A[0] = 'a';
        System.out.println(new String(A));
    }
}
