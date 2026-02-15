package backend;

import parser.*;
import reporter.ReportCreator;

import java.util.*;

import dom2app.SimpleTableModel;
public class MainController implements IMainController {
	private String[] Columnnames  = {"TaskId","TaskText","MamaId","Start","End","Cost"};
	private SimpleTableModel s;
	private ReportCreator myType;
	Object[][] TasksFile;
	@SuppressWarnings("rawtypes")
	private Loader l; 
	private String tmp1;
	public SimpleTableModel getS() {
		return s;
	}

	
	public String getFile() {
		return tmp1;
	}
	
	/**
     * @message load(String fileName, String delimiter)
     * @params String filename, String delimiter
     * @brief SimpleTableModel
     */
	@SuppressWarnings("rawtypes")
	public SimpleTableModel load(String fileName, String delimiter) {
		// see print again
		l = new Loader();
		tmp1 = fileName;
		s = l.load(fileName, delimiter, false);
		return s;
	}
	
	public Loader getLoader() {
		return l;
	}
 
	
	/**
     * @message getTasksByPrefix(String prefix)
     * @params String prefix
     */
	public SimpleTableModel getTasksByPrefix(String prefix){
		SimpleTableModel tmpStm = null;
		load(s.getPrjName(),"\t");
		List<String[]> p2 = new ArrayList<String[]>();

		for(int i=0; i< s.getRowCount(); i++){
			String tempTasksByPrefix= s.getValueAt(i, 1).toString().substring(0, prefix.length());
			if(prefix.equals(tempTasksByPrefix)){
							
				String[] lst3 = new String[6];
				for(int j=0; j< 6; j++){
					lst3[j] = s.getValueAt(i,j).toString();
				}
				if(p2.contains(lst3)) {
					break;
				}else {
					p2.add(lst3);							
				}
				continue;
						
			}
		}
		tmpStm = new SimpleTableModel(s.getName(),s.getPrjName(),Columnnames ,p2);

		return tmpStm;
	}

	
	/**
     * @message getTaskById(int id)
     * @params int id
     */
	@SuppressWarnings("null")
	public SimpleTableModel getTaskById(int id) {
		SimpleTableModel tmpStm = null;
		load(s.getPrjName(),"\t");
	
		
		String tempId;
		int numbOfRows = s.getRowCount();
		List<String[]> p = new 	ArrayList<String[]>();
		List<String[]> p2 = new ArrayList<String[]>();
		
			while(numbOfRows != 0){
				for(int i=0; i< s.getRowCount(); i++){
					tempId = s.getValueAt(i, 0).toString();
					int pop = Integer.parseInt(tempId);
					if(id == pop) {
						String[] lst3 = new String[6];
						for(int j=0; j< 6; j++){
							lst3[j] = s.getValueAt(i,j).toString();
						}
						p2.add(lst3);
						tmpStm = new SimpleTableModel(s.getName(),s.getPrjName(),Columnnames ,p2);
						
						return tmpStm;
					}
					else {
						continue;
					
					}
					
				}
				
				numbOfRows= numbOfRows - 1;
			}
			System.out.println("No such TaskId!");
	
		return null;
	    }

	/**
     * @message getTopLevelTasks()
     * @apiNote from the loaded file this method finds the TopLevel Tasks 
     * @return SimpleTableModel
     */
		public SimpleTableModel getTopLevelTasks() {
			SimpleTableModel tmpStm = null;
			load(s.getPrjName(),"\t");
			List<String[]> p2 = new ArrayList<String[]>();
	
			for(int i=0; i< s.getRowCount(); i++){
				String tempTopLevelTasks = s.getValueAt(i, 2).toString();
				String myTopTasks = "0"; 
				if(myTopTasks.equals(tempTopLevelTasks)){
								
					String[] lst3 = new String[6];
					for(int j=0; j< 6; j++){
						lst3[j] = s.getValueAt(i,j).toString();
					}
					if(p2.contains(lst3)) {
						break;
					}else {
						p2.add(lst3);							
					}
					continue;
							
				}
			}
			tmpStm = new SimpleTableModel(s.getName(),s.getPrjName(),Columnnames ,p2);

			return tmpStm;
		}

	/**
     * @message createReport(String path, ReportType type)
     * @params String path ,ReportType type
     * @apiNote this method takes the loaded data and makes it HTML or MD or TEXT 
     * and it saves the file into user's chosen path and with the user's chosen name
     * @return the formatted file 
     */
	
		 public int createReport(String path, ReportType type) {
		        myType = new ReportCreator(this);
		        if (type == ReportType.HTML) {
		            return myType.writeAsHtml(path, "\t");
		        }
		        /*if (type == ReportType.MD) {
		            return myType.writeAsMd(path);
		        }
		        if (type == ReportType.TEXT) {
		            return myType.writeAsTxt(path);
		        }
		        else {
		            System.out.println("No such ReportType.");
		        }*/
		        return 0;
		    }

}