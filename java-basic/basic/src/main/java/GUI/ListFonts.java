package GUI;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Defias
 * Date: 2018-05
 *
 * 列出系统中所有可用的字体
 */
import java.awt.*;

public class ListFonts {
    public static void main(String[] args) {
        String[] fontNames = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

        for (String fontName : fontNames)
            System.out.println(fontName);
    }
}
