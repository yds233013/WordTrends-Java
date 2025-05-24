package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import ngrams.NGramMap;
import java.util.List;

public class HistoryTextHandler extends NgordnetQueryHandler {
    private NGramMap tools;
    public HistoryTextHandler(NGramMap map) {
        this.tools = map;
    }
    @Override
    public String handle(NgordnetQuery querr) {
        List<String> words = querr.words();
        int startYear = querr.startYear();
        int endYear = querr.endYear();
        StringBuilder ans = new StringBuilder();

        for (String word : words) {
            ans.append(word).append(": ").append(tools.weightHistory(word, startYear, endYear)).append("\n");
        }
        return ans.toString();

    }
}
