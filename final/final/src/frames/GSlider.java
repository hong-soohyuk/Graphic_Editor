package frames;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import main.GConstants;

public class GSlider extends JSlider {
	/**
	* 
	*/
	private static final long serialVersionUID = GConstants.serialVersionUID;
	
	private SlideListen listener;
	private GDrawingPanel drawingpanel;
	public GSlider(int min, int max, int value) {
		super(min, max, value);
		this.listener = new SlideListen();
		this.setMajorTickSpacing(20);
		this.setMinorTickSpacing(1);
		this.setPaintTicks(true);
		this.setPaintLabels(true);
		
		this.addChangeListener(this.listener);
	}
	
	class SlideListen implements ChangeListener{
		@Override
		public void stateChanged(ChangeEvent e) {
			setStrWidth();
		}
	}
	
	private void setStrWidth() {
		final int stroke = this.getValue();
		this.drawingpanel.setstrWidth(stroke);
	}
	
	public void setAssociation(GDrawingPanel drawingPanel) {
		this.drawingpanel = drawingPanel;
	}

	public void initialize() {
		this.drawingpanel.setstrWidth(this.getValue());		
	}
}
