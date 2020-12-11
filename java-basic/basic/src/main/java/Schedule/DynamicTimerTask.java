package Schedule;
import java.util.Date;
import java.util.TimerTask;
/**
 * Created by Defias on 2020/08.
 * Description:  可动态修改的任务
 */

public class DynamicTimerTask extends  TimerTask {

    @Override
    public void run() {
        System.out.println("---------start--------");
        Date d = new Date();
        for (int i = 0; i < 2; i++) {
            try {
                Thread.sleep(1000);
                System.out.println("已执行【" + (i + 1) + "】秒钟，at: " + d.toLocaleString());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("本次任务调度结束，at: " + new Date().toLocaleString());
        System.out.println("---------------------");
    }
}
