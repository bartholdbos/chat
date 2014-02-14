package tk.bartbart333.chat;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.File;

import tk.bartbart333.chat.ui.Window;

public class Chat {
	
	public static void main(String[] args) {
		try {
			File fle_baumans = new File("./assets/fonts/Baumans-Regular.ttf");
			Font baumans = Font.createFont(Font.TRUETYPE_FONT, fle_baumans);
			
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(baumans);
		} catch(Exception ex) {
			System.err.println("Could not load fonts!");
			System.exit(1);
		}
		
		Window window = new Window();
		window.show();
	}
	
}