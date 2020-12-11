package Net;

import Net.Customer.VoteMsg;
import Net.Customer.VoteMsgCoder;
import Net.Customer.VoteMsgTextCoder;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
/**
 * Created by Defias on 2020/08.
 * Description: 多播 - 发送


 */

public class Test_MulticastSender {
    public static final int CANDIDATEID = 475;

    public static void main(String args[]) throws IOException {

        String argadress = "239.255.255.200";
        InetAddress destAddr = InetAddress.getByName(argadress);
        if (!destAddr.isMulticastAddress()) { //是否是多播地址进行了验证
            throw new IllegalArgumentException("Not a multicast address");
        }

        int destPort = 8083;
        int TTL =  1;

        MulticastSocket sock = new MulticastSocket();
        sock.setTimeToLive(TTL); //为多播数据报文设置了初始的TTL值

        VoteMsgCoder coder = new VoteMsgTextCoder();

        VoteMsg vote = new VoteMsg(true, true, CANDIDATEID, 1000001L);

        byte[] msg = coder.toWire(vote);
        DatagramPacket message = new DatagramPacket(msg, msg.length, destAddr, destPort);
        System.out.println("Sending Text-Encoded Request (" + msg.length + " bytes): ");
        System.out.println(vote);
        sock.send(message);

        sock.close();
    }
}
