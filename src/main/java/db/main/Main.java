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

        // users.update("id", 1, Map.of("name", "Alice Cooper", "is_active", false));

        List<Row> activeUsers = users.selectWhere("is_active", true);
        
        // users.delete("id", 2);

        // users.insert(Map.of("id", 3, "name", "Charlie"));

        // users.insert(Map.of("name", "David"));

        Row user = users.findById(1);
        System.out.println("Id 1 のユーザー: " + (user != null ? user.getData() : "該当なし"));

        System.out.println();

        System.out.println("データ一覧:");
        for (Row row : users.selectAll()) {
            System.out.println(row.getData());
        }

        System.out.println();
        
        System.out.println("アクティブなユーザー一覧:");
        for (Row row : users.selectWhere("is_active", true)) {
            System.out.println(row.getData());
        }
    }
}
