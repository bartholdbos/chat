package tk.bartbart333.udp;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;

public abstract class Connection extends Thread {
	
	protected ArrayList<Packet> packets = new ArrayList<Packet>();
	protected boolean running = true;
	protected Socket socket;
	protected InetAddress ip;
	protected int port;
	
	private int seq;
	
	public boolean connected = false;
	
	protected abstract void receive(Packet packet);
	
	protected Connection(Socket socket, InetAddress ip, int port){
		this.socket = socket;
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
	
	public void setSeq(int seq){
		this.seq = seq;
	}
	
	public int getSeq(){
		return seq++;
	}
	
	public void send(Packet packet){
		try{
			socket.send(this, packet);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void close(){
		running = false;
	}
	
	public void run(){
		Packet packet;
		
		while(running){
			if(packets.size() >= 1){
				packet = packets.get(0);
				
				if(packet.getType() == "punch"){
					
				}else if(packet.getType() == "connect"){
					receive(packet);
				}else if(packet.getType() == "ok"){
					receive(packet);
				}
				
				packets.remove(0);
			}
			
			
		}
	}
}