package com.example.hellojavafx;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AssetType {
    private final SimpleStringProperty assetName;

    public AssetType(String name) {
        this.assetName = new SimpleStringProperty(name);
    }

    public String getAssetName() {
        return assetName.get();
    }

    public void setAssetName(String name) {
        assetName.set(name);
    }

    public StringProperty assetNameProperty() {
        return assetName;
    }
}
