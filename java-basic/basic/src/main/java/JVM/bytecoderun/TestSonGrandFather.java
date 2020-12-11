package JVM.bytecoderun;
import static java.lang.invoke.MethodHandles.lookup;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;
/**
 * Created by Defias on 2020/09.
 * Description:
 */
public class TestSonGrandFather {

    public static void main(String[] args) {
        (new TestSonGrandFather().new Son()).thinking();
    }

    class GrandFather{
        void thinking(){
            System.out.println("i am grandfather");
        }
    }

    class Father extends GrandFather{
        void thinking(){
            System.out.println("i am father");
        }
    }

    class Son extends Father {
        void thinking() {
            //填入适当的代码(不能修改其他地方的代码)
            //实现调用祖父类的thinking()方法, 打印"i am grandfather"
            //使用MethodHandle来解决相关问题?????压根儿没解决
            try {
                MethodType mt = MethodType.methodType(void.class);
                MethodHandle mh = lookup().findSpecial(GrandFather.class, "thinking", mt, getClass());
                mh.invoke(this);
            } catch (Throwable e) {
            }
        }
    }
}
