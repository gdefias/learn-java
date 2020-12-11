package JVM.classload.classloader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by Defias on 2016/4/27.
 *
 * 用户自定义类加载器
 */

public class My2ClassLoader extends ClassLoader {
	private String name;   //给类加载器指定一个名字
	private String path = "/";  //默认路径
	private final String fileType = ".class";

	public My2ClassLoader(String name) {
		super();
		this.name=name;
	}

	public My2ClassLoader(ClassLoader parent, String name) {
		super(parent);
		this.name=name;
	}

	public String toString() {
		return name;
	}

	public void setPath(String path) {
		this.path=path;
	}

	public String getPath() {
		return path;
	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		byte[] data = loadClassData(name);
		return this.defineClass(name, data, 0, data.length);
	}

	//把类的二进制数据读入到内存中
	private byte[] loadClassData(String name) throws ClassNotFoundException {
		FileInputStream fis = null;
		byte[] data = null;
		ByteArrayOutputStream baos = null;
		try {
			//把name字符串中的“.”替换为“\”，从而把类中的包名转变为路径名
			name = name.replaceAll("\\.","\\\\");
            System.out.println(path + name + fileType);
			fis = new FileInputStream(new File(path + name + fileType));
            baos = new ByteArrayOutputStream();
            int ch = 0;
            while ((ch = fis.read()) != -1){
				baos.write(ch);
            }
            data = baos.toByteArray();
            System.out.println(data.length);

            //或：
            //int len = fis.available();
            //data = new byte[len];
            //fis.read(data);

		} catch (IOException e) {
			throw new ClassNotFoundException("Class is not found:"+name,e);
		} finally {
			try{
				fis.close();
				baos.close();
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		return data;
	}

}
