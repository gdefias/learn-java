package mail.demo1;
import javax.mail.*;
import java.util.*;
/**
 * Created by Defias on 2020/08.
 * Description: 操作邮件夹

 URLName类：
 类似URL，区别是：不尝试连接目标地址；可以表示非标准的URL地址： 协议名://用户名:密码@主机:端口/邮件夹

 */
public class MailClientURLName extends MailClient {
    private String sendHost="localhost"; //发送邮件服务器
    private String receiveHost;
    private String receiveProtocol; //接收邮件协议
    private String username;
    private String password;
    private String fromAddr="admin@mydomain.com";  //发送者地址
    private String toAddr="admin@mydomain.com"; //接收者地址

    public static void main(String[] args)throws Exception {
        MailClientURLName client=new MailClientURLName();
        client.init();
        client.sendMessage(client.fromAddr,client.toAddr);
        client.receiveMessage();
        client.close();

        MailClientFullURL client2 = new MailClientFullURL();
        client2.init();
        client2.browseMessagesFromFolder(client2.folder);
    }


    public void init()throws Exception{
        init(new URLName("imap://admin:1234@localhost/"));
    }

    public void init(URLName urlName)throws Exception{
        receiveProtocol=urlName.getProtocol();
        receiveHost=urlName.getHost();
        username=urlName.getUsername();
        password=urlName.getPassword();

        //设置属性
        Properties props = new Properties();
        props.put("mail.smtp.host", sendHost);

        // 创建Session对象
        session = Session.getDefaultInstance(props);

        // 创建Store对象
        store = session.getStore(receiveProtocol);
        //连接到邮件服务器
        store.connect(receiveHost,username,password);
    }
}


class MailClientFullURL extends MailClient {
    public Folder folder;
    public void init()throws Exception{
        init(new URLName("imap://admin:1234@localhost/inbox"));
        //init(new URLName("pop3://java_mail:123456@pop.citiz.net/inbox"));
    }
    public void init(URLName urlName)throws Exception{
        //设置属性
        Properties props = new Properties();

        // 创建Session对象
        session = Session.getDefaultInstance(props);
        folder = session.getFolder(urlName);
        if(folder==null){
            System.out.println(urlName.getFile()+"邮件夹不存在");
            System.exit(0);
        }
    }
}