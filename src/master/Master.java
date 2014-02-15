package master;

import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import tk.bartbart333.udp.Connection;
import tk.bartbart333.udp.Socket;

public class Master {
	
	private Socket socket;
	private Connection slave;
	
	public Master(){
		try{
			socket = new Socket(50100);
			slave = socket.connect(InetAddress.getByName("80.101.213.60"), 50200);
			
			System.out.println("connection made " + slave.getAddress().getHostAddress() + ":" + slave.getPort());
		}catch(SocketException e){
			e.printStackTrace();
		}catch(UnknownHostException e){
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		new Master();
	}
}