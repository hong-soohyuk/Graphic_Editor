package menus;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import file.GFile;
import frames.GDrawingPanel;
import main.GConstants;
import main.Pair;

public class GFileMenu extends GMenu {
	private static final long serialVersionUID = GConstants.serialVersionUID;


	// association
	public void association(GDrawingPanel drawingpanel){
		this.drawingpanel = drawingpanel;
	}

	//working variables
	private File directory;
	private File file;

	public GFileMenu(String name) {
		super(name);
		this.directory = new File(GConstants.EStrVal.defaultDirec.getValue());
		this.file = null;
	}	
	public void imageOpen() {
		System.out.println("unavailable");
	}
//		// Image Select
//		JFileChooser fileChooser = new JFileChooser(this.directory);
//		//
////		FileNameExtensionFilter filter = new FileNameExtensionFilter("", "png");
////		fileChooser.setFileFilter(filter);
//		//
//		int returnValue = fileChooser.showOpenDialog(this.drawingpanel);
//		
//		if(returnValue == JFileChooser.APPROVE_OPTION) {
//			this.drawingpanel.addImage(fileChooser.getSelectedFile());
//		}
//	}
	
	public boolean checkSave() {
		if (this.drawingpanel.isUpdated()) {
			int reply = JOptionPane.showConfirmDialog(this.drawingpanel,GConstants.EStrVal.checSaveMessage.getValue(), 
					GConstants.EStrVal.checSaveTitle.getValue(),JOptionPane.YES_NO_CANCEL_OPTION);
			
			if (reply == JOptionPane.OK_OPTION) {
				this.save();
				return true;}
			else if(reply == JOptionPane.NO_OPTION) {return true;}
			else if(reply == JOptionPane.CANCEL_OPTION) {return false;}
		}
		return true;
	}
	public void nnew() {
		if(this.checkSave()) {
		this.drawingpanel.clearShapes();
		this.file = null;
		}
	}
	public void open() {
		if (this.checkSave()) {
			JFileChooser fileChooser = new JFileChooser(this.directory);
			
			fileChooser.setDialogType(JFileChooser.OPEN_DIALOG);
			fileChooser.setFileFilter(new FileNameExtensionFilter("niceguy", "niceguyEddie"));
			
			int returnValue = fileChooser.showOpenDialog(this.drawingpanel);
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				this.drawingpanel.clearShapes();
				this.directory = fileChooser.getCurrentDirectory();
				this.file = fileChooser.getSelectedFile();
				GFile gFile = new GFile();
				Pair pair = (Pair) gFile.readObject(this.file);
				
				this.drawingpanel.setShapes(pair.getFirst());
				this.drawingpanel.setText(pair.getSecond());
			}
		}
	}
	public void save() {
		if(this.file==null) {this.saveAs();}
		else {
			GFile gFile = new GFile();
			gFile.saveObject(drawingpanel.getShapes(),drawingpanel.getText(),this.file);
			this.drawingpanel.setUpdate(false);
		}
	}

	public void saveAs() {
		JFileChooser fileChooser = new JFileChooser(this.directory);
		fileChooser.setDialogType(JFileChooser.SAVE_DIALOG);
		fileChooser.setSelectedFile(new File("_your file name_.niceguyEddie"));
		fileChooser.setFileFilter(new FileNameExtensionFilter("niceguy", "niceguyEddie"));
		
		int returnValue = fileChooser.showSaveDialog(this.drawingpanel);
		if(returnValue == JFileChooser.APPROVE_OPTION) {
			this.directory = fileChooser.getCurrentDirectory();
			this.file = fileChooser.getSelectedFile();
			this.save();
		}
	}
	public void print() {
		this.drawingpanel.print();
	}
	
	public void exit() {if(this.checkSave())System.exit(0);}
}
