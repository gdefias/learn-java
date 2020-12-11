package Exception.FileIOException;

/**
 * Created by Defias on 2020/07.
 * Description: 清理的通用用法
 */

public class Cleanup {
    public static void main(String[] args) {
        try {
            InputFile in = new InputFile("Cleanup.java");  //文件还没打开时发生异常不需要执行dispose
            try {  //文件打开后发生异常都需要执行dispose 因此使用两层嵌套的try
                String s;
                int i = 1;
                while((s = in.getLine()) != null)
                    ; // Perform line-by-line processing here...
            } catch(Exception e) {
                System.out.println("Caught Exception in main");
                e.printStackTrace(System.out);
            } finally {
                in.dispose();
            }
        } catch(Exception e) {
            System.out.println("InputFile construction failed");
        }
    }
}