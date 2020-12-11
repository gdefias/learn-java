package FileIO.Compress;

import org.apache.commons.codec.binary.StringUtils;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Created by Defias on 2017/3/9.
 *
 * GZIPOutputStream
 * GZIP压缩输出流，构造器只支持接受OutputStream对象，不能接受Writer对象
 *
 *
 * GZIPInpputStream
 * GZIP压缩输入流，打开文件时，GZIPInputStream就会被转换成Reader
 *
 */
public class TestIOCompressGzip {
    public static final String GZIP_ENCODE_UTF_8 = "UTF-8";
    public static final String GZIP_ENCODE_ISO_8859_1 = "ISO-8859-1";
    private static Logger log = Logger.getLogger(TestIOCompressGzip.class);

    public static void main(String[] args) {

        test0();
        //test1();
    }

    public static void test0() {
        String filename = "base/src/comprese.txt";
        try (InputStream in = new BufferedInputStream(
                new FileInputStream(filename));

             BufferedOutputStream out = new BufferedOutputStream(
                     new GZIPOutputStream(
                             new FileOutputStream("base/src/comprese.gz")))
        ) {
            System.out.println("Writing file");
            int c;
            while ((c = in.read()) != -1)
                out.write(c);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Reading file");

        try (BufferedReader in2 = new BufferedReader(
                new InputStreamReader(new GZIPInputStream(
                        new FileInputStream("base/src/comprese.gz"))))
        ) {
            in2.lines().forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    public static void test1() {
        String str = "%5B%7B%22lastUpdateTime%22%3A%222011-10-28+9%3A39%3A41%22%2C%22smsList%22%3A%5B%7B%22l" +
                "iveState%22%3A%221";
        System.out.println("原长度：" + str.length());

        System.out.println("压缩后字符串：" +
                TestIOCompressGzip.compress(str).toString().length());

        System.out.println("解压缩后的字符串：" +
                StringUtils.newStringUtf8(TestIOCompressGzip.uncompress(TestIOCompressGzip.compress(str))));

        System.out.println("解压缩后的字符串：" +
                TestIOCompressGzip.uncompressToString(TestIOCompressGzip.compress(str)));
    }


    //字符串压缩为GZIP字节数组
    public static byte[] compress(String str) {
        return compress(str, GZIP_ENCODE_UTF_8);
    }


    //字符串压缩为GZIP字节数组
    public static byte[] compress(String str, String encoding) {
        if (str == null || str.length() == 0) {
            return null;
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gzip;
        try {
            gzip = new GZIPOutputStream(out);
            gzip.write(str.getBytes(encoding));
            gzip.close();
        } catch (IOException e) {
            log.error("gzip compress error.", e);
        }

        return out.toByteArray();
    }



    //GZIP解压缩为字节数组
    public static byte[] uncompress(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        try {
            GZIPInputStream ungzip = new GZIPInputStream(in);
            byte[] buffer = new byte[256];
            int n;
            while ((n = ungzip.read(buffer)) >= 0) {
                out.write(buffer, 0, n);
            }
        } catch (IOException e) {
            log.error("gzip uncompress error.", e);
        }

        return out.toByteArray();
    }


    //GZIP解压缩为字符串
    public static String uncompressToString(byte[] bytes) {
        return uncompressToString(bytes, GZIP_ENCODE_UTF_8);
    }


    public static String uncompressToString(byte[] bytes, String encoding) {
        if (bytes == null || bytes.length == 0) {
            return null;
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        try {
            GZIPInputStream ungzip = new GZIPInputStream(in);
            byte[] buffer = new byte[256];
            int n;
            while ((n = ungzip.read(buffer)) >= 0) {
                out.write(buffer, 0, n);
            }
            return out.toString(encoding);
        } catch (IOException e) {
            log.error("gzip uncompress to string error.", e);
        }

        return null;
    }


}
