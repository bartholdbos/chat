package tk.bartbart333.chat.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window {

	private JFrame frame;
	private JPanel panel;
	private JButton button;
	
	public Window() {
		frame = new JFrame("Chat Application");
		
		panel = new ContentPanel();
		panel.setPreferredSize(new Dimension(800, 600));
		panel.setLayout(null);
		initUI();
		frame.setContentPane(panel);
		
		frame.pack();
		frame.setMinimumSize(frame.getSize());
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
	}
	
	private void initUI() {
		panel.setBackground(new Color(255, 255, 255));
		
		button = new JButton("Send");
		button.setBounds(100, 100, 100, 25);
		panel.add(button);
	}
	
	public void show() {
		frame.setVisible(true);
	}
	
	private class ContentPanel extends JPanel {
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			g.clearRect(0, 0, getWidth(), getHeight());
			g.drawRect(getWidth() / 2 - 50, getHeight() / 2 - 50, 100, 100);
			button.setBounds(getWidth() / 2 - 40, getHeight() / 2 - 12, 80, 24);
		}
		
	}
	
}
