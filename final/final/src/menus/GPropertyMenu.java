package menus;

import java.util.Vector;

import frames.GDrawingPanel;
import main.GConstants;
import menuSub.GSubMenu;

public class GPropertyMenu extends GMenu {
	private static final long serialVersionUID = GConstants.serialVersionUID;
	/**
	 * 
	 */
	private Vector<GSubMenu> menus;

	public GPropertyMenu(String name) {
		super(name);
		this.menus = new Vector<GSubMenu>();
		for (GConstants.ESubMenu eMenu : GConstants.ESubMenu.values()) {
			GSubMenu menu = eMenu.getMenu();
			menu.setMyEnum(eMenu.getMyEnum());
			this.menus.add(menu);
			this.add(menu);
		}
	}
	public void initialize() {
		super.initialize();
		for (GSubMenu menu: this.menus) {menu.initialize();}
	}
	@Override
	public void setAssociation(GDrawingPanel drawingPanel) {
		super.setAssociation(drawingPanel);
		for (GSubMenu menu: this.menus) {
			menu.setAssociation(drawingPanel);
		}
	}
	public void clearAll() {this.drawingpanel.clearShapes();}
}
