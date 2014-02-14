package tk.bartbart333.udp;

import java.net.InetAddress;

public class SlaveConnection extends Connection{
	
	public SlaveConnection(Socket socket, InetAddress ip, int port){
		super(socket, ip, port);
		
		Packet punch = new Packet("");
		punch.setValue("type", "punch");
		
		send(punch);
	}
	
	@Override
	protected void receive(Packet packet){
		if(packet.getType() == "connect"){
			Packet connect = new Packet("");
			connect.setValue("type", "connect");
			connect.setSeq(33);
			
			send(connect);
		}else if(packet.getType() == "ok"){
			connected = true;
		}
	}
}