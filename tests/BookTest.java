import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class BookTest {

    List<String> listOfWords = new ArrayList<>();
    LargestWord largestWord = new LargestWord();
    MostUsedWord mostUsedWord = new MostUsedWord();

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
        FakeBook fakeBook = new FakeBook(listOfWords, largestWord, mostUsedWord);

        int result = fakeBook.averageWordLength();

        assertEquals(4, result);


    }

    @Test
    public void largestWord() {
        FakeBook fakeBook = new FakeBook(listOfWords, largestWord, mostUsedWord);

        LargestWord largest = fakeBook.largestWord();

        assertEquals("tractor", largest.getLargestWords());
        assertEquals(7, largest.getLength());
    }

    @Test
    public void mostUsedWord() {
        FakeBook fakeBook = new FakeBook(listOfWords, largestWord, mostUsedWord);

        MostUsedWord mostUsedWord = fakeBook.frequencyOfWords();

        assertEquals("dog", mostUsedWord.getMostUsedWord());
        assertEquals(3, mostUsedWord.getFrequency());
    }


}
