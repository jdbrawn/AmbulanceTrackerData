package view;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;

import controller.MainControl;
import controller.AmbulanceAddControl;
import controller.AmbulanceListControl;
import controller.PatientListControl;
import controller.PatientAddControl;
import controller.PatientEditControl;
import model.AmbulanceCSVData;
import model.PatientCSVData;

public class A2Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	// title for Frame
	private String label = "Ambulance Tracker App";
	
	// models
	private AmbulanceCSVData ambulanceDataModel;
	private PatientCSVData patientDataModel;

	// views
	private MainPanel mainPanel;
	private AmbulanceListPanel ambulanceListPanel;
	private AmbulanceAddPanel ambulanceAddPanel;
	private PatientListPanel patientListPanel;
	private PatientAddPanel patientAddPanel;
	private PatientEditPanel patientEditPanel;
	//private AmbulanceAddPanel ambulanceAddPanel;
	
	// controllers
	private AmbulanceListControl ambulanceListController;
	private AmbulanceAddControl ambulanceAddController;
	private PatientListControl patientListController;
	private MainControl mainController;
	private PatientAddControl patientAddController;
	private PatientEditControl patientEditController;
	//private AmbulanceAddControl ambulanceAddController;
	
	public A2Frame(){
		// initial settings for frame
		initFrame();
		
		// create the panel
		mainPanel = new MainPanel(this);
		
		// create the panel
		ambulanceListPanel = new AmbulanceListPanel(this);
		ambulanceAddPanel = new AmbulanceAddPanel(this);
		
		// create the panel
		patientListPanel = new PatientListPanel(this);
		patientAddPanel = new PatientAddPanel(this);
		patientEditPanel = new PatientEditPanel(this);
		
		// create the models
		ambulanceDataModel = new AmbulanceCSVData();
		patientDataModel = new PatientCSVData();
				
		// create the controller and connect them with views and/or models
		mainController = new MainControl(mainPanel);
		ambulanceListController = new AmbulanceListControl(ambulanceListPanel, ambulanceDataModel);
		ambulanceAddController = new AmbulanceAddControl(ambulanceAddPanel, ambulanceDataModel);
		
		patientListController = new PatientListControl(patientListPanel, patientDataModel);
		patientAddController = new PatientAddControl(patientAddPanel, patientDataModel);
		patientEditController = new PatientEditControl(patientEditPanel, patientDataModel);
				
		// when A2Frame is created, mainPanel is displayed by default
		setContentPane(mainPanel);

	}
	
	protected void initFrame(){
		// Set the title of the main window
		setTitle(label);

		// set the initial window size
		setSize(1000,400);
		
		// present the window at the center of the screen
		setLocationRelativeTo(null);		
				
		// set the default close button to System.exit(0)
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	protected void backButtonAction(ActionEvent e) {
		// redirect to mainPanel
		setTitle(label);
		mainPanel.setVisible(true);
		setContentPane(mainPanel);
	}
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
	public MainPanel getMainPanel() {
		return mainPanel;
	}
	public void setMainPanel(MainPanel mainPanel) {
		this.mainPanel = mainPanel;
	}
	public AmbulanceListPanel getAmbulanceListPanel() {
		return ambulanceListPanel;
	}
	public void setAmbulanceListPanel(AmbulanceListPanel ambulanceListPanel) {
		this.ambulanceListPanel = ambulanceListPanel;
	}
	public PatientListPanel getPatientListPanel() {
		return patientListPanel;
	}
	public void setPatientListPanel(PatientListPanel patientListPanel) {
		this.patientListPanel = patientListPanel;
	}
	public PatientAddPanel getPatientAddPanel() {
		return patientAddPanel;
	}
	public void setPatientAddPanel(PatientAddPanel patientAddPanel) {
		this.patientAddPanel = patientAddPanel;
	}
	public AmbulanceAddPanel getAmbulanceAddPanel() {
		return ambulanceAddPanel;
	}
	public void setAmbulanceAddPanel(AmbulanceAddPanel ambulanceAddPanel) {
		this.ambulanceAddPanel = ambulanceAddPanel;
	}
	public PatientEditPanel getPatientEditPanel() {
		return patientEditPanel;
	}
	public void setPatientEditPanel(PatientEditPanel patientEditPanel) {
		this.patientEditPanel = patientEditPanel;
	}
	
	public MainControl getMainController() {
		return mainController;
	}
	public void setMainController(MainControl mainController) {
		this.mainController = mainController;
	}
	public AmbulanceListControl getAmbulanceListController() {
		return ambulanceListController;
	}
	public void setAmbulanceListController(AmbulanceListControl ambulanceListController) {
		this.ambulanceListController = ambulanceListController;
	}
	public PatientListControl getPatientListController() {
		return patientListController;
	}
	public void setPatientListController(PatientListControl patientListController) {
		this.patientListController = patientListController;
	}
	public PatientAddControl getPatientAddController() {
		return patientAddController;
	}
	public void setPatientAddController(PatientAddControl patientAddController) {
		this.patientAddController = patientAddController;
	}
	public AmbulanceAddControl getAmbulanceAddController() {
		return ambulanceAddController;
	}
	public void setAmbulanceAddController(AmbulanceAddControl ambulanceAddController) {
		this.ambulanceAddController = ambulanceAddController;
	}
	public PatientEditControl getPatientEditController() {
		return patientEditController;
	}
	public void setPatientEditController(PatientEditControl patientEditController) {
		this.patientEditController = patientEditController;
	}
	public PatientCSVData getPatientDataModel(){
		return patientDataModel;
	}

	public AmbulanceCSVData getAmbulanceDataModel() {
		return ambulanceDataModel;
	}
	
}
