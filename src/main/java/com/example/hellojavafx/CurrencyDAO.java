package com.example.hellojavafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CurrencyDAO {
    public static ObservableList<Currency> selectCurrencies() throws SQLException, ClassNotFoundException {
        String sql = "select id, code, name from currencies order by id;";
        try {
            ResultSet rsCurrencies = DBUtil.dbExecuteQuery(sql);
            ObservableList<Currency> currenciesList = getCurrenciesList(rsCurrencies);
            return currenciesList;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

        private static ObservableList<Currency> getCurrenciesList(ResultSet rs) throws SQLException, ClassNotFoundException {
        ObservableList<Currency> currenciesList = FXCollections.observableArrayList();

        while (rs.next()) {
            Currency currency = new Currency(rs.getLong("id"), rs.getString("code"),  rs.getString("name"));
            currenciesList.add(currency);
        }
        return currenciesList;
    }
}
