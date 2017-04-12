package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.MainPanel;
import view.A2Frame;

public class MainControl implements ActionListener {

	MainPanel mainPanel;
	
	public MainControl(MainPanel mainPanel){
		
		this.mainPanel = mainPanel;
		
		// register the listeners
		this.mainPanel.getPatientsButton().addActionListener(this);
		this.mainPanel.getAmbulancesButton().addActionListener(this);
		this.mainPanel.getExitButton().addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source == this.mainPanel.getExitButton() ){
			// call exitButtonAction
			exitButtonAction(e);
		} else if(source == this.mainPanel.getPatientsButton() ){
			// call patientsButtonAction
			patientsButtonAction(e);
		} else if(source == this.mainPanel.getAmbulancesButton() ){
			// call ambulancesButtonAction
			ambulancesButtonAction(e);
		}
	}
	
	private void ambulancesButtonAction(ActionEvent e) {
		// redirect to ambulanceListPanel
		A2Frame a2Frame = mainPanel.getParentFrame();
		a2Frame.setTitle(a2Frame.getLabel() + ": Ambulances");
		mainPanel.setVisible(false);
		a2Frame.setContentPane(a2Frame.getAmbulanceListPanel());
	}

	private void patientsButtonAction(ActionEvent e) {
		// redirect to ambulanceListPanel
		A2Frame a2Frame = mainPanel.getParentFrame();
		a2Frame.setTitle(a2Frame.getLabel() + ": Patients");
		mainPanel.setVisible(false);
		a2Frame.setContentPane(a2Frame.getPatientListPanel());
	}

	private void exitButtonAction(ActionEvent e) {
		// terminate the application
		System.exit(0);
		
	}
}
