package com.todo.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.todo.constants.ToDoConstants;
import com.todo.helper.AddTasksDisplayHelper;
import com.todo.helper.PendingTasksDisplayHelper;
import com.todo.mbean.ToDoMBean;
import com.todo.model.DateToTasksMapper;
import com.todo.model.PriorityToTasksMapper;
import com.todo.model.Task;

import edu.emory.mathcs.backport.java.util.Collections;

public class ToDoUtil {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//getLastWeekDates().forEach(System.out::println);
		LocalDate date = LocalDate.of(2022, 8, 22);
		System.out.println(date.toString());
		for(int i=0;i<10;i++) {
		date = date.plusDays(1);
		System.out.println(date.toString());
		System.out.println(date.get(ChronoField.DAY_OF_MONTH));
		}
	}
	public static Map<String, String> priority2StyleMap = new HashMap<String, String>();
	
	static {
		priority2StyleMap.put(ToDoConstants.priorityHigh, ToDoConstants.priorityHighStyle);
		priority2StyleMap.put(ToDoConstants.priorityMedium, ToDoConstants.priorityMediumStyle);
		priority2StyleMap.put(ToDoConstants.priorityLow, ToDoConstants.priorityLowStyle);
		priority2StyleMap.put(ToDoConstants.priorityComplete, ToDoConstants.priorityCompletedStyle);
	}
	

	
	public static void retrieveData(ToDoMBean toDoMBean) throws IOException {
		
		BufferedReader reader=getReader(toDoMBean);

		String readLine = "";
		while((readLine = reader.readLine())!=null) {
			AddTasksDisplayHelper.updateLists(toDoMBean,readLine,"null",false);
		}
		
		PendingTasksDisplayHelper.updateMBeanWithPendingTasks(toDoMBean);
		reader.close();
		Collections.sort(toDoMBean.getReviewTasks(), Collections.reverseOrder());
	
	}
	public static BufferedReader getReader(ToDoMBean toDoMBean) throws IOException {
		String fileName = toDoMBean.getPlanType().equals(ToDoConstants.planTypeOfficial)?ToDoConstants.filePath:ToDoConstants.personalFilePath;
		File file = new File(fileName);
		if(!file.exists()) {
			if(file.createNewFile()) {
				System.out.println("new file created");
			}

		}

		//System.out.println(file.getAbsolutePath());
		return new BufferedReader(new FileReader(file));
	}
	public static String getFormattedDate(Date toDoDate) {
		SimpleDateFormat sdf = new SimpleDateFormat(ToDoConstants.datePattern);
		String formatedDate = "";
		if(toDoDate!=null) {
		 formatedDate = sdf.format(toDoDate);
		}
		return formatedDate;
	}

	public static void initTodayTasks(ToDoMBean toDoMBean) {
		
		 toDoMBean.setTodayTasks(new ArrayList<DateToTasksMapper>());
		 toDoMBean.setReviewTasks(new ArrayList<DateToTasksMapper>());
		 toDoMBean.setPendingTasks(new ArrayList<DateToTasksMapper>());
	}
	



	public static List<String> getLastWeekDates() {
		LocalDate date = LocalDate.now(); 
		List<String> lastWeekDates = new ArrayList<String>();
		for(int i = 0; i < 7; i++) {
			lastWeekDates.add(date.plusDays(-i-1).format(DateTimeFormatter.ofPattern(ToDoConstants.datePattern)).toString());
		}
		return lastWeekDates;
	}
	public static List<String> getDatesListForRecurringTasks() {
		LocalDate date = LocalDate.now(); 
		List<String> lastWeekDates = new ArrayList<String>();
		for(int i = 0; i < 7; i++) {
			lastWeekDates.add(date.plusDays(-i-1).format(DateTimeFormatter.ofPattern(ToDoConstants.datePattern)).toString());
		}
		return lastWeekDates;
	}

	public static String prepareTodayDate() {
		Date localDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(ToDoConstants.datePattern);
		return sdf.format(localDate);
	}

	public static void sortAllLists(ToDoMBean toDoMBean) {
		Collections.sort(toDoMBean.getTodayTasks());
		Collections.sort(toDoMBean.getPendingTasks());
		Collections.sort(toDoMBean.getReviewTasks());
	}
	public static void updateTasksList(ToDoMBean toDoMBean,List<DateToTasksMapper> inputList, String readLine) {
		String [] taskDetails = readLine.split(ToDoConstants.splitString);
		String date = taskDetails[0];
		String priority = taskDetails[1];
		String taskName = taskDetails[2];

		boolean dateMatchFound = false;
		boolean priorityMatchFound = false;
		boolean taskNameMatchFound = false;

		for(DateToTasksMapper d2t: inputList) {

			if(d2t.getDate().equals(date)) { 
				dateMatchFound = true;
				List<PriorityToTasksMapper> p2tList = d2t.getP2TMap();

				for(PriorityToTasksMapper p2t: p2tList) {
					if(p2t.getPriority().equals(priority)) {
						priorityMatchFound = true;

						List<Task> tasks = p2t.getTasks();

						for(Task task: tasks) {
							if(task.getTaskLabel().equals(taskName)) {
								taskNameMatchFound = true;
								break;
							}
						}
						if(!taskNameMatchFound) {
							Task task = new Task();
							task.setTaskLabel(taskName);
							task.setTaskValue(date+ToDoConstants.splitString+priority+ToDoConstants.splitString+taskName);
							tasks.add(task);
							p2t.setTasks(tasks);
						}

						break;
					}
				}
				if(!priorityMatchFound) {
					PriorityToTasksMapper priorityToTasksMapper = new PriorityToTasksMapper();
					priorityToTasksMapper.setPriority(priority);
					priorityToTasksMapper.setStyle(priority2StyleMap.get(priority));
					List<Task> tasks = new ArrayList<>();
					Task task = new Task();
					task.setTaskLabel(taskName);
					task.setTaskValue(date+ToDoConstants.splitString+priority+ToDoConstants.splitString+taskName);
					tasks.add(task);
					priorityToTasksMapper.setTasks(tasks);
					p2tList.add(priorityToTasksMapper);
					Collections.sort(p2tList);
				}

				break;
			}
		}
		if(!dateMatchFound) {

			DateToTasksMapper dateToTasksMapper= new DateToTasksMapper();

			dateToTasksMapper.setDate(date);
			dateToTasksMapper.setDateStyle(ToDoConstants.dateStyle);
			List<PriorityToTasksMapper> p2tList = new ArrayList<PriorityToTasksMapper>();
			PriorityToTasksMapper priorityToTasksMapper = new PriorityToTasksMapper();
			priorityToTasksMapper.setPriority(priority);
			priorityToTasksMapper.setStyle(priority2StyleMap.get(priority));
			List<Task> tasks = new ArrayList<>();
			Task task = new Task();
			task.setTaskLabel(taskName);
			task.setTaskValue(date+ToDoConstants.splitString+priority+ToDoConstants.splitString+taskName);
			tasks.add(task);
			priorityToTasksMapper.setTasks(tasks);
			p2tList.add(priorityToTasksMapper);
			Collections.sort(p2tList);
			dateToTasksMapper.setP2TMap(p2tList);

			inputList.add(dateToTasksMapper);
			
			
		}
		Collections.sort(inputList);
	}
	public static void initCheckMap(ToDoMBean toDoMBean) {
		populateMap(toDoMBean,toDoMBean.getTodayTasks());
		populateMap(toDoMBean,toDoMBean.getPendingTasks());
		populateMap(toDoMBean,toDoMBean.getReviewTasks());
	
	}
	public static void populateMap(ToDoMBean toDoMBean,List<DateToTasksMapper> givenList) {
		
		for(DateToTasksMapper d2t : givenList) {
			for(PriorityToTasksMapper p2t : d2t.getP2TMap()) {
				for(Task task:p2t.getTasks()) {
					toDoMBean.getCheckMap().put(task.getTaskValue(),false);
				}
			}
		}
		
		
	}
	
	public static Date parseStringToDateObj(String date) throws Exception {
		return (new SimpleDateFormat(ToDoConstants.datePattern)).parse(date);
	}
	
	public static boolean saveToFile(StringBuffer sb, ToDoMBean toDoMBean) {
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(toDoMBean.getPlanType().equals(ToDoConstants.planTypeOfficial)?ToDoConstants.filePath:ToDoConstants.personalFilePath));
			writer.write(sb.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		finally{
			try {
				writer.flush();
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		return true;
	}

}
