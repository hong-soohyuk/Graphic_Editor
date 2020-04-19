package shape;

import java.awt.Graphics;

public class GLine extends GShape{
	
	public GLine() {
		this.eDrawingStyle = EDrawingStyle.e2Points;

		this.xPoints = new int[2];
		this.yPoints = new int[2];
	}
	
	public void draw(Graphics graphics) {
		int x1 = this.xPoints[0];
		int y1 = this.yPoints[0];
		int x2 = this.xPoints[1];
		int y2 = this.yPoints[1];
		graphics.drawLine(x1, y1, x2, y2);
	}
}
