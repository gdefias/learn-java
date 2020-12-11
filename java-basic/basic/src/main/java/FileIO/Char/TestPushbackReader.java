package FileIO.Char;

/**
 * Created with IntelliJ IDEA.
 * Description: 回推流  PushbackReader
 * User: Defias
 * Date: 2019-09
 */

import java.io.FileReader;
import java.io.IOException;
import java.io.PushbackReader;

public class TestPushbackReader {

    public static void main(String[] args) {
        try (
                // 创建一个PushbackReader对象，指定推回缓冲区的长度为64
                PushbackReader pr = new PushbackReader(new FileReader(
                        "base/src/pushback.txt"), 64);

        ) {
            char[] buf = new char[32];
            // 用以保存上次读取字符串的内容
            String lastContent = "";
            int hasRead = 0;

            // 循环读取文件内容
            while ((hasRead = pr.read(buf)) > 0) {
                // 将读取的内容转化为字符串
                String content = new String(buf, 0, hasRead);
                int targetIndex = 0;

                // 将上次读取的字符串和本次读取的字符串拼接起来
                // 查看是否包含目标字符串，
                // 如果包含目标字符串
                if ((targetIndex = (lastContent + content)
                        .indexOf("new PushbackReader")) > 0) {
                    // 将本次的内容和上次的内容一起推回缓冲区
                    pr.unread((lastContent + content).toCharArray());

                    // 重新定义一个长度为targetIndex的char类型的数组
                    if (targetIndex > 32) {
                        buf = new char[targetIndex];
                    }

                    // 再次读取指定长度的内容，即目标字符串之前的内容
                    pr.read(buf, 0, targetIndex);

                    // 答应读取指定长度的内容
                    System.out.println(new String(buf, 0, targetIndex));
                    System.exit(0);
                } else {

                    // 打印上次读取的内容
                    System.out.println(lastContent);
                    // 将本次读取的内容设置为上次读取的内容
                    lastContent = content;

                }

            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }
}
