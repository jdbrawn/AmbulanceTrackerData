package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.ListSelectionListener;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;

import model.PatientCSVData;
import view.A2Frame;
import view.PatientListPanel;

public class PatientListControl implements ActionListener {

	PatientListPanel patientListPanel;
	PatientCSVData patientCSVData;
	
	public PatientListControl(PatientListPanel patientListPanel, PatientCSVData patientCSVData){
		// connect with view and model
		this.patientListPanel = patientListPanel;
		this.patientCSVData = patientCSVData;
				
		// add the data to table
		DefaultTableModel m = (DefaultTableModel) this.patientListPanel.getPatientTable().getModel();
		m.setDataVector(this.patientCSVData.getPatientRowData() , this.patientCSVData.getPatientColNames());
		
		// register the listeners
		this.patientListPanel.getBackButton().addActionListener(this);
		this.patientListPanel.getAddNewButton().addActionListener(this);
		this.patientListPanel.getPatientTable().getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	        	
	        	int row1 = 0;
	        	if (patientListPanel.getPatientTable().getSelectedRow() >= 0){
		        	row1 = patientListPanel.getPatientTable().getSelectedRow();
	        	} 	
	        	
	        	JTextField t = patientListPanel.getParentFrame().getPatientEditPanel().getID();
	    		t.setText(""+(row1+1));
	    		
	    		String xy = (String)patientCSVData.getPatientRowData()[row1][1];
	    		String[] xySplit = xy.split(",");
	    		xySplit[1].replaceAll("\\s","");
	    		
	    		JTextField t1 = patientListPanel.getParentFrame().getPatientEditPanel().getXField();
	    		t1.setText(xySplit[0].replace("(", ""));
	    		JTextField t2 = patientListPanel.getParentFrame().getPatientEditPanel().getYField();
	    		t2.setText(xySplit[1].replace(")", ""));
	    		
	    		JComboBox<String> c = patientListPanel.getParentFrame().getPatientEditPanel().getStatus();
	    		c.setSelectedItem(patientCSVData.getPatientRowData()[row1][2]);
	    		
	    		JComboBox<String> c2 = patientListPanel.getParentFrame().getPatientEditPanel().getAmbulance();
	    		c2.setSelectedItem(patientCSVData.getPatientRowData()[row1][3]);
	    		
	    		A2Frame a2Frame = patientListPanel.getParentFrame();
	    		a2Frame.setTitle(a2Frame.getLabel() + ": Edit Patient");
	    		patientListPanel.setVisible(false);
	    		a2Frame.getPatientEditPanel().setVisible(true);
	    		a2Frame.setContentPane(a2Frame.getPatientEditPanel());
	        }
	    });
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source == this.patientListPanel.getBackButton() ){
			backButtonAction(e);
		} else if(source == this.patientListPanel.getAddNewButton() ){
			addNewButtonAction(e);
		}
	}
	
	private void backButtonAction(ActionEvent e) {
		// redirect to mainPanel
		A2Frame a2Frame = patientListPanel.getParentFrame();
		a2Frame.setTitle(a2Frame.getLabel());
		a2Frame.getMainPanel().setVisible(true);
		a2Frame.setContentPane(a2Frame.getMainPanel());
	}
	
	private void addNewButtonAction(ActionEvent e) {
		
		A2Frame a2Frame = patientListPanel.getParentFrame();
		a2Frame.setTitle(a2Frame.getLabel() + ": Add Patient");
		patientListPanel.setVisible(false);
		a2Frame.getPatientAddPanel().setVisible(true);
		a2Frame.setContentPane(a2Frame.getPatientAddPanel());

	}
}
