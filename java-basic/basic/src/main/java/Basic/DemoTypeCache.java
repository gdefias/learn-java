package Basic;

/**
 * @author: Defias
 * @date: 2020/11/29
 * @description: 缓存 IntegerCache
 *
 */

public class DemoTypeCache {
    public static void main(String[] args) {
        //缓存
        //Integer中有个静态内部类IntegerCache，里面有个cache[],也就是Integer常量池/缓存，常量池的大小为一个字节（-128~127）
        //如果i >= IntegerCache.low && i <= IntegerCache.high则调用IntegerCache.cache[i + (-IntegerCache.low)]
        //如果i的值不满足i >= IntegerCache.low && i <= IntegerCache.high则调用new Integer(i)
        //Java 6后，最大值映射到java.lang.Integer.IntegerCache.high，可以使用JVM的启动参数设置最大值（通过JVM的启动参数
        //-XX:AutoBoxCacheMax=size设置），不设置默认就是127
        Integer a=9527;
        Integer b=9527;
        System.out.println(a==b);   //false

        Integer c=97;
        Integer d=97;
        System.out.println(c==d);     //true
        System.out.println("-------------");

        int i = 10;
        int i1 = 10;
        Integer in1 = 10;
        Integer in2 = 10;
        Integer in3 = new Integer(10);
        Integer in4 = new Integer(10);
        Integer in5 = 199;
        Integer in6 = 199;

        System.out.println(i == i1);		// true
        System.out.println(i == in1);		// true
        System.out.println(i == in2);		// true
        System.out.println(i == in3);		// true

        System.out.println(in1 == in2);		// true
        System.out.println(in5 == in6);		// false

        System.out.println(in1 == in3);		// false

        System.out.println(in3 == in4);		// false



        //除了Integer之外，在其他包装类(Byte、Short、Long、Character)中也存在类似的设计:ByteCache、ShortCache、LongCache、CharacterCache
        //Byte，Short，Long 的缓存池范围默认都是: -128 到 127。可以看出，Byte的所有值都在缓存区中，用它生成的相同值对象都是相等的
        //Character对象也有CharacterCache缓存池，范围是 0 到 127
        //除了Integer可以通过参数改变范围外，其它的都不行

    }
}
