package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.PatientCSVData;
import view.A2Frame;
import view.PatientEditPanel;

public class PatientEditControl implements ActionListener {

	PatientEditPanel patientEditPanel;
	PatientCSVData patientCSVData;
	
	public PatientEditControl(PatientEditPanel patientEditPanel, PatientCSVData patientCSVData){
		// connect with view and model
		this.patientEditPanel = patientEditPanel;
		this.patientCSVData = patientCSVData;
		
		// add data to fields	
		JTextField t = this.patientEditPanel.getID();
		t.setText(""+(this.patientCSVData.getCurrentRow()));
		//System.out.println(this.patientCSVData.getCurrentRow());
		
		//String[] a = this.patientAddPanel.getAmbulances();
		String[] ambulances = this.patientEditPanel.getParentFrame().getAmbulanceDataModel().getAmbulanceIDs();
		Arrays.sort(ambulances);
		JComboBox<String> a = this.patientEditPanel.getAmbulance();
		a.addItem("-");
		for (int i = 0; i < ambulances.length; i++){
			a.addItem(ambulances[i]);
		}		
		
		// register the listeners
		this.patientEditPanel.getCancelButton().addActionListener(this);
		this.patientEditPanel.getSaveButton().addActionListener(this);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source == this.patientEditPanel.getCancelButton() ){
			cancelButtonAction(e);
		} else if(source == this.patientEditPanel.getSaveButton() ){
			try {
				saveButtonAction(e);
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	private void cancelButtonAction(ActionEvent e) {
		// redirect to mainPanel
		A2Frame a2Frame = patientEditPanel.getParentFrame();
		a2Frame.setTitle(a2Frame.getLabel() + ": Patients");
		patientEditPanel.setVisible(false);
		a2Frame.getPatientListPanel().setVisible(true);
		a2Frame.setContentPane(a2Frame.getPatientListPanel());
	}
	
	private void saveButtonAction(ActionEvent e) throws NumberFormatException, IOException {
		
		String id = patientEditPanel.getID().getText();
		String xPos = patientEditPanel.getXField().getText();
		String yPos = patientEditPanel.getYField().getText();
		String status = (String)patientEditPanel.getStatus().getSelectedItem();
		String ambulance = (String)patientEditPanel.getAmbulance().getSelectedItem();
		
		if (xPos.isEmpty()){
			JFrame frame = new JFrame();
        	JOptionPane.showMessageDialog(frame,"X-coordinate must be a number between 0 and 100","Error",JOptionPane.PLAIN_MESSAGE);
		}
		else if (!isInteger(xPos)){
			JFrame frame = new JFrame();
        	JOptionPane.showMessageDialog(frame,"X-coordinate must be a number between 0 and 100","Error",JOptionPane.PLAIN_MESSAGE);
		}
		else if (Integer.parseInt(xPos) < 0 || Integer.parseInt(xPos) > 100){
			JFrame frame = new JFrame();
        	JOptionPane.showMessageDialog(frame,"X-coordinate must be a number between 0 and 100","Error",JOptionPane.PLAIN_MESSAGE);
		}
		else if (yPos.isEmpty()){
			JFrame frame = new JFrame();
        	JOptionPane.showMessageDialog(frame,"Y-coordinate must be a number between 0 and 100","Error",JOptionPane.PLAIN_MESSAGE);
		}
		else if (!isInteger(yPos)){
			JFrame frame = new JFrame();
        	JOptionPane.showMessageDialog(frame,"Y-coordinate must be a number between 0 and 100","Error",JOptionPane.PLAIN_MESSAGE);
		}
		else if (Integer.parseInt(yPos) < 0 || Integer.parseInt(yPos) > 100){
			JFrame frame = new JFrame();
        	JOptionPane.showMessageDialog(frame,"Y-coordinate must be a number between 0 and 100","Error",JOptionPane.PLAIN_MESSAGE);
		}
		else{
		
			patientCSVData.updatePatient(Integer.parseInt(id), Integer.parseInt(xPos), Integer.parseInt(yPos), status, ambulance);
		
			if (!"-".equals(ambulance)){
				patientEditPanel.getParentFrame().getAmbulanceDataModel().updateAmbulancePass(ambulance, id);
			}
		
			DefaultTableModel m1 = (DefaultTableModel) patientEditPanel.getParentFrame().getAmbulanceListPanel().getAmbulanceTable().getModel();
			m1.setDataVector(patientEditPanel.getParentFrame().getAmbulanceDataModel().getAmbulanceRowData() , patientEditPanel.getParentFrame().getAmbulanceDataModel().getAmbulanceColNames());
			m1.fireTableDataChanged();
		
			DefaultTableModel m = (DefaultTableModel) patientEditPanel.getParentFrame().getPatientListPanel().getPatientTable().getModel();
			m.setDataVector(patientCSVData.getPatientRowData() , patientCSVData.getPatientColNames());
			m.fireTableDataChanged();
		
			JTextField t;
			t = patientEditPanel.getXField();
			t.setText("");
			t = patientEditPanel.getYField();
			t.setText("");
		
			// redirect to list
			A2Frame a2Frame = patientEditPanel.getParentFrame();
			a2Frame.setTitle(a2Frame.getLabel() + ": Patients");
			patientEditPanel.setVisible(false);
			a2Frame.getPatientListPanel().repaint();
			a2Frame.getPatientListPanel().setVisible(true);
		
			a2Frame.setContentPane(a2Frame.getPatientListPanel());
		}
	}
	
	public boolean isInteger(String input){
		try {
			Integer.parseInt(input);
		    return true;
		}
		catch(Exception e){
		    return false;
		}
	}
}
