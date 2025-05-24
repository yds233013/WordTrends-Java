package wordnet;

import edu.princeton.cs.algs4.In;
import ngrams.NGramMap;

import java.util.*;

public class WordnetSem {
    private final Graph<Integer> net;
    private final Map<String, List<Integer>> wordMapping;
    private final Map<Integer, List<String>> synsetMapping;
    private final NGramMap ngm;

    public WordnetSem(String synset, String hyponyms, NGramMap ngm) {
        net = new Graph<>();
        wordMapping = new HashMap<>();
        synsetMapping = new HashMap<>();
        this.ngm = ngm;

        In synsetDare = new In(synset);
        for (; synsetDare.hasNextLine();) {
            String[] line = synsetDare.readLine().split(",");
            int id = Integer.parseInt(line[0]);
            String[] words = line[1].split(" ");
            for (String word : words) {
                wordMapping.putIfAbsent(word, new ArrayList<>());
                wordMapping.get(word).add(id);
                synsetMapping.putIfAbsent(id, new ArrayList<>());
                synsetMapping.get(id).add(word);
            }
        }
        In hyponymsField = new In(hyponyms);
        for (; hyponymsField.hasNextLine();) {
            String[] line = hyponymsField.readLine().split(",");
            int parent = Integer.parseInt(line[0]);
            net.addVertex(parent);
            for (int i = 1; i < line.length; i++) {
                int child = Integer.parseInt(line[i]);
                net.addVertex(child);
                net.addcorner(parent, child);
            }
        }
    }

    public Set<String> getHyponymWords(String word) {
        Set<String> returnWords = new TreeSet<>();
        List<Integer> ids = wordMapping.getOrDefault(word, new ArrayList<>());
        for (int id : ids) {
            returnWords.addAll(synsetMapping.get(id));
            List<Integer> childrenIds = net.getdescend(id);
            for (int childId : childrenIds) {
                returnWords.addAll(synsetMapping.get(childId));
            }
        }
        return returnWords;
    }

    public Set<String> getHyponymWords(List<String> words) {
        Set<String> returnWords = new TreeSet<>();
        Map<String, Set<String>> cache = new HashMap<>();
        if (words.size() == 1) {
            return getHyponymWords(words.get(0));
        }
        for (String word : words) {
            cache.put(word, getHyponymWords(word));
            returnWords.addAll(cache.get(word));
        }
        for (String word : words) {
            returnWords.retainAll(cache.get(word));
        }
        return returnWords;
    }

    private double wordFrequency(String word, int startYear, int endYear) {
        List<Double> totalCount = ngm.countHistory(word, startYear, endYear).data();
        return totalCount.stream().mapToDouble(Double::doubleValue).sum();
    }

    public Set<String> getHyponymWords(List<String> words, int startYear, int endYear, int M) {
        Set<String> hyponyms = getHyponymWords(words);
        if (M == 0) {
            return hyponyms;
        }
        TreeMap<Double, List<String>> orderedHyponyms = new TreeMap<>();
        for (String word : hyponyms) {
            double frequency = wordFrequency(word, startYear, endYear);
            orderedHyponyms.putIfAbsent(frequency, new ArrayList<>());
            orderedHyponyms.get(frequency).add(word);
        }
        orderedHyponyms.remove(0.0);
        Set<String> selectedHyponyms = new TreeSet<>();
        for (Iterator<Map.Entry<Double, List<String>>> it = orderedHyponyms.descendingMap().entrySet().iterator();
             selectedHyponyms.size() < Math.min(M, orderedHyponyms.size()) && it.hasNext();) {
            Map.Entry<Double, List<String>> entry = it.next();
            selectedHyponyms.addAll(entry.getValue());
        }
        return selectedHyponyms;
    }
}
