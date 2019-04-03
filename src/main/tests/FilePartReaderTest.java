import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

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
        assertThrows(IllegalArgumentException.class, () -> this.filePartReader.setup("src/resources/text.txt", -1, 3));
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

    @Test
    void testIfWordsAreReturnedAlphabetically() throws IOException {
        List<String> result = Arrays.asList("costam", "linijka", "trzecia", "tutaj");
        filePartReader.setup("src/resources/text.txt", 3, 3);
        FileWordAnalyzer fileWordAnalyzer = new FileWordAnalyzer(filePartReader);

        assertEquals(result, fileWordAnalyzer.getWordsOrderedAlphabetically());

        result = Arrays.asList("druga", "linijka", "linijka", "pierwsza");
        filePartReader.setup("src/resources/text.txt", 1, 2);
        fileWordAnalyzer = new FileWordAnalyzer(filePartReader);

        assertEquals(result, fileWordAnalyzer.getWordsOrderedAlphabetically());
    }

    @Test
    void testIfWordsContainingSubstringAreReturnedCorrectly() throws IOException {
        filePartReader.setup("src/resources/text.txt", 1, 2);
        FileWordAnalyzer fileWordAnalyzer = new FileWordAnalyzer(filePartReader);
        List<String> result = Arrays.asList("linijka", "linijka");

        assertEquals(result, fileWordAnalyzer.getWordsContainingSubstring("nijk"));

        filePartReader.setup("src/resources/text.txt", 1, 6);
        fileWordAnalyzer = new FileWordAnalyzer(filePartReader);
        result = Arrays.asList("pierwsza", "za");

        assertEquals(result, fileWordAnalyzer.getWordsContainingSubstring("za"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"sedes", "kajak", "anna", "i"})
    void testIfPalindrome(String str) {
        FileWordAnalyzer fileWordAnalyzer = new FileWordAnalyzer(filePartReader);
        assertTrue(fileWordAnalyzer.checkIfPalindrome(str));
    }

    @ParameterizedTest
    @ValueSource(strings = {"hmm", "notPalindrom", "costam", "12"})
    void testIfNotPalindrome(String str) {
        FileWordAnalyzer fileWordAnalyzer = new FileWordAnalyzer(filePartReader);
        assertFalse(fileWordAnalyzer.checkIfPalindrome(str));
    }

    @Test
    void testIfPalindromesAreCorrectlyReturned() throws IOException {
        filePartReader.setup("src/resources/text.txt", 1, 6);
        FileWordAnalyzer fileWordAnalyzer = new FileWordAnalyzer(filePartReader);
        List<String> result = Arrays.asList("i", "i", "kajak");
        assertEquals(result, fileWordAnalyzer.getStringsWhichPalindromes());
    }
}