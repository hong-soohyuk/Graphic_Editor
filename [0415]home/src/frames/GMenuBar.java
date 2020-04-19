package frames;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Vector;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

import Main.GConstant;

public class GMenuBar extends JMenuBar {
	private static final long serialVersionUID = GConstant.serialVersionUID;
	/**
	 * 
	 */
	private Vector<JMenu> menus;
	public GMenuBar() {
		super();
		//set attribute
		this.menus = new Vector<JMenu>();
		for(GConstant.EMenubar menu : GConstant.EMenubar.values()) {
			try {
				Class<?> menuClass = Class.forName(menu.getClassName());
				Constructor<?> menuConstructor = menuClass.getDeclaredConstructor(String.class);

				Object menuObject = menuConstructor.newInstance(menu.getTitle());
				this.menus.add((JMenu) menuObject);
				this.add((JMenu) menuObject);
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException 
					| IllegalArgumentException | SecurityException | InvocationTargetException | NoSuchMethodException  e) {
				e.printStackTrace();
			}
		}
		//create components
	}

	public void initialize() {
		//set associations
		
		//set associative attributes
		
		//initialize components
		for (JMenu thisMenus : this.menus) {
			try {
				Method initMethod = thisMenus.getClass().getDeclaredMethod("initialize");
				initMethod.invoke(thisMenus);

			} catch (NoSuchMethodException | SecurityException | IllegalAccessException 
					| IllegalArgumentException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
	}
}
