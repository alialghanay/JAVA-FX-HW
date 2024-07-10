package com.mycompany.hw.q3;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
    
        Circle sun = new Circle(50, Color.GOLD);
        sun.setCenterX(75);
        sun.setCenterY(85);

        Rectangle sea = new Rectangle(700, 600);
        sea.setFill(Color.AQUAMARINE);
        sea.setY(400);

        Polygon ship = new Polygon(50.0,449.0  ,   600.0,449.0   , 500.0,510.0,   150.0,510.0);
        ship.setFill(Color.BURLYWOOD);
        
        Polygon polygon = new Polygon(325.0,250.0,    410.0,425.0 ,    325.0,425.0 );
        polygon.setFill(Color.RED);
        
        Line line = new Line(325, 250, 325, 450);
        line.setStroke(Color.BLACK);
            
        Ellipse firstWave = new Ellipse(325, 510, 220, 50);
        firstWave.setFill(null);
        firstWave.setStroke(Color.BLUE);

        Ellipse secondWave = new Ellipse(325, 510, 180, 40);
        secondWave.setFill(null);
        secondWave.setStroke(Color.BLUE);

        Ellipse thirdWave = new Ellipse(325, 510, 250, 60);
        thirdWave.setFill(null);
        thirdWave.setStroke(Color.BLUE);
            
        root.getChildren().addAll(sun, sea, firstWave,secondWave,thirdWave,ship, line,polygon);
        Scene scene = new Scene(root, 650, 650);
        scene.setFill(Color.BEIGE);
        primaryStage.setTitle("HW-Q3");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}