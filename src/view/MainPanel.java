package view;

import java.awt.Component;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class MainPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private A2Frame parentFrame;
	private JButton patientsButton, ambulancesButton, exitButton;
	
	public MainPanel(A2Frame parent){
		setParentFrame(parent);
		
		// create the components
		patientsButton = new JButton("Patients");
		ambulancesButton = new JButton("Ambulances");
		exitButton = new JButton("Exit");
		
		// set the layout
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS)); //  for main panel to be top to bottom
		
		// set the components to be center aligned on the panel
		patientsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		ambulancesButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		// add buttons onto the panel
		add(patientsButton);
		add(ambulancesButton);
		add(exitButton);
		
	}
	
	public JButton getPatientsButton() {
		return patientsButton;
	}

	public void setPatientsButton(JButton patientsButton) {
		this.patientsButton = patientsButton;
	}

	public JButton getAmbulancesButton() {
		return ambulancesButton;
	}

	public void setAmbulancesButton(JButton ambulancesButton) {
		this.ambulancesButton = ambulancesButton;
	}

	public JButton getExitButton() {
		return exitButton;
	}

	public void setExitButton(JButton exitButton) {
		this.exitButton = exitButton;
	}

	public A2Frame getParentFrame() {
		return parentFrame;
	}

	public void setParentFrame(A2Frame parent) {
		this.parentFrame = parent;
	}
}
