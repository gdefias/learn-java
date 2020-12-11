package Net;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
/**
 * Created by Defias on 2020/08.
 * Description: ECHO UDP服务器
 */
public class Test_UDPEchoServer {
    private static final int ECHOMAX = 255; // Maximum size of echo datagram

    public static void main(String[] args) throws IOException {

        int servPort = 8987;
        DatagramSocket socket = new DatagramSocket(servPort);
        DatagramPacket packet = new DatagramPacket(new byte[ECHOMAX], ECHOMAX);

        while (true) { // Run forever, receiving and echoing datagrams
            socket.receive(packet); // Receive packet from client
            System.out.println("Handling client at " + packet.getAddress().getHostAddress() + " on port " + packet.getPort());
            socket.send(packet); // Send the same packet back to client
            packet.setLength(ECHOMAX); // Reset length to avoid shrinking buffer
        }
        /* NOT REACHED */
    }
}
