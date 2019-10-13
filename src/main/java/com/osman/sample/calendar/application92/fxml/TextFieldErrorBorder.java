package com.osman.sample.calendar.application92.fxml;

import javafx.scene.control.*;

public class TextFieldErrorBorder {
	public static void wrapTextFieldWithRedBorder(TextInputControl field) {
		if(!field.getStyleClass().contains("text-field-error")) {
			field.getStyleClass().add("text-field-error");
		}
	}
	
	public static void removeRedBorder(TextInputControl field) {
		if(field.getStyleClass().contains("text-field-error")) {
			field.getStyleClass().remove("text-field-error");
		}
	}
}
