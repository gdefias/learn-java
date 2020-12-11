package Collection;

import java.util.*;

/**
 * Created by Defias on 2020/07.
 * Description: Collection与迭代器Iterator: 所有序列容器的共性
 */

public class TestCollectionVsIterator {

    //Iterator版本
    public static void display(Iterator<String> it) {
        while(it.hasNext()) {
            String p = it.next();
            System.out.print(p + " ");
        }
        System.out.println();
    }

    //Collection版本
    public static void display(Collection<String> pets) {
        for(String p : pets)
            System.out.print(p + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        List<String> petList =  new ArrayList<String>(Arrays.asList("zhangsan", "lisi", "wangwu","zhangdan","liuhu","yemin","dawei","xiaoh"));
        Set<String> petSet = new HashSet<String>(petList);
        Map<String,String> petMap = new LinkedHashMap<String,String>();
        String[] names = ("Ralph, Eric, Robin, Lacey, Britney, Sam, Spot, Fluffy").split(", ");
        for(int i = 0; i < names.length; i++)
            petMap.put(names[i], petList.get(i));

        display(petList);
        display(petSet);
        System.out.println("------------");

        display(petList.iterator());
        display(petSet.iterator());
        System.out.println("------------");

        System.out.println(petMap);
        System.out.println(petMap.keySet());
        System.out.println("------------");

        display(petMap.values());
        display(petMap.values().iterator());
    }
}
