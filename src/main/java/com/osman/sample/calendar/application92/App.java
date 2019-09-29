package com.osman.sample.calendar.application92;

import com.osman.sample.calendar.application92.sqlite.SQLiteConnection;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class App extends Application 
{
    public static void main( String[] args ) {
    	com.osman.sample.calendar.application92.sqlite.SQLiteConnection sqLiteConnection = new SQLiteConnection(false);
    	launch(args);
    }

	@Override
	public void start(Stage primaryStage) throws Exception {
		Pane root = (Pane) FXMLLoader.load(App.class.getClassLoader().getResource("view/cal_layout.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}