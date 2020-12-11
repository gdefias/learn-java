package Net;
import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.io.IOException;
import java.io.InterruptedIOException;
/**
 * Created by Defias on 2020/08.
 * Description: ECHO UDP客户端
 */
public class Test_UDPEchoClient {
    private static final int TIMEOUT = 3000;   // Resend timeout (milliseconds)
    private static final int MAXTRIES = 5;     // Maximum retransmissions

    public static void main(String[] args) throws IOException {

        String servadd = "localhost";
        InetAddress serverAddress = InetAddress.getByName(servadd);  // Server address

        // Convert the argument String to bytes using the default encoding
        String argdata = "hello YZH!";
        byte[] bytesToSend = argdata.getBytes();

        int servPort = 8987;

        DatagramSocket socket = new DatagramSocket();
        socket.setSoTimeout(TIMEOUT);  // Maximum receive blocking time (milliseconds)

        DatagramPacket sendPacket = new DatagramPacket(bytesToSend,  // Sending packet
                bytesToSend.length, serverAddress, servPort);

        DatagramPacket receivePacket =                              // Receiving packet
                new DatagramPacket(new byte[bytesToSend.length], bytesToSend.length);

        int tries = 0;      // Packets may be lost, so we have to keep trying
        boolean receivedResponse = false;
        do {
            socket.send(sendPacket);          // Send the echo string
            try {
                socket.receive(receivePacket);  // Attempt echo reply reception

                if (!receivePacket.getAddress().equals(serverAddress)) {// Check source
                    throw new IOException("Received packet from an unknown source");
                }
                receivedResponse = true;
            } catch (InterruptedIOException e) {  // We did not get anything
                tries += 1;
                System.out.println("Timed out, " + (MAXTRIES - tries) + " more tries...");
            }
        } while ((!receivedResponse) && (tries < MAXTRIES));  //重试

        if (receivedResponse) {
            System.out.println("Received: " + new String(receivePacket.getData()));
        } else {
            System.out.println("No response -- giving up.");
        }
        socket.close();
    }
}
