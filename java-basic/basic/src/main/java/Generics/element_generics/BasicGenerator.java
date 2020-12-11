package Generics.element_generics;

/**
 * Created by Defias on 2020/07.
 * Description:
 */

//利用泛型得到一个通用的生成器
public class BasicGenerator<T> implements Generator<T> {
    private Class<T> type;
    public BasicGenerator(Class<T> type){
        this.type = type;
    }

    public T next() {
        try {
            return type.newInstance();
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> Generator<T> create(Class<T> type) {
        return new BasicGenerator<T>(type);
    }
}