package shape;

import java.awt.Polygon;

import main.GConstants;

public class GTriangle extends GShape implements Cloneable {

	private static final long serialVersionUID = GConstants.serialVersionUID;
	private Polygon triangle;

	
	public GTriangle() {
		this.eDrawingStyle = EDrawingStyle.e2Points;
		
		this.triangle = this.makeTri();
		this.shape = this.triangle;
	}
	private Polygon makeTri() {
		int[] xpoints = new int [3];
		int[] ypoints = new int [3];
		return new Polygon(xpoints, ypoints, 3);
	}
	@Override
	public void setOrigin(int x, int y) {
		this.startPX=x;
		this.startPY=y;
	}

	@Override
	public void setPoint(int x, int y) {
		int w = (x-this.startPX);
		
		this.triangle.xpoints[0]=this.startPX;
		this.triangle.ypoints[0]=y;
		
		this.triangle.xpoints[1]=this.startPX + w/2;
		this.triangle.ypoints[1]=this.startPY;
		
		this.triangle.xpoints[2]=this.startPX + w;
		this.triangle.ypoints[2]=y;
		
		this.triangle.invalidate();
	}

	@Override
	public void addPoint(int x, int y) {}

	
}
