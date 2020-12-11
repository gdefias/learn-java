package net.jcip.examples.part2_structapplication.chapter7_cancelclose;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * ReaderThread

 通过改写interrupt方法将非标准的取消操作封装在thread中

 */

public class ReaderThread extends Thread {
    private static final int BUFSZ = 512;
    private final Socket socket;
    private final InputStream in;

    public ReaderThread(Socket socket) throws IOException {
        this.socket = socket;
        this.in = socket.getInputStream();
    }

    //改写中断方法
    //无论ReaderThread线程在read方法中阻塞还是在某个可中断的阻塞方法中阻塞都可以被中断并停止执行当前的工作
    public void interrupt() {
        try {
            socket.close();
        } catch (IOException ignored) {
        } finally {
            super.interrupt();
        }
    }

    public void run() {
        try {
            byte[] buf = new byte[BUFSZ];
            while (true) {
                int count = in.read(buf);
                if (count < 0)
                    break;
                else if (count > 0)
                    processBuffer(buf, count);
            }
        } catch (IOException e) { /* Allow thread to exit */
        }
    }

    public void processBuffer(byte[] buf, int count) {
    }
}
