package transformer;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import shape.GShape;

public class GMover extends GTransformer {
	public GMover(GShape shape) {super(shape);}
	@Override
	public void initTransforming(Graphics2D gr2d, int x, int y) {
		this.setPreXY(x,y);
	}

	@Override
	public void keepTransforming(Graphics2D gr2d, int x, int y) {
		double dw = x - this.previousX;
		double dh = y - this.previousY;
		AffineTransform af = new AffineTransform();
		af.translate(dw, dh);
		this.shape.setShape(af.createTransformedShape(this.shape.getShape()));
		this.setPreXY(x, y);
	}
	@Override
	public void finishTransforming(Graphics2D gr2d, int x, int y) {}
	@Override
	public void continueTransforming(Graphics2D gr2d, int x, int y) {}
}
