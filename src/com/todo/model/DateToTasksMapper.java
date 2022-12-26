package com.todo.model;

import java.util.List;

public class DateToTasksMapper implements Comparable<DateToTasksMapper>{

	private String date;
	private String dateStyle;
	
	private List<PriorityToTasksMapper> p2TMap;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<PriorityToTasksMapper> getP2TMap() {
		return p2TMap;
	}

	public void setP2TMap(List<PriorityToTasksMapper> p2tMap) {
		p2TMap = p2tMap;
	}

	public String getDateStyle() {
		return dateStyle;
	}

	public void setDateStyle(String dateStyle) {
		this.dateStyle = dateStyle;
	}

	@Override
	public int compareTo(DateToTasksMapper o) {
		// TODO Auto-generated method stub
		return o.date.compareTo(this.date);
	}
	
	
}
