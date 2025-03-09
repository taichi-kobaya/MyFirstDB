package db.core;

import db.table.Table;
import java.util.*;

// データベース管理クラス
public class Database {
    private Map<String, Table> tables = new HashMap<>();

    public void createTable(String name, List<String> columns) {
        tables.put(name, new Table(name, columns));
    }

    public Table getTable(String name) {
        return tables.get(name);
    }
}
