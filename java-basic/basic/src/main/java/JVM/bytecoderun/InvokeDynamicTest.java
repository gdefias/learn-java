package JVM.bytecoderun;

import jdk.internal.dynalink.support.Lookup;
import java.lang.invoke.CallSite;
import java.lang.invoke.ConstantCallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;
import static java.lang.invoke.MethodHandles.lookup;
/**
 * Created by Defias on 2020/09.
 * Description: invokedynamic指令演示
 */

public class InvokeDynamicTest {
    public static void main(String[]args)throws Throwable{
        INDY_BootstrapMethod().invokeExact("icyfenix");
    }

    public static void testMethod(String s){
        System.out.println("hello String:"+s);
    }

    public static CallSite BootstrapMethod(Lookup lookup, String name, MethodType mt)throws Throwable {
        System.out.println("333333333");
        return new ConstantCallSite(lookup.findStatic(InvokeDynamicTest.class,name,mt));
    }

    private static MethodType MT_BootstrapMethod() {
        System.out.println("222222222");
        return MethodType
                .fromMethodDescriptorString(
                        "(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;",
                        null);
    }

    private static MethodHandle MH_BootstrapMethod()throws Throwable {
        System.out.println("111111");
        return lookup().findStatic(InvokeDynamicTest.class, "BootstrapMethod", MT_BootstrapMethod());
    }

    private static MethodHandle INDY_BootstrapMethod() throws Throwable{
        CallSite cs = (CallSite)MH_BootstrapMethod().invokeWithArguments(lookup(), "testMethod",
                MethodType.fromMethodDescriptorString("(Ljava/lang/String;)V", null));
        return cs.dynamicInvoker();
    }
}


