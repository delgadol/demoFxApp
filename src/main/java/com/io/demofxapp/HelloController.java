package com.io.demofxapp;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.robot.Robot;
import javafx.stage.Screen;
import javafx.util.Duration;

import java.util.Objects;
import java.util.Random;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
        if (!Objects.isNull(timeline)) {
            if(!isRunning){
                timeline.play();
                isRunning= true;
            }else{
                timeline.stop();
                segundosTranscurridos = 0;
                isRunning= false;
            }
        }
    }

    private Timeline timeline ;

    private int segundosTranscurridos = 0;

    private boolean isRunning;

    private double screenWidth;
    private double screenHeight;

    private void moveMouse() {
        Platform.runLater(() -> {
            try {
                Robot robot = new Robot();
                robot.mouseMove(screenWidth, screenHeight);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void runTimer(){
        timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), event -> {
                    segundosTranscurridos++;
                    welcomeText.setText("Segundos transcurridos: " + segundosTranscurridos);
                    getDisPlayPoint();
                    moveMouse();
                })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
    }

    private void getDisPlayPoint(){
        Random random = new Random();
        Screen screen = Screen.getPrimary();
        screenWidth =   (100*random.nextDouble()+1)/100 * screen.getBounds().getWidth();
        screenHeight =  (100*random.nextDouble()+1)/100 * screen.getBounds().getHeight();
        System.out.println(" " + screenWidth + " -" + screenHeight);

    }

    public HelloController() {
        runTimer();
    }
}