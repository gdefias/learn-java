package TypeInfo.jdkdyproxy;

/**
 * Created by Defias on 2020/08.
 * Description:  接口的实现类(委托类)
 */

class RealSubject implements Subject {
    public void request() {
        System.out.println("====RealSubject Request====");
    }
}
