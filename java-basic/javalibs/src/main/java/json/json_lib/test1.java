package json.json_lib;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * Created by Defias on 2017/3/10.
 *
 * JSON-lib
 * 基本的JSONArray与JSONObject操作
 *
 */



public class test1 {
    public static void main(String args[])
    {
        JSONObject jsonObj  = new JSONObject();
        jsonObj.put("name0", "zhangsan");
        jsonObj.put("sex1", "female");
        System.out.println(jsonObj);

        JSONArray jsonArray = new JSONArray();
        jsonArray.add("11");
        jsonArray.add("22");
        jsonArray.add("33");
        System.out.println(jsonArray);
    }
}
