package FileIO.Char;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
/**
 * Created by Defias on 2017/3/17.

 java多进程
 Java提供了两种方法用来启动进程：
 1）使用java.lang.Runtime的exec()方法
 2）使用ProcessBuilder的start()方法

 java.lang.Process类
 一个抽象类（大部分的方法均是抽象的），封装了一个进程（即一个执行程序）。提供了执行从进程输入、执行输出到进程、等待进程完成、
 检查进程的退出状态以及销毁（杀掉）进程的方法

 java.lang.ProcessBuilder类
 为进程提供了更多的控制，例如可以设置当前工作目录，还可以改变环境参数。而Process的功能相对来说简单的多

 Process类常用API:
 destroy()   杀掉子进程
 exitValue()  返回子进程的出口值
 InputStream getErrorStream()  获得子进程的错误流
 InputStream getInputStream()  获得子进程的输入流
 OutputStream getOutputStream()  获得子进程的输出流
 waitFor()  导致当前线程等待，如果必要，一直要等到由该Process对象表示的进程已经终止
 */

public class TestIOProcess {
    public static void main(String[] args) throws IOException {
        String cmd = "ifconfig";

        TestProcess_Runtime.readConsole(cmd, true);

        Test_ProcessBuilder.testProcessBuilder();
    }
}


//Runtime
class TestProcess_Runtime {

    //读取控制命令的输出结果
    //cmd 命令
    //isPrettify 返回的结果是否进行美化（换行）
    public static String readConsole(String cmd, Boolean isPrettify) throws IOException {
        StringBuffer cmdout = new StringBuffer();

        System.out.println("执行命令：" + cmd);
        Process process = Runtime.getRuntime().exec(cmd);     //执行一个系统命令
        InputStream fis = process.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));

        String line = null;
        if (isPrettify == null || !isPrettify) {
            while ((line = br.readLine()) != null) {
                cmdout.append(line);
            }
        } else {
            while ((line = br.readLine()) != null) {
                cmdout.append(line).append(System.getProperty("line.separator"));
            }
        }
        System.out.println("执行系统命令后的结果为：\n" + cmdout.toString());
        return cmdout.toString().trim();
    }
}


//ProcessBuilder
class Test_ProcessBuilder {

    public static void testProcessBuilder() throws IOException {
        ProcessBuilder proc = new ProcessBuilder("netstat", "-an", "|", "grep", "apple");
        Process process = proc.start();

        InputStream fis = process.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));

        String line = null;
        StringBuffer cmdout = new StringBuffer();
        while ((line = br.readLine()) != null) {
            cmdout.append(line).append(System.getProperty("line.separator"));
        }
        System.out.println("ProcessBuilder执行系统命令后的结果为：\n");
        System.out.println(cmdout.toString().trim());
    }

}



