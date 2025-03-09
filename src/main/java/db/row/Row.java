package db.row;

import java.util.*;

// 1行のデータを管理するクラス
public class Row {
    private Map<String, String> data;

    public Row(Map<String, String> data) {
        this.data = new HashMap<>(data);
    }

    public Map<String, String> getData() {
        return data;
    }
}
