package Main;

import shape.GLine;
import shape.GOval;
import shape.GPolygon;
import shape.GRectangle;
import shape.GShape;


public class GConstant {
	public static final long serialVersionUID = 1L;
	
	public GConstant() {
		
	}
	public enum EMainFrame{
		eWidth(600), eHeight(800);
		private int value;
		//assigning value "title"
		private EMainFrame(int value) {this.value = value;}
		public int getValue() {return this.value;}
	}
	public enum EToolbar{
		eRectangle("네모", new GRectangle()), 
		eOval("타원", new GOval()),
		eLine("선", new GLine()),
		ePolygon("다각형", new GPolygon());
		
		private String title;
		private GShape tool;
		
		private EToolbar(String title,GShape shape) {
			this.title = title;
			this.tool = shape;
		}
		
		public String getTitle() {
			return this.title;
		}
		
		public GShape getTool() {
			return this.tool;
		}
		
	}
	
	public enum EMenubar{
		eFile("파일", "menus.GFileMenu"), eEdit("편집", "menus.GEditMenu")
		, eProperties("속성", "menus.GColorMenu");
		
		private String title;
		private String className;
		
		private EMenubar(String title, String className) {
			this.title = title;
			this.className = className;
		}

		public String getTitle() {
			return this.title;
		}
		public String getClassName() {
			return this.className;
		}
	}
	
	
	/////////MenuBar/////////
	public enum EFilemenu{
		eNew("새로운 파일"), eOpen("열기"), eClose("닫기"),
		eSave("저장하기"), eSaveAs("다른 이름으로 저장"), ePrint("인쇄");
		private String title;
		
		private EFilemenu(String title) {
			this.title = title;
		}
		public String getTitle() {
			return this.title;
		}
	}
	public enum	EEditmenu{
		eCut("잘라내기"), ePaste("붙혀넣기"), eCopy("복사하기"),
		eGroup("그룹"), eUnGroup("그룹 해제"), eDo("실행"), eUnDo("실행 취소");
		private String title;
		
		private EEditmenu(String title) {
			this.title = title;
		}
		public String getTitle() {
			return this.title;
		}
	}
	public enum	EColourmenu{
		eLineColour("선 색깔 변경"), eFillColour("색 채우기");
		private String title;
		
		private EColourmenu(String title) {
			this.title = title;
		}
		public String getTitle() {
			return this.title;
		}
	}
	
}
