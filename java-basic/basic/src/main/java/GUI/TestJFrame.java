package GUI;

/**
 * Created by Defias on 2017/3/18.
 *
 * 显示一个空框架
 *
 */

import java.awt.EventQueue;
import javax.swing.JFrame;

public class TestJFrame {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {  //EventQueue是一个与平台无关的类，它将来自于底层同位体类和受信任的应用程序类的事件列入队列
                                                 //static void invokeLater(Runnable runnable) 导致runnable的run方法在EventQueue的指派线程上被调用
            public void run() {
                SimpleFrame frame = new SimpleFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }

    static class SimpleFrame extends JFrame {
        private static final int DEFAULT_WIDTH = 300;
        private static final int DEFAULT_HEIGHT = 200;

        public SimpleFrame() {
            setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        }
    }
}


