package FileIO;

/**
 * Created by Defias on 2016/5/6.
 *
 *
 * PipedInputStream 与 PipedOutputStream
 *
 * 管道通信
 * Java IO中的管道为运行在同一个JVM中的两个线程提供了通信的能力（传递字节数据）。在概念上，Java的管道不同于Unix/Linux系统中的管道
 * 在Unix/Linux中，运行在不同地址空间的两个进程可以通过管道通信。在Java中，通信的双方应该是运行在同一进程中的不同线程
 *
 * 当使用两个相关联的管道流时，务必将它们分配给不同的线程。read()方法和write()方法调用时会导致流阻塞，这意味着如果你尝试在一个线程中同时进行
 * 读和写，可能会导致线程死锁
 */

import java.io.*;


//由一个线程向管道输出流写数据，另一个线程从管道输入流中读取数据，两个线程可以使用管道进行通信，读线程read()读时，如果没有数据这个线程会
//被阻塞，直到另一个线程写入新的数据
public class TestIOPiped extends Thread {
	private PipedInputStream in;
	public TestIOPiped(Sender sender) throws IOException {
		//一个PipedOutputStream总是需要与一个PipedInputStream相关联，当这两种流联系起来时就形成了一条管道
		in = new PipedInputStream(sender.getPipedOutputStream());
	}

	public void run() {
		try {
			int data;
			while((data=in.read()) != -1)   //读
				System.out.println(data);
			in.close();
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String args[]) throws Exception {
		Sender sender = new Sender();
        TestIOPiped receiver = new TestIOPiped(sender);
		sender.start();
		receiver.start();
	}
}


class Sender extends Thread {
	private PipedOutputStream out = new PipedOutputStream();  //PipedOutputStream
	public PipedOutputStream getPipedOutputStream() {
		return out;
	}

	public void run(){
		try{
			for(int i=0; i<=128; i++) {   //写
				out.write(i);
				yield();   //线程让步
			}
			out.close();
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
}


