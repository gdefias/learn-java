package FileIO.Compress;
public @interface zone {}

/**
 * Created by Defias on 2020/07.
 * Description: Java 数据压缩
 *
 Java I/O 类库提供了可以读写压缩格式流的类。可以将其他 I/O 类包装起来用于提供压缩功能

 压缩类	                 功能
 ----------------------------------------------------------------------------------
 CheckedInputStream	    getCheckSum() 可以对任意 InputStream 计算校验和（而不只是解压）
 CheckedOutputStream	getCheckSum() 可以对任意 OutputStream 计算校验和（而不只是压缩）
 DeflaterOutputStream	压缩类的基类
 ZipOutputStream	    DeflaterOutputStream 类的一种，用于压缩数据到 Zip 文件结构
 GZIPOutputStream	    DeflaterOutputStream 类的一种，用于压缩数据到 GZIP 文件结构
 InflaterInputStream	解压类的基类
 ZipInputStream	        InflaterInputStream 类的一种，用于解压 Zip 文件结构的数据
 GZIPInputStream	    InflaterInputStream 类的一种，用于解压 GZIP 文件结构的数据


 这些类不是从 Reader 和 Writer 类派生的，而是 InputStream 和 OutputStream 层级结构的一部分。这是由于压缩库处理的是字节，
 而不是字符。可以使用 InputStreamReader 和 OutputStreamWriter 在字节类型和字符类型之间轻松转换



 */

