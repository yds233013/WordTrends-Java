package main;

import browser.NgordnetServer;
import ngrams.NGramMap;
import wordnet.WordnetSem;

public class Main {
    public static void main(String[] args) {
        NgordnetServer hns = new NgordnetServer();

        String wordFile = "./data/ngrams/top_14377_words.csv";
        String countFile = "./data/ngrams/total_counts.csv";

        String synsetFile = "./data/wordnet/synsets.txt";
        String hyponymFile = "./data/ngrams/total_counts.csv";
        NGramMap gmn = new NGramMap(wordFile, countFile);
        WordnetSem gmw = new WordnetSem(synsetFile, hyponymFile, gmn);
        hns.startUp();
        hns.register("history", new DummyHistoryHandler());
        hns.register("historytext", new DummyHistoryTextHandler());
        hns.register("hyponyms", new HyponymsHandler(gmw, gmn));


        System.out.println("Finished server startup! Visit http://localhost:4567/ngordnet.html");
    }
}
