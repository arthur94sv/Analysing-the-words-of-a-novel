import java.io.File;
import java.util.*;


public class Book {
    private final Object lockAvgWord = new Object();
    private final Object lockLargestWord = new Object();
    private final Object lockMostFreqWord = new Object();

    private File file;
    private Scanner scanner;
    private List<String> listOfWords;
    private Map<String, Integer> frequencyOfWords;

    private LargestWord largestWord;
    private MostUsedWord mostUsedWord;


    public Book(File file, Scanner scannerOfTheLines, LargestWord largestWord, MostUsedWord mostUsedWord) {
        this.file = file;
        this.scanner = scannerOfTheLines;
        this.largestWord = largestWord;
        this.mostUsedWord = mostUsedWord;
        this.listOfWords = new ArrayList<>();
        this.frequencyOfWords = new HashMap<>();
        addTheWordsIntoArray();
    }


    private void addTheWordsIntoArray() {
        while (scanner.hasNext()) {

            String word = scanner.next();

            if (!word.equals(""))
                listOfWords.add(word);

        }
    }


    public int averageWordLength() {
        synchronized (lockAvgWord) {
            int totalLength = 0;

            for (String word : listOfWords) {
                totalLength += word.length();
            }

            return totalLength / listOfWords.size();

        }
    }


    public LargestWord largestWord() {
        synchronized (lockLargestWord) {
            //Initialising the variable with the first word in the array
            largestWord.setLargestWords(listOfWords.get(0));

            for (String word : listOfWords) {
                if (word.length() > largestWord.getLargestWords().length())
                    largestWord.setLargestWords(word);
            }

            largestWord.setLength(largestWord.getLargestWords().length());

            return largestWord;


        }
    }


    public MostUsedWord mostUsedWord() {
        synchronized (lockMostFreqWord) {
            int initialFrequency = 1;

            // The ArrayList of words is iterate and when a word is encountered for the very first time, the mentioned word
            //is introduce into the HashMap with the initial frequency of 1;
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
    }

    private MostUsedWord iterateThroughMap() {

        int freqOfTheMostUsedWord = 1;
        String mostUsedWordInTheBook = null;
        for (Map.Entry<String, Integer> entry : frequencyOfWords.entrySet()) {
            if (freqOfTheMostUsedWord < entry.getValue()) {
                freqOfTheMostUsedWord = entry.getValue();
                mostUsedWordInTheBook = entry.getKey();
            }
        }


        mostUsedWord.setMostUsedWord(mostUsedWordInTheBook);
        mostUsedWord.setFrequency(freqOfTheMostUsedWord);

        return mostUsedWord;
    }


    public List<String> getListOfWords() {
        return listOfWords;
    }
}
