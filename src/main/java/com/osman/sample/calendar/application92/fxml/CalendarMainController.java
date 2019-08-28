package com.osman.sample.calendar.application92.fxml;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

import java.time.LocalDateTime;

import com.sun.javafx.scene.control.skin.LabeledText;

public class CalendarMainController {

	@FXML
    private Pane rootNode;

    @FXML
    private Rectangle prevMonthRect;

    @FXML
    private Polygon prevMonthTriangle;

    @FXML
    private Label monthYearLabel;

    @FXML
    private Rectangle nextMonthRect;

    @FXML
    private Polygon nextMonthTriangle;

    @FXML
    private Pane centerMonthPane;
    
    private int currentMonth;
    private int currentYear;

    @FXML
    void onNextMonthButtonEnter(MouseEvent event) {

    }
    
    @FXML
    void onNextMonthButtonClick(MouseEvent event) {
    	setCurrentMonth(currentMonth < 12 ? currentMonth + 1 : 1);
    	if(currentMonth == 1) currentYear++;
    	refreshMonth();
    }

    @FXML
    void onNextMonthButtonExit(MouseEvent event) {
    	
    }
    
    @FXML
    void onPreviousMonthButtonClick(MouseEvent event) {

    }

    @FXML
    void onPreviousMonthButtonEnter(MouseEvent event) {

    }

    @FXML
    void onPreviousMonthButtonExit(MouseEvent event) {

    }

    @FXML
    void onStackPaneDayClick(MouseEvent event) {
    	String text;
    	if(event.getTarget() instanceof LabeledText) {
    		text = ((LabeledText) event.getTarget()).getText();
    	} else if(event.getTarget() instanceof Label) {
    		text = ((Label) event.getTarget()).getText();
    	} else {
    		text = ((Label)((StackPane) event.getTarget()).getChildren().get(0)).getText();
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
    	GridDatesInitialiser.addDaysToGrid(new Pane[] {centerMonthPane}, now.getMonthValue(), now.getYear());
    	MonthYearLabelUtils.initMonthLabel(monthYearLabel, now.getMonthValue(), now.getYear());
    	setCurrentMonth(now.getMonthValue());
    	setCurrentYear(now.getYear());
    }
    
    public void refreshMonth() {
    	GridDatesInitialiser.addDaysToGrid(new Pane[] {centerMonthPane}, currentMonth, currentYear);
    	MonthYearLabelUtils.initMonthLabel(monthYearLabel, currentMonth, currentYear);
    }
}