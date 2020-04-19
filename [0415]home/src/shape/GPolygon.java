package shape;

import java.awt.Graphics;

public class GPolygon extends GShape{
	public final static int nMaxPoint = 100;
	public GPolygon() {
		this.eDrawingStyle = EDrawingStyle.eNPoint;
		this.xPoints = new int [nMaxPoint];
		this.yPoints = new int [nMaxPoint];
	}
	public void draw(Graphics graphics) {
		graphics.drawPolygon(this.xPoints, this.yPoints, this.nPoint+1);
	}
}
