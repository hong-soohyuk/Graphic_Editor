package transformer;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.Vector;

import shape.GShape;

public abstract class GTransformer {

	protected GShape shape;
	protected int previousX;
	protected int previousY;
	protected Point anchorPoint;
	protected Vector<GShape> groupList;	
	
	public GTransformer(GShape shape) {
		this.shape = shape;
		}
	abstract public void initTransforming(Graphics2D gr2d, int x, int y);
	abstract public void keepTransforming(Graphics2D gr2d, int x, int y);
	abstract public void finishTransforming(Graphics2D gr2d, int x, int y);
	abstract public void continueTransforming(Graphics2D gr2d, int x, int y);
	
	
	public void setPreXY(int x, int y){
		this.previousX = x;
		this.previousY = y;
	}
	
	public Point getAnchorP(){
		return anchorPoint;
	}
	
	public Vector<GShape> getGroupList() {
		return groupList;
	}
	
	public void setGroupList(Vector<GShape> groupList) {
		this.groupList = groupList;
	}
	
}
