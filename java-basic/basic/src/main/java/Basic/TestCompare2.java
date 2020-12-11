/**
 * Created by Defias on 2017/2/23.
 *
 * 比较
 * 区分compareTo和compare方法
 *
 */
package Basic;
import java.util.*;

class User implements Comparable<Object>{
    int id;
    String name;

    public User(int id,String name){
        this.id = id;
        this.name = name;
    }
    /*
     * Getters and Setters
    */
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Object o) {
        if(this == o){
            return 0;
        }
        else if (o!=null && o instanceof User) {
            User u = (User) o;
            if(id<=u.id){
                return -1;
            } else {
                return 1;
            }
        } else {
            return -1;
        }
    }
}


public class TestCompare2 {
    //比较器
    private static final Comparator<User> COMPARATOR = new Comparator<User>() {
        public int compare(User o1, User o2) {
            return o1.compareTo(o2);  //用User类的compareTo方法比较两个对象
        }
    };

    public static void main(String[] args) {
        ArrayList<User> student = new ArrayList<User>();
        User user1 = new User(10,"yueliming");
        User user2 = new User(2,"yueliming");
        student.add(user1);
        student.add(user2);

        Collections.sort(student, COMPARATOR);   //用写好的Comparator对student进行排序
        for(int i=0;i<student.size();i++){
            System.out.println(student.get(i).getId());
        }
    }

}
