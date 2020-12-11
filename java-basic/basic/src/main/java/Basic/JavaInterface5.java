package Basic;

/**
 * Created by Defias on 2020/07.
 * Description: 嵌套接口
 */

public class JavaInterface5 {

    public static void main(String[] args) {
        A a = new A();
        // Can't access A.D:
        //! A.D ad = a.getD();

        // Doesn't return anything but A.D:
        //! A.DImp2 di2 = a.getD();

        // Cannot access a member of the interface:  直接使用 private接口的引用 肯定是失败的
        //! a.getD().f();

        // Only another A can do anything with getD():  将private接口的引用交给有权使用它的对象才能成功
        a.receiveD(a.getD());
        A a2 = new A();
        a2.receiveD(a.getD());
    }
}


class A {
    interface B {  //包访问权限接口
        void f();
    }
    public class BImp implements B {
        public void f() {}
    }
    private class BImp2 implements B {
        public void f() {}
    }


    public interface C {  //public接口
        void f();
    }
    class CImp implements C {
        public void f() {}
    }
    private class CImp2 implements C {
        public void f() {}
    }


    private interface D {  //private接口
        void f();
    }

    private class DImp implements D {  //被实现为private内部类
        public void f() {}
    }

    public class DImp2 implements D {  //也可被实现为public内部类，但是只能被其自身所使用
        public void f() {}
    }


    public D getD() { //虽然自己是public的，但返回的是一个private接口的引用
        return new DImp2();
    }

    private D dRef;

    public void receiveD(D d) {
        dRef = d;
        dRef.f();
    }
}

//接口彼此之间也可以嵌套
interface E {
     interface G {  //嵌套在另一个接口中的接口自动就是public的，而不能声明为private
        void f();
    }

    // Redundant "public":
    public interface H {
        void f();
    }

    void g();
    // Cannot be private within an interface:
    //! private interface I {}
}

class NestingInterfaces {
    public class BImp implements A.B {
        public void f() {}
    }
    class CImp implements A.C {
        public void f() {}
    }
    // Cannot implement a private interface except
    // within that interface's defining class:
    //! class DImp implements A.D {   //private接口不能在定义它的类之外被实现
    //!  public void f() {}
    //! }
    class EImp implements E {  //当实现某个接口时，可以不实现嵌套在其内部的任何接口
        public void g() {}
    }
    class EGImp implements E.G {
        public void f() {}
    }
    class EImp2 implements E {
        public void g() {}
        class EG implements E.G {
            public void f() {}
        }
    }
}