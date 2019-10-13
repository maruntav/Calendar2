package com.osman.sample.calendar.application92.fxml;

import java.net.URL;
import java.time.LocalTime;
import java.util.*;

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
    	if(noEmptyFields() && TimeValidation.timeIsValid(timeHourInput.getText(), timeMinuteInput.getText())) {
    		EditEvent editEvent = new EditEvent(new SQLiteConnection(false).getConnection());
        	editEvent.addEvent(eventNameInput.getText(), detailsInput.getText(), 
        			CurrentDateClick.CURRENT_DATE, 
        			LocalTime.of(Integer.valueOf(timeHourInput.getText()), Integer.valueOf(timeMinuteInput.getText())));
        	Arrays.asList(new TextInputControl[] {timeHourInput, timeMinuteInput, eventNameInput, detailsInput})
        				.stream()
        				.forEach(field -> field.clear());
        	editEvent = new EditEvent(new SQLiteConnection(false).getConnection());
        	ArrayList<Event> events = editEvent.getEventsForDay(CurrentDateClick.CURRENT_DATE);
        	CalendarShowEvents.showEvents((Pane)addEventRootPane.getParent(), CurrentDateClick.CURRENT_DATE, events, addEventRootPane);
        	
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
        List<TextInputControl> textFields = Arrays.asList(new TextInputControl[] {eventNameInput, timeHourInput, timeMinuteInput, detailsInput});
        timeFields.stream().forEach(input -> {
        	input.textProperty().addListener((observable, oldValue, newValue) -> {
        		if(!TimeValidation.hourOrMinuteValid(newValue)) {
        			input.setText(oldValue);
        		}
        	});
        });
        textFields.stream().forEach(input -> {
        	input.focusedProperty().addListener((observable, oldValue, newValue) -> {
        		if(newValue) {
        			TextFieldErrorBorder.removeRedBorder(input);
        		} else {
        			if(input.getText().isEmpty()) {
        				TextFieldErrorBorder.wrapTextFieldWithRedBorder(input);
        			}
        		}
        	});
        });
    }
    
    public boolean noEmptyFields() {
    	return !eventNameInput.getText().isEmpty() &&
    			!detailsInput.getText().isEmpty() &&
    			!timeHourInput.getText().isEmpty() &&
    			!timeHourInput.getText().isEmpty();
    }
}
