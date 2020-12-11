package I18n;

import java.io.File;

/**
 * Created by Defias on 2017/3/16.
 *
 * 获取资源文件的方法：（Class实例和ClassLoader实例）
 * public InputStream getResourceAsStream(String name)
 * public URL getResource(String name)
 *
 * class.getResource("")  返回的是当前Class这个类所在包开始的位置
 * class.getResource("/") 返回的是classpath的位置
 * class.getClassLoader().getResource("") 返回的是classpath的位置
 * class.getClassLoader().getResource("/") 错误的!!
 *
 * class.getResourceAsStream("")  path不以/开头时默认是从此类所在的包下取资源, 只是通过path构造一个绝对路径，最终还是由ClassLoader获取资源
 * class.getResourceAsStream("/") 以/开头则是从ClassPath根下获取, 只是通过path构造一个绝对路径，最终还是由ClassLoader获取资源
 *
 * class.getClassLoader.getResourceAsStream(String path) 默认则是从ClassPath根下获取，path不能以/开头，最终是由ClassLoader获取资源
 *
 * class.ClassLoader.getSystemResourceAsStream  从用来加载类的搜索路径打开具有指定名称的资源，以读取该资源
 *
 * ServletContext.getResourceAsStream(String path) 默认从WebAPP根目录下取资源，Tomcat下path是否以/开头无所谓，和具体的容器实现有关
 */


public class TestBaseClassGetResource {
    public static void main(String[] args) throws Exception {
        System.out.println(TestBaseClassGetResource.class.getResource(""));
        System.out.println(TestBaseClassGetResource.class.getResource("/"));
        System.out.println(TestBaseClassGetResource.class.getResource("/").getPath());

        System.out.println(TestBaseClassGetResource.class.getClassLoader());
        System.out.println(TestBaseClassGetResource.class.getClassLoader().getResource(""));
        System.out.println(TestBaseClassGetResource.class.getClassLoader().getResource("").getFile());
        System.out.println(TestBaseClassGetResource.class.getClassLoader().getResource("").getPath());
        System.out.println(TestBaseClassGetResource.class.getClassLoader().getResource("").getProtocol());
        System.out.println(TestBaseClassGetResource.class.getClassLoader().getResource("/"));   //null

        System.out.println(Thread.currentThread().getContextClassLoader().getResource(""));
        System.out.println(ClassLoader.getSystemResource(""));
        System.out.println(new File("/").getAbsolutePath());
        System.out.println(System.getProperty("user.dir"));

        //InputStream in = TestBaseClassGetResource.class.getResourceAsStream("readme.md");
        //if(in==null)
        //    System.out.println("123");
        //BufferedReader rn = new BufferedReader(new InputStreamReader(in));
        //System.out.println(rn.readLine());
    }
}
