package Annotation;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.Random;
/**
 * Created by Defias on 2017/9/6.
 *
 * 微型机器人
 */

public class TestAutoCall {
    static String dj[] = new String[] {
            "你好，有什么我能帮你的吗？",
            "您有什么要我做的吗？",
            "有什么指示吗？",
            "需要我为您做点什么吗？"
    };

    public static void main(String[] args) {
        SonClassA a = new SonClassA("Sxf");
        Random r = new Random();
        String input = null;
        while (true) {
            System.err.println(dj[r.nextInt(4)]);
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            try {
                input = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(input.equals("退出")) {
                System.err.println("已退出，再见！");
                break;
            }

            Method m = a.findMethod(input);
            if(m==null) {
                System.err.println("听不懂你在说啥");
                continue;
            }

            AutoCall autocall = m.getDeclaredAnnotation(AutoCall.class);
            if (!"".equals(autocall.tip())) {
                System.err.println(autocall.tip());
                try {
                    input = br.readLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String[] ss = input.split(" ");

                //注意此处，可变参数列表的调用，必须将数组转成一个Object传进去,否则，自动被认为传了一堆参数
                a.CallFunc(m, (Object)ss);
            } else {
                a.CallFunc(m);
            }
            try {
                Thread.currentThread().sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
