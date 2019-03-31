import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        FilePartReader filePartReader = new FilePartReader();
        filePartReader.setup("src/resources/text.txt", 1, 2);
        System.out.println(filePartReader.readLines());
    }
}
