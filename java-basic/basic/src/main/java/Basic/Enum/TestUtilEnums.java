package Basic.Enum;
import java.util.*;
/**
 * Created by Defias on 2020/07.
 * Description: 枚举随机选取工具类
 */


public class TestUtilEnums {
    private static Random rand = new Random(47);

    public static <T extends Enum<T>> T random(Class<T> ec) {
        return random(ec.getEnumConstants());
    }
    public static <T> T random(T[] values) {
        return values[rand.nextInt(values.length)];
    }
}
