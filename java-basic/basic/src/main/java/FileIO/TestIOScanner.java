package FileIO;

/**
 * Created by Defias on 2017/2/24.
 *
 * Scanner类

 Java SE5引入了Scanner类来简化扫描输入的负担
 Scanner类可以以 File 对象、InputStream、String对象和Readable对象作为数据源
 定义好了数据源，就要对数据源进行扫描输入，Scanner中有两类扫描方法，并且各自都有对应各种数据类型的读取方法。分别是 hasNextXxx() 方法
 和 nextXxx() 方法，前者判断是否存在，后者获取相应数据

 Scanner类从文件中读取文本数据
 Scanner将输入分为由空白字符分隔的标记
 Scanner类似一个容器，将输入流放进去，然后通过nextXXX()，就可以通过特定的输入流中获取到数据，这个输入流不仅可以是控制台输入System.in，
 也可以是文件流


 java.util.Scanner
 ----------------------------------------------------------
 +Scanner(Bytecode: File)   创建一个Scanner，从指定的文件扫描标记
 +Scanner(Bytecode: String)  创建一个Scanner，从指定的字符串中扫描标记
 +close() 关闭该Scanner
 +hasNext(): boolean   如果Scanner还有更多的数据读取，返货true
 +next(): String        从该Scanner中读取下一个标记作为字符串返回（不是一行）
 +nextByte(): byte      从该Scanner中读取下一个标记作为byte值返回
 +nextShort(): short    从该Scanner中读取下一个标记作为short值返回
 +nextInt(): int    从该Scanner中读取下一个标记作为int值返回
 +nextLong(): long      从该Scanner中读取下一个标记作为long值返回
 +nextFloat(): float    从该Scanner中读取下一个标记作为float值返回
 +nextDouble(): double      从该Scanner中读取下一个标记作为double值返回
 +useDelimiter(pattern: String): Scanner    设置该Scanner的分割符，并返回该Scanner
 +nextLine(): String    从该Scanner中读取一行，以换行结束

 boolean hasNext(Pattern pattern)   下一段文本与模式匹配，则返回 true
 boolean hasNext(String pattern)    如果下一个文本与指定字符串构造的模式匹配，则返回 true
 boolean hasNextBigDecimal()    如果下一段文本可以解释一个 BigDecimal，则返回 true
 boolean hasNextBigInteger()    如果下一段文本可以解释为一个 BigInteger 值，则返回 true
 boolean hasNextLine()  如果在此扫描器的输入中存在另一行，则返回 true
 String next()  查找并返回来自此扫描器的下一个完整标记
 String next(Pattern pattern)   如果下一个标记与指定模式匹配，则返回下一个标记
 String next(String pattern)    如果下一个标记与从指定字符串构造的模式匹配，则返回下一个标记
 BigDecimal nextBigDecimal()    将输入信息的下一个标记扫描为一个 BigDecimal
 BigInteger nextBigInteger()    将输入信息的下一个标记扫描为一个 BigInteger
 */
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.MatchResult;

public class TestIOScanner {
    static String data = "58.24.21.52@02/10/2018\n"+
            "58.24.21.52@02/10/2018\n"+
            "58.24.21.52@02/10/2018\n"+
            "58.24.21.52@02/10/2018\n"+
            "58.24.21.52@02/10/2018\n";


    public static void main(String[] args) throws Exception {
        //test1();
        //test2();
        test3();
    }


    public static void test1() {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter Numbers1: ");
        double number1 = input.nextDouble();
        System.out.print("Enter Numbers2: ");
        double number2 = input.nextDouble();
        System.out.print("Enter Numbers3: ");
        double number3 = input.nextDouble();

        double avg = (number1 + number2 + number3) / 3;
        System.out.println("avg is " + avg);

        System.out.print("Enter One String: ");
        //String str1 = input.nextLine();  //读取以回车结束的字符串
        String str1 = input.next();  //读取以空白字符结束的字符串
        System.out.println(str1);
    }

    public static void test2() throws Exception {
        //获取特定平台上的行分割符
        //String lineSeparator = System.getProperty("line.separator");
        //System.out.println("line.separator: " + lineSeparator);  //windows平台是\r\n  unix平台是\n

        // Create a File instance
        File file = new File("base/src/scores.txt");
        if (!file.exists()) {
            System.out.println("File not exists");
            System.exit(0);
        }

        //// Create a Scanner for the file
        //Scanner input = new Scanner(file);
        //
        //// Read data from a file
        //while (input.hasNext()) {
        //    String firstName = input.next();
        //    String mi = input.next();
        //    String lastName = input.next();
        //    int score = input.nextInt();
        //    System.out.println(
        //            firstName + " " + mi + " " + lastName + " " + score);
        //}
        //
        //// Close the file
        //input.close();

        //使用try-with-resources语法
        try (Scanner input = new Scanner(file)) {
            while (input.hasNext()) {
                String firstName = input.next();
                String mi = input.next();
                String lastName = input.next();
                int score = input.nextInt();
                System.out.println(firstName + " " + mi + " " + lastName + " #" + score);
            }
        }

    }

    public static void test3() {
        Scanner sc = new Scanner(data);
        String pattern = "(\\d+[.]\\d+[.]\\d+[.]\\d+)@(\\d{2}/\\d{2}/\\d{4})";

        while (sc.hasNext(pattern)) {
            sc.next(pattern);
            MatchResult match = sc.match();
            String ip = match.group(1);
            String date = match.group(2);

            System.out.format("Threat on %s from %s\n", date, ip);
        }
    }

    public static void test4() {
        //从web上读取数据(web爬虫)
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a URL: ");
        String url = input.nextLine();
        crawler(url); // Traverse the Web from the a starting url
    }

    //爬虫
    public static void crawler(String startingURL) {
        ArrayList<String> listOfPendingURLs = new ArrayList<>();  //保存将被遍历的url
        ArrayList<String> listOfTraversedURLs = new ArrayList<>();   //保存已经被遍历过的url

        listOfPendingURLs.add(startingURL);
        while (!listOfPendingURLs.isEmpty() && listOfTraversedURLs.size() <= 100) {  //遍历100个页面后退出
            String urlString = listOfPendingURLs.remove(0);
            if (!listOfTraversedURLs.contains(urlString)) {
                listOfTraversedURLs.add(urlString);
                System.out.println("Craw " + urlString);

                for (String s : getSubURLs(urlString)) {
                    if (!listOfTraversedURLs.contains(s))
                        listOfPendingURLs.add(s);
                }
            }
        }
    }

    //从web页面中读取每行并且寻找该行中的url
    public static ArrayList<String> getSubURLs(String urlString) {
        ArrayList<String> list = new ArrayList<>();

        try {
            URL url = new URL(urlString);
            Scanner input = new Scanner(url.openStream());  //从url打开输入流（出错时报异常：java.net.MalformedURLException）  从输入流中读取数据
            int current = 0;
            while (input.hasNext()) {
                String line = input.nextLine();
                current = line.indexOf("http:", current);
                while (current > 0) {
                    int endIndex = line.indexOf("\"", current);   //出于简化，假设一个URL以引号"结束
                    if (endIndex > 0) { // Ensure that a correct URL is found
                        list.add(line.substring(current, endIndex));
                        current = line.indexOf("http:", endIndex);
                    } else {
                        current = -1;  //该行中没有发现url
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return list;
    }
}
