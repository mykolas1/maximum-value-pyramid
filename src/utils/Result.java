package utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Util class which stores longest path value, path, and even flag information.
 */
public class Result {
    public int sumValue;
    public boolean evenFlag;
    private Map paths = new HashMap<Integer, List<Integer>>();

    public Map getPaths() {
        return paths;
    }

    public void setPaths(HashMap paths) {
        this.paths = paths;
    }

    public int getSumValue() {
        return sumValue;
    }

    public boolean isEvenFlag() {
        return evenFlag;
    }
}
