package Basic;
import java.io.PrintStream;

/**
 * Created by Defias on 2020/07.
 * Description:  打印类
 */
public class Print {
    // Print with a newline:
    public static void print(String obj) {
        System.out.println(obj);
    }

    public static void print(Boolean obj) {
        System.out.println(obj.toString());
    }

    public static void print(int obj) {
        System.out.println(obj);
    }

    // Print a newline by itself:
    public static void print() {
        System.out.println();
    }
    // Print with no line break:
    public static void printnb(String obj) {
        System.out.print(obj);
    }
    // The new Java SE5 printf() (from C):
    public static PrintStream
    printf(String format, String... args) {
        return System.out.printf(format, args);
    }
}
