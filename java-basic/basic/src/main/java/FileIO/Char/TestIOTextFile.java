package FileIO.Char;
import java.io.*;
import java.util.*;
/**
 * Created by Defias on 2020/07.
 * Description: 文件读写工具
 */
public class TestIOTextFile extends ArrayList<String> {       //作为Arraylist

    public static void main(String[] args) {
        String file = read("base/src/TextFile.txt");
        write("base/src/TFtest.txt", file);

        TestIOTextFile text = new TestIOTextFile("base/src/TFtest.txt");
        text.write("base/src/TFtest2.txt");

        // Break into unique sorted list of words:
        TreeSet<String> words = new TreeSet<String>(
                new TestIOTextFile("base/src/TextFile.txt", "\\W+"));
        //headSet:返回此set的部分视图，其元素严格小于toElement
        System.out.println(words.headSet("k"));
    }

    // Read a file as a single string:
    public static String read(String fileName) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader in= new BufferedReader(new FileReader(
                    new File(fileName).getAbsoluteFile()));
            try {
                String s;
                while((s = in.readLine()) != null) {
                    sb.append(s);
                    sb.append("\n");
                }
            } finally {
                in.close();
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
    }

    // Write a single file in one method call:
    public static void write(String fileName, String text) {
        try {
            PrintWriter out = new PrintWriter(
                    new File(fileName).getAbsoluteFile());
            try {
                out.print(text);
            } finally {
                out.close();
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Read a file, split by any regular expression:
    public TestIOTextFile(String fileName, String splitter) {
        super(Arrays.asList(read(fileName).split(splitter)));
        // Regular expression split() often leaves an empty
        // String at the first position:
        if(get(0).equals(""))
            remove(0);
    }

    // Normally read by lines:
    public TestIOTextFile(String fileName) {
        this(fileName, "\n");
    }

    public void write(String fileName) {
        try {
            PrintWriter out = new PrintWriter(
                    new File(fileName).getAbsoluteFile());
            try {
                for(String item : this)
                    out.println(item);
            } finally {
                out.close();
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
}
