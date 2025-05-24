package ngrams;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * An object for mapping a year number (e.g. 1996) to numerical data. Provides
 * utility methods useful for data analysis.
 *
 * @author Josh Hug
 */
public class TimeSeries extends TreeMap<Integer, Double> {

    public static final int MIN_YEAR = 1400;
    public static final int MAX_YEAR = 2100;

    /**
     * Constructs a new empty TimeSeries.
     */
    public TimeSeries() {
        super();
    }

    /**
     * Creates a copy of TS, but only between STARTYEAR and ENDYEAR,
     * inclusive of both end points.
     */
    public TimeSeries(TimeSeries ts, int startYear, int endYear) {
        super();
        for (int y = startYear; y <= endYear; y++) {
            Double ans = ts.get(y);
            if (ans != null) {
                this.put(y, ans);
            }
        }
    }

    /**
     * Returns all years for this TimeSeries (in any order).
     */
    public List<Integer> years() {
        return new ArrayList<>(keySet());
    }

    /**
     * Returns all data for this TimeSeries (in any order).
     * Must be in the same order as years().
     */
    public List<Double> data() {
        List<Double> dataList = new ArrayList<>();
        for (Integer y : years()) {
            dataList.add(get(y));
        }
        return dataList;
    }

    /**
     * Returns the year-wise sum of this TimeSeries with the given TS. In other words, for
     * each year, sum the data from this TimeSeries with the data from TS. Should return a
     * new TimeSeries (does not modify this TimeSeries).
     *
     * If both TimeSeries don't contain any years, return an empty TimeSeries.
     * If one TimeSeries contains a year that the other one doesn't, the returned TimeSeries
     * should store the value from the TimeSeries that contains that year.
     */
    public TimeSeries plus(TimeSeries ts) {
        TimeSeries sum = new TimeSeries();
        for (Integer y : this.keySet()) {
            if (ts.containsKey(y)) {
                sum.put(y, this.get(y) + ts.get(y));
            } else {
                sum.put(y, this.get(y));
            }
        }
        for (Integer y : ts.keySet()) {
            if (!this.containsKey(y)) {
                sum.put(y, ts.get(y));
            }
        }
        return sum;
    }

    /**
     * Returns the quotient of the value for each year this TimeSeries divided by the
     * value for the same year in TS. Should return a new TimeSeries (does not modify this
     * TimeSeries).
     *
     * If TS is missing a year that exists in this TimeSeries, throw an
     * IllegalArgumentException.
     * If TS has a year that is not in this TimeSeries, ignore it.
     */
    public TimeSeries dividedBy(TimeSeries ts) {
        TimeSeries quot = new TimeSeries();
        for (Integer y : this.keySet()) {
            if (ts.containsKey(y)) {
                if (ts.get(y) == 0) {
                    throw new IllegalArgumentException(" Div by 0");
                }
                quot.put(y, this.get(y) / ts.get(y));
            }
        }
        return quot;
    }
}
