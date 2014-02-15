package tk.bartbart333.udp;

import java.net.InetAddress;

public class SlaveConnection extends Connection{
	
	public SlaveConnection(Socket socket, InetAddress ip, int port){
		super(socket, ip, port);
	}
	
	@Override
	protected void receive(Packet packet){
		punching = false;
		if(packet.getType().equals("punch")){
			send(punch);
		}else if(packet.getType().equals("connect")){
			Packet connect = new Packet("");
			connect.setValue("type", "connect");
			connect.setSeq(33);
			
			send(connect);
		}else if(packet.getType().equals("ok")){
			connected = true;
			System.out.println("connected");
		}
	}
}