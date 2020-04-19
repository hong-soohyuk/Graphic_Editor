package frames;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import Main.GConstant;

public class MainFrame extends JFrame {
	//attributes ex) color size etc.
	private static final long serialVersionUID = GConstant.serialVersionUID;
	/**
	 * 
	 */
	
	//components
	private GMenuBar menuBar;
	private GToolBar toolBar;
	private GDrawingPanel drawingPanel;
	
	public MainFrame() {
		super();
		//initialize attibutes
		this.setSize(GConstant.EMainFrame.eWidth.getValue(), GConstant.EMainFrame.eHeight.getValue());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		
		this.menuBar = new GMenuBar();
		this.setJMenuBar(this.menuBar);
		
		this.toolBar = new GToolBar();
		this.add(toolBar, BorderLayout.NORTH);

		this.drawingPanel = new GDrawingPanel();
		this.add(drawingPanel, BorderLayout.CENTER);
	}

	public void initialize() {
		//association
		this.toolBar.setAssociation(this.drawingPanel);
		
		//initialzie associative attributes
		this.menuBar.initialize();
		
		this.toolBar.initialize();
		
		this.drawingPanel.initialize();
	}
}
