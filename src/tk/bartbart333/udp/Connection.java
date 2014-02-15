package tk.bartbart333.udp;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;

public abstract class Connection extends Thread {
	
	protected ArrayList<Packet> packets = new ArrayList<Packet>();
	protected boolean running = true;
	protected boolean punching = true;
	protected Socket socket;
	protected InetAddress ip;
	protected int port;
	protected Packet punch = new Packet("");
	
	private int seq;
	private long timer;
	
	public boolean connected = false;
	
	protected abstract void receive(Packet packet);
	
	protected Connection(Socket socket, InetAddress ip, int port){
		this.socket = socket;
		this.ip = ip;
		this.port = port;
		punch.setValue("type", "punch");
		timer = System.currentTimeMillis();
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
			System.out.println("send packet: " + packet.getType());
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
				
				System.out.println("received packet: " + packet.getType());
				
				if(packet.getType().equals("punch")){
					receive(packet);
				}else if(packet.getType().equals("connect")){
					receive(packet);
				}else if(packet.getType().equals("ok")){
					receive(packet);
				}
				
				packets.remove(0);
			}
			
			if(punching){
				if(System.currentTimeMillis() - timer >= 2000){
					send(punch);
					timer = System.currentTimeMillis();
				}
			}
		}
	}
}