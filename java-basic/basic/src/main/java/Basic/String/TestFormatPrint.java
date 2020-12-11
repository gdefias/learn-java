package Basic.String;
import org.apache.commons.lang3.Conversion;
import java.io.*;
import java.io.PrintStream;
import java.math.BigInteger;
import java.util.Formatter;
/**
 * Created by Defias on 2020/07.
 * Description: 格式化输出
 *
 * System.out.format方法等价于System.out.printf方法
 *
 * java.util.Formatter类：格式化功能，看作是一个翻译器，将格式化字符串与数据翻译成需要的结果
 * 构造Formatter类对象需要传递一个参数告知最终结果将向哪里输出
 *
 * format字符串的格式化参数语法如下：
 * %[argument_index$][flags][width][.precision]conversion
 *
 * argument_index$：argument_index 是一个十进制整数，用于表明参数在参数列表中的位置。第一个参数由 “1$” 引用，第二个参数由 “2$” 引用，依此类推
 * flags：可选 flags 是修改输出格式的字符集。有效标志集取决于转换类型
 * width：控制一个域的最小值，默认情况下下是右对齐的，可以通过使用“-”标志来改变对其方向
 * precision：精度，用于String时，表示输出字符的最大数量，用于浮点数时，表示小数部分要显示出来的 位数（默认是6位），多则舍入，少则补0，用于整数会触发异常。
 *
 * conversion：转换格式，可选的格式有：
 * d 整数型（十进制）
 * c Unicode字符
 * b Boolean值
 * s String
 * f 浮点数（十进制）
 * e 浮点数（科学计数）
 * x或X 整数（十六进制）
 * o  无符号以八进制表示的整数
 * g  把输出的值按照%e或者%f类型中输出长度较小的方式输出
 * h 散列码
 * % 字符串“%”
 *
 * 注意: 当使用b作为转换格式时，即Boolean，对于boolean基本类型或者Boolean对象，其转换结果是对应的true或false。但是对于其他类型
 * 的参数，只要该参数不为null，那么该转换的结果就永远都是true。0也会转换为true的
 *
 * flags:
 * 标志	常规	字符	整数	浮点	日期/时间	说明
 ‘-‘	y	    y	    y	    y	    y	        结果将是左对齐的。
 ‘#’	y1	    –	    y3	    y	    –	        结果应该使用依赖于转换类型的替换形式
 ‘+’	–	    –	    y4	    y	    –	        结果总是包括一个符号
 ‘‘	–	    –	    y4	    y	    –	        对于正值，结果中将包括一个前导空格
 ‘0’	–	    –	    y	    y	    –	        结果将用零来填充
 ‘,’	–	    –	    y2	    y5	    –	        结果将包括特定于语言环境的组分隔符
 ‘(‘	–	    –	    y4	    y5	    –	        结果将是用圆括号括起来的负数

 1 取决于 Formattable 的定义
 2 只适用于 'd' 转换
 3 只适用于 'o'、'x' 和 'X' 转换
 4 对 BigInteger 应用 'd'、'o'、'x' 和 'X' 转换时，或者对 byte 及 Byte、short 及 Short、int 及 Integer、long 及 Long 分别应用 'd' 转换时适用
 5 只适用于 'e'、'E'、'f'、'g' 和 'G' 转换

 \n	换行
 \t	Tab符

 * String.format
 * 一个static方法，接受和Formatter.format()方法一样的参数， 返回字符串对象
 */


public class TestFormatPrint {
    private double total = 0;
    private String name;
    private Formatter f;

    public static void main(String[] args) throws Exception {
        //test1();
        //test2();
        //test3();
        //test4();
        //test5();
        test6(args);
    }

    //format
    public static void test1() {
        int x = 5;
        double y = 5.332542;
        // The old way:
        System.out.println("Row 1: [" + x + " " + y + "]");
        // The new way:
        System.out.format("Row 1: [%d %f]\n", x, y);
        // or
        System.out.printf("Row 1: [%d %f]\n", x, y);

        String formatStr = String.format("%#4X", 12);  //4表示域的长度，#是flags
        System.out.println(formatStr);

    }

    //Formatter类
    public static void test2() {
        PrintStream outAlias = System.out;
        TestFormatPrint tommy = new TestFormatPrint("Tommy", new Formatter(System.out));
        TestFormatPrint terry = new TestFormatPrint("Terry", new Formatter(outAlias));
        tommy.move(0,0);
        terry.move(4,8);
        tommy.move(3,4);
        terry.move(2,5);
        tommy.move(3,3);
        terry.move(3,3);
    }

    public TestFormatPrint(String name, Formatter f) {
        this.name = name;
        this.f = f;
    }

    public void move(int x, int y) {
        f.format("%s The Turtle is at (%d,%d)\n", name, x, y);
    }

    //格式化说明符
    public static void test3() {
        TestFormatPrint receipt = new TestFormatPrint("receipt", new Formatter(System.out));
        receipt.printTitle();
        receipt.print("Jack's Magic Beans", 4, 4.25);
        receipt.print("Princess Peas", 3, 5.1);
        receipt.print("Three Bears Porridge", 1, 14.29);
        receipt.printTotal();
    }

    public void printTitle() {
        f.format("%-15s %5s %10s\n", "Item", "Qty", "Price");  //默认数据右对齐，加-号变为左对齐
        f.format("%-15s %5s %10s\n", "----", "---", "-----");
    }

    public void print(String name, int qty, double price) {
        f.format("%-15.15s %5d %10.2f\n", name, qty, price);
        total += price;
    }

    public void printTotal() {
        f.format("%-15s %5s %10.2f\n", "Tax", "", total*0.06);
        f.format("%-15s %5s %10s\n", "", "", "-----");
        f.format("%-15s %5s %10.2f\n", "Total", "", total * 1.06);
    }


    //Formatter转换
    public static void test4() {
        Formatter f = new Formatter(System.out);

        char u = 'a';
        System.out.println("u = 'a'");
        f.format("s: %s\n", u);
        // f.format("d: %d\n", u);
        f.format("c: %c\n", u);
        f.format("b: %b\n", u);
        // f.format("f: %f\n", u);
        // f.format("e: %e\n", u);
        // f.format("x: %x\n", u);
        f.format("h: %h\n", u);

        int v = 121;
        System.out.println("v = 121");
        f.format("d: %d\n", v);
        f.format("c: %c\n", v);
        f.format("b: %b\n", v);
        f.format("s: %s\n", v);
        // f.format("f: %f\n", v);
        // f.format("e: %e\n", v);
        f.format("x: %x\n", v);
        f.format("h: %h\n", v);

        BigInteger w = new BigInteger("50000000000000");
        System.out.println(
                "w = new BigInteger(\"50000000000000\")");
        f.format("d: %d\n", w);
        // f.format("c: %c\n", w);
        f.format("b: %b\n", w);
        f.format("s: %s\n", w);
        // f.format("f: %f\n", w);
        // f.format("e: %e\n", w);
        f.format("x: %x\n", w);
        f.format("h: %h\n", w);

        double x = 179.543;
        System.out.println("x = 179.543");
        // f.format("d: %d\n", x);
        // f.format("c: %c\n", x);
        f.format("b: %b\n", x);
        f.format("s: %s\n", x);
        f.format("f: %f\n", x);
        f.format("e: %e\n", x);
        // f.format("x: %x\n", x);
        f.format("h: %h\n", x);

        Conversion y = new Conversion();
        System.out.println("y = new Conversion()");
        // f.format("d: %d\n", y);
        // f.format("c: %c\n", y);
        f.format("b: %b\n", y);
        f.format("s: %s\n", y);
        // f.format("f: %f\n", y);
        // f.format("e: %e\n", y);
        // f.format("x: %x\n", y);
        f.format("h: %h\n", y);

        boolean z = false;
        System.out.println("z = false");
        // f.format("d: %d\n", z);
        // f.format("c: %c\n", z);
        f.format("b: %b\n", z);
        f.format("s: %s\n", z);
        // f.format("f: %f\n", z);
        // f.format("e: %e\n", z);
        // f.format("x: %x\n", z);
        f.format("h: %h\n", z);
    }

    //String.format
    public static void test5() {
        try {
            throw new DatabaseException(3, 7, "Write failed");
        } catch(Exception e) {
            System.out.println(e);
        }
    }


    //十六进制转储工具
    public static void test6(String[] args) throws Exception {
        if(args.length == 0)
            // Test by displaying this class file:
            System.out.println(format(BinaryFile.read("base/target/classes/Init/String/TestFormatPrint.class")));
        else
            System.out.println(format(BinaryFile.read(new File(args[0]))));
    }

    public static String format(byte[] data) {
        StringBuilder result = new StringBuilder();
        int n = 0;
        for(byte b : data) {
            if(n % 16 == 0)
                result.append(String.format("%04X: ", n));
            result.append(String.format("%02X ", b));
            n++;
            if(n % 16 == 0) result.append("\n");
        }
        result.append("\n");
        return result.toString();
    }
}

class DatabaseException extends Exception {
    public DatabaseException(int transactionID, int queryID, String message) {

        super(String.format("(t%d, q%d) %s", transactionID, queryID, message));
    }
}