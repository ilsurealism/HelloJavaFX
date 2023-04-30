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
                       "order by b.creation_dt desc, at.id, b.asset_id, b.sum desc;";
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

    public static ObservableList<BudgetTotal> selectBudgetTotal() throws SQLException, ClassNotFoundException {
        String sql = "with t as (select row_number() over (partition by b.asset_id, b.currency_id order by b.creation_dt desc) rownum, \n" +
                "b.id budget_id, b.asset_id,  a.name asset_name, at.name asset_type_name,  b.currency_id,  c.code currency_code,  b.sum\n" +
                "from budget b inner join assets a on a.id = b.asset_id  \n" +
                "inner join assets_types at on at.id = a.asset_type_id \n" +
                "inner join currencies c on c.id = b.currency_id \n" +
                "order by b.sum desc,  b.asset_id),\n" +
                "sum_by_at as (select t.asset_type_name || ' -> total' asset_type_name, t.currency_id, t.currency_code, round(sum(t.sum), 2) sum, 2 lvl\n" +
                "from t where t.rownum = 1 \n" +
                "group by t.asset_type_name, t.currency_id, t.currency_code),\n" +
                "sum_by_curr as (select t.currency_id, t.currency_code || ' -> total' currency_code, round(sum(t.sum), 2) sum, 3 lvl\n" +
                "from t where t.rownum = 1 \n" +
                "group by t.currency_id, t.currency_code)\n" +
                "select m.budget_id, m.asset_id, m.asset_name, m.asset_type_name, m.currency_id, m.currency_code, m.sum, m.lvl\n" +
                "  from (select t.budget_id budget_id, t.asset_id asset_id, t.asset_name asset_name, t.asset_type_name asset_type_name, t.currency_id currency_id, t.currency_code currency_code, t.sum sum, 1 lvl\n" +
                "from t where t.rownum = 1\n" +
                "union all\n" +
                "select null budget_id, null asset_id, null asset_name, at.asset_type_name asset_type_name, at.currency_id currency_id, at.currency_code currency_code, at.sum sum, at.lvl lvl\n" +
                "from sum_by_at at\n" +
                "union all\n" +
                "select null budget_id, null asset_id, null asset_name, null asset_type_name, c.currency_id currency_id, c.currency_code currency_code, c.sum sum, c.lvl lvl\n" +
                "from sum_by_curr c) m \n" +
                "order by m.asset_type_name nulls last, m.lvl,  m.asset_id, m.currency_id;\n";
        try {
            ResultSet rsBudget = DBUtil.dbExecuteQuery(sql);
//            ObservableList<Budget> budgetList = getBudgetList(rsBudget);
            ObservableList<BudgetTotal> budgetList = getBudgetTotalList(rsBudget);
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
    private static ObservableList<BudgetTotal> getBudgetTotalList(ResultSet rs) throws SQLException, ClassNotFoundException {
        ObservableList<BudgetTotal> budgetList = FXCollections.observableArrayList();
        while (rs.next()) {
            BudgetTotal budget = new BudgetTotal(rs.getLong("budget_id"),
                    rs.getLong("asset_id"),
                    rs.getString("asset_name"),
                    rs.getString("asset_type_name"),
                    rs.getLong("currency_id"),
                    rs.getString("currency_code"),
                    rs.getDouble("sum"),
                    rs.getInt("lvl"));
            budgetList.add(budget);
        }
        return budgetList;
    }

}
