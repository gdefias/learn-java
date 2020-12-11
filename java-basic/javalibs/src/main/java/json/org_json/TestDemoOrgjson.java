package json.org_json;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import json.json_lib.Employee;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;
/**
 * Created by Defias on 2017/3/9.
 *
 * Json解析
 *
 * org.json包是另一个用来beans,collections,maps,java arrays 和XML和JSON互相转换的包，主要就是用来解析Json数据
 *
 * 1:将JavaBean转换成Map、JSONObject
 * 2:将Map转换成Javabean
 * 3:将JSONObject转换成Map、Javabean
 */

class JsonHelper {
    /**
     * 将Javabean转换为Map
     *
     * @param javaBean javaBean
     * @return Map对象
     */
    public static Map toMap(Object javaBean) {

        Map result = new HashMap();
        Method[] methods = javaBean.getClass().getDeclaredMethods();

        for (Method method : methods) {
            try {
                if (method.getName().startsWith("get")) {
                    String field = method.getName();
                    field = field.substring(field.indexOf("get") + 3);
                    field = field.toLowerCase().charAt(0) + field.substring(1);

                    Object value = method.invoke(javaBean, (Object[])null);
                    result.put(field, null == value ? "" : value.toString());

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return result;

    }

    /**
     * 将Json对象转换成Map
     *
     * @param jsonObject  json对象
     * @return Map对象
     * @throws JSONException
     */
    public static Map toMap(String jsonString) throws JSONException {

        JSONObject jsonObject = new JSONObject(jsonString);

        Map result = new HashMap();
        Iterator iterator = jsonObject.keys();
        String key = null;
        String value = null;

        while (iterator.hasNext()) {

            key = (String) iterator.next();
            value = jsonObject.getString(key);
            result.put(key, value);

        }
        return result;

    }

    /**
     * 将JavaBean转换成JSONObject（通过Map中转）
     *
     * @param bean  javaBean
     * @return json对象
     */
    public static JSONObject toJSON(Object bean) {

        return new JSONObject(toMap(bean));

    }

    /**
     * 将Map转换成Javabean
     *
     * @param javabean javaBean
     * @param data  Map数据
     */
    public static Object toJavaBean(Object javabean, Map data) {

        Method[] methods = javabean.getClass().getDeclaredMethods();
        for (Method method : methods) {

            try {
                if (method.getName().startsWith("set")) {

                    String field = method.getName();
                    field = field.substring(field.indexOf("set") + 3);
                    field = field.toLowerCase().charAt(0) + field.substring(1);
                    method.invoke(javabean, new Object[] {

                            data.get(field)

                    });

                }
            } catch (Exception e) {
            }

        }

        return javabean;

    }

    /**
     * JSONObject到JavaBean
     *
     * @param bean javaBean
     * @return json对象
     * @throws ParseException json解析异常
     * @throws JSONException
     */
    public static void toJavaBean(Object javabean, String jsonString)
            throws ParseException, JSONException {

        JSONObject jsonObject = new JSONObject(jsonString);

        Map map = toMap(jsonObject.toString());

        toJavaBean(javabean, map);

    }
}


public class TestDemoOrgjson {

    /**
     * @param args
     * @throws JSONException
     * @throws ParseException
     */
    public static void main(String[] args) throws JSONException, ParseException {

        String sjson = BuildJson();

        System.out.println("\n------------\n");

        ParseJson(sjson);
    }


    /**
     * 构造Json数据
     *
     * @return
     * @throws JSONException
     */
    public static String BuildJson() throws JSONException {

        //下面构造两个map、一个list和一个Employee对象
        Map<String, String> map1 = new HashMap<String, String>();
        map1.put("name", "Alexia");
        map1.put("sex", "female");
        map1.put("age", "23");

        Map<String, String> map2 = new HashMap<String, String>();
        map2.put("name", "Edward");
        map2.put("sex", "male");
        map2.put("age", "24");

        List<Map> list = new ArrayList<Map>();
        list.add(map1);
        list.add(map2);

        Employee employee = new Employee();
        employee.setName("wjl");
        employee.setSex("female");
        employee.setAge(24);

        //将Map转换为JSONArray数据
        JSONArray ja = new JSONArray();
        ja.put(map1);

        System.out.println("JSONArray对象数据格式：");
        System.out.println(ja.toString());


        //将Javabean转换为Json数据（需要Map中转）
        JSONObject jo1 = JsonHelper.toJSON(employee);

        System.out.println("\n仅含Employee对象的Json数据格式：");
        System.out.println(jo1.toString());


        //JSON格式数据解析对象
        JSONObject jo = new JSONObject();
        //构造Json数据，包括一个map和一个含Employee对象的Json数据
        jo.put("map", ja);
        jo.put("employee", jo1.toString());
        System.out.println("\n最终构造的JSON数据格式：");
        System.out.println(jo.toString());

        return jo.toString();

    }

    /**
     * 解析Json数据
     *
     * @param jsonString  Json数据字符串
     * @throws JSONException
     * @throws ParseException
     */
    public static void ParseJson(String jsonString) throws JSONException, ParseException {

        JSONObject jo = new JSONObject(jsonString);
        JSONArray ja = jo.getJSONArray("map");

        System.out.println("\n解析Json Map数据：");
        System.out.println("name: " + ja.getJSONObject(0).getString("name")
                + " sex: " + ja.getJSONObject(0).getString("sex") + " age: "
                + ja.getJSONObject(0).getInt("age"));

        String jsonStr = jo.getString("employee");
        Employee emp = new Employee();
        JsonHelper.toJavaBean(emp, jsonStr);

        System.out.println("\n解析Json Employee对象数据：");
        System.out.println("name: " + emp.getName() + " sex: " + emp.getSex()
                + " age: " + emp.getAge());

    }

}