package shape;

import main.GConstants;

public class GRubber extends GShape {
	private static final long serialVersionUID = GConstants.serialVersionUID;

	public GRubber() {
		this.eDrawingStyle = EDrawingStyle.eRubber;
	}
	
	@Override
	public void setOrigin(int x, int y) {}
	@Override
	public void setPoint(int x, int y) {}
	@Override
	public void addPoint(int x, int y) {}

	
}
