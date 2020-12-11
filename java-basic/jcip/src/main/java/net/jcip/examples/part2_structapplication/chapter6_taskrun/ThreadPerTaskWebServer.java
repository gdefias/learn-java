package net.jcip.examples.part2_structapplication.chapter6_taskrun;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * ThreadPerTaskWebServer

 在web服务器中为每个请求启动一个新的线程（不要这么做）

 */
public class ThreadPerTaskWebServer {
    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(80);
        while (true) {
            final Socket connection = socket.accept();
            Runnable task = new Runnable() {
                public void run() {
                    handleRequest(connection);
                }
            };
            new Thread(task).start();
        }
    }

    private static void handleRequest(Socket connection) {
        // request-handling logic here
    }
}
