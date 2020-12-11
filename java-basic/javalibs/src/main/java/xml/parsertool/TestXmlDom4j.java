package xml.parsertool;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import java.io.File;
import java.util.Iterator;
/**
 * Created by Defias on 2017/3/8.
 *
 * xml文件解析： DOM4J解析
 */

public class TestXmlDom4j {

    public static void main(String[] args) {
        String fileNam = "javalibs/src/main/java/xml/outxml/Test.xml";
        TestXmlDom4j dom4jdemo = new TestXmlDom4j();
        dom4jdemo.parserXml(fileNam);
    }

    public void parserXml(String fileName) {
        File inputXml = new File(fileName);
        SAXReader saxReader = new SAXReader();

        try {
            Document document = saxReader.read(inputXml);
            Element users = document.getRootElement();
            for (Iterator i = users.elementIterator(); i.hasNext();) {
                Element user = (Element)i.next();
                for (Iterator j = user.elementIterator(); j.hasNext();) {
                    Element node = (Element)j.next();
                    System.out.println(node.getName() + ":" + node.getText());
                }
                System.out.println();
            }
        } catch (DocumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
