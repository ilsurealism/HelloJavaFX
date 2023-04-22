package com.example.hellojavafx;

import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.stage.Popup;

import java.sql.SQLException;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private Label clickCountText;
    @FXML private TableView<Purse> tableView;
    @FXML private TextField purseNameField;
    @FXML private TextField sumField;

    // AssetType table
    @FXML private TableView<AssetType> assetTypeView;
    @FXML
    private TableColumn<AssetType, String> assetTypeName;
    @FXML private TextField assetTypeNameField;
    int iClickCount = 0;


    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
        iClickCount++;
        if (iClickCount == 1)
        {
            clickCountText.setText("You have clicked once.");
        }
        else
        {
            clickCountText.setText("You have clicked " + iClickCount + " times.");
        }

    }

    @FXML
    protected void addPurse(ActionEvent event) {
        ObservableList<Purse> data = tableView.getItems();
        data.add(new Purse(purseNameField.getText(), Double.parseDouble(sumField.getText())));

        purseNameField.setText("");
        sumField.setText("");
    }

//    @FXML
//    protected void initAssetType() {
//        ObservableList<AssetType> data = assetTypeView.getItems();
//
//    }

    @FXML
    private void populateAssetsTypes (ObservableList<AssetType> assetTypesData) throws ClassNotFoundException {
        assetTypeView.setItems(assetTypesData);
    }

//    @FXML
//    private void populateAndShowAssetsTypes(AssetType aT) {
//        if (aT != null) {
//            populateAssetsTypes(aT);
//
//        }
//    }
    @FXML
    private void getAssetsTypes(/*ActionEvent actionEvent*/) throws SQLException, ClassNotFoundException {
        try {
            ObservableList<AssetType> aTData = AssetTypeDAO.selectAssetsTypes();
            populateAssetsTypes(aTData); // error
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    @FXML
    private void initialize() throws SQLException, ClassNotFoundException {
        try {
            getAssetsTypes();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        assetTypeName.setCellValueFactory(cellData -> cellData.getValue().assetNameProperty());
    }

    private void setAssetTypeViewEditable() {
        assetTypeView.setEditable(true);
        assetTypeView.getSelectionModel().cellSelectionEnabledProperty().set(true);
        assetTypeView.setOnKeyPressed(e -> {
            if (e.getCode().isLetterKey() || e.getCode().isDigitKey()) {
                editFocusedCell();
            } else if (e.getCode() == KeyCode.RIGHT || e.getCode() == KeyCode.TAB) {
                assetTypeView.getSelectionModel().selectNext();
                e.consume();
            } /*else if (e.getCode() == KeyCode.LEFT) {

            }*/
        });
    }

    @SuppressWarnings("unchecked")
    private void editFocusedCell() {
        final TablePosition<AssetType, ?> focusedCell = assetTypeView.focusModelProperty().get().focusedCellProperty().get();
        assetTypeView.edit(focusedCell.getRow(), focusedCell.getTableColumn());
    }

    @FXML
    protected void addAssetType(ActionEvent event) throws SQLException, ClassNotFoundException {
//        ObservableList<Purse> data = tableView.getItems();
//        data.add(new Purse(purseNameField.getText(), Double.parseDouble(sumField.getText())));
        String arg = assetTypeNameField.getText();
        if (arg.length() > 0) {
            String sql = "insert into assets_types (name) values ('" + arg + "');";
            try {
                DBUtil.dbExecuteUpdate(sql);
                assetTypeNameField.setText("");
                getAssetsTypes();
            } catch (SQLException e) {
                System.out.print("Error occurred while DELETE Operation: " + e);
                throw e;
            }
        }
    }

    @FXML
    protected void deleteAssetType(ActionEvent event) throws SQLException, ClassNotFoundException {

//        ObservableList<Purse> data = tableView.getItems();
//        data.add(new Purse(purseNameField.getText(), Double.parseDouble(sumField.getText())));
        System.out.println(assetTypeView.focusModelProperty().get().focusedCellProperty().get().toString());
        System.out.println(assetTypeView.focusModelProperty().get().focusedCellProperty().get());
        System.out.println(assetTypeView.focusModelProperty().get().focusedCellProperty());
        System.out.println(assetTypeView.focusModelProperty().get());
        System.out.println(assetTypeView.focusModelProperty());
        System.out.println(assetTypeView);

        System.out.println("--------------------------------");
        TablePosition pos = assetTypeView.getSelectionModel().getSelectedCells().get(0);
        System.out.println(pos);
        int row = pos.getRow();
        System.out.println(row);
        AssetType assetType = assetTypeView.getItems().get(row);
        System.out.println(assetType);
        TableColumn col = pos.getTableColumn();
//        String assetTypeName = (String) col.getCellObservableValue(assetType).getValue();
        String assetTypeName = (String) assetType.getAssetName();
        System.out.println(col);
        System.out.println(assetTypeName);
        String sql = "delete from assets_types where name = '" + assetTypeName + "';";
        try {
            DBUtil.dbExecuteUpdate(sql);
            assetTypeNameField.setText("");
            getAssetsTypes();
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }
    }

    @FXML
    public void showTableRowContextMenu() {

        ContextMenu tableRowContextMenu = new ContextMenu();

        MenuItem deleteRow = new MenuItem("Delete");

        tableRowContextMenu.getItems().addAll(deleteRow);
    }




}