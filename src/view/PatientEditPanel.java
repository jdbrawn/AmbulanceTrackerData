package view;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class PatientEditPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private A2Frame parentFrame;
	
	private JPanel row1;
	private JLabel idLabel;
	private int idNum;
	private JTextField id;
	
	private JPanel row2;
	private JLabel locationLabel;
	private JTextField x;
	private JTextField y;
	
	private JPanel row3;
	private JLabel statusLabel;
	private String[] statuses = {"Pending", "Assigned", "Transporting","Completed"};
	private JComboBox<String> status;
	
	private JPanel row4;
	private JLabel ambulanceLabel;
	private String[] ambulances;
	private JComboBox<String> ambulance;
	
	private JPanel row5;
	private JButton cancel;
	private JButton save;
	
	public PatientEditPanel(A2Frame parent){
		
		setParentFrame(parent);
		
		// create the components
		row1 = new JPanel(new FlowLayout());
		idLabel = new JLabel("ID:");
		id = new JTextField("", 10);
		id.setEditable(false);
				
		row2 = new JPanel(new FlowLayout());
		locationLabel = new JLabel("Location:");
		x = new JTextField("", 5);
		y = new JTextField("", 5);
		
		row3 = new JPanel(new FlowLayout());
		statusLabel = new JLabel("Status:");
		status =  new JComboBox<String>(statuses);
		
		row4 = new JPanel(new FlowLayout());
		ambulanceLabel = new JLabel("Ambulance:");
		//ambulances = this.parentFrame.getAmbulanceDataModel().getAmbulanceIDs();
		ambulance = new JComboBox<String>();
		
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
		row4.add(ambulanceLabel);
		row4.add(ambulance);
		add(row4);
		row5.add(cancel);
		row5.add(save);
		add(row5);
		
	}
	
	public String[] getAmbulances(){
		return ambulances;
	}
	public void setAmbulances(String[] ambulances){
		this.ambulances = ambulances;
	}
	
	public int getIDNum() {
		return idNum;
	}
	public void setIDNum(int id){
		this.idNum = id;
	}
	
	public JComboBox<String> getAmbulance(){
		return ambulance;
	}
	public void setAmbulance(String[] ambulances){
		this.ambulance = new JComboBox<String>(ambulances);
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
