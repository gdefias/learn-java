package Security.auth.demo1;
import java.security.PrivilegedAction;
/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Defias
 * Date: 2018-05
 */


public class SysPropAction implements PrivilegedAction<String> {
    private String propertyName;

    public SysPropAction(String propertyName) {
        this.propertyName = propertyName;
    }

    public String run() {  //执行你想要主体去执行的操作
        return System.getProperty(propertyName);
    }
}
