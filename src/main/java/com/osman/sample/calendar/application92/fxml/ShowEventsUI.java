package com.osman.sample.calendar.application92.fxml;


import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;
import javafx.event.*;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import com.osman.sample.calendar.application92.dto.Event;
import com.osman.sample.calendar.application92.sqlite.*;

public class ShowEventsUI extends ScrollPane {
	private VBox rootVbox;
	
	private Button addNewEventButton;
	private Button selectEvents;
	private Button deleteEvents;
	
	private Label eventsTitle;
	private String cssFile;
	private VBox currentSelectedEvent = null;
	
	private Pane addEventPane;
	
	public ShowEventsUI(String cssFile) {
		super();
		this.cssFile = cssFile;
		rootVbox = new VBox(10);
		rootVbox.setPrefSize(500, 350);
		initialiseVbox();
		setLayoutX(75);
		setLayoutY(50);
		setContent(rootVbox);
		try {
			addEventPane = FXMLLoader.load(getClass().getResource("/view/add_event_layout.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void initialiseVbox() {
		eventsTitle = new Label("Events here");
		rootVbox.getChildren().addAll(initialiseButtons(), eventsTitle);	
	}
	
	public HBox initialiseButtons() {
		HBox hbox = new HBox(10);
		hbox.getStylesheets().add(cssFile);
		hbox.setAlignment(Pos.CENTER_RIGHT);
		addNewEventButton = new Button("Add New Event");
		addNewEventButton.setOnAction(actionEvent -> addNewEventButtonClick(actionEvent));
		selectEvents = new Button("Select Events");
		selectEvents.setOnAction(actionEvent -> selectEventsButtonClick(actionEvent));
		Label exit = new Label("✖");
		exit.getStylesheets().add(cssFile);
		exit.getStyleClass().add("exit-button");
		exit.setFont(Font.font(25));
		hbox.getChildren().addAll(addNewEventButton, selectEvents, exit);
		
		deleteEvents = new Button("Delete Events");
		deleteEvents.setOnAction(actionEvent -> {
			List<VBox> selectedEvents = rootVbox.getChildren().stream()
				.filter(node -> {
					if(node instanceof VBox && ((VBox)node).getChildren().get(0) instanceof HBox) {
						CheckBox checkBox = (CheckBox)((HBox)((VBox)node).getChildren().get(0)).getChildren().get(0);
						return checkBox.isSelected();
					}
					return false;
				})
				.map(node -> (VBox)node)
				.collect(Collectors.toList());
			deleteSelectedEvents(selectedEvents);
		});
		
		exit.setOnMouseClicked(mouseEvent -> ((Pane)getParent()).getChildren().remove(this));
		return hbox;
	}
	
	public void deleteSelectedEvents(List<VBox> selectedEvents) {
		EditEvent editEvent = new EditEvent(new SQLiteConnection(false).getConnection());
		selectedEvents.stream().forEach(selectedEvent -> {
			Label label = (Label)selectedEvent.getChildren().stream().filter(node -> node instanceof Label).findFirst().get();
			int eventid = Integer.valueOf(label.getText());
			editEvent.deleteEvent(eventid);
//			rootVbox.getChildren().remove(selectedEvent);
			
		});
	} 
	
	public void addNewEventButtonClick(ActionEvent actionEvent) {
		System.out.println("add new click");
		Pane root = (Pane) getParent();
		root.getChildren().remove(this);
		root.getChildren().add(addEventPane);
	}
	
	public void setEventsTitle(String newTitle) {
		eventsTitle.setText(newTitle);
	}
	
	public void selectEventsButtonClick(ActionEvent actionEvent) {
		Button selectEvents  = ((Button)actionEvent.getTarget());
		HBox container = ((HBox)selectEvents.getParent());
		if(selectEvents.getText().equals("Select Events")) {
			selectEvents.setText("Cancel Selection");
			container.getChildren().add(2, deleteEvents);
			container.getChildren().remove(addNewEventButton);
			toggleDeleteEvents(false);
		} else {
			selectEvents.setText("Select Events");
			container.getChildren().remove(deleteEvents);
			container.getChildren().add(0, addNewEventButton);
			toggleDeleteEvents(true);
		}
	}
	
	//'newState' is whether the textfields are editable
	public void toggleDeleteEvents(boolean newState) {
		rootVbox.getChildren()
		.stream()
		.filter(node -> node instanceof VBox)
		.forEach(node -> {
			if(!newState) {
				ObservableList<Node> nodeChildren = ((VBox)node).getChildren();
				if(nodeChildren.size() == 6) {
					nodeChildren.remove(nodeChildren.size() - 1);
				}
			}
			((VBox)node).getChildren().stream()
				.forEach(element -> {
					if(element instanceof TextField) {
						editTextFieldState(newState, (TextField)element);
					} else if(element instanceof HBox) {
						ObservableList<Node> children  = ((HBox) element).getChildren();
						if(children.get(0) instanceof CheckBox) {
							element.setVisible(!newState);
						} else {
							editTextFieldState(newState, (TextField)((HBox) element).getChildren().get(1));
						}
					}
				});
		});
	}
	
	public void editTextFieldState(boolean newState, TextField textField) {
		textField.setEditable(newState);
		if(newState)  {
			textField.getStyleClass().add("text-field-editable");
		} else {
			textField.getStyleClass().remove("text-field-editable");
		}
	}
	
	public VBox createNewEvent(Event event) {
		TextField labelTitle = new TextField(event.getTitle());
		TextField labelTime = new TextField(event.getTime());
		HBox timeRow = new HBox(new Label("  Time: "), labelTime);
		timeRow.setAlignment(Pos.CENTER_LEFT);
		TextField labelDetails= new TextField(event.getDetails());
		Label labelId = new Label(String.valueOf(event.getId()));
		labelId.setVisible(false);
		Button editSubmitButton = new Button("Save Changes");
		Button cancelChanges = new Button("Cancel");
		HBox editButtonsHbox = new HBox(10, editSubmitButton, cancelChanges);
		HBox checkBoxWrapper = new HBox(new CheckBox());
		checkBoxWrapper.setAlignment(Pos.CENTER_RIGHT);
		checkBoxWrapper.setVisible(false);
		
		VBox vBox = new VBox(checkBoxWrapper, labelTitle, timeRow, labelDetails, labelId);
		vBox.getStylesheets().add(cssFile);
		vBox.getStyleClass().add("event-vbox");
		
		vBox.setOnMouseClicked(mouseEvent -> changeEvent(vBox));
		
		List<TextField> textFields =  Arrays.asList(new TextField[] {labelTitle, labelTime, labelDetails});
		textFields.stream()
			.forEach(textField -> {
				textField.getStylesheets().add(cssFile);
				textField.getStyleClass().addAll("text-field", "text-field-editable");
				textField.setOnMouseClicked(mouseEvent -> {
					changeEvent(vBox);
					if(!vBox.getChildren().contains(editButtonsHbox) && textField.isEditable())
						vBox.getChildren().add(editButtonsHbox);
				});
			});
		

		cancelChanges.setOnAction(actionEvent -> {
			vBox.getChildren().remove(editButtonsHbox);
			setTextFieldsInEvent(event, labelTitle, labelDetails, labelTime);
			//just refresh - select where id=bla (original data)
		});
		editSubmitButton.setOnAction(actionEvent -> {
			Event inEvent = new Event(event.getId(), event.getDate(), event.getTime(), "new title", "updated details");
			EditEvent editEvent = new EditEvent(new SQLiteConnection(false).getConnection());
			editEvent.editEvent(inEvent);
			vBox.getChildren().remove(editButtonsHbox);
			//update query and refresh (updated data)
		});
		
		return vBox;
	}
	
	public void setTextFieldsInEvent(Event event, TextField title, TextField details, TextField time) {
		title.setText(event.getTitle());
		details.setText(event.getDetails());
		time.setText(event.getTime());
	}
	
	public void changeEvent(VBox newEvent) {
		if(currentSelectedEvent == null || newEvent != currentSelectedEvent) {
			if(currentSelectedEvent != null) {
				Node node = currentSelectedEvent.getChildren().get(currentSelectedEvent.getChildren().size() - 1);
				if(node instanceof HBox) {
					currentSelectedEvent.getChildren().remove(node);
				}
			}
			currentSelectedEvent = newEvent;
		}
	}
}
