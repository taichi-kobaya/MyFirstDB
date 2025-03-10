package db.table;

import db.row.Row;
import java.util.*;

// テーブル管理クラス
public class Table {
    private String name;
    private List<String> columns;
    private List<Row> rows = new ArrayList<>();

    public Table(String name, List<String> columns) {
        this.name = name;
        this.columns = columns;
    }

    public void insert(Map<String, Object> data) {
        rows.add(new Row(data));
    }

    public List<Row> selectAll() {
        return rows;
    }

    public List<Row> selectWhere(String column, Object value) {
        List<Row> result = new ArrayList<>();
        for (Row row : rows) {
            if (value.equals(row.getData().get(column))) {
                result.add(row);
            }
        }
        return result;
    }

    public void update(String column, Object oldValue, Map<String, Object> newValues) {
        for (Row row : rows) {
            if (oldValue.equals(row.getData().get(column))) {
                row.getData().putAll(newValues);
            }
        }
    } 

    public void delete(String column, Object value) {
        rows.removeIf(row -> value.equals(row.getData().get(column)));
    }
}

