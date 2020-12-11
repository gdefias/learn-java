package JVM.bytecoderun;

import java.io.Serializable;

/**
 * Created by Defias on 2020/09.
 * Description:  静态分派的优先级匹配
 *
 *  没有显示指定静态类型时根据 静态类型的优先级 从而选择 优先的静态类型进行方法分派
 *
 *  优先级顺序为：char>int>long>float>double>Character>Serializable>Object>...
 *  （...为变长参数，将其视为一个数组元素。变长参数的重载优先级最低）
 *  （因为 char 转型到 byte 或 short 的过程是不安全的，所以不会选择参数类型为byte 或 short的方法进行重载）
 *
 *  若是引用类型，则根据 继承关系 进行优先级匹配
 */
public class JavaOverloadAssign2 {

    //因为'a'是一个char类型数据（即静态类型是char），所以会选择参数类型为char的重载方法
    public static void main(String[] args) {
        sayHello('a');
    }

//    private static void sayHello(char arg){
//        System.out.println("hello char");
//    }

    private static void sayHello(Object arg){
        System.out.println("hello Object");
    }

    private static void sayHello(char... arg){
        System.out.println("hello char...");
    }

//    private static void sayHello(int arg){
//        System.out.println("hello int");
//    }
//
//    private static void sayHello(long arg){
//        System.out.println("hello long");
//    }


    private static void sayHello(Character arg){
        System.out.println("hello Character");
    }

    private static void sayHello(Serializable arg){
        System.out.println("hello Serializable");
    }

    private static void sayHello(byte arg){
        System.out.println("hello byte");
    }
}
