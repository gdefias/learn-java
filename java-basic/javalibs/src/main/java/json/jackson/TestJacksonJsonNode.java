package json.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.util.Iterator;

/**
 * Created by Defias on 2020/08.
 * Description: JsonNode

 Jackson具有内置的树模型，可用于表示JSON对象
 如果不知道接收到的JSON的格式，或者由于某种原因而不能（或者只是不想）创建一个类来表示它，那么就要用到Jackson的树模型

 com.fasterxml.jackson.databind.JsonNode是Jackson的JSON树形模型（对象图模型）
 Jackson可以将JSON读取到JsonNode实例中，然后将JsonNode写入JSON

 ObjectNode
 JsonNode类是不可变的。这意味着，实际上不能直接构建JsonNode实例的对象图。而是创建JsonNode子类ObjectNode的对象图
 例如: 设置属性值和子JsonNode实例等。由于是不可变的，因此无法直接使用JsonNode来实现，而是创建一个ObjectNode实例

 */
public class TestJacksonJsonNode {
    public static void main(String[] args) throws Exception {
        //test1();
        //test2();
        //test3();
        test4();
    }


    public static void test1() throws JsonProcessingException {

        //JSON-->JsonNode
        String carJson = "{ \"brand\" : \"Mercedes\", \"doors\" : 5," +
                "  \"owners\" : [\"John\", \"Jack\", \"Jill\"]," +
                "  \"nestedObject\" : { \"field\" : \"value\" } }";
        ObjectMapper objectMapper = new ObjectMapper();


        //JsonNode jsonNode = objectMapper.readValue(carJson, JsonNode.class);
        JsonNode jsonNode = objectMapper.readTree(carJson);

        //将JSON解析为JsonNode（或JsonNode实例树）后，就可以浏览JsonNode树模型
        JsonNode brandNode = jsonNode.get("brand");
        String brand = brandNode.asText();
        System.out.println("brand = " + brand);

        JsonNode doorsNode = jsonNode.get("doors");
        int doors = doorsNode.asInt();
        System.out.println("doors = " + doors);

        JsonNode array = jsonNode.get("owners");
        JsonNode item = array.get(0);
        String john = item.asText();
        System.out.println("john  = " + john);

        JsonNode child = jsonNode.get("nestedObject");
        JsonNode childField = child.get("field");
        String field = childField.asText();
        System.out.println("field = " + field);
    }


    public static void test2() throws JsonProcessingException {

        //Java对象-->JsonNode
        ObjectMapper objectMapper = new ObjectMapper();
        Car car = new Car();
        car.brand = "Cadillac";
        car.doors = 4;
        JsonNode carJsonNode = objectMapper.valueToTree(car);

        //JsonNode-->JSON
        String json = objectMapper.writeValueAsString(carJsonNode);
        System.out.println(json);


        //JsonNode-->Java对象
        String carJson = "{ \"brand\" : \"Mercedes\", \"doors\" : 5 }";
        JsonNode carJsonN = objectMapper.readTree(carJson);

        Car car1 = objectMapper.treeToValue(carJsonN, Car.class);

        //获取JsonNode字段
        //get()方法始终返回JsonNode来表示字段
        JsonNode field1 = carJsonN.get("brand");
        JsonNode field2 = carJsonN.get("doors");
        System.out.println(field1.asText());
        System.out.println(field2.asInt());
    }


    public static void test3() throws JsonProcessingException {

        //在路径中获取JsonNode字段
        String strJson = "{\"identification\" : {\"name\":\"James\", \"ssn\": \"ABC123552\"}, \"doors\" : 5, " +
                " \"flag\" : \"3212\", \"brand\" : \"Mercedes\", \"f2\":null}";
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(strJson);

        //JSON路径表达式指定从根JsonNode到您要访问其值的字段的完整路径
        JsonNode nameNode = jsonNode.at("/identification/name");
        System.out.println(nameNode.asText());

        //JsonNode类包含一组可以将字段值转换为另一种数据类型的方法
        System.out.println(jsonNode.get("doors").asInt());
        System.out.println(jsonNode.get("doors").asText());

        System.out.println(jsonNode.get("flag").asInt());

        Long l1 = jsonNode.get("flag").asLong();
        System.out.println(l1);

        //JsonNode中的字段为null
        String f2Value1 = jsonNode.get("f2").asText();
        System.out.println(f2Value1);

        //在尝试转换它时可以提供默认值
        String f2Value2 = jsonNode.get("f2").asText("Default");
        System.out.println(f2Value2);


        //JsonNode类具有一个名为fieldNames()的方法，该方法返回一个Iterator，可以迭代JsonNode的所有字段名称
        Iterator<String> fieldNames = jsonNode.fieldNames();

        while(fieldNames.hasNext()) {
            String fieldName = fieldNames.next();
            JsonNode field = jsonNode.get(fieldName);
            System.out.println(field.asText());
        }
    }

    public static void test4() throws JsonProcessingException {

        //在路径中获取JsonNode字段
        String strJson = "{\"identification\" : {\"name\":\"James\", \"ssn\": \"ABC123552\"}, \"doors\" : 5, " +
                " \"flag\" : \"3212\", \"brand\" : \"Mercedes\", \"f2\":null}";
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(strJson);

        ObjectNode parentNode = objectMapper.createObjectNode();
        parentNode.set("child1", jsonNode);

        //ObjectNode类还具有一组方法，可以直接为字段put(设置)值
        parentNode.put("field1", "value1");
        parentNode.put("field2", 123);
        parentNode.put("field3", 999.999);

        //remove()方法可用于从ObjectNode中删除字段
        parentNode.remove("field2");

        String json = objectMapper.writeValueAsString(parentNode);
        System.out.println(json);
    }
}
