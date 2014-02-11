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
		buffer[5] = '\n';
		buffer[6] = '\n';
		buffer[7] = 'b';
		buffer[8] = 'o';
		buffer[9] = 'd';
		buffer[10] = 'y';
		Packet packet = new Packet(buffer, 11);
		//Window window = new Window();
		//window.show();
	}
	
}