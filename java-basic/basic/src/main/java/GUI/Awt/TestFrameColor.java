package GUI.Awt;

/**
 * Created by Defias on 2017/3/18.
 *
 * 显示空框架
 * Frame
 *
 * Java.awt.Color类定义颜色，提供了13中标准颜色常量
 * SystemColor类中预定义了很多颜色名字，封装了用户系统的各个元素的颜色（桌面背景色、标题背景色、文本颜色...）
 */

import java.awt.*;

public class TestFrameColor {
    public static void main(String args[]){
        testFrame2();
    }

    public static void testFrame1() {
        //显示一个Frame窗口
        Frame f = new Frame("hello1");
        f.add(new Button("Press Me"));
        f.setSize(500,500);  //设置Frame的宽和高
        f.setVisible(true);   //使Frame变为可见
    }

    public static void testFrame2() {
        //显示一个Frame+Panel窗口
        Frame f1 = new Frame("hello2");
        Panel p = new Panel();
        p.add(new Button("press me"));
        p.setBackground(Color.BLUE);  //设置Panel的背景颜色
        f1.add(p);  //把Panel加入到Frame中
        f1.setSize(1000,700);
        f1.setBackground(Color.YELLOW);  //设置Frame的背景颜色
        f1.setVisible(true);
    }
}
