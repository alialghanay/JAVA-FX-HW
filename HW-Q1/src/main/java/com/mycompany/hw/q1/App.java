package com.mycompany.hw.q1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.net.URL;
import javafx.scene.layout.GridPane;

/**
 * JavaFX App
 */
public class App extends Application {

     @Override
    public void start(Stage stage) {
        TitledPane titlePaneApple = titlePene("images/apple.png", "Apple");
        TitledPane titlePaneFlower = titlePene("images/flower.png", "Flower");
        TitledPane titlePaneLeaves = titlePene("images/tree-leaves.png", "Leaves");
        Accordion accordion = new Accordion();
        accordion.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        accordion.getPanes().addAll(titlePaneApple, titlePaneFlower, titlePaneLeaves);
        accordion.setExpandedPane(titlePaneApple);
        Group root =new Group(accordion);
        
        Scene scene = new Scene(root,320,275);
        stage.setTitle("HW-Q1");
        stage.setScene(scene);
        stage.show();
    }

    public TitledPane titlePene(String imageStr, String textTitle) {
        Label label = new Label();
        GridPane grid = new GridPane();
        TitledPane result = new TitledPane();
        URL imageUrl = getClass().getClassLoader().getResource(imageStr);
        
        if (imageUrl == null) {
            System.err.println("Couldn't find file: " + imageStr);
            return new TitledPane("Error", new Label("Image not found"));
        }
        
        Image img = new Image(imageUrl.toExternalForm());
        ImageView imgView = new ImageView(img);
        label.setGraphic(imgView);
        label.setMaxSize(Double.MAX_VALUE,Double.MAX_VALUE);
        result.setText(textTitle);
        result.setContent(label);
     
        return result;
    }

    public static void main(String[] args) {
        launch(args);
    }

}