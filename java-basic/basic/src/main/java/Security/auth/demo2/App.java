package Security.auth.demo2;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
/**
 * Created with IntelliJ IDEA.
 * Description:   JAAS认证
 * User: Defias
 * Date: 2018-05
 */

public class App {
    public static void main(String[] args) {
        System.setProperty("java.security.auth.login.config", "/Users/baidu/Code/JavaDemo/java-basic/src/main/java/Security/Auth/demo2/demo.config");
        System.setProperty("java.security.policy", "/Users/baidu/Code/JavaDemo/java-basic/src/main/java/Security/Auth/demo2/demo.policy");
        System.setSecurityManager(new SecurityManager());

        try {
            //创建登录上下文
            LoginContext context = new LoginContext("demo", new DemoCallbackHander());
            //进行登录，登录不成功则系统退出
            context.login();
        } catch (LoginException le) {
            System.err.println("Cannot create LoginContext. " + le.getMessage());
            System.exit(-1);
        } catch (SecurityException se) {
            System.err.println("Cannot create LoginContext. " + se.getMessage());
            System.exit(-1);
        }

        //访问资源
        System.out.println(System.getProperty("java.home"));
    }
}
