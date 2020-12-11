package JavaRelease.Java8;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Defias
 * Date: 2019-07
 *
 * 重复注解
 * 允许相同注解在声明使用的时候重复使用超过一次，重复注解本身需要被@Repeatable注解
 *
 * @Repeatable注解
 * @Repeatable(Filters.class)
 * Filters只是一个容器，它持有Filter, 编译器尽力向程序员隐藏它的存在
 *
 * 反射的API提供一个新方法getAnnotationsByType()来返回重复注解的类型
 *
 */




class RepeatingAnnotations {
    @Target( ElementType.TYPE )
    @Retention( RetentionPolicy.RUNTIME )
    public @interface Filters {
        Filter[] value();
    }

    @Target( ElementType.TYPE )
    @Retention( RetentionPolicy.RUNTIME )
    @Repeatable( Filters.class )
    public @interface Filter {
        String value();
    };

    @Filter( "filter1" )
    @Filter( "filter2" )
    public interface Filterable {
    }
}

public class Test4RepeatAnnotations {
    public static void main(String[] args) {
        for( RepeatingAnnotations.Filter filter: RepeatingAnnotations.Filterable.class.getAnnotationsByType( RepeatingAnnotations.Filter.class ) ) {
            System.out.println( filter.value() );
        }
    }
}
