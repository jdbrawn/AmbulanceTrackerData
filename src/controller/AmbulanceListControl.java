package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import model.AmbulanceCSVData;
import view.A2Frame;
import view.AmbulanceListPanel;

public class AmbulanceListControl implements ActionListener {

	AmbulanceListPanel ambulanceListPanel;
	AmbulanceCSVData ambulanceCSVData;
	
	public AmbulanceListControl(AmbulanceListPanel ambulanceListPanel, AmbulanceCSVData ambulanceCSVData){
		// connect with view and model
		this.ambulanceListPanel = ambulanceListPanel;
		this.ambulanceCSVData = ambulanceCSVData;
				
		// add the data to table
		DefaultTableModel m = (DefaultTableModel) this.ambulanceListPanel.getAmbulanceTable().getModel();
		m.setDataVector(this.ambulanceCSVData.getAmbulanceRowData() , this.ambulanceCSVData.getAmbulanceColNames());
		
		// register the listener
		this.ambulanceListPanel.getBackButton().addActionListener(this);
		this.ambulanceListPanel.getAddNewButton().addActionListener(this);
		
		this.ambulanceListPanel.getAmbulanceTable().getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	        	
	        	int row = 0;
	        	if (ambulanceListPanel.getAmbulanceTable().getSelectedRow() >= 0){
		        	row = ambulanceListPanel.getAmbulanceTable().getSelectedRow();
		        	ambulanceCSVData.setCurrentRow(row);
	        	}
	        	
	        	JTextField t = ambulanceListPanel.getParentFrame().getAmbulanceAddPanel().getID();
	        	t.setText((String)ambulanceCSVData.getAmbulanceRowData()[row][0]);
	        	
	        	String xy = (String)ambulanceCSVData.getAmbulanceRowData()[row][1];
	    		String[] xySplit = xy.split(",");
	    		xySplit[1].replaceAll("\\s","");
	    		
	    		JTextField t1 = ambulanceListPanel.getParentFrame().getAmbulanceAddPanel().getXField();
	    		t1.setText(xySplit[0].replace("(", ""));
	    		JTextField t2 = ambulanceListPanel.getParentFrame().getAmbulanceAddPanel().getYField();
	    		t2.setText(xySplit[1].replace(")", ""));
	    		
	    		JComboBox<String> c = ambulanceListPanel.getParentFrame().getAmbulanceAddPanel().getStatus();
	    		c.setSelectedItem(ambulanceCSVData.getAmbulanceRowData()[row][2]);
	    		
	    		JComboBox<String> c2 = ambulanceListPanel.getParentFrame().getAmbulanceAddPanel().getPatient();
	    		c2.setSelectedItem(ambulanceCSVData.getAmbulanceRowData()[row][3]);
	        	
	        	A2Frame a2Frame = ambulanceListPanel.getParentFrame();
	    		a2Frame.setTitle(a2Frame.getLabel() + ": Edit Ambulance");
	    		ambulanceListPanel.setVisible(false);
	    		a2Frame.getAmbulanceAddPanel().setVisible(true);
	    		a2Frame.setContentPane(a2Frame.getAmbulanceAddPanel());
	        	
	        }
		});
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source == this.ambulanceListPanel.getBackButton() ){
			backButtonAction(e);
		} else if(source == this.ambulanceListPanel.getAddNewButton() ){
			addNewButtonAction(e);
		}
	}
	
	private void backButtonAction(ActionEvent e) {
		// redirect to mainPanel
		A2Frame a2Frame = ambulanceListPanel.getParentFrame();
		a2Frame.setTitle(a2Frame.getLabel());
		a2Frame.getMainPanel().setVisible(true);
		a2Frame.setContentPane(a2Frame.getMainPanel());
	}
	
	private void addNewButtonAction(ActionEvent e) {
		ambulanceCSVData.setCurrentRow(ambulanceCSVData.getAmbulanceRowData().length);
		
		JTextField t = ambulanceListPanel.getParentFrame().getAmbulanceAddPanel().getID();
    	t.setText("");
    	
    	JTextField t1 = ambulanceListPanel.getParentFrame().getAmbulanceAddPanel().getXField();
    	t1.setText("");
    	JTextField t2 = ambulanceListPanel.getParentFrame().getAmbulanceAddPanel().getYField();
    	t2.setText("");
		
		A2Frame a2Frame = ambulanceListPanel.getParentFrame();
		a2Frame.setTitle(a2Frame.getLabel() + ": Add Ambulance");
		ambulanceListPanel.setVisible(false);
		a2Frame.getAmbulanceAddPanel().setVisible(true);
		a2Frame.setContentPane(a2Frame.getAmbulanceAddPanel());

	}
	

}
