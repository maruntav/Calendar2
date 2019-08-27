package com.osman.sample.application92;

import java.util.concurrent.TimeoutException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

import com.osman.sample.calendar.application92.App;
import com.osman.sample.calendar.application92.fxml.CalendarMainController;
import com.sun.javafx.scene.paint.GradientUtils;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import junit.framework.Assert;

public class AppTest extends ApplicationTest {
	CalendarMainController controller;
	
	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader();
		Pane root = fxmlLoader.load(App.class.getClassLoader().getResource("view/cal_layout.fxml").openStream());
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
	public void bla() {
//		Assert.assertEquals(101, controller.getNum());
	}
}
