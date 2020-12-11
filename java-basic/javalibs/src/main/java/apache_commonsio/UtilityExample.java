package apache_commonsio;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileSystemUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.LineIterator;
import org.apache.commons.io.IOCase;

/*
*
*  FilenameUtils： 这个工具类是用来处理文件名的，他可以轻松解决不同操作系统文件名称规范不同的问题（比如windows和Unix）（在Unix系统以及Linux系统中文件分隔符是“/”，不支持”\“，windows中支持”\“以及”/“）
*  FileUtils：提供文件操作（移动文件，读取文件，检查文件是否存在等等）的方法
*  IOCase：提供字符串操作以及比较的方法
*  FileSystemUtils：提供查看指定目录剩余空间的方法
*
* */

public final class UtilityExample {
    
    // We are using the file exampleTxt.txt in the folder ExampleFolder,
    // and we need to provide the full path to the Utility classes.
    private static final String EXAMPLE_TXT_PATH = "res/exampleTxt.txt";

    private static final String PARENT_DIR = "res/";

    public static void runExample() throws IOException {
        System.out.println("Utility Classes example...");
        
        
        // FilenameUtils
        System.out.println("Full path of exampleTxt: " +
                FilenameUtils.getFullPath(EXAMPLE_TXT_PATH));
        
        System.out.println("Full name of exampleTxt: " +
                FilenameUtils.getName(EXAMPLE_TXT_PATH));
        
        System.out.println("Extension of exampleTxt: " +
                FilenameUtils.getExtension(EXAMPLE_TXT_PATH));
        
        System.out.println("Base name of exampleTxt: " +
                FilenameUtils.getBaseName(EXAMPLE_TXT_PATH));
        
        
        // FileUtils
        // We can create a new File object using FileUtils.getFile(String)
        // and then use this object to get information from the file.
        File exampleFile = FileUtils.getFile(EXAMPLE_TXT_PATH);
        LineIterator iter = FileUtils.lineIterator(exampleFile);
        
        System.out.println("Contents of exampleTxt...");
        while (iter.hasNext()) {
            System.out.println("\t" + iter.next());
        }
        iter.close();
        
        // We can check if a file exists somewhere inside a certain directory.
        File parent = FileUtils.getFile(PARENT_DIR);
        System.out.println("Parent directory contains exampleTxt file: " +
                FileUtils.directoryContains(parent, exampleFile));
        
        
        // IOCase
        String str1 = "This is a new String.";
        String str2 = "This is another new String, yes!";
        
        System.out.println("Ends with string (case sensitive): " +
                IOCase.SENSITIVE.checkEndsWith(str1, "string."));
        System.out.println("Ends with string (case insensitive): " +
                IOCase.INSENSITIVE.checkEndsWith(str1, "string."));
        
        System.out.println("String equality: " +
                IOCase.SENSITIVE.checkEquals(str1, str2));
        
        
        // FileSystemUtils
        System.out.println("Free disk space (in KB): " + FileSystemUtils.freeSpaceKb("/Users/yzh"));
        System.out.println("Free disk space (in MB): " + FileSystemUtils.freeSpaceKb("/Users/yzh") / 1024);
    }
}