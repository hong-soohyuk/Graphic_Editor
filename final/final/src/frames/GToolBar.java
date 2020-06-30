package frames;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;

import main.GConstants;

public class GToolBar extends JToolBar {
	// attributes
	private static final long serialVersionUID = GConstants.serialVersionUID;
	
	// components
	private ActionHadler actionHadler;
	private Vector<JRadioButton> buttons;
	
	private ButtonGroup buttonGroup;
	private Box verticalBox;
	// associations
	private GDrawingPanel drawingPanel;
	
	public GToolBar() {
		super(null, JToolBar.VERTICAL);
		this.setFloatable(false);
		this.buttonGroup = new ButtonGroup();
		this.verticalBox = Box.createVerticalBox();
		// create components
		this.actionHadler = new ActionHadler();
		this.buttons = new Vector<JRadioButton>();		
		
		for (GConstants.EToolbar eTool: GConstants.EToolbar.values()) {
			JRadioButton button = new JRadioButton();
			button.setIcon(new ImageIcon(eTool.getIcon()));
			button.setSelectedIcon(new ImageIcon(eTool.getSeIcon()));
			button.setActionCommand(eTool.toString());
			button.addActionListener(this.actionHadler);
			this.verticalBox.add(button);
			this.buttonGroup.add(button);
			
			this.buttons.add(button);
		}
		this.add(this.verticalBox);
//		this.addSeparator(new Dimension(10,4));
	}
	
	public void setAssociation(GDrawingPanel drawingPanel) {
		this.drawingPanel = drawingPanel;
	}
	public void initialize() {
		// set associations
		
		// set associative attributes
		this.buttons.get(0).doClick();
		// initialize components
	}
	class ActionHadler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			drawingPanel.setCurrentTool(GConstants.EToolbar.valueOf(event.getActionCommand()));
		}		
	}
}
