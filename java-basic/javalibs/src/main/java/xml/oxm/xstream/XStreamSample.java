package xml.oxm.xstream;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import xml.demo.LoginLog;
import xml.demo.User;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;
/**
 * XOM  ---XStream
 * */

public class XStreamSample {
	private static XStream xstream;
	static {
		//创建xstream实例并指定一个XML解析器，如果不指定默认使用XPP解析器
		xstream = new XStream(new DomDriver());
	}

	// 初始化转换对象
	public static User getUser() {
		LoginLog log1 = new LoginLog();
		LoginLog log2 = new LoginLog();
		log1.setIp("192.168.1.91");
		log1.setLoginDate(new Date());
		log2.setIp("192.168.1.92");
		log2.setLoginDate(new Date());
		User user = new User();
		user.setUserId(1);
		user.setUserName("xstream");
		user.addLoginLog(log1);
		user.addLoginLog(log2);
		return user;
	}

	/**
	 * JAVA对象转化为XML
	 */
	public static void objectToXml() throws Exception {
		User user = getUser();
		FileOutputStream outputStream = new FileOutputStream("javalibs/src/main/java/xml/outxml/XStreamSample.xml");
		xstream.toXML(user, outputStream);
	}

	/**
	 * XML转化为JAVA对象
	 */
	public static User xmlToObject() throws Exception {
		FileInputStream fis = new FileInputStream("javalibs/src/main/java/xml/outxml/XStreamSample.xml");
		User u = (User) xstream.fromXML(fis);
		for (LoginLog log : u.getLogs()) {
			if (log != null) {
				System.out.println("访问IP: " + log.getIp());
				System.out.println("访问时间: " + log.getLoginDate());
			}
		}
		return u;
	}

	public static void main(String[] args) throws Exception {
		//objectToXml();
		User user = xmlToObject();
	}
}
