package tk.bartbart333.udp;

import java.net.InetAddress;
import java.util.ArrayList;

public class Connection extends Thread {
	
	protected ArrayList<Packet> packets = new ArrayList<Packet>();
	protected boolean running = true;
	protected InetAddress ip;
	protected int port;
	
	protected Connection(InetAddress ip, int port){
		this.ip = ip;
		this.port = port;
	}
	
	public void addPacket(byte[] data){
		
	}
	
	public InetAddress getAddress(){
		return ip;
	}
	
	public int getPort(){
		return port;
	}
	
	public void close(){
		running = false;
	}
}