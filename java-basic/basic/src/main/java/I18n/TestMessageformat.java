package I18n;

import java.text.MessageFormat;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Defias
 * Date: 2018-05
 *
 * Messageformat
 * 提供一种与语言无关的拼接消息的方式。通过这种拼接方式，将最终呈现返回给使用者
 *
 */

public class TestMessageformat {
    public static void main(String[] args) {
        String pattern1 = "{0}，你好！你于  {1} 消费  {2} 元。";
        String pattern2 = "At {1,time,short} On {1,date,long}，{0} paid {2,number, currency}.";

        Object[] params = { "Jack", new GregorianCalendar().getTime(), 8888 };

        String msg1 = MessageFormat.format(pattern1, params);
        System.out.println(msg1);

        MessageFormat mf = new MessageFormat(pattern2, Locale.US);
        String msg2 = mf.format(params);
        System.out.println(msg2);
    }
}
