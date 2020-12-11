package GUI;

/**
 * Created by Defias on 2017/3/18.
 *
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TestJButton extends JFrame implements ActionListener {
    private JLabel jLabel;
    private JButton jButton;
    private String labelPrefix ="Number of button clicks: ";
    private int numClicks = 0;

    public TestJButton(String title){
        super(title);

        jLabel = new JLabel(labelPrefix + "0");
        jButton = new JButton("I am a Swing button!");

        // 创建一个快捷键: 用户按下Alt-i键等价于点击该Button
        jButton.setMnemonic('i');

        //设置鼠标移动到该Button时的提示信息
        jButton.setToolTipText("Press me");

        jButton.addActionListener(this);

        /* //wrong
           setLayout(new GridLayout(2, 1));
           add(jLabel) ;
           add(jButton);
        */

        Container contentPane=getContentPane();  //每个JFrame都有一个与之相关联的内容面板getContentPane
        contentPane.setLayout(new GridLayout(2, 1));
        contentPane.add(jLabel) ;
        contentPane.add(jButton);

        pack();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {
        numClicks++;
        jLabel.setText(labelPrefix + numClicks);
    }

    public static void main(String[] args) {
        new TestJButton("Hello");
    }
}
