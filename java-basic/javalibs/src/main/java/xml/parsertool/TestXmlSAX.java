package xml.parsertool;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.FileInputStream;
import java.io.InputStream;
/**
 * Created by Defias on 2017/3/8.
 *
 * xml文件解析： SAX解析

 要读取<name>节点的文本，就必须在解析过程中根据startElement()和endElement()定位当前正在读取的节点，可以使用栈结构保存，
 每遇到一个startElement()入栈，每遇到一个endElement()出栈，这样，读到characters()时我们才知道当前读取的文本是哪个节点的
 可见，使用SAX API仍然比较麻烦
 */

public class TestXmlSAX {


    public static void main (String[]args) throws Exception {
        InputStream input = new FileInputStream("javalibs/src/main/java/xml/outxml/book.xml");
        SAXParserFactory spf = SAXParserFactory.newInstance();
        SAXParser saxParser = spf.newSAXParser();
        saxParser.parse(input, new MyHandler());
    }

    static class MyHandler extends DefaultHandler {

        void print(Object... objs) {
            for (Object obj : objs) {
                System.out.print(obj);
                System.out.print(" ");
            }
            System.out.println();
        }

        @Override
        public void startDocument() throws SAXException {
            print("start document");
        }

        @Override
        public void endDocument() throws SAXException {
            print("end document");
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            print("start element:", localName, qName);
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            print("end element:", localName, qName);
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            print("characters:", new String(ch, start, length));
        }

        @Override
        public void error(SAXParseException e) throws SAXException {
            print("error:", e);
        }
    }
}

