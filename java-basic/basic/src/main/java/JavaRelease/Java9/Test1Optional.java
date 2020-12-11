package JavaRelease.Java9;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Defias
 * Date: 2019-07
 *
 * Java 9 为 Optional 类添加了三个方法：or()、ifPresentOrElse() 和 stream()
 */

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
        return this.name;
    }
}

public class Test1Optional {
    public static void main() {

    }

    ////or: or()方法与orElse()和orElseGet()类似，它们都在对象为空的时候提供了替代情况。or()的返回值是由Supplier参数产生的另一个Optional对象
    //public static void test1() {
    //    User user = null;
    //    User result = Optional.ofNullable(user).or( () -> Optional.of(new User("default","1234"))).get();
    //    System.out.println(result.getEmail());
    //}
    //
    //
    ////ifPresentOrElse: ifPresentOrElse()方法需要两个参数：一个Consumer和一个Runnable。如果对象包含值会执行Consumer的动作，否则运行Runnable
    //public static void test2() {
    //    User user = null;
    //    Optional.ofNullable(user).ifPresentOrElse( u -> System.out.println("User is:" + u.getEmail()),
    //            () -> System.out.println("User not found"));
    //}
    //
    ////Stream: 通过把实例转换为Stream对象，从广大的Stream API中受益。如果没有值它会得到空的Stream；有值的情况下Stream则会包含单一值
    //public static void test3() {
    //    User user = new User("john@gmail.com", "1234");
    //    List<String> emails = Optional.ofNullable(user)
    //            .stream()
    //            .filter(u -> u.getEmail() != null && u.getEmail().contains("@"))
    //            .map( u -> u.getEmail())
    //            .collect(Collectors.toList());
    //
    //    System.out.println(emails.size());
    //    System.out.println(emails.get(0));
    //    System.out.println(user.getEmail());
    //}
}
