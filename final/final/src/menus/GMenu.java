package menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.EnumSet;
import java.util.Vector;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import frames.GDrawingPanel;
import main.GConstants;
import main.GConstants.EMenuItem;

public abstract class GMenu extends JMenu {
	private static final long serialVersionUID = GConstants.serialVersionUID;
	
	protected GDrawingPanel drawingpanel;
	protected ActionHandler actionHandler;
	protected Vector<JMenuItem> menuItems;
	
	protected EnumSet<EMenuItem> myEnumSet;
	
	public void setMyEnum(EnumSet<EMenuItem> myEnumSet) {this.myEnumSet = myEnumSet;}
	
	public GMenu(String name) {
		super(name);
		this.menuItems = new Vector<JMenuItem>();
		this.actionHandler = new ActionHandler();
	}
	public void initialize() {
		for(EMenuItem eMenu : this.myEnumSet) {
			this.addSeparator();
			JMenuItem menuItem = new JMenuItem(eMenu.getTitle());
			menuItem.setActionCommand(eMenu.getActionCommand());
			menuItem.addActionListener(this.actionHandler);
			menuItem.setAccelerator(eMenu.getKeystr());
			this.menuItems.add(menuItem);
			this.add(menuItem);
		}	
	}

	public void setAssociation(GDrawingPanel drawingPanel) {
		this.drawingpanel = drawingPanel;
	}
	private void invokeMethod(String methodName) {
		try {this.getClass().getMethod(methodName).invoke(this);}
		
		catch (IllegalAccessException | IllegalArgumentException 
				| InvocationTargetException | NoSuchMethodException| SecurityException e)
		{e.printStackTrace();}
		
	}
	protected class ActionHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent event) {
			String methodName = event.getActionCommand();
			invokeMethod(methodName);}
	}
}
