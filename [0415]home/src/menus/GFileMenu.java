package menus;

import java.util.Vector;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import Main.GConstant;

public class GFileMenu extends JMenu{
	private static final long serialVersionUID = GConstant.serialVersionUID;

	/**
	 * 
	 */
	private Vector<JMenuItem> menuItems;

	public GFileMenu(String name) {
		super(name);
		this.menuItems = new Vector<JMenuItem>();
		//create & register
		
		for (GConstant.EFilemenu eFileMenu : GConstant.EFilemenu.values()) {
			JMenuItem menuItem = new JMenuItem(eFileMenu.getTitle());
			this.menuItems.add(menuItem);
			this.add(menuItem);
		}
	}
	public void initialize() {
		
	}
}
