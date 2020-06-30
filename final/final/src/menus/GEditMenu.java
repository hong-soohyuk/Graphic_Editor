package menus;
import main.GConstants;



public class GEditMenu extends GMenu{
	// attributes
	private static final long serialVersionUID = GConstants.serialVersionUID;
	
	// components
	public GEditMenu(String name) {
		super(name);
	}
	public void copy() {
		this.drawingpanel.copy();
		}
	public void paste() {
		this.drawingpanel.paste();}
	public void cut() {this.drawingpanel.cut();}
	
	public void ungroup() {}
	public void bringForward() {this.drawingpanel.bringForward();}
	public void bringBackward() {this.drawingpanel.bringBackward();}
	public void undo() {
		this.drawingpanel.undo();
	}
	public void redo() {
		this.drawingpanel.redo();
	}
	

}
