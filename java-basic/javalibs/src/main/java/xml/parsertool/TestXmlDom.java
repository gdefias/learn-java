package xml.parsertool;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileInputStream;
import java.io.InputStream;
/**
 * Created by Defias on 2017/4/12.
 * xml文件解析： DOM解析器

 从根节点Document出发，可以遍历所有子节点，获取所有元素、属性、文本数据，还可以包括注释，这些节点被统称为Node，
 每个Node都有自己的Type，根据Type来区分一个Node到底是元素，还是属性，还是文本，等等

 */

public class TestXmlDom {

    public static void main(String[] args) throws Exception {
        InputStream input = new FileInputStream("javalibs/src/main/java/xml/outxml/book.xml");
        // 解析并获取Document对象:
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse(input);
        printNode(doc, 0);
    }

    static void printNode(Node n, int indent) {
        for (int i = 0; i < indent; i++) {
            System.out.print(' ');
        }
        switch (n.getNodeType()) {
            case Node.DOCUMENT_NODE:
                System.out.println("Document: " + n.getNodeName());
                break;
            case Node.ELEMENT_NODE:
                System.out.println("Element: " + n.getNodeName());
                break;
            case Node.TEXT_NODE:
                System.out.println("Text: " + n.getNodeName() + " = " + n.getNodeValue());
                break;
            case Node.ATTRIBUTE_NODE:
                System.out.println("Attr: " + n.getNodeName() + " = " + n.getNodeValue());
                break;
            case Node.CDATA_SECTION_NODE:
                System.out.println("CDATA: " + n.getNodeName() + " = " + n.getNodeValue());
                break;
            case Node.COMMENT_NODE:
                System.out.println("Comment: " + n.getNodeName() + " = " + n.getNodeValue());
                break;
            default:
                System.out.println("NodeType: " + n.getNodeType() + ", NodeName: " + n.getNodeName());
        }
        for (Node child = n.getFirstChild(); child != null; child = child.getNextSibling()) {
            printNode(child, indent + 1);
        }
    }
}
