package main;
import java.awt.Cursor;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.EnumSet;
import java.util.ResourceBundle;

import javax.swing.KeyStroke;
import java.awt.Color;
import menuSub.GLSubmenu;
import menuSub.GSubMenu;
import menus.GEditMenu;
import menus.GFileMenu;
import menus.GMenu;
import menus.GPropertyMenu;
import shape.GEllipse;
import shape.GTriangle;
import shape.GLine;
import shape.GPen;
import shape.GPolygon;
import shape.GRectangle;
import shape.GSelectionRec;
import shape.GRubber;
import shape.GShape;

public class GConstants {
	public static final long serialVersionUID = 1L;
	private static ResourceBundle rb = 
			ResourceBundle.getBundle("tips/java/cfg/resource_bundle");
	public void resetRb() {rb = ResourceBundle.getBundle("tips/java/cfg/resource_bundle");}
	public GConstants() { }
	public enum EMainFrame{
		eWidth(1440),
		eHeight(1080);
		private int value;		
		private EMainFrame(int value) {this.value = value;}	
		public int getValue() {return this.value;}
	}
	public enum EStrVal{
		mainTitle(rb.getString("title")), checSaveTitle(rb.getString("SaveCheckTitle")),checSaveMessage(rb.getString("SaveCheckMessage")),
		defaultDirec("./"),
		popupLine(rb.getString("popupLine")), popupFill(rb.getString("popupFill"));
		
		;
		private String title;
		private EStrVal(String title) {this.title = title;}
		public String getValue() {return this.title;}
	}
	public enum EMenubar {
		eFile(new GFileMenu(rb.getString("File")), EnumSet.range(EMenuItem.eNew, EMenuItem.ePrint)),
		eEdit(new GEditMenu(rb.getString("Edit")), EnumSet.range(EMenuItem.eCut, EMenuItem.eReDo)),
		eProperties(new GPropertyMenu(rb.getString("Properties")), EnumSet.of(EMenuItem.eClear));
		private GMenu menu;
		private EnumSet<EMenuItem> myEnum;
		private EMenubar(GMenu menu, EnumSet<EMenuItem> myEnum) {
			this.menu = menu;
			this.myEnum = myEnum;
		}		
		public GMenu getMenu() {return this.menu;}
		public EnumSet<EMenuItem> getMyEnum() {return this.myEnum;}
	}
	public enum ESubMenu {
		elangSub(new GLSubmenu(rb.getString("Changeshape'scolor")), EnumSet.range(EMenuItem.eLineColour, EMenuItem.eFillColour));
		private GSubMenu menu;
		private EnumSet<EMenuItem> myEnum;
		private ESubMenu(GSubMenu menu, EnumSet<EMenuItem> myEnum) {
			this.menu = menu;
			this.myEnum = myEnum;
		}
		public GSubMenu getMenu() {return this.menu;}
		public EnumSet<EMenuItem> getMyEnum() {return this.myEnum;}
	}

	public enum EMenuItem{
		eNew(rb.getString("NewFile"),"nnew",KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK)),eOpen(rb.getString("OpenFile"),"open",KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK)),
		eExit(rb.getString("Exit"),"exit",KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_DOWN_MASK)),eSave(rb.getString("Save"),"save",KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK)),
		eImage(rb.getString("OpenImage"),"imageOpen",KeyStroke.getKeyStroke(KeyEvent.VK_I,InputEvent.CTRL_DOWN_MASK)) , eSaveAs(rb.getString("SaveAs"),"saveAs",KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK)),
		ePrint(rb.getString("Print"),"print",KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_DOWN_MASK)),
		
		eCut(rb.getString("Cut"),"cut",KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK)),
		eCopy(rb.getString("Copy"),"copy",KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK)),
		ePaste(rb.getString("Paste"),"paste",KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK)),
//		eGroup(rb.getString("Group"),"group",KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_DOWN_MASK)),
//		eUnGroup(rb.getString("UnGroup"),"ungroup",null),
		eBringF(rb.getString("bringForward"),"bringForward", KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_DOWN_MASK)),
		eBringB(rb.getString("bringBackward"),"bringBackward", KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.CTRL_DOWN_MASK)),
		eUnDo(rb.getString("UnDo"),"undo",KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_DOWN_MASK)),
		eReDo(rb.getString("Redo"),"redo",KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK)),
		
		eLineColour(rb.getString("LineColor"),"setLineColor",null), eFillColour(rb.getString("FillColor"),"setFillColor",null),
		eClear(rb.getString("clearall"),"clearAll", null);
		
		private String title;
		private String actionCommand;
		private KeyStroke keystroke;
		private EMenuItem(String title,String actionCommand, KeyStroke keystroke) {
			this.title = title;
			this.actionCommand = actionCommand;
			this.keystroke = keystroke;
		}
		public String getTitle() {return this.title;}
		public String getActionCommand() {return this.actionCommand;}
		public KeyStroke getKeystr() {return this.keystroke;}
	}
	
	public enum EToolbar {
		eSelectionRec("resource/icon/selectionRec.png", "resource/icon/selectionRecSe.png",new GSelectionRec()),
		eRectangle("resource/icon/rec.png", "resource/icon/recSe.png",new GRectangle()),
		eEllipse("resource/icon/ellipse.png", "resource/icon/ellipseSe.png",new GEllipse()),
		eTriangle("resource/icon/triangle.png", "resource/icon/triangleSe.png",new GTriangle()),
		eLine("resource/icon/line.png", "resource/icon/lineSe.png",new GLine()),
		ePen("resource/icon/pen.png", "resource/icon/penSe.png",new GPen()),
//		eTextBox("resource/icon/text.png", "resource/icon/textSe.png",new GTextBox()),
		eEraser("resource/icon/rubber.png","resource/icon/rubberSe.png", new GRubber()),
		ePolygon("resource/icon/polygon.png", "resource/icon/polygonSe.png",new GPolygon());
		
		private String icon;
		private String selectedIcon;
		private GShape tool;
		
		private EToolbar(String title, String selectedIcon , GShape tool) {
			this.icon = title;
			this.selectedIcon = selectedIcon;
			this.tool = tool;
		}
		public String getIcon() {return this.icon;}
		public String getSeIcon() {return this.selectedIcon;}
		public GShape getTool() {return this.tool;}
//		public int getKeyEv() {return this.keyEvent;}
	}
	public enum ESubTool{
		eLineColour("Resource/icon/palette.png","setLineColor",0),eFillColour("Resource/icon/bucket.png","setFillColor",0);
		private String icon;
		private String actionCommand;
		private int keyEvent;

		private ESubTool(String icon, String method, int keyEvent) {
			
		}
		public String getIco() {return this.icon;}
		public String getActionCommand() {return this.actionCommand;}
		public int getKeyEv() {return this.keyEvent;}
	}
		
	public enum ECursor {
		eDefault(new Cursor(Cursor.DEFAULT_CURSOR)),
		eMove(new Cursor(Cursor.HAND_CURSOR)),
		eRotate(new Cursor(Cursor.WAIT_CURSOR)),
		eEE(new Cursor(Cursor.E_RESIZE_CURSOR)),
		eWW(new Cursor(Cursor.W_RESIZE_CURSOR)),
		eNN(new Cursor(Cursor.N_RESIZE_CURSOR)),
		eSS(new Cursor(Cursor.S_RESIZE_CURSOR)),
		eNE(new Cursor(Cursor.NE_RESIZE_CURSOR)),
		eSE(new Cursor(Cursor.SE_RESIZE_CURSOR)),		
		eNW(new Cursor(Cursor.NW_RESIZE_CURSOR)),
		eSW(new Cursor(Cursor.SW_RESIZE_CURSOR));
		
		private Cursor cursor;
		
		private ECursor(Cursor cursor) {this.cursor = cursor;}		
		public Cursor getCursor() {return this.cursor;}
	}
	public enum ESwatched{
		GREEN("resource/swatch/00cc66.png", new Color(0x00, 0xcc, 0x66)),YELLOW("resource/swatch/ffcc00.png", new Color(0xff, 0xcc, 0x00)),
		RED("resource/swatch/ff3333.png", new Color(0xff, 0x33, 0x33)),VIOLET("resource/swatch/6666ff.png", new Color(0x66, 0x66, 0xff)),
		VOOID("resource/swatch/voidColor.png", new Color(0,0,0,0)),BLUE("resource/swatch/3366ff.png", new Color(0x33, 0x66, 0xff)),
		CORAL("resource/swatch/ff6666.png",new Color(0xff, 0x66, 0x66)),PURPLE("resource/swatch/4f3961.png",new Color(0x4f, 0x39, 0x61));
		
		private String swatchSr;
		private Color swatColor;
		private ESwatched(String swatchSr, Color swatColor) {
			this.swatchSr=swatchSr;
			this.swatColor = swatColor;
		}
		public String getSwatSr() {return this.swatchSr;}
		public Color getSwatColo() {return this.swatColor;}
	}

}
