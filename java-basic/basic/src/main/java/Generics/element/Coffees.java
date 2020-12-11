package Generics.element;

/**
 * Created by Defias on 2020/07.
 * Description:
 */

public class Coffees {

    //咖啡基类
    public static class Coffee {
        private static long counter = 0;
        private final long id = counter++;
        public String toString() {
            System.out.println("Coffee id: " + id);
            return getClass().getSimpleName() + "-" + id;
        }
    }

    //各种咖啡
    public static class Americano extends Coffee {}
    public static class Breve extends Coffee {}
    public static class Cappuccino extends Coffee {}
    public static class Mocha extends Coffee {}
    public static class Latte extends Coffee {}

}

