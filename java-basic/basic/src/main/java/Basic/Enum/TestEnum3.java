package Basic.Enum;

/**
 * Created by Defias on 2020/07.
 * Description: enum类
 *
 * 除了不能继承enum外，enum就是一个常规的有少许限制的类
 */

public enum TestEnum3 {
    WEST("Miss Gulch, aka the Wicked Witch of the West"),
    NORTH("Glinda, the Good Witch of the North"),
    EAST("Wicked Witch of the East, wearer of the Ruby " +
                 "Slippers, crushed by Dorothy's house"),
    SOUTH("Good by inference, but missing");  //分号

    private String description;
    //枚举的构造器必须是private的
    private TestEnum3(String description) {
        this.description = description;
    }

    //enum中添加方法
    public String getDescription() {
        return description;
    }

    //将枚举名首字母大写其他小写
    public String toString() {
        String id = name();
        String lower = id.substring(1).toLowerCase();
        return id.charAt(0) + lower;
    }

    public static void main(String[] args) {
        for(TestEnum3 witch : TestEnum3.values())
            System.out.println(witch + ": " + witch.getDescription());

        System.out.println("----------------");
        for(TestEnum3 s : values()) {
            System.out.println(s);
        }
    }
}
