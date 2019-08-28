package com.osman.sample.calendar.application92.fxml;

import javafx.scene.control.Label;
import com.osman.sample.calendar.application92.date.MonthInfo;

public class MonthYearLabelUtils {
	public static void initMonthLabel(Label label, int month, int year) {
		MonthInfo monthInfo = new MonthInfo(month, year);
		label.setText(monthInfo.getMonthName() + " " + year);
	}
}
