package com.example.hellojavafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

// DAO - data access object
public class AssetTypeDAO {
//    public static AssetType selectAll() throws SQLException, ClassNotFoundException {
//        String sql = "select name from assets_types at";
//        try {
//            ResultSet rsAT = Connect.dbExecuteQuery(sql);
////            AssetType assetType =
//        }
//    }

//    public static AssetType getAssetTypeFromResultSet(ResultSet rs) throws SQLException {
//        AssetType assetType = null;
//        if (rs.next()) {
//            assetType = new AssetType(rs.getString("name"));
//        }
//        return assetType;
//    }

    public static ObservableList<AssetType> selectAssetsTypes() throws SQLException, ClassNotFoundException {
        String sql = "select name from assets_types order by name;";
        try {
            ResultSet rsAssetsTypes = DBUtil.dbExecuteQuery(sql);
            ObservableList<AssetType> assetTypesList = getAssetsTypesList(rsAssetsTypes);
            return assetTypesList;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    private static ObservableList<AssetType> getAssetsTypesList(ResultSet rs) throws SQLException, ClassNotFoundException {
        ObservableList<AssetType> assetTypesList = FXCollections.observableArrayList();

        while (rs.next()) {
            AssetType assetType = new AssetType(rs.getString("name"));
            assetTypesList.add(assetType);
        }
        return assetTypesList;
    }
}
