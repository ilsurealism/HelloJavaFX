package com.example.hellojavafx;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;

public class BudgetController {
    @FXML
    private TableView<Budget> budgetView;
    @FXML
    private TableColumn<Budget, Number> budgetIDColumn;
    @FXML
    private TableColumn<Budget, String> assetNameColumn, assetTypeNameColumn, currencyCodeColumn, creationDTColumn;
    @FXML
    private TableColumn<Budget, Double> sumColumn;
    @FXML
    private ComboBox<Asset> assetNameComboBox;
    @FXML
    private ComboBox<Currency> currencyCodeComboBox;
    @FXML
    private TextField sumField;

    @FXML
    public void showTableRowContextMenu() {

        ContextMenu tableRowContextMenu = new ContextMenu();

        MenuItem deleteRow = new MenuItem("Delete");

        tableRowContextMenu.getItems().addAll(deleteRow);
    }

    @FXML
    private void populateBudget(ObservableList<Budget> budgetData) throws ClassNotFoundException{
        budgetView.setItems(budgetData);
    }
    @FXML
    private void populateAssets(ObservableList<Asset> assetData) throws ClassNotFoundException {
        assetNameComboBox.setItems(assetData);
    }

    @FXML
    private void populateCurrencies(ObservableList<Currency> currencyData) throws ClassNotFoundException {
        currencyCodeComboBox.setItems(currencyData);
    }

    @FXML
    private void getBudget() throws SQLException, ClassNotFoundException {
        try {
            ObservableList<Budget> bData = BudgetDAO.selectBudget();
            populateBudget(bData);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw e;
        }
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
    private void getCurrencies() throws SQLException, ClassNotFoundException {
        try {
            ObservableList<Currency> cData = CurrencyDAO.selectCurrencies();
            populateCurrencies(cData);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    @FXML
    protected void deleteBudgetRecord(ActionEvent event) throws SQLException, ClassNotFoundException {
        TablePosition pos = budgetView.getSelectionModel().getSelectedCells().get(0);

        int row = pos.getRow();

        Budget budget = budgetView.getItems().get(row);

        TableColumn col = pos.getTableColumn();
//        String assetTypeName = (String) col.getCellObservableValue(assetType).getValue();
        String budgetID =  String.valueOf(budget.getBudgetID());
        String sql = "delete from budget where id = " + budgetID + ";";

        try {
            DBUtil.dbExecuteUpdate(sql);
            getBudget();
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }
    }

    @FXML
    protected void addBudgetRecord(ActionEvent event) throws SQLException, ClassNotFoundException {
        String arg1 = String.valueOf(assetNameComboBox.getSelectionModel().getSelectedItem().getAssetID());
        String arg2 = String.valueOf(currencyCodeComboBox.getSelectionModel().getSelectedItem().getCurrencyID());
        String arg3 = sumField.getText();
        if (arg1.length() > 0 && arg2.length() > 0 && arg3.length() > 0) {
            String sql = "insert into budget (asset_id, currency_id, sum) values (" + arg1 + ", " + arg2 + ", " + arg3 + ");";
            try {
                DBUtil.dbExecuteUpdate(sql);
                sumField.setText("");
                getBudget();
            } catch (SQLException e) {
                System.out.print("Error occurred while INSERT Operation: " + e);
                throw e;
            }
        }
    }
    @FXML
    private void initialize() throws SQLException, ClassNotFoundException {
        try {
            getBudget();
            getAssets();
            getCurrencies();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        budgetIDColumn.setCellValueFactory(cellData -> cellData.getValue().getBudgetIDProperty());
        assetNameColumn.setCellValueFactory(cellData -> cellData.getValue().getAssetNameProperty());
        assetTypeNameColumn.setCellValueFactory(cellData -> cellData.getValue().getAssetTypeNameProperty());
        currencyCodeColumn.setCellValueFactory(cellData -> cellData.getValue().getCurrencyCodeProperty());
        sumColumn.setCellValueFactory(cellData -> cellData.getValue().getSumProperty().asObject());
        creationDTColumn.setCellValueFactory(cellData -> cellData.getValue().getCreationDTProperty());
    }


}
