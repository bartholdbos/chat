package tk.bartbart333.chat;

import tk.bartbart333.udp.Packet;

public class Chat {
	
	public static void main(String[] args) {
		byte[] buffer = new byte[1024];
		buffer[0] = 'h';
		buffer[1] = 'e';
		buffer[2] = 'l';
		buffer[3] = 'l';
		buffer[4] = 'o';
		Packet packet = new Packet(buffer, 5);
		//Window window = new Window();
		//window.show();
	}
	
}