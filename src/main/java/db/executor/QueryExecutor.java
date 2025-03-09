package db.executor;

import db.core.Database;
import db.sql.SQLParser;

// SQLの実行クラス
public class QueryExecutor {
    private Database db;

    public QueryExecutor(Database db) {
        this.db = db;
    }

    public void execute(String query) {
        String command = SQLParser.parseCommand(query);
        if (command.equals("INSERT")) {
            System.out.println("INSERT クエリを実行");
        } else if (command.equals("SELECT")) {
            System.out.println("SELECT クエリを実行");
        } else {
            System.out.println("未対応のクエリ");
        }
    }
}
