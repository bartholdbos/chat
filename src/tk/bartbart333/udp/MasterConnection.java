package tk.bartbart333.udp;

import java.net.InetAddress;

public class MasterConnection extends Connection {
	
	public MasterConnection(InetAddress ip, int port){
		super(ip, port);
	}
	
	@Override
	protected void receive(Packet packet){
		if(packet.getType() == "connect"){
			punching = false;
			// send ack packet
		}else if(packet.getType() == "ack"){
			// connection is made
			connected = true;
		}
	}
}