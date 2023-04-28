package com.example.hellojavafx;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Asset {
    private final SimpleLongProperty assetID;
    private final SimpleStringProperty assetName;
    private final SimpleLongProperty assetTypeID;

    public Asset(long id, String name, long assetTypeID) {
        this.assetID = new SimpleLongProperty(id);
        this.assetName = new SimpleStringProperty(name);
        this.assetTypeID = new SimpleLongProperty(assetTypeID);
    }

    public long getAssetID() {
        return assetID.get();
    }

    public String getAssetName() {
        return assetName.get();
    }

    public long getAssetTypeID() {
        return assetTypeID.get();
    }

    public LongProperty assetIDProperty() {
        return assetID;
    }

    public StringProperty assetNameProperty() {
        return assetName;
    }

    public LongProperty assetTypeIDProperty() {
        return assetTypeID;
    }
}
