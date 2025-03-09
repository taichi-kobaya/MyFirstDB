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

    public void insert(Map<String, String> data) {
        rows.add(new Row(data));
    }

    public List<Row> selectAll() {
        return rows;
    }
}
