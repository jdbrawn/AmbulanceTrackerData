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
import view.PatientAddPanel;


public class PatientAddControl implements ActionListener {

	PatientAddPanel patientAddPanel;
	PatientCSVData patientCSVData;
	
	public PatientAddControl(PatientAddPanel patientAddPanel, PatientCSVData patientCSVData){
		// connect with view and model
		this.patientAddPanel = patientAddPanel;
		this.patientCSVData = patientCSVData;
		
		// add data to fields	
		JTextField t = this.patientAddPanel.getID();
		t.setText(""+(this.patientCSVData.getCurrentID()+1));
		//System.out.println(this.patientCSVData.getCurrentID());
		
		//String[] a = this.patientAddPanel.getAmbulances();
		String[] ambulances = this.patientAddPanel.getParentFrame().getAmbulanceDataModel().getAmbulanceIDs();
		Arrays.sort(ambulances);
		JComboBox<String> a = this.patientAddPanel.getAmbulance();
		a.addItem("-");
		for (int i = 0; i < ambulances.length; i++){
			a.addItem(ambulances[i]);
		}
		//a = new JComboBox<String>(this.patientAddPanel.getParentFrame().getAmbulanceDataModel().getAmbulanceIDs());
		
		
		// register the listeners
		this.patientAddPanel.getCancelButton().addActionListener(this);
		this.patientAddPanel.getSaveButton().addActionListener(this);
		//this.patientAddPanel.getXField().addActionListener(this);
		//this.patientAddPanel.getYField().addActionListener(this);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source == this.patientAddPanel.getCancelButton() ){
			cancelButtonAction(e);
		} else if(source == this.patientAddPanel.getSaveButton() ){
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
		A2Frame a2Frame = patientAddPanel.getParentFrame();
		a2Frame.setTitle(a2Frame.getLabel() + ": Patients");
		patientAddPanel.setVisible(false);
		a2Frame.getPatientListPanel().setVisible(true);
		a2Frame.setContentPane(a2Frame.getPatientListPanel());
	}
	
	private void saveButtonAction(ActionEvent e) throws NumberFormatException, IOException {
		
		String id = patientAddPanel.getID().getText();
		String xPos = patientAddPanel.getXField().getText();
		String yPos = patientAddPanel.getYField().getText();
		String status = (String)patientAddPanel.getStatus().getSelectedItem();
		String ambulance = (String)patientAddPanel.getAmbulance().getSelectedItem();
		
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
		
			patientCSVData.addPatient(Integer.parseInt(id), Integer.parseInt(xPos), Integer.parseInt(yPos), status, ambulance);
		
			if (!"-".equals(ambulance)){
				patientAddPanel.getParentFrame().getAmbulanceDataModel().updateAmbulancePass(ambulance, id);
			}
		
		
			DefaultTableModel m1 = (DefaultTableModel) patientAddPanel.getParentFrame().getAmbulanceListPanel().getAmbulanceTable().getModel();
			m1.setDataVector(patientAddPanel.getParentFrame().getAmbulanceDataModel().getAmbulanceRowData() , patientAddPanel.getParentFrame().getAmbulanceDataModel().getAmbulanceColNames());
			m1.fireTableDataChanged();
		
			DefaultTableModel m = (DefaultTableModel) patientAddPanel.getParentFrame().getPatientListPanel().getPatientTable().getModel();
			m.setDataVector(patientCSVData.getPatientRowData() , patientCSVData.getPatientColNames());
			m.fireTableDataChanged();
		
			JTextField t = patientAddPanel.getID();
			t.setText(""+(patientCSVData.getCurrentID()+1));
		
			t = patientAddPanel.getXField();
			t.setText("");
			t = patientAddPanel.getYField();
			t.setText("");
		
			// redirect to list
			A2Frame a2Frame = patientAddPanel.getParentFrame();
			a2Frame.setTitle(a2Frame.getLabel() + ": Patients");
			patientAddPanel.setVisible(false);
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
