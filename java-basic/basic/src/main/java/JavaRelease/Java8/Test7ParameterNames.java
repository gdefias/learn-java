package JavaRelease.Java8;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Defias
 * Date: 2019-07
 *
 * Java编译器的新特性 - 参数名字
 */

public class Test7ParameterNames {
    public static void main(String[] args) throws Exception {
        Method method = Test7ParameterNames.class.getMethod( "main", String[].class );
        for( final Parameter parameter: method.getParameters() ) {
            System.out.println(parameter.isNamePresent());  //参数名是否可用
            System.out.println( "Parameter: " + parameter.getName() );
        }
    }
}

//编译这个class的时候没有添加参数–parameters，运行的时候得到的结果： Parameter: arg0
//编译的时候添加了–parameters参数的话，运行结果会不一样：Parameter: args