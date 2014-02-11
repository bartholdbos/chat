package tk.bartbart333.udp;

import java.net.InetAddress;

public class SlaveConnection extends Connection{
	
	public SlaveConnection(InetAddress ip, int port){
		super(ip, port);
	}
	
	@Override
	protected void receive(Packet packet){
		if(packet.getType() == "connect"){
			punching = false;
		}else if(packet.getType() == "ack"){
			// connection is made
			connected = true;
		}
	}
}