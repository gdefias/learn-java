package Basic;

/**
 * Created by Defias on 2017/10/9.
 *
 * 可变长参数
 *
 * 1、一个方法只能有一个可变长参数，并且这个可变长参数必须是该方法的最后一个参数
 * 2、在调用方法的时候，如果能够和固定参数的方法匹配，也能够与可变长参数的方法匹配，则选择固定参数的方法
 * 3、如果要调用的方法可以和两个可变参数匹配，则出现错误
 */


public class JavaVarArgs {
    public static void m1(String s, String... ss) {
        System.out.println(s);
        for (int i = 0; i < ss.length; i++) {
            System.out.println(ss[i]);
        }
    }

    public static void main(String[] args) {
        //m1("");
        //m1("aaa");
        //m1("aaa", "bbb");
        //m1("aaa", "bbb", "ccc");
        String[] A = {"111", "222", "333"};
        m1("aaa", A);
    }
}
