package JVM.MEM.References;
/**
 * Created by Defias on 2020/07.
 * Description:
 */

public class Employee {
    private String id;
    private String name;

    public Employee(String id) {
        this.id = id;
        this.name = String.valueOf(System.currentTimeMillis());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return "id[" + id + "]";
    }
}
