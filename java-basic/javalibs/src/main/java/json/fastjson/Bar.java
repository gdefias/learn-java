package json.fastjson;

/**
 * Created by Defias on 2017/8/17.
 */

import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;

public class Bar {
    //SerializeConfig：是对序列化过程中一些序列化过程的特殊配置，这里用作日期格式的定义。
    public static SerializeConfig mapping = new SerializeConfig();
    private String barName;
    private int barAge;
    private Date barDate = new Date();

    static {
        mapping.put(Date.class, new SimpleDateFormatSerializer("yyyy-MM-dd"));
    }

    //代码块给Bar对象中的barName和barAge赋值
    {
        Random random = new Random();
        barName = "name_"+String.valueOf(random.nextInt());
        barAge = random.nextInt();
    }

    public String getBarName() {
        return barName;
    }

    public void setBarName(String barName) {
        this.barName = barName;
    }

    public int getBarAge() {
        return barAge;
    }

    public void setBarAge(int barAge) {
        this.barAge = barAge;
    }

    public Date getBarDate() {
        return barDate;
    }

    public void setBarDate(Date barDate) {
        this.barDate = barDate;
    }

    @Override
    public String toString() {
        return "Bar{" +
                "barName='" + barName + '\'' +
                ", barAge=" + barAge +
                ", barDate=" + barDate +
                '}';
    }

    public static void main(String[] args) {

        //System.out.println(new Bar());

        ////把Bar对象转换成JSON格式的Object类型
        //Object obj = JSON.toJSON(new Bar());
        //System.out.println(obj);
        ////输出{"barAge":-664880579,"barDate":1471348117095,"barName":"name_464160862"}
        //
        ////Bar对象转换成String类型
        //String x1 = JSON.toJSONString(new Bar(), true);
        //System.out.println(x1);
        //
        ////String类型转换成JSONObject
        //JSONObject var = JSON.parseObject(x1);
        //System.out.println(var);
        //

        //String类型转换成MAP
        String x2 = "{\"barAge\":\"1369019199\",\"barName\":\"[{\\\"man\\\":100,\\\"shop_rate\\\":1.1}]\",\"barDate\":\"1559459682805\"}";
        Map<String,String> map1 = (Map<String,String>)JSON.parse(x2);
        for (String key : map1.keySet()) {
            System.out.println(key+":"+map1.get(key));
        }


        //格式化时间的JSON字符串
        //String x2 = JSON.toJSONString(new Bar(), mapping);
        //System.out.println(x2);
        //输出:{"barAge":1755982737,"barDate":"2016-08-16","barName":"name_453018403"}
    }
}
