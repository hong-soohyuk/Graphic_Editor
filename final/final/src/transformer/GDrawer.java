package transformer;

import java.awt.Graphics2D;

import shape.GShape;

public class GDrawer extends GTransformer {

	public GDrawer(GShape shape) {
		super(shape);
		}

	@Override
	public void initTransforming(Graphics2D gr2d, int x, int y) {
		this.shape.setOrigin(x, y);
	}

	@Override
	public void keepTransforming(Graphics2D gr2d, int x, int y) {
		this.shape.setPoint(x, y);
		this.shape.draw(gr2d);
	}

	@Override
	public void finishTransforming(Graphics2D gr2d, int x, int y) {
	}
	 
	public void continueTransforming(Graphics2D gr2d, int x, int y) {
		this.shape.addPoint(x, y);
	}
}
