package frames;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.EmptyStackException;
import java.util.Stack;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import clipboard.Clipboard;
import main.GConstants;
import main.GConstants.ECursor;
import main.GConstants.EToolbar;
import shape.GAnchors;
import shape.GShape;
import shape.GShape.EDrawingStyle;
import transformer.GDrawer;
import transformer.GMover;
import transformer.GResizer;
import transformer.GRotator;
import transformer.GTransformer;

public class GDrawingPanel extends JPanel implements Printable{
	// attributes
	private static final long serialVersionUID = GConstants.serialVersionUID;
	
	private enum EDrawingState {eIdle, eDrawing, eTransforming;}
	private EDrawingState eDrawingState;	
	private Color lineColor, fillColor;
	private int strokWidth;
	private boolean bUpdated;

	// components
	private MouseHandler mouseHandler;
	private Vector<GShape> shapes;
	
	private Stack<Vector<GShape>> undostack = new Stack<Vector<GShape>>();
	private Stack<Vector<GShape>> redostack = new Stack<Vector<GShape>>();

	// association components

	//WORKING VARIABLES
	private int pasX;
	private int pasY;
	
	private boolean multiselec;
	
	private GShape currentShape;
	private GShape currentTool;
	private GTransformer transf;
	
	private Clipboard clipboard;
	
	private Gpopup popup;
	
	private GTextArea textArea;
	
	// constructors and initializers
	public GDrawingPanel() {
		// attributes
		
		this.setLayout(new BorderLayout(0,10));
		this.setForeground(Color.black);
		this.setBackground(Color.white);
		this.eDrawingState = EDrawingState.eIdle;
		
		this.setComponentPopupMenu(popup);
		this.setInheritsPopupMenu(true);
		
		// components
		this.mouseHandler = new MouseHandler();
		this.addMouseListener(this.mouseHandler);
		this.addMouseMotionListener(this.mouseHandler);
			
		
		addKeyBinding(this, KeyEvent.VK_C, false, "seleOn", e-> this.multiselec = true);//press
		addKeyBinding(this, KeyEvent.VK_C, true, "seleOff", e-> this.multiselec = false);//release
		this.shapes = new Vector<GShape>();
		//working variables
		this.currentTool = null;
		this.lineColor = null;
		this.fillColor = null;
		this.strokWidth = 0;
		
		this.currentShape = null;
		this.bUpdated = false;
		this.clipboard = new Clipboard();
//		this.staPoint = null;
		this.textArea = null;
	}
	public void setAssociation(GTextArea textArea) {
		this.textArea = textArea;
		this.textArea.getDocument().addDocumentListener(new DocumentListener() {
			public void insertUpdate(DocumentEvent e) {setUpdate(true);}
			public void changedUpdate(DocumentEvent e) {setUpdate(true);}
			public void removeUpdate(DocumentEvent e) {setUpdate(true);}
		});
	}
	public void initialize() {
		// set associations
		// set associative attributes
		// initialize components
		this.lineColor = Color.black;
		this.fillColor = this.getBackground();
		this.transf = null;
	}

	// setters & getters
	public Vector<GShape> getShapes() {return this.shapes;}
	@SuppressWarnings("unchecked")
	public void setShapes(Object shapes) {
		this.shapes = (Vector<GShape>) shapes;
		this.repaint();
	}
	public String getText() {
		return this.textArea.getText();
	}
	public void setText(Object tex) {
		this.textArea.setText((String) tex);
	}
	public void clearShapes() {
		this.undostack.push(this.copytoStack());
		
		this.shapes.clear();
		this.repaint();
	}
	public void setCurrentTool(EToolbar eToolBar) {
		this.currentTool = eToolBar.getTool();
	}
	public boolean isUpdated() {
		return this.bUpdated;
	}
	public void setUpdate(boolean bUpdated) {
		this.bUpdated = bUpdated;
	}

	public void setLineColor(Color lineColor) {
		this.lineColor=lineColor;
	}
	public void setFillColor(Color fillColor) {
		this.fillColor=fillColor;
	}
	public void setstrWidth(int stroke) {
		this.strokWidth = stroke;
	}
	
	// methods
	public void paint(Graphics graphics) {
		super.paint(graphics);
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D gr2d = (Graphics2D) g;
		for (GShape shape: this.shapes) {shape.draw(gr2d);}
	}

	private void checkCursor(int x, int y) {
		GShape selectedShape = this.onShape(x, y);
		if(selectedShape==null) {
			this.setCursor(ECursor.eDefault.getCursor());
		}else {
			GAnchors.EAnchors eSelectedAnchor = selectedShape.getESelectedAnchor();
			switch(eSelectedAnchor) {
			case NW:this.setCursor(ECursor.eNW.getCursor());break;
			case NN:this.setCursor(ECursor.eNN.getCursor()); break;
			case NE:this.setCursor(ECursor.eNE.getCursor()); break;
			case EE:this.setCursor(ECursor.eEE.getCursor()); break;
			case SE:this.setCursor(ECursor.eSE.getCursor()); break;
			case SS:this.setCursor(ECursor.eSS.getCursor()); break;
			case SW:this.setCursor(ECursor.eSW.getCursor()); break;
			case WW:this.setCursor(ECursor.eWW.getCursor()); break;
			case RR:this.setCursor(ECursor.eRotate.getCursor()); break;
			case MM:this.setCursor(ECursor.eMove.getCursor()); break;
			default:break;
			}
		}
	}
	private GShape onShape(int x, int y) {
		if (this.eDrawingState != EDrawingState.eDrawing) {
			for (int i = this.shapes.size() - 1; i > -1; i--) {
				GShape onsh = this.shapes.get(i);
				if (onsh.contains(x, y)) {
					return onsh;
				}
			}
		}
		return null;
	}
	
	private void setSelected(GShape selectedShape) {
		if (!multiselec) {
			for(GShape shape : this.shapes) {shape.setSelected(false);}
		}
		selectedShape.setSelected(true);
		this.repaint();
	}
	private void initTransforming(GShape shape, int x, int y) {
		this.undostack.push(this.copytoStack());

		if (shape == null) {
			this.currentShape = this.currentTool.clone();
			this.currentShape.setLineColor(this.lineColor);
			this.currentShape.setFillColor(this.fillColor);
			this.currentShape.setStroke(this.strokWidth);
			this.shapes.add(this.currentShape);
			this.transf = new GDrawer(this.currentShape);
			this.setSelected(this.currentShape);
			
		} else {		
			//transformation
			this.currentShape = shape;
			switch (shape.getESelectedAnchor()) {
			case MM : 
				this.transf = new GMover(this.currentShape);
				break;
			case RR : this.transf = new GRotator(this.currentShape);
				break;
			default : this.transf = new GResizer(this.currentShape);
				break;
			}
		}
		Graphics2D graphics = (Graphics2D) this.getGraphics();
		this.transf.initTransforming(graphics, x, y);
	}
	private void keepTransforming(int x, int y) {
		Graphics2D graphics = (Graphics2D) this.getGraphics();
		this.transf.keepTransforming(graphics, x, y);
	}
	private void finishTransforming(int x, int y) {
		Graphics2D graphics = (Graphics2D) this.getGraphics();
		this.transf.finishTransforming(graphics, x, y);
		this.bUpdated = true;

		this.setSelected(this.currentShape);
	}
	private void finishSelecting(int x, int y) {
		for (GShape shape : this.shapes) {
			if (this.currentShape.getShape().intersects(shape.getBounds())) {
				shape.setSelected(true);
			}
		}
		this.shapes.remove(this.currentShape);//gotta remove selection Rectangle.
		this.repaint();
		
	}
	
	private void continueTransforming(int x, int y) {
		Graphics2D graphics = (Graphics2D) this.getGraphics();
		this.transf.continueTransforming(graphics, x, y);
	}

	public void removeShape(GShape onShape) {
		this.undostack.push(this.copytoStack());
		
		this.shapes.remove(onShape);
		this.shapes.trimToSize();
		this.repaint();
	}
	
	// inner class
	class MouseHandler implements MouseMotionListener, MouseListener {
		@Override
		public void mouseClicked(MouseEvent event) {
				if (event.getClickCount() == 1) {
					this.mouse1Clicked(event);
				} else if (event.getClickCount() == 2) {
					this.mouse2Clicked(event);
				}
			
		}
		// n point drawing
		private void mouse1Clicked(MouseEvent event) {
			if (!event.isPopupTrigger()) {
			int x = event.getX();
			int y = event.getY();
			GShape shape = onShape(x, y);
			if(shape==null) {
				if (currentTool.getEDrawingStyle() == EDrawingStyle.eNPoints && eDrawingState ==EDrawingState.eIdle) {
					initTransforming(null, x, y);
					eDrawingState = EDrawingState.eDrawing;
				}else if (currentTool.getEDrawingStyle() == EDrawingStyle.eNPoints && eDrawingState ==EDrawingState.eDrawing) {
					continueTransforming(x, y);
				}
			} else {
				setSelected(shape);
			}
		}
	}

		private void mouse2Clicked(MouseEvent event) {
			int x = event.getX();
			int y = event.getY();
			if (currentTool.getEDrawingStyle() == EDrawingStyle.eNPoints && eDrawingState==EDrawingState.eDrawing) {
				finishTransforming(x, y);
				eDrawingState = EDrawingState.eIdle;
			}
			if (currentTool.getEDrawingStyle() == EDrawingStyle.eRubber && eDrawingState==EDrawingState.eIdle) {
				removeShape(onShape(x,y));
			}
		}
		@Override
		public void mouseMoved(MouseEvent event) {
			int x = event.getX();
			int y = event.getY();
			if (currentTool.getEDrawingStyle() == EDrawingStyle.eNPoints && eDrawingState==EDrawingState.eDrawing) {
				keepTransforming(x, y);
				repaint();
			}
			checkCursor(x,y);
		}
		// 2 point drawing
		@Override
		public void mousePressed(MouseEvent event) {
			if (event.isPopupTrigger()) {
				System.out.println("right button press");
				makePop(event);
			} else {
				int x = event.getX();
				int y = event.getY();
				GShape shape = onShape(x, y);
				if (eDrawingState == EDrawingState.eIdle) {
					if (shape == null) {
						if (currentTool.getEDrawingStyle() == EDrawingStyle.e2Points
								|| currentTool.getEDrawingStyle() == EDrawingStyle.eSelect) {
							initTransforming(null, x, y);
							eDrawingState = EDrawingState.eDrawing;
						}
					} else {
						initTransforming(shape, x, y);
						eDrawingState = EDrawingState.eTransforming;
					}
				}
			}
		}
		@Override
		public void mouseDragged(MouseEvent event) {
			int x = event.getX();
			int y = event.getY();
			keepTransforming(x, y);
			repaint();
		}
		
		@Override
		public void mouseReleased(MouseEvent event) {
				int x = event.getX();
				int y = event.getY();
				if (eDrawingState == EDrawingState.eTransforming) {
					finishTransforming(x, y);
					eDrawingState = EDrawingState.eIdle;
				} else if (eDrawingState == EDrawingState.eDrawing) {
					if (currentTool.getEDrawingStyle() == EDrawingStyle.e2Points) {
						finishTransforming(x, y);
						eDrawingState = EDrawingState.eIdle;
					} else if (currentTool.getEDrawingStyle() == EDrawingStyle.eSelect) {
						finishSelecting(x, y);
						eDrawingState = EDrawingState.eIdle;
					}
				}
				//////it is not called.
				if(event.isPopupTrigger()) {System.out.println("right button released");
				makePop(event);}
				//////
			repaint();
		}
		
		@Override
		public void mouseEntered(MouseEvent event) {}

		@Override
		public void mouseExited(MouseEvent event) {}
	}

	private void makePop(MouseEvent event) {
		Vector<GShape> selectedShapes = new Vector<GShape>();

		for (int i = this.shapes.size() - 1; i > -1; i--) {
			if (this.shapes.get(i).getSelected()) {
				selectedShapes.add(this.shapes.get(i));
			}
		}
		this.popup = new Gpopup(selectedShapes);
		this.undostack.push(this.copytoStack());
		this.popup.addPopupMenuListener(new PopupMenuListener() {
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {}
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {repaint();}
			public void popupMenuCanceled(PopupMenuEvent e) {}
		});
		this.popup.show(event.getComponent(), event.getX(), event.getY());
	}
	
//	public void addImage(File imageFile) {
//		this.shapes.add(new GImageRectangle(imageFile));
//		this.repaint();
//	}
	
	public void copy() {
		this.pasX = 10;
		this.pasY = 10;
		Vector<GShape> selectedShapes = new Vector<GShape>();
		for (GShape shape : this.shapes) {
			if (shape.getSelected()) {
				GShape copyShape = shape.clone();
				selectedShapes.add(copyShape);
			}
		}
		this.clipboard.setContents(selectedShapes);
	}
	public Vector<GShape> getSelectedShapes() {
		Vector<GShape> selectedShapes = new Vector<GShape>();
		
		for(int i=this.shapes.size()-1; i > -1 ; i--) {
			if(this.shapes.get(i).getSelected()) {
				selectedShapes.add(this.shapes.get(i));
				this.removeShape(this.shapes.get(i));				
			}
		}
		return selectedShapes;
	}
	public void cut() {
		this.pasX = 10;
		this.pasY = 10;
		this.clipboard.setContents(getSelectedShapes());
		this.repaint();
	}

	public void paste() {
		this.undostack.push(this.copytoStack());

		Vector<GShape> pasteShapes = this.clipboard.getContents();
		
		for(GShape shape : this.shapes){shape.setSelected(false);}
		
		for (GShape shape : pasteShapes) {
			GShape clonedShape = shape.clone();
			AffineTransform affineTransform = new AffineTransform();
			affineTransform.translate(pasX, pasY);
			clonedShape.setShape(affineTransform.createTransformedShape(clonedShape.getShape()));
			this.shapes.add(clonedShape);
			this.pasX += 10;
			this.pasY += 10;
		}
		repaint();
	}

	public void print() {
		PrinterJob pj = PrinterJob.getPrinterJob();
		pj.setPrintable(this);
		if(pj.printDialog()) {
			try {pj.print();}
			catch(PrinterException e) {JOptionPane.showMessageDialog(this,"exception during print.");}
		}
	}

	@Override
	public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
		if(pageIndex>0) return NO_SUCH_PAGE;
		Graphics2D gr2d = (Graphics2D)graphics;
		gr2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
		for (GShape shape : this.shapes) {shape.draw(gr2d);}
		return PAGE_EXISTS;
	}

	public void bringForward() {
		GShape temp;
		int index = -1;
		for (GShape shape : this.shapes) {
			if (shape.getSelected()) {
				index = this.shapes.indexOf(shape);
				if (index+1<this.shapes.size()) {
					temp = this.shapes.get(index + 1);
					this.shapes.set(index + 1, shape);
					this.shapes.set(index, temp);
					break;
				}
			}
		}
		this.repaint();
	}

	public void bringBackward() {
		GShape temp;
		int index = -1;
		for (GShape shape : this.shapes) {
			if (shape.getSelected()) {
				index = this.shapes.indexOf(shape);
				if (index>0) {
					temp = this.shapes.get(index - 1);
					this.shapes.set(index - 1, shape);
					this.shapes.set(index, temp);
					break;
				}
			}
		}
		this.repaint();
	}
	public static void addKeyBinding(JComponent comp, int keyCode,boolean release, String id, ActionListener actionListener) {
		InputMap im = comp.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		ActionMap ap = comp.getActionMap();
		im.put(KeyStroke.getKeyStroke(keyCode, 0, release), id);
		ap.put(id, new AbstractAction() {
			private static final long serialVersionUID = GConstants.serialVersionUID;
			@Override
			public void actionPerformed(ActionEvent e) {actionListener.actionPerformed(e);}
		});
	}
	
	
	private Vector<GShape> copytoStack(){
		try {
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
			objectOutputStream.writeObject(this.shapes);

			ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
			ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
			return (Vector<GShape>) objectInputStream.readObject();
		} catch (IOException | ClassNotFoundException e) {e.printStackTrace();
			return null;}
	}
	public void undo() {
		try {if (!undostack.isEmpty()) {
			this.redostack.push(this.copytoStack());
			this.shapes.clear();
			this.setShapes(this.undostack.pop());}
		}
		catch (EmptyStackException e) {System.out.println("Undo Stack is empty.");}
	}
	public void redo() {
		try {
			this.undostack.push(this.copytoStack());
			this.shapes.clear();
			this.setShapes(this.redostack.pop());
		}
		catch (EmptyStackException e) {System.out.println("Redo Stack is empty.");}
	}
}
