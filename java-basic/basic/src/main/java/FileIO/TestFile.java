package FileIO;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * Created by Defias on 2017/2/24.
 *
 * File类
 *
 * File类可以用来获取文件和目录的属性，删除和重命名文件和目录，以及创建目录（不能创建文件）
 * File类意图提供一种抽象，以不依赖机器的方式来处理很多依赖机器的文件和路径名的复杂性
 *
 * Java语言在java.io包中定义了一个File类专门用来管理磁盘文件和目录
 *
 * 每个File类对象表示一个磁盘文件或目录，其对象属性中包含了文件或目录的相关信息。通过调用File类提供的各种方法，能够创建、删除、重名名
 * 文件、判断文件的读写权限以及是否存在，设置和查询文件的最近修改时间等。不同操作系统具有不同的文件系统组织方式，通过使用File类对象，
 * Java程序可以用与平台无关的、统一的方式来处理文件和目录
 *
 * public class File extends Object implements Serializable, Comparable<File>
 *
 * File类的构造方法：
 * 构造方法								功能描述
 * --------------------------------------------------------------------------------------------------
 * public File(String pathname)		 通过将给定路径名字符串转换为抽象路径名来创建一个新File实例
 * public File(String parent, String child)	 根据parent路径名字符串和child路径名字符串创建一个新File实例
 * public File(File parent, String child)	根据parent抽象路径名和child路径名字符串创建一个新File实例
 * public File(URL ui)	 通过将给定的file:URI转换为一个抽象路径名来创建一个新的File实例
 *
 * parent说明：
 * 1）path参数可以是绝对路径，也可以是相对路径，也可以是磁盘上的某个目录
 * 2）由于不同操作系统使用的目录分隔符不同，可以使用System类的一个静态变量System.dirSep，来实现在不同操作系统下都通用的路径。如：
 * "d:"+System.dirSep+"myjava"+System.dirSep+"file"
 *
 * 静态字段：
 * static String   pathSeparator 与系统有关的路径分隔符，为了方便，它被表示为一个字符串。
 * static char	pathSeparatorChar  与系统有关的路径分隔符。
 * static String  separator  与系统有关的默认名称分隔符，为了方便，它被表示为一个字符串。
 * static char	separatorChar  与系统有关的默认名称分隔符
 *
 * File的常用方法：
 * 方法							功能描述
 * --------------------------------------------------------------------------------------------------
 * boolean canRead()				如果文件可读，返回真，否则返回假
 * boolean canWrite()			如果文件可写，返回真，否则返回假
 * boolean exists()				判断文件或目录是否存在
 * boolean createNewFile()		若文件不存在，则创建指定名字的空文件，并返回真，若不存在返回假
 * boolean isFile()				判断对象是否代表有效文件
 * boolean isDirectory()			判断对象是否代表有效目录
 * boolean equals(File f)		比较两个文件或目录是否相同
 * string getName()				返回文件名或目录名的字符串
 * string getPath()				返回文件或目录路径的字符串
 * long length()					返回文件的字节数，若 File 对象代表目录，则返回 0
 * long lastModified()			返回文件或目录最近一次修改的时间
 * String[] list()	将目录中所有文件名保存在字符串数组中并返回，若 File 对象不是目录返回 null
 * boolean delete()	删除文件或目录，必须是空目录才能删除，删除成功返回真，否则返回假
 * boolean mkdir()	创建当前目录的子目录，成功返回真，否则返回假
 * boolean mkdirs()  和mkdir相同，除开在父目录不存的情况下，将和父目录一起创建
 * boolean renameTo(File newFile)	将文件重命名为指定的文件名
 * Sting getParent()   返回父路径
 * String getAbsolutePath()  返回完整的绝对路径
 * String CanonicalPath()  与getAbsolutePath相同，从路径中去掉了冗余的名字
 * boolean isHidden()  是否是隐藏文件
 * boolean isAbsolute()  File对象是否是采用绝对路径名创建的
 *
 */

public class TestFile {
    public static void main(String[] args) {

        test1();
        //test2();
        //test3(null);
        //test3(".*\\.xml");
    }


    //文件基本操作
    public static void test1() {
        File file = new File("base/src/us.gif");
        System.out.println("Does it exist? " + file.exists());
        System.out.println("The file has " + file.length() + " bytes");
        System.out.println("Can it be read? " + file.canRead());
        System.out.println("Can it be written? " + file.canWrite());
        System.out.println("Is it a directory? " + file.isDirectory());
        System.out.println("Is it a file? " + file.isFile());
        System.out.println("Is it absolute? " + file.isAbsolute());
        System.out.println("Is it hidden? " + file.isHidden());
        System.out.println("Absolute tmp is " + file.getAbsolutePath());
        System.out.println("Last modified on " + new java.util.Date(file.lastModified()));

        try {
            //getCanonicalFile: 返回此抽象路径名的规范形式
            System.out.println("getCanonicalFile: " + file.getCanonicalFile().toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("------------------------------");
    }


    //目录列表器
    public static void test2() {
        File fp = new File("base/src/Log/log4j");
        //list：返回一个字符串数组，这些字符串指定此抽象路径名表示的目录中的文件和目录（仅1层）
        //若此抽象路径名fp不表示一个目录，那么此方法将返回null
        String[] list = fp.list();
        for(String s: list) {
            System.out.println(s);
        }
        System.out.println("------------------------------");

        File[] flist = fp.listFiles();
        for(File f: flist) {
            System.out.println(f);
        }
        System.out.println("------------------------------");


        //受限列表: 通过文件过滤器FileFilter列出目录下的符合条件的文件
        //FileFilter接口：用于抽象路径名的过滤器
        File[] f = fp.listFiles(new FileFilter(){
            @Override
            public boolean accept(File ff) { //重写accept方法
                if(ff.getName().endsWith(".txt")){  //name以.txt结尾的为符合条件，将被筛出
                    return true;
                } else {
                    return false;
                }
            }
        });
        for(File g: f) {
            System.out.println(g.getName());
        }
        System.out.println("------------------------------");
    }


    //受限列表: 通过文件名过滤器FilenameFilter列出目录下的符合条件的文件
    //FilenameFilter接口：实现此接口的类实例可用于过滤器文件名
    public static void test3(String regex) {
        File path = new File(".");
        String[] list;
        if(regex == null)
            list = path.list();
        else
            list = path.list(new FilenameFilter() {
                private Pattern pattern = Pattern.compile(regex);

                public boolean accept(File dir, String name) {
                    return pattern.matcher(name).matches();
                }
            });
        Arrays.sort(list, String.CASE_INSENSITIVE_ORDER); //按字母顺序排序
        for(String dirItem : list)
            System.out.println(dirItem);
        System.out.println("------------------------------");
    }
}