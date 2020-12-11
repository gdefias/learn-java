package Basic.DateAndTime;

import java.text.ParseException;

/**
 * Created by Defias on 2020/08.
 * Description:  测试SimpleDateFormat的线程安全问题
 */

public class DataUtilTest {

    public static void main(String[] args) {
        test0();
    }

    public static void test0() {
        for(int i = 0; i < 3; i++){
            new TestSimpleDateFormatThreadSafe().start();
        }
        System.out.println("----------");
    }

    public static class TestSimpleDateFormatThreadSafe extends Thread {
        @Override
        public void run() {
            while(true) {
                try {
                    this.join(2000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                try {
                    System.out.println(this.getName()+":"+ DateUtil.ConcurrentDateUtil.parse("2013-05-24 06:02:20"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
