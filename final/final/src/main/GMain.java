package main;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import frames.GMainFrame;

public class GMain {
	
	public static void main(String[] args) {
		try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
		
		catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e)
		{e.printStackTrace();}
		
		new GStartingPop();
//		Locale.setDefault(new Locale("English", "US"));

		GMainFrame mainFrame = new GMainFrame();
		mainFrame.initialize();
		mainFrame.setVisible(true);
	}
}
