package shape;

import java.awt.geom.Line2D;

import main.GConstants;

public class GLine extends GShape implements Cloneable {

	private static final long serialVersionUID = GConstants.serialVersionUID;

	private Line2D line;
	public GLine() {
		this.eDrawingStyle = EDrawingStyle.e2Points;
		this.line = new Line2D.Double();
		this.shape = this.line;
	}
	
	public boolean contains(int x, int y) {
		boolean bContains = false;
		this.eSelectedAnchor = null;
		if(this.bSelected) {
			this.eSelectedAnchor = this.anchors.contains(x,y);
		}
		if(this.eSelectedAnchor == null) {
			if(this.line.ptSegDist(x,y)<7) {
				this.eSelectedAnchor = GAnchors.EAnchors.MM;
				bContains=true;
			}
		}else {bContains=true;}
		return bContains;
	}
	
	@Override
	public void setOrigin(int x, int y) {
		this.line.setLine(x, y, x, y);
	}

	@Override
	public void setPoint(int x, int y) {
		this.line.setLine(line.getX1(), line.getY1(), x, y);
	}

	@Override
	public void addPoint(int x, int y) {
	}
}
