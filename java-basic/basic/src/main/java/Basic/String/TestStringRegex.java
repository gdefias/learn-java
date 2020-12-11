/**
 * Created by Defias on 2016/3/5.
 *
 *
 * 正则表达式工具（对象）
 *
 * Pattern
 * java.util.regex.Pattern：模式类：字符串要被匹配的这么一个模式，该模式本身已经被编译过，使用的话效率要高很多
 *
 * public final class Pattern extends Object implements Serializable
 *
 * 正则表达式的编译表示形式
 * 指定为字符串的正则表达式必须首先被编译为此类的实例。然后，可将得到的模式用于创建 Matcher 对象，依照正则表达式，该对象可以与任意
 * 字符序列匹配。执行匹配所涉及的所有状态都驻留在匹配器中，所以多个匹配器可以共享同一模式
 *
 * 构造器：
 * static Pattern	compile(String regex)   将给定的正则表达式编译到模式中
 * static Pattern	compile(String regex, int flags) 将给定的正则表达式编译到具有给定标志的模式中，多个flag使用|连接起来
 *
 * API：
 * int	flags()   返回此模式的匹配标志
 * Matcher	matcher(CharSequence input)  创建匹配给定输入与此模式的匹配器
 * static boolean	matches(String regex, CharSequence input)  编译给定正则表达式并尝试将给定输入与其匹配
 * String	pattern()  返回在其中编译过此模式的正则表达式
 * static String	quote(String s)  返回指定 String 的字面值模式 String
 * String[]	split(CharSequence input)  围绕此模式的匹配拆分给定输入序列
 * String[]	split(CharSequence input, int limit)  围绕此模式的匹配拆分给定输入序列
 * String	toString()  返回此模式的字符串表示形式
 *
 * flags：
 * static int	CANON_EQ        启用规范等价
 * static int	CASE_INSENSITIVE    启用不区分大小写的匹配
 * static int	COMMENTS        模式中允许空白和注释
 * static int	DOTALL      启用 dotall 模式
 * static int	LITERAL     启用模式的字面值解析
 * static int	MULTILINE       启用多行模式
 * static int	UNICODE_CASE    启用 Unicode 感知的大小写折叠
 * static int	UNIX_LINES      启用 Unix 行模式
 *
 *
 *
 * Matcher
 * java.util.regex.Matcher：匹配类：这个模式匹配某个字符串所产生的结果，这个结果可能会有很多个
 * 通过解释 Pattern 对 character sequence 执行匹配操作的引擎
 *
 * public final class Matcher extends Object implements MatchResult
 *
 * 通过调用模式的matcher方法从模式创建匹配器。创建匹配器后，可以使用它执行三种不同的匹配操作：
 * boolean	matches()   尝试将整个输入序列与该模式匹配 （永远从整个字符串的开头位置开始匹配 且 整个输入都匹配才成功）
 * boolean	lookingAt()  尝试将输入序列从头开始与该模式匹配  （永远从整个字符串的开头位置开始匹配 且 只要输入的第一部分匹配就成功）
 * boolean	find()  尝试查找与该模式匹配的输入序列的下一个子序列（匹配子字符串，可以再输入字符串的任意位置匹配）
 * boolean	find(int start)  重置此匹配器，然后尝试查找匹配该模式、从指定索引开始的输入序列的下一个子序列
 *
 *
 * int	start()     返回以前匹配的初始索引
 * int	start(int group)    返回在以前的匹配操作期间，由给定组所捕获的子序列的初始索引
 * int	end()   返回最后匹配字符之后的偏移量
 * int	end(int group)  返回在以前的匹配操作期间，由给定组所捕获子序列的最后字符之后的偏移量
 *
 * String	group() 返回由以前匹配操作所匹配的输入子序列
 * String	group(int group)    返回在以前匹配操作期间由给定组捕获的输入子序列
 * int	groupCount()    返回此匹配器模式中的捕获组数
 *
 * Matcher	reset() 重置匹配器
 * Matcher	reset(CharSequence input)  重置此具有新输入序列的匹配器
 */
package Basic.String;
import java.util.regex.*;

//正则表达式
public class TestStringRegex {

    public static void main(String[] args) throws Exception {
//        test0();
//        test1();
//        test2();
//        test3();
//        test4();
        //test5();
        test6("@yzh87112222163.com");
    }

    //String.matches
    public static void test0() {
        for(String pattern : new String[]{ "Rudolph", "[rR]udolph", "[rR][aeiou][a-z]ol.*", "R.*" })
            System.out.println("Rudolph".matches(pattern));  //此字符串是否匹配给定的正则表达式
    }

    //基本用法
    public static void test1() {
        String str = "sadfasfasasdf ";
        String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern p = Pattern.compile(regEx);  //将给定的正则表达式编译到模式中
        Matcher m = p.matcher(str); //创建匹配给定输入与此模式的匹配器
        System.out.println(m.find());  //没有下一个匹配返回false

        regEx = "[0-9A-Za-z]";
        p = Pattern.compile(regEx);
        m = p.matcher(str);
        System.out.println(m.find());  //有下一个匹配返回true
    }

    //Matcher方法
    public static void test2() {
        Pattern p = Pattern.compile("([a-z]+)(\\d+)");
        Matcher m = p.matcher("aaa2223bb");
        System.out.println(m.find());   //匹配aaa2223  true
        System.out.println(m.groupCount());   //返回2,因为有2组
        System.out.println(m.start(1));   //返回0 返回第一组匹配到的子字符串在字符串中的索引号
        System.out.println(m.start(2));   //返回3
        System.out.println(m.end(1));   //返回3 返回第一组匹配到的子字符串的最后一个字符在字符串中的索引位置.
        System.out.println(m.end(2));   //返回7
        System.out.println(m.group(1));   //返回aaa,返回第一组匹配到的子字符串
        System.out.println(m.group(2));   //返回2223,返回第二组匹配到的子字符串
        System.out.println("------------------");

        p = Pattern.compile("\\d+\\w+");
        String myText = "this is my 1st test string";
        m = p.matcher(myText);
        System.out.println(m.find());
        System.out.println(m.group());
        System.out.println(m.start());
        System.out.println(m.end());

        String result = m.replaceAll("aaa");
        System.out.println(result);
    }


    //group
    public static void test3() {
        Pattern pattern = Pattern.compile("nfa|nfa@not");
        Matcher m = pattern.matcher("nfa@not");
        m.find();
        System.out.println(m.group());

        pattern = Pattern.compile("^.*([0-9]+)");
        m = pattern.matcher("csdfasf 2003.");
        m.find();
        System.out.println(m.group(1));
    }

    //查找多个匹配
    public static void test4() {
        Pattern pattern = Pattern.compile("a|b");
        Matcher m = pattern.matcher("abcabca@126.com");
        StringBuffer sb = new StringBuffer();
        while (m.find()) {  //find可在字符序列中查找多个匹配
            m.appendReplacement(sb, m.group().toUpperCase()); //appendReplacement：渐进式的替换
            System.out.println(sb.toString());
        }
        m.appendTail(sb); //appendTail: 在执行1次或多次appendReplacement后，调用次方法可以将输入字符串余下的部分复制到sb中
        System.out.println(sb.toString());
    }

    //reset
    public static void test5() {
        Matcher m = Pattern.compile("[frb][aiu][gx]").matcher("fix the rug with bags");
        while(m.find())
            System.out.print(m.group() + " ");
        System.out.println();
        m.reset("fix the rig with rags");
        while(m.find())
            System.out.print(m.group() + " ");
    }

    //检验Email地址
    public static void test6(String email)  throws Exception {
        String input = email;
        // 检测输入的 EMAIL 地址是否以 非法符号"."或"@"作为起始字符
        Pattern p = Pattern.compile("^\\.|^\\@");
        Matcher m = p.matcher(input);
        if (m.find()){
            System.err.println("EMAIL 地址不能以'.'或'@'作为起始字符");
        }
        // 检测是否以"www."为起始
        p = Pattern.compile("^www\\.");
        m = p.matcher(input);
        if (m.find()) {
            System.out.println("EMAIL 地址不能以'www.'起始");
        }
        // 检测是否包含非法字符
        p = Pattern.compile("[^A-Za-z0-9\\.\\@_\\-~#]+");
        m = p.matcher(input);
        StringBuffer sb = new StringBuffer();
        boolean result = m.find();
        boolean deletedIllegalChars = false;
        while(result) {
            // 如果找到了非法字符那么就设下标记
            deletedIllegalChars = true;
            // 如果里面包含非法字符如冒号双引号等，那么就把他们消去，加到 SB 里面
            m.appendReplacement(sb, "");
            result = m.find();
        }
        m.appendTail(sb);
        input = sb.toString();
        if (deletedIllegalChars) {
            System.out.println("输入的 EMAIL 地址里包含有冒号、逗号等非法字符，请修改");
        }
    }


}
