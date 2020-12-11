package GUI;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Defias
 * Date: 2018-05
 *
 * 显示一个空框架
 *
 * EventQueue
 * 一个与平台无关的类，它将来自于底层同位体类和受信任的应用程序类的事件列入队列。它封装了异步事件指派机制，该机制从队列中提取事件，然后通过对
 * EventQueue调用dispatchEvent(AWTEvent)方法来指派这些事件(事件作为参数被指派)
 */

import java.awt.*;
import javax.swing.*;

public class SimpleFrameTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() { //swing组件必须由事件分派线程进行配置
            public void run() {
                SimpleFrame frame = new SimpleFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //定义关闭框架时的响应动作--退出
                frame.setVisible(true);  //框架默认是不可见的
            }
        });
    }
}

class SimpleFrame extends JFrame {
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 200;

    public SimpleFrame() {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);  //设置大小300*200像素，默认为0*0
    }
}
