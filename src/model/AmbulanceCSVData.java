package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class AmbulanceCSVData {

	private String[] ambulanceColNames = {"ID", "Location", "Status", "Patient"};
	private Object[][] ambulanceRowData;
	private String ambulanceCSVFile = "ambulances.csv";
	private int currentRow;
	
	public AmbulanceCSVData(){
		loadAmbulances();
	}
	
	public void loadAmbulances() {
		// declare a scanner object
		Scanner scanner = null;
		try {
			
			// open the file for reading
			scanner = new Scanner(new File(ambulanceCSVFile));
			
			// set column headers
			setAmbulanceColNames(ambulanceColNames);
			
			// declare temporary structure to hold the rows
			ArrayList<String[]> temp = new ArrayList<>();
			
			// read the data into temporary structure
			scanner.nextLine();
	        while(scanner.hasNext()) {
	            String[] line = scanner.nextLine().split(",");
	           
	            String[] templine = new String[4];
	            templine[0] = line[0];
	            templine[0] = templine[0].replace("\"", "");
	            templine[1] = "("+line[1]+","+line[2]+")";
	            templine[2] = line[3];
	            templine[2] = templine[2].replace("\"", "");
	            if (line.length == 5) {templine[3] = line[4];}
	            else {templine[3] = "-";}
	            templine[3] = templine[3].replace("\"", "");
	            
	            temp.add(templine);
	        }
	        
	        // initialize instance variable data
	        ambulanceRowData = new Object[temp.size()][];
	        
	        // add it to our data instance variable
	        for (int row= 0 ; row < temp.size(); row++){
	        	ambulanceRowData[row] = new String[temp.get(row).length];
	        	ambulanceRowData[row] = temp.get(row);
	        }
	        
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			System.exit(0);
		} finally {
			if(scanner != null)
				scanner.close();
		}
	}
	
	public void saveAmbulances(Object[][] ambulanceData) throws IOException{
		BufferedWriter bw = null;
		try{
			// open the file
			bw = new BufferedWriter(new FileWriter(new File("ambulances.csv")));
			
			// write the row in csv format
			bw.append("id,x.location,y.location,status,patient");
			bw.append(System.lineSeparator());
			for (int i = 0; i < ambulanceData.length; i++) {
				String xy = (String)ambulanceData[i][1];
				String[] xySplit = xy.split(",");
				xySplit[1].replaceAll("\\s","");
				bw.append(ambulanceData[i][0]+","+xySplit[0].replace("(", "")+","+xySplit[1].replace(")", "")+","+ambulanceData[i][2]+","+ambulanceData[i][3]);
				bw.append(System.lineSeparator());
			}
			
		} catch (FileNotFoundException e) {
			System.out.println(ambulanceCSVFile + " file not found");
		}finally{
			if(bw!=null)
				bw.close();
		}
	}
	
	public void updateAmbulancePass(String id, String patient) throws IOException{
		int index=-1;
		for (int i=0;i<ambulanceRowData.length;i++) {
		    if (ambulanceRowData[i][0].equals(id)) {
		        index = i;
		        break;
		    }
		}
		ambulanceRowData[index][3] = patient;
		saveAmbulances(ambulanceRowData);
	}
	
	public void updateAmbulance(int index, String id, int x, int y, String status, String patient) throws IOException{
		//int index=-1;
		//for (int i=0;i<ambulanceRowData.length;i++) {
		//    if (ambulanceRowData[i][0].equals(id)) {
		//        index = i;
		//        break;
		//    }
		//}
		
		ambulanceRowData[index][0] = id;
		ambulanceRowData[index][1] = "("+x+","+y+")";
		ambulanceRowData[index][2] = status;
		ambulanceRowData[index][3] = patient;
		saveAmbulances(ambulanceRowData);
	}
	
	public void addAmbulance(String id, int x, int y, String status, String patient) throws IOException{
		Object[][] tempRowData = new Object[ambulanceRowData.length + 1][4];
		for (int i = 0; i < ambulanceRowData.length; i++){
			tempRowData[i] = ambulanceRowData[i];
		}
		tempRowData[ambulanceRowData.length][0] = id;
		tempRowData[ambulanceRowData.length][1] = "("+x+","+y+")";
		tempRowData[ambulanceRowData.length][2] = status;
		tempRowData[ambulanceRowData.length][3] = patient;
		
		ambulanceRowData = tempRowData;
		
		saveAmbulances(tempRowData);
	}
	
	public String[] getAmbulanceIDs(){
		String[] temp = new String[ambulanceRowData.length];
		for (int i = 0; i < ambulanceRowData.length; i++){
			temp[i] = (String) ambulanceRowData[i][0];
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
		return ambulanceRowData.length;
	}
	
	public String getAmbulanceCSVFile() {
		return ambulanceCSVFile;
	}

	public void setAmbulanceCSVFile(String ambulanceCSVFile) {
		this.ambulanceCSVFile = ambulanceCSVFile;
	}
	public String[] getAmbulanceColNames() {
		return ambulanceColNames;
	}

	public void setAmbulanceColNames(String[] ambulanceColNames) {
		this.ambulanceColNames = ambulanceColNames;
	}

	public Object[][] getAmbulanceRowData() {
		return ambulanceRowData;
	}

	public void setAmbulanceRowData(Object[][] ambulanceRowData) {
		this.ambulanceRowData = ambulanceRowData;
	}
}
