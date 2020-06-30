//package shape;
//
//import java.awt.Graphics2D;
//import java.awt.Rectangle;
//import java.awt.image.BufferedImage;
//import java.awt.image.ColorModel;
//import java.awt.image.WritableRaster;
//import java.io.File;
//import java.io.IOException;
//
//import javax.imageio.ImageIO;
//
//import main.GConstants;
//
//public class GImageRectangle extends GRectangle{
//	private static final long serialVersionUID = GConstants.serialVersionUID;
//
//	// Attribute
//	private BufferedImage image;
//	
//	// Constructor
//	public GImageRectangle(File imageFile) {
//		// Set Attribute
//		this.eDrawingStyle = EDrawingStyle.e2Points;
//		try {this.image = ImageIO.read(imageFile);} catch (IOException e) {e.printStackTrace();}
//		
//		// Create Component
//		this.shape = new Rectangle();
//		
//		// Set Rectangle
//		this.setOrigin(0, 0);
//		this.setPoint(this.image.getWidth(), this.image.getHeight());
//		
//	}
//	@Override
//	public void setOrigin(int x, int y) {
//		Rectangle rec = (Rectangle) this.shape;
//		rec.setLocation(x, y);
//		rec.setSize(0, 0);
//	}
//	@Override
//	public void setPoint(int x, int y) {
//		Rectangle rec = (Rectangle) this.shape;
//		int w = x - rec.x;
//		int h = y - rec.y;
//		rec.setSize(w, h);
//	}
//	@Override
//	public void draw(Graphics2D graphics) {
//		Rectangle bound = this.shape.getBounds();
//		graphics.drawImage(this.image, bound.x, bound.y, bound.width, bound.height, null);
//		
//		super.draw(graphics);
//	}
//	@Override
//	public GShape clone() {
//		ColorModel cm = this.image.getColorModel();
//		boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
//		WritableRaster raster = this.image.copyData(null);
//		return new BufferedImage(cm, raster, isAlphaPremultiplied,null);
//	}
//}
