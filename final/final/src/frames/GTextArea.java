package frames;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextArea;

import main.GConstants;
import main.TextPrompt;

public class GTextArea extends JTextArea {
	private static final long serialVersionUID = GConstants.serialVersionUID;
	/**
	 * 
	 */
	private TextPrompt placeholder;

	public GTextArea() {
		super(3,0);
		this.setBackground(new Color(0xF5, 0xF5, 0xF5));
		this.setToolTipText("Tips for you : \n Note anything you want. :)");
		this.placeholder = new TextPrompt("   Type to add notes.", this);
		this.placeholder.setFont(new Font("Avenir-Light", Font.PLAIN, 14));
		

}

}
