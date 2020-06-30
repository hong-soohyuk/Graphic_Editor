package transformer;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;

import shape.GAnchors.EAnchors;
import shape.GShape;

public class GResizer extends GTransformer {
	public GResizer(GShape shape) {
		super(shape);
	}
	
	@Override
	public void initTransforming(Graphics2D graphics2d, int x, int y) {
		this.previousX = x;
		this.previousY = y;
	}

	@Override
	public void finishTransforming(Graphics2D graphics2d, int x, int y) {
	}
	
	@Override
	public void continueTransforming(Graphics2D gr2d, int x, int y) {		
	}
	
	
	private double getDX(double x, double width) {
		return (x - this.previousX) / width;
	}

	private double getDY(double y, double height) {
		return (y - this.previousY) / height;
	}
	@Override
	public void keepTransforming(Graphics2D graphics2d, int x, int y) {
		AffineTransform affineTransform = new AffineTransform();
		Rectangle bound = this.shape.getShape().getBounds();
		
		double dx = this.getDX(x, bound.getWidth());
		double dy = this.getDY(y, bound.getHeight());
		
		EAnchors anchor = this.shape.getESelectedAnchor();
		switch (anchor) {
		case NW:
			affineTransform.setToTranslation(bound.getMinX() + bound.getWidth(), bound.getMinY() + bound.getHeight());
			affineTransform.scale(1-dx, 1-dy);
			affineTransform.translate(-(bound.getMinX() + bound.getWidth()), -(bound.getMinY() + bound.getHeight()));
			break;
		
		case NN:
			affineTransform.setToTranslation(0, bound.getMinY() + bound.getHeight());
			affineTransform.scale(1, 1-dy);
			affineTransform.translate(0, -(bound.getMinY() + bound.getHeight()));
			break;
		
		case NE:
			affineTransform.setToTranslation(bound.getMinX(), bound.getMinY() + bound.getHeight());
			affineTransform.scale(1+dx, 1-dy);
			affineTransform.translate(-(bound.getMinX()), -(bound.getMinY() + bound.getHeight()));
			break;
		
		case WW:
			affineTransform.setToTranslation(bound.getMinX() + bound.getWidth(), 0);
			affineTransform.scale(1-dx, 1);
			affineTransform.translate(-(bound.getMinX() + bound.getWidth()), 0);
			break;
		
		case EE:
			affineTransform.setToTranslation(bound.getMinX(), 0);
			affineTransform.scale(1+dx, 1);
			affineTransform.translate(-(bound.getMinX()), 0);
			break;
		
		case SW:
			affineTransform.setToTranslation(bound.getMinX() + bound.getWidth(), bound.getMinY());
			affineTransform.scale(1-dx, 1+dy);
			affineTransform.translate(-(bound.getMinX() + bound.getWidth()), -(bound.getMinY()));
			break;
		
		case SS:
			affineTransform.setToTranslation(0, bound.getMinY());
			affineTransform.scale(1, 1+dy);
			affineTransform.translate(0, -(bound.getMinY()));
			break;
		
		case SE:
			affineTransform.setToTranslation(bound.getMinX(), bound.getMinY());
			affineTransform.scale(1+dx, 1+dy);
			affineTransform.translate(-(bound.getMinX()), -(bound.getMinY()));
			break;
		default:break;
		}
		
		this.shape.setShape(affineTransform.createTransformedShape(this.shape.getShape()));
		this.setPreXY(x, y);
	}


}
