import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileWordAnalyzer {
    private final FilePartReader filePartReader;

    public FileWordAnalyzer(FilePartReader filePartReader) {
        this.filePartReader = filePartReader;
    }

    public List getWordsOrderedAlphabetically () throws IOException {
        String text = filePartReader.readLines();
        String lines[] = text.split("\n");
        List<String> words = new ArrayList<>();
        for (int i = 0; i < lines.length; i++) {
            String[] strings = lines[i].split(" ");
            for (String word : strings) {
                if (!word.equals(" ") && !word.isEmpty()) {
                    words.add(word);
                }
            }
        }
        Collections.sort(words);
        return words;
    }

    public List getWordsContainingSubstring (String subString) throws IOException {
        String text = filePartReader.readLines();
        String lines[] = text.split("\n");
        List<String> words = new ArrayList<>();
        for (int i = 0; i < lines.length; i++) {
            String[] strings = lines[i].split(" ");
            for (String word : strings) {
                if (!word.equals(" ") && !word.isEmpty()) {
                    if (word.contains(subString)) {
                        words.add(word);
                    }
                }
            }
        }
        return words;
    }

    public List getStringsWhichPalindromes() throws IOException {
        String text = filePartReader.readLines();
        String lines[] = text.split("\n");
        List<String> words = new ArrayList<>();
        for (int i = 0; i < lines.length; i++) {
            String[] strings = lines[i].split(" ");
            for (String word : strings) {
                if (!word.equals(" ") && !word.isEmpty()) {
                    if(checkIfPalindrome(word)) {
                        words.add(word);
                    }
                }
            }
        }
        return words;
    }

    public boolean checkIfPalindrome(String str) {
        str = str.replaceAll("[\\W]", "");
        if (str.equals("")) {
            return false;
        }
        str = str.toLowerCase();
        return new StringBuilder(str).reverse().toString().equals(str);
    }
}
