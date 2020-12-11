package xml.oxm.xstream.persistence;

import com.thoughtworks.xstream.persistence.FilePersistenceStrategy;
import com.thoughtworks.xstream.persistence.PersistenceStrategy;
import com.thoughtworks.xstream.persistence.XmlArrayList;
import xml.demo.User;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
/**
 * XOM  ---XStream
 *
 * 持久化
 *
 * */

public class PersistenceSample {
	private static List<User> users;

	@SuppressWarnings("unchecked")
	public void persist() {
		users = new ArrayList<User>();
		User user = new User();
		user.setUserId(1);
		user.setCredits(10);
		user.setUserName("tom");
		user.setPassword("123456");
		users.add(user);

		//创建持久化策略
		File file = new File("javalibs/src/main/java/xml/outxml/persistenceO");
		PersistenceStrategy strategy = new FilePersistenceStrategy(file);

		//持久化集合对象users
		List list = new XmlArrayList(strategy);
		list.addAll(users);
	}
	
	public static void main(String[] args)throws Exception {
		PersistenceSample converter = new PersistenceSample();
		converter.persist();
		/*
		  xstream = new XStream();
		  xstream.registerConverter(new PersistenceArrayListConverter(xstream));
		  String xml = xstream.toXML(users);
          System.out.println(xml);
        */
	}

}
