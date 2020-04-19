package Main;

import frames.MainFrame;

public class Main {
	public static void main(String[] args) {
		MainFrame mainFrame = new MainFrame();
		mainFrame.setTitle("this is title");
		mainFrame.initialize();
		mainFrame.setVisible(true);
	}
}
