package tk.bartbart333.udp;

import java.net.InetAddress;
import java.util.ArrayList;

public abstract class Connection extends Thread {
	
	protected ArrayList<Packet> packets = new ArrayList<Packet>();
	protected boolean running = true;
	protected boolean punching = true;
	protected InetAddress ip;
	protected int port;
	
	public boolean connected = false;
	
	protected abstract void receive(Packet packet);
	
	protected Connection(InetAddress ip, int port){
		this.ip = ip;
		this.port = port;
	}
	
	public void addPacket(Packet packet){
		packets.add(packet);
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
	
	public void run(){
		Packet packet;
		
		while(running){
			if(packets.size() >= 1){
				packet = packets.get(0);
				
				if(packet.getType() == "connect"){
					receive(packet);
				}else if(packet.getType() == "ack"){
					receive(packet);
				}
				
				packets.remove(0);
			}
			
			if(punching){
				//send connect packet
			}
		}
	}
}