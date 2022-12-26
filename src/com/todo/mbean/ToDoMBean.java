package com.todo.mbean;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.IntStream;

import javax.faces.event.PhaseId;
import javax.faces.event.ValueChangeEvent;

import com.todo.constants.ToDoConstants;
import com.todo.helper.AddTasksDisplayHelper;
import com.todo.helper.ReviewTasksDisplayHelper;
import com.todo.model.DateToTasksMapper;
import com.todo.model.ToDoData;
import com.todo.util.ToDoUtil;

import edu.emory.mathcs.backport.java.util.Collections;

public class ToDoMBean {

	private ToDoData toDoData = new ToDoData();
	private ToDoData toDoData2bEdited = new ToDoData();

	private List<DateToTasksMapper> todayTasks = new ArrayList<DateToTasksMapper>();
	private List<DateToTasksMapper> reviewTasks = new ArrayList<DateToTasksMapper>();	
	private List<DateToTasksMapper> pendingTasks = new ArrayList<DateToTasksMapper>();

	private String [] selectedTodayTasks;
	private String [] selectedPendingTasks;
	private String [] selectedReviewTasks;

	private String todayDate = "";
	private String selectedDate = "";
	private String toDoTaskType = "ott";
	private String includeWeekends = "No";
	private String recurringType = "daily";

	private Date reviewDate; 
	private Date moveToDate;
	private Date fromDate = ToDoUtil.parseStringToDateObj(getTodayDate());
	private Date tillDate;

	private String errorMessage = "";
	private String errorMessageAT = "";
	private String errorMessageRT = "";

	private String developedBy = "Munwar Tadipatri";
	private String planType = ToDoConstants.planTypeOfficial;
	
	private boolean displayFutureTasksButton = false;
	private String [] forWeekDays = {""};
	private String [] selectedDaysOfMonth = {""};
	private HashMap<Integer, Integer> daysOfMonth = new HashMap<Integer, Integer>();
	
	
	public String[] getSelectedDaysOfMonth() {
		return selectedDaysOfMonth;
	}

	public void setSelectedDaysOfMonth(String[] selectedDaysOfMonth) {
		this.selectedDaysOfMonth = selectedDaysOfMonth;
	}

	public HashMap<Integer, Integer> getDaysOfMonth() {
		return daysOfMonth;
	}

	public void setDaysOfMonth(HashMap<Integer, Integer> daysOfMonth) {
		this.daysOfMonth = daysOfMonth;
	}

	public String[] getForWeekDays() {
		return forWeekDays;
	}

	public void setForWeekDays(String[] forWeekDays) {
		this.forWeekDays = forWeekDays;
	}

	public String getRecurringType() {
		return recurringType;
	}

	public void setRecurringType(String recurringType) {
		this.recurringType = recurringType;
	}


	public boolean isDisplayFutureTasksButton() {
		return displayFutureTasksButton;
	}

	public void setDisplayFutureTasksButton(boolean displayFutureTasksButton) {
		this.displayFutureTasksButton = displayFutureTasksButton;
	}

	public String getIncludeWeekends() {
		return includeWeekends;
	}

	public void setIncludeWeekends(String includeWeekends) {
		this.includeWeekends = includeWeekends;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getTillDate() {
		return tillDate;
	}

	public void setTillDate(Date tillDate) {
		this.tillDate = tillDate;
	}

	public String getToDoTaskType() {
		return toDoTaskType;
	}

	public void setToDoTaskType(String toDoTaskType) {
		this.toDoTaskType = toDoTaskType;
	}

	public String getPlanType() {
		return planType;
	}

	public void setPlanType(String planType) {
		this.planType = planType;
	}

	public String getDevelopedBy() {
		return developedBy;
	}

	public void setDevelopedBy(String developedBy) {
		this.developedBy = developedBy;
	}

	public String getErrorMessageAT() {
		return errorMessageAT;
	}

	public void setErrorMessageAT(String errorMessageAT) {
		this.errorMessageAT = errorMessageAT;
	}

	public String getErrorMessageRT() {
		return errorMessageRT;
	}

	public void setErrorMessageRT(String errorMessageRT) {
		this.errorMessageRT = errorMessageRT;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public ToDoData getToDoData2bEdited() {
		return toDoData2bEdited;
	}

	public void setToDoData2bEdited(ToDoData toDoData2bEdited) {
		this.toDoData2bEdited = toDoData2bEdited;
	}

	public String[] getSelectedTodayTasks() {
		return selectedTodayTasks;
	}

	public void setSelectedTodayTasks(String[] selectedTodayTasks) {
		this.selectedTodayTasks = selectedTodayTasks;
	}

	public String[] getSelectedPendingTasks() {
		return selectedPendingTasks;
	}

	public void setSelectedPendingTasks(String[] selectedPendingTasks) {
		this.selectedPendingTasks = selectedPendingTasks;
	}

	public String[] getSelectedReviewTasks() {
		return selectedReviewTasks;
	}

	public void setSelectedReviewTasks(String[] selectedReviewTasks) {
		this.selectedReviewTasks = selectedReviewTasks;
	}

	public Date getMoveToDate() {
		return moveToDate;
	}

	public void setMoveToDate(Date moveToDate) {
		this.moveToDate = moveToDate;
	}

	public List<DateToTasksMapper> getPendingTasks() {
		return pendingTasks;
	}

	public void setPendingTasks(List<DateToTasksMapper> pendingTasks) {
		this.pendingTasks = pendingTasks;
	}


	public Date getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}


	public String getTodayDate() {
		return ToDoUtil.prepareTodayDate();
	}

	public void setTodayDate(String todayDate) {
		this.todayDate = todayDate;
	}

	public ToDoMBean() throws Exception {
		loadPage();		
	}

	public ToDoData getToDoData() {
		return toDoData;
	}

	public void setToDoData(ToDoData toDoData) {
		this.toDoData = toDoData;
	}

	public String getSelectedDate() {
		return selectedDate;
	}

	public void setSelectedDate(String selectedDate) {
		this.selectedDate = selectedDate;
	}

	public List<DateToTasksMapper> getTodayTasks() {
		return todayTasks;
	}

	public void setTodayTasks(List<DateToTasksMapper> todayTasks) {
		this.todayTasks = todayTasks;
	}

	public List<DateToTasksMapper> getReviewTasks() {
		return reviewTasks;
	}

	public void setReviewTasks(List<DateToTasksMapper> reviewTasks) {
		this.reviewTasks = reviewTasks;
	}

	//static Logger log = LogManager.getLogger(ToDoMBean.class.getName());  
	
	public void addTask() throws Exception {
	      //log.fatal("Hello this is a debug message");  
	      //log.info("Hello this is an info message");  
		clearErrorMessages();
		if(this.toDoData.getToDoTaskName().equals("") 
				|| this.toDoData.getToDoTaskPriority().equals("") 
				|| this.toDoData.getToDoDate() == null) {
			this.errorMessageAT = "Task Name, Date and Priority are required fields. Please enter required details.";
			return;
		}
		if(this.toDoData.getToDoTaskName().contains(ToDoConstants.splitString) ) {
			this.errorMessageAT = "Character sequence "+ToDoConstants.splitString+" is not allowed in Task Name field";
			return;
		}
		if(this.getToDoTaskType().equals("ott")) {
			addUpdateTask();
		}else {
			String fromDateValue = ToDoUtil.getFormattedDate(fromDate);
			String tillDateValue = ToDoUtil.getFormattedDate(tillDate);
			if(tillDateValue.compareTo(fromDateValue)>=0) {
				String [] fromDateArray = fromDateValue.split("-");
				LocalDate date = LocalDate.of(Integer.parseInt(fromDateArray[0]), Integer.parseInt(fromDateArray[1]), Integer.parseInt(fromDateArray[2]));
				if(this.recurringType.equals("daily")) {
					do {
						if(date.get(ChronoField.DAY_OF_WEEK)==6 || date.get(ChronoField.DAY_OF_WEEK)==7) {
							if("No".equals(this.includeWeekends)) {
								date = date.plusDays(1);
								continue;
							}
						}
						this.toDoData.setToDoDate(ToDoUtil.parseStringToDateObj(date.toString()));
						addUpdateTask(toDoData);
						date = date.plusDays(1);

					}while(date.toString().compareTo(tillDateValue)<=0);
										
				}else if(this.recurringType.equals("weekly")) {
					do {
						for(String forWeekDay : forWeekDays) {
							if(date.get(ChronoField.DAY_OF_WEEK)==Integer.parseInt(forWeekDay)) {
								this.toDoData.setToDoDate(ToDoUtil.parseStringToDateObj(date.toString()));
								addUpdateTask(toDoData);
								
								break;
							}
						}
						date = date.plusDays(1);
					}while(date.toString().compareTo(tillDateValue)<=0);
										
				}else if(this.recurringType.equals("monthly")) {

					do {
						for(String selectedDayOfMonth : selectedDaysOfMonth) {
							if(date.get(ChronoField.DAY_OF_MONTH)==Integer.parseInt(selectedDayOfMonth)) {
								this.toDoData.setToDoDate(ToDoUtil.parseStringToDateObj(date.toString()));
								addUpdateTask(toDoData);
								
								break;
							}
						}
						date = date.plusDays(1);
					}while(date.toString().compareTo(tillDateValue)<=0);
					
				}
				fromDate = ToDoUtil.parseStringToDateObj(getTodayDate());
				resetDataAfterAddUpdate();
			}else {
				this.errorMessageAT = "Till Date cannot be less than From Date";
			}
		}
		
	}
	public void addUpdateTask() throws Exception {

		AddTasksDisplayHelper.addTask(this, toDoData);
		resetDataAfterAddUpdate();
	}
	public void addUpdateTask(ToDoData toDoData) throws Exception {

		AddTasksDisplayHelper.addTask(this, toDoData);

	}
	public void resetDataAfterAddUpdate() throws Exception{
		this.setToDoData(new ToDoData());
		this.toDoData.setToDoDate(ToDoUtil.parseStringToDateObj(getTodayDate()));
		this.checkMap.put(this.getToDoData2bEdited().toString(), false);
		this.setToDoData2bEdited(new ToDoData());
		this.toDoTaskType="ott";
		this.recurringType="daily";
		this.forWeekDays = new String [1];
		forWeekDays[0]="";
	}

	public void reviewTasksAction() throws Exception {
		clearErrorMessages();
		if(this.getReviewDate() == null) {
			this.errorMessageRT = "Please enter/select Review Date.";
			return;
		}
		ToDoUtil.initTodayTasks(this);
		this.setSelectedDate(ToDoUtil.getFormattedDate(reviewDate));
		ReviewTasksDisplayHelper.reviewOnSelectingADate(this, ToDoUtil.getFormattedDate(reviewDate));
		this.toDoData.setToDoDate(ToDoUtil.parseStringToDateObj(getTodayDate()));
		this.setDisplayFutureTasksButton(true);
	}

	public void editSelected() throws Exception {
		clearErrorMessages();
		if("".equals(getSelected())) {
			this.errorMessage = "Please select a task to edit";
			return;
		}		
		String [] selectedValues = getSelected().split("::");
		if(selectedValues.length==1) {
			String [] selectedValue= selectedValues[0].split(ToDoConstants.splitString);
			ToDoData todoData = new ToDoData();
			todoData.setToDoDate(ToDoUtil.parseStringToDateObj(selectedValue[0]));
			todoData.setToDoTaskPriority(selectedValue[1]);
			todoData.setToDoTaskName(selectedValue[2]);
			toDoData2bEdited.setToDoDate(ToDoUtil.parseStringToDateObj(selectedValue[0]));
			toDoData2bEdited.setToDoTaskPriority(selectedValue[1]);
			toDoData2bEdited.setToDoTaskName(selectedValue[2]);
			this.setToDoData(todoData);
		}else {
			this.errorMessage = "You can Edit only one Task at a time";
		}
	}

	public void markCompleted() throws Exception {
		clearErrorMessages();
		if("".equals(getSelected())) {
			this.errorMessage = "Please select a task to Mark Complete";
			return;
		}
		String [] selectedValues = getSelected().split("::");
		for(String eachValue : selectedValues) {
			String [] selectedValue= eachValue.split(ToDoConstants.splitString);
			ToDoData todoData = new ToDoData();
			todoData.setToDoDate(ToDoUtil.parseStringToDateObj(selectedValue[0]));
			todoData.setToDoTaskPriority("Completed");
			todoData.setToDoTaskName(selectedValue[2]);
			toDoData2bEdited.setToDoDate(ToDoUtil.parseStringToDateObj(selectedValue[0]));
			toDoData2bEdited.setToDoTaskPriority(selectedValue[1]);
			toDoData2bEdited.setToDoTaskName(selectedValue[2]);
			this.setToDoData(todoData);
			addUpdateTask();
		}
		this.toDoData.setToDoDate(ToDoUtil.parseStringToDateObj(getTodayDate()));
	}

	public void removeSelected() throws Exception {
		clearErrorMessages();
		if("".equals(getSelected())) {
			this.errorMessage = "Please select a task to Delete";
			return;
		}
		String [] selectedValues = getSelected().split("::");
		for(String eachValue : selectedValues) {
			String [] selectedValue= eachValue.split(ToDoConstants.splitString);
			ToDoData todoData = new ToDoData();
			todoData.setToDoDate(ToDoUtil.parseStringToDateObj(selectedValue[0]));
			todoData.setToDoTaskPriority("");
			todoData.setToDoTaskName("");
			toDoData2bEdited.setToDoDate(ToDoUtil.parseStringToDateObj(selectedValue[0]));
			toDoData2bEdited.setToDoTaskPriority(selectedValue[1]);
			toDoData2bEdited.setToDoTaskName(selectedValue[2]);
			this.setToDoData(todoData);
			addUpdateTask();
		}
		this.toDoData.setToDoDate(ToDoUtil.parseStringToDateObj(getTodayDate()));
	}


	public void moveTasksToDate() throws Exception {
		clearErrorMessages();
		if("".equals(getSelected())) {
			this.errorMessage = "Please select a task to Move";
			return;
		}
		if(this.getMoveToDate() == null) {
			this.errorMessage = "Please enter/select Move To Date.";
			return;
		}
		String [] selectedValues = getSelected().split("::");
		for(String eachValue : selectedValues) {
			String [] selectedValue= eachValue.split(ToDoConstants.splitString);
			ToDoData todoData = new ToDoData();
			todoData.setToDoDate(this.moveToDate);
			todoData.setToDoTaskPriority(selectedValue[1]);
			todoData.setToDoTaskName(selectedValue[2]);
			toDoData2bEdited.setToDoDate(ToDoUtil.parseStringToDateObj(selectedValue[0]));
			toDoData2bEdited.setToDoTaskPriority(selectedValue[1]);
			toDoData2bEdited.setToDoTaskName(selectedValue[2]);
			this.setToDoData(todoData);
			addUpdateTask();
		}
		this.moveToDate = null;

	}
	public void unselectAll() throws Exception {
		clearErrorMessages();
		ToDoUtil.initCheckMap(this);
	}
	private Map<String,Boolean> checkMap = new HashMap<String,Boolean>();



	public Map<String, Boolean> getCheckMap() {
		return checkMap;
	}

	public void setCheckMap(Map<String, Boolean> checkMap) {
		this.checkMap = checkMap;
	}

	public String getSelected() {
		String result = "";
		for (Entry<String,Boolean> entry : checkMap.entrySet()) {
			if (entry.getValue()) {
				if(!result.equals("")) {
					result = result +"::"+ entry.getKey(); 
				}else {
					result = entry.getKey(); 
				}
			}           
		}
		return result;
	}
	public void clearErrorMessages() {
		this.errorMessage = "";
		this.errorMessageAT = "";
		this.errorMessageRT = "";
	}

	public void planTypeListener(ValueChangeEvent event) throws Exception {

		if (PhaseId.ANY_PHASE.equals(event.getPhaseId())) {
			event.setPhaseId(PhaseId.UPDATE_MODEL_VALUES);
			event.queue();
		} else if (PhaseId.UPDATE_MODEL_VALUES.equals(event.getPhaseId())) {
			if (event.getNewValue() != null && !"".equals(event.getNewValue().toString())) {
				this.setPlanType(event.getNewValue().toString());
				loadPage();
			}
		}
	}
	public void loadPage() throws Exception {
		todayTasks = new ArrayList<DateToTasksMapper>();
		reviewTasks = new ArrayList<DateToTasksMapper>();	
		pendingTasks = new ArrayList<DateToTasksMapper>();
		clearErrorMessages();
		ToDoUtil.retrieveData(this);
		ToDoUtil.initCheckMap(this);
		this.toDoData.setToDoDate(ToDoUtil.parseStringToDateObj(getTodayDate()));
		Collections.sort(reviewTasks, Collections.reverseOrder());
		this.setDisplayFutureTasksButton(false);
		IntStream.rangeClosed(1, 31).forEach(n->this.daysOfMonth.put(n,n));
		
	}

}
