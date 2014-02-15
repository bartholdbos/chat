package tk.bartbart333.udp;

import java.net.InetAddress;

public class MasterConnection extends Connection {
	
	private Packet connect = new Packet("");
	
	public MasterConnection(Socket socket, InetAddress ip, int port){
		super(socket, ip, port);
		
		connect.setValue("type", "connect");
		connect.setSeq(1);
	}
	
	@Override
	protected void receive(Packet packet){
		if(packet.getType().equals("punch")){
			punching = false;
			send(connect);
		}else if(packet.getType().equals("connect")){
			setSeq(packet.getSeq());
			
			Packet ok = new Packet("");
			ok.setValue("type", "ok");
			ok.setSeq(getSeq());
			
			send(ok);
			
			connected = true;
			System.out.println("connected");
		}
	}
}