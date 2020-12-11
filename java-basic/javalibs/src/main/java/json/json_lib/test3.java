package json.json_lib;

/**
 * Created by Defias on 2017/3/10.
 *
 * JSON-lib
 * 读取JSON文本
 *
 */

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class test3 {
    public static void main(String args[])
    {
        JSONArray jsonarray;
        JSONObject jsonObj;

        //读取JSONArray，用下标索引获取
        String array="[\"11\",\"22\",\"33\"]";
        jsonarray = JSONArray.fromObject(array);
        System.out.println(jsonarray.getString(1));

        //读取JSONObject
        String object="{\"NO1\":[\"44\",\"55\",\"66\"],\"NO2\":{\"NO1\":\"第一个\"}}";
        jsonObj  = JSONObject.fromObject(object);
        System.out.println(jsonObj.get("NO1"));

        jsonarray = (JSONArray)(jsonObj.get("NO1"));
        System.out.println(jsonarray.getString(1));

        jsonObj=(JSONObject)jsonObj.get("NO2");
        System.out.println(jsonObj);

    }
}
