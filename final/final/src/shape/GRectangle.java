package shape;

import java.awt.Rectangle;

import main.GConstants;

public class GRectangle extends GShape {
	private static final long serialVersionUID = GConstants.serialVersionUID;
	private Rectangle rec;
	public GRectangle() {
		this.eDrawingStyle = EDrawingStyle.e2Points;
		this.rec = new Rectangle();
		this.shape = this.rec;
	}
	@Override
	public void setOrigin(int x, int y) {
		this.startPX = x;
		this.startPY = y;
	}
	@Override
	public void setPoint(int x, int y) {
		this.rec.setBounds(
				Math.min(this.startPX,x), Math.min(this.startPY,y),
				
				Math.abs(this.startPX-x),Math.abs(this.startPY-y));
	}
	@Override
	public void addPoint(int x, int y) {}
}
