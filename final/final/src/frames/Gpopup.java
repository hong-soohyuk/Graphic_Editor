package frames;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import main.GConstants;
import shape.GShape;

public class Gpopup extends JPopupMenu {
	private static final long serialVersionUID = GConstants.serialVersionUID;
	/**
	 * 
	 */
	private JMenu i1;
	private JMenu i2;
	public Gpopup(Vector<GShape> seletedshape) {
		super();
		this.i1 = new JMenu(GConstants.EStrVal.popupLine.getValue());
		this.i1.setIcon(new ImageIcon("resource/icon/palette.png"));

		this.i1.getPopupMenu().setLayout(new GridLayout(2, 4));
		for (GConstants.ESwatched swatch : GConstants.ESwatched.values()) {
			JMenuItem menuItem = new JMenuItem(new ImageIcon(swatch.getSwatSr()));
			menuItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					for (GShape shape : seletedshape) 
					{shape.setLineColor(swatch.getSwatColo());}
				}
			});
			this.i1.add(menuItem);
		}
		this.i2 = new JMenu(GConstants.EStrVal.popupFill.getValue());
		this.i2.setIcon(new ImageIcon("resource/icon/bucket.png"));
		this.i2.getPopupMenu().setLayout(new GridLayout(2, 4));
		for (GConstants.ESwatched swatch : GConstants.ESwatched.values()) {	
			JMenuItem menuItem = new JMenuItem(new ImageIcon(swatch.getSwatSr()));
			menuItem.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					for (GShape shape : seletedshape) 
					{shape.setFillColor(swatch.getSwatColo());}					
				}
			});
//			menuItem.addActionListener(e -> shape.setFillColor(swatch.getSwatColo()));
			this.i2.add(menuItem);
		}
		this.add(this.i1);
		this.add(this.i2);
	}
}
