package Concurrency;

import java.util.concurrent.TimeUnit;

/**
 * Created by Defias on 2016/2/29.
 *
 * 死锁
 *
 * RacingThread占着B的锁请求A的锁
 * MainThread占着A的锁请求B的锁
 */

public class Test4_Deadlock1 {
	public static void main(String args[]) {
		//演示死锁可能发生和死锁不会发生的开关
		boolean deadlocktest = true;
		//boolean deadlocktest = false;

		if(deadlocktest) {
			new TestDeadlock();
		} else {
			new TestFixDeadlock();
		}
	}

}

class TestDeadlock implements Runnable {
	A a = new A();
	B b = new B();

	TestDeadlock() {
		Thread.currentThread().setName("MainThread");
		Thread t = new Thread(this, "RacingThread");
		t.start();

		try {
			TimeUnit.MILLISECONDS.sleep(100);
		} catch(InterruptedException e) {
			System.out.println("A Interrupted");
		}
		a.foo(b); //导致死锁发生
		//b.bar(a); //不会发生死锁了
		System.out.println("Back in main thread");
	}

	public void run() {
		b.bar(a);
		System.out.println("Back in other thread");
	}
}


class TestFixDeadlock implements Runnable {
	A a = new A();
	B b = new B();

	TestFixDeadlock() {
		Thread.currentThread().setName("MainThread");
		Thread t = new Thread(this, "RacingThread");
		t.start();

		//使用资源排序技术避免死锁
		synchronized(a) {
			synchronized(b) {
				a.foo(b);
			}
		}
		System.out.println("Back in main thread");
	}

	public void run() {
		synchronized(b) {
			synchronized(a) {
				b.bar(a);
			}
		}
		System.out.println("Back in other thread");
	}
}


class A {
	synchronized void foo(B b) {
		String name = Thread.currentThread().getName();
		System.out.println(name + " entered A.foo");
		try {
			TimeUnit.MILLISECONDS.sleep(1000);
		} catch(InterruptedException e) {
			System.out.println("A Interrupted");
		}
		System.out.println(name + " trying to call B.last()");
		b.last();
	}

	synchronized void last() {
		System.out.println("Inside A.last");
	}
}


class B {
	synchronized void bar(A a) {
		String name = Thread.currentThread().getName();
		System.out.println(name + " entered B.bar");
		try {
			TimeUnit.MILLISECONDS.sleep(1000);
		} catch(Exception e) {
			System.out.println("B Interrupted");
		}
		System.out.println(name + " trying to call A.last()");
		a.last();
	}

	synchronized void last() {
		System.out.println("Inside B.last");
	}
}
