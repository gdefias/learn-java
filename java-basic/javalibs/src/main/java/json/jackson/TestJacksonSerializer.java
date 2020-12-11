package json.jackson;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Defias on 2020/08.
 * Description: Jackson

 Jackson ObjectMapper从JSON中获取Java对象(反序列化)
 readValue()


 Jackson ObjectMapper也可以用于从对象生成JSON（序列化）
 可以使用以下方法之一进行操作：
 writeValue()
 writeValueAsString()
 writeValueAsBytes()

 */
public class TestJacksonSerializer {
    public static void main(String[] args) throws Exception {
        //test1();
        //test2();
        test3();
    }


    //从JSON中获取Java对象(反序列化)
    public static void test1() throws MalformedURLException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String carJson ="{ \"brand\" : \"Mercedes\", \"doors\" : 5 }";
        Reader reader = new StringReader(carJson);
        File file = new File("javalibs/src/main/java/json/jackson/car.json");
        URL url = new URL("file:javalibs/src/main/java/json/jackson/car.json");
        InputStream input = new FileInputStream("javalibs/src/main/java/json/jackson/car.json");
        byte[] bytes = carJson.getBytes("UTF-8");

        String jsonArray = "[{\"brand\":\"ford\", \"doors\" : 11}, {\"brand\":\"Fiat\"}]";

        String carJsonmul ="{ \"brand\" : \"Mercedes\", \"doors\" : 5, \"unkonw\" : 123 }";
        String carJsonnull = "{ \"brand\":\"Toyota\", \"doors\":null }";
        String json = "{ \"brand\" : \"Ford\", \"doors\" : 6 }";


        try {
            //JSON字符串-->Java对象
            //Car car = objectMapper.readValue(reader, Car.class);

            //JSON 字符输入流-->Java对象
            //Car car = objectMapper.readValue(carJson, Car.class);

            //JSON文件-->Java对象
            //Car car = objectMapper.readValue(file, Car.class);

            //URL--->Java对象 也可以使用HTTP URL
            //Car car = objectMapper.readValue(url, Car.class);

            //JSON字节输入流-->Java对象
            //Car car = objectMapper.readValue(input, Car.class);

            //JSON二进制数组-->Java对象
            //Car car = objectMapper.readValue(bytes, Car.class);

            //JSON数组字符串-->Java对象数组
            //Car[] cars1 = objectMapper.readValue(jsonArray, Car[].class);

            //JSON数组字符串-->Java对象List
//            List<Car> cars2 = objectMapper.readValue(jsonArray, new TypeReference<List<Car>>(){});
//
//            for(Car car: cars2) {
//                System.out.println("car brand = " + car.getBrand());
//                System.out.println("car doors = " + car.getDoors());
//            }

            //JSON字符串-->Map
            //将JSON对象读入Java Map。 JSON对象中的每个字段都将成为Java Map中的键值对
//            Map<String, Object> jsonMap = objectMapper.readValue(carJson, new TypeReference<Map<String,Object>>(){});
//            System.out.println(jsonMap);

            //忽略未知的JSON字段（允许JSON中的字段多于相应的Java对象中的字段）
//            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//            Car car = objectMapper.readValue(carJsonmul, Car.class);

            //不允许基本类型为null
            //在FAIL_ON_NULL_FOR_PRIMITIVES配置值设置为true的情况下，尝试将空JSON字段解析为基本类型Java字段时会遇到异常
//            objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, true);
//            Car car = objectMapper.readValue(carJsonnull, Car.class);

            //自定义反序列化
            SimpleModule module = new SimpleModule("CarDeserializer",
                    new Version(3, 1, 8, null, null, null));
            module.addDeserializer(Car.class, new CarDeserializer(Car.class));

            objectMapper.registerModule(module);
            Car car = objectMapper.readValue(json, Car.class);

            System.out.println("car brand = " + car.getBrand());
            System.out.println("car doors = " + car.getDoors());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //将对象写入JSON（序列化）
    public static void test2() throws MalformedURLException, IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Car car = new Car();
        car.setBrand("BMW");
        car.setDoors(4);

        String json = objectMapper.writeValueAsString(car);
        System.out.println(json);

        objectMapper.writeValue(new FileOutputStream(
                "javalibs/src/main/java/json/jackson/output-2.json"), car);

    }

    //自定义序列化
    public static void test3() throws MalformedURLException, IOException {
        CarSerializer carSerializer = new CarSerializer(Car.class);
        ObjectMapper objectMapper = new ObjectMapper();

        SimpleModule module = new SimpleModule("CarSerializer",
                new Version(2, 1, 3, null, null, null));
        module.addSerializer(Car.class, carSerializer);
        objectMapper.registerModule(module);

        Car car = new Car();
        car.setBrand("Mercedes");
        car.setDoors(5);

        String carJson = objectMapper.writeValueAsString(car);
        System.out.println(carJson);
    }


}
