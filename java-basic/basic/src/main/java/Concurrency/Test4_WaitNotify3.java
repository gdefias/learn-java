package Concurrency;

/**
 * Created by Defias on 2016/2/29.
 *
 * wait() 与 notify()
 *
 * 针对特定对象notify()时其他对象不受影响
 */


public class Test4_WaitNotify3 {
	public static void main(String args[]) {
		NewThreadNew ob1 = new NewThreadNew("One");
		NewThreadNew ob2 = new NewThreadNew("Two");
		try {
			Thread.sleep(1000);

			System.out.println("Suspending thread One");
			ob1.mysuspend();

			Thread.sleep(1000);
			System.out.println("Resuming thread One");
			ob1.myresume();

			Thread.sleep(1000);
			System.out.println("Suspending thread Two");
			ob2.mysuspend();

			Thread.sleep(1000);
			System.out.println("Resuming thread Two");
			ob2.myresume();

			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.out.println("Main thread Interrupted");
		}

		// wait for threads to finish
		try {
			System.out.println("Waiting for threads to finish.");
			ob1.t.join();
			ob2.t.join();
		} catch (InterruptedException e) {
			System.out.println("Main thread Interrupted");
		}
		System.out.println("Main thread exiting.");
	}
}



class NewThreadNew implements Runnable {
	String name; // name of thread
	Thread t;
	boolean suspendFlag;

	NewThreadNew(String threadname) {
		name = threadname;
		t = new Thread(this, name);
		System.out.println("New thread: " + t);
		suspendFlag = false;
		t.start();
	}

	public void run() {
		try {
			for(int i = 25; i > 0; i--) {
				System.out.println(name + ": " + i);
				Thread.sleep(200);

                synchronized(this) {
					while(suspendFlag) {
						wait();
					}
				}
			}
		} catch (InterruptedException e) {
			System.out.println(name + " interrupted.");
		}
		System.out.println(name + " exiting.");
	}

	void mysuspend() {  //暂停线程运行
		suspendFlag = true;
	}

	synchronized void myresume() { //恢复线程运行
		suspendFlag = false;
		notify();
	}
}

