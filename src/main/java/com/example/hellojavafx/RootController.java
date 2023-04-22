package com.example.hellojavafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
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

    }

    public void changeView(ActionEvent Event) throws IOException {
        String menuItemId = ((Hyperlink) Event.getSource()).getId();
        switch (menuItemId) {
            case "settingsBtn":
                System.out.println("Settings");
                System.out.println(mainView.getChildren());
                mainView.getChildren().clear();
                mainView.getChildren().add(FXMLLoader.load(getClass().getResource("hello-view.fxml")));
                break;
            case "mainBtn":
                mainView.getChildren().clear();
                mainView.getChildren().add(FXMLLoader.load(getClass().getResource("main.fxml")));
                break;
            default:
                break;
        }
        System.out.println(((Hyperlink) Event.getSource()).getId());
    }
}
