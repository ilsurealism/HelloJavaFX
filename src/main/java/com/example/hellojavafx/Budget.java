package com.example.hellojavafx;

import javafx.beans.property.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Budget {
    protected  SimpleLongProperty budgetID;
    protected  SimpleLongProperty assetID;
    protected  SimpleStringProperty assetName;
    protected  SimpleStringProperty assetTypeName;
    protected  SimpleLongProperty currencyID;
    protected  SimpleStringProperty currencyCode;
    protected  SimpleDoubleProperty sum;
    protected  SimpleStringProperty creationDT;

    public Budget(long budgetID,
                  long assetID,
                  String assetName,
                  String assetTypeName,
                  long currencyID,
                  String currencyCode,
                  double sum,
                  String creationDT) {
            this.budgetID = new SimpleLongProperty(budgetID);
            this.assetID = new SimpleLongProperty(assetID);
            this.assetName = new SimpleStringProperty(assetName);
            this.assetTypeName = new SimpleStringProperty(assetTypeName);
            this.currencyID = new SimpleLongProperty(currencyID);
            this.currencyCode = new SimpleStringProperty(currencyCode);
            this.sum = new SimpleDoubleProperty(sum);
            this.creationDT = new SimpleStringProperty(creationDT);
    }

    public Budget() {

    }

    public long getBudgetID() {
        return budgetID.get();
    }

    public long getAssetID() {
        return assetID.get();
    }

    public String getAssetName() {
        return assetName.get();
    }

    public String getAssetTypeName() {
        return assetTypeName.get();
    }

    public long getCurrencyID() {
        return currencyID.get();
    }

    public String getCurrencyCode() {
        return currencyCode.get();
    }

    public double getSum() {
        return sum.get();
    }

    public String getCreationDT() {
        return creationDT.get();
    }

    public LongProperty getBudgetIDProperty() {
        return budgetID;
    }

    public LongProperty getAssetIDProperty() {
        return assetID;
    }

    public StringProperty getAssetNameProperty() {
        return assetName;
    }

    public StringProperty getAssetTypeNameProperty() {
        return assetTypeName;
    }

    public LongProperty getCurrencyIDProperty() {
        return currencyID;
    }

    public StringProperty getCurrencyCodeProperty() {
        return currencyCode;
    }

    public DoubleProperty getSumProperty() {
        return sum;
    }
    public StringProperty getCreationDTProperty() {
        return creationDT;
    }

}
