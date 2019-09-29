package com.osman.sample.calendar.application92.fxml;

import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.util.*;
import javafx.scene.*;

import java.awt.Checkbox;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.osman.sample.calendar.application92.dto.Event;

import com.osman.sample.calendar.application92.date.*;
import com.osman.sample.calendar.application92.sqlite.EditEvent;
import com.osman.sample.calendar.application92.sqlite.SQLiteConnection;
import com.sun.javafx.scene.control.skin.LabeledText;
import javafx.scene.control.Labeled;
import javafx.scene.control.ScrollPane;

public class CalendarMainController {

	@FXML
    private Pane rootNode;

    @FXML
    private Rectangle prevMonthRect;

    @FXML
    private Polygon prevMonthTriangle;

    @FXML
    private Label monthYearLabel;

    public Polygon getPrevMonthTriangle() {
		return prevMonthTriangle;
	}

	public void setPrevMonthTriangle(Polygon prevMonthTriangle) {
		this.prevMonthTriangle = prevMonthTriangle;
	}

	public Polygon getNextMonthTriangle() {
		return nextMonthTriangle;
	}

	public void setNextMonthTriangle(Polygon nextMonthTriangle) {
		this.nextMonthTriangle = nextMonthTriangle;
	}

	@FXML
    private Rectangle nextMonthRect;

    @FXML
    private Polygon nextMonthTriangle;
    
    @FXML
    private Pane threeMonthsWrapperPane;

    @FXML
    private Pane centerMonthPane;
    
    @FXML
    private Pane monthBeforePane;

    @FXML
    private Pane monthAfterPane;
    
    private ScrollPane showEventsPane;
    private Pane addEventPane;
    private int currentMonth;
    private int currentYear;
    String showEventsCss;
    
    public CalendarMainController() {
    	try {
			addEventPane = FXMLLoader.load(getClass().getResource("/view/add_event_layout.fxml"));
			showEventsPane = FXMLLoader.load(getClass().getResource("/view/show_events_layout.fxml"));
			showEventsCss = getClass().getResource("/css/show_events_style.css").toExternalForm();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    public Pane getCenterMonthPane() {
    	return centerMonthPane;
    }
    
    public Label getMonthYearLabel() {
    	return monthYearLabel;
    }
     
    @FXML
    void onNextMonthButtonEnter(MouseEvent event) {
    }
    
    @FXML
    void onNextMonthButtonClick(MouseEvent event) {
    	moveMonth("next");
    }

    @FXML
    void onNextMonthButtonExit(MouseEvent event) {
    	
    }
    
    @FXML
    void onPreviousMonthButtonClick(MouseEvent event) {
    	moveMonth("previous");
    }

    @FXML
    void onPreviousMonthButtonEnter(MouseEvent event) {

    }

    @FXML
    void onPreviousMonthButtonExit(MouseEvent event) {
    	
    }

    @FXML
    void onStackPaneDayClick(MouseEvent mouseEvent) {
    	Label label;
    	StackPane stackPane;
    	if(mouseEvent.getTarget() instanceof LabeledText) {
    		label = (Label)((LabeledText) mouseEvent.getTarget()).getParent();
    	} else if(mouseEvent.getTarget() instanceof Label) {
    		label = (Label) mouseEvent.getTarget();
    	} else {
    		if(((StackPane) mouseEvent.getTarget()).getStyleClass().toString() == "grid-stack-pane-sixth-row-empty") {
    			return;
    		}
    		label = (Label)((StackPane) mouseEvent.getTarget()).getChildren().get(0);
    	}
    	
    	LocalDate date;
    	if(label.getStyleClass().contains("label-different-month")) {
    		boolean previousMonth = label.getStyleClass().contains("label-previous-month");
    		int month = previousMonth ? currentMonth - 1 : currentMonth + 1;
    		date = LocalDate.of(currentYear, month, Integer.valueOf(label.getText()));
    	} else {
    		date = LocalDate.of(currentYear, currentMonth, Integer.valueOf(label.getText()));
    	}
    	CurrentDateClick.CURRENT_DATE = date;
    	EditEvent editEvent = new EditEvent(new SQLiteConnection(false).getConnection());
    	ArrayList<Event> events = editEvent.getEventsForDay(date);
    	if(!events.isEmpty()) {
    		CalendarShowEvents.showEvents(rootNode, date, events);
    	} else {
    		rootNode.getChildren().add(addEventPane);
    	}
    }
    
    public void setCurrentMonth(int currentMonth) {
    	this.currentMonth = currentMonth;
    }
    
    public void setCurrentYear(int currentYear) {
    	this.currentYear = currentYear;
    }
    
    @FXML
    public void initialize() {
    	LocalDateTime now = LocalDateTime.now();
    	setCurrentMonth(now.getMonthValue());
    	setCurrentYear(now.getYear());
    	GridDatesInitialiser.addDayToGrid(centerMonthPane, currentMonth, currentYear);
    	MonthYearLabelUtils.initMonthLabel(monthYearLabel, now.getMonthValue(), now.getYear());
    }
    
    public void moveMonth(String direction) {
    	Pane updatePane = direction == "next" ? monthBeforePane : monthAfterPane;
    	GridDatesInitialiser.addDayToGrid(updatePane, currentMonth, currentYear);
    	threeMonthsWrapperPane.setTranslateX(direction == "next" ? 650 : -650);
    	updateMonthAndYear(direction);
    	GridDatesInitialiser.addDayToGrid(centerMonthPane, currentMonth, currentYear);
    	translateMonthWrapper(direction == "next" ? "right" : "left");
    	MonthYearLabelUtils.initMonthLabel(monthYearLabel, currentMonth, currentYear);
    }
    
    public void updateMonthAndYear(String direction) {
    	MonthInfo monthInfo = new MonthInfo(currentMonth, currentYear);
    	LocalDate nextDate = direction == "next" ? monthInfo.getNextMonth() : monthInfo.getPreviousMonth();
    	setCurrentMonth(nextDate.getMonthValue());
    	setCurrentYear(nextDate.getYear());
    }
    
    public void translateMonthWrapper(String direction) {
    	TranslateTransition transition = new TranslateTransition(Duration.millis(500), threeMonthsWrapperPane);
    	transition.setByX(direction == "left" ? 650 : -650);
    	transition.play();
    }
}