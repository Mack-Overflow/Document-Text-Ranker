import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;

public class Search {

    public static void main(String[] args) {
        Document doc = null;

        try {
            // doc = Jsoup.connect(args[1]).get(); // runtime cli argument
            doc = Jsoup.connect("https://en.wikipedia.org/wiki/Alice%27s_Adventures_in_Wonderland").get();

            String title = doc.title();
            System.out.println("Title: " + title);

            Elements divs = doc.select("div.mw-parser-output > p");

            // System.out.println(divs);
            for (Element div : divs) {
                // System.out.println("\n Link : " + div.attr("href"));
                System.out.println("Text : " + div.text());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}