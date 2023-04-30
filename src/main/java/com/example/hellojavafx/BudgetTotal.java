package com.example.hellojavafx;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

public class BudgetTotal extends Budget {
    private final SimpleIntegerProperty level;
    public BudgetTotal(long budgetID,
                  long assetID,
                  String assetName,
                  String assetTypeName,
                  long currencyID,
                  String currencyCode,
                  double sum,
                  int level) {
        this.budgetID = new SimpleLongProperty(budgetID);
        this.assetID = new SimpleLongProperty(assetID);
        this.assetName = new SimpleStringProperty(assetName);
        this.assetTypeName = new SimpleStringProperty(assetTypeName);
        this.currencyID = new SimpleLongProperty(currencyID);
        this.currencyCode = new SimpleStringProperty(currencyCode);
        this.sum = new SimpleDoubleProperty(sum);
        this.level = new SimpleIntegerProperty(level);
    }

    public int getLevel() {
        return level.get();
    }

    public SimpleIntegerProperty getLevelProperty() {
        return level;
    }
}
