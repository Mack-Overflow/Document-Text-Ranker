import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import jdk.nashorn.api.tree.WhileLoopTree;

import java.io.IOException;

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

            // rank words by most frequent occurence
            rankText();
            // print first n most common words in descending order (highest count to lower)
            printPopular();
            // call print links. Input params are # of links to print, links
            printLinks(15, links);
            // ensure proper output of divs
            // for (Element div : divs) {
            // // System.out.println("\n Link : " + div.attr("href"));
            // System.out.println("Text : " + div.text());
            // }

        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
         * Print
         */
    }

    private static void rankText() {

    }

    private static void printPopular() {

    }

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