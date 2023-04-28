package com.example.hellojavafx;


import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;

public class AssetsController {
    @FXML
    private TableView<Asset> assetsView;
    @FXML
    private TextField assetNameField, assetTypeNameField;
    @FXML
    private TableColumn<Asset, Number> assetIDColumn;
    @FXML
    private TableColumn<Asset, String> assetNameColumn, assetTypeNameColumn;

    @FXML
    public void showTableRowContextMenu() {

        ContextMenu tableRowContextMenu = new ContextMenu();

        MenuItem deleteRow = new MenuItem("Delete");

        tableRowContextMenu.getItems().addAll(deleteRow);
    }

    @FXML
    private void populateAssets(ObservableList<Asset> assetsData) throws ClassNotFoundException{
        assetsView.setItems(assetsData);
    }

    @FXML
    private void getAssets() throws SQLException, ClassNotFoundException {
        try {
            ObservableList<Asset> aData = AssetDAO.selectAssets();
            populateAssets(aData);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    @FXML
    protected void deleteAsset(ActionEvent event) throws SQLException, ClassNotFoundException {
        TablePosition pos = assetsView.getSelectionModel().getSelectedCells().get(0);

        int row = pos.getRow();

        Asset asset = assetsView.getItems().get(row);

        TableColumn col = pos.getTableColumn();
//        String assetTypeName = (String) col.getCellObservableValue(assetType).getValue();
        String assetID =  String.valueOf(asset.getAssetID());
        String sql = "delete from assets where id = " + assetID + ";";

        try {
            DBUtil.dbExecuteUpdate(sql);
            getAssets();
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }
    }

    @FXML
    protected void addAsset(ActionEvent event) throws SQLException, ClassNotFoundException {
        String arg1 = assetNameField.getText();
        String arg2 = assetTypeNameField.getText();
        if (arg1.length() > 0 && arg2.length() > 0) {
            String sql = "insert into assets (name, asset_type_id) values ('" + arg1 + "', '" + arg2 + "');";
            try {
                DBUtil.dbExecuteUpdate(sql);
                assetNameField.setText("");
                assetTypeNameField.setText("");
                getAssets();
            } catch (SQLException e) {
                System.out.print("Error occurred while INSERT Operation: " + e);
                throw e;
            }
        }
    }

    @FXML
    private void initialize() throws SQLException, ClassNotFoundException {
        try {
            getAssets();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        assetIDColumn.setCellValueFactory(cellData -> cellData.getValue().assetIDProperty());
        assetNameColumn.setCellValueFactory(cellData -> cellData.getValue().assetNameProperty());
        //assetTypeNameColumn.setCellValueFactory(cellData -> cellData.getValue().assetTypeIDProperty());


    }
}
