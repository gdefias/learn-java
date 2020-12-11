package SPI;
/**
 * Created by Defias on 2020/07.
 * Description: SPI
 *
 SPI的全名为Service Provider Interface
 是针对厂商或者插件的，在java.util.ServiceLoader的文档里有比较详细的介绍

 从Java 6开始，为了支持更加灵活的、对服务的扩展（对服务接口的独立实现），JDK提供了java.util.ServiceLoader<S>类
 该类能够在运行时动态加载第三方提供的服务实现，而无需预先进行任何的代码级编译集成。从而实现了服务接口与服务实现的完全松耦合

 简单来说就是通过配置文件指定接口的实现类
 当我们开发一套框架、一套机制、一个插件或一套API时候，如果需要第三方的服务支持，可以直接写死到代码里面，但这种方式耦合太强，
 不利于切换到其它服务，好的方法是写一个配置文件指定服务的实现方，幸运的是java的spi机制已经帮我们做好了

 数据库DriverManager、Spring、ConfigurableBeanFactory等都用到了SPI机制
 */

