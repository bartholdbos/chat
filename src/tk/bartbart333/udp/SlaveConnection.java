package tk.bartbart333.udp;

import java.net.InetAddress;

public class SlaveConnection extends Connection{
	
	public SlaveConnection(PacketInputStream in, InetAddress ip, int port){
		super(in, ip, port);
	}
	
	public void run(){
		Packet packet;
		
		while(running){
			packet = in.readPacket();
			
			receive(packet);
		}
		
		return;
	}
}