package Schedule;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

/**
 * Created by Defias on 2020/08.
 * Description: 任务调度管理器
 */

public class DynamicTaskManager {
    private static final long PERIOD = 5 * 1000;// 5秒钟
    private static DynamicTaskManager taskManager = null;  //单例对象
    private static Timer timer = new Timer();  //时间调度对象
    private static DynamicTimerTask task = null;  //任务
    static {
        taskManager = new DynamicTaskManager();
    }


    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        DynamicTaskManager manager = DynamicTaskManager.getInstance();
        //启动任务，会立即执行一次，2s时执行完毕，5s时第二次执行，7s时第二次执行完毕
        manager.start();

        for (int i = 0; i < 8; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //8s时，stop原任务，动态更改启动时间
        manager.stop();
        System.out.println("当前时间：" + new Date().toLocaleString());

        System.out.println("修改原计划，5s后重新执行");
        //5s后再启动，即13s时再启动
        manager.start(DateUtils.addDay(new Date(), Calendar.SECOND, 5));
    }


    public static DynamicTaskManager getInstance() {
        if (taskManager == null) {
            taskManager = new DynamicTaskManager();
        }
        return taskManager;
    }

    public DynamicTaskManager() {
    }

    @SuppressWarnings("deprecation")
    public void startTask(Date startTime, long period) {

        System.out.println("设置启动时间: " + startTime.toLocaleString());
        //如果当前时间超过了设定时间，会立即执行一次
        task = new DynamicTimerTask();
        timer.schedule(task, startTime, period);

    }



    //启动定时器
    public void start() {
        //启动任务，10点40启动任务
        start(DateUtils.bookTime(10, 40, 0));
    }

    //启动定时器
    public void start(long preiod) {
        //启动任务，10点40启动任务
        start(DateUtils.bookTime(10, 40, 0), preiod);
    }

    //启动定时器
    public void start(Date startTime) {
        start(startTime, PERIOD);
    }

    //启动定时器
    public void start(Date startTime, long preiod) {
        startTask(startTime, preiod);
    }

    //重新启动定时器
    public void restart() {
        clean();
        start();
    }

    //清空timer
    public void clean() {
        if (task != null) {
            task.cancel();
        }
        timer.purge();
    }

    //停止任务
    public void stop() {
        System.out.println("--------任务正在停止---------");
        clean();
        System.out.println("---------任务已停止----------");
    }

    static class DateUtils {
        //增加或减少天数
        //CalendarFlag 取值 Calendar.DAY_OF_MONTH, Calendar.HOUR_OF_DAY，Calendar.MINUTE，Calendar.SECOND，Calendar.MILLISECOND
        public static Date addDay(Date date, int CalendarFlag, int num) {
            Calendar startDT = Calendar.getInstance();
            startDT.setTime(date);
            startDT.add(CalendarFlag, num);
            return startDT.getTime();
        }

        //设定时间
        public static Date bookTime(int hour, int minute, int second) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, hour);
            calendar.set(Calendar.MINUTE, minute);
            calendar.set(Calendar.SECOND, second);
            Date date = calendar.getTime();
            return date;
        }
    }
}