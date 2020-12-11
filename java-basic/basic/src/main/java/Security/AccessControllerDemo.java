package Security;

import java.io.*;
import java.security.AccessControlException;
import java.security.AccessController;

/**
 * Created by Defias on 2020/08.
 * Description: 访问控制器  ---AccessController

 java命令加入参数：
 -Djava.security.manager -Djava.security.policy=/Users/yzh/Code/JavaDemo/java-basic/base/src/main/java/Security/custom.policy

 */

public class AccessControllerDemo {
    public static void main(String[] args) throws IOException {
        FilePermission permission = new FilePermission("base/src/test.txt", "read");
        try {
            AccessController.checkPermission(permission);  //模拟启用了安全管理器

            FileReader fr = new FileReader(new File("base/src/test.txt"));
            BufferedReader reader = new BufferedReader(fr);
            String s;
            while ((s = reader.readLine()) != null) {
                System.out.println(s);
            }
        } catch (AccessControlException e) {
            System.out.println("没有访问的权限");
        }
    }
}
