package db.sql;

// SQLの解析クラス（簡易版）
public class SQLParser {
    public static String parseCommand(String query) {
        return query.split(" ")[0].toUpperCase();
    }
}
