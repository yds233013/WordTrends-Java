package ngrams;

import edu.princeton.cs.algs4.In;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static ngrams.TimeSeries.MAX_YEAR;
import static ngrams.TimeSeries.MIN_YEAR;

/**
 * An object that provides utility methods for making queries on the
 * Google NGrams dataset (or a subset thereof).
 *
 * An NGramMap stores pertinent data from a "words file" and a "counts
 * file". It is not a map in the strict sense, but it does provide additional
 * functionality.
 *
 * @author Josh Hug
 */
public class NGramMap {
    private static final int MIN_YEAR = 1400;
    private static final int MAX_YEAR = 2100;
    private Map<String, TimeSeries> word1;
    private TimeSeries total;


    /**
     * Constructs an NGramMap from WORDSFILENAME and COUNTSFILENAME.
     */
    public NGramMap(String wordsFilename, String countsFilename) {
        word1 = new HashMap<>();
        total = new TimeSeries();

        In wordFile = new In(wordsFilename);
        while (wordFile.hasNextLine()) {
            String line = wordFile.readLine();
            String[] splitline = line.split("\t");
            if (splitline.length < 3) {
                throw new IndexOutOfBoundsException("Skipping line");
            }
            String word = splitline[0];
            int year = Integer.parseInt(splitline[1]);
            double count = Double.parseDouble(splitline[2]);

            word1.putIfAbsent(word, new TimeSeries());
            word1.get(word).put(year, count);
        }
        In countFile = new In(countsFilename);
        while (countFile.hasNextLine()) {
            String line = countFile.readLine();
            String[] splitline = line.split(",");
            if (splitline.length < 2) {
                throw new IndexOutOfBoundsException(" Skipping line");
            }
            int year = Integer.parseInt(splitline[0]);
            double count = Double.parseDouble(splitline[1]);
            total.put(year, count);
        }
    }

    /**
     * Provides the history of WORD between STARTYEAR and ENDYEAR, inclusive of both ends. The
     * returned TimeSeries should be a copy, not a link to this NGramMap's TimeSeries. In other
     * words, changes made to the object returned by this function should not also affect the
     * NGramMap. This is also known as a "defensive copy". If the word is not in the data files,
     * returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word, int startYear, int endYear) {
        TimeSeries hist = countHistory(word);
        return new TimeSeries(hist, startYear, endYear);
    }

    /**
     * Provides the history of WORD. The returned TimeSeries should be a copy, not a link to this
     * NGramMap's TimeSeries. In other words, changes made to the object returned by this function
     * should not also affect the NGramMap. This is also known as a "defensive copy". If the word
     * is not in the data files, returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word) {
        if (!word1.containsKey(word)) {
            return new TimeSeries();
        }
        return new TimeSeries(word1.get(word), MIN_YEAR, MAX_YEAR);
    }

    /**
     * Returns a defensive copy of the total number of words recorded per year in all volumes.
     */
    public TimeSeries totalCountHistory() {
        return new TimeSeries(total, MIN_YEAR, MAX_YEAR);
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD between STARTYEAR
     * and ENDYEAR, inclusive of both ends. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word, int startYear, int endYear) {
        TimeSeries hist = weightHistory(word);
        return new TimeSeries(hist, startYear, endYear);
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD compared to all
     * words recorded in that year. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word) {
        if (!word1.containsKey(word)) {
            return new TimeSeries();
        }
        TimeSeries wordcount = word1.get(word);
        TimeSeries weights = new TimeSeries();

        for (int year: wordcount.keySet()) {
            double weight = wordcount.get(year) / total.get(year);
            weights.put(year, weight);
        }
        return weights;
    }

    /**
     * Provides the summed relative frequency per year of all words in WORDS between STARTYEAR and
     * ENDYEAR, inclusive of both ends. If a word does not exist in this time frame, ignore it
     * rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words,
                                          int startYear, int endYear) {
        TimeSeries summedweight = new TimeSeries();
        for (String word : words) {
            TimeSeries wordWeight = weightHistory(word, startYear, endYear);
            for (int year : wordWeight.keySet()) {
                summedweight.put(year, summedweight.getOrDefault(year, 0.0) + wordWeight.get(year));

            }
        }
        return summedweight;
    }

    /**
     * Returns the summed relative frequency per year of all words in WORDS. If a word does not
     * exist in this time frame, ignore it rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words) {
        TimeSeries summedWeight = new TimeSeries();
        for (String word : words) {
            TimeSeries wordWeight = weightHistory(word);
            for (int year : wordWeight.keySet()) {
                summedWeight.put(year, summedWeight.getOrDefault(year, 0.0) + wordWeight.get(year));
            }
        }
        return summedWeight;
    }
}
