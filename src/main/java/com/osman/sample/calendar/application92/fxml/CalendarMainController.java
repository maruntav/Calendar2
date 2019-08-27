package com.osman.sample.calendar.application92.fxml;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class CalendarMainController {

    @FXML
    private Pane rootNode;

    @FXML
    private Rectangle prevMonthRect;

    @FXML
    private Polygon prevMonthTriangle;

    @FXML
    private Label monthLabel;

    @FXML
    private Rectangle nextMonthRect;

    @FXML
    private Polygon nextMonthTriangle;

    @FXML
    private Pane centerMonthPane;

    @FXML
    void onNextMonthButtonEnter(MouseEvent event) {
    	
    }

    @FXML
    void onNextMonthButtonExit(MouseEvent event) {

    }

    @FXML
    void onPreviousMonthButtonEnter(MouseEvent event) {
    	
    }

    @FXML
    void onPreviousMonthButtonExit(MouseEvent event) {

    }
    
    @FXML
    public void initialize() {
    	GridDatesInitialiser.addDaysToGrid(centerMonthPane, 8, 2019);
    }
}