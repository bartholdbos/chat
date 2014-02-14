package tk.bartbart333.udp;

import java.net.InetAddress;

public class MasterConnection extends Connection {
	
	public MasterConnection(Socket socket, InetAddress ip, int port){
		super(socket, ip, port);
		
		Packet connect = new Packet("");
		connect.setValue("type", "connect");
		connect.setSeq(1);
		
		send(connect);
	}
	
	@Override
	protected void receive(Packet packet){
		if(packet.getType() == "connect"){
			setSeq(packet.getSeq());
			connected = true;
			
			Packet ok = new Packet("");
			ok.setValue("type", "ok");
			ok.setSeq(getSeq());
			
			send(ok);
		}
	}
}