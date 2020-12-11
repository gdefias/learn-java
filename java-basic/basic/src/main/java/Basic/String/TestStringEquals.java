package Basic.String;

/**
 * Created with IntelliJ IDEA.
 * Description: 字符串 与 ==/equals
 * User: Defias
 * Date: 2019-05
 */

public class TestStringEquals {
    public static void main(String[] args ) {

//        test1();
        test2();
//        test3();
//        test4();
    }

    public static void test1() {
        String str1 = "abc";
        String str2 = "abc";
        String str3 = new String("abc");

        System.out.println(str1 == str2);  //字符串是引用类型，比较的是地址
        System.out.println(str1.equals(str2)); //equals对字符串进行了重写，比较的是值
        System.out.println(str2 == str3);
        System.out.println(str2.equals(str3));
    }

    public static void test2() {
        String str1 = "ab";
        String str2 = "c";
        String str3 = "ab" + "c";
        String str33 = "ab" + "c";
        String str4 = str1 + str2;
        String str5 = "ab" + str2;
        String str55 = str1 + "c";

        System.out.println(str3 == str33);
        System.out.println(str3 == str4);
        System.out.println(str3 == str5);
        System.out.println(str4 == str5);
        System.out.println(str55 == str5);

        String str6 = "ab" +  new String("c");
        System.out.println(str3 == str6);
        System.out.println(str5 == str6);

        String str7 = "ab" +  new String("c");
        String str8 = str1 + str2;
        System.out.println(str7 == str6);
        System.out.println(str4 == str8);
    }

    public static void test3() {
        String str1 = "a1";
        String str2 = "a"+1;
        System.out.println(str1==str2);
    }

    public static void test4() {
        String str1 = "abc";
        final String str2 = "c";
        String str3 = "ab" + str2;
        System.out.println(str1 == str3);
    }
}
