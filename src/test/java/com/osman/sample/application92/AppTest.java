package com.osman.sample.application92;

import static org.junit.Assert.assertEquals;

import java.util.Optional;
import java.util.concurrent.TimeoutException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

import com.osman.sample.calendar.application92.App;
import com.osman.sample.calendar.application92.fxml.CalendarMainController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.*;
import com.osman.sample.calendar.application92.date.MonthInfo;;

public class AppTest extends ApplicationTest {
	CalendarMainController controller;
	
	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getClassLoader().getResource("view/cal_layout.fxml"));
		Pane root = (Pane) fxmlLoader.load();
		this.controller = (CalendarMainController) fxmlLoader.getController();
		
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		stage.toFront();
//		super.start(stage);
	}

	@Before
	public void setUp() {
		
	}
	
	@After
	public void tearDown() {
		
	  try {
		FxToolkit.hideStage();
	} catch (TimeoutException e) {
		e.printStackTrace();
	}
	  release(new KeyCode[]{});
	  release(new MouseButton[]{});
	}
	
	@Test
	public void checkIfLabelDisplaysDate() {
		MonthInfo monthInfo = new MonthInfo();
		String expected = monthInfo.getMonthName() + " " + monthInfo.getYear();
		assertEquals(expected, controller.getMonthYearLabel().getText());
//		System.out.println(controller.getMonthYearLabel().getText());
//		clickOn(controller.getNextMonthTriangle());
//		System.out.println(controller.getMonthYearLabel().getText());
		int lastMonth = 1;
		int currentMonth = 2;
		int previousYear = 2019;
		int currentYear = 2019;
		boolean goesToNextMonth = currentMonth == 1 ? lastMonth == 12 : currentMonth == lastMonth + 1;
		boolean correctYear = currentMonth == 1 ? currentYear == previousYear + 1 : currentYear == previousYear;
		for(int i = 0; i < 4; i++) {
			
		}
	}
	
	@Test
	public void movesMonthsForwardsProperly() {
		MonthInfo currentDate = new MonthInfo();
		int monthCount = currentDate.getMonth();
		int yearCount = currentDate.getYear();
		for(int i = 0; i < 30; i++) {
			String[] labelTextMonthYear = controller.getMonthYearLabel().getText().split(" ");
			String month = labelTextMonthYear[0];
			String year = labelTextMonthYear[1];
			MonthInfo currInfo = new MonthInfo(month, Integer.valueOf(year));
			assertEquals(monthCount, currInfo.getMonth());
			clickOn(controller.getNextMonthTriangle());
			monthCount = monthCount == 12 ? 1 : ++monthCount;
			yearCount = monthCount == 1 ? ++yearCount : yearCount;
		}
	}
	
	@Test
	public void movesMonthsBackwardsProperly() {
		MonthInfo currentDate = new MonthInfo();
		int monthCount = currentDate.getMonth();
		int yearCount = currentDate.getYear();
		for(int i = 0; i < 30; i++) {
			String[] labelTextMonthYear = controller.getMonthYearLabel().getText().split(" ");
			String month = labelTextMonthYear[0];
			String year = labelTextMonthYear[1];
			MonthInfo currInfo = new MonthInfo(month, Integer.valueOf(year));
			assertEquals(monthCount, currInfo.getMonth());
			clickOn(controller.getPrevMonthTriangle());
			monthCount = monthCount == 1 ? 12 : --monthCount;
			yearCount = monthCount == 12 ? --yearCount : yearCount;
		}
	}
	
	@Test
	public void checkIfCurrentDateIsHighlighted() {
		String currentDay = String.valueOf(new MonthInfo().getDayOfMonth());
		GridPane monthGrid = (GridPane) controller.getCenterMonthPane().getChildren().get(0);
		Optional<Node> currentDayOptionalNode = monthGrid.getChildren().stream()
					.filter(stackPane -> {
						Label label = (Label)((StackPane)stackPane).getChildren().get(0);
						return label.getText().equals(currentDay);
					})
					.findFirst();
		if(currentDayOptionalNode.isPresent()) {
			StackPane todayStackPane =  (StackPane)currentDayOptionalNode.get();
			assertEquals("grid-stack-pane-today", todayStackPane.getStyleClass().get(0));
		}
	}
}
