package menus;

import java.util.Vector;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import Main.GConstant;

public class GEditMenu extends JMenu{
	private static final long serialVersionUID = GConstant.serialVersionUID;
	/**
	 * 
	 */
	
	private Vector<JMenuItem> editMenus;
	public GEditMenu(String name) {
		super(name);
		this.editMenus = new Vector<JMenuItem>();
		for(GConstant.EEditmenu eEditmenu : GConstant.EEditmenu.values()) {
			JMenuItem items = new JMenuItem(eEditmenu.getTitle());
			this.editMenus.add(items);
			this.add(items);
		}
	}

	public void initialize() {		
	}
}
