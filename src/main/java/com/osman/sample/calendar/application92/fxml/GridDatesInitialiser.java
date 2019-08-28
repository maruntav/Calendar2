package com.osman.sample.calendar.application92.fxml;

import java.time.LocalDate;

import com.osman.sample.calendar.application92.date.MonthInfo;

import javafx.scene.control.*;
import javafx.scene.layout.*;

public class GridDatesInitialiser {
	public static void addDaysToGrid(Pane monthsPane[], int month, int year) {
		addDayToGrid(monthsPane[0], month, year);
	}
	
	public static void addDayToGrid(Pane pane, int month, int year) {
		GridPane grid = (GridPane) pane.getChildren().get(0);
		MonthInfo monthInfo = new MonthInfo(month, year);
		LocalDate currentDay = monthInfo.getFirstMondayForMonthCalendar();
		boolean addSixthRow = currentDay.plusDays(34).getDayOfMonth() > 10;
		String cssPath = GridDatesInitialiser.class.getClassLoader().getResource("css/fxstyle.css").toExternalForm();
		for(int i = 7; i < 49; i++) {
			StackPane stackPane = (StackPane) grid.getChildren().get(i);
			stackPane.getStylesheets().add(cssPath);
			stackPane.getStyleClass().clear();
			
			Label label = (Label) stackPane.getChildren().get(0);
			label.getStylesheets().add(cssPath);
			label.getStyleClass().clear();
			
			if(i > 41 && !addSixthRow) {
				stackPane.getStyleClass().add("grid-stack-pane-sixth-row-empty");
				label.setText("");
			} else {
				label.setText(String.valueOf(currentDay.getDayOfMonth()));
				if(currentDay.getMonthValue() != month) {
					label.getStyleClass().add("label-different-month");
				}
			}
			currentDay = currentDay.plusDays(1);
		}
	}
}
