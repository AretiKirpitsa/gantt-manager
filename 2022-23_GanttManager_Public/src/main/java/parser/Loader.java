package parser;
import parser.FileHandler;
import dom.Task;
import dom2app.SimpleTableModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @class Loader
 * @brief Implements the ILoader interface and its base functions
 * 			primarily dealing with loading and reading data files
 */
public class Loader<E> implements ILoader<E>{
	/**
	 * delimiter -> a String with the delimiter between columns of the source fill
	 * objCollection -> and empty list which will be loaded with the data from the input file
	 */
	private String delimiter;
	
	private List<Task> objCollection = new ArrayList<Task>();
	private FileHandler fileHandler;
	private SimpleTableModel s;
	private Task task = new Task();
	public String tmpfilename;
	/**
	 * @message writeToCollection
	 * @brief Writes the data to the provided collection
	 * @return the return type of the function
	 */
	public List<Task> getobjCollection(){
		return objCollection;
	}
	public String getTmpFilename() {
		return tmpfilename;
	}
	
	public int writeToCollection() {
		List<Task> tempObjCollection = new ArrayList<Task>();
		
		/* Write data while reading */
		while(true) {
			String data = fileHandler.readLineFromFile();
			if(data == null)
				break;

			/* In most cases 'E' dissolves into MeasurementRecord */
			Task dataRecord = createDataRecord(data, delimiter);

			/* Some line had wrong data */
			if(dataRecord == null)
				continue;

			/* The delimiter is different than the one in the file */
			if(((Task) dataRecord).get__delimiter_error())
				return -1;

			tempObjCollection.add(dataRecord);
		}
		
		for(int i =0; i <tempObjCollection.size(); i++){
			if(tempObjCollection.get(i).getMamaId().equals("0")){
				tempObjCollection.get(i).setStart(startDate(tempObjCollection, tempObjCollection.get(i).getTaskId()));
				tempObjCollection.get(i).setEnd(endDate(tempObjCollection, tempObjCollection.get(i).getTaskId()));
				tempObjCollection.get(i).setCost(Cost(tempObjCollection, tempObjCollection.get(i).getTaskId()));
			}
		}
		
		
		while (tempObjCollection.size() > 0){
			//Find minimum TaskId and its position
			int minTaskId = Integer.parseInt(tempObjCollection.get(0).getTaskId());
			int minIndex = 0;
			for(int j = 1; j < tempObjCollection.size(); j++){
				if (Integer.parseInt(tempObjCollection.get(j).getTaskId()) < minTaskId) {
					minTaskId = Integer.parseInt(tempObjCollection.get(j).getTaskId());
					minIndex = j;
				}
			}
			objCollection.add(tempObjCollection.get(minIndex));
			tempObjCollection.remove(minIndex);
		}
			
		return 0;
	}

	/**
	 * @message createDataRecord
	 * @brief Creates a new   ArrayList and sets its fields according to what we read from the input file
	 * @param data the string we split into the different fields
	 * @param delimiter the delimiter to which we split to
	 * @return the filled data we want to insert to objCollection ArrayList
	 */
	

	
	public Task createDataRecord(String data, String delimiter) {
		//AdataItems = data.split(delimiter);
		ArrayList<String> dataItems = new ArrayList<>();
		String[] myData = new String[6];
		if (data.split(this.delimiter).length==3){
			data = data + "\t \t \t ";
			myData = data.split(this.delimiter);
		}else {
				myData = data.split(this.delimiter);
		}
		try {
			for(int i = 0; i<myData.length; i++) {
				dataItems.add(myData[i]);
			}
		}
		/* Possible exceptions on splitting, due to wrong inputs on the data files */
		catch(Exception e) {
			System.out.println("The delimiter you set was wrong for the specific input file.");
			Task wrongData = new Task();	
			wrongData.set__delimiter_error(true);
			return wrongData;
		}
		Task dateModel = new Task();

		
		try {
			/* Trying to access dateItems[1] might reproduce out of index errors due to a wrong hasHeaderLine input */
			dateModel.setTaskId(dataItems.get(0));
			dateModel.setTaskText(dataItems.get(1));
			dateModel.setMamaId(dataItems.get(2));
				dateModel.setStart(dataItems.get(3));
				dateModel.setEnd(dataItems.get(4));
				dateModel.setCost(dataItems.get(5));
			
		}
		catch(Exception e) { /* Swallow the error */
			System.out.println("The file has a header line though you provided that it didn't.");
			Task wrongData = new Task();
			wrongData.set__delimiter_error(true);
			return wrongData;
		}
		
		
		
		

		return dateModel;
	}
	/**
	 * @message load
	 * @return a SimpleTableModel with all the data
	 * @param filename: a String with the name of the input file
	 * @param delimiter: a String with the delimiter between columns of the source file
	 * @param hasHeaderLine: specifies whether the file has a header (true) or not (false)
	 * @param objCollection: and empty list which will be loaded with the data from the input file
	 */
	@Override
	public SimpleTableModel load(String filename, String delimiter, boolean hasHeaderLine) {
		this.delimiter = delimiter;
		//filename=s.getPrjName();
		tmpfilename = filename;
		
		this.fileHandler = new FileHandler(filename);
		
		/* Create a managed file descriptor */
		if(fileHandler.createReaderFD() == -1) {
			fileHandler.closeFD();
			return null;
		}

		if(hasHeaderLine) {
			/* Start reading from 2nd row and ignore the first line */
			 fileHandler.readLineFromFile();
		}

		/* Start reading from 1st row */
		if(writeToCollection() == -1) {
			fileHandler.closeFD();
			return null;
		}
		
		List<String[]> tmpList123= new ArrayList<String[]>();
		String[] Columnnames  = {"TaskId","TaskText","MamaId","Start","End","Cost"};
		for(int i =0; i < objCollection.size(); i ++) {
		   	tmpList123.add(objCollection.get(i).toString().split("\t"));
		}
		
		s = new SimpleTableModel(filename.substring(filename.lastIndexOf("\\")+1), filename,Columnnames,tmpList123);		
		
		fileHandler.closeFD();
		return this.s;
	}

	/*It finds the lowest Tasks Date and returns it to their TopLevelTask
	 * 
	 */
	public String startDate(List<Task> list,String taskId){
		List<String> StartDate = new ArrayList<String>();
		for(int i =0; i <list.size();i++) {
			if (list.get(i).getMamaId().equals(taskId)){
					StartDate.add(list.get(i).getStart());
			}
		}	
		return Collections.min(StartDate);
	}
	/*It finds the highest Tasks Date and returns it to their TopLevelTask
	 * 
	 */
	public String endDate(List<Task> list, String taskId){
		List<String> EndDate = new ArrayList<String>();
		for(int i =0; i <list.size();i++) {
			if (list.get(i).getMamaId().equals(taskId)){
					EndDate.add(list.get(i).getEnd());
			}
		}	
		return Collections.max(EndDate);
	}
	/*It calculates the sum of the tasks cost
	 * 
	 */
	public String Cost(List<Task> list, String taskId){
		int sum = 0;
		for(int i =0; i <list.size();i++) {
			if (list.get(i).getMamaId().equals(taskId)){
				sum += Integer.parseInt(list.get(i).getCost());
			}
		}	
		return Integer.toString(sum);
	}
	
}