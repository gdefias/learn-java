package json.jackson;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by Defias on 2020/08.
 * Description:
 */

public class TestIsbnDeserializer {
    public static void main(String[] args) throws Exception {
        InputStream input = new FileInputStream("javalibs/src/main/java/json/jackson/book.json");
        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

        // 反序列化时忽略不存在的JavaBean属性:
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Book book = mapper.readValue(input, Book.class);
        System.out.println(book.id);
        System.out.println(book.name);
        System.out.println(book.author);
        System.out.println(book.isbn);
        System.out.println(book.tags);
        System.out.println(book.pubDate);
        System.out.println(book.price);

        // 序列化为JSON:
        String json = mapper.writeValueAsString(book);
        System.out.println(json);
    }
}