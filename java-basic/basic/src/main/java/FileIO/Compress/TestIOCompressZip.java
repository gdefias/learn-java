package FileIO.Compress;

/**
 * Created by Defias on 2016/2/29.
 *
 * ZipOutputStream
 * ZIP压缩输出流，把数据压缩成GZIP和Zip格式
 *
 * ZipInputStream
 * ZIP压缩输入流，解压缩
 *
 * ZipEntry
 * 在每一个压缩文件中都会存在多个子文件，那么这每一个的子文件在JAVA中就使用ZipEntry表示
 * ZipEntry对象包含一个扩展接口，用于获取和设置 Zip 文件中该特定条目的所有可用数据：名称，压缩和未压缩大小，日期，CRC 校验和，
 * 额外字段数据，注释，压缩方法以及它是否是目录条目。但是，即使 Zip 格式有设置密码的方法，Java 的 Zip 库也不支持
 *
 *
 * ZipOutputStream的putNextEntry(ZipEntry e) 方法：设置ZipEntry对象
 * ZipInputStream的getNextEntry()方法：在有文件存在的情况下调用，会返回下一个ZipEntry
 * 作为一个更简洁的替代方法，可以使用 ZipFile 对象读取该文件，该对象具有方法entries() 返回一个包裹ZipEntries的Enumeration
 *
 * ZipFile
 * 一个专门表示压缩文件的类，在JAVA中，每一个压缩文件都可以使用ZipFile表示
 *
 * Checksum类计算和验证文件的校验和
 * 有两种校验和类型：Adler32（更快）和 CRC32（更慢但更准确）
 *
 * 虽然CheckedInputStream和CheckedOutputStream都支持 Adler32 和 CRC32 校验和，但ZipEntry类仅支持 CRC 接口
 *
 */

import java.io.*;
import java.util.Enumeration;
import java.util.zip.*;


public class TestIOCompressZip {
    public static void main(String[] args) throws Exception {
        //test0();
        test1();

    }

    public static void test0() {
        //待压缩的文件
        String[] filenames = new String[] {"base/src/comprese.txt", "base/src/comprese2.txt"};

        try (FileOutputStream f = new FileOutputStream("base/src/compresetestz.zip");
             CheckedOutputStream csum = new CheckedOutputStream(f, new Adler32());
             ZipOutputStream zos = new ZipOutputStream(csum);
             BufferedOutputStream out = new BufferedOutputStream(zos)
        ) {
            zos.setComment("A test of Java Zipping");
            // No corresponding getComment(), though.
            for (String arg : filenames) {
                System.out.println("Writing file " + arg);
                try (
                        InputStream in = new BufferedInputStream(new FileInputStream(arg))
                ) {
                    zos.putNextEntry(new ZipEntry(arg));
                    int c;
                    while ((c = in.read()) != -1)
                        out.write(c);
                }
                out.flush();
            }

            // Checksum valid only after the file is closed!
            System.out.println("Checksum: " + csum.getChecksum().getValue());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Now extract the files:
        System.out.println("Reading file");
        try (FileInputStream fi = new FileInputStream("base/src/compresetestz.zip");
             CheckedInputStream csumi = new CheckedInputStream(fi, new Adler32());
             ZipInputStream in2 = new ZipInputStream(csumi);
             BufferedInputStream bis = new BufferedInputStream(in2)
        ) {
            ZipEntry ze;
            while ((ze = in2.getNextEntry()) != null) {
                System.out.println("Reading file " + ze);
                int x;
                while ((x = bis.read()) != -1)
                    System.out.write(x);
            }

            if (filenames.length == 1)
                System.out.println("Checksum: " + csumi.getChecksum().getValue());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Alternative way to open and read Zip files:
        try (ZipFile zf = new ZipFile("base/src/compresetestz.zip")) {
            Enumeration e = zf.entries();
            while (e.hasMoreElements()) {
                ZipEntry ze2 = (ZipEntry) e.nextElement();
                System.out.println("File: " + ze2);
                // ... and extract the data as before
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //?
    public static void test1() throws Exception{
        File file = new File("base/src/compresetestz.zip") ;
        File outputFile = new File("base/src/compreseout.txt") ;

        ZipFile zipFile = new ZipFile(file) ;
        ZipEntry entry = zipFile.getEntry("base/src/compreseout.txt") ;
        OutputStream out = new FileOutputStream(outputFile) ;
        InputStream input = zipFile.getInputStream(entry) ;
        int temp = 0 ;
        while((temp=input.read()) != -1){
            out.write(temp) ;
        }
        input.close() ;
        out.close() ;
    }


    //对文件进行压缩：输入若干文件名，将所有文件压缩为testz.zip，再从压缩文件中解压并显示
	public static void test2() throws Exception {
        FileOutputStream a = new FileOutputStream("base/src/compresezz.zip");
        ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(a));

        //待压缩的文件
        String[] filenames = new String[] {"base/src/comprese.txt", "base/src/comprese2.txt"};

        for(int i=0; i<filenames.length; i++) {
            System.out.println("Writing file"+filenames[i]);
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(filenames[i]));

            out.putNextEntry(new ZipEntry(filenames[i])); //设置ZipEntry对象
            out.setComment("this is a test");  //public void setComment(String comment)   设置ZIP文件的注释

            int b;
            while((b=in.read()) != -1)  //读
                out.write(b);  //写
            in.close();
        }
        out.close();  //关闭输出流
        System.out.println("---------------------------");

        //解压缩
        System.out.println("Reading file ");
        FileInputStream d = new FileInputStream("base/src/compresezz.zip");
        ZipInputStream  inout = new  ZipInputStream(new BufferedInputStream(d));
        ZipEntry z;
        while((z=inout.getNextEntry()) != null) {
            System.out.println("Reading file: "+z.getName());  //显示文件初始名
            int x;
            while((x=inout.read()) != -1)
                System.out.write(x);
            System.out.println("\n");
        }
        inout.close();  //关闭输入流
	}

}