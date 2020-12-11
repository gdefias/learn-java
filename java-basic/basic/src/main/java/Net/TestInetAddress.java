package Net;

import java.net.*;
import java.util.Enumeration;

/**
 * Created by Defias on 2016/2/29.

 NetworkInterface
 静态方法getNetworkInterfaces()返回一个列表，其中包含了该主机每一个接口所对 应的NetworkInterface类实例

 InetAddress
 该类有两个子类，Inet4Address和Inet6Address，分别对应了目前IP地址的两个版本。InetAddress 实例是不可变的，一旦创建，
 每个实例就始终指向同一个地址

 */

public class TestInetAddress {
	public static void main(String args[]) {
		test1();
	}

	public static void test1() {
		// Get the network interfaces and associated addresses for this host
		try {
			Enumeration<NetworkInterface> interfaceList = NetworkInterface.getNetworkInterfaces();
			if (interfaceList == null) {
				System.out.println("--No interfaces found--");
			} else {
				while (interfaceList.hasMoreElements()) {
					NetworkInterface iface = interfaceList.nextElement();
					System.out.println("Interface " + iface.getName() + ":");
					Enumeration<InetAddress> addrList = iface.getInetAddresses();
					if (!addrList.hasMoreElements()) {
						System.out.println("\t(No addresses for this interface)");
					}
					while (addrList.hasMoreElements()) {
						InetAddress address = addrList.nextElement();
						System.out.print("\tAddress "
								+ ((address instanceof Inet4Address ? "(v4)"
								: (address instanceof Inet6Address ? "(v6)" : "(?)"))));
						System.out.println(": " + address.getHostAddress());
					}
				}
			}
		} catch (SocketException se) {
			System.out.println("Error getting network interfaces:" + se.getMessage());
		}

		// Get name(s)/address(es) of hosts given on command line
		String host = "localhost";

		try {
			System.out.println(host + ":");
			InetAddress[] addressList = InetAddress.getAllByName(host);
			for (InetAddress address : addressList) {
				System.out.println("\t" + address.getHostName() + "/" + address.getHostAddress());
			}
		} catch (UnknownHostException e) {
			System.out.println("\tUnable to find address for " + host);
		}
	}
}
