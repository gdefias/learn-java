package json.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Defias on 2020/08.
 * Description:

 Jackson 日期转化

 默认情况下，Jackson会将java.util.Date对象序列化为其long型的值，该值是自1970年1月1日以来的毫秒数
 Jackson还支持将日期格式化为字符串

 */
public class TestJacksonDate {

    public static void main(String[] args) throws Exception {
        //test1();
        test2();
    }

    //Date-->long
    public static void test1() throws JsonProcessingException {
        Transaction transaction = new Transaction("transfer", new Date());

        ObjectMapper objectMapper = new ObjectMapper();
        String output = objectMapper.writeValueAsString(transaction);
        System.out.println(output);
    }

    //Date-->String
    public static void test2() throws JsonProcessingException {
        Transaction transaction = new Transaction("transfer", new Date());

        ObjectMapper objectMapper = new ObjectMapper();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        objectMapper.setDateFormat(dateFormat);

        String output2 = objectMapper.writeValueAsString(transaction);
        System.out.println(output2);

    }
}
