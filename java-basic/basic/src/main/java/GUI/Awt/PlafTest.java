package GUI.Awt;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Defias
 * Date: 2018-05
 *
 * 处理鼠标点击按钮事件： 改变观感
 *
 * SwingUtilities
 * Swing提供了许多的在Swing组件内部使用的实用工具，SwingUtilities类就是其中一个。它提供了许多的涉及计算、转换、访问控制、布局等方面的方法
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PlafTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new PlafFrame();
                frame.setTitle("PlafTest");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}


class PlafFrame extends JFrame {
    private JPanel buttonPanel;
    public PlafFrame() {
        buttonPanel = new JPanel();

        //获得一个用于描述已安装的观感实现的对象数组
        UIManager.LookAndFeelInfo[] infos = UIManager.getInstalledLookAndFeels();
        for (UIManager.LookAndFeelInfo info : infos)
            makeButton(info.getName(), info.getClassName());  //每遍历到一个观感添加一个按钮，并给按钮设置监听器
                                                              //参数：观感的显示名称、观感实现类的名称

        add(buttonPanel);
        pack();
    }

    /**
     * Makes a button to change the pluggable look and feel.
     * @param name the button name
     * @param plafName the name of the look and feel class
     */
    void makeButton(String name, final String plafName) {
        // add button to panel
        JButton button = new JButton(name);
        buttonPanel.add(button);

        // set button action
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                // button action: switch to the new look and feel
                try {
                    UIManager.setLookAndFeel(plafName);  //利用给定的类名设置当前的观感
                    SwingUtilities.updateComponentTreeUI(PlafFrame.this); //当改变LookAndFeel外观时，通过调用这个方法来刷新整个UI
                                                                              //参数：外部内对象
                    pack();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}