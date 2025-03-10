package db.main;

import db.core.Database;
import db.table.Table;
import db.row.Row;
import java.util.*;

// 実行テスト用クラス
public class Main {
    public static void main(String[] args) {
        Database db = new Database();
        db.createTable("users", Arrays.asList("id", "name"));
        
        Table users = db.getTable("users");
        users.insert(Map.of(
            "id", 1,
            "name", "Alice",
            "created_at", new Date(),
            "is_active", true
        ));
        
        users.insert(Map.of(
            "id", 2,
            "name", "Bob",
            "created_at", new Date(),
            "is_active", false
        ));
        
        System.out.println("データ一覧:");
        for (Row row : users.selectAll()) {
            System.out.println(row.getData());
        }
    }
}
