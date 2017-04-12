package view;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class AmbulanceAddPanel extends JPanel  {
	
private static final long serialVersionUID = 1L;
	
	private A2Frame parentFrame;
	
	private JPanel row1;
	private JLabel idLabel;
	private String idString;
	private JTextField id;
	
	private JPanel row2;
	private JLabel locationLabel;
	private JTextField x;
	private JTextField y;
	
	private JPanel row3;
	private JLabel statusLabel;
	private String[] statuses = { "At Station", "Responding", "At Scene", "Transporting", "At Destination", "Returning"};
	private JComboBox<String> status;
	
	private JPanel row4;
	private JLabel patientLabel;
	private String[] patients;
	private JComboBox<String> patient;
	
	private JPanel row5;
	private JButton cancel;
	private JButton save;
	
	public AmbulanceAddPanel(A2Frame parent){
		setParentFrame(parent);
		
		// create the components
		row1 = new JPanel(new FlowLayout());
		idLabel = new JLabel("Ambulance ID:");
		id = new JTextField("", 10);
		id.setEditable(true);
				
		row2 = new JPanel(new FlowLayout());
		locationLabel = new JLabel("Location:");
		x = new JTextField("", 5);
		y = new JTextField("", 5);
		
		row3 = new JPanel(new FlowLayout());
		statusLabel = new JLabel("Status:");
		status =  new JComboBox<String>(statuses);
		
		row4 = new JPanel(new FlowLayout());
		patientLabel = new JLabel("Patient:");
		//ambulances = this.parentFrame.getAmbulanceDataModel().getAmbulanceIDs();
		patient = new JComboBox<String>();
		
		row5 = new JPanel(new FlowLayout());
		cancel = new JButton("Cancel");
		save = new JButton("Save");
		
		// set the layout
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS)); //  for panel to be top to bottom
		
		// add the components
		row1.add(idLabel);
		row1.add(id);
		add(row1);
		row2.add(locationLabel);
		row2.add(x);
		row2.add(y);
		add(row2);
		row3.add(statusLabel);
		row3.add(status);
		add(row3);
		row4.add(patientLabel);
		row4.add(patient);
		add(row4);
		row5.add(cancel);
		row5.add(save);
		add(row5);
		
	}
	
	public String[] getPatients(){
		return patients;
	}
	public void setPatients(String[] patients){
		this.patients = patients;
	}
	
	public String getIDString() {
		return idString;
	}
	public void setIDString(String id){
		this.idString = id;
	}
	
	public JComboBox<String> getPatient(){
		return patient;
	}
	public void setPatient(String[] patients){
		this.patient = new JComboBox<String>(patients);
	}
	
	public JTextField getID(){
		return id;
	}
	public void setID(String id){
		this.id = new JTextField(id, 10);
	}
	
	public A2Frame getParentFrame() {
		return parentFrame;
	}

	public void setParentFrame(A2Frame parent) {
		this.parentFrame = parent;
	}
	
	public JButton getSaveButton() {
		return save;
	}

	public void setSaveButton(JButton saveButton) {
		this.save = saveButton;
	}
	
	public JButton getCancelButton() {
		return cancel;
	}

	public void setCancelButton(JButton cancelButton) {
		this.cancel = cancelButton;
	}
	
	public JTextField getXField(){
		return x;
	}
	public void setXField(String x){
		this.x = new JTextField(x, 5);
	}
	public JTextField getYField(){
		return y;
	}
	public void setYField(String y){
		this.y = new JTextField(y, 5);
	}
	public JComboBox<String> getStatus(){
		return status;
	}
	public void setStatus(String[] statuses){
		this.status = new JComboBox<String>(statuses);
	}

}
