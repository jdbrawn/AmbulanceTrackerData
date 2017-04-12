package view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class AmbulanceListPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private A2Frame parentFrame;
	
	private JTable ambulanceTable; 
	private JScrollPane scrollPane; // to host ambulanceTable
	private JButton backButton;
	private JButton addNewButton;
	private JPanel bottom;
	
	public AmbulanceListPanel(A2Frame parent){
		setParentFrame(parent);
		
		// create the components
		ambulanceTable = new JTable();
		scrollPane = new JScrollPane(ambulanceTable);
		backButton = new JButton("Back");
		addNewButton = new JButton("Add New");
		bottom = new JPanel(new FlowLayout());
		
		// set the layout
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));//  for panel to be top to bottom
		
		// set the alignment
		backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		addNewButton.setAlignmentX(Component.CENTER_ALIGNMENT);

		// set the size
		ambulanceTable.setPreferredScrollableViewportSize(new Dimension(200, 100));
		ambulanceTable.setFillsViewportHeight(true);
		
		// add the components
		add(scrollPane);
		bottom.add(backButton);
		bottom.add(addNewButton);
		add(bottom);
		
	}
	
	public JTable getAmbulanceTable() {
		return ambulanceTable;
	}

	public void setAmbulanceTable(Object[][] data, String[] colnames) {		
		this.ambulanceTable.setModel(new DefaultTableModel(data,colnames));
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}

	public JButton getBackButton() {
		return backButton;
	}

	public void setBackButton(JButton backButton) {
		this.backButton = backButton;
	}

	
	public JButton getAddNewButton() {
		return addNewButton;
	}

	public void setAddNewButton(JButton addNewButton) {
		this.addNewButton = addNewButton;
	}

	public A2Frame getParentFrame() {
		return parentFrame;
	}

	public void setParentFrame(A2Frame parent) {
		this.parentFrame = parent;
	}
}
