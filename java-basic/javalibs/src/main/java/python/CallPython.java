package python;

/**
 * Created by Defias on 2018/2/26.
 *
 * 调用python
 * 需要增加模块依赖：jython安装目录下的jython.jar， 不要改变jython.jar的路径(jython2.7.0/Lib、jython2.7.0/Lib/site-packages)
 *
 * https://www.programcreek.com/java-api-examples/index.php?api=org.python.util.PythonInterpreter
 */
//
//import java.io.IOException;
//import java.io.FileInputStream;
//import java.io.InputStream;
//import org.python.util.PythonInterpreter;
//import org.python.core.PySystemState;
//import org.python.core.Py;
//
//
//public class CallPython {
//    public static void main(String args[]) throws IOException {
//        PythonInterpreter interpreter = new PythonInterpreter();
//        PySystemState sys = Py.getSystemState();
//        sys.path.add("/Users/yzh/Code/JavaDemo/UtilJava/src/main/resources/");
//        interpreter.exec("import sys");
//        interpreter.exec("print sys.path");
//        interpreter.exec("path = \"/Users/yzh/Code/JavaDemo/UtilJava/src/main/resources\"");
//        interpreter.exec("sys.path.append(path)");
//        interpreter.exec("print sys.path");
//        interpreter.exec("a=3; b=5;");
//        InputStream filepy = new FileInputStream("/Users/yzh/Code/JavaDemo/UtilJava/src/main/resources/test.py");
//        interpreter.execfile(filepy);
//        filepy.close();
//    }
//}
