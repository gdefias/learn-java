package FileIO.Char;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Defias on 2020/07.
 * Description:  进程控制
 *
 * 执行控制台命令行  --java.lang.ProcessBuilder
 *
 */


public class TestIOProcess2 {
    public static class OSExecute {
        public static void main(String[] args) {
            OSExecute.command("ls -l");
        }


        public static void command(String command) {
            boolean err = false;
            try {
                Process process = new ProcessBuilder(command.split(" ")).start();
                BufferedReader results = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String s;
                while((s = results.readLine())!= null)
                    System.out.println(s);

                BufferedReader errors = new BufferedReader(
                        new InputStreamReader(process.getErrorStream()));

                while((s = errors.readLine())!= null) {
                    System.err.println(s);
                    err = true;
                }
            } catch(Exception e) {
                throw new RuntimeException(e);
            }
            if(err)
                throw new OSExecuteException("Errors executing " + command);
        }
    }

    public static class OSExecuteException extends RuntimeException {
        public OSExecuteException(String why) {
            super(why);
        }
    }
}


