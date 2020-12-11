package Net;

import Net.Customer.VoteMsg;
import Net.Customer.VoteMsgTextCoder;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.Arrays;
/**
 * Created by Defias on 2020/08.
 * Description: 多播 - 接收
 */
public class Test_MulticastReceiver {
    public static void main(String[] args) throws IOException {

        String argadress = "239.255.255.200";
        InetAddress address = InetAddress.getByName(argadress);
        if (!address.isMulticastAddress()) {
            throw new IllegalArgumentException("Not a multicast address");
        }

        int port = Integer.parseInt("8083");
        MulticastSocket sock = new MulticastSocket(port);
        sock.joinGroup(address); // Join the multicast group

        VoteMsgTextCoder coder = new VoteMsgTextCoder();
        DatagramPacket packet = new DatagramPacket(new byte[VoteMsgTextCoder.MAX_WIRE_LENGTH],
                VoteMsgTextCoder.MAX_WIRE_LENGTH);
        sock.receive(packet);

        VoteMsg vote = coder.fromWire(Arrays.copyOfRange(packet.getData(), 0, packet
                .getLength()));

        System.out.println("Received Text-Encoded Request (" + packet.getLength()
                + " bytes): ");
        System.out.println(vote);

        sock.close();
    }
}
