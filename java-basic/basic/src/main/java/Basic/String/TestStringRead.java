package Basic.String;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;
import java.util.regex.MatchResult;

/**
 * Created by Defias on 2020/07.
 * Description:  扫描输入
 *
 */

public class TestStringRead {
    public static BufferedReader input = new BufferedReader(
            new StringReader("Sir Robin of Camelot\n22 1.61803"));

    public static String threatData =
            "58.27.82.161@02/10/2005\n" +
            "204.45.234.40@02/11/2005\n" +
            "58.27.82.161@02/11/2005\n" +
            "58.27.82.161@02/12/2005\n" +
            "58.27.82.161@02/12/2005\n" +
            "[Next log section with different data format]";

    public static void main(String[] args) {
        //test1();
        //test2();
        //test3();
        test4();
    }

    public static void test1() {
        try {
            System.out.println("What is your name?");
            String name = input.readLine();
            System.out.println(name);
            System.out.println("How old are you? What is your favorite double?");
            System.out.println("(input: <age> <double>)");
            String numbers = input.readLine();
            System.out.println(numbers);

            String[] numArray = numbers.split(" ");
            int age = Integer.parseInt(numArray[0]);  //解析String为int
            double favorite = Double.parseDouble(numArray[1]);  //解析String为double
            System.out.format("Hi %s.\n", name);
            System.out.format("In 5 years you will be %d.\n", age + 5);
            System.out.format("My favorite double is %f.", favorite / 2);
        } catch(IOException e) {
            System.err.println("I/O exception");
        }
    }

    //更好的方法 - 使用Scanner
    public static void test2() {
        Scanner stdin = new Scanner(input);  //Scanner默认根据空白定界符对输入进行分词
        System.out.println("What is your name?");
        String name = stdin.nextLine();
        System.out.println(name);
        System.out.println(
                "How old are you? What is your favorite double?");
        System.out.println("(input: <age> <double>)");
        int age = stdin.nextInt();  //解析String为int被隐藏在各种next方法中
        double favorite = stdin.nextDouble();
        System.out.println(age);
        System.out.println(favorite);
        System.out.format("Hi %s.\n", name);
        System.out.format("In 5 years you will be %d.\n", age + 5);
        System.out.format("My favorite double is %f.", favorite / 2);
    }


    public static void test3() {
        Scanner scanner = new Scanner("12, 42, 78, 99, 42");
        scanner.useDelimiter("\\s*,\\s*");  //用正则表达式指定定界符进行分词
        while(scanner.hasNextInt())
            System.out.println(scanner.nextInt());
    }


    //使用自定义的正则表达式扫描（扫描复杂数据时非常有用）
    public static void test4() {
        Scanner scanner = new Scanner(threatData);
        String pattern = "(\\d+[.]\\d+[.]\\d+[.]\\d+)@" +
                "(\\d{2}/\\d{2}/\\d{4})";
        while(scanner.hasNext(pattern)) {
            scanner.next(pattern);
            MatchResult match = scanner.match();
            String ip = match.group(1);
            String date = match.group(2);
            System.out.format("Threat on %s from %s\n", date,ip);
        }
    }
}
