package Collection.generator;
import Generics.element_generics.Generator;
/**
 * Created by Defias on 2020/07.
 * Description: 测试数据生成器
 */

public class TestArrayGenerator {
    public static int size = 10;

    public static void main(String[] args) {
        //test(CountingGenerator.class);

        test(RandomGenerator.class);
    }

    public static void test(Class<?> surroundingClass) {
        for(Class<?> type : surroundingClass.getClasses()) {
            System.out.print(type.getSimpleName() + ": ");
            try {
                Generator<?> g = (Generator<?>)type.newInstance();
                for(int i = 0; i < size; i++)
                    System.out.printf(g.next() + " ");
                System.out.println();
            } catch(Exception e) {
                throw new RuntimeException(e);
            }
        }
    }


}
