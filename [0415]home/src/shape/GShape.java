package shape;

import java.awt.Graphics;
import java.lang.reflect.InvocationTargetException;


public abstract class GShape {

	public enum EDrawingStyle {
		e2Points, eNPoint;
	}
	
	protected EDrawingStyle eDrawingStyle;
	
	protected int xPoints[];
	protected int yPoints[];
	
	protected int nPoint;
	
	public GShape() {
		this.nPoint = 0;
	}
	public EDrawingStyle geteDrawingStyle() {return this.eDrawingStyle;}
	
	public abstract void draw(Graphics graphics);
	public GShape clone() {
		try {
			return this.getClass().getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void setOrigin(int x, int y) {
		this.xPoints[this.nPoint] = x;
		this.yPoints[this.nPoint] = y;
		addPoint(x, y);
	}
	public void setPoint(int x, int y) {
		this.xPoints[this.nPoint] = x;
		this.yPoints[this.nPoint] = y;
	}
	public void addPoint(int x, int y) {
		this.nPoint++;
		this.xPoints[this.nPoint] = x;
		this.yPoints[this.nPoint] = y;
	}
}
