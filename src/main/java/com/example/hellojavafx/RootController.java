package com.example.hellojavafx;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.SQLException;

public class RootController {
    @FXML
    private ListView<String> menuList;
    @FXML
    private AnchorPane mainView;

//    @FXML
//    ListView<String> menuListView = new ListView<String>();

//    @FXML
//    menuListView.setItems
    @FXML
    private void initialize() throws ClassNotFoundException {
        System.out.println(mainView.getChildren().getClass());
    }

    public void changeView(ActionEvent Event) throws IOException {
        String menuItemId = ((Hyperlink) Event.getSource()).getId();
        String newViewName = null;
        String currentViewName ;

        switch (menuItemId) {
            case "settingsBtn":
                newViewName = "hello-view.fxml";
                break;
            case "budgetBtn":
                newViewName = "budget-view.fxml";
                break;
            case "assetsBtn":
                newViewName = "assets-view.fxml";
                break;
            case "mainBtn":
                newViewName = "dashboard-view.fxml";
                break;
            default:
                break;
        }

        if (newViewName != null) {
            mainView.getChildren().clear();
            mainView.getChildren().add(FXMLLoader.load(getClass().getResource(newViewName)));
        }
//        System.out.println(location.getClass());
    }

    public void getMainViewHeight() {

        System.out.println(mainView.getHeight());
    }

    public void quit() {
        Platform.exit();
    }
}
