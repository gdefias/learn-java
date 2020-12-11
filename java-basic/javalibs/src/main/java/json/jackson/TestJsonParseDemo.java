package json.jackson;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

/**
 * Created by Defias on 2020/08.
 * Description:
 */
public class TestJsonParseDemo {

    public static void main(String[] args) {
        //readFromFile();
        writeSimpleToFile();
        writeComplexToFile();
    }

    public static void readFromFile() {
        String path = "javalibs/src/main/java/json/jackson/user.json";
        JsonFactory jsonFactory = new JsonFactory();

        try {
            JsonParser parser = jsonFactory.createParser(new File(path));
            System.out.println(parser.nextToken().asString());    //进入JSON的“{”

            while (parser.nextToken() != JsonToken.END_OBJECT){
                String fieldName = parser.getCurrentName();
                parser.nextToken();
                if(fieldName.equals("name")) {
                    System.out.println(fieldName + ":");
                    while (parser.nextToken() != JsonToken.END_OBJECT) {
                        parser.nextToken();
                        String field = parser.getCurrentName();
                        System.out.println("\t" + field + ": " + parser.getValueAsString());
                    }
                } else {
                    System.out.println(fieldName + ": " + parser.getText());
                }
            }
            parser.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void writeSimpleToFile() {
        //直接new JsonFactory
        JsonFactory jsonFactory = new JsonFactory();
        OutputStream outputStream = null;

        try {
            outputStream = new FileOutputStream("javalibs/src/main/java/json/jackson/generate_simple.json");
            JsonGenerator generator = jsonFactory.createGenerator(outputStream, JsonEncoding.UTF8);
            //JsonGenerator generator = jsonFactory.createGenerator(System.out, JsonEncoding.UTF8);

            generator.writeStartObject();

            generator.writeStringField("brand", "Mercedes");
            generator.writeNumberField("doors", 5);

            generator.writeObjectFieldStart("owner");
            generator.writeStringField("first", "Gatsby");
            generator.writeStringField("last", "Newton");
            generator.writeEndObject();

            generator.writeArrayFieldStart("component");
            generator.writeString("engine");
            generator.writeString("brake");
            generator.writeEndArray();
            generator.writeEndObject();

            generator.flush();
            generator.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void writeComplexToFile() {
        //ObjectMapper对象中获取的JsonFactory
        JsonFactory jsonFactory = new ObjectMapper().getJsonFactory();
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream("javalibs/src/main/java/json/jackson/generate_complex.json");
            JsonGenerator generator = jsonFactory.createGenerator(outputStream, JsonEncoding.UTF8);
            generator.writeStartObject();

            generator.writeStringField("brand", "Mercedes");
            generator.writeNumberField("doors", 5);

            // Write a object.
            generator.writeObjectField("owner", new Car2.Owner("Gatsby", "Newton"));
            generator.writeObjectField("component", new String[]{"engine", "brake"});
            generator.writeEndObject();

            generator.flush();
            generator.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
