package menus;

import java.util.Vector;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import Main.GConstant;

public class GColorMenu extends JMenu {
	/**
	 * 
	 */
	private static final long serialVersionUID = GConstant.serialVersionUID;
	private Vector<JMenuItem> colorMenuItems;

	public GColorMenu(String name) {
		super(name);
		this.colorMenuItems = new Vector<JMenuItem>();
		// create & register
		for (GConstant.EColourmenu ecolourMenu : GConstant.EColourmenu.values()) {
			JMenuItem colourMenuItem = new JMenuItem(ecolourMenu.getTitle());
			this.colorMenuItems.add(colourMenuItem);
			this.add(colourMenuItem);
		}
	}

	public void initialize() {
	}
}
