package com.mycompany.hw.q2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        
        Label nameLabel = new Label("Name");
        TextField nameField = new TextField("enter your name");
        Label dobLabel = new Label("Date of birth");
        DatePicker dobPicker = new DatePicker();
        Label genderLabel = new Label("Gender");
        RadioButton radio1 = new RadioButton("Male");
        RadioButton radio2 = new RadioButton("Female");
        Label techLabel = new Label("Technologies Known");
        CheckBox checkbox1 = new CheckBox("Java");
        CheckBox checkbox2 = new CheckBox("DoNet");
        checkbox2.setSelected(true);
        Label locationLabel = new Label("Location");
        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.setItems(FXCollections.observableArrayList("Tripoli", "Tunis", "Cairo"));
        Button registerButton = new Button("Register");
        registerButton.setStyle("-fx-background-color: lightblue;");
        
        
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.add(nameLabel, 0, 0);
        gridPane.add(nameField, 1, 0);
        gridPane.add(dobLabel, 0, 1);
        gridPane.add(dobPicker, 1, 1);
        gridPane.add(genderLabel, 0, 2);
        gridPane.add(radio1, 1, 2);
        gridPane.add(radio2, 2, 2);
        gridPane.add(techLabel, 0, 3);
        gridPane.add(checkbox1, 1, 3);
        gridPane.add(checkbox2, 2, 3);
        gridPane.add(locationLabel, 0, 4);
        gridPane.add(choiceBox, 1, 4);
        gridPane.add(registerButton, 2, 5);
        gridPane.setBackground(new Background(new BackgroundFill(Color.BEIGE, null, null)));
        
        Scene scene = new Scene(gridPane, 500, 200);
        stage.setTitle("HW-Q2");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}