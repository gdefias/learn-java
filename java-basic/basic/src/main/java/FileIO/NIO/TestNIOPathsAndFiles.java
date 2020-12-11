package FileIO.NIO;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
/**
 * Created with IntelliJ IDEA.
 * Description: Path和Files类（Java 7新增）
 * User: Defias
 * Date: 2018-05

 Path类（java.nio.file.Path）
 一个Path实例代表一个文件系统内的路径
 path可以指向文件也可以指向目录。可以使相对路径也可以是绝对路径，绝对路径包含了从根目录到该文件（目录）的完整路径
 在很多情况下java.no.file.Path接口和java.io.File比较相似，但是他们之间存在一些细微的差异

 创建一个Path实例（工厂方法get）：
 Path path= Paths.get("c:\\data\\myfile.txt");   //使用绝对路径
 Path path = Paths.get("/home/jakobjenkov/myfile.txt");   //使用相对路径（Linux, MacOS,FreeBSD等）
 Path path = FileSystems.getDefault().getPath("c:\\data\\myfile.txt");  //等效的方式

 File和Path之间的转换:
 File file = new File("C:/my.ini");
 Path p1 = file.toPath();
 p1.toFile();



 Files类（java.nio.file.Files）
 Java NIO中的Files类提供了多种操作文件系统中文件的方法
 Files类是和Path相结合使用的

 Files API：
 Files.exits()方法用来检查给定的Path在文件系统中是否存在
 第二个参数是一个数组，这个参数直接影响到Files.exists()如何确定一个路径是否存在
 如：LinkOptions.NOFOLLOW_LINKS，表示检测时不包含符号链接文件

 Files.createDirectory() 创建Path表示的路径

 Files.delete() 删除一个文件或目录

 Files.copy()  拷贝文件

 Files.move()  移动文件 和重命名是一样的，但是还会改变文件的目录位置

 Files.newDirectoryStream()

 Files.walkFileTree()  遍历整个文件目录
 walkFileTree接受一个Path和FileVisitor作为参数。Path对象是需要遍历的目录，FileVistor则会在每次遍历中被调用



 Files.walkFileTree() 具有递归遍历目录的功能
 walkFileTree接受一个Path和FileVisitor作为参数。Path对象是需要遍历的目录，FileVistor则会在每次遍历中被调用
 FileVisitor需要调用方自行实现，然后作为参数传入walkFileTree()
 FileVisitor的每个方法会在遍历过程中被调用多次。如果不需要处理每个方法，那么可以继承它的默认实现类SimpleFileVisitor，
 它将所有的接口做了空实现



 FileVisitor接口
 public interface FileVisitor {
     public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException;
     public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException;
     public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException;
     public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException;
 }

 这些方法会在不同时机被调用：
     preVisitDirectory() 在访问目录前被调用
     postVisitDirectory() 在访问目录后调用
     visitFile() 在整个遍历过程中的每次访问文件都被调用，不是针对目录的，而是针对文件的
     visitFileFailed() 在文件访问失败的时候调用。例如，当缺少合适的权限或者其他错误


 SimpleFileVisitor类
 FileVisitor接口的默认实现类，它将所有的接口做了空实现


 FileVisitResult枚举对象(让调用方决定文件遍历是否需要继续)
 具体的可选枚举项包括：
 CONTINUE 表示文件遍历和正常情况下一样继续
 TERMINATE 表示文件访问需要终止
 SKIP_SIBLINGS  表示文件访问继续，但是不需要访问其他同级文件或目录
 SKIP_SUBTREE  表示继续访问，但是不需要访问该目录下的子目录。这个枚举值仅在preVisitDirectory()中返回才有效。如果在另外几个方法中返回，那么会被理解为CONTINE

 */

public class TestNIOPathsAndFiles {

    public static void main(String[] args) throws IOException {
        //test1();
        test2();
    }

    //Paths
    public static void test1() throws IOException {
        //获取Path的相关信息
        Path path = Paths.get("base/src/testnio.txt");
        System.out.println("文件名：" + path.getFileName());
        System.out.println("名称元素的数量：" + path.getNameCount());
        System.out.println("父路径：" + path.getParent());
        System.out.println("根路径：" + path.getRoot());
        System.out.println("是否是绝对路径：" + path.isAbsolute());

        //startsWith()方法的参数既可以是字符串也可以是Path对象
        System.out.println("是否是以为给定的路径开始：" + path.startsWith("base") );
        System.out.println("该路径的字符串形式：" + path.toString());
        System.out.println("---------------------------1");


        Path currentDir = Paths.get(".");
        System.out.println(currentDir.toAbsolutePath());  //当前路径绝对路径
        System.out.println("---------------------------2");

        Path currentDir2 = Paths.get("base/src/../src/testnio.txt");
        System.out.println("原始路径格式："+currentDir2.toAbsolutePath());

        //normalize(): 可以把路径规范化。返回一个路径，该路径是冗余名称元素的消除
        //toRealPath(): 融合了toAbsolutePath()方法和normalize()方法
        System.out.println("执行normalize()方法之后："+currentDir2.normalize());
        System.out.println("执行toRealPath()方法之后："+currentDir2.toRealPath());
        System.out.println("---------------------------3");

        Path currentDir3 = Paths.get("..");
        System.out.println("原始路径格式："+currentDir3.toAbsolutePath());
        System.out.println("执行normalize()方法之后："+currentDir3.normalize());
        System.out.println("执行toRealPath()方法之后："+currentDir3.toRealPath());
        System.out.println("---------------------------4");

        String originalPath = "/Users/baidu/Code/JavaDemo/../JavaDemo/java-basic/";
        Path path1 = Paths.get(originalPath);
        System.out.println("path1 = " + path1);
        Path path2 = path1.normalize();
        System.out.println("path2 = " + path2);
    }


    public static void test2() throws IOException {
        //检查给定的Path在文件系统中是否存在
        Path path = Paths.get("base/src/nio-data.txt");
        boolean pathExists = Files.exists(path, new LinkOption[] { LinkOption.NOFOLLOW_LINKS });
        System.out.println(pathExists);
        System.out.println("---------------------------1");

        //获取文件属性
        System.out.println(Files.getLastModifiedTime(path));
        System.out.println(Files.size(path));
        System.out.println(Files.isSymbolicLink(path));
        System.out.println(Files.isDirectory(path));
        System.out.println(Files.readAttributes(path,   "*"));
        System.out.println("---------------------------2");


        //创建文件/文件夹
        Path path1 = Paths.get("base/src/Log/subdir");
        try {
            if(!Files.exists(path1)) {
                Path newDir = Files.createDirectory(path1);
                System.out.println("createDirectory.");
            }
        } catch(FileAlreadyExistsException e){
            System.out.println("the directory already exists.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("---------------------------3");

        //删除文件或目录
        //try {
        //    Files.delete(path1);
        //} catch (IOException e) {
        //    e.printStackTrace();
        //}
        System.out.println("---------------------------4");

        //把一个文件从一个地址复制到另一个位置
        Path sourcePath = Paths.get("base/src/nio-data.txt");
        Path destinationPath = Paths.get("base/src/Log/subdir/nio-data.txt");
        try {
            //Files.copy(sourcePath, destinationPath);  //拷贝

            //拷贝时覆盖已经存在的文件
            Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);

            //Files.move(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
        } catch(FileAlreadyExistsException e) {
            System.out.println("destination file already exists.");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("---------------------------5");

        //遍历一个文件夹
        Path dir = Paths.get("base/src/Log");
        try(DirectoryStream<Path> stream = Files.newDirectoryStream(dir)){
            for(Path e : stream){
                System.out.println(e.getFileName());
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        System.out.println("---------------------------6");

        //遍历整个文件目录
        Files.walkFileTree(path1, new FileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                System.out.println("pre visit directory:" + dir);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                System.out.println("visit file: " + file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                System.out.println("visit file failed: " + file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                System.out.println("post visit directory: " + dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }


}
