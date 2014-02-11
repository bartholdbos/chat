package tk.bartbart333.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;

public class Socket extends Thread{
	
	private DatagramSocket socket;
	private boolean running = true;
	private ArrayList<Connection> connections = new ArrayList<Connection>();
	
	public Socket() throws SocketException{
		socket = new DatagramSocket();
				
		this.start();
	}
	
	public Connection accept(InetAddress ip, int port){
		Connection connection = new SlaveConnection(ip, port);
		
		connections.add(connection);
		connection.start();
		while(!connection.connected);
		return connection;
	}
	
	public Connection connect(InetAddress ip, int port){
		Connection connection = new MasterConnection(ip, port);
		
		connections.add(connection);
		connection.start();
		
		while(!connection.connected);
		return connection;
	}
	
	public InetAddress getLocalAddress(){
		return socket.getLocalAddress();
	}
	
	public int getLocalPort(){
		return socket.getLocalPort();
	}
	
	public void send(Connection connection, Packet packet) throws IOException{
		byte[] data = packet.getData();
		DatagramPacket datapacket = new DatagramPacket(data, data.length, connection.ip, connection.port);
		
		socket.send(datapacket);
	}
	
	public void close(){
		running = false;
		socket.close();
	}
	
	public void run(){
		byte[] data = new byte[1024];
		DatagramPacket packet = new DatagramPacket(data, data.length);
		Packet datapacket;
		
		while(running){
			try{
				socket.receive(packet);
				
				for(Connection connection : connections){
					if(packet.getAddress().equals(connection.getAddress())){
						if(packet.getPort() == connection.getPort()){
							datapacket = new Packet(packet.getData(), packet.getLength());
							
							connection.addPacket(datapacket);
						}
					}
				}
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		
		return;
	}
}