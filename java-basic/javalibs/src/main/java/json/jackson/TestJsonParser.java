package json.jackson;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

import java.io.File;

/**
 * Created by Defias on 2020/08.
 * Description: JsonParser

JsonParser类是一个底层一些的JSON解析器。它类似于XML的Java StAX解析器，差别是JsonParser解析JSON而不解析XML

 JsonParser工作方式是：将JSON分成一个记号序列，让你迭代记号（Token）序列进行解析。Parser的Token有：
 START_OBJECT
 END_OBJECT
 START_ARRAY
 END_ARRAY
 FIELD_NAME
 VALUE_EMBEDDED_OBJECT
 VALUE_FALSE
 VALUE_TRUE
 VALUE_NULL
 VALUE_STRING
 VALUE_NUMBER_INT
 VALUE_NUMBER_FLOAT

 */
public class TestJsonParser {
    public static void main(String[] args) throws Exception {
        test1();
    }

    public static void test1() throws Exception {
        String carJson = "{ \"brand\" : \"Mercedes\", \"doors\" : 5 }";
        JsonFactory factory = new JsonFactory();
        JsonParser  parser  = factory.createParser(carJson);

        Car car = new Car();

        //只要JsonParser的isClosed()方法返回false，那么JSON源中仍然会有更多的令牌
        while(!parser.isClosed()) {
            JsonToken jsonToken = parser.nextToken();

            if(JsonToken.FIELD_NAME.equals(jsonToken)) {
                //getCurrentName()方法将返回当前字段名称
                String fieldName = parser.getCurrentName();
                System.out.println(fieldName);

                jsonToken = parser.nextToken();

                if("brand".equals(fieldName)){
                    car.brand = parser.getValueAsString();  //getValueAsString()返回当前令牌值作为字符串
                } else if ("doors".equals(fieldName)) {
                    car.doors = parser.getValueAsInt();  //getValueAsInt()返回当前令牌值作为int值
                }
            }
        }

        System.out.println("car.brand = " + car.brand);
        System.out.println("car.doors = " + car.doors);
    }
}
