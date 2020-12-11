package FileIO;
import java.io.*;
/**
 * Created by Defias on 2020/07.
 * Description: 目录的检查和创建
 */
public class TestFileDirectory3 {
    public static void main(String[] args) {
        String path = "base/src/Log/log4j";
        String filename = "test1.txt";
        String nname = "test2.txt";
        File old = new File(path+"/"+filename);
        File rname = new File(path+"/"+nname);
        old.renameTo(rname);      //文件重命名
        fileData(old);
        fileData(rname);

        File newdir = new File(path+"/newdir");
        newdir.mkdirs();     //创建目录
        System.out.println("created " + newdir);


        //文件删除 !!!
//        File f = new File(path+"/test");
//        f = newdir;
//        if(f.exists()) {
//            System.out.println(f + " exists");
//            System.out.println("deleting..." + f);
//            f.delete();
//        } else {
//            System.out.println(f + " not exists");
//        }

    }

    private static void usage() {
        System.err.println(
                "Usage:MakeDirectories path1 ...\n" +
                        "Creates each path\n" +
                        "Usage:MakeDirectories -d path1 ...\n" +
                        "Deletes each path\n" +
                        "Usage:MakeDirectories -r path1 path2\n" +
                        "Renames from path1 to path2");
        System.exit(1);
    }

    private static void fileData(File f) {
        System.out.println("Absolute path: " + f.getAbsolutePath() +
                        "\n Can read: " + f.canRead() +
                        "\n Can write: " + f.canWrite() +
                        "\n getName: " + f.getName() +
                        "\n getParent: " + f.getParent() +
                        "\n getPath: " + f.getPath() +
                        "\n length: " + f.length() +
                        "\n lastModified: " + f.lastModified());
        if(f.isFile())
            System.out.println("It's a file");
        else if(f.isDirectory())
            System.out.println("It's a directory");

        System.out.println();
    }
}
