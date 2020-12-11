package xml.oxm.xstream.objectstreams;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import xml.demo.LoginLog;
import xml.demo.User;
import java.io.*;
import java.util.Date;
/**
 * XOM  ---XStream
 *
 * 流化对象 ---以对象流的方式进行XML序列化或反序列化操作
 *
 * */
public class ObjectStreamSample {
    private static  XStream xstream;
    static{
        xstream = new XStream();
    }

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

    //java对象转换为xml
    public void objectToXml()throws Exception{
        User user = getUser();
        //创建一个PrintWriter对象用于输出
        PrintWriter pw = new PrintWriter("javalibs/src/main/java/xml/outxml/ObjectStreamSample.xml");
        PrettyPrintWriter ppw = new PrettyPrintWriter(pw);

        //创建对象输出流
        ObjectOutputStream out = xstream.createObjectOutputStream(ppw);
        out.writeObject(user);
        out.close();
    }


    //xml转换为java对象
    public User xmlToObject()throws Exception{
        //通过对象流进行输入操作
        FileReader fr=new FileReader("javalibs/src/main/java/xml/outxml/ObjectStreamSample.xml");
        BufferedReader br=new BufferedReader(fr);
        ObjectInputStream input = xstream.createObjectInputStream(br);

        User user=(User)input.readObject();  //从xml文件中读取对象
        return user;

    }

    public static void main(String[] args)throws Exception {
        ObjectStreamSample converter = new ObjectStreamSample();
        converter.objectToXml();

        User u = converter.xmlToObject();
        for (LoginLog log : u.getLogs()) {
            if (log != null) {
                System.out.println("访问IP: " + log.getIp());
                System.out.println("访问时间: " + log.getLoginDate());
            }
        }

    }
}