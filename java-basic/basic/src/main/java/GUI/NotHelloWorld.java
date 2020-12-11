package GUI;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Defias
 * Date: 2018-05
 *
 * 在组件中显示信息
 */
import javax.swing.*;
import java.awt.*;


public class NotHelloWorld {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new NotHelloWorldFrame();
                frame.setTitle("NotHelloWorld");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

/**
 * A frame that contains a message panel
 */
class NotHelloWorldFrame extends JFrame {
    public NotHelloWorldFrame() {
        add(new NotHelloWorldComponent());  //将一个给定的组件添加到框架
        pack();   //调整窗口大小
    }
}

/**
 * A component that displays a message.
 */
//绘制一个组件，需要定义一个扩展JComponent的类，并覆盖其中的paintComponent方法
class NotHelloWorldComponent extends JComponent {
    public static final int MESSAGE_X = 75;
    public static final int MESSAGE_Y = 100;

    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 200;

    public void paintComponent(Graphics g) {    //Graphics保存着绘制图像和文本的设置
        g.drawString("Not a Hello, World program", MESSAGE_X, MESSAGE_Y);  //在指定位置绘制字符串
    }

    public Dimension getPreferredSize() {  //设置组件有多大
        return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }
}