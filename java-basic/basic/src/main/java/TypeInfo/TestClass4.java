package TypeInfo;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Defias on 2017/9/30.
 *
 * 泛化的Class引用
 */

public class TestClass4 {
    public static void main(String[] args) {
        //test1();
        //test2();
        test3();

    }

    //泛化的Class
    public static void test1() throws Exception {
        Class intClass = int.class;
        Class<Integer> genericIntClass = int.class;
        genericIntClass = Integer.class; // Same thing
        intClass = double.class;
        // genericIntClass = double.class; // Illegal 添加了泛型的Class提供了编译器类型检查

        Class<? extends Number> bounded = int.class;
        Number numo = bounded.newInstance();  //这里newInstance返回的确切的Number
        bounded = double.class;
        bounded = Number.class;


        Class<FancyToy> ftClass = FancyToy.class;
        // Produces exact type:
        FancyToy fancyToy = ftClass.newInstance();
        Class<? super FancyToy> up = ftClass.getSuperclass();
        // This won't compile:
        // Class<Toy> up2 = ftClass.getSuperclass();
        // Only produces Object:
        Object obj = up.newInstance();  //这里newInstance返回的又是Object
    }


    public static void test2() {
        FilledList<CountedInteger> fl = new FilledList<CountedInteger>(CountedInteger.class);
        System.out.println(fl.create(15));
    }


    public static void test3() {
        //转型语法cast
        Building b = new House();
        Class<House> houseType = House.class; //Class引用
        House h = houseType.cast(b);   //将对象b转型为Class引用的类型对象
        h = (House)b; // ... or just do this.  //功能同上
    }
}



class CountedInteger {
    private static long counter;
    private final long id = counter++;
    public String toString() {
        return Long.toString(id);
    }
}

class FilledList<T> {
    private Class<T> type;
    public FilledList(Class<T> type) {
        this.type = type;
    }

    public List<T> create(int nElements) {
        List<T> result = new ArrayList<T>();
        try {
            for(int i = 0; i < nElements; i++)
                result.add(type.newInstance());  //泛化Class时newInstance返回的是对象的确切类型而不再是Object
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}


class Building {}
class House extends Building {}