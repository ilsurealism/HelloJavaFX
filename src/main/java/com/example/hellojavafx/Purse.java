package com.example.hellojavafx;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleDoubleProperty;
import java.util.HashMap;
import java.util.Map;

public class Purse {
    private final SimpleStringProperty purseName = new SimpleStringProperty("");
//    private final HashMap<SimpleStringProperty, SimpleDoubleProperty> sumByCurrency = new HashMap<SimpleStringProperty, SimpleDoubleProperty>();
    private final SimpleDoubleProperty sum = new SimpleDoubleProperty(0.00);
//    private final SimpleStringProperty sum = new SimpleStringProperty("");

//    private final SimpleStringProperty RUB = new SimpleStringProperty("");
//    private final SimpleStringProperty USD = new SimpleStringProperty("");
//    private final SimpleStringProperty EUR = new SimpleStringProperty("");
//    private final SimpleStringProperty KZT = new SimpleStringProperty("");

    public Purse() {
        this("", 0.00);
    }

//    public Purse(String purseName, HashMap<SimpleStringProperty, SimpleDoubleProperty> sumByCurrency) {
//        setPurseName(purseName);
//        setSum(0.00);
////        setSumByCurrency(sumByCurrency);
//    }

    public Purse(String purseName, double sum) {
        setPurseName(purseName);
        setSum(sum);
//        setSumByCurrency(sumByCurrency);
    }

    public String getPurseName() {
        return purseName.get();
    }

    public double getSum() {
        return sum.get();
    }

//    public SimpleDoubleProperty getSumByCurrency(SimpleStringProperty currency) {
//        return sumByCurrency.get(currency);
//    }

    public void setPurseName(String pName) {
        purseName.set(pName);
    }

    public void setSum(double value) {
        sum.set(value);
    }

//    public void setSumByCurrency(SimpleStringProperty currency, SimpleDoubleProperty sum) {
//        sumByCurrency.put(currency, sum);
//    }
//
//    public void setSumByCurrency(HashMap<SimpleStringProperty, SimpleDoubleProperty> sumByCurr) {
//        sumByCurrency.putAll(sumByCurr);
//    }
}
