package ngrams;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * A placeholder implementation of TimeSeries.
 */
public class TimeSeries extends TreeMap<Integer, Double> {
    public List<Integer> years() {
        return new ArrayList<>(keySet());
    }

    public List<Double> data() {
        List<Double> dataList = new ArrayList<>();
        for (Integer y : years()) {
            dataList.add(get(y));
        }
        return dataList;
    }
}
