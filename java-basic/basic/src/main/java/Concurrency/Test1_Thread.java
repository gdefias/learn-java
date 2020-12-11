package Concurrency;

import Concurrency.task.LiftOff;
import Concurrency.task.NewThread;

/**
 * Created by Defias on 2016/2/29.

 创建多线程 - 继承Thread类

 */

public class Test1_Thread {
	public static void main(String args[]) {
		//test1();
//		test2();
//		test3();
		test4();
//		test5();
	}

	//单任务
	public static void test1() {
		Thread t = new Thread(new LiftOff());
		t.start();
		System.out.println("--------------Main thread end");
	}

	//多任务 --线程调度机制是非确定的，每次执行的结果可能不一样
	public static void test2() {
		for(int i = 0; i < 5; i++)
			new Thread(new LiftOff()).start();
		System.out.println("--------------Main thread end");
	}

	//sleep等待子线程一段固定时间
	public static void test3() {
		new NewThread(); // 子线程

		//主线程
		try {
			for(int i=5; i>0; i--) {
				System.out.println("Main Thread: " + i);
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			System.out.println("Main thread interrupted.");
		}
		System.out.println("Main thread exiting.");
	}

	//主线程不等待子线程
	public static void test4() {
		new NewThread(); // 子线程

		//主线程先执行结束退出后，子线程独自执行自己的任务直到结束
		System.out.println("Main thread exiting.");
	}

	public static void test5() {
		for(int i = 0; i < 5; i++)
			new SimpleThread();
	}

}



class SimpleThread extends Thread {
	private int countDown = 5;
	private static int threadCount = 0;

	public SimpleThread() {
		// Store the thread name:
		super(Integer.toString(++threadCount));
		start();
	}

	public String toString() {
		return "#" + getName() + "(" + countDown + "), ";
	}

	public void run() {
		while(true) {
			System.out.print(this);
			if(--countDown == 0)
				return;
		}
	}
}