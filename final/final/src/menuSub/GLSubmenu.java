package menuSub;

import java.awt.Color;

import javax.swing.JColorChooser;

import main.GConstants;

public class GLSubmenu extends GSubMenu {
	private static final long serialVersionUID = GConstants.serialVersionUID;
	/**
	 * 
	 */
	public GLSubmenu(String name) {super(name);}
	public void setLineColor() {
		Color selectedColor = JColorChooser.showDialog(this.drawingpanel,
				"", this.drawingpanel.getForeground());
		this.drawingpanel.setLineColor(selectedColor);
	}
	
	public void setFillColor() {
		Color selectedColor = JColorChooser.showDialog(this.drawingpanel,
				"", this.drawingpanel.getForeground());
		this.drawingpanel.setFillColor(selectedColor);
	}
}

