package shape;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import main.GConstants;

public class GSelectionRec extends GRectangle {
	/**
	 * 
	 */
	private Color selectColor;
	private Color seleclineColor;
	private static final long serialVersionUID = GConstants.serialVersionUID;
	public GSelectionRec() {
		super();
		this.eDrawingStyle = EDrawingStyle.eSelect;
		this.selectColor = new Color(0x7F, 0xFF, 0xD4, 42);
		this.seleclineColor = new Color(0x7F, 0xFF, 0xD4, 100);
	}
	@Override
	public void draw(Graphics2D gr2d) {
//		 = (Graphics2D) graphics;
		gr2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );
		gr2d.setColor(this.selectColor);
		gr2d.fill(this.shape);
		gr2d.setColor(this.seleclineColor);
		gr2d.draw(this.shape);
		
	}
}
