package FileIO;
import nu.xom.*;
import java.io.*;
import java.util.*;
/**
 * Created by Defias on 2020/07.
 * Description: 将数据转换为XML格式
 */

public class TestIOXML {
    public static void main(String[] args) throws Exception {
        //test1();
        test2();

    }

    //将数据转换为XML
    public static void test1() throws Exception {
        List<Person> people = Arrays.asList(
                new Person("Dr. Bunsen", "Honeydew"),
                new Person("Gonzo", "The Great"),
                new Person("Phillip J.", "Fry"));
        System.out.println(people);

        Element root = new Element("people");
        for(Person p : people)
            root.appendChild(p.getXML());
        Document doc = new Document(root);
        Person.format(System.out, doc);
        Person.format(new BufferedOutputStream(new FileOutputStream("base/src/People.xml")), doc);
    }

    //从XML中恢复数据
    public static void test2() throws Exception {
        People p = new People("base/src/People.xml");
        System.out.println(p);
    }
}



class Person {
    private String first, last;
    public Person(String first, String last) {
        this.first = first;
        this.last = last;
    }

    // Constructor to restore a Person from an XML Element:
    public Person(Element person) {
        first= person.getFirstChildElement("first").getValue();
        last = person.getFirstChildElement("last").getValue();
    }

    // Produce an XML Element from this Person object:
    public Element getXML() {
        Element person = new Element("person");
        Element firstName = new Element("first");
        firstName.appendChild(first);
        Element lastName = new Element("last");
        lastName.appendChild(last);
        person.appendChild(firstName);
        person.appendChild(lastName);
        return person;
    }



    public String toString() {
        return first + " " + last;
    }

    // Make it human-readable:
    public static void format(OutputStream os, Document doc) throws Exception {
        Serializer serializer= new Serializer(os,"ISO-8859-1");
        serializer.setIndent(4);
        serializer.setMaxLength(60);
        serializer.write(doc);
        serializer.flush();
    }
}


class People extends ArrayList<Person> {
    public People(String fileName) throws Exception  {
        Document doc = new Builder().build(fileName);
        Elements elements = doc.getRootElement().getChildElements();
        for(int i = 0; i < elements.size(); i++)
            add(new Person(elements.get(i)));
    }
}