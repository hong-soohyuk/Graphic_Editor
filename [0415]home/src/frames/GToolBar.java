package frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JToolBar;

import Main.GConstant;

public class GToolBar extends JToolBar {
	private static final long serialVersionUID = GConstant.serialVersionUID;
	/**
	 * 
	 */
	// components
	private actionHandler actionHandlerrrr;
	private Vector<JButton> buttons;

	GDrawingPanel drawingPanel;

	public GToolBar() {

		// create components
		this.actionHandlerrrr = new actionHandler();
		this.buttons = new Vector<JButton>();
		for (GConstant.EToolbar eTool : GConstant.EToolbar.values()) {
			JButton button = new JButton(eTool.getTitle());
			button.setActionCommand(eTool.toString());
			button.addActionListener(actionHandlerrrr);
			this.buttons.add(button);
			this.add(button);
		}
	}

	public void setAssociation(GDrawingPanel drawingPanel) {
		this.drawingPanel = drawingPanel;
	}

	public void initialize() {
		// set associations
		// set associative attributes
		this.buttons.get(GConstant.EToolbar.eRectangle.ordinal()).doClick();

		// initialize components
	}

	class actionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			drawingPanel.setCurrentTool(GConstant.EToolbar.valueOf(e.getActionCommand()));
			
		}
	}

}