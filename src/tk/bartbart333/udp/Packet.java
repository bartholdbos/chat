package tk.bartbart333.udp;

import java.util.HashMap;
import java.util.Map.Entry;

public class Packet {
	
	private HashMap<String, String> headers = new HashMap<String, String>();
	private String body = "";
	
	public Packet(byte[] data, int length){
		String s = new String(data, 0, length);
		int headerend = s.indexOf("\n\n");
		
		String headers = s.substring(0, headerend);
		body = s.substring(headerend + 2, length);
		
		for(String line : headers.split("\n")){
			String[] entry = line.split("=");
			
			this.headers.put(entry[0], entry[1]);
		}
	}
	
	public Packet(String body){
		this.body = body;
	}
	
	public Packet(String body, HashMap<String, String> headers){
		this.body = body;
		this.headers = headers;
	}
	
	public String getValue(String key){
		return headers.get(key);
	}
	
	public String getType(){
		return getValue("type");
	}
	
	public int getSeq(){
		return Integer.valueOf(getValue("seq"));
	}
	
	public void setSeq(int seq){
		setValue("seq", String.valueOf(seq));
	}
	
	public void setValue(String key, String value){
		headers.put(key, value);
	}
	
	public String getBody(){
		return body;
	}
	
	public void setBody(String body){
		this.body = body;
	}
	
	public byte[] getData(){
		String data = "";
		
		for(Entry<String, String> s : headers.entrySet()){
			data += s.getKey() + "=" + s.getValue() + "\n";
		}
		
		data += "\n" + body;
		
		return data.getBytes();
	}
}