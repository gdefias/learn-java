package Security;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Defias on 2020/08.
 * Description: 安全管理器 --SecurityManager


 FileInputStream中的源码：
    public FileInputStream(File file) throws FileNotFoundException {
        String name = (file != null ? file.getPath() : null);
        SecurityManager security = System.getSecurityManager();   //安全管理器
        if (security != null) {
            security.checkRead(name);
        }
        if (name == null) {
            throw new NullPointerException();
        }
        if (file.isInvalid()) {
            throw new FileNotFoundException("Invalid file path");
        }
        fd = new FileDescriptor();
        fd.attach(this);
        path = name;
        open(name);
    }
 */

public class SecurityManagerDemo {
    public static void main(String[] args) throws IOException {
        //设置为应用程序的安全管理器
        System.setSecurityManager(new SecurityManagerImpl());

        FileReader fr = new FileReader(new File("base/src/test.txt"));
        BufferedReader reader = new BufferedReader(fr);

        String s;
        while ((s = reader.readLine()) != null) {
            System.out.println(s);
        }
    }
}


//自定义一个安全管理器  --继承SecurityManager
class SecurityManagerImpl extends SecurityManager {
    public void CheckRead(String file) {
        throw new SecurityException();
    }


}

