package tk.bartbart333.udp;

import java.util.HashMap;

public class Packet {
	
	private HashMap<String, String> header = new HashMap<String, String>();
	private String body;
	
	public Packet(byte[] buffer, int length) {
		String s = new String(buffer, 0, length);
		int headerEnd = s.indexOf("\n\n");
		String header = s.substring(0, headerEnd);
		body = s.substring(headerEnd + 2).substring(0, length - header.length() - 2);
		
		String[] headerLines = header.split("\n");
		if(headerLines.length <= 1) {
			System.err.println("Recieved a packet without header, contents: " + body);
			return;
		}
		
		for(int i = 0; i < headerLines.length; i++) {
			String[] tokens = headerLines[i].split(":");
			if(tokens.length <= 1) {
				System.err.println("Invalid header line, ignoring:" + headerLines[i]);
				continue;
			}
		}
	}
	
	public String getHeaderProperty(String key) {
		if(!header.containsKey(key))
			return null;
		else
			return header.get(key);
	}
	
	public String getBody() {
		return body;
	}
	
}