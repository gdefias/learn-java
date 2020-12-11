package FileIO;
import java.io.*;
/**
 * Created by Defias on 2020/07.
 * Description:  更灵活的策略模式 - 自定义处理文件的策略
 */
public class TestFileDirectory2 {
    public interface Strategy {
        void process(File file);
    }
    private Strategy strategy;  //处理文件的策略
    private String ext;  //用于过滤的后缀


    public static void main(String[] args) {
        new TestFileDirectory2(new TestFileDirectory2.Strategy() {
            public void process(File file) {
                System.out.println(file);
            }
        }, "java").start(args);
    }

    public TestFileDirectory2(Strategy strategy, String ext) {
        this.strategy = strategy;
        this.ext = ext;
    }

    public void start(String[] args) {
        try {
            if(args.length == 0)
                processDirectoryTree(new File("."));
            else
                for(String arg : args) {
                    File fileArg = new File(arg);
                    if(fileArg.isDirectory())
                        processDirectoryTree(fileArg);
                    else {
                        // Allow user to leave off extension:
                        if(!arg.endsWith("." + ext))
                            arg += "." + ext;
                        strategy.process(new File(arg).getCanonicalFile());
                    }
                }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void processDirectoryTree(File root) throws IOException {
        for(File file : TestFileDirectory.walk(root.getAbsolutePath(), ".*\\." + ext))
            strategy.process(file.getCanonicalFile());
    }
}
