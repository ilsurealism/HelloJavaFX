package com.example.hellojavafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Screen;

import java.io.IOException;
import java.sql.SQLException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException, ClassNotFoundException, SQLException {
//        Connect.createDB();
        DBUtil.connect();
        DBUtil.initDBObjects();
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("root.fxml"));
//        System.out.println(Screen.getPrimary().getDpi());
//        System.out.println(Screen.getPrimary().getOutputScaleX());
//        System.out.println(Screen.getPrimary().getOutputScaleY());
//
//        System.out.println(Screen.getPrimary().getVisualBounds().getWidth());
//        System.out.println(Screen.getPrimary().getVisualBounds().getMinX());
//        System.out.println(Screen.getPrimary().getVisualBounds().getMinY());
        // width and height of the screen extension divided by 2
        int minX = (int) Screen.getPrimary().getVisualBounds().getWidth() / 2;
        int minY = (int) Screen.getPrimary().getVisualBounds().getHeight() / 2;

//        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        Scene scene = new Scene(fxmlLoader.load(), minX, minY);
        stage.setTitle("Finance Book");
        //stage.setFullScreen(true);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}