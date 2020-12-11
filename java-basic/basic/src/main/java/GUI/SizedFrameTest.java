package GUI;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Defias
 * Date: 2018-05
 *
 * 确定合适的框架大小
 *
 * Toolkit类：包含很多与本地窗口系统打交道的方法
 *
 */

import java.awt.*;
import javax.swing.*;

public class SizedFrameTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new SizedFrame();
                frame.setTitle("SizedFrame");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

class SizedFrame extends JFrame {
    public SizedFrame() {
        // get screen dimensions
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();  //以Dimension形式放回本地屏幕大小
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;

        // set frame width, height and let platform pick screen location
        setSize(screenWidth / 2, screenHeight / 2);
        setLocationByPlatform(true);  //设置窗口位置，通常是距最后一个显示窗口很少偏移量的位置

        // set frame icon
        Image img = new ImageIcon("icon.gif").getImage();  //获得图标的图像
        setIconImage(img);  //设置框架的图标，mac上貌似没显示
    }
}