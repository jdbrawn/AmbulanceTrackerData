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

import model.AmbulanceCSVData;
import view.A2Frame;
import view.AmbulanceAddPanel;

public class AmbulanceAddControl implements ActionListener {
	
	AmbulanceAddPanel ambulanceAddPanel;
	AmbulanceCSVData ambulanceCSVData;
	
	public AmbulanceAddControl(AmbulanceAddPanel ambulanceAddPanel, AmbulanceCSVData ambulanceCSVData){
		// connect with view and model
		this.ambulanceAddPanel = ambulanceAddPanel;
		this.ambulanceCSVData = ambulanceCSVData;
		
		// add data
		String[] patients = this.ambulanceAddPanel.getParentFrame().getPatientDataModel().getPatientIDs();
		Arrays.sort(patients);
		JComboBox<String> a = this.ambulanceAddPanel.getPatient();
		a.addItem("-");
		for (int i = 0; i < patients.length; i++){
			a.addItem(patients[i]);
		}
		
		// register the listeners
		this.ambulanceAddPanel.getCancelButton().addActionListener(this);
		this.ambulanceAddPanel.getSaveButton().addActionListener(this);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source == this.ambulanceAddPanel.getCancelButton() ){
			cancelButtonAction(e);
		} else if(source == this.ambulanceAddPanel.getSaveButton() ){
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
		// redirect to ambulanceListPanel
		A2Frame a2Frame = ambulanceAddPanel.getParentFrame();
		a2Frame.setTitle(a2Frame.getLabel() + ": Patients");
		ambulanceAddPanel.setVisible(false);
		a2Frame.getAmbulanceListPanel().setVisible(true);
		a2Frame.setContentPane(a2Frame.getAmbulanceListPanel());
	}
	
	private void saveButtonAction(ActionEvent e) throws NumberFormatException, IOException {
		
		
		int row = ambulanceCSVData.getCurrentRow();
		String id = ambulanceAddPanel.getID().getText();
		String xPos = ambulanceAddPanel.getXField().getText();
		String yPos = ambulanceAddPanel.getYField().getText();
		String status = (String)ambulanceAddPanel.getStatus().getSelectedItem();
		String patient = (String)ambulanceAddPanel.getPatient().getSelectedItem();
		
		if (id.isEmpty()){
			JFrame frame = new JFrame();
        	JOptionPane.showMessageDialog(frame,"Ambulance ID must consist of an A followed by a 1-3 digit number","Error",JOptionPane.PLAIN_MESSAGE);
		}
		else if (id.charAt(0) != 'A'){
			JFrame frame = new JFrame();
        	JOptionPane.showMessageDialog(frame,"Ambulance ID must consist of an A followed by a 1-3 digit number","Error",JOptionPane.PLAIN_MESSAGE);
		}
		else if (id.length() > 4 || id.length() == 1){
			JFrame frame = new JFrame();
        	JOptionPane.showMessageDialog(frame,"Ambulance ID must consist of an A followed by a 1-3 digit number","Error",JOptionPane.PLAIN_MESSAGE);
		}
		else if (!isInteger(id.substring(1))){
			JFrame frame = new JFrame();
        	JOptionPane.showMessageDialog(frame,"Ambulance ID must consist of an A followed by a 1-3 digit number","Error",JOptionPane.PLAIN_MESSAGE);
		}
		else if (Integer.parseInt(id.substring(1)) < 0 || Integer.parseInt(id.substring(1)) > 999){
			JFrame frame = new JFrame();
        	JOptionPane.showMessageDialog(frame,"Ambulance ID must consist of an A followed by a 1-3 digit number","Error",JOptionPane.PLAIN_MESSAGE);
		}
		else if (xPos.isEmpty()){
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
		
		
		
			if (row < ambulanceCSVData.getAmbulanceRowData().length){
				ambulanceCSVData.updateAmbulance(row, id, Integer.parseInt(xPos), Integer.parseInt(yPos), status, patient);
			}
			else {
				ambulanceCSVData.addAmbulance(id, Integer.parseInt(xPos), Integer.parseInt(yPos), status, patient);
			}
				
			if (!"-".equals(patient)){
				ambulanceAddPanel.getParentFrame().getPatientDataModel().updatePatientAmbulance(patient, id);
			}
		
		
		
			DefaultTableModel m = (DefaultTableModel) ambulanceAddPanel.getParentFrame().getAmbulanceListPanel().getAmbulanceTable().getModel();
			m.setDataVector(ambulanceCSVData.getAmbulanceRowData() , ambulanceCSVData.getAmbulanceColNames());
			m.fireTableDataChanged();
		
			DefaultTableModel m1 = (DefaultTableModel) ambulanceAddPanel.getParentFrame().getPatientListPanel().getPatientTable().getModel();
			m1.setDataVector(ambulanceAddPanel.getParentFrame().getPatientDataModel().getPatientRowData() , ambulanceAddPanel.getParentFrame().getPatientDataModel().getPatientColNames());
			m1.fireTableDataChanged();
		
			JTextField t;
			t = ambulanceAddPanel.getXField();
			t.setText("");
			t = ambulanceAddPanel.getYField();
			t.setText("");
		
			// redirect to AmbulanceListPanel
			A2Frame a2Frame = ambulanceAddPanel.getParentFrame();
			a2Frame.setTitle(a2Frame.getLabel() + ": Patients");
			ambulanceAddPanel.setVisible(false);
			a2Frame.getAmbulanceListPanel().setVisible(true);
			a2Frame.setContentPane(a2Frame.getAmbulanceListPanel());
		
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
