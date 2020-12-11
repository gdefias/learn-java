package json.org_json;

/**
 * Created by Defias on 2017/7/27.
 *
 * org.json
 *
 */

import org.json.JSONObject;

public class test1 {
    public static void main(String[] args) {
        JSONObject jsonobj = new JSONObject("{\"loc\": [{\"d\": \"206.300003052\", \"lon\": \"116.433207\", \"s\": \"0\", \"r\": \"-1\", \"t\": \"61\", \"time\": \"20170607073034000\", \"lat\": \"39.939853\"}, {\"d\": \"209.619995117\", \"lon\": \"116.433188\", \"s\": \"10.4472141266\", \"r\": \"5\", \"t\": \"61\", \"time\": \"20170607073035000\", \"lat\": \"39.939829\"}, {\"d\": \"267.829986572\", \"lon\": \"116.432978\", \"s\": \"14.1420955658\", \"r\": \"4\", \"t\": \"61\", \"time\": \"20170607073106000\", \"lat\": \"39.939853\"}], \"partid\": \"f630e289-52af-4ca4-8d9b-60608cbea104\", \"modulenumber\": \"ZSCIVGHM7LAAGEZL\"}");
        String partid = jsonobj.getString("partid");
        String modulenumber = jsonobj.getString("modulenumber");
        System.out.println("partid = " + partid + ",modulenumber = " + modulenumber);

        jsonobj.put("modulenumber", "ZSCIVGHM7LAAGEZL123456");
        modulenumber = jsonobj.getString("modulenumber");
        System.out.println("partid = " + partid + ",modulenumber = " + modulenumber);
    }

}
