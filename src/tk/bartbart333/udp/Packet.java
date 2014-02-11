package tk.bartbart333.udp;

public class Packet {
	
	public Packet(byte[] buffer, int length) {
		String s = new String(buffer, 0, length);
	}
	
}