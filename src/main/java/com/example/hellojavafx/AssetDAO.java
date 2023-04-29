package com.example.hellojavafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AssetDAO {
    public static ObservableList<Asset> selectAssets() throws SQLException, ClassNotFoundException {
        String sql = "select a.id, a.name, a.asset_type_id, at.name asset_type_name, a.creation_dt from assets a inner join assets_types at on a.asset_type_id = at.id order by at.name, creation_dt;";
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
            Asset asset = new Asset(rs.getLong("id"), rs.getString("name"), rs.getLong("asset_type_id"), rs.getString("asset_type_name"));
            assetsList.add(asset);
        }
        return assetsList;
    }
}
