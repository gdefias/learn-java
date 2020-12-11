package Annotation;

/**
 * Created by Defias on 2017/9/6.
 *
 * 功能测试类
 */
public class SonClassA extends BaseClass {
    String name;

    public SonClassA(String name) {
        this.name = name;
    }

    @AutoCall(name="打印")
    public void printName() {
        System.out.println("Hello "+name);
    }

    @AutoCall(name="打电话", tip="给谁打电话呢？")
    public void call(String... str) {
        System.out.print("正在打电话给: ");
        for(String s: str) {
            System.out.print(s+"、");
        }
        System.out.println();
    }

    @AutoCall(name="发短信", tip="给谁发短信呢？")
    public void send(String... str) {
        StringBuilder sb = new StringBuilder();
        boolean flag = true;
        for (String s : str) {
            if (flag)
                flag = false;
            else
                sb.append("、");
            sb.append(s);
        }
        System.out.println("正在发短信给"+sb.toString());
    }
}


