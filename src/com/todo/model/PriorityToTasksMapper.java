package com.todo.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;

import com.todo.constants.ToDoConstants;

public class PriorityToTasksMapper implements Comparable<PriorityToTasksMapper>{
	
	public static Map<String,Integer> priorityMap = new HashMap<>();
	
	static {
		priorityMap.put(ToDoConstants.priorityHigh, 1);
		priorityMap.put(ToDoConstants.priorityMedium, 2);
		priorityMap.put(ToDoConstants.priorityLow, 3);
		priorityMap.put(ToDoConstants.priorityComplete, 4);
	}
	
	private String priority;
	private String style;
	
	private List<Task> tasks;

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	@Override
	public int compareTo(PriorityToTasksMapper o) {
		// TODO Auto-generated method stub
		return priorityMap.get(this.priority)-priorityMap.get(o.priority);
	}
	
	

}
