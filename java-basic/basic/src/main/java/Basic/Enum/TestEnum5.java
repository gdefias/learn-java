package Basic.Enum;

/**
 * Created by Defias on 2020/07.
 * Description:  使用接口组织枚举
 *
 */
public class TestEnum5 {
    public static void main(String[] args) {
        //testTypeOfFood();
        testMeal();
        //testSecurityCategory();
    }

    public static void testTypeOfFood() {
        //使用接口组织枚举 将枚举向上转型为Food
        Food food = Food.Appetizer.SALAD;
        food = Food.MainCourse.LASAGNE;
        food = Food.Dessert.GELATO;
        food = Food.Coffee.CAPPUCCINO;
    }


    //菜单
    public static void testMeal() {
        for(int i = 0; i < 5; i++) {
            for(Course course : Course.values()) {
                Food food = course.randomSelection();
                System.out.println(food);
            }
            System.out.println("---");
        }
    }


    public static void testSecurityCategory() {
        for(int i = 0; i < 10; i++) {
            SecurityCategory category =
                    TestUtilEnums.random(SecurityCategory.class);

            System.out.println(category + ": " +
                    category.randomSelection());
        }
    }
}


//enum嵌套enum
enum SecurityCategory {
    STOCK(Security.Stock.class), BOND(Security.Bond.class);

    Security[] interenum;

    SecurityCategory(Class<? extends Security> kind) {
        interenum = kind.getEnumConstants();
    }

    interface Security {
        enum Stock implements Security { SHORT, LONG, MARGIN }
        enum Bond implements Security { MUNICIPAL, JUNK }
    }

    public Security randomSelection() {
        return TestUtilEnums.random(interenum);
    }
}