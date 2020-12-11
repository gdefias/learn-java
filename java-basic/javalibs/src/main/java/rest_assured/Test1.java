package rest_assured;
import java.util.*;

import io.restassured.path.json.JsonPath;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static io.restassured.path.json.JsonPath.from;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;  //依赖于json-schema-validator jar module

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.hasItems;
/**
 * Created by Defias on 2017/9/4.
 *
 * rest-assured
 * REST Assured是一个可以简化HTTP Builder顶层 基于REST服务的测试过程的Java DSL（针对某一领域，具有受限表达性的一种计算机程序设计语言）。
 * 它支持发起POST,GET,PUT,DELETE,OPTIONS,PATCH和HEAD请求，并且可以用来验证和校对这些请求的响应信息
 *
 */


public class Test1 {

    public static void test1() {
        String url = "http://test2bservice.goluk.cn/tracks?xieyi=100&imei=353211083276077&operation=0&index=&pagesize&commuid=ec51cab733af4b92a73a34fa0500c90b";

        Response response = get(url);   //发起get请求，并获取响应
        if (response.getStatusCode() == 200) {
            response.getBody().prettyPrint();       //格式化打印Body JSON数据

            //节点值验证
            response.then().assertThat().statusCode(200);  //判断返回的code码是否为200
            response.then().body("code", equalTo(0));   //判断code是否等于0
            response.then().body("msg", equalTo("ok"));


            response.then().body("data.tracks", hasSize(8));     //判断数组的长度是否等于2
            response.then().body("data.tracks[0].state", equalTo(1));    //判断数组第一个元素下state字段值是否等于1
            response.then().body("data.tracks[0].state", lessThan(2));   //判断数组第一个元素下state字段值是否小于2
            response.then().body("data.tracks[0].state", greaterThan(0));  //判断数组第一个元素下state字段值是否大于0
            response.then().body("data.tracks.state", hasItems(1, 0));  //检查state的取值包括1和0
            response.then().body("data.tracks[0].imei", not(""));   //判断第一个数组元素中imei字段值不为“”
            response.then().body("data.tracks[0].uid.length()", greaterThan(10));   //判断第一个数组元素中uid字段值长度大于10

            //用jsonpath可以很方便的获取json中的各种数据
            System.out.println(response.getBody().jsonPath().getString("data.tracks[1].imei"));
            System.out.println(response.getBody().jsonPath().getString("msg"));
            System.out.println(response.getBody().jsonPath().getInt("code"));

            JsonPath jsonPath = new JsonPath(response.asString()).setRoot("data.tracks[0]");
            System.out.println((String) jsonPath.get("trackId"));
            System.out.println((String) jsonPath.get("imei"));

            //List<String> dspnameStr = from(jsonStr).getList("result.adlist.findAll { it.ishavead == 1 }.dspname");  //得到所有的ishavead=1的adlist数组元素，并获取其中dspname的值,放在一个集合里


            //响应时间
            //when().get("https://server/demo?p1=0&p2=1").then().time(lessThan(100L),TimeUnit.MILLISECONDS);//判断响应时间是否少于预期值。

            //JSON Schema Validation
            response.then().assertThat().body(matchesJsonSchemaInClasspath("test.json"));  //验证点：保证返回data、code、msg字段，并对每个字段的value进行类型限定
        }
    }

    public static void test2() {
        String url = "http://test2bservice.goluk.cn/tracks?xieyi=100&imei=353211083276077&operation=0&index=&pagesize&commuid=ec51cab733af4b92a73a34fa0500c90b";
        //设置入参
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("xieyi", "100");
        parameters.put("imei", "353211083276077");
        parameters.put("operation", "0");
        parameters.put("commuid", "ec51cab733af4b92a73a34fa0500c90b");


        //设置url请求头
        //Header header = new Header("Accept", "application/json");
        Headers headers = new Headers(new Header("Accept", "application/json"), new Header("Content-Type", "application/json; charset=UTF-8"));
        Response response = given()   //代理：.proxy("192.168.221.190",80)
                .parameters(parameters)
                .headers(headers)
                .get(url);

        //查看请求正文
        System.out.println(response.asString());
        //response常用方法
        //response.asString()	                获取请求返回内容体
        //response.response.getContentType()	获取响应的内容类型
        //response.getStatusCode()	            获取响应的状态代码
        //response.getHeaders()	                获取所有响应头信息
        //response.getHeader(String name)	    根据指定的header名称，获取对应的响应信息
        //response.getCookie(String name)	    根据指定的cookie名称，获取对应cookie的值
        //response.getCookies()	                获取所有cookies信息
        //response.getTime()	                响应时间(单位：毫秒)

    }


    public static void main(String[] args) {

        test1();
    }

}
