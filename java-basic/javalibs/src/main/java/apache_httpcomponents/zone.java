package apache_httpcomponents;
public @interface zone {}
/**
 * Created by Defias on 2020/09.
 * Description:

 HttpComponents
 也就是以前的httpclient项目（现在作为遗留的Commons HttpClient）
 可以用来提供高效的、最新的、功能丰富的支持HTTP协议的客户端/服务器编程工具包，并且它支持HTTP协议最新的版本和建议

 HttpComponents包含多个子项目：
 HttpComponents Core
 简称HttpCore, 是一组底层Http传输协议组件，支持阻塞I/O模型和和非阻塞I/O模型，上层组件(HttpComponents Client, HttpComponents
 AsyncClient)依赖此组件实现数据传输。阻塞I/O模型基于基本的JAVA I/O实现，非阻塞模型基于JAVA NIO实现

 HttpComponents Client
 建立在HttpCore之上的Http客户端管理组件。底层基于HttpCore阻塞I/O。从Commons HttpClient 3.x继承而来，Commons HttpClient原来是
 apache commons组建的一部分，现在被HttpComponents Client所替代了

 HttpComponents AsyncClient
 建立在HttpCore NIO模型之上的Http客户端，与基于阻塞I/O的HttpComponents Client形成互补，由于底层使用的NIO非阻塞模型，所以适用
 于高性能的应用场景


 区别类库Commons http与HttpComponents
 Commons http与HttpComponents是完全两个东西，HttpComponents中的Client是从Commons http继承而来的，所以很多类名是相同的
 为了避免出现莫名奇妙的问题，应将Commons http从工程中删除

 Commons http类库的包是org.apache.apache_commonsio.apache_httpclient
 HttpComonents类库的包是org.apache.http

 */

