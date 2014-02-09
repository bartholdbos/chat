package tk.bartbart333.udp;

import java.net.InetAddress;

public class Connection extends Thread {
	
	protected boolean running = true;
	protected PacketInputStream in;
	protected InetAddress ip;
	protected int port;
	
	protected Connection(PacketInputStream in, InetAddress ip, int port){
		this.in = in;
		this.ip = ip;
		this.port = port;
	}
	
	protected void receive(Packet packet){
		
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