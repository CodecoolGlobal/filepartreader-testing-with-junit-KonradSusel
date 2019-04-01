import java.io.*;

public class FilePartReader {
    private String filePath;
    private Integer fromLine;
    private Integer toLine;

    public FilePartReader() {
        this.fromLine = -1;
        this.toLine = -2;
    }

    public void setup(String filePath, Integer fromLine, Integer toLine) throws IllegalArgumentException {
        if (toLine < fromLine || fromLine < 1) {
            throw new IllegalArgumentException();
        } else {
            this.filePath = filePath;
            this.fromLine = fromLine;
            this.toLine = toLine;
        }
    }

    public String read(String filepath) throws IOException {
        File file = new File(filepath);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        String ls = System.getProperty("line.separator");
        while ((line = br.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append(ls);
        }
        return stringBuilder.toString();
    }

    public String readLines() throws IOException {
        String[] lines = read(filePath).split(System.getProperty("line.separator"));
        StringBuilder stringBuilder = new StringBuilder();
        String ls = System.getProperty("line.separator");

        for (int i = fromLine; i <= toLine ; i++) {
            stringBuilder.append(lines[i-1]);
            stringBuilder.append(ls);
        }
        return stringBuilder.toString();
    }
}
