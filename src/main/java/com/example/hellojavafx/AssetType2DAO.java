package com.example.hellojavafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AssetType2DAO {
    public static ObservableList<AssetType2> selectAssetsTypes() throws SQLException, ClassNotFoundException {
        String sql = "select id, name from assets_types order by name;";
        try {
            ResultSet rsAssetsTypes = DBUtil.dbExecuteQuery(sql);
            ObservableList<AssetType2> assetTypesList = getAssetsTypesList(rsAssetsTypes);
            return assetTypesList;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }
    private static ObservableList<AssetType2> getAssetsTypesList(ResultSet rs) throws SQLException, ClassNotFoundException {
        ObservableList<AssetType2> assetTypesList = FXCollections.observableArrayList();

        while (rs.next()) {
            AssetType2 assetType = new AssetType2(rs.getLong("id"), rs.getString("name"));
            assetTypesList.add(assetType);
        }
        return assetTypesList;
    }
}
