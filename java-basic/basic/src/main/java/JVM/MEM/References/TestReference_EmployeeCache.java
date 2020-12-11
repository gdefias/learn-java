package JVM.MEM.References;

/**
 * Created by Defias on 2017/8/27.
 *
 * 通过 软引用/弱引用 实现内存敏感的对象缓存
 *
 * 重新获取那些尚未被回收的Java对象的引用，必将减少不必要的访问，大大提高程序的运行速度
 *
 */

public class TestReference_EmployeeCache {

    public static void main(String[] args) throws InterruptedException {
        EmployeeCache ecache = EmployeeCache.getInstance();

        for (int i = 0; i <= 60000; i++) {
            Employee e = new Employee(String.valueOf(i));
            ecache.addEmployee(e);
        }

        Employee e0 = ecache.getEmployee(String.valueOf(1));
        System.out.println(e0);


        //缓存敏感
        String id = String.valueOf(1);
        Employee e1 = ecache.getEmployee(id);
        e1.setName("xxx");
        ecache.addEmployee(e1);
        System.out.println(ecache.getEmployee(id).getName());
    }
}







