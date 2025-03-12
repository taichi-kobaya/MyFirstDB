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

        // ソフト削除: ID=1 のユーザーを削除
        System.out.println("ソフト削除前のデータ一覧:");
        for (Row row : users.selectAll()) {
            System.out.println(row.getData());
        }

        users.softDelete(1);  // ID=1 のユーザーを論理削除

        // ソフト削除後のデータ表示
        System.out.println("\nソフト削除後のデータ一覧:");
        for (Row row : users.selectAll()) {
            System.out.println(row.getData());
        }

        // ID=1 のユーザーを再度取得して表示
        Row user = users.findById(1);
        System.out.println("\nID 1 のユーザー:");
        System.out.println(user != null ? user.getData() : "該当なし");

        // 他のユーザーのデータも確認
        Row user2 = users.findById(2);
        System.out.println("\nID 2 のユーザー:");
        System.out.println(user2 != null ? user2.getData() : "該当なし");

        // アクティブなユーザーの一覧を取得
        System.out.println("\nアクティブなユーザー一覧:");
        for (Row row : users.selectWhere("is_deleted", false)) {
            System.out.println(row.getData());
        }

        // その他の操作のテスト
        /*
        users.insert(Map.of("id", 3, "name", "Charlie"));
        users.update("id", 1, Map.of("name", "Alice Cooper", "is_active", false));
        users.delete("id", 2);
        Row user = users.findById(1);
        */
    }
}
