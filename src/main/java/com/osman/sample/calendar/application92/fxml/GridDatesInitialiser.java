package com.osman.sample.calendar.application92.fxml;

import java.time.LocalDate;

import com.osman.sample.calendar.application92.date.MonthInfo;

import javafx.scene.control.*;
import javafx.scene.layout.*;

public class GridDatesInitialiser {
	public static void addDaysToGrid(Pane monthGrid, int month, int year) {
		GridPane grid = (GridPane) monthGrid.getChildren().get(0);
		MonthInfo monthInfo = new MonthInfo(month, year);
		LocalDate currentDay = monthInfo.getFirstMondayForMonthCalendar();
		int firstDayMonth = currentDay.getMonthValue();
		int currentMonth = currentDay.getDayOfMonth() == 1 ? firstDayMonth : firstDayMonth + 1;
//		boolean rowLimit = currentDay.plusDays(35).getDayOfMonth();
		for(int row = 1; row < 8; row++) {
			for(int col = 1; col < 7; col++) {
				getLabelInGrid(grid, row, col).setText(String.valueOf(currentDay.getDayOfMonth()));
				currentDay = currentDay.plusDays(1);
					
			}
		}
	}
	
	public static Label getLabelInGrid(GridPane grid, int row, int col) {
		StackPane stackPane = (StackPane) grid.getChildren().get((6 * row) +  (col));
		return (Label) stackPane.getChildren().get(0);
//		label.getStylesheets().add(GridDatesInitialiser.class.getClassLoader().getResource("css/fxstyle.css").toExternalForm());
//		label.getStyleClass().add("label-different-month");
	}
}
