import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class FilePartReaderTest {
    private FilePartReader filePartReader;

    @BeforeEach
    void setUp() {
        FilePartReader filePartReader = new FilePartReader();
        this.filePartReader = filePartReader;
    }

    @Test
    void testIfIllegalArgumentExceptionIsThrown() {
        assertThrows(IllegalArgumentException.class, () ->
                this.filePartReader.setup("src/resources/text.txt", -1, 3)
        );
        assertThrows(IllegalArgumentException.class, () -> this.filePartReader.setup("src/resources/text.txt", 1, -3));
        assertThrows(IllegalArgumentException.class, () -> this.filePartReader.setup("src/resources/text.txt", 0, 3));
        assertThrows(IllegalArgumentException.class, () -> this.filePartReader.setup("src/resources/text.txt", 1, 0));
        assertThrows(IllegalArgumentException.class, () -> this.filePartReader.setup("src/resources/text.txt", 5, 4));
        assertThrows(IllegalArgumentException.class, () -> this.filePartReader.setup("src/resources/text.txt", 4, 0));
    }

    @Test
    void testIfIOExceptionIsThrownIfFileIsNotFound() {
        assertThrows(IOException.class, () -> filePartReader.read("bad/filepath"));
        assertDoesNotThrow( () -> filePartReader.read("src/resources/text.txt"));
    }

    @Test
    void testIfFirstLineIsReadCorrectly() throws IOException {
        filePartReader.setup("src/resources/text.txt", 1,1);
        assertEquals("pierwsza linijka", filePartReader.readLines());
    }

    @Test
    void testIfTwoOrMoreLinesAreReadCorrectly() throws IOException {
        filePartReader.setup("src/resources/text.txt", 1,2);
        assertEquals("pierwsza linijka\ndruga linijka", filePartReader.readLines());
        filePartReader.setup("src/resources/text.txt", 4,6);
        assertEquals("czwarta i za chwile bedzie piata\ni jest\n" +
                        "kajak palindrom", filePartReader.readLines());
    }

    @Test
    void testIfLastLineIsReadCorrectly() throws IOException {
        filePartReader.setup("src/resources/text.txt", 6,6);
        assertEquals("kajak palindrom", filePartReader.readLines());
    }
}