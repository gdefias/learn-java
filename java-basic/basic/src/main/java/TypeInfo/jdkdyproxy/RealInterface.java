package TypeInfo.jdkdyproxy;
import static Basic.Print.print;

/**
 * Created by Defias on 2020/08.
 * Description: 真实实现类
 */
class RealInterface implements Interface {
    public void doSomething() {
        print("doSomething");
    }

    public void somethingElse(String arg) {
        print("somethingElse " + arg);
    }
}