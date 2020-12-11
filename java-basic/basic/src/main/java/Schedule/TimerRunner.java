package Schedule;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimerRunner {
	public static void main(String[] args) {
         Timer timer = new Timer();
         TimerTask task = new SimpleTimerTask();
         timer.schedule(task,1000L,1000L);     
	}
}


class SimpleTimerTask  extends TimerTask {
    private int count = 0;
    public void run() {
        System.out.println("execute task.");
        Date exeTime = (new Date(scheduledExecutionTime()));
        System.out.println("本次任务安排执行时间点为："+exeTime);

        if(++count > 5) {  //执行5次后取消执行
            cancel();
        }
    }
}
