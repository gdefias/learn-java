package JavaRelease.Java8;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Defias
 * Date: 2019-07
 *
 * Nashorn javascript引擎
 * Java 8提供了一个新的Nashorn javascript引擎，它允许在JVM上运行特定的javascript应用。Nashorn javascript引擎只是javax.script.ScriptEngine另一个
 * 实现，而且规则也一样，允许Java和JavaScript互相操作
 *
 * jjs
 * 基于Nashorn引擎的命令行工具。它接受一些JavaScript源代码为参数，并且执行这些源代码
 */

public class Test11NashornJavascript {

    public static void main(String[] args) throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName( "JavaScript" );

        System.out.println( engine.getClass().getName() );
        System.out.println( "Result:" + engine.eval( "function f() { return 1; }; f() + 1;" ) );
    }
}
