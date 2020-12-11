package FileIO.Compress;

/**
 * Created by Defias on 2017/3/7.
 *
 * 1、压缩一个非嵌套型文件夹
 * 2、嵌套文件或文件夹的压缩
 */

import java.io.File ;
import java.io.FileInputStream ;
import java.io.InputStream ;
import java.util.zip.ZipEntry ;
import java.util.zip.ZipOutputStream ;
import java.io.FileOutputStream ;

public class TestIOCompressZipDirectory {
    public static void main(String args[]) throws Exception {
        testZipDirectory();
        System.out.println("-----------");

        testZipMultiFile();

    }

    public static void testZipDirectory() throws Exception {
        File file = new File("/Users/yzh" + File.separator + "Movies") ;    //定义要压缩的文件夹
        File zipFile = new File("testMovies.zip") ;  //定义压缩文件名称

        InputStream input = null ;  //定义文件输入流
        ZipOutputStream zipOut = null ; //声明压缩流对象
        zipOut = new ZipOutputStream(new FileOutputStream(zipFile)) ;
        zipOut.setComment("test zip movies directory") ;   //设置注释
        int temp = 0 ;
        if(file.isDirectory()){ //判断是否是文件夹
            File lists[] = file.listFiles() ;   //列出全部文件
            for(int i=0;i<lists.length;i++){
                input = new FileInputStream(lists[i]) ; //定义文件的输入流
                zipOut.putNextEntry(new ZipEntry(file.getName()
                        +File.separator+lists[i].getName())) ;  //设置ZipEntry对象
                while((temp=input.read())!=-1){ //读取内容
                    zipOut.write(temp) ;    //压缩输出
                }
                input.close() ; //关闭输入流
            }
        }
        zipOut.close() ;    //关闭输出流
    }

    public static void testZipMultiFile() throws Exception {
        String filepath = "/Users/yzh/Movies";  //文件所在目录
        String zippath = "testMovies2.zip";  //压缩后zip文件名称
        boolean dirFlag = true;  //zip文件中第一层是否包含一级目录，true包含；false没有

        //File file = new File(filepath) ;    //定义要压缩的文件夹
        //File zipFile = new File(zippath) ;  //定义压缩文件名称

        try {
            File file = new File(filepath); //要被压缩的文件夹
            File zipFile = new File(zippath);  //定义压缩文件名称
            ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
            if(file.isDirectory()){
                File[] files = file.listFiles();
                for(File fileSec:files){
                    if(dirFlag){
                        recursionZip(zipOut, fileSec, file.getName() + File.separator);
                    }else{
                        recursionZip(zipOut, fileSec, "");
                    }
                }
            }
            zipOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void recursionZip(ZipOutputStream zipOut, File file, String baseDir) throws Exception {
        if(file.isDirectory()) {
            File[] files = file.listFiles();
            for(File fileSec:files){
                recursionZip(zipOut, fileSec, baseDir + file.getName() + File.separator);
            }
        } else {
            byte[] buf = new byte[1024];
            InputStream input = new FileInputStream(file);
            zipOut.putNextEntry(new ZipEntry(baseDir + file.getName()));
            int len;
            while((len = input.read(buf)) != -1) {
                zipOut.write(buf, 0, len);
            }
            input.close();
        }
    }
}
