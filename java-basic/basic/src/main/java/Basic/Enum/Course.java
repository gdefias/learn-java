package Basic.Enum;

/**
 * Created by Defias on 2020/07.
 * Description: 相当于枚举的枚举
 *
 */

public enum Course {
    APPETIZER(Food.Appetizer.class),
    MAINCOURSE(Food.MainCourse.class),
    DESSERT(Food.Dessert.class),
    COFFEE(Food.Coffee.class);

    private Food[] interenum;

    private Course(Class<? extends Food> kind) {

        //Class.getEnumConstants()  获取枚举类型的实例
        //这个方法的本质是通过反射调用values方法获取枚举的实例
        interenum = kind.getEnumConstants();
    }

    public Food randomSelection() {
        return TestUtilEnums.random(interenum);
    }
}
