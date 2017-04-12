package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class PatientCSVData {

	private String[] patientColNames = {"ID", "Location", "Status", "Ambulance"};
	private Object[][] patientRowData;
	private String patientCSVFile = "patients.csv";
	private int currentRow;
	
	public PatientCSVData(){
		loadPatients();
	}
	
	public void loadPatients() {
		// declare a scanner object
		Scanner scanner = null;
		try {
			
			// open the file for reading
			scanner = new Scanner(new File(patientCSVFile));
			
			// set column headers
			setPatientColNames(patientColNames);
			
			// declare temporary structure to hold the rows
			ArrayList<String[]> temp = new ArrayList<>();
			
			// read the data into temporary structure
			scanner.nextLine();
	        while(scanner.hasNext()) {
	            String[] line = scanner.nextLine().split(",");
	           
	            String[] templine = new String[4];
	            templine[0] = line[0];
	            templine[1] = "("+line[1]+","+line[2]+")";
	            templine[2] = line[3];
	            templine[2] = templine[2].replace("\"", "");
	            if (line.length == 5) {templine[3] = line[4];}
	            else {templine[3] = "-";}
	            templine[3] = templine[3].replace("\"", "");
	            
	            temp.add(templine);
	        }
	        
	        // initialize instance variable data
	        patientRowData = new Object[temp.size()][];
	        
	        // add it to our data instance variable
	        for (int row= 0 ; row < temp.size(); row++){
	        	patientRowData[row] = new String[temp.get(row).length];
	        	patientRowData[row] = temp.get(row);
	        }
	        
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			System.exit(0);
		} finally {
			if(scanner != null)
				scanner.close();
		}
	}
	
	public void savePatients(Object[][] patientData) throws IOException{
		BufferedWriter bw = null;
		try{
			// open the file
			bw = new BufferedWriter(new FileWriter(new File("patients.csv")));
			
			// write the row in csv format
			bw.append("id,x.location,y.location,status,ambulance");
			bw.append(System.lineSeparator());
			for (int i = 0; i < patientData.length; i++) {
				String xy = (String)patientData[i][1];
				String[] xySplit = xy.split(",");
				xySplit[1].replaceAll("\\s","");
				bw.append(patientData[i][0]+","+xySplit[0].replace("(", "")+","+xySplit[1].replace(")", "")+","+patientData[i][2]+","+patientData[i][3]);
				bw.append(System.lineSeparator());
			}
			
		} catch (FileNotFoundException e) {
			System.out.println(patientCSVFile + " file not found");
		}finally{
			if(bw!=null)
				bw.close();
		}
	}
	
	public void updatePatient(int id, int x, int y, String status, String ambulance) throws IOException{
		int row = id-1;
		patientRowData[row][0] = Integer.toString(id);
		patientRowData[row][1] = "("+x+","+y+")";
		patientRowData[row][2] = status;
		patientRowData[row][3] = ambulance;
		savePatients(patientRowData);
	}
	
	public void addPatient(int id, int x, int y, String status, String ambulance) throws IOException{
		Object[][] tempRowData = new Object[patientRowData.length + 1][4];
		for (int i = 0; i < patientRowData.length; i++){
			tempRowData[i] = patientRowData[i];
		}
		tempRowData[patientRowData.length][0] = Integer.toString(id);
		tempRowData[patientRowData.length][1] = "("+x+","+y+")";
		tempRowData[patientRowData.length][2] = status;
		tempRowData[patientRowData.length][3] = ambulance;
		
		patientRowData = tempRowData;
		
		savePatients(tempRowData);
	}
	
	public String[] getPatientIDs(){
		String[] temp = new String[patientRowData.length];
		for (int i = 0; i < patientRowData.length; i++){
			temp[i] = (String) patientRowData[i][0];
		}
		return temp;
	}
	
	public void setCurrentRow(int row){
		this.currentRow = row;
	}
	public int getCurrentRow(){
		return currentRow;
	}
	public int getCurrentID() {
		//System.out.println(patientRowData.length);
		return patientRowData.length;
	}
	
	public String getPatientCSVFile() {
		return patientCSVFile;
	}

	public void setPatientCSVFile(String patientCSVFile) {
		this.patientCSVFile = patientCSVFile;
	}
	public String[] getPatientColNames() {
		return patientColNames;
	}

	public void setPatientColNames(String[] patientColNames) {
		this.patientColNames = patientColNames;
	}

	public Object[][] getPatientRowData() {
		return patientRowData;
	}

	public void setPatientRowData(Object[][] patientRowData) {
		this.patientRowData = patientRowData;
	}

	public void updatePatientAmbulance(String patient, String id) throws IOException {
		patientRowData[Integer.parseInt(patient)-1][3] = id;
		savePatients(patientRowData);
	}
}
