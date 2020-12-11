package GUI;

/**
 * Created by Defias on 2017/3/18.
 *
 * JFrame
 *
 * Toolkit
 *
 * 复制文本到剪贴板
 *
 */

import javax.swing.JFrame;
import javax.swing.JTextArea;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class TestClipboard {
    public static void main(String[] args) {
        JFrame jf = new JFrame();
        jf.setSize(400,400);
        jf.setDefaultCloseOperation(3);
        JTextArea jta = new JTextArea();
        jf.getContentPane().add(jta);

        Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();  //Clipboard剪切板,具有储存的内容 可以取得和设置它的内容
        ///////////////////////存储到剪切板
        StringSelection ss = new StringSelection("哈哈哈哈哈哈"); //StringSelection用来向剪切板传输文本数据的  被选择好的
        cb.setContents(ss, ss);

        //////////////////////////从剪切板中取出
        DataFlavor df = DataFlavor.stringFlavor;  //DataFlavor表示的是存储数据的格式,文本,图像...
        Transferable tt = cb.getContents(df);  //Clipboard的内容被封装在Transferable里
        try {
            System.out.println(tt.getTransferData(df));  //输出剪切的内容
        } catch (UnsupportedFlavorException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        jf.setVisible(true);
    }
}
