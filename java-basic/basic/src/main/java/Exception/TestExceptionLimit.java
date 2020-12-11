package Exception;

/**
 * Created by Defias on 2020/07.
 * Description: 异常限制 - 异常与继承体系（深入异常说明）
 *
 * 在覆盖方法的时候，只能抛出在基类方法的异常说明里列出的那些异常，也可以不抛任何异常
 *
 * 异常说明本身并不属于方法类型的一部分，所以不能基于异常说明来重载方法
 * 在继承和覆盖体系中，某个特定方法的异常说明不是变大而是变小了（因为派生类可以不抛基类异常说明中的异常），与类接口在继承时的情形相反
 *
 */

public class TestExceptionLimit extends Inning implements Storm {

    public static void main(String[] args) {
        try {
            TestExceptionLimit si = new TestExceptionLimit();
            si.atBat();
        } catch(PopFoul e) {       //需要捕获TestExceptionLimit类的各异常
            System.out.println("Pop foul");
        } catch(RainedOut e) {
            System.out.println("Rained out");
        } catch(BaseballException e) {
            System.out.println("Generic baseball exception");
        }

        // Strike not thrown in derived version.
        try {
            // What happens if you upcast?
            Inning i = new TestExceptionLimit();
            i.atBat();
            // You must catch the exceptions from the
            // base-class version of the method:
        } catch(Strike e) {               //向上转型后，需要捕获基类Inning的各异常
            System.out.println("Strike");
        } catch(Foul e) {
            System.out.println("Foul");
        } catch(RainedOut e) {
            System.out.println("Rained out");
        } catch(BaseballException e) {
            System.out.println("Generic baseball exception");
        }
    }

    //派生类构造器的异常说明必须包含基类构造器的异常说明
    public TestExceptionLimit() throws RainedOut, BaseballException {}
    public TestExceptionLimit(String s) throws Foul, BaseballException {}

    //派生类需要遵守基类方法的异常说明，基类方法walk没有抛异常，而这里派生类中walk抛异常了，那么编译器就不让通过了
    // Regular methods must conform to base class:
//! void walk() throws PopFoul {} //Compile error

    // Interface CANNOT add exceptions to existing
    // methods from the base class:
//! public void event() throws RainedOut {} //event方法同时存在于继承的类和实现的接口中

    // You can choose to not throw any exceptions,
    // even if the base version does:
    public void event()  {}  //派生类可以不抛任何异常（如果要抛异常一定要抛基类方法中异常说明中的异常或其派生类，否则编译错误）

    // If the method doesn't already exist in the
    // base class, the exception is OK:
    public void rainHard() throws RainedOut  {}



    // Overridden methods can throw inherited exceptions:
    public void atBat() throws PopFoul {}  //抛的PopFoul异常时基类异常说明中的Foul异常的派生类，所以这是没问题的

}


class BaseballException extends Exception {}
class Foul extends BaseballException {}
class Strike extends BaseballException {}

abstract class Inning {
    public Inning() throws BaseballException {}
    public void event() throws BaseballException {
        // Doesn't actually have to throw anything
    }
    public abstract void atBat() throws Strike, Foul;
    public void walk() {} // Throws no checked exceptions
}


class StormException extends Exception {}
class RainedOut extends StormException {}
class PopFoul extends Foul {}

interface Storm {
    public void event() throws RainedOut;
    public void rainHard() throws RainedOut;
}