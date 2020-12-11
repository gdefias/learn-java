package bit;

/**
 * Created by Defias on 2017/6/2.
 */

public class Main {

    public Main() {
        System.out.println("Hello, Welcome this shell.");
    }

    public static void main(String[] args) {
        new Main();

        System.out.println(System.currentTimeMillis());
    }

    public static void printLog() {
        System.out.println("This is a shell log show 2 you. And you will get nothing whit this function return.");
    }

    public static String getLog() {
        String toReturn = "Custom log";
        System.out.println("This is another shell log show 2 you. And you will get a return String.");
        return toReturn;
    }
}