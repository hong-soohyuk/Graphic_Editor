package frames;

import java.io.File;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import main.GConstants;

public class GFileBrowser extends JTree {
	private static final long serialVersionUID = GConstants.serialVersionUID;
	/**
	 * 
	 */
	private DefaultMutableTreeNode root;
	private DefaultTreeModel treeModel;
	
	private File fileRoot;
	public GFileBrowser() {
		super();
		this.fileRoot = new File(GConstants.EStrVal.defaultDirec.getValue());
		
		this.root = new DefaultMutableTreeNode(new FileNode(this.fileRoot));
		this.treeModel = new DefaultTreeModel(this.root);
//		this.popupMenu = new JPopupMenu();
		this.setModel(treeModel);
		this.setShowsRootHandles(true);
		
		this.addTreeSelectionListener(new TreeSelectionListener() {
			
			@Override
			public void valueChanged(TreeSelectionEvent e) {
			}
				
		});
		
		CreateChildNodes createchildNodes = new CreateChildNodes(fileRoot, root);
		new Thread(createchildNodes).start();
		
	}
	
	public class CreateChildNodes implements Runnable {
		private DefaultMutableTreeNode root;

		private File fileRoot;

		public CreateChildNodes(File fileRoot, DefaultMutableTreeNode root) {
			this.fileRoot = fileRoot;
			this.root = root;
		}

		@Override
		public void run() {createChildren(fileRoot, root);}

		private void createChildren(File fileRoot, DefaultMutableTreeNode node) {
			File[] files = fileRoot.listFiles();
			if (files == null)
				return;

			for (File file : files) {
				DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(new FileNode(file));
				node.add(childNode);
				if (file.isDirectory()) {
					createChildren(file, childNode);
				}
			}
		}

	}
	public class FileNode {
		private File file;
		public FileNode(File file) {this.file = file;}

		@Override
		public String toString() {
			String name = file.getName();
			if (name.equals("")) {return file.getAbsolutePath();}
			else {return name;}
		}
	}
	
//	public void setAssociation(GDrawingPanel drawingPanel) {this.drawingpanel = drawingPanel;}
	
}
