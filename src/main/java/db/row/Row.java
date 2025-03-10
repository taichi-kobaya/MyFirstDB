package db.row;

import java.util.*;

public class Row {
    private Map<String, Object> data;

    public Row(Map<String, Object> data) {
        this.data = new HashMap<>(data);
    }

    public Map<String, Object> getData() {
        return data;
    }

    public Object getValue(String key) {
        return data.get(key);
    }
}
