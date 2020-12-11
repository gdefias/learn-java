package FileIO.Compress;

/**
 * Created with IntelliJ IDEA.
 * Description: JAR
 * User: Defias
 * Date: 2018-05
 *
 * JARFile: JAR文件
 * JAR文件是打包基于JAVA技术的解决方案的标准方法。它允许开发者将所有相关内容（.class、图片、声音、及所有支持的文件）打
 * 包到一个文件中。JAR格式支持压缩、认证、版本号及其他很多特性
 *
 * JAR文件提供一种将多个文件打包到一个文件中的方法，其中每一个文件可能独立地被压缩。JAR文件所增加的内容是manifest，它允许
 * 开发者可以提供附加的关于内容的信息。例如，manifest表明JAR文件中的哪个文件是用来运行一个程序的，或者库的版本号等
 *
 *
 * JarInputStream
 * JAR压缩输出流
 *
 * JarOutputStream
 * JAR压缩输入流
 *
 * JAREntry
 * JAR实体  在每一个JAR压缩文件中都会存在多个子文件，那么这每一个的子文件就使用JAREntry表示，该类有getName，
 * getSize，getCompressedSize等方法
 *
 *  一些结论:
 *  通过JarInputStream和JarOutputStream配合复制jar
 *  A)如果源jar是未压缩的,复制后,目的jar会比源jar多8个字节，但是并不影响程序的运行，这个增加量是固定的
 *  B)如果源jar是压缩的,目的jar会和源jar大小相同
 *
 *  如果采用JarFile和JarOutputStream配合复制jar
 *  A)如果源jar是未压缩的,复制后,目的jar会比源jar小,而且也不影响程序的运行，减小的大小试源jar大小变化
 *  B)如果源jar是压缩的,目的jar会和源jar大小相同
 */

import java.io.*;
import java.util.Enumeration;
import java.util.TreeMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarInputStream;
import java.util.jar.JarOutputStream;


public class TestIOCompressJar {

    public static void main(String[] args) {
        cpJar();
        //testJarFile();
    }

    public static void cpJar() {
        try {
            JarInputStream jarIn = new JarInputStream(new FileInputStream("base/src/atf-thrift-1.0-SNAPSHOT-input.jar"));
            JarOutputStream jarOut = new JarOutputStream(new FileOutputStream("base/src/atf-thrift-1.0-SNAPSHOT-output.jar"));

            System.out.println(jarIn.getManifest().toString());
            System.out.println(jarIn.getManifest().getMainAttributes().size());

            byte[] buf = new byte[4096];
            JarEntry entry;
            while ((entry = jarIn.getNextJarEntry()) != null) {
                jarOut.putNextEntry(entry);
                int read;
                while ((read = jarIn.read(buf)) != -1) {
                    jarOut.write(buf, 0, read);
                }
                jarOut.closeEntry();
            }
            jarOut.flush();
            jarOut.close();
            jarIn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void testJarFile() {
        try {
            JarFile jarFile = new JarFile("base/src/thefile.jar");
            //或
            //File file = new File("thefile.jar");
            //JarFile jarFile = new JarFile(file);

            TreeMap<String, JarEntry> byName = new TreeMap<String, JarEntry>();
            for (Enumeration<JarEntry> e = jarFile.entries(); e.hasMoreElements(); ) {
                JarEntry entry = e.nextElement();
                byName.put(entry.getName(), entry);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
