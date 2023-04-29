package com.example.hellojavafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BudgetDAO {
    public static ObservableList<Budget> selectBudget() throws SQLException, ClassNotFoundException {
        String sql = "select b.id budget_id, \n" +
                            "b.asset_id, \n" +
                            "a.name asset_name, \n" +
                            "at.name asset_type_name, \n" +
                            "b.currency_id, \n" +
                            "c.code currency_code, \n" +
                            "b.sum, \n" +
                            "strftime('%d.%m.%Y %H:%M', b.creation_dt) creation_dt \n" +
                       "from budget b inner join assets a on a.id = b.asset_id  \n" +
                                     "inner join assets_types at on at.id = a.asset_type_id \n" +
                                     "inner join currencies c on c.id = b.currency_id \n" +
                       "order by at.id, b.asset_id, b.sum desc;";
        try {
            ResultSet rsBudget = DBUtil.dbExecuteQuery(sql);
            ObservableList<Budget> budgetList = getBudgetList(rsBudget);
            return budgetList;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getSQLState());
            System.out.println(e.getErrorCode());
            System.out.println(e);
            throw e;
        }
    }

    private static ObservableList<Budget> getBudgetList(ResultSet rs) throws SQLException, ClassNotFoundException {
        ObservableList<Budget> budgetList = FXCollections.observableArrayList();
        while (rs.next()) {
            Budget budget = new Budget(rs.getLong("budget_id"),
                                       rs.getLong("asset_id"),
                                       rs.getString("asset_name"),
                                       rs.getString("asset_type_name"),
                                       rs.getLong("currency_id"),
                                       rs.getString("currency_code"),
                                       rs.getDouble("sum"),
                                       rs.getString("creation_dt"));
            budgetList.add(budget);
        }
        return budgetList;
    }
}
