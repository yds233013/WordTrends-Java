package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import ngrams.NGramMap;
import ngrams.TimeSeries;
import org.knowm.xchart.XYChart;
import plotting.Plotter;

import java.util.ArrayList;
import java.util.List;

public class HistoryHandler extends NgordnetQueryHandler {
    private NGramMap tool;
    public HistoryHandler(NGramMap map) {
        this.tool = map;
    }
    @Override
    public String handle(NgordnetQuery quer) {
        List<String> words = quer.words();
        int startYear = quer.startYear();
        int endYear = quer.endYear();

        List<TimeSeries> timeSeriesList = new ArrayList<>();
        for (String word: words) {
            TimeSeries t = tool.weightHistory(word, startYear, endYear);
            timeSeriesList.add(t);
        }
        XYChart chart = Plotter.generateTimeSeriesChart(words, timeSeriesList);
        String encode = Plotter.encodeChartAsString(chart);
        return encode;
    }
}
