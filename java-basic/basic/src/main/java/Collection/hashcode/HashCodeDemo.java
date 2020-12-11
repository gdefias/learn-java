package Collection.hashcode;

import GUI.Swing.dataExchange.User;

/**
 * Created by Defias on 2020/09.
 * Description:

 如果一个对象覆盖了hashCode方法，仍然想获得它的内存地址计算的Hash值，可以使用java.lang.System类提供的一个静态方法：
 public static native int identityHashCode(Object x);

 */
public class HashCodeDemo {
    public static void main(String[] args) {
        String s1 = "yasin shaw";     //String类覆盖了Object类的hashcode方法
        String s2 = "yasin shaw";
        System.out.println("str1 hashCode: " + s1.hashCode());
        System.out.println("str2 hashCode: " + s2.hashCode());
        System.out.println("str1 identityHashCode: " + System.identityHashCode(s1));
        System.out.println("str2 identityHashCode: " + System.identityHashCode(s2));

        String s3 = new String("yasin shaw");
        String s4 = new String("yasin shaw");
        System.out.println("str1 hashCode: " + s3.hashCode());
        System.out.println("str2 hashCode: " + s4.hashCode());
        System.out.println("str1 identityHashCode: " + System.identityHashCode(s3));
        System.out.println("str2 identityHashCode: " + System.identityHashCode(s4));

        //User类没有覆盖Object类的hashcode方法
        User user = new User("test", "1".toCharArray());
        System.out.println("user hashCode: " + user.hashCode());
        System.out.println("user identityHashCode: " + System.identityHashCode(user));
    }
}
