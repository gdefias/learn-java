package FileIO;
import java.util.regex.*;
import java.io.*;
import java.util.*;
/**
 * Created by Defias on 2020/07.
 * Description:  目录实用工具
 */

public final class TestFileDirectory {

    public static void main(String[] args) {
        //test0(args);
        test1();
    }


    public static void test0(String[] args) {
        if(args.length == 0) {
            //PPrint.pprint(local("base/src/Log/log4j", ".*"));
            System.out.println(walk("base/src/Log/log4j"));

        } else {
            for(String arg : args)
                System.out.println(walk(arg));
        }
    }


    public static void test1() {
        // All directories:
        //PPrint.pprint(walk(".").dirs);

        // All files beginning with 'T'
        for(File file : local(".", "d.*"))
            System.out.println(file);
        System.out.println("----------------------");

        // All Java files beginning with 'T':
        for(File file : walk(".", "T.*\\.java"))
            System.out.println(file);
        System.out.println("======================");

        // Class files containing "Z" or "z":
        for(File file : walk(".",".*[Zz].*\\.class"))
            System.out.println(file);
    }

    //本地目录中的文件构成的File对象数组
    public static File[] local(File dir, final String regex) {
        return dir.listFiles(new FilenameFilter() {
            private Pattern pattern = Pattern.compile(regex);

            public boolean accept(File dir, String name) {
                return pattern.matcher(
                        new File(name).getName()).matches();
            }
        });
    }

    public static File[] local(String path, final String regex) { // Overloaded
        return local(new File(path), regex);
    }

    public static class TreeInfo implements Iterable<File> {
        public List<File> files = new ArrayList<File>();
        public List<File> dirs = new ArrayList<File>();

        // The default iterable element is the file list:
        public Iterator<File> iterator() {
            return files.iterator();
        }

        void addAll(TreeInfo other) {
            files.addAll(other.files);
            dirs.addAll(other.dirs);
        }

        public String toString() {
            return "dirs: " + PPrint.pformat(dirs) +
                    "\n\nfiles: " + PPrint.pformat(files);
        }
    }

    //给定目录下的由整个目录树中满足条件的所有文件构成的List<File>
    public static TreeInfo walk(String start, String regex) { // Begin recursion
        return recurseDirs(new File(start), regex);
    }

    public static TreeInfo walk(File start, String regex) { // Overloaded
        return recurseDirs(start, regex);
    }

    public static TreeInfo walk(File start) { // Everything
        return recurseDirs(start, ".*");
    }

    public static TreeInfo walk(String start) {
        return recurseDirs(new File(start), ".*");
    }

    //递归遍历目录树
    static TreeInfo recurseDirs(File startDir, String regex){
        TreeInfo result = new TreeInfo();
        for(File item : startDir.listFiles()) {
            if(item.isDirectory()) {
                result.dirs.add(item);
                result.addAll(recurseDirs(item, regex));
            } else // Regular file
                if(item.getName().matches(regex))
                    result.files.add(item);
        }
        return result;
    }
}
