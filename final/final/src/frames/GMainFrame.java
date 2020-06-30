package frames;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import main.GConstants;

public class GMainFrame extends JFrame {
	// attributes
	private static final long serialVersionUID = GConstants.serialVersionUID;
	
	// components
	private GSlider slideBar;
	
	private GMenuBar menuBar;
	private GToolBar toolBar;
	
	private JScrollPane txscroll;
	private GTextArea textArea;
	
	private JScrollPane scroll;
	private GFileBrowser fileList;
	private GDrawingPanel drawingPanel;
	private WindowEventAdapter windowAdapter;
	public GMainFrame() {
		super();
		
		// initialize attributes
		this.setTitle(GConstants.EStrVal.mainTitle.getValue());
		this.setSize(GConstants.EMainFrame.eWidth.getValue(), 
				GConstants.EMainFrame.eHeight.getValue());
		this.setBackground(Color.DARK_GRAY);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setLayout(new BorderLayout());
		
		this.setLocationRelativeTo(null);
		
		// create,register components
		this.windowAdapter = new WindowEventAdapter();
		this.addWindowListener(windowAdapter);
		
		this.slideBar = new GSlider(1, 50, 1);
		this.add(this.slideBar, BorderLayout.PAGE_START);
		
		this.menuBar = new GMenuBar();
		this.setJMenuBar(this.menuBar);
		
		this.toolBar = new GToolBar();
		this.add(this.toolBar, BorderLayout.LINE_END);
		
		this.fileList = new GFileBrowser();
		this.scroll = new JScrollPane(this.fileList);
		this.scroll.setPreferredSize(new Dimension(GConstants.EMainFrame.eWidth.getValue()/6, GConstants.EMainFrame.eHeight.getValue()));
		this.scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		this.scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		this.add(this.scroll, BorderLayout.LINE_START);
		
		this.textArea = new GTextArea();
		this.txscroll = new JScrollPane(this.textArea);
		this.txscroll.setPreferredSize(new Dimension(GConstants.EMainFrame.eWidth.getValue(), GConstants.EMainFrame.eHeight.getValue()/10));
		this.txscroll.setHorizontalScrollBarPolicy(32);
		this.txscroll.setVerticalScrollBarPolicy(22);
		
		this.add(this.txscroll, BorderLayout.PAGE_END);
		
		this.drawingPanel = new GDrawingPanel();
		this.drawingPanel.setFocusable(true);
		this.add(this.drawingPanel, BorderLayout.CENTER);
	}

	public void initialize() {
		// set associations
		this.slideBar.setAssociation(this.drawingPanel);
		this.toolBar.setAssociation(this.drawingPanel);
		this.menuBar.setAssociation(this.drawingPanel);
//		this.fileList.setAssociation(this.drawingPanel);
		this.drawingPanel.setAssociation(this.textArea);
		// initialize associative attributes
		
		// initialize components
		this.slideBar.initialize();
		this.menuBar.initialize();
		this.toolBar.initialize();		
		this.drawingPanel.initialize();
	}
	
	public class WindowEventAdapter extends WindowAdapter{
		@Override
		public void windowClosing(WindowEvent e) {
			if (menuBar.callSaveCheck()) {dispose();}
		}
	}
}
