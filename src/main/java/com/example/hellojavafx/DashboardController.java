package com.example.hellojavafx;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;
import java.sql.SQLException;

public class DashboardController {
    @FXML
    private TableView<BudgetTotal> budgetView;
    @FXML
    private TableColumn<BudgetTotal, Number> budgetIDColumn;
    @FXML
    private TableColumn<BudgetTotal, String> assetNameColumn, assetTypeNameColumn, currencyCodeColumn, creationDTColumn;
    @FXML
    private TableColumn<BudgetTotal, Double> sumColumn;
    @FXML
    private TableColumn<BudgetTotal, Integer> levelColumn;
    @FXML
    private ComboBox<Asset> assetNameComboBox;
    @FXML
    private ComboBox<Currency> currencyCodeComboBox;
    @FXML
    private TextField sumField;

    @FXML
    private void populateBudget(ObservableList<BudgetTotal> budgetData) throws ClassNotFoundException{
        budgetView.setItems(budgetData);
    }

    @FXML
    private void getBudget() throws SQLException, ClassNotFoundException {
        try {
            ObservableList<BudgetTotal> bData = BudgetDAO.selectBudgetTotal();
            populateBudget(bData);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    @FXML
    private void initialize() throws SQLException, ClassNotFoundException {
        try {
            getBudget();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        budgetIDColumn.setCellValueFactory(cellData -> cellData.getValue().getBudgetIDProperty());
        assetNameColumn.setCellValueFactory(cellData -> cellData.getValue().getAssetNameProperty());
        assetTypeNameColumn.setCellValueFactory(cellData -> cellData.getValue().getAssetTypeNameProperty());
        currencyCodeColumn.setCellValueFactory(cellData -> cellData.getValue().getCurrencyCodeProperty());
        sumColumn.setCellValueFactory(cellData -> cellData.getValue().getSumProperty().asObject());
        levelColumn.setCellValueFactory(cellData -> cellData.getValue().getLevelProperty().asObject());

        sumColumn.setStyle("-fx-alignment: CENTER-RIGHT;");

        budgetView.setRowFactory(new Callback<TableView<BudgetTotal>, TableRow<BudgetTotal>>() {
            @Override
            public TableRow<BudgetTotal> call(TableView<BudgetTotal> budgetTotalTableView) {
                return new TableRow<BudgetTotal>() {
                    @Override
                    protected void updateItem(BudgetTotal item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            if (item.getLevel() >= 2 ) {
//                                System.out.println(item.getLevel());
//                                System.out.println(item.getLevelProperty());
                                setStyle("-fx-font-weight: bold");
                            } else {
                                setStyle("");
                            };
                        }

                        //if (item.getLevelProperty() > 1) {

                        // }

                    }
                };
            }
        });

    }



}