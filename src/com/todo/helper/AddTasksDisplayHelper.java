package com.todo.helper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.ibm.db2.jcc.c.sb;
import com.todo.constants.ToDoConstants;
import com.todo.mbean.ToDoMBean;
import com.todo.model.ToDoData;
import com.todo.util.ToDoUtil;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import edu.emory.mathcs.backport.java.util.Collections;

public class AddTasksDisplayHelper {
	
	private static final Logger
    LOGGER = LogManager.getLogger(AddTasksDisplayHelper.class);

	public static void addTask(ToDoMBean toDoMBean, ToDoData toDoData) throws IOException {

		/*
		 * File file = new File("/toDoList.txt"); if(!file.exists()) {
		 * if(file.createNewFile()) { System.out.println("new file created"); }
		 * 
		 * } System.out.println(file.getAbsolutePath());
		 */
		StringBuffer sb = new StringBuffer();
		String readLine = "";
		BufferedReader reader = ToDoUtil.getReader(toDoMBean);
		ToDoUtil.initTodayTasks(toDoMBean);

		LOGGER.error("This is an ERROR level log message!");

		String newlyAddedTaskDate = toDoData.toString().split(ToDoConstants.splitString)[0];
		while((readLine = reader.readLine())!=null) {
			if(!readLine.equals(toDoData.toString()) && !readLine.equals(toDoMBean.getToDoData2bEdited().toString())) {
				sb.append(readLine).append("\n");
				updateLists(toDoMBean,readLine,"$",false);
			}
		}
		if(!toDoData.toString().contains(ToDoConstants.splitString+ToDoConstants.splitString)) {
			sb.append(toDoData.toString()+"\n");
			updateLists(toDoMBean,toDoData.toString(),"$",false);
		}
		Collections.sort(toDoMBean.getReviewTasks(), Collections.reverseOrder());



		toDoMBean.setSelectedDate(newlyAddedTaskDate);
		ToDoUtil.saveToFile(sb, toDoMBean);
		/*
		 * BufferedWriter writer = new BufferedWriter(new
		 * FileWriter(toDoMBean.getPlanType().equals(ToDoConstants.planTypeOfficial)?
		 * ToDoConstants.filePath:ToDoConstants.personalFilePath));
		 * writer.write(sb.toString()); writer.flush(); writer.close();
		 */
		reader.close();

	}

	public static void updateLists(ToDoMBean toDoMBean, String readLine, String newlyAddedTaskDate, boolean isAnyReviewDateSelected) {

		if(readLine.startsWith(ToDoUtil.prepareTodayDate())) {
			ToDoUtil.updateTasksList(toDoMBean,toDoMBean.getTodayTasks(), readLine);
		}else if(readLine.startsWith(newlyAddedTaskDate)) {
			ToDoUtil.updateTasksList(toDoMBean,toDoMBean.getReviewTasks(), readLine);
		}else {
			if(ToDoUtil.prepareTodayDate().compareTo(readLine.split(ToDoConstants.splitString)[0])>0 && !readLine.contains(ToDoConstants.splitString+"Completed"+ToDoConstants.splitString)) {
				ToDoUtil.updateTasksList(toDoMBean,toDoMBean.getPendingTasks(), readLine);
			}
		}
		if(!isAnyReviewDateSelected) {
			if(ToDoUtil.prepareTodayDate().compareTo(readLine.split(ToDoConstants.splitString)[0])<0 ) {
				ToDoUtil.updateTasksList(toDoMBean,toDoMBean.getReviewTasks(), readLine);
			}
		}
	}






}
