package Collection.choose;

/**
 * Created by Defias on 2016/4/26.
 *
 * 比较Java数组和各种List的性能
 *
 * -----------------------------------------------------------------
 * 类型               Java数组    ArrayList    LinkedList    Vector
 * -----------------------------------------------------------------
 * 随机访问操作（get）   16          23           63           31
 * 迭代操作（iterate）  31           47           33           48
 * 插入操作（insert）   不适用       1610         31           1625
 * 删除操作（remove）   不适用       6625          16           6750
 *
 * Vector在各方面都没有突出的性能，属于历史集合类，不提倡使用
 */

import java.util.*;


public class PerformanceForList {
    private static final int TIMES=100000;

    public static void main(String args[]){
        List<String> list=null;

        //测试Java数组
        System.out.println("----测试Java数组----");
        String[] ss = new String[TIMES];
        Arrays.fill(ss,"hello");
        list = Arrays.asList(ss);
        testJavaArray(list);

        ss = new String[TIMES/2];
        Collection<String> col = Arrays.asList(ss);

        //测试Vector
        System.out.println("----测试Vector----");
        list=new Vector<String>();
        list.addAll(col);
        testList(list);

        //测试LinkedList
        System.out.println("----测试LinkedList----");
        list=new LinkedList<String>();
        list.addAll(col);
        testList(list);

        //测试ArrayList
        System.out.println("----测试ArrayList----");
        list=new ArrayList<String>();
        list.addAll(col);
        testList(list);
    }


    public static abstract class Tester {
        private String operation;
        public Tester(String operation) {
            this.operation = operation;
        }

        public abstract void test(List<String> list);

        public String getOperation() {
            return operation;
        }
    }

    static Tester iterateTester = new Tester("iterate") {
        public void test(List<String> list){  //迭代操作
            for(int i=0;i<10;i++){
                Iterator<String> it = list.iterator();
                while(it.hasNext()){
                    it.next();
                }
            }
        }
    };

    static Tester getTester=new Tester("get") {
        public void test(List<String> list){  //随机访问操作
            for(int i=0;i<list.size();i++)
                for(int j=0;j<10;j++)
                    list.get(j);
        }
    };

    static Tester insertTester=new Tester("insert"){
        public void test(List<String> list){  //插入操作
            ListIterator<String> it = list.listIterator(list.size()/2);  //从列表的中间开始
            for(int i=0; i<TIMES/2; i++)
                it.add("hello");
        }
    };

    static Tester removeTester=new Tester("remove"){
        public void test(List<String> list){   //删除操作
            ListIterator<String> it =  list.listIterator();
            while(it.hasNext()){
                it.next();
                it.remove();
            }
        }
    };

    static public void testJavaArray(List<String> list) {
        Tester[] testers = {iterateTester,getTester};
        test(testers, list);
    }

    static public void testList(List<String> list){
        Tester[] testers={insertTester,iterateTester,getTester,removeTester};
        test(testers,list);
    }

    static public void test(Tester[] testers,List<String> list){
        for(int i=0;i<testers.length;i++){
            System.out.print(testers[i].getOperation()+"操作：");
            long t1 = System.currentTimeMillis();
            testers[i].test(list);
            long t2 = System.currentTimeMillis();
            System.out.print(t2-t1+" ms");
            System.out.println();
        }
    }


}
