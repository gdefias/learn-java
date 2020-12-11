package JavaRelease.Java8;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import static java.util.Comparator.comparingInt;
import static java.util.Comparator.comparing;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Defias
 * Date: 2019-07
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
class Userr {
    private String name;
    private Integer age;
    private Integer credits;
}


public class Test15Sort {

    public static void main(String[] args) {
        List<Userr> users = new ArrayList<>();
        users.add( new Userr("jack",17,10));
        users.add( new Userr("jack",18,10));
        users.add( new Userr("jack",19,11));
        users.add( new Userr("apple",25,15));
        users.add( new Userr("tommy",23,8));
        users.add( new Userr("jessica",15,13));

        //对年龄从小到大排序
        users.sort((o1, o2) -> o1.getAge() - o2.getAge());
        users.forEach(user -> System.out.println(user));
        System.out.println();

        users.sort(comparingInt(Userr::getAge));
        users.forEach(System.out::println);
        System.out.println();

        //对年龄从大到小排序(反向排序)
        users.sort((o1, o2) -> o2.getAge() - o1.getAge());
        users.forEach(user -> System.out.println(user));
        System.out.println();

        users.sort(comparingInt(Userr::getAge).reversed());
        System.out.println();

        //按照姓名，年龄与积分的顺序依次排序，也就是多条件组合排序
        users.sort(comparing(Userr::getName)
                .thenComparing(Userr::getAge)
                .thenComparing(Userr::getCredits));
        users.forEach(System.out::println);
    }

}
