package frames;

import java.util.Vector;

import javax.swing.JMenuBar;

import main.GConstants;
import main.GConstants.EMenubar;
import menus.GFileMenu;
import menus.GMenu;

public class GMenuBar extends JMenuBar {
	// attributes
	private static final long serialVersionUID = GConstants.serialVersionUID;
	
	// components
	private Vector<GMenu> menus;
	
	public GMenuBar() {
		super();
		this.menus = new Vector<GMenu>();
		for (GConstants.EMenubar eMenu: GConstants.EMenubar.values()) {
			GMenu menu = eMenu.getMenu();
			menu.setMyEnum(eMenu.getMyEnum());
			this.menus.add(menu);
			this.add(menu);			
		}
	}

	public void initialize() {
		for (GMenu menu: this.menus) {menu.initialize();}
	}

	public void setAssociation(GDrawingPanel drawingPanel) 
	{for(GMenu menu : this.menus ) {menu.setAssociation(drawingPanel);}}
	
	public boolean callSaveCheck() {
		GFileMenu abd = (GFileMenu)this.menus.get(EMenubar.eFile.ordinal());
		return abd.checkSave();
	}
}
