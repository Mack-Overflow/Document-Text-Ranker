package ranking;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ranking.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.Iterator;

public class Search {

    public static void main(String[] args) {
        Document doc = null;
        // call read
        try {
            // doc = Jsoup.connect(args[1]).get(); // runtime cli argument
            doc = Jsoup.connect("https://en.wikipedia.org/wiki/Alice%27s_Adventures_in_Wonderland").get();

            String title = doc.title();
            System.out.println("Title: " + title);

            Elements divs = doc.select("div.mw-parser-output > p");
            Elements links = doc.getElementsByAttribute("href");

            // Hash Map holding words and their occurences
            Map<String, Integer> words = new TreeMap<>();
            // Hold Nodes and their count
            Map<String, Node> nodes = new TreeMap<String, Node>();
            String s[] = new String[divs.size()];
            // Create buffer string array to hold value of input
            s = divs.text().split("([\\,.\\s]+)");

            /**
             * Text Ranking Algo: Take input of the input Element divs, Map <String, Node>
             *
             */
            for (int i = 0; i < s.length; i++) {
                String key = s[i];
                int count = words.getOrDefault(key, 0);
                words.put(key, ++count);
            }

            // for (Entry<String, Integer> result : words.entrySet()) {
            // System.out.println(result.getKey() + " " + result.getValue());
            // }

            // for (String el : s) {

            // el = el.toLowerCase();
            // // check if current word is already in Node
            // Boolean contains = words.containsKey(el);

            // if (!contains) { // Create new node if none exist
            // // For this word
            // Node node = new Node(el, 1);
            // nodes.put(el, node);
            // words.computeIfAbsent(el, (w) -> Integer.valueOf(1));

            // } else {
            // // Change nodes count
            // Node node = nodes.get(el);
            // Integer word = words.get(el);
            // node.count++;
            // words.computeIfPresent(el, (w, c) -> Integer.valueOf(c.intValue() + 1));
            // words.put(el, word++);
            // }

            // }

            printLinks(15, links);

            printPopular(15, words, nodes);

        } catch (

        IOException e) {
            e.printStackTrace();
        }

        /**
         * Print
         */
    }

    public static void printPopular(int numWords, Map<String, Integer> words, Map<String, Node> nodes) {
        Set<Entry<String, Integer>> entrySet = words.entrySet();
        List<Entry<String, Integer>> wordList = new ArrayList<>(entrySet);
        Collections.sort(wordList, (o2, o1) -> o1.getValue().compareTo(o2.getValue()));
        int[] index = { 0 };
        System.out.printf("Printing most popular %d words \n\n", numWords);
        wordList.forEach(s -> {
            if (index[0] == numWords) {
                return;
            }
            System.out.println(s.getKey() + "\t" + s.getValue());
            index[0] += 1;
        });

    }
    // List<Entry<String, Integer>> unsorted = new LinkedList<>(words.entrySet());
    // Collections.sort(unsorted, new Comparator<Map.Entry<String, Integer>>() {
    // @Override
    // public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
    // return o1.getValue().compareTo(o2.getValue());
    // }
    // });
    // Map<String, Integer> sortedWords = new LinkedHashMap<>(unsorted.size());
    // for (Entry<String, Integer> word : unsorted) {
    // sortedWords.put(word.getKey(), word.getValue());
    // }

    // Collection<String> keys = sortedWords.keySet();
    // Collection<Integer> vals = sortedWords.values();

    // Iterator i = keys.iterator();
    // Iterator j = vals.iterator();
    // int index = 0;
    // while (i.hasNext() && j.hasNext() && index < numWords) {
    // System.out.println("Word : " + i.next() + "\n Count: " + j.next());
    // index++;
    // }

    public static void printLinks(int numLinks, Elements links) {
        int currDepth = 0;

        System.out.printf("First %d links in the page: \n\n", numLinks);
        for (Element link : links) {
            if (currDepth == numLinks) {
                return;
            }
            currDepth++;
            String href = link.attr("href");
            System.out.println(href);

        }
        System.out.println("\n\n");
    }
}
