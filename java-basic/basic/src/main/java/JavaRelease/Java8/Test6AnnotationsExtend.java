package JavaRelease.Java8;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Defias
 * Date: 2019-07
 *
 * 注解扩展
 * 扩展了注解可以使用的范围，现在几乎可以在所有的地方：局部变量、泛型、超类和接口实现、甚至是方法的Exception声明
 *
 * Java 8新增加了两个注解的程序元素类型ElementType.TYPE_USE 和ElementType.TYPE_PARAMETER ，这两个新类型描述了可以使用注解的新场合
 * 注解处理API（Annotation Processing API）也做了一些细微的改动，来识别这些新添加的注解类型
 *
 */


public class Test6AnnotationsExtend {
    @Retention( RetentionPolicy.RUNTIME )
    @Target({ElementType.TYPE_USE, ElementType.TYPE_PARAMETER})
    public @interface NonEmpty {
    }

    public static class Holder<@NonEmpty T> extends @NonEmpty Object {
        public void method() throws @NonEmpty Exception {
        }
    }

    @SuppressWarnings( "unused" )
    public static void main(String[] args) {
        final Holder< String > holder = new @NonEmpty Holder< String >();
        @NonEmpty Collection< @NonEmpty String > strings = new ArrayList<>();
    }
}
