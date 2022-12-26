package com.todo.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.todo.constants.ToDoConstants;

public class ToDoData {
	private Date toDoDate;
	
	private String toDoTaskName;
	private String toDoTaskPriority;
	public Date getToDoDate() {
		return toDoDate;
	}
	public void setToDoDate(Date toDoDate) {
		this.toDoDate = toDoDate;
	}
	public String getToDoTaskName() {
		return toDoTaskName;
	}
	public void setToDoTaskName(String toDoTaskName) {
		this.toDoTaskName = toDoTaskName;
	}
	public String getToDoTaskPriority() {
		return toDoTaskPriority;
	}
	public void setToDoTaskPriority(String toDoTaskPriority) {
		this.toDoTaskPriority = toDoTaskPriority;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat(ToDoConstants.datePattern);
		String formatedDate = "";
		if(toDoDate!=null) {
		 formatedDate = sdf.format(toDoDate);
		}
		return formatedDate+ToDoConstants.splitString+this.toDoTaskPriority+ToDoConstants.splitString+this.toDoTaskName;
	}
	
	

}
