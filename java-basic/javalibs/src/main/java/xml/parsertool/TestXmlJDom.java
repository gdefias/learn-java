package xml.parsertool;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import java.io.IOException;
import java.util.List;
/**
 * Created by Defias on 2017/3/8.
 *
 * xml文件解析： JDOM解析
 *
 */

public class TestXmlJDom {

    public static void main(String[] args) {
        String fileNam = "javalibs/src/main/java/xml/outxml/Test.xml";
        TestXmlJDom jdomdemo = new TestXmlJDom();
        jdomdemo.parserXml(fileNam);
    }

    public void parserXml(String fileName) {
        SAXBuilder builder = new SAXBuilder();
        try {
            Document document = builder.build(fileName);
            Element users = document.getRootElement();
            List userList = users.getChildren("user");

            for (int i = 0; i < userList.size(); i++) {
                Element user = (Element) userList.get(i);
                List userInfo = user.getChildren();

                for (int j = 0; j < userInfo.size(); j++) {
                    System.out.println(((Element)userInfo.get(j)).getName()
                            + ":" + ((Element) userInfo.get(j)).getValue());

                }
                System.out.println();
            }
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
