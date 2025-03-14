package db.table;

import db.row.Row;
import java.util.*;
import java.util.stream.Collectors;

public class Table {
    private String name;
    private List<String> columns;
    private List<Row> rows = new ArrayList<>();
    private List<String> uniqueColumns = List.of("name");
    private int autoIncrementId = 1;

    public Table(String name, List<String> columns) {
        this.name = name;
        this.columns = columns;
    }

    public void insert(Map<String, Object> data) {
        for (String column : uniqueColumns) {
            Object value = data.get(column);
            if (value != null && isDuplicateValue(column, value)) {
                throw new IllegalArgumentException("重複する値が存在します: " + column + "=" + value + 
                        " (既存のID: " + findDuplicateRow(column, value).getData().get("id") + ")");
            }
        }
        Map<String, Object> insertedRowData = new HashMap<>(data);
        insertedRowData.putIfAbsent("id", autoIncrementId++);
        insertedRowData.putIfAbsent("created_at", new Date());
        rows.add(new Row(insertedRowData));
    }

    private boolean isDuplicateValue(String column, Object value) {
        return rows.stream()
                .anyMatch(row -> value.equals(row.getData().get(column)));
    }

    private Row findDuplicateRow(String column, Object value) {
        return rows.stream()
                .filter(row -> value.equals(row.getData().get(column)))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("重複する値が存在しません"));
    }

    public List<Row> selectAll() {
        return rows;
    }

    public List<Row> selectWhere(String column, Object value) {
        return rows.stream()
                .filter(row -> value.equals(row.getData().get(column)))
                .collect(Collectors.toList());
    }

    public void update(int id, Map<String, Object> newData) {
        rows.stream()
            .filter(row -> id == (int) row.getData().get("id"))
            .findFirst()
            .ifPresent(row -> {
                row.getData().putAll(newData);
                row.getData().put("updated_at", new Date());
            });
    }

    public void delete(String column, Object value) {
        rows.removeIf(row -> value.equals(row.getData().get(column)));
    }

    public Row findById(int id) {
        return rows.stream()
                .filter(row -> id == (int) row.getData().get("id"))
                .findFirst()
                .orElse(null);
    }

    public void softDelete(int id) {
        rows.stream()
            .filter(row -> id == (int) row.getData().get("id"))
            .findFirst()
            .ifPresent(row -> row.getData().put("is_deleted", true));
    }
}
