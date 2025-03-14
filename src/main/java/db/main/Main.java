package db.main;

import db.core.Database;
import db.table.Table;
import db.row.Row;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // データベースとテーブルのセットアップ
        Database db = new Database();
        db.createTable("users", Arrays.asList("id", "name", "is_deleted"));

        Table users = db.getTable("users");

        // ユーザーのデータ挿入
        users.insert(Map.of("id", 1, "name", "Alice", "is_deleted", false));
        users.insert(Map.of("id", 2, "name", "Bob", "is_deleted", false));

        // 更新前のデータ表示
        System.out.println("更新前のデータ一覧:");
        for (Row row : users.selectAll()) {
            System.out.println(row.getData());
        }

        // ID=2 のユーザーの名前を更新し、updated_at を自動設定
        users.update(2, Map.of("name", "Bob Smith"));

        // 更新後のデータ表示
        System.out.println("\n更新後のデータ一覧:");
        for (Row row : users.selectAll()) {
            System.out.println(row.getData());
        }

        // ID=2 のユーザーの情報を取得
        Row updatedUser = users.findById(2);
        System.out.println("\nID 2 の更新後のデータ:");
        System.out.println(updatedUser != null ? updatedUser.getData() : "該当なし");
    }
}
