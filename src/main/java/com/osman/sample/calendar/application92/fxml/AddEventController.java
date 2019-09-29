package com.osman.sample.calendar.application92.fxml;

import java.net.URL;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.*;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import com.osman.sample.calendar.application92.date.*;
import com.osman.sample.calendar.application92.dto.Event;
import com.osman.sample.calendar.application92.sqlite.EditEvent;
import com.osman.sample.calendar.application92.sqlite.SQLiteConnection;

public class AddEventController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Pane addEventRootPane;
    
    @FXML
    private TextField eventNameInput;

    @FXML
    private TextField timeHourInput;

    @FXML
    private TextField timeMinuteInput;

    @FXML
    private TextArea detailsInput;

    @FXML
    void addEventButtonClick(MouseEvent event) {
    	if(noEmptyFields() && timeIsValid(timeHourInput.getText(), timeMinuteInput.getText())) {
    		EditEvent editEvent = new EditEvent(new SQLiteConnection(false).getConnection());
        	editEvent.addEvent(eventNameInput.getText(), detailsInput.getText(), 
        			CurrentDateClick.CURRENT_DATE, 
        			LocalTime.of(Integer.valueOf(timeHourInput.getText()), Integer.valueOf(timeMinuteInput.getText())));
//        	Arrays.asList(new TextInputControl[] {timeHourInput, timeMinuteInput, eventNameInput, detailsInput})
//        				.stream()
//        				.forEach(field -> field.clear());
        	editEvent = new EditEvent(new SQLiteConnection(false).getConnection());
        	ArrayList<Event> events = editEvent.getEventsForDay(CurrentDateClick.CURRENT_DATE);
        	CalendarShowEvents.showEvents((Pane)addEventRootPane.getParent(), CurrentDateClick.CURRENT_DATE, events);
    	} else {
    		//display message
    	}
    }
    
    @FXML
    void exitButtonClick(MouseEvent event) {
    	((Pane)addEventRootPane.getParent()).getChildren().remove(addEventRootPane);
    }

    @FXML
    void initialize() {
        assert addEventRootPane != null : "fx:id=\"addEventRootPane\" was not injected: check your FXML file 'add_event_layout.fxml'.";
        List<TextField> timeFields = Arrays.asList(new TextField[] {timeHourInput, timeMinuteInput});
        timeFields.stream().forEach(input -> {
        	input.textProperty().addListener((observable, oldValue, newValue) -> {
        		if(!hourOrMinuteValid(newValue)) {
        			input.setText(oldValue);
        		}
        	});
        });
    }
    
    public boolean hourOrMinuteValid(String newValue) {
    	if(newValue.length() > 2) {
    		return false;
    	} else if(newValue.length() == 0) {
    		return true;
    	}
    	return !Arrays.asList(newValue.split("")).stream()
    			.filter(str -> !Character.isDigit(str.charAt(0))).findFirst().isPresent();
    }
    
    public boolean noEmptyFields() {
    	return !eventNameInput.getText().isEmpty() &&
    			!detailsInput.getText().isEmpty() &&
    			!timeHourInput.getText().isEmpty() &&
    			!timeHourInput.getText().isEmpty();
    }
    
    public boolean timeIsValid(String hour, String minute) {
    	int hourVal = Integer.valueOf(hour);
    	int minuteVal = Integer.valueOf(minute);
    	if(hourVal < 0 || hourVal > 23)
    		return false;
    	else if(minuteVal < 0 || minuteVal > 59)
    		return false;
    	return true;
    }
}
