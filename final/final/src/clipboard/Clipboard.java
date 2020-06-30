package clipboard;

import java.util.Vector;
import shape.GShape;

public class Clipboard {
	
	private Vector<GShape> cutShapes;
	
	public Clipboard() {
		this.cutShapes = new Vector<GShape>();
	}
	public void setContents(Vector<GShape> shapes) {
		this.cutShapes.clear();
		this.cutShapes.addAll(shapes);
	}
	
	public Vector<GShape> getContents() {
		Vector<GShape> pasteshapes = new Vector<GShape>();
//		for(GShape shape: this.cutShapes) {
//			GShape clonedShape = shape.clone();
//			clonedshapes.add(clonedShape);
//		}
		for (int i = 0; i < this.cutShapes.size(); i++) {
			GShape pasteShape = this.cutShapes.get(i);
			pasteshapes.add(pasteShape);
		}
		return pasteshapes;
	}
	
	
}
