package com.todo.helper;

import java.io.BufferedReader;
import java.io.IOException;

import com.todo.constants.ToDoConstants;
import com.todo.mbean.ToDoMBean;
import com.todo.util.ToDoUtil;

import edu.emory.mathcs.backport.java.util.Collections;

public class ReviewTasksDisplayHelper {

	public static void reviewTasksForDate(ToDoMBean toDoMBean, String readLine) {
		/*
		 * // TODO Auto-generated method stub
		 * 
		 * String taskName = readLine.split(ToDoConstants.splitString)[2]; if(readLine.contains(",high,")) {
		 * toDoMBean.getReviewHighList().add(taskName); }else
		 * if(readLine.contains(",medium,")) {
		 * toDoMBean.getReviewMediumList().add(taskName); }else
		 * if(readLine.contains(",low,")) { toDoMBean.getReviewLowList().add(taskName);
		 * }
		 * 
		 */}
	
	public static void reviewOnSelectingADate(ToDoMBean toDoMBean, String date) throws IOException {
		BufferedReader reader=ToDoUtil.getReader(toDoMBean);

		String readLine = "";
		while((readLine = reader.readLine())!=null) {
			AddTasksDisplayHelper.updateLists(toDoMBean,readLine,date,true);
		}
		reader.close();
		Collections.sort(toDoMBean.getReviewTasks(), Collections.reverseOrder());
	}



}
