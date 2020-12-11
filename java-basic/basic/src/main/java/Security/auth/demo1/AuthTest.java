package Security.auth.demo1;
import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import java.security.Principal;
import java.security.PrivilegedAction;
import java.util.Set;
/**
 * Created with IntelliJ IDEA.
 * Description:  JAAS认证
 * User: Defias
 * Date: 2018-05

 登录认证


 cd ~/Code/JavaDemo/java-basic/src/main/java

 编译打jar包：
 javac Security/Auth/demo1/*.java
 jar cvf login.jar Security/Auth/demo1/AuthTest.class
 jar cvf action.jar Security/Auth/demo1/SysPropAction.class

 执行：
 java -classpath login.jar:action.jar -Djava.security.policy=Security/Auth/demo1/AuthTest.policy \
 -Djava.security.auth.login.config=Security/Auth/demo1/jaas.config Security.Auth.demo1.AuthTest
 */

public class AuthTest {
    public static void main(final String[] args) {
        System.setSecurityManager(new SecurityManager());  //设置安全管理器
        try {
            LoginContext context = new LoginContext("Login1"); //创建一个登录上下文 name对应于jaas配置文件中的登录描述符
            context.login(); //建立一个登录操作，登录失败抛异常LoginException，会调用jaas配置文件中的管理器上的login方法，登录成功后会调用管理器上commit方法，用来把身份标识关联到主体上
            System.out.println("Authentication successful.");

            //返回认证过的subject
            Subject subject = context.getSubject();
            System.out.println("subject=" + subject);

            //获取该Subject的各个Principal（身份）
            Set<Principal> set = subject.getPrincipals();
            for(Principal p: set) {
                System.out.println(p);
            }

            //要执行的操作action
            PrivilegedAction<String> action = new SysPropAction("user.home");

            //以subject身份执行特定操作，将返回run方法的返回值，doAsPrivileged方法在给定的控制上下文中执行该操作，可以提供一个调用静态方法
            //AccessController.getContext()所获得的上下文快照，或者指定为null，以便使其在一个新的上下中执行
            //相比doAs方法：doAs开始于当前控制上下文，而doAsPrivileged开始于一个新的或给定的控制上下文
            String result = Subject.doAsPrivileged(subject, action, null);

            System.out.println(result);
            context.logout();  //退出登录，会调用jaas配置文件中的管理器上的logout方法
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }
}
