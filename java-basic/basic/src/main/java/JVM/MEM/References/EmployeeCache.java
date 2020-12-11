package JVM.MEM.References;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Defias on 2020/09.
 * Description:
 *
 * 软引用/弱引用 可以和一个引用队列（ReferenceQueue）联合使用
 * 如果软引用/弱引用所引用的对象被垃圾回收，Java虚拟机就会把这个软引用/弱引用 加入到与之关联的引用队列中
 */
public class EmployeeCache {
    private static EmployeeCache employeecache;

    private Map<String, WEmployee> cache;
    private ReferenceQueue<Employee> queue;
    private static volatile Object lock = new Object();  //同步锁

    public class WEmployee extends WeakReference<Employee> {
        private String key;
        public String getKey() {
            return key;
        }

        public WEmployee(Employee referent, ReferenceQueue<Employee> queue) {
            super(referent, queue);
            this.key = referent.getId();
        }
    }

    private EmployeeCache() {
        this.cache = Collections.synchronizedMap(new HashMap<String, WEmployee>());
        this.queue = new ReferenceQueue<Employee>();
    }

    //单例
    public static EmployeeCache getInstance() {
        if(employeecache == null) {
            synchronized (lock) {
                if(employeecache == null) {
                    employeecache = new EmployeeCache();
                }
            }
        }
        return employeecache;
    }

    //获取对象
    public Employee getEmployee(String id) {
        Employee e = null;
        if(cache.containsKey(id)) {
            e = cache.get(id).get();
        }

        if(null == e){
            e = new Employee(id);
            WEmployee ref = new WEmployee(e, queue);
            cache.put(id, ref);
        }
        return e;
    }

    //添加对象
    public synchronized void addEmployee(Employee employee) {
        cleanCache();

        //缓存对象
        WEmployee ref = new WEmployee(employee, queue);
        cache.put(ref.getKey(), ref);
    }


    //删除已被垃圾回收的键值对
    public void cleanCache() {
        WEmployee se = null;
        while((se = (WEmployee)queue.poll()) != null) {
            // 同时清除该弱引用作为KEY的对象内容
            cache.remove(se.getKey());
            System.out.println("对象ID : " + se.getKey() + "现已从map中删除");
        }
    }


    //缓存大小
    public int getSize() {
        return cache.size();
    }


    //清除缓存
    public void clearCache() {
        cache.clear();
    }
}
