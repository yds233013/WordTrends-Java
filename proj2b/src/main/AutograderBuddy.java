package main;

import browser.NgordnetQueryHandler;
import ngrams.NGramMap;
import wordnet.WordnetSem;


public class AutograderBuddy {
    /** Returns a HyponymHandler */
    public static NgordnetQueryHandler getHyponymHandler(
            String wordFile, String countFile,
            String synsetFile, String hyponymFile) {

        NGramMap ngm = new NGramMap(wordFile, countFile);
        return new HyponymsHandler(new WordnetSem(synsetFile, hyponymFile, ngm), ngm);

    }
}
