package shape;

import java.awt.geom.Ellipse2D;

import main.GConstants;

public class GEllipse extends GShape implements Cloneable {
	private static final long serialVersionUID = GConstants.serialVersionUID;
	private Ellipse2D ellip;

	public GEllipse() {
		this.eDrawingStyle = EDrawingStyle.e2Points;
		this.ellip = new Ellipse2D.Float();
		this.shape = this.ellip;
	}

	@Override
	public void setOrigin(int x, int y) {
		this.startPX = x;
		this.startPY = y;
	}
	@Override
	public void setPoint(int x, int y) {
		this.ellip.setFrame(
				Math.min(this.startPX, x),
				Math.min(this.startPY, y),
				Math.abs(this.startPX - x),
				Math.abs(this.startPY - y));
	}

	@Override
	public void addPoint(int x, int y) {
	}
}
