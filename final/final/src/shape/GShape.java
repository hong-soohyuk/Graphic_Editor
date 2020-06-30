package shape;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import main.GConstants;

public abstract class GShape implements Serializable,Cloneable {
	private static final long serialVersionUID = GConstants.serialVersionUID;
	
	protected int startPX;
	protected int startPY;
	protected int secondPX;
	protected int secondPY;
	
	protected Shape shape;
	public Shape getShape() {return this.shape;}
	public void setShape(Shape shape) {this.shape = shape;}
	
	protected Color lineColor,fillColor;
	protected int strok;
	protected GAnchors anchors;
	
	protected GAnchors.EAnchors eSelectedAnchor;
	
	public enum EDrawingStyle {e2Points, eNPoints, eRubber, eSelect}
	
	protected EDrawingStyle eDrawingStyle;
	protected boolean bSelected;
	
	public GShape() {
		this.lineColor=null;
		this.fillColor=null;
		this.strok = 1;
		this.bSelected=false;
		this.anchors = new GAnchors();
	}
	
	public EDrawingStyle getEDrawingStyle() { return this.eDrawingStyle; }
	public void setSelected(boolean bSelected) {this.bSelected=bSelected;}
	public boolean getSelected() {return this.bSelected;}
	
	
	public void draw(Graphics2D gr2d) {				
		gr2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );
		
		gr2d.setStroke(new BasicStroke(this.strok));

		if(this.fillColor!=null) {
			gr2d.setColor(this.fillColor);
			gr2d.fill(this.shape);
		}
		if(this.lineColor != null) {
			gr2d.setColor(this.lineColor);
			gr2d.draw(this.shape);
		}
		if(this.bSelected) {
			this.anchors.setBounds(this.shape.getBounds());
			this.anchors.draw(gr2d);
			gr2d.setStroke(new BasicStroke(0.4f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{9}, 0));
			gr2d.draw(this.shape.getBounds());
			gr2d.setStroke(new BasicStroke(this.strok));
		}
	}
	public GShape clone() {
		try {
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
			objectOutputStream.writeObject(this);

			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
			ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
			return (GShape) objectInputStream.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	public void setLineColor(Color lineColor) {
		this.lineColor = lineColor;
	}
	public void setFillColor(Color fileColor) {
		this.fillColor=fileColor;
	}
	public void setStroke(int strokWidth) {
		this.strok = strokWidth;
	}
	
	public boolean contains(int x, int y) {
		boolean bContains = false;
		this.eSelectedAnchor = null;
		if(this.bSelected) {
			this.eSelectedAnchor = this.anchors.contains(x,y);
		}
		if(this.eSelectedAnchor == null) {
			if(this.shape.contains(x, y)) {
				this.eSelectedAnchor = GAnchors.EAnchors.MM;
				bContains=true;
			}
		}else {bContains = true;}
		return bContains;
	}
	
	public Rectangle getBounds() {;
		return this.shape.getBounds();
	}

	
	public GAnchors.EAnchors getESelectedAnchor(){return this.eSelectedAnchor;}
	
	public abstract void setOrigin(int x, int y);
	public abstract void setPoint(int x, int y);
	public abstract void addPoint(int x, int y);
	public void move(double dw, double dh) {}
	public void resize(int x, int y) {}
	
	
}
