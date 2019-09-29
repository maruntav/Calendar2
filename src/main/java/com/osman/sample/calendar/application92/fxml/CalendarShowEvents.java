package com.osman.sample.calendar.application92.fxml;

import java.time.LocalDate;
import java.util.ArrayList;

import com.osman.sample.calendar.application92.date.CurrentDateClick;
import com.osman.sample.calendar.application92.dto.Event;

import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class CalendarShowEvents {
	public static void showEvents(Pane rootNode, LocalDate date, ArrayList<Event> events) {
    	if(!date.equals(CurrentDateClick.CURRENT_DATE)) {
    		return;
    	}
    	ShowEventsUI showEventsUI = new ShowEventsUI(CalendarShowEvents.class.getClass().getResource("/css/show_events_style.css").toExternalForm());
		showEventsUI.setEventsTitle(String.valueOf(CurrentDateClick.CURRENT_DATE));
		VBox eventList = (VBox) showEventsUI.getContent();
		events.stream().forEach(event -> {
			VBox newEvent = showEventsUI.createNewEvent(event);
			eventList.getChildren().add(newEvent);
		});
//		((VBox)showEventsUI.getContent()).getChildren().stream()
//			.filter(node -> node instanceof VBox)
//			.forEach(node -> ((VBox)node).getChildren().get(6));
		rootNode.getChildren().add(showEventsUI);
    	
    }
}
