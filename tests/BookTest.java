import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class BookTest {

    List<String> listOfWords = new ArrayList<>();
    LargestWord largestWord = new LargestWord();
    MostUsedWord mostUsedWord = new MostUsedWord();

    FakeBook fakeBook = new FakeBook(listOfWords, largestWord, mostUsedWord);

    @Before
    public void setUp() {
        listOfWords.add("dog");
        listOfWords.add("dog");
        listOfWords.add("dog");
        listOfWords.add("bull");
        listOfWords.add("bull");
        listOfWords.add("tractor");
    }

    @Test
    public void averageWordLength() {
        int result = fakeBook.averageWordLength();

        assertEquals(4, result);


    }

    @Test
    public void largestWord() {
        LargestWord largest = fakeBook.largestWord();

        assertEquals("tractor", largest.getLargestWords());
        assertEquals(7, largest.getLength());
    }

    @Test
    public void mostUsedWord() {
        MostUsedWord mostUsedWord = fakeBook.frequencyOfWords();

        assertEquals("dog", mostUsedWord.getMostUsedWord());
        assertEquals(3, mostUsedWord.getFrequency());
    }


}
