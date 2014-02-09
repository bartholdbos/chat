package tk.bartbart333.udp;

import java.net.InetAddress;

public class Connection extends Thread{
	
	private InetAddress ip;
	private int port;
	
	protected Connection(InetAddress ip, int port){
		
	}

	public InetAddress getAddress(){
		return ip;
	}

	public int getPort(){
		return port;
	}
}