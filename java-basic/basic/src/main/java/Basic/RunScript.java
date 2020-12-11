package Basic;

/**
 * Created by Defias on 2017/9/5.
 */
import javax.script.*;
import java.io.*;

public class RunScript {
    public static void main(String[] args) throws Exception {
        String script = args[0];
        String file = args[1];

        FileReader scriptReader = new FileReader(new File(file));
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName(script);
        engine.eval(scriptReader);
    }
}
