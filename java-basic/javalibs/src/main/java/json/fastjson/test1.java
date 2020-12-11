package json.fastjson;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;
/**
 * Created by Defias on 2017/8/17.
 *
 * fastjson
 */
public class test1 {
    private static SerializeConfig mapping = new SerializeConfig();
    static {
        mapping.put(Date.class, new SimpleDateFormatSerializer("yyyy-MM-dd HH:mm:ss"));
    }

    public static void main(String[] args) {

        //Date date = new Date();
        //String text = JSON.toJSONString(date, mapping);
        //System.out.println(text);//输出"2016-08-16 19:53:07"
        //System.out.println("=======1");
        //
        //Foo f1 = new Foo();
        //System.out.println(JSON.toJSONString(f1, true));    //将JavaBean序列化为带格式的JSON文本
        //System.out.println("=======2");
        //
        //String x2 =JSON.toJSONString(f1, mapping);
        //System.out.println(x2);
        //System.out.println("=======3");
        //
        //json2List();
        //System.out.println("=======4");
        //
        //json2Map();
        //System.out.println("=======5");
        //
        ////普通类型Array数组和JSON的相互转换
        //array2JSON();
        //System.out.println("=======6");
        //
        //array2JSON2();
        //System.out.println("=======7");
        //
        ////普通类型Map和JSON的相互转换
        //map2JSON();
        //System.out.println("=======8");

        JSONObject2Map();

    }

    public static void json2List(){
        //List -> JSON array
        List<Bar> barList = new ArrayList<Bar>();
        barList.add(new Bar());
        barList.add(new Bar());
        barList.add(new Bar());
        String json= JSON.toJSONString(barList, true);
        System.out.println(json);

        //JSON array -> List
        List<Bar> barList1 = JSON.parseArray(json,Bar.class);
        for (Bar bar : barList1) {
            System.out.println(bar.toString());
        }
    }

    public static void json2Map(){
        //Map -> JSON
        Map<String,Bar> map = new HashMap<String, Bar>();
        map.put("a",new Bar());
        map.put("b",new Bar());
        map.put("c",new Bar());
        String json = JSON.toJSONString(map,true);
        System.out.println(json);

        //JSON -> Map
        Map<String,Bar> map1 = (Map<String,Bar>)JSON.parse(json);
        for (String key : map1.keySet()) {
            System.out.println(key+":"+map1.get(key));
        }
    }


    public static void array2JSON(){
        //String数组转换成JSON
        String[] arr_String  = {"a","b","c"};
        String json_arr_String = JSON.toJSONString(arr_String,true);
        System.out.println(json_arr_String); //输出["a","b","c"]

        //JSON转换成array
        JSONArray jsonArray = JSON.parseArray(json_arr_String);
        for (Object o : jsonArray) {
            System.out.println(o);
        }
    /* 输出:
     a
     b
     c
     */
        System.out.println(jsonArray);//输出:["a","b","c"]
    }

    public static void array2JSON2(){
        Bar[] arr_Bar    = {new Bar(),new Bar(),new Bar()};
        String json_arr_Bar = JSON.toJSONString(arr_Bar,true);
        System.out.println(json_arr_Bar);
        JSONArray jsonArray = JSON.parseArray(json_arr_Bar);
        for (Object o : jsonArray) {
            System.out.println(o);
        }
        System.out.println(jsonArray);
    }


    public static void map2JSON(){

        //Map转换成JSON
        Map<String,String> map = new HashMap<String,String>();
        map.put("a","aaa");
        map.put("b","bbb");
        map.put("c","ccc");
        String json=JSON.toJSONString(map);
        System.out.println(json);//输出{"a":"aaa","b":"bbb","c":"ccc"}

        //JSON字符串转换成Map
        Map map1 = JSON.parseObject(json);
        for (Object o : map.entrySet()) {
            Map.Entry<String,String> entry = (Map.Entry<String,String>)o;
            System.out.println(entry.getKey()+"--->"+entry.getValue());
        }
    /*
     b--->bbb
     c--->ccc
     a--->aaa
    */
    }

    public static void JSONObject2Map() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("a","aaa");
        jsonObject.put("b","bbb");
        jsonObject.put("c","ccc");
        jsonObject.put("d",1);
        Set<Map.Entry<String,Object>> set = jsonObject.entrySet();
        System.out.println(set);
    }
}
