package python;

/**
 * Created by Defias on 2018/2/26.
 */
public class HelloWorld {

    public String hello() {
        return "hhh1";
    }

    public String hello(String args) {
        return args;
    }

    public static void main(String[] args) {
        HelloWorld h = new HelloWorld();
        System.out.println(h.hello());
        System.out.println(h.hello("hhh2"));
    }
}
