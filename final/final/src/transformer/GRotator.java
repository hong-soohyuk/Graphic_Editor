package transformer;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;

import shape.GShape;

public class GRotator extends GTransformer {
	public GRotator(GShape shape) {super(shape);}

	private Point rotatePoint;
	
    @Override
    public void initTransforming(Graphics2D graphics2D, int x, int y) {
    	this.setPreXY(x,y);
    	rotatePoint = new Point((int)this.shape.getBounds().getCenterX(),(int)this.shape.getBounds().getCenterY());
    }
    
    private double computeRotateAngle(Point centerP, Point startP, Point endP) {
    	double startAngle = Math.toDegrees(Math.atan2(startP.getY() - centerP.getY(), startP.getX()-centerP.getX()));
    	double endAngle = Math.toDegrees(Math.atan2(endP.getY()-centerP.getY(), endP.getX()-centerP.getX()));
    	double angle = endAngle - startAngle;
    	
    	if(angle<0) {angle += 360;}
    	return angle;
    }

    @Override
    public void keepTransforming(Graphics2D graphics2D, int x, int y) {
    	AffineTransform affineTransform = new AffineTransform();
    	double rotateAngle = Math.toRadians(computeRotateAngle(rotatePoint, new Point(previousX, previousY), new Point(x,y)));
    	affineTransform.rotate(rotateAngle, this.rotatePoint.getX(), this.rotatePoint.getY());
    	this.shape.setShape(affineTransform.createTransformedShape(this.shape.getShape()));
    	this.setPreXY(x,y);
    }

    @Override
    public void finishTransforming(Graphics2D graphics2D, int x, int y) {}
	@Override
	public void continueTransforming(Graphics2D gr2d, int x, int y) {}
}
