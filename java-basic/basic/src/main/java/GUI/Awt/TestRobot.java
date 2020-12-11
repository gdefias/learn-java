package GUI.Awt;

/**
 * Created by Defias on 2017/3/18.
 *
 * 控制鼠标和键盘
 *
 * Java.awt.Robot类用于控制鼠标和键盘
 方法：
 BufferedImage createScreenCapture(Rectangle screenRect)
 提供类似于键盘上的PrintScreen键的功能，将指定矩形区域内的屏幕像素copy下来产生一个BufferedImage。可以将这个方法用在图形程序中，或是用它来实现远端屏幕传输，可做成远端电脑监控程序等

 void delay(int ms)
 用来将当前的程序(thread)休眠(sleep)若干毫秒(ms)。可用来控制程序的延时

 Color getPixelColor(int x, int y)
 取得给定屏幕坐标像素位置的颜色值

 void keyPress(int keycode)
 void keyRelease(int keycode)
 这两个方法用来产生指定键的按键按下与抬起动作,可用于程序的自动演示、测试等

 void mouseMove(int x, int y)
 将鼠标光标移动到指定的屏幕坐标,可用于程序的自动演示、测试等

 void mousePress(int buttons)
 void mouseRelease(int buttons)
 void mouseWheel(int wheelAmt)
 上面的三种方法，产生指定鼠标按钮的按下，抬起，及滚轮动作,一样也可用于程序的自动演示、测试等
 */

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TestRobot {
    public static void main(String[] args) throws AWTException, InterruptedException, IOException {

        Robot robot = new Robot();

        //设置Robot产生一个动作后的休眠时间,否则执行过快
        robot.setAutoDelay(1000);

        //获取屏幕分辨率
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        System.out.println(d);

        //截图
        Rectangle screenRect = new Rectangle(d);
        BufferedImage bufferedImage = robot.createScreenCapture(screenRect);

        ////指定要抓取的区域
        //BufferedImage screencapture = new Robot().createScreenCapture(new Rectangle( 15, 15, 150, 150));
        //
        ////抓取指定控件
        //BufferedImage image = new Robot().createScreenCapture(new Rectangle(myframe.getX(), myframe.getY(), myframe.getWidth(), myframe.getHeight()));

        //保存截图
        File file = new File("screenRect.png");
        ImageIO.write(bufferedImage, "png", file);

        //移动鼠标
        robot.mouseMove(500, 500);

        //点击鼠标
        //鼠标左键
        System.out.println("单击");
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_MASK);

        //鼠标右键
        System.out.println("右击");
        robot.mousePress(InputEvent.BUTTON3_MASK);
        robot.mouseRelease(InputEvent.BUTTON3_MASK);

        //按下ESC，退出右键状态
        System.out.println("按下ESC");
        robot.keyPress(KeyEvent.VK_ESCAPE); //按下空格键
        robot.keyRelease(KeyEvent.VK_ESCAPE);  //释放空格键

        //滚动鼠标滚轴
        System.out.println("滚轴");
        robot.mouseWheel(5);

        //按下Alt+TAB键
        robot.keyPress(KeyEvent.VK_ALT);
        for (int i = 1; i <= 2; i++) {
            robot.keyPress(KeyEvent.VK_TAB);
            robot.keyRelease(KeyEvent.VK_TAB);
        }
        robot.keyRelease(KeyEvent.VK_ALT);
    }

    public static void closeApplication(Robot robot) {
        robot.keyPress(KeyEvent.VK_ALT);
        robot.keyPress(KeyEvent.VK_F4);  //F4键
        robot.keyRelease(KeyEvent.VK_ALT);
        robot.keyRelease(KeyEvent.VK_F4);
    }
}
