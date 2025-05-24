package main;

import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import ngrams.NGramMap;
import wordnet.WordnetSem;

import java.util.List;


public class HyponymsHandler extends NgordnetQueryHandler {
    private final WordnetSem gmw;
    public HyponymsHandler(WordnetSem m, NGramMap n) {
        gmw = m;
    }
    public String handle(NgordnetQuery query) {
        List<String> words = query.words();
        int startYear = query.startYear();
        int endYear = query.endYear();
        int k = query.k();

        return gmw.getHyponymWords(words, startYear, endYear, k).toString();


    }
}
