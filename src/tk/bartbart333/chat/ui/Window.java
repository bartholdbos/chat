package tk.bartbart333.chat.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

public class Window {

	private JFrame frame;
	private JPanel panel;
	private JTextArea msgArea;
	private JScrollPane msgAreaPane;
	
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
		
		msgArea = new JTextArea();
		msgArea.setLineWrap(true);
		msgArea.setWrapStyleWord(true);
		msgArea.setFont(new Font("Baumans", Font.PLAIN, 15));
		msgAreaPane = new JScrollPane(msgArea);
		msgAreaPane.setBorder(BorderFactory.createEmptyBorder());
		msgAreaPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panel.add(msgAreaPane);
		
		InputMap input = msgArea.getInputMap();
		KeyStroke enter = KeyStroke.getKeyStroke("ENTER");
		KeyStroke shiftEnter = KeyStroke.getKeyStroke("shift ENTER");
		input.put(shiftEnter, "insert-break");
		input.put(enter, "text-submit");
		
		ActionMap actions = msgArea.getActionMap();
		actions.put("text-submit", new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				submitMessage();
			}
		});
	}
	
	private void submitMessage() {
		System.out.println("----------------");
		System.out.println(msgArea.getText());
		msgArea.setText("");
		msgArea.requestFocusInWindow();
	}
	
	public void show() {
		frame.setVisible(true);
	}
	
	private class ContentPanel extends JPanel {
		
		private Image img_profilePicture;
		private Image img_back;
		
		public ContentPanel() {
			try {
				img_profilePicture = ImageIO.read(new File("./assets/images/profile_picture.png"));
				img_back = ImageIO.read(new File("./assets/images/back.png"));
			} catch(Exception ex) {
				System.err.println("Could not load images!");
				System.exit(1);
			}
		}
		
		Color lineColor = new Color(128, 128, 128);
		
		public void paintComponent(Graphics graphics) {
			super.paintComponent(graphics);
			
			// Update graphics
			Graphics2D g = (Graphics2D)graphics;
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g.clearRect(0, 0, getWidth(), getHeight());
			
			// Draw lines
			int seperatorX = Math.min(getWidth() / 5, 276);
			int imageSize = seperatorX - 20;
			paintChatBox(g, seperatorX + 1 + 5, getHeight() - getHeight() / 6 - 5, getWidth() - seperatorX - 11, getHeight() / 6);
			g.drawLine(seperatorX, 0, seperatorX, getHeight());
			
			// Draw back button
			g.drawImage(img_back, 0, 0, seperatorX, seperatorX / 4, null);
			
			// Draw string 'friend history'
			g.setFont(new Font("Baumans", Font.PLAIN, (int)((12f/116f) * imageSize + 12)));
			g.setColor(new Color(128, 128, 128));
			int friendCrumblesOffs = (int)((12f/116f) * imageSize);
			g.drawString("chat history", 10, 80 + friendCrumblesOffs);

			// Draw friend history
			g.drawImage(img_profilePicture, 10, 80 + 24 + friendCrumblesOffs, imageSize, imageSize, null);
		}
		
		private void paintChatBox(Graphics2D g, int x, int y, int width, int height) {
			g.setColor(Color.black);
			int size = Math.min(height, 128);
			g.fillRect(x, y, size, size); // profile picture
			
			g.setColor(lineColor);
			g.drawLine(x + size + 5, y, x + width, y); // top
			g.drawLine(x + width, y, x + width, y + height - 1); // right
			g.drawLine(x + width, y + height - 1, x + size + 15, y + height - 1); // bottom
			g.drawLine(x + size + 15, y + height - 1, x + size + 15, y + 10); // left
			g.drawLine(x + size + 15, y + 10, x + size + 5, y); // tilted
			
			// Update text area
			msgAreaPane.setBounds(x + size + 18, y + 3, width - size - 20, height - 6);
		}
		
	}
	
}
