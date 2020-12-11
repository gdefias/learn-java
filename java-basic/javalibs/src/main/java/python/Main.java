package python;

/**
 * Created by Defias on 2018/2/26.
 */
public class Main {

    public static void printLog() {
        System.out.println("I am printLog");
    }

    public static String getLog() {
        String result = "I am getLog";
        return result;
    }

    public static void main(String[] args) {
        Main.printLog();
        System.out.println(Main.getLog());
    }
}
