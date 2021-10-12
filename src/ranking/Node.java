package ranking;

public class Node {
    String name;
    int count;

    public Node(String word, Integer count) {
        name = word;
        count = 0;
        int index = 0;
    }

    public String text(String name) {
        return name;
    }

    public Integer count(int count) {
        return count;
    }

    public static void setText(String text) {
        String name = text;
    }
}
