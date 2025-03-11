package db.table;

import db.row.Row;
import java.util.*;

// テーブル管理クラス
public class Table {
    private String name;
    private List<String> columns;
    private List<Row> rows = new ArrayList<>();
    private int autoIncrementId = 1;

    public Table(String name, List<String> columns) {
        this.name = name;
        this.columns = columns;
    }

    public void insert(Map<String, Object> data) {
        Map<String, Object> rowData = new HashMap<>(data);
        rowData.putIfAbsent("id", autoIncrementId++);
        rowData.putIfAbsent("created_at", new Date());
        rows.add(new Row(rowData));
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

    public Row findById(int id) {
        return rows.stream()
            .filter(row -> id == (int) row.getData().get("id"))
            .findFirst()
            .orElse(null); // 該当データがない場合は null を返す
    }
}

