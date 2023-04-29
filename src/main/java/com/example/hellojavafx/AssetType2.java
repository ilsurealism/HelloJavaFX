package com.example.hellojavafx;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AssetType2 {
    private final SimpleLongProperty assetID;
    private final SimpleStringProperty assetName;

    public AssetType2(long ID, String name) {
        this.assetID = new SimpleLongProperty(ID);
        this.assetName = new SimpleStringProperty(name);
    }

    public long getAssetID() {
        return assetID.get();
    }
    public String getAssetName() {
        return assetName.get();
    }

    public void setAssetName(String name) {
        assetName.set(name);
    }

    public LongProperty assetIDProperty() {
        return assetID;
    }
    public StringProperty assetNameProperty() {
        return assetName;
    }

    @Override
    public String toString() {
        return getAssetName();
    }
}
