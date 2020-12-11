package xml.oxm.xstream.converters;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import xml.demo.LoginLog;
import xml.demo.User;
import xml.utils.ResourceUtils;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.Locale;
/**
 * XOM  ---XStream
 * 转换过程中使用自定义的日志转换器
 *
 * */

public class XStreamConverterSample {
	public static String FILE_NAME = "";
	private static XStream xstream;
	private static User u;
	static{

	    xstream = new XStream(new DomDriver());
        xstream.alias("loginLog", LoginLog.class);
        xstream.alias("user", User.class);

        xstream.aliasField("id",User.class,"userId");
        xstream.useAttributeFor(User.class, "userId");
        xstream.addImplicitCollection(User.class, "logs");

        //注册自定义的日期转换器
        xstream.registerConverter(new DateConverter(Locale.SIMPLIFIED_CHINESE));
	}


	public static void initUser() {
		LoginLog log1 = new LoginLog();
		LoginLog log2 = new LoginLog();
		log1.setIp("192.168.1.91");
		log1.setLoginDate(new Date());
		log2.setIp("192.168.1.92");
		log2.setLoginDate(new Date());
        u = new User();
		u.setUserId(1);
		u.setUserName("xstream");
		u.addLoginLog(log1);
		u.addLoginLog(log2);
	}
	 /**
     * JAVA对象转化为XML
     */
    public static void objectToXml()throws Exception{
    	initUser();
        FileOutputStream fs = new FileOutputStream(FILE_NAME);
        OutputStreamWriter writer = new OutputStreamWriter(fs, Charset.forName("UTF-8"));    
        xstream.toXML(u, writer);
    }
    
    /**
     * XML转化为JAVA对象
     */
    public static User xmlToObject()throws Exception{
    	FileInputStream fis = new FileInputStream(FILE_NAME);
    	InputStreamReader reader = new InputStreamReader(fis, Charset.forName("UTF-8")); 
        User u = (User) xstream.fromXML(fis);
        for(LoginLog log : u.getLogs()){
        	if(log!=null){
				System.out.println("访问IP: " + log.getIp());
				System.out.println("访问时间: " + log.getLoginDate());
            }
        }
        return u;
    }

    public static void main(String[] args)throws Exception {
    	FILE_NAME = "javalibs/src/main/java/xml/outxml/XStreamConverterSample.xml";
        objectToXml();
        //xmlToObject();
    }
}
