package frames;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

import javax.swing.JPanel;

import Main.GConstant;
import Main.GConstant.EToolbar;
import shape.GShape;
import shape.GShape.EDrawingStyle;

public class GDrawingPanel extends JPanel {
	// attributes
	private static final long serialVersionUID = GConstant.serialVersionUID;
	/**
	 * 
	 */
	private enum EDrawingState {
		eIdle, eDrawing;
	}
	private EDrawingState eDrawingstate;
	private EDrawingStyle eDrawingstyle;
	private GShape currentTool;
	
	private MouseHandlerrrrr handle;
	private Vector<GShape> shapes;
	private GShape currentshape;

	public GDrawingPanel() {
		// attribute
		this.setBackground(Color.white);
		this.eDrawingstate = EDrawingState.eIdle;
		
		// components
		this.handle = new MouseHandlerrrrr();
		this.addMouseListener(this.handle);
		this.addMouseMotionListener(this.handle);

		this.shapes = new Vector<GShape>();
		}
	
	public void setCurrentTool(EToolbar eToolbar) {
		this.currentTool = eToolbar.getTool();
		this.eDrawingstyle = this.currentTool.geteDrawingStyle();
		
	}

	public void initialize() {
		// set associations
		// set associative attributes
		// initialize components
	}
	
	public void paint(Graphics graphics) {
		super.paint(graphics);
		for (GShape shape : this.shapes) {
			shape.draw(graphics);
		}
		
	}
	void initDrawing(int x, int y) {
		this.currentshape = this.currentTool.clone();		
		this.currentshape.setOrigin(x, y);
	}

	void keepDrawing(int x, int y) {
		Graphics g = this.getGraphics();
		g.setColor(Color.black);
		g.setXORMode(getBackground());
		// 처음 들어오는 드로우는 그림을 그리는 용도. 지우기
		this.currentshape.draw(g);
		// 마우스 좌표를 옮긴다.
		this.currentshape.setPoint(x, y);
		// 밑의 드로우는 좌표를 옮기는 용도.
		this.currentshape.draw(g);
	}
	
	void addPoint(int x, int y){
		this.currentshape.addPoint(x, y);
	}
	void finishDrawing(int x, int y) {
		this.shapes.add(this.currentshape);
	}

	// inner class
	class MouseHandlerrrrr implements MouseMotionListener, MouseListener {
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
			if (eDrawingstyle == EDrawingStyle.eNPoint 
					&& eDrawingstate ==EDrawingState.eIdle) {
				int x = event.getX();
				int y = event.getY();
				initDrawing(x, y);
				eDrawingstate = EDrawingState.eDrawing;
			}
			if (eDrawingstyle == EDrawingStyle.eNPoint 
					&& eDrawingstate ==EDrawingState.eDrawing) {
				int x = event.getX();
				int y = event.getY();
				addPoint(x, y);
			}
		}
		private void mouse2Clicked(MouseEvent event) {
			if (eDrawingstyle == EDrawingStyle.eNPoint 
					&& eDrawingstate==EDrawingState.eDrawing) {
				int x = event.getX();
				int y = event.getY();
				finishDrawing(x, y);
				eDrawingstate = EDrawingState.eIdle;
			}
		}
		@Override
		public void mouseMoved(MouseEvent event) {
			if (eDrawingstyle == EDrawingStyle.eNPoint 
					&& eDrawingstate==EDrawingState.eDrawing) {
				int x = event.getX();
				int y = event.getY();
				keepDrawing(x, y);
			}
		}
		
		// 2 points drawing
		@Override
		public void mousePressed(MouseEvent event) {
//			if (eDrawingStyle == EDrawingStyle.eNPoint && eDrawingstate ==EDrawingState.eIdle) {
//				int x = event.getX();
//				int y = event.getY();
//				initDrawing(x, y);
//				eDrawingstate = EDrawingState.eDrawing;
//			}
			if (eDrawingstyle == EDrawingStyle.eNPoint 
					&& eDrawingstate ==EDrawingState.eDrawing) {
				int x = event.getX();
				int y = event.getY();
				addPoint(x, y);
			}
			if (eDrawingstyle == EDrawingStyle.e2Points 
					&& eDrawingstate ==EDrawingState.eIdle) {
				int x = event.getX();
				int y = event.getY();
				initDrawing(x, y);
				eDrawingstate = EDrawingState.eDrawing;
			}
		}
		@Override
		public void mouseDragged(MouseEvent event) {
//			if (eDrawingStyle == EDrawingStyle.eNPoint && eDrawingstate==EDrawingState.eDrawing) {
//				int x = event.getX();
//				int y = event.getY();
//				keepDrawing(x, y);
//			}
			if (eDrawingstyle == EDrawingStyle.e2Points 
					&& eDrawingstate==EDrawingState.eDrawing) {
				int x = event.getX();
				int y = event.getY();
				keepDrawing(x, y);
			}
		}
		@Override
		public void mouseReleased(MouseEvent event) {
			if (eDrawingstyle == EDrawingStyle.eNPoint 
					&& eDrawingstate ==EDrawingState.eDrawing) {
				int x = event.getX();
				int y = event.getY();
				addPoint(x, y);
			}
			if (eDrawingstyle == EDrawingStyle.e2Points 
					&& eDrawingstate==EDrawingState.eDrawing) {
				int x = event.getX();
				int y = event.getY();
				finishDrawing(x, y);
				eDrawingstate = EDrawingState.eIdle;
			}
		}
		
		@Override
		public void mouseEntered(MouseEvent event) {			
		}
		@Override
		public void mouseExited(MouseEvent event) {			
		}
	}
}
