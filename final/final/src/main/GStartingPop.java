package main;

import java.awt.Component;
import java.util.Locale;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;



public class GStartingPop extends DefaultComboBoxModel<Locale> {
	private static final long serialVersionUID = GConstants.serialVersionUID;
	/**
	 * 
	 */
	public GStartingPop() {
		DefaultComboBoxModel<Locale> model = new DefaultComboBoxModel<Locale>();
		model.addElement(new Locale("Korean", "Korea"));
		model.addElement(new Locale("English", "US"));

		JComboBox<Locale> combo = new JComboBox<Locale>(model);
		combo.setRenderer(new LocaListCellRenderer());

		int result = JOptionPane.showConfirmDialog(null, combo, "Selected Your Language.",
				JOptionPane.OK_CANCEL_OPTION);
		if (result == JOptionPane.OK_OPTION) {
			Locale local = (Locale) combo.getSelectedItem();
//				System.out.println( local.getDisplayLanguage());
//				System.out.println(local.getLanguage());
			Locale.setDefault(local);
		} else {
			System.exit(0);
		}
	}
	
	public static class LocaListCellRenderer extends DefaultListCellRenderer{
		private static final long serialVersionUID = GConstants.serialVersionUID;
		/**
		 * 
		 */
		@Override
		public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
//			System.out.println(value);
			super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
			if (value instanceof Locale) {
				Locale local = (Locale) value;
				setText(local.getDisplayName());
			}
			return this;
		}
	}
	
}
