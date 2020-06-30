package shape;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.GeneralPath;

import main.GConstants;

public class GPen extends GShape{
	private static final long serialVersionUID = GConstants.serialVersionUID;
	private GeneralPath pen;
	
	public GPen() {
		super();
		this.eDrawingStyle = EDrawingStyle.e2Points;
		this.pen = new GeneralPath();
		this.shape = this.pen;
	}

	@Override
	public void setOrigin(int x, int y) {
		this.pen.moveTo(x, y);
	}

	@Override
	public void setPoint(int x, int y) {
		this.pen.lineTo(x, y);
	}
	@Override
	public void draw(Graphics2D gr2d) {
		
		gr2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );
		gr2d.setStroke(new BasicStroke(this.strok, BasicStroke.JOIN_ROUND,BasicStroke.JOIN_MITER, 10.0f, null, 0.0f));

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
	@Override
	public void addPoint(int x, int y) {}
}
