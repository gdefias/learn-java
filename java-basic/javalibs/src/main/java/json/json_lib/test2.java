package json.json_lib;

/**
 * Created by Defias on 2017/3/10.
 *
 * JSON-lib
 * 由java自带的数据结构转换为JSON文本
 *
 */

import java.util.HashMap;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class test2 {
    public static void main(String args[])
    {
        //可以由数组，列表等生成JSONArray
        String list[]={"11","22"};
        JSONArray jsonarray = JSONArray.fromObject(list);
        jsonarray.add("33");
        System.out.println(jsonarray);

        //可以由Map生成JSONObject
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("NO1", "第一个");
        map.put("NO2", "第二个");
        map.put("NO3", jsonarray);
        JSONObject jsonObj  = JSONObject.fromObject(map);
        System.out.println(jsonObj);
    }
}
