package shape;

import java.awt.Polygon;

import main.GConstants;

public class GPolygon extends GShape implements Cloneable {
	private static final long serialVersionUID = GConstants.serialVersionUID;
	private Polygon polygon;
	public GPolygon() {
		this.eDrawingStyle = EDrawingStyle.eNPoints;
		this.polygon = new Polygon();
		this.shape = this.polygon;
	}
	
	@Override
	public void setOrigin(int x, int y) {
		this.polygon.addPoint(x, y);
		this.polygon.addPoint(x, y);

	}
	@Override
	public void setPoint(int x, int y) {
		this.polygon.xpoints[polygon.npoints-1]= x;
		this.polygon.ypoints[polygon.npoints-1]= y;
		this.polygon.invalidate();
	}
	@Override
	public void addPoint(int x, int y) {this.polygon.addPoint(x, y);}
}
