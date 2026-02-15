package dom;

import java.util.ArrayList;
import java.util.List;


public class Task {
	private String TaskId;
	private String TaskText;
	private String MamaId;
	private String Start;
	private String End;
	private String Cost;
	private boolean _delimiter_error;
	private List<String[]> allTasks = new ArrayList<String[]>();
	private Task date;
	/*
	 *@MUTATOR AND ACCESORS METHODS SO THAT WE CAN USE THE DATA 
	 * 
	 */
	public Task() {}
	
	public void setDate(Task date) {
		this.date = date;
	}
	public Task getDate() {
		return date;
	}
	public String getTaskId() {
		return this.TaskId;
	}
	
	public void setTaskId(String TaskId) {
		this.TaskId=TaskId;
	}
	
	public String getTaskText() {
		return this.TaskText;
	}
	
	public void setTaskText(String TaskText) {
		this.TaskText=TaskText;
	}
	
	public String getMamaId() {
		return this.MamaId;
	}
	
	public void setMamaId(String MamaId) {
		this.MamaId=MamaId;
	}
	
	public String getStart() {
		return this.Start;
	}
	
	public void setStart(String Start) {
		this.Start=Start;
	}
	
	public String getEnd() {
		return this.End;
	}
	
	public void setEnd(String End) {
		this.End=End;
	}
	
	public String getCost() {
		return this.Cost;
	}
	
	public void setCost(String Cost) {
		this.Cost=Cost;
	}
	public boolean get__delimiter_error() {
        return this._delimiter_error;
    }
    public void set__delimiter_error(boolean _delimiter_error) {
        this._delimiter_error = _delimiter_error;
    }

	public String toString() {
		
		String result = this.TaskId + "\t" + this.TaskText +"\t"+this.MamaId + "\t" + ((this.Start == null) ? " " : this.Start) + "\t" +((this.End == null) ? " " : this.End) + "\t" +((this.Cost == null) ? " " : this.Cost) ;
		
		return result;
	}
	
    
}
