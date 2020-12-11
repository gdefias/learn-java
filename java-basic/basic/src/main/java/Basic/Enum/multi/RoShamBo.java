package Basic.Enum.multi;
import Basic.Enum.TestUtilEnums;
/**
 * Created by Defias on 2020/07.
 * Description: 石头剪刀布
 *
 */
public class RoShamBo {
    public static <T extends Competitor<T>>
    void match(T a, T b) {
        System.out.println(
                a + " vs. " + b + ": " +  a.compete(b));
    }
    public static <T extends Enum<T> & Competitor<T>>
    void play(Class<T> rsbClass, int size) {
        for(int i = 0; i < size; i++)
            match(
                    TestUtilEnums.random(rsbClass), TestUtilEnums.random(rsbClass));
    }
}
