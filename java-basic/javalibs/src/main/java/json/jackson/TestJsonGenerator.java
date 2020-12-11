package json.jackson;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;

import java.io.File;
import java.io.IOException;

/**
 * Created by Defias on 2020/08.
 * Description: JsonGenerator

 JsonGenerator
 用于从Java对象（或代码从中生成JSON的任何数据结构）生成JSON

 Feature
 JsonGenerator的内部枚举类，共10个枚举值，枚举值均为bool类型，括号内为默认值

 这个Feature的每个枚举值都控制着JsonGenerator写JSON时的不同行为，并且可分为三大类（源码处我也有标注）：
 Low-level I/O
 底层I/O流相关 Jackson的流式API指的是I/O流，因此就涉及到关流、flush刷新流等操作

 Quoting-related
 双引号""引用相关。JSON规范规定key都必须有双引号，但这对于某些场景下并不需要

 Schema/Validity support
 约束/规范/校验相关。JSON作为K-V结构的数据，那么允许相同key出现吗？这便由这些特征去控制


 可以用Feature去控制JsonGenerator的写行为，不同的特征值控制着不同的行为
 在实际使用时可针对不同的需求，定制出不同的JsonGenerator实例


 */
public class TestJsonGenerator {
    public static void main(String[] args) throws IOException {
        test1();
    }

    public static void test1() throws IOException {
        JsonFactory factory = new JsonFactory();

        //createGenerator()方法的第一个参数是生成的JSON的目标；第二个参数是生成JSON时使用的字符编
        //JsonGenerator generator = factory.createGenerator(
        //        new File("javalibs/src/main/java/json/jackson/output.json"), JsonEncoding.UTF8);

        JsonGenerator generator = factory.createGenerator(System.out, JsonEncoding.UTF8);

        generator.writeStartObject();  //将{写入输出
        generator.writeStringField("brand", "Mercedes");
        generator.writeNumberField("doors", 5);
        generator.writeEndObject();  //将}写入输出

        generator.close(); //关闭JsonGenerator
    }
}
