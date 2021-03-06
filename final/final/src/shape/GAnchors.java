package shape;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.io.Serializable;
import java.util.Vector;

@SuppressWarnings("serial")
public class GAnchors implements Serializable {
	
	private final int ANCHORS_RRHIGHT = 10;
	final private int ANCHOR_W = 9;
	final private int ANCHOR_H = 9;	
	
	public enum EAnchors{
		NW, NN, NE, EE, SE, SS, SW, WW, RR ,MM;
	}
	private Vector<Ellipse2D> anchors;

	public GAnchors() {
		
		this.anchors = new Vector<Ellipse2D>();
		for(int i=0;i<EAnchors.values().length-1;i++) {
			Ellipse2D anchor = new Ellipse2D.Double();
			this.anchors.add(anchor);
		}
	}

	private void setCoordinae(EAnchors eAnchor, Ellipse2D anchor, Rectangle bounds) {
		int x = 0;
		int y = 0;
		int w = ANCHOR_H;
		int h = ANCHOR_W;
		
		switch(eAnchor) {
		case NW:
			x=bounds.x;
			y=bounds.y;
			break;
		case NN:
			x=bounds.x+bounds.width/2;
			y=bounds.y;
			break;
		case NE:
			x=bounds.x+bounds.width;
			y=bounds.y;
			break;
		case EE:
			x=bounds.x+bounds.width;
			y=bounds.y+bounds.height/2;
			break;
		case SE:
			x=bounds.x+bounds.width;
			y=bounds.y+bounds.height;
			break;
		case SS:
			x=bounds.x+bounds.width/2;
			y=bounds.y+bounds.height;
			break;
		case SW:
			x=bounds.x;
			y=bounds.y+bounds.height;
			break;
		case WW:
			x=bounds.x;
			y=bounds.y+bounds.height/2;
			break;
		case RR:
			x=bounds.x+bounds.width/2;
			y=bounds.y-ANCHORS_RRHIGHT;
			break;
		default:
			break;
		}
		x = x-w/2;
		y = y-h/2;
		
		anchor.setFrame(x,y,w,h);
	}

	public void setBounds(Rectangle bounds) {
		for(int i=0;i<EAnchors.values().length-1;i++) {
			Ellipse2D anchor = this.anchors.get(i);
			this.setCoordinae(EAnchors.values()[i], anchor,bounds);
		}
	}

	public void draw(Graphics2D graphics2d) {
		graphics2d.setColor(Color.darkGray);		
		for(Ellipse2D eAnchor: this.anchors) {
			graphics2d.setStroke(new BasicStroke(1));
			graphics2d.draw(eAnchor);
		}
	}

	public EAnchors contains(int x, int y) {
		for(int i=0;i<EAnchors.values().length-1;i++) {
			if(this.anchors.get(i).contains(x,y)) {
				return EAnchors.values()[i];
			}
		}
		return null;
	}
}
