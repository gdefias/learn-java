package Collection.hashcode;
import java.util.*;
/**
 * Created by Defias on 2020/07.
 * Description: 预报
 */

public class Prediction {
    private static Random rand = new Random(47);
    private boolean shadow = rand.nextDouble() > 0.5;
    public String toString() {
        if(shadow)
            return "Six more weeks of Winter!";
        else
            return "Early Spring!";
    }
}
