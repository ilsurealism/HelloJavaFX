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
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("root.fxml"));

        // width and height of the screen extension divided by 2
        int minX = (int) Math.round(Screen.getPrimary().getVisualBounds().getWidth() / 1.5);
        int minY = (int) Math.round(Screen.getPrimary().getVisualBounds().getHeight() / 1.5);

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