package json.gson;

import com.google.gson.Gson;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: Defias
 * Date: 2018-03
 *
 * GJson
 */


public class test1 {
    public static void main(String[] args) {
        // 使用new方法
        Gson gson = new Gson();
//
//        // toJson 将bean对象转换为json字符串
//        String jsonStr = gson.toJson(user, User.class);
//
//// fromJson 将json字符串转为bean对象
//        Student user= gson.fromJson(jsonStr, User.class);
//
//// **序列化List**
//        String jsonStr2 = gson.toJson(list);
//
//// **反序列化成List时需要使用到TypeToken getType()**
//        List<User> retList = gson.fromJson(jsonStr2,new TypeToken<List<User>>(){}.getType());
    }
}