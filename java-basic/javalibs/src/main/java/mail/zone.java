package mail;
public @interface zone {}
/**
 * Created by Defias on 2020/08.
 * Description: JavaMail 邮件服务器

 javax.mail.Sessionl类
 邮件会话 JavaMail API的最高层入口

 javax.mail.Store类
 接收邮件服务器上注册用户的存储空间，通过getFolder方法可以访问用户的特定邮件夹

 javax.mail.Folder
 邮件夹，邮件都放在邮件夹中，Folder提供了管理邮件夹和邮件的各种方法

 javax.mail.Message抽象类
 电子邮件，提供了读取和设置邮件内容的方法，邮件包括：地址信息（收发件人。。。） 邮件标题、邮件收发日期、邮件正文（含附件）

 javax.mail.Address抽象类
 邮件地址

 javax.mail.Transport
 send(Message)方法负责发送邮件，可以指定邮件发送协议SMTP


 */

