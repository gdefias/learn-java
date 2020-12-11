package Generics;

import Generics.element_generics.Info;
import Generics.element_generics.InfoImp;
import Generics.element_generics.Point;
/**
 * Created by Defias on 2016/2/28.
 *
 * 泛型的基本使用
 */


public class Test0_Generics {

    public static void main(String[] args) {
        //test1();
        test2();
    }

    public static void test1() {
        Point<Integer, Integer> p1 = new Point<Integer, Integer>();
        p1.setX(10);
        p1.setY(20);
        int x = p1.getX();
        int y = p1.getY();
        p1.printPoint(x, y);

        Point<Double, String> p2 = new Point<Double, String>();
        p2.setX(25.0);
        p2.setY("东京180度");
        double m = p2.getX();
        String n = p2.getY();
        p2.printPoint(m, n);

    }


    public static void test2() {
        //实例化泛型接口实现类
        Info<String> obj = new InfoImp<String>("www.weixueyuan.net");
        System.out.println("Length Of String: " + obj.getVar().length());


        InfoImp<String> obj2 = new InfoImp<String>("");
        Integer[] A = new Integer[] {231,22,11,1,4,6,2,8,23,321,100};
        System.out.println(obj2.getMax(A));

        Long[] B = new Long[] {231L,22L,11L,1L,4L,6L,2L,8L,23L,321L,100L};
        System.out.println(obj2.getMax(B));
    }
}


