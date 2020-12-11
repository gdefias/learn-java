package Stream;

import java.util.Optional;

/**
 * Created by Defias on 2020/07.
 * Description: 使用Optional容器

 */
public class TestOptional0 {

    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
        test5();
    }

    public static void test1() {
        //ofNullable：如果value为null，就new Optional<>()
        Optional<String> fullName1 = Optional.ofNullable(null);
        //! System.out.println(fullName1.get());

        //ofNullable：如果value不为null，就new Optional<>(value)
        Optional<String> fullName2 = Optional.ofNullable("abc");

        //Optional对象的get方法：如果Optional对象的value为null，抛异常NoSuchElementException；如果不为null则返回
        System.out.println(fullName2.get());

        //如果Optional对象有非空的值则方法isPresent()返回true，否则返回false
        System.out.println("Full Name2 is set? " + fullName2.isPresent());

        //方法orElseGet提供了回退机制，当Optional的值为空时接受一个Supplier方法返回默认值
        System.out.println("Full Name2: " + fullName2.orElseGet(() -> "[none]"));
        System.out.println("Full Name1: " + fullName1.orElseGet(() -> "[none]"));

        //map()方法转化Optional当前的值并且返回一个新的Optional实例，接受一个Function方法参数
        //orElse方法和orElseGet类似，但是它不接受一个方法，而是接受一个默认值
        System.out.println(fullName2.map(s -> "Hey Full Name2 " + s + "!").orElse("Hey Stranger!"));
        System.out.println(fullName1.map(s -> "Hey Full Name1 " + s + "!").orElse("Hey Stranger!"));
        System.out.println();
    }

    public static void test2() {
        Integer value1 = null;
        Integer value2 = new Integer(10);

        // Optional.ofNullable - 允许传递为 null 参数
        Optional<Integer> a = Optional.ofNullable(value1);

        // Optional.of - 如果传递的参数是null，抛出异常 NullPointerException
        Optional<Integer> b = Optional.of(value2);
        System.out.println(sum(a,b));
        System.out.println();
    }

    public static Integer sum(Optional<Integer> a, Optional<Integer> b){
        // Optional.isPresent - 判断值是否存在
        System.out.println("第一个参数值存在: " + a.isPresent());
        System.out.println("第二个参数值存在: " + b.isPresent());

        // Optional.orElse - 如果值存在，返回它，否则返回默认值
        Integer value1 = a.orElse(new Integer(0));

        //Optional.get - 获取值，值需要存在
        Integer value2 = b.get();
        return value1 + value2;
    }

    //orElse与orElseGet
    public static void test3() {
        //User user = null;
        User user = new User( "1234");
        System.out.println("Using orElse");
        User result = Optional.ofNullable(user).orElse(createNewUser());
        System.out.println(result);

        System.out.println("Using orElseGet");
        User result2 = Optional.ofNullable(user).orElseGet(() -> createNewUser());
        System.out.println(result2);
        System.out.println();
    }

    private static User createNewUser() {
        System.out.println("Creating New User 5678");
        return new User("5678");
    }

    //orElseThrow 在对象为空的时候抛出异常  接受一个Supplier方法参数
    public static void test4() {
        User user = null;
        //！ User result = Optional.ofNullable(user).orElseThrow( () -> new IllegalArgumentException() );
        User result = Optional.ofNullable(createNewUser()).orElseThrow( () -> new IllegalArgumentException() );
        System.out.println();
    }

    //filter 过滤 filter()接受一个断言参数，返回测试结果为true的值。如果测试结果为false，会返回一个空的Optional
    public static void test5() {
        User user = new User("anna@gmail.com", "1234");
        Optional<User> result = Optional.ofNullable(user).filter(u -> u.getEmail() != null && u.getEmail().contains("@"));
        System.out.println(result.isPresent());
        System.out.println(result);
    }
}


class User {
    private String name;
    private String email=null;

    public User(String name) {
        this.name=name;
    }

    public User(String email, String name) {
        this.email=email;
        this.name=name;
    }

    public String getEmail() {
        return this.email;
    }

    @Override
    public String toString() {
        return this.name+","+this.email;
    }
}
