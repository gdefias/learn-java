package Schedule;
import java.util.Timer;
import java.util.TimerTask;
/**
 * Created by Defias on 2017/8/29.

 定时器

 几种调度task的方式
 timer.schedule(TimerTask task, Date time);   //在指定时间执行一次
 timer.schedule(TimerTask task, Date firstTime, long period);  //从firstTime时刻开始，每隔period毫秒执行一次
 timer.schedule(TimerTask task, long delay);   //从现在起过delay毫秒执行一次
 timer.schedule(TimerTask task, long delay, long period);   //从现在起过delay毫秒以后，每隔period毫秒执行一次

 */

public class Test1 extends TimerTask {
    @Override
    public void run() {
        System.out.println("start");
    }

    public static void main(String[] args){
        Timer timer = new Timer();
        timer.schedule(new Test1(), 1000, 2000);
    }
}
