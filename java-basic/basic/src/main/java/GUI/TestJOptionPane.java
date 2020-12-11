package GUI;

/**
 * Created by Defias on 2017/3/18.
 *
 * JOptionPane
 * 消息提示框
 *
 */
import javax.swing.JOptionPane;

public class TestJOptionPane {
    public static void main(String[] args) {

        //showMessageDialog  显示一个带有OK按钮的模态对话框
        JOptionPane.showMessageDialog(null, "友情提示");

        JOptionPane.showMessageDialog(null, "提示消息", "标题",JOptionPane.WARNING_MESSAGE);

        JOptionPane.showMessageDialog(null, "提示消息.", "标题",JOptionPane.ERROR_MESSAGE);

        JOptionPane.showMessageDialog(null, "提示消息.", "标题",JOptionPane.PLAIN_MESSAGE);

        //showOptionDialog  可以改变显示在按钮上的文字。你还可以执行更多的个性化操作
        int n = JOptionPane.showConfirmDialog(null, "你高兴吗?", "标题",JOptionPane.YES_NO_OPTION);
        System.out.println(n);

        Object[] options ={ "好啊！", "去一边！" };
        int m = JOptionPane.showOptionDialog(null, "我可以约你吗？", "标题",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        System.out.println(m);

        //程序实例1
        String str = JOptionPane.showInputDialog("输入用钱数:");
        float  money = Float.parseFloat(str);
        int intmoney = (int)(money * 100);
        int dollar = intmoney/100;  intmoney %= 100;
        int quarter = intmoney/25;  intmoney %= 25;
        int dime = intmoney/10;     intmoney %= 10;
        int nikel = intmoney/5;     intmoney %= 5;
        int penny = intmoney;
        JOptionPane.showMessageDialog(null,money+"￥ = "+dollar+"（元）+ "+quarter+"（2.5角）+ "+dime+"角 + "+nikel+"(0.5分) + "+penny+"分" );

        //程序实例2
        String strr = JOptionPane.showInputDialog("请输入华氏度 （°C）：");
        double fact = Double.parseDouble(strr);
        JOptionPane.showMessageDialog(null, "摄氏度 =" + 5.0/9*(fact-32));
    }

}
