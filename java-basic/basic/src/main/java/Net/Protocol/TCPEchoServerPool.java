package Net.Protocol;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Created by Defias on 2020/08.
 * Description: 线程池
 */
public class TCPEchoServerPool {
    public static void main(String[] args) throws IOException {
        //test1();
        test2();
    }

    //方式1 - 循环创建
    public static void test1() throws IOException {
        int echoServPort = 8081;
        int threadPoolSize = 3;
        final ServerSocket servSock = new ServerSocket(echoServPort);
        final Logger logger = Logger.getLogger("practical");

        for (int i = 0; i < threadPoolSize; i++) {
            Thread thread = new Thread() {
                public void run() {
                    while (true) {
                        try {
                            Socket clntSock = servSock.accept(); // Wait for a connection
                            EchoProtocol.handleEchoClient(clntSock, logger); // Handle it
                        } catch (IOException ex) {
                            logger.log(Level.WARNING, "Client accept failed", ex);
                        }
                    }
                }
            };
            thread.start();
            logger.info("Created and started Thread = " + thread.getName());
        }
    }

    //方式2 - Executor
    public static void test2() throws IOException {
        int echoServPort = 8081;
        ServerSocket servSock = new ServerSocket(echoServPort);
        Logger logger = Logger.getLogger("practical");

        Executor service = Executors.newCachedThreadPool();

        while (true) {
            Socket clntSock = servSock.accept();
            service.execute(new EchoProtocol(clntSock, logger));
        }

    }
}
