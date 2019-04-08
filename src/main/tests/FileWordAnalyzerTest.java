import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class FileWordAnalyzerTest {

    private FilePartReader filePartReader;
    private FileWordAnalyzer fileWordAnalyzer;

    @BeforeEach
    void setUp() {
        FilePartReader filePartReader = mock(FilePartReader.class);
        this.filePartReader = filePartReader;
        FileWordAnalyzer fileWordAnalyzer = new FileWordAnalyzer(filePartReader);
        this.fileWordAnalyzer = fileWordAnalyzer;
    }

    @Test
    @DisplayName("Test if getWordsOrderedAlphabetically() method returns list of words in alphabetical order")
    void testIfgetWordsOrderedAlphabeticallyMethodReturnsListOfWordsInAlphabeticalOrder() throws IOException {
        List<String> result = Arrays.asList("costam", "linijka", "trzecia", "tutaj");
        when(filePartReader.readLines()).thenReturn("trzecia linijka tutaj costam\n");

        List actual = fileWordAnalyzer.getWordsOrderedAlphabetically();

        assertEquals(result, actual);
    }

    @Test
    @DisplayName("Test if getWordsContainingSubstring() method returns list of words that contain substring")
    void testIfgetWordsContainingSubstringMethodReturnsListOfWordsThatContainSubstring() throws IOException {
        when(filePartReader.readLines()).thenReturn("pierwsza linijka\ndruga linijka");
        List<String> result = Arrays.asList("linijka", "linijka");

        List actual = fileWordAnalyzer.getWordsContainingSubstring("nijk");

        assertEquals(result, actual);
    }

    @Test
    @DisplayName("Test if getWordsContainingSubstring() method returns empty list if no words containing substring were found ")
    void testIfgetWordsContainingSubstringMethodReturnsEmptyListIfNoWordsContainingSubstringWereFound() throws IOException {
        when(filePartReader.readLines()).thenReturn("pierwsza linijka\ndruga linijka");
        List<String> expected = new ArrayList<>();

        List actual = fileWordAnalyzer.getWordsContainingSubstring("aaaaaaaaaaaa");

        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @DisplayName("Test if checkIfPalindrome() method returns true if given word is a palindrome")
    @ValueSource(strings = {"sedes", "kajak", "anna", "i"})
    void testIfPalindrome(String str) {
        assertTrue(fileWordAnalyzer.checkIfPalindrome(str));
    }

    @ParameterizedTest
    @DisplayName("Test if checkIfPalindrome() method returns false if given word is not a palindrome")
    @ValueSource(strings = {"hmm", "notPalindrom", "costam", "12"})
    void testIfNotPalindrome(String str) {
        assertFalse(fileWordAnalyzer.checkIfPalindrome(str));
    }

    @Test
    @DisplayName("Test if getStringsWhichPalindromes() method returns a list of words that are palindromes")
    void testIfgetStringsWhichPalindromesMethodReturnsListOfWordsThatArePalindromes() throws IOException {
        when(filePartReader.readLines()).thenReturn("pierwsza linijka\n" +
                "druga linijka\n" +
                "trzecia linijka tutaj costam\n" +
                "czwarta i za chwile bedzie piata\n" +
                "i jest\n" +
                "kajak palindrom");
        List<String> result = Arrays.asList("i", "i", "kajak");

        List actual = fileWordAnalyzer.getStringsWhichPalindromes();

        assertEquals(result, actual);
    }


    @Test
    @DisplayName("newTest")
    void newTest() throws IOException {
        when(filePartReader.readLines()).thenReturn("");

        List actual = fileWordAnalyzer.getWordsContainingSubstring("aaaaa");

        assertEquals(new ArrayList<>(), actual);
    }
}
