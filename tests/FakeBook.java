import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FakeBook {
    private List<String> listOfWords;
    private Map<String, Integer> frequencyOfWords;
    private LargestWord largestWord;
    private MostUsedWord mostUsedWord;


    public FakeBook(List<String> listOfWords, LargestWord largestWord, MostUsedWord mostUsedWord) {
        this.listOfWords = listOfWords;
        this.largestWord = largestWord;
        this.mostUsedWord = mostUsedWord;
        this.frequencyOfWords = new HashMap<>();
    }

    public int averageWordLength() {

        int totalLength = 0;

        for (String word : listOfWords) {
            totalLength += word.length();
        }


        return totalLength / listOfWords.size();

    }


    public LargestWord largestWord() {

        //Initialising the variable with the first word in the array
        largestWord.setLargestWords(listOfWords.get(0));

        for (String word : listOfWords) {
            if (word.length() > largestWord.getLargestWords().length())
                largestWord.setLargestWords(word);
        }

        largestWord.setLength(largestWord.getLargestWords().length());

        return largestWord;

    }




    public MostUsedWord frequencyOfWords() {

        int initialFrequency = 1;
;
        for (String word : listOfWords) {
            if (!frequencyOfWords.containsKey(word)) {
                frequencyOfWords.put(word, initialFrequency);

            } else {
                int newFreq = frequencyOfWords.get(word);
                newFreq++;
                frequencyOfWords.put(word, newFreq);

            }

        }

       return iterateThroughMap();


    }

    private MostUsedWord iterateThroughMap() {

        int freqOfTheMostUsedWord = 1;
        String mostUsedWords = null;
        for (Map.Entry<String, Integer> entry : frequencyOfWords.entrySet()) {
            if (freqOfTheMostUsedWord < entry.getValue()) {
                freqOfTheMostUsedWord = entry.getValue();
                mostUsedWords = entry.getKey();
            }
        }
        mostUsedWord.setMostUsedWord(mostUsedWords);
        mostUsedWord.setFrequency(freqOfTheMostUsedWord);

        return mostUsedWord;


    }


}
