package slave;

import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import tk.bartbart333.udp.Connection;
import tk.bartbart333.udp.Socket;

public class Slave {
	
	private Socket socket;
	private Connection master;
	
	public Slave(){
		try{
			socket = new Socket(314);
			master = socket.accept(InetAddress.getByName("localhost"), 313);
			
			System.out.println("connection made " + master.getAddress().getHostAddress() + ":" + master.getPort());
		}catch(SocketException e){
			e.printStackTrace();
		}catch(UnknownHostException e){
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		new Slave();
	}
}