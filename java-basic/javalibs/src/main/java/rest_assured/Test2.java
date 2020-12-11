package rest_assured;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.apache.log4j.Logger;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static io.restassured.path.json.JsonPath.from;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import static org.hamcrest.Matchers.*;

import apache_httpclient.SendRequest;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Defias
 * Date: 2019-06
 */
public class Test2 {
    private static Logger logger = Logger.getLogger(Test2.class);

    public static void test1() {
        RestAssured.baseURI = "http://api.douban.com/v2/book";
        RestAssured.port = 80;
        Response response = get("/1220562");
        response.getBody().prettyPrint();

        get("/1220562").then().body("code", equalTo(101));

        //带参数
        //given().param("q", "java8").when().get("/search").then().body("count", equalTo(2));
    }

    public static void test2() throws Exception {
        //指定 header、cookie、content type
        //given().cookie("name", "xx").when().get("/xxx").then().body();
        //given().header("name", "xx").when().get("/xxx").then().body();
        //given().contentType("application/json").when().get("/xxx").then().body();

        //身份验证
        //given().auth().basic(username, password).when().get("/secured").then().statusCode(200);
        //given().auth().oauth(..);
        //given().auth().oauth2(..);


    }




    public static void main(String[] args) {
        test1();
    }





}
