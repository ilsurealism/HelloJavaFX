package com.example.hellojavafx;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Currency {
    private final SimpleLongProperty currencyID;
    private final SimpleStringProperty currencyCode;
    private final SimpleStringProperty currencyName;

    public Currency(long id, String code, String name) {
        this.currencyID = new SimpleLongProperty(id);
        this.currencyCode = new SimpleStringProperty(code);
        this.currencyName = new SimpleStringProperty(name);
    }

    public long getCurrencyID() {
        return currencyID.get();
    }

    public String getCurrencyCode() {
        return currencyCode.get();
    }

    public String getCurrencyName() {
        return currencyName.get();
    }

    public LongProperty currencyIDProperty() {
        return currencyID;
    }

    public StringProperty currencyCodeProperty() {
        return currencyCode;
    }

    public StringProperty currencyNameProperty() {
        return currencyName;
    }


}
