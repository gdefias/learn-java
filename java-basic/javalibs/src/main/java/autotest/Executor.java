package autotest;

/**
 * Created by Defias on 2017/3/16.
 *
 * 自动化测试关键字驱动的原理及实现
 *
 */

import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.lang.reflect.*;

/*
关键字: 张三，去把桌子从A地点搬到B地点
 */
class MoveDesk {
    public void setZhangsan() {
        System.out.println("this is zhangsan");
    }

    public void moveDeskFromA2B(){
        System.out.println("this is test!");
    }

}

/*
对象池
 */
class RegisterCenter {
    public static Map<String, Object> OBJ_POOLS = new HashMap<String, Object>();

    static{
        OBJ_POOLS.put(MoveDesk.class.getName(), new MoveDesk());
    }
}


/*
映射表
 */
class KeywordReflect {
    public static Map<String, Map<String, String>> KEYWORD_POOLS = new HashMap<String, Map<String, String>>();

    static{
        KEYWORD_POOLS.put("张三", KeywordReflect.methodInfo(MoveDesk.class.getName(), "setZhangsan"));
        KEYWORD_POOLS.put("把桌子从A地点搬到B地点", KeywordReflect.methodInfo(MoveDesk.class.getName(), "moveDeskFromA2B"));
    }

    public static Map<String, String> methodInfo(String className, String methodName){
        Map<String, String> methodInfo = new HashMap<String, String>();
        methodInfo.put("class", className);
        methodInfo.put("method", methodName);
        return methodInfo;
    }

}


/*
解析关键字
 */

//正则类
class RegExp {
    public boolean match(String reg, String str) {
        return Pattern.matches(reg, str);
    }

    public List<String> find(String reg, String str) {
        Matcher matcher = Pattern.compile(reg).matcher(str);
        List<String> list = new ArrayList<String>();
        while (matcher.find()) {
            list.add(matcher.group());
        }
        return list;
    }

}


//获取关键字类
class ParseKeyword {

    public List<String> getKeywords(String p){
        String reg = "(?<=(?<!\\\\)\\$\\{)(.*?)(?=(?<!\\\\)\\})";   //正则预查模式
        RegExp re = new RegExp();
        List<String> list = re.find(reg, p);
        return list;
    }

    //public static void main(String[] args) {
    //    ParseKeyword p = new ParseKeyword();
    //    System.out.println(p.getKeywords("a${a}a"));
    //    System.out.println(p.getKeywords("a\\${a}a"));
    //    System.out.println(p.getKeywords("a${a\\}a"));
    //    System.out.println(p.getKeywords("a${a\\}a}a"));
    //    System.out.println(p.getKeywords("a${a}a${"));
    //    System.out.println(p.getKeywords("a${ab}a${a}"));
    //}
}



/*
执行引擎
 */
public class Executor {
    private ParseKeyword pk = new ParseKeyword();

    public List<String> readTxtFile(String filePath) {
        List<String> list = new ArrayList<String>();
        try {
            String encoding = "UTF8";
            File file = new File(filePath);
            if (file.isFile() && file.exists()) {
                InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    list.add(lineTxt);
                }
                read.close();
                bufferedReader.close();
            } else {
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
        return list;
    }

    public void executor(){
        List<String> commands = this.readTxtFile("src/command.txt");
        for (String command : commands) {
            List<String> keywords = pk.getKeywords(command);
            for (String keyword : keywords) {
                this.invoke(keyword);
            }
        }
    }

    public void invoke(String keyword){
        Map<String, String> keyMethod = KeywordReflect.KEYWORD_POOLS.get(keyword);
        String className = keyMethod.get("class");
        String methodName = keyMethod.get("method");
        Object obj = RegisterCenter.OBJ_POOLS.get(className);
        Method method = this.getMethod(methodName, obj);
        try {
            method.invoke(obj);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private Method getMethod(String methodName, Object obj) {
        try {
            Method[] methods = obj.getClass().getMethods();
            for (Method m : methods) {
                if (m.getName().equals(methodName)) {
                    return m;
                }
            }
        } catch (SecurityException e) {
            return null;
        }
        return null;
    }

    public static void main(String[] args) {
        Executor e = new Executor();
        e.executor();
    }
}
