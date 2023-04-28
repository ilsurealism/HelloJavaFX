package com.example.hellojavafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AssetDAO {
    public static ObservableList<Asset> selectAssets() throws SQLException, ClassNotFoundException {
        String sql = "select id, name, asset_type_id, creation_dt from assets order by creation_dt;";
        try {
            ResultSet rsAssets = DBUtil.dbExecuteQuery(sql);
            ObservableList<Asset> assetsList = getAssetsList(rsAssets);
            return assetsList;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    private static ObservableList<Asset> getAssetsList(ResultSet rs) throws SQLException, ClassNotFoundException {
        ObservableList<Asset> assetsList = FXCollections.observableArrayList();

        while (rs.next()) {
            Asset asset = new Asset(rs.getLong("id"), rs.getString("name"), rs.getLong("asset_type_id"));
            assetsList.add(asset);
        }
        return assetsList;
    }
}
